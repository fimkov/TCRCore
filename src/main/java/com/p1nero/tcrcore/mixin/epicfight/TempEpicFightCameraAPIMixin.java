package com.p1nero.tcrcore.mixin.epicfight;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.fml.ModList;
import net.shelmarow.betterlockon.client.control.BLOCameraSetting;
import net.shelmarow.betterlockon.compat.HandlerShoulderSurfingCompat;
import net.shelmarow.betterlockon.config.LockOnConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.client.camera.EpicFightCameraAPI;
import yesman.epicfight.api.client.event.types.BuildCameraTransform;
import yesman.epicfight.api.client.event.types.CoupleTPSCamera;
import yesman.epicfight.api.client.event.types.LockOnEvent;
import yesman.epicfight.api.client.input.InputManager;
import yesman.epicfight.api.client.input.action.EpicFightInputAction;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.config.ClientConfig;
import yesman.epicfight.config.ClientConfig.TPSType;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.ZoomInType;

@Mixin(
    value = {EpicFightCameraAPI.class},
    remap = false
)
public abstract class TempEpicFightCameraAPIMixin {
    @Unique
    private static final float MAX_ZOOM_TICK = 8.0F;
    @Final
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private float cameraXRot;
    @Shadow
    private @Nullable HitResult crosshairHitResult;
    @Shadow
    private LivingEntity focusingEntity;
    @Shadow
    private boolean lockingOnTarget;
    @Shadow
    private float cameraYRot;
    @Shadow
    private int fpvLerpTick;
    @Shadow
    private float fpvXRot;
    @Shadow
    private float fpvYRot;
    @Shadow
    private int zoomTick;
    @Shadow
    private boolean zoomingIn;
    @Shadow
    private float cameraXRotO;
    @Shadow
    private float cameraYRotO;
    @Shadow
    private int quickShiftDelay;
    @Shadow
    private double accumulatedX;
    @Unique
    public boolean blo$isAiming = false;
    @Unique
    public int blo$maxAimingTick = 8;
    @Unique
    public int blo$aimingTick;
    @Unique
    public int blo$maxUnlockDelayTick = 60;
    @Unique
    public int blo$unlockDelayTick;

    @Shadow
    public abstract boolean isLockingOnTarget();

    @Shadow
    public abstract boolean isTPSMode();

    @Shadow
    protected abstract boolean predicateFocusableEntity(Entity var1);

    @Shadow
    public abstract void zoomIn();

    @Shadow
    public abstract void zoomOut(int var1);

    @Shadow
    public abstract void setLockOn(boolean var1);

    @Shadow
    protected abstract void sendTargeting(LivingEntity var1);

    @Shadow
    public abstract boolean setNextLockOnTarget(int var1, boolean var2, boolean var3);

    @Shadow
    public abstract boolean isFirstPerson();

    @Shadow
    protected abstract CoupleTPSCamera predicateCouplingPlayer();

    @Shadow
    public abstract void setCameraRotations(float var1, float var2, boolean var3);

    @Shadow
    public abstract boolean isLerpingFpv();

    @Shadow
    public abstract void fireCameraBuildPost(Camera var1, float var2);

    @Shadow
    protected abstract Matrix4f getCompactProjectionMatrix();

    @Unique
    private float blo$getOffset() {
        float offset = 0.0F;
        if (this.minecraft.options.keySprint.isDown() && !this.minecraft.options.keyUse.isDown() && this.minecraft.player != null) {
            Input input = this.minecraft.player.input;
            float dir = 0.0F;
            boolean forward = input.up && !input.down;
            boolean backward = !input.up && input.down;
            if (input.left && forward) {
                dir = 45.0F;
            } else if (input.left && !backward) {
                dir = 90.0F;
            } else if (input.left) {
                dir = 135.0F;
            } else if (input.right && forward) {
                dir = -45.0F;
            } else if (input.right && !backward) {
                dir = -90.0F;
            } else if (input.right) {
                dir = -135.0F;
            } else if (backward) {
                dir = 180.0F;
            }

            offset += dir;
        }

        return offset;
    }

    @Unique
    private Vec3 blo$getCameraOffset(Camera camera, float partialTick) {
        float offsetX = Mth.lerp(partialTick, BLOCameraSetting.cameraOffsetXO, BLOCameraSetting.cameraOffsetX);
        float offsetY = Mth.lerp(partialTick, BLOCameraSetting.cameraOffsetYO, BLOCameraSetting.cameraOffsetY);
        float offsetZ = Mth.lerp(partialTick, BLOCameraSetting.cameraOffsetZO, BLOCameraSetting.cameraOffsetZ);
        return new Vec3((double)offsetX, (double)offsetY, (double)offsetZ);
    }

    @Inject(
        method = {"getYRotForHead"},
        at = {@At("RETURN")},
        cancellable = true
    )
    private void onGetYRotForHead(Player player, CallbackInfoReturnable<Float> cir) {
        if (this.isLockingOnTarget() && this.minecraft.options.keySprint.isDown() && !this.minecraft.options.keyUse.isDown()) {
            cir.setReturnValue(player.getYRot());
        }

    }

    @Inject(
        method = {"setNextLockOnTarget(IZZ)Z"},
        at = {@At("HEAD")},
        cancellable = true
    )
    private void onSetNextLockOnTarget(int direction, boolean necessarilyLockingOn, boolean sendChange, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        if (!this.lockingOnTarget && necessarilyLockingOn) {
            cir.setReturnValue(false);
        } else {
            List<Entity> entitiesInLevel = new ArrayList();
            Iterable<Entity> var10000 = this.minecraft.level.entitiesForRendering();
            Objects.requireNonNull(entitiesInLevel);
            var10000.forEach(entitiesInLevel::add);
            Vec3 cameraLocation = this.minecraft.gameRenderer.getMainCamera().getPosition();
            Matrix4f compactProjection = this.getCompactProjectionMatrix();
            double lockOnRange = (Double)LockOnConfig.MAX_TARGET_SELECT_DISTANCE.get();
            Optional<Pair<LivingEntity, Float>> next = entitiesInLevel.stream().filter((entity) -> this.predicateFocusableEntity(entity) && !entity.is(this.focusingEntity) && MathUtils.canBeSeen(entity, this.minecraft.player, lockOnRange) && this.minecraft.getEntityRenderDispatcher().shouldRender(entity, this.minecraft.levelRenderer.getFrustum(), cameraLocation.x(), cameraLocation.y(), cameraLocation.z()) && !entity.hasIndirectPassenger(this.minecraft.player) && entity.distanceToSqr(this.minecraft.player) < lockOnRange * lockOnRange).map((entity) -> Pair.of((LivingEntity)entity, MathUtils.worldToScreenCoord(compactProjection, this.minecraft.gameRenderer.getMainCamera(), entity.getBoundingBox().getCenter()).x)).filter((pair) -> (Float)pair.getSecond() >= -1.0F && (Float)pair.getSecond() <= 1.0F && (direction == 0 || MathUtils.getSign((double)(Float)pair.getSecond()) == MathUtils.getSign((double)direction))).min((p1, p2) -> Float.compare(Math.abs((Float)p1.getSecond()), Math.abs((Float)p2.getSecond())));
            next.ifPresent((pair) -> {
                this.focusingEntity = (LivingEntity)pair.getFirst();
                if (sendChange) {
                    this.sendTargeting(this.focusingEntity);
                }

            });
            cir.setReturnValue(next.isPresent());
        }
    }

    @Inject(
        method = {"preClientTick"},
        at = {@At("HEAD")}
    )
    private void onPreClientTick(CallbackInfo ci) {
        BLOCameraSetting.cameraOffsetXO = BLOCameraSetting.cameraOffsetX;
        BLOCameraSetting.cameraOffsetYO = BLOCameraSetting.cameraOffsetY;
        BLOCameraSetting.cameraOffsetZO = BLOCameraSetting.cameraOffsetZ;
        if (this.blo$isAiming && this.blo$aimingTick < this.blo$maxAimingTick) {
            ++this.blo$aimingTick;
        } else if (!this.blo$isAiming && this.blo$aimingTick > 0) {
            --this.blo$aimingTick;
        }

    }

    @Inject(
        method = {"setLockOn"},
        at = {@At(
    value = "INVOKE",
    target = "Lnet/minecraft/client/player/LocalPlayer;setXRot(F)V"
)},
        remap = true
    )
    private void onSetLockOn(CallbackInfo ci) {
        this.minecraft.player.setYRot(this.cameraYRot);
        BLOCameraSetting.cameraOffsetX = 0.0F;
        BLOCameraSetting.cameraOffsetY = 0.0F;
        BLOCameraSetting.cameraOffsetZ = 0.0F;
        BLOCameraSetting.cameraOffsetXO = BLOCameraSetting.cameraOffsetX;
        BLOCameraSetting.cameraOffsetYO = BLOCameraSetting.cameraOffsetY;
        BLOCameraSetting.cameraOffsetZO = BLOCameraSetting.cameraOffsetZ;
    }

    @Redirect(
        method = {"setLockOn"},
        at = @At(
    value = "INVOKE",
    target = "Lyesman/epicfight/api/client/camera/EpicFightCameraAPI;setCameraRotations(FFZ)V"
)
    )
    private void onSetLockOn2(EpicFightCameraAPI instance, float xRot, float yRot, boolean syncOld) {
        if (ModList.get().isLoaded("shouldersurfing")) {
            HandlerShoulderSurfingCompat.handlerCam();
        } else {
            instance.setCameraRotations(xRot, yRot, syncOld);
        }

    }

    @Inject(
        method = {"turnCamera"},
        at = {@At("HEAD")},
        cancellable = true
    )
    private void onTurnCamera(double dy, double dx, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        MutableBoolean cancel = new MutableBoolean(false);
        EpicFightCapabilities.getUnparameterizedEntityPatch(this.minecraft.player, LocalPlayerPatch.class).ifPresent((playerpatch) -> {
            cancel.setValue(this.minecraft.options.getCameraType() != CameraType.FIRST_PERSON && (this.isTPSMode() || this.lockingOnTarget));
            if (cancel.booleanValue()) {
                float modifier = this.lockingOnTarget && !InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY) ? (ClientConfig.lockOnQuickShift ? 0.005F : 0.0F) : 0.15F;
                this.setCameraRotations(Mth.clamp(this.cameraXRot + (float)dx * modifier, -90.0F, 90.0F), this.cameraYRot + (float)dy * modifier, false);
                if (ClientConfig.lockOnQuickShift && this.quickShiftDelay <= 0) {
                    this.accumulatedX += -dy * (double)0.15F;
                    if (Math.abs(this.accumulatedX) > (double)20.0F && this.lockingOnTarget) {
                        this.setNextLockOnTarget(Mth.sign(this.accumulatedX), true, true);
                        this.accumulatedX = (double)0.0F;
                        this.quickShiftDelay = 4;
                    }
                }

                this.accumulatedX *= 0.98;
            }

        });
        cir.setReturnValue(cancel.booleanValue());
    }

    @Inject(
        method = {"postClientTick"},
        at = {@At("HEAD")},
        cancellable = true
    )
    private void rewroteClientTick(CallbackInfo ci) {
        ci.cancel();
        if (!this.minecraft.isPaused() && this.minecraft.player != null) {
            EpicFightCapabilities.getUnparameterizedEntityPatch(this.minecraft.player, LocalPlayerPatch.class).ifPresent((playerpatchx) -> {
                CapabilityItem mainHandItemCap = playerpatchx.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND);
                CapabilityItem offhandItemCap = playerpatchx.getAdvancedHoldingItemCapability(InteractionHand.OFF_HAND);
                CapabilityItem.ZoomInType rangeWeaponZoomInType = !mainHandItemCap.isEmpty() && mainHandItemCap.getZoomInType() != ZoomInType.NONE ? mainHandItemCap.getZoomInType() : offhandItemCap.getZoomInType();
                switch (rangeWeaponZoomInType) {
                    case ALWAYS:
                        this.zoomIn();
                        this.blo$isAiming = true;
                        break;
                    case USE_TICK:
                        this.blo$isAiming = ((LocalPlayer)playerpatchx.getOriginal()).getUseItemRemainingTicks() > 0;
                        break;
                    case AIMING:
                        this.blo$isAiming = playerpatchx.getClientAnimator().isAiming();
                        break;
                    case CUSTOM:
                        this.blo$isAiming = true;
                        break;
                    default:
                        this.zoomOut(1);
                        this.blo$isAiming = false;
                }

            });
            double pickRange = (double)(Integer)this.minecraft.options.renderDistance().get() * (double)16.0F;
            Camera mainCamera = this.minecraft.gameRenderer.getMainCamera();
            Vec3 cameraPos = mainCamera.getPosition();
            Vec3 lookVec = new Vec3(mainCamera.getLookVector());
            Vec3 rayEed = cameraPos.add(lookVec.x * pickRange, lookVec.y * pickRange, lookVec.z * pickRange);
            LocalPlayer localPlayer = this.minecraft.player;
            this.crosshairHitResult = localPlayer.level().clip(new ClipContext(cameraPos, rayEed, Block.OUTLINE, Fluid.NONE, localPlayer));
            double entityPickRange = (Double)LockOnConfig.MAX_TARGET_SELECT_DISTANCE.get() * (Double)LockOnConfig.MAX_TARGET_SELECT_DISTANCE.get();
            AABB aabb = localPlayer.getBoundingBox().move(cameraPos.subtract(localPlayer.getEyePosition(1.0F))).expandTowards(lookVec.scale(entityPickRange)).inflate((double)1.0F, (double)1.0F, (double)1.0F);
            EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(localPlayer, cameraPos, rayEed, aabb, (entity -> entity.isPickable() && !entity.isSpectator()), entityPickRange);
            if (entityHitResult != null) {
                this.crosshairHitResult = entityHitResult;
                if (!entityHitResult.getEntity().is(this.focusingEntity)) {
                    Entity parent = entityHitResult.getEntity();
                    if (parent instanceof LivingEntity) {
                        LivingEntity livingentity = (LivingEntity)parent;
                        if (!(entityHitResult.getEntity() instanceof ArmorStand) && (!this.lockingOnTarget || InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY))) {
                            this.focusingEntity = livingentity;
                        }
                    } else {
                        parent = entityHitResult.getEntity();
                        if (parent instanceof PartEntity) {
                            PartEntity<?> partEntity = (PartEntity)parent;
                            parent = partEntity.getParent();
                            if (parent instanceof LivingEntity) {
                                LivingEntity parentLivingEntity = (LivingEntity)parent;
                                if (!this.lockingOnTarget || InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY)) {
                                    this.focusingEntity = parentLivingEntity;
                                }
                            }
                        } else {
                            this.setLockOn(false);
                            this.focusingEntity = null;
                        }
                    }

                    if (this.focusingEntity != null) {
                        this.sendTargeting(this.focusingEntity);
                    }
                }
            }

            boolean tpsMode = this.isTPSMode();
            if (tpsMode) {
                Vec3 view = new Vec3(mainCamera.getLookVector());
                if (view.dot(this.crosshairHitResult.getLocation().subtract(localPlayer.getEyePosition()).normalize()) < -0.1) {
                    this.crosshairHitResult = BlockHitResult.miss(cameraPos.add(lookVec.x * (double)50.0F, lookVec.y * (double)50.0F, lookVec.z * (double)50.0F), Direction.UP, BlockPos.ZERO);
                    if (!this.lockingOnTarget && this.focusingEntity != null) {
                        this.focusingEntity = null;
                        this.sendTargeting((LivingEntity)null);
                    }
                }

                if (this.focusingEntity != null) {
                    double dot = view.dot(this.focusingEntity.getEyePosition().subtract(localPlayer.getEyePosition()));
                    if (dot < -0.1 && !this.lockingOnTarget) {
                        this.focusingEntity = null;
                        this.sendTargeting((LivingEntity)null);
                    }
                }
            }

            if (this.focusingEntity != null) {
                if (this.lockingOnTarget && !this.focusingEntity.isAlive()) {
                    boolean releaseLockOn = !ClientConfig.lockOnQuickShift || !this.setNextLockOnTarget(0, true, true);
                    if (releaseLockOn) {
                        this.setLockOn(false);
                    }
                } else {
                    double distance = this.minecraft.player.distanceToSqr(this.focusingEntity.position());
                    double maxLockOnDistance = (Double)LockOnConfig.MAX_LOCK_ON_DISTANCE.get() * (Double)LockOnConfig.MAX_LOCK_ON_DISTANCE.get();
                    boolean canBeSeen = MathUtils.canBeSeen(this.focusingEntity, this.minecraft.player, maxLockOnDistance);
                    if (canBeSeen) {
                        this.blo$unlockDelayTick = 0;
                    } else if (this.blo$unlockDelayTick < this.blo$maxUnlockDelayTick) {
                        ++this.blo$unlockDelayTick;
                    }

                    if (this.focusingEntity.isInvisibleTo(localPlayer) || distance > maxLockOnDistance || this.blo$unlockDelayTick >= this.blo$maxUnlockDelayTick || !this.lockingOnTarget && this.focusingEntity.position().subtract(mainCamera.getPosition()).normalize().dot(new Vec3(mainCamera.getLookVector())) < Mth.clampedLerp(0.8, 0.96, Mth.inverseLerp(Mth.clamp(distance, (double)9.0F, (double)64.0F), (double)9.0F, (double)64.0F))) {
                        if (this.lockingOnTarget) {
                            this.setLockOn(false);
                        }

                        this.blo$unlockDelayTick = 0;
                        this.focusingEntity = null;
                        this.sendTargeting((LivingEntity)null);
                    }
                }
            }

            if (this.isFirstPerson() && this.isLerpingFpv()) {
                --this.fpvLerpTick;
                if (!this.isLerpingFpv()) {
                    this.minecraft.player.setXRot(this.fpvXRot);
                    this.minecraft.player.setYRot(this.fpvYRot);
                }
            } else if (!this.isTPSMode() && !this.lockingOnTarget) {
                this.cameraXRot = this.minecraft.player.getXRot();
                this.cameraYRot = this.minecraft.player.getYRot();
            } else {
                LocalPlayerPatch playerpatch = (LocalPlayerPatch)EpicFightCapabilities.getEntityPatch(localPlayer, LocalPlayerPatch.class);
                float minPitch = ((Double)LockOnConfig.MAX_PITCH.get()).floatValue();
                float maxPitch = ((Double)LockOnConfig.MIN_PITCH.get()).floatValue();
                float pitchOffset = ((Double)LockOnConfig.PITCH_OFFSET.get()).floatValue();
                float clamp = 30.0F;
                float desiredXRot = 0.0F;
                float desiredYRot = 0.0F;
                if (this.focusingEntity != null && this.lockingOnTarget && !this.isLerpingFpv() && !InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY)) {
                    Vec3 lockEnd;
                    Vec3 lockStart;
                    if (tpsMode) {
                        double toTargetDistanceSqr = localPlayer.position().distanceToSqr(this.focusingEntity.position());
                        lockStart = MathUtils.lerpVector(localPlayer.getEyePosition(), cameraPos, (float)Mth.clampedMap(toTargetDistanceSqr, (double)1.0F, (double)18.0F, (double)0.2F, (double)1.0F));
                        lockEnd = MathUtils.lerpVector(this.focusingEntity.getEyePosition(), this.focusingEntity.getBoundingBox().getCenter(), (float)Mth.clampedMap(toTargetDistanceSqr, (double)0.0F, (double)18.0F, (double)0.5F, (double)1.0F));
                    } else {
                        lockStart = localPlayer.getEyePosition();
                        lockEnd = this.focusingEntity.getEyePosition();
                    }

                    Vec3 toTarget = lockEnd.subtract(lockStart);
                    float xRot = (float)MathUtils.getXRotOfVector(toTarget);
                    float yRot = (float)MathUtils.getYRotOfVector(toTarget);
                    float originalXRot = xRot;
                    CameraType cameraType = this.minecraft.options.getCameraType();
                    Vec3 cameraToTarget = lockEnd.subtract(cameraPos);
                    if (!cameraType.isFirstPerson()) {
                        xRot = (float)MathUtils.getXRotOfVector(cameraToTarget);
                        xRot = Mth.clamp(xRot + pitchOffset, minPitch, maxPitch);
                    }

                    if (cameraType == CameraType.THIRD_PERSON_BACK && (Boolean)LockOnConfig.ENABLE_DYNAMIC_CAMERA.get()) {
                        float maxXRot = -10.0F;
                        float minXRot = -70.0F;
                        float progress = Mth.clamp((maxXRot - originalXRot) / (maxXRot - minXRot), 0.0F, 1.0F);
                        float distance2D = (new Vec2((float)localPlayer.position().x, (float)localPlayer.position().z)).distanceToSqr(new Vec2((float)this.focusingEntity.position().x, (float)this.focusingEntity.position().z));
                        float distance = Mth.sqrt(distance2D);
                        float distanceWeight = Mth.clampedMap(distance, 0.5F, 4.0F, 0.0F, 1.0F);
                        float length = -progress * ((Double)LockOnConfig.MAX_DYNAMIC_CAMERA_X.get()).floatValue() * distanceWeight;
                        Vec3 horizontalForward = (new Vec3(cameraToTarget.x, (double)0.0F, cameraToTarget.z)).normalize().scale((double)length);
                        BLOCameraSetting.cameraOffsetX = (float)horizontalForward.x;
                        BLOCameraSetting.cameraOffsetZ = (float)horizontalForward.z;
                        distanceWeight = Mth.clampedMap(distance, 0.0F, 3.0F, 0.0F, 1.0F);
                        float distanceY = (float)(this.focusingEntity.getEyePosition().y - localPlayer.getEyePosition().y) * distanceWeight;
                        BLOCameraSetting.cameraOffsetY = Mth.clamp(distanceY, 0.0F, ((Double)LockOnConfig.MAX_DYNAMIC_CAMERA_Y.get()).floatValue()) * progress;
                        float aimProgress = (float)this.blo$aimingTick / (float)this.blo$maxAimingTick;
                        Vec3f relocation = (new Vec3f((float)ClientConfig.cameraHorizontalLocation * 0.2F, (float)ClientConfig.cameraVerticalLocation * 0.2F, 0.0F)).scale(aimProgress);
                        OpenMatrix4f.transform3v(OpenMatrix4f.createRotatorDeg(-yRot, Vec3f.Y_AXIS), relocation, relocation);
                        BLOCameraSetting.cameraOffsetX *= 1.0F - aimProgress;
                        BLOCameraSetting.cameraOffsetY *= 1.0F - aimProgress;
                        BLOCameraSetting.cameraOffsetZ *= 1.0F - aimProgress;
                        if (!this.isTPSMode()) {
                            BLOCameraSetting.cameraOffsetX += relocation.x;
                            BLOCameraSetting.cameraOffsetY += relocation.y;
                            BLOCameraSetting.cameraOffsetZ += relocation.z;
                        }
                    }

                    float xLerp = Mth.clamp(Mth.wrapDegrees(xRot - this.cameraXRot) * 0.4F, -clamp, clamp);
                    float yLerp = Mth.clamp(Mth.wrapDegrees(yRot - this.cameraYRot) * 0.4F, -clamp, clamp);
                    this.setCameraRotations(this.cameraXRot + xLerp, this.cameraYRot + yLerp, false);
                    Vec3 playerToTarget = lockEnd.subtract(localPlayer.getEyePosition());
                    desiredXRot = (float)MathUtils.getXRotOfVector(playerToTarget);
                    desiredYRot = (float)MathUtils.getYRotOfVector(playerToTarget);
                } else if (this.lockingOnTarget && InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY)) {
                    desiredXRot = this.cameraXRot;
                    desiredYRot = this.cameraYRot;
                } else if (tpsMode) {
                    CoupleTPSCamera coupleCameraEvent = this.predicateCouplingPlayer();
                    boolean shouldCoupling = coupleCameraEvent.shouldCoupleCamera();
                    if (!(Mth.abs(Mth.wrapDegrees(this.cameraYRot - localPlayer.yBodyRot)) <= 51.0F) && !shouldCoupling) {
                        desiredXRot = 0.0F;
                        desiredYRot = localPlayer.yBodyRot;
                        clamp = 15.0F;
                    } else if (coupleCameraEvent.isOnlyMoving()) {
                        Vec2 movemoventPulse = localPlayer.input.getMoveVector();
                        desiredYRot = this.cameraYRot + (float)MathUtils.getYRotOfVector(new Vec3((double)movemoventPulse.x, (double)0.0F, (double)movemoventPulse.y));
                        desiredXRot = desiredYRot == this.cameraYRot ? this.cameraXRot : 0.0F;
                    } else {
                        Vec3 toHitResult;
                        if (this.lockingOnTarget) {
                            toHitResult = this.focusingEntity.getEyePosition();
                        } else if (this.crosshairHitResult.getType() == Type.MISS) {
                            double delta = (double)(Mth.clamp(localPlayer.getXRot(), -30.0F, 0.0F) / -30.0F);
                            double lookVecScale = Mth.clampedLerp((double)30.0F, (double)75.0F, delta);
                            toHitResult = cameraPos.add(lookVec.scale(lookVecScale));
                        } else {
                            toHitResult = this.crosshairHitResult.getLocation();
                        }

                        toHitResult = toHitResult.subtract(localPlayer.getEyePosition());
                        desiredXRot = (float)MathUtils.getXRotOfVector(toHitResult);
                        desiredYRot = shouldCoupling ? (Math.abs(this.cameraXRot) > 80.0F ? this.cameraYRot : (float)MathUtils.getYRotOfVector(toHitResult)) : this.cameraYRot;
                    }
                }

                if (this.focusingEntity != null && this.lockingOnTarget) {
                    EpicFightCameraAPI cameraAPI = (EpicFightCameraAPI)(Object)this;
                    LockOnEvent.Tick lockOnEventTick = new LockOnEvent.Tick(cameraAPI, this.focusingEntity, desiredXRot, desiredYRot);
                    yesman.epicfight.api.client.event.EpicFightClientHooks.Camera.LOCK_ON_TICK.post(lockOnEventTick);
                    desiredXRot = lockOnEventTick.getModifiedXRot();
                    desiredYRot = lockOnEventTick.getModifiedYRot();
                }

                if ((playerpatch == null || !playerpatch.getEntityState().turningLocked() || playerpatch.getEntityState().lockonRotate()) && (tpsMode || this.minecraft.options.getCameraType() == CameraType.THIRD_PERSON_BACK && this.lockingOnTarget)) {
                    float xDelta = Mth.clamp(Mth.wrapDegrees(desiredXRot - localPlayer.getXRot()), -clamp, clamp);
                    float yDelta = Mth.wrapDegrees(desiredYRot - localPlayer.getYRot()) - this.blo$getOffset();
                    if (this.isLockingOnTarget() && this.minecraft.options.keySprint.isDown() && !this.minecraft.options.keyUse.isDown()) {
                        localPlayer.setXRot(0.0F);
                    } else {
                        localPlayer.setXRot(localPlayer.getXRot() + xDelta);
                    }

                    localPlayer.setYRot(localPlayer.getYRot() + yDelta);
                }
            }

        }
    }

    @Inject(
        method = {"setupCamera"},
        at = {@At("HEAD")},
        cancellable = true
    )
    private void rewroteSetupCamera(Camera camera, float partialTick, CallbackInfoReturnable<BuildCameraTransform.Pre> cir) {
        cir.cancel();
        EpicFightCameraAPI cameraAPI = (EpicFightCameraAPI)(Object)this;
        BuildCameraTransform.Pre event = new BuildCameraTransform.Pre(cameraAPI, camera, partialTick);
        if (!camera.getEntity().is(this.minecraft.player)) {
            event.cancel();
            BLOCameraSetting.cameraPos = camera.getPosition();
            cir.setReturnValue(event);
        } else {
            yesman.epicfight.api.client.event.EpicFightClientHooks.Camera.BUILD_TRANSFORM_PRE.post(event);
            if (event.hasCanceled()) {
                BLOCameraSetting.cameraPos = camera.getPosition();
                cir.setReturnValue(event);
            } else if (this.isTPSMode()) {
                float partialZoomTick = this.zoomTick == 0 ? 0.0F : Math.min((float)this.zoomTick + (this.zoomingIn ? partialTick : -partialTick), 7.0F);
                float delta = ClientConfig.getCameraMode() == TPSType.WHEN_AIMING ? partialZoomTick / 7.0F : 1.0F;
                float xRot = Mth.rotLerp(delta, this.minecraft.player.getXRot(), Mth.rotLerp(partialTick, this.cameraXRotO, this.cameraXRot));
                float yRot = Mth.rotLerp(delta, this.minecraft.player.getYRot(), Mth.rotLerp(partialTick, this.cameraYRotO, this.cameraYRot));
                camera.setRotation(yRot, xRot);
                Vec3 cameraOffset = Vec3.ZERO;
                if (this.isLockingOnTarget()) {
                    cameraOffset = this.blo$getCameraOffset(camera, partialTick);
                }

                Vec3 playerPos = new Vec3(Mth.lerp((double)partialTick, camera.getEntity().xo, camera.getEntity().getX()), Mth.lerp((double)partialTick, camera.getEntity().yo, camera.getEntity().getY()) + Mth.lerp((double)partialTick, (double)camera.eyeHeightOld, (double)camera.eyeHeight), Mth.lerp((double)partialTick, camera.getEntity().zo, camera.getEntity().getZ()));
                Vec3f relocation = new Vec3f((float)ClientConfig.cameraHorizontalLocation * 0.2F, (float)ClientConfig.cameraVerticalLocation * 0.2F, 0.0F);
                OpenMatrix4f.transform3v(OpenMatrix4f.createRotatorDeg(-yRot, Vec3f.Y_AXIS), relocation, relocation);
                double cameraZoom = (double)ClientConfig.cameraZoom * (double)0.5F - (double)partialZoomTick * 0.1;
                double hitDistance = (double)1.0F;
                Vec3 baseOffset = new Vec3((double)relocation.x - (double)camera.getLookVector().x() * cameraZoom + cameraOffset.x, (double)relocation.y - (double)camera.getLookVector().y() * cameraZoom + cameraOffset.y, (double)relocation.z - (double)camera.getLookVector().z() * cameraZoom + cameraOffset.z);

                for(int i = 0; i < 8; ++i) {
                    float f = (float)((i & 1) * 2 - 1) * 0.1F;
                    float f1 = (float)((i >> 1 & 1) * 2 - 1) * 0.1F;
                    float f2 = (float)((i >> 2 & 1) * 2 - 1) * 0.1F;
                    Vec3 start = playerPos.add((double)f, (double)f1, (double)f2);
                    Vec3 end = playerPos.add(baseOffset).add((double)f, (double)f1, (double)f2);
                    double fullLength = start.distanceTo(end);
                    HitResult hit = this.minecraft.level.clip(new ClipContext(start, end, Block.VISUAL, Fluid.NONE, camera.getEntity()));
                    if (hit.getType() != Type.MISS) {
                        double ratio = hit.getLocation().distanceTo(start) / fullLength;
                        if (ratio < hitDistance) {
                            hitDistance = ratio;
                        }
                    }
                }

                Vec3 finalPos = playerPos.add(baseOffset.scale(hitDistance));
                double nearestX = finalPos.x;
                double nearestY = finalPos.y;
                double nearestZ = finalPos.z;
                if (Float.compare(1.0F, delta) == 0) {
                    camera.setPosition(nearestX, nearestY, nearestZ);
                } else {
                    camera.setRotation(this.minecraft.player.getViewYRot(partialTick), this.minecraft.player.getViewXRot(partialTick));
                    camera.setPosition(Mth.lerp((double)partialTick, this.minecraft.player.xo, this.minecraft.player.getX()), Mth.lerp((double)partialTick, this.minecraft.player.yo, this.minecraft.player.getY()) + (double)Mth.lerp(partialTick, camera.eyeHeightOld, camera.eyeHeight), Mth.lerp((double)partialTick, this.minecraft.player.zo, this.minecraft.player.getZ()));
                    camera.move(-camera.getMaxZoom((double)4.0F), (double)0.0F, (double)0.0F);
                    camera.setRotation(yRot, xRot);
                    camera.setPosition(camera.getPosition().x() + (nearestX - camera.getPosition().x()) * (double)delta, camera.getPosition().y() + (nearestY - camera.getPosition().y()) * (double)delta, camera.getPosition().z() + (nearestZ - camera.getPosition().z()) * (double)delta);
                }

                event.setVanillaCameraSetupCanceled(true);
                this.fireCameraBuildPost(camera, partialTick);
                BLOCameraSetting.cameraPos = camera.getPosition();
                cir.setReturnValue(event);
            } else {
                if (this.lockingOnTarget && this.focusingEntity != null) {
                    if (this.minecraft.options.getCameraType() == CameraType.THIRD_PERSON_BACK) {
                        float xRot = Mth.rotLerp(partialTick, this.cameraXRotO, this.cameraXRot);
                        float yRot = Mth.rotLerp(partialTick, this.cameraYRotO, this.cameraYRot);
                        camera.setRotation(yRot, xRot);
                        Vec3 playerPos = new Vec3(Mth.lerp((double)partialTick, camera.getEntity().xo, camera.getEntity().getX()), Mth.lerp((double)partialTick, camera.getEntity().yo, camera.getEntity().getY()) + (double)Mth.lerp(partialTick, camera.eyeHeightOld, camera.eyeHeight), Mth.lerp((double)partialTick, camera.getEntity().zo, camera.getEntity().getZ()));
                        Vec3 cameraOffset = this.blo$getCameraOffset(camera, partialTick);
                        Vec3 desiredPos = playerPos.add(cameraOffset.x, cameraOffset.y, cameraOffset.z);
                        double hitDistance = (double)1.0F;

                        for(int i = 0; i < 8; ++i) {
                            float f = (float)((i & 1) * 2 - 1);
                            float f1 = (float)((i >> 1 & 1) * 2 - 1);
                            float f2 = (float)((i >> 2 & 1) * 2 - 1);
                            f *= 0.1F;
                            f1 *= 0.1F;
                            f2 *= 0.1F;
                            Vec3 start = playerPos.add((double)f, (double)f1, (double)f2);
                            Vec3 end = desiredPos.add((double)f, (double)f1, (double)f2);
                            double fullLength = start.distanceTo(end);
                            HitResult hit = this.minecraft.level.clip(new ClipContext(start, end, Block.VISUAL, Fluid.NONE, camera.getEntity()));
                            if (hit.getType() != Type.MISS) {
                                double ratio = hit.getLocation().distanceTo(start) / fullLength;
                                if (ratio < hitDistance) {
                                    hitDistance = ratio;
                                }
                            }
                        }

                        Vec3 finalPos = playerPos.add((new Vec3(cameraOffset.x, cameraOffset.y, cameraOffset.z)).scale(hitDistance));
                        camera.setRotation(yRot, xRot);
                        camera.setPosition(finalPos.x, finalPos.y, finalPos.z);
                        if (camera.isDetached()) {
                            camera.move(-camera.getMaxZoom((double)4.0F), (double)0.0F, (double)0.0F);
                        } else {
                            Entity direction = camera.getEntity();
                            if (direction instanceof LivingEntity) {
                                LivingEntity livingEntity = (LivingEntity)direction;
                                if (livingEntity.isSleeping()) {
                                    Direction direction2 = ((LivingEntity)camera.getEntity()).getBedOrientation();
                                    camera.setRotation(direction2 != null ? direction2.toYRot() - 180.0F : 0.0F, 0.0F);
                                    camera.move((double)0.0F, 0.3, (double)0.0F);
                                }
                            }
                        }

                        event.setVanillaCameraSetupCanceled(true);
                        this.fireCameraBuildPost(camera, partialTick);
                        BLOCameraSetting.cameraPos = camera.getPosition();
                        cir.setReturnValue(event);
                        return;
                    }

                    if (this.minecraft.options.getCameraType() == CameraType.FIRST_PERSON) {
                        if (!InputManager.isActionActive(EpicFightInputAction.LOCK_ON_SHIFT_FREELY)) {
                            camera.getEntity().setXRot(Mth.rotLerp(partialTick, this.cameraXRotO, this.cameraXRot));
                            camera.getEntity().setYRot(Mth.rotLerp(partialTick, this.cameraYRotO, this.cameraYRot));
                        } else {
                            this.cameraXRot = camera.getEntity().getXRot();
                            this.cameraYRot = camera.getEntity().getYRot();
                        }
                    }
                }

                BLOCameraSetting.cameraPos = camera.getPosition();
                cir.setReturnValue(event);
            }
        }
    }
}

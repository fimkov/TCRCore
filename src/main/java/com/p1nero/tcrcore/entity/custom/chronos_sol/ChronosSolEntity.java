package com.p1nero.tcrcore.entity.custom.chronos_sol;

import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.component.DialogNode;
import com.p1nero.dialog_lib.api.entity.custom.IEntityNpc;
import com.p1nero.dialog_lib.api.entity.goal.LookAtConservingPlayerGoal;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.ItemUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * 曦轮
 */
public class ChronosSolEntity extends PathfinderMob implements IEntityNpc, GeoEntity {

    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.god_girl.idle2");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Nullable
    private Player conversingPlayer;

    public ChronosSolEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        TCRCapabilityProvider.syncPlayerDataToClient(serverPlayer);//保险
    }

    @Override
    public void tick() {
        super.tick();
        if(level() instanceof ServerLevel) {
            if(conversingPlayer != null && (conversingPlayer.isRemoved() || conversingPlayer.isDeadOrDying() || conversingPlayer.distanceTo(this) > 5)) {
                conversingPlayer = null;
            }
            //位置矫正保险
            if(tickCount % 100 == 0) {
                BlockPos myPos = this.getOnPos();
                if(myPos.getX() != WorldUtil.CHRONOS_SOL_BLOCK_POS.getX() || myPos.getZ() != WorldUtil.CHRONOS_SOL_BLOCK_POS.getZ()) {
                    this.setPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS).getCenter());
                }
            }
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float value) {
        if(source.getEntity() instanceof Player player && player.isCreative()) {
            player.displayClientMessage(Component.translatable("/summon " + ForgeRegistries.ENTITY_TYPES.getKey(this.getType())).withStyle(ChatFormatting.RED), false);
            this.discard();
        }
        return false;
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0f)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 114514f)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LookAtConservingPlayerGoal<>(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("current_quest_id", TCRQuestManager.getCurrentQuestId(player));
            this.sendDialogTo(serverPlayer, tag);
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public DialogueScreen getDialogueScreen(CompoundTag compoundTag) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        int currentQuestId = compoundTag.getInt("current_quest_id");
        TCRQuestManager.Quest currentQuest = TCRQuestManager.getQuestById(currentQuestId);
        StreamDialogueScreenBuilder treeBuilder = new StreamDialogueScreenBuilder(this, TCRCoreMod.MOD_ID);
        DialogueComponentBuilder dBuilder = treeBuilder.getComponentBuildr();

        //-1:继续
        //-2:结束对话
        DialogNode root = new DialogNode(dBuilder.ans(0), dBuilder.opt(-3));//冒险可还顺利？

        DialogNode aboutThisWorld = new DialogNode(dBuilder.ans(1), dBuilder.opt(0))
                .addChild(new DialogNode(dBuilder.ans(2), dBuilder.opt(-1))
                        .addChild(root)
                        .addLeaf(dBuilder.opt(-2)));

        DialogNode aboutAine = new DialogNode(dBuilder.ans(3), dBuilder.opt(1, TCREntities.AINE_IRIS.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));

        DialogNode aboutFerryGirl = new DialogNode(dBuilder.ans(4), dBuilder.opt(2, TCREntities.FERRY_GIRL.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));

        DialogNode aboutOrnn = new DialogNode(dBuilder.ans(5), dBuilder.opt(3, TCREntities.ORNN.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));

        if(TCRQuests.TALK_TO_CHRONOS_1.equals(currentQuest)) {
            //初次对话
            root = new DialogNode(dBuilder.ans(6));
            //你是何人
            DialogNode aboutMe = new DialogNode(dBuilder.ans(7, TCREntities.CHRONOS_SOL.get().getDescription()), dBuilder.opt(4))
                    .addChild(new DialogNode(dBuilder.ans(8), dBuilder.opt(5))
                            .addChild(root)
                            .addLeaf(dBuilder.opt(-2)));
            //关于接下来的行动
            DialogNode aboutNext = new DialogNode(dBuilder.ans(9), dBuilder.opt(6))
                    .addChild(new DialogNode(dBuilder.ans(10), dBuilder.opt(-1))
                            .addChild(new DialogNode(dBuilder.ans(11, TCREntities.ORNN.get().getDescription().copy().withStyle(ChatFormatting.GOLD), TCREntities.FERRY_GIRL.get().getDescription().copy().withStyle(ChatFormatting.GOLD)), dBuilder.opt(-1))
                                    .addLeaf(dBuilder.opt(-2), 1)));

            root.addChild(aboutMe)
                    .addChild(aboutThisWorld)
                    .addChild(aboutNext);
        } else {
            //默认的情况

            if(PlayerDataManager.aineTalked.get(localPlayer)) {
                root.addChild(aboutAine);
            }
            if(PlayerDataManager.ferryGirlTalked.get(localPlayer)) {
                root.addChild(aboutFerryGirl);
            }
            if(PlayerDataManager.ornnTalked.get(localPlayer)) {
                root.addChild(aboutOrnn);
            }

            root.addChild(aboutThisWorld);
        }

        return treeBuilder.buildWith(root);

    }

    @Override
    public void handleNpcInteraction(ServerPlayer player, int code) {
        //初次对话，准备启程
        if(code == 1) {
            TCRQuests.TALK_TO_CHRONOS_1.finish(player);

            TCRQuests.TALK_TO_FERRY_GIRL_1.start(player);
            TCRQuests.TALK_TO_ORNN_1.start(player);
            ItemUtil.addItem(player, TCRItems.RESONANCE_STONE.get(), 1, true);
            PlayerDataManager.chonosTalked.put(player, true);
        }
        this.setConversingPlayer(null);
    }

    @Override
    public void setConversingPlayer(@Nullable Player player) {
        this.conversingPlayer = player;
    }

    @Override
    public @Nullable Player getConversingPlayer() {
        return conversingPlayer;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends ChronosSolEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}

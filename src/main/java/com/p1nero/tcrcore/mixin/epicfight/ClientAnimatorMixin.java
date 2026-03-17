package com.p1nero.tcrcore.mixin.epicfight;

import com.p1nero.tcrcore.TCRCoreMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.client.animation.ClientAnimator;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

/**
 * 有跑酷就把冲刺改为跑步动画
 */
@Mixin(ClientAnimator.class)
public abstract class ClientAnimatorMixin extends Animator {

    public ClientAnimatorMixin(LivingEntityPatch<?> entitypatch) {
        super(entitypatch);
    }

    @Inject(method = "getLivingMotion", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$getLiving(LivingMotion motion, CallbackInfoReturnable<AssetAccessor<? extends StaticAnimation>> cir) {
        if(TCRCoreMod.isEpicParCoolLoaded()) {
            LivingMotion livingMotion = LivingMotions.ENUM_MANAGER.get("fast_run");
            if(livingMotion != null && motion.isSame(livingMotion)) {
                cir.setReturnValue(this.livingAnimations.getOrDefault(LivingMotions.RUN, this.livingAnimations.get(LivingMotions.IDLE)));
            }
        }
    }

}

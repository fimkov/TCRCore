package com.p1nero.tcrcore.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reascer.wom.animation.attacks.UltimateAttackAnimation;
import reascer.wom.events.WOMLivingEntityEvents;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import static reascer.wom.events.WOMLivingEntityEvents.*;

@Mixin(WOMLivingEntityEvents.class)
public class WOMLivingEntityEventsMixin {

    @Inject(method = "onSpawnEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingSpawn(MobSpawnEvent event, CallbackInfo ci) {
        ci.cancel();
    }

    /**
     * 加非玩家的判断，防止bug
     */
    @Inject(method = "onUpdateEvent", at = @At("HEAD"), cancellable = true, remap = false)
    private static void tcr$onLivingTick(LivingEvent.LivingTickEvent event, CallbackInfo ci) {
        ci.cancel();
        Entity e = event.getEntity();
        if(e.isInvulnerable() && !e.level().isClientSide) {
            LivingEntityPatch<?> entityPatch = EpicFightCapabilities.getEntityPatch(e, LivingEntityPatch.class);
            if (entityPatch != null && !(entityPatch.getServerAnimator().animationPlayer.getAnimation() instanceof UltimateAttackAnimation)) {
                for(String tag : event.getEntity().getTags()) {
                    if (tag.contains("wom_ultimate_Invulnerable")) {
                        e.setInvulnerable(false);
                        event.getEntity().getTags().remove(tag);
                        break;
                    }
                }
            }
        }
        AntiStunlock(event, e);
        TimedSakuraSlashes(event, e);
        LunarEclipse(event, e);
        SolarIgnited(event);
        Blackout(event);
    }
}

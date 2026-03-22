package com.p1nero.tcrcore.mixin.epicfight;

import com.p1nero.p1nero_ec.capability.PECPlayer;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.AbstractPlayerEvent;
import yesman.epicfight.world.entity.eventlistener.TakeDamageEvent;

/**
 * 招架计数
 */
@Mixin(value = TakeDamageEvent.Attack.class)
public abstract class TakeDamageEventAttackMixin extends AbstractPlayerEvent<ServerPlayerPatch> {

    @Shadow(remap = false)
    protected boolean parried;

    public TakeDamageEventAttackMixin(ServerPlayerPatch playerPatch, boolean cancelable) {
        super(playerPatch, cancelable);
    }

    @Inject(method = "setParried", at = @At("HEAD"), remap = false)
    private void pec$setParry(boolean parried, CallbackInfo ci){
        if(!this.parried && parried) {
            ServerPlayer serverPlayer = this.getPlayerPatch().getOriginal();
            if(!PlayerDataManager.parried.get(serverPlayer)) {
                PlayerDataManager.parried.put(serverPlayer, true);
            }
            if(!PECPlayer.isValidWeapon(this.getPlayerPatch().getOriginal().getMainHandItem())) {
                SkillContainer weaponInnate = getPlayerPatch().getSkill(SkillSlots.WEAPON_INNATE);
                if(weaponInnate.hasSkill()) {
                    weaponInnate.getSkill().setStackSynchronize(weaponInnate, weaponInnate.getStack() + 1);
                }
            }
            //反弹凋零伤害
            TakeDamageEvent.Attack attackEvent = ((TakeDamageEvent.Attack)(Object)this);
            if(attackEvent.getDamageSource().getEntity() instanceof WitherBoss witherBoss) {
                witherBoss.hurt(witherBoss.damageSources().playerAttack(serverPlayer), witherBoss.getMaxHealth() * 0.06F);
            }
        }
    }
}

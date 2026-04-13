package com.p1nero.tcrcore.mixin.epicfight;

import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.utils.ISSUtils;
import com.p1nero.tcrcore.utils.ItemUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.merlin204.efsiss.util.EFSISSUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mixin(SkillContainer.class)
public abstract class SkillContainerMixin {

    @Shadow(remap = false) public abstract Skill getSkill();

    @Inject(method = "requestCasting", at = @At("HEAD"), remap = false)
    private void tcr$executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf buf, CallbackInfoReturnable<Boolean> cir){
        ServerPlayer serverPlayer = executor.getOriginal();
        if(this.getSkill().getCategory() == SkillCategories.WEAPON_INNATE) {
            if(!PlayerDataManager.weapon_innate_used.get(serverPlayer)) {
                PlayerDataManager.weapon_innate_used.put(serverPlayer, true);
            }
        } else if(this.getSkill().getCategory() == SkillCategories.BASIC_ATTACK) {
            //触发魔纹技能
            if(ItemUtil.isBetterMagicWeaponItems(serverPlayer.getMainHandItem())) {
                boolean dashAttack = serverPlayer.isSprinting();
                boolean airAttack = !serverPlayer.onGround() && !serverPlayer.isInWater();
                //仅限冲刺和跳跃攻击可以
                if(!dashAttack && !airAttack) {
                    return;
                }
                SpellSelectionManager ssm = new SpellSelectionManager(serverPlayer);
                SpellSelectionManager.SelectionOption spellItem = ssm.getSelection();
                if (spellItem != null) {
                    SpellData spellData = ssm.getSelectedSpellData();
                    if (spellData != SpellData.EMPTY) {
                        MagicData playerMagicData = MagicData.getPlayerMagicData(serverPlayer);
                        if (playerMagicData.isCasting()) {
                            return;
                        }
                        int level = spellData.getSpell().getLevelFor(spellData.getLevel(), serverPlayer);
                        int newLevel = level / 2;
                        if (newLevel > 0) {
                            ISSUtils.attemptInitiateCast(spellData.getSpell(), ItemStack.EMPTY, newLevel, serverPlayer.level(), serverPlayer, spellItem.getCastSource(), spellItem.slot, false, 1);
                        }
                    }
                }
            }
        }
    }
}

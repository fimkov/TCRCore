package com.p1nero.tcrcore.mixin.epicfight;

import com.hm.efn.gameasset.EFNSKillDataKeys;
import com.hm.efn.skill.guard.EFNParryingSkill;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;

/**
 * 添加抖刀警告
 */
@Mixin(EFNParryingSkill.class)
public abstract class EFNParryingSkillMixin {

    @Shadow(remap = false)
    private int SHAKEDETECTIONCOUNT;

    @Inject(method = "startHolding", at = @At("TAIL"), remap = false)
    private void tcr$startHolding(SkillContainer container, CallbackInfo ci) {
        if (container.getExecutor().getOriginal() instanceof ServerPlayer serverPlayer) {
            SkillDataManager dataManager = container.getDataManager();
            int shakePenaltyCount = dataManager.getDataValue(EFNSKillDataKeys.EFN_SHAKE_PENALTY_COUNT.get());
            if (shakePenaltyCount > this.SHAKEDETECTIONCOUNT) {
                int effectivePenalty = shakePenaltyCount - this.SHAKEDETECTIONCOUNT;
                serverPlayer.displayClientMessage(TCRCoreMod.getInfo("shake_penalty_warning", effectivePenalty).withStyle(ChatFormatting.RED), true);
            }
        }
    }

}

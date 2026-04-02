package com.p1nero.tcrcore.mixin.iss;

import com.p1nero.tcrcore.utils.WorldUtil;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 仅主城才能回蓝
 * OUT
 */
@Mixin(MagicManager.class)
public class MagicManagerMixin {

//    @Inject(method = "regenPlayerMana", at = @At("HEAD"), cancellable = true, remap = false)
//    private void tcr$regenPlayerMana(ServerPlayer serverPlayer, MagicData playerMagicData, CallbackInfoReturnable<Boolean> cir) {
//        if(!WorldUtil.inMainLand(serverPlayer)) {
//            cir.setReturnValue(false);
//        }
//    }

}

package com.p1nero.tcrcore.utils;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastResult;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastResult.Type;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.network.casting.OnCastStartedPacket;
import io.redspace.ironsspellbooks.network.casting.UpdateCastingStatePacket;
import io.redspace.ironsspellbooks.setup.PacketDistributor;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ISSUtils {

    public static void attemptInitiateCast(AbstractSpell spell, ItemStack stack, int spellLevel, Level level, Player player, CastSource castSource, String castingEquipmentSlot, boolean warning) {
        attemptInitiateCast(spell, stack, spellLevel, level, player, castSource, castingEquipmentSlot, warning, 0);
    }

    public static void attemptInitiateCast(AbstractSpell spell, ItemStack stack, int spellLevel, Level level, Player player, CastSource castSource, String castingEquipmentSlot, boolean warning, int modifiedCastTime) {
        if (player instanceof ServerPlayer serverPlayer) {
            MagicData playerMagicData = MagicData.getPlayerMagicData(serverPlayer);
            if (!playerMagicData.isCasting()) {
                CastResult castResult = spell.canBeCastedBy(spellLevel, castSource, playerMagicData, serverPlayer);

                if (warning) {
                    if (castResult.message != null) {
                        serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(castResult.message));
                    }
                } else {
                    castResult = new CastResult(Type.SUCCESS);
                }

                if (castResult.isSuccess() && spell.checkPreCastConditions(level, spellLevel, serverPlayer, playerMagicData) && !MinecraftForge.EVENT_BUS.post(new SpellPreCastEvent(player, spell.getSpellId(), spellLevel, spell.getSchoolType(), castSource))) {
                    if (serverPlayer.isUsingItem()) {
                        serverPlayer.stopUsingItem();
                    }

                    int effectiveCastTime = spell.getEffectiveCastTime(spellLevel, player);
                    if(modifiedCastTime > 0) {
                        effectiveCastTime = modifiedCastTime;
                    }
                    playerMagicData.initiateCast(spell, spellLevel, effectiveCastTime, castSource, castingEquipmentSlot);
                    playerMagicData.setPlayerCastingItem(stack);
                    spell.onServerPreCast(player.level(), spellLevel, player, playerMagicData);
                    PacketDistributor.sendToPlayer(serverPlayer, new UpdateCastingStatePacket(spell.getSpellId(), spellLevel, effectiveCastTime, castSource, castingEquipmentSlot));
                    PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new OnCastStartedPacket(serverPlayer.getUUID(), spell.getSpellId(), spellLevel));
                }
            } else {
                Utils.serverSideCancelCast(serverPlayer);
            }
        }

    }
}

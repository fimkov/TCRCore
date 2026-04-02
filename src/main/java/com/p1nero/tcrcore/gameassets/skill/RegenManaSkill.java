package com.p1nero.tcrcore.gameassets.skill;

import com.p1nero.dpr.skill.ParryAndDodgeRewardSkill;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import io.redspace.ironsspellbooks.setup.PacketDistributor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class RegenManaSkill extends ParryAndDodgeRewardSkill {

    protected double regenRate;

    public RegenManaSkill(Builder builder) {
        super(builder);
        this.playerPatchConsumer = playerPatch -> {
            if(playerPatch.getOriginal() instanceof ServerPlayer serverPlayer) {
                MagicData playerMagicData = MagicData.getPlayerMagicData(serverPlayer);
                float maxMana = (float)serverPlayer.getAttributeValue( AttributeRegistry.MAX_MANA.get());
                playerMagicData.addMana((float) (regenRate * maxMana));
                PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(playerMagicData));
            }
        };
    }

    public void setParams(CompoundTag parameters) {
        super.setParams(parameters);
        if (parameters.contains("regen_rate")) {
            this.regenRate = parameters.getDouble("regen_rate");
        }
    }

    @Override
    public List<Object> getTooltipArgsOfScreen(List<Object> list) {
        list.add(regenRate * 100);
        return list;
    }
}

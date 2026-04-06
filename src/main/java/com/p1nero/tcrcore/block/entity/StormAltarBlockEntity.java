package com.p1nero.tcrcore.block.entity;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class StormAltarBlockEntity extends AbstractAltarBlockEntity {
    public StormAltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(TCRBlockEntities.STORM_ALTAR_BLOCK_ENTITY.get(), pos, blockState, ModItems.STORM_EYE.get());
    }

    @Override
    public void onPlayerInteract(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        super.onPlayerInteract(pState, pLevel, pPos, pPlayer, pHand, pHit);
        if(pPlayer instanceof ServerPlayer serverPlayer && TCRQuestManager.hasQuest(pPlayer, TCRQuests.PUT_STORM_EYE_ON_ALTAR)) {
            TCRQuests.PUT_STORM_EYE_ON_ALTAR.finish(serverPlayer, true);
            TCRQuests.KILL_STORM_EYE.start(serverPlayer);
        }
    }

    @Override
    public void setActivated(Player player, boolean activated) {
        PlayerDataManager.stormEyeActivated.put(player, activated);
    }

    @Override
    public boolean isActivated(Player player) {
        return PlayerDataManager.stormEyeActivated.get(player);
    }

    @Override
    protected ParticleOptions getSpawnerParticle() {
        return ModParticle.SPARK.get();
    }

    @Override
    public boolean checkBossKilled(Player player) {
        return PlayerDataManager.stormEyeKilled.get(player);
    }

    @Override
    public boolean checkEyeFound(Player player) {
        return PlayerDataManager.stormEyeGotten.get(player);
    }

    @Override
    public int getColor() {
        return ChatFormatting.AQUA.getColor();
    }

}

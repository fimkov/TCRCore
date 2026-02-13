package com.p1nero.tcrcore.item.custom;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.capability.TCRPlayer;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.utils.WaypointUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.yesman.epicskills.registry.entry.EpicSkillsSounds;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import xaero.hud.minimap.waypoint.WaypointColor;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class LandResonanceStoneItem extends ResonanceStoneItem{

    public LandResonanceStoneItem(Properties properties, ResourceLocation targetStructure, int y, ResourceLocation dimension, Predicate<ServerPlayer> predicate, BiConsumer<BlockPos, ServerPlayer> callback) {
        super(properties, targetStructure, y, dimension, predicate, callback);
    }

    /**
     * 额外搜索奇美拉地牢，用super怕丢了一个。。
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if(player instanceof ServerPlayer serverPlayer) {
            if(predicate.test(serverPlayer) && level.dimension().equals(Level.OVERWORLD)) {
                CompletableFuture.supplyAsync(() -> {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("resonance_stone_working", this.getDescription()), true);
                    serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));

                    BlockPos pos = null;
                    try {
                        pos = WorldUtil.getNearbyStructurePos(serverPlayer, targetStructure.toString(), y);
                    } catch (Exception e) {
                        TCRCoreMod.LOGGER.error("TCRCore : Error finding structure [{}]: {}", targetStructure, e.getMessage());
                    }

                    BlockPos pos1 = null;
                    try {
                        //以大地高塔为中心搜奇美拉的位置
                        pos1 = WorldUtil.getNearbyStructurePos(serverPlayer.serverLevel(), pos, WorldUtil.BONE_CHIMERA_STRUCTURE, 130);
                    } catch (Exception e) {
                        TCRCoreMod.LOGGER.error("TCRCore : Error finding structure [{}]: {}", WorldUtil.BONE_CHIMERA_STRUCTURE, e.getMessage());
                    }
                    return Pair.of(pos, pos1);
                })
                .thenAccept(posPair -> {
                    TCRPlayer tcrPlayer = TCRCapabilityProvider.getTCRPlayer(player);
                    BlockPos pos = posPair.first();
                    if(pos != null) {
                        tcrPlayer.playDirectionParticle(player.getEyePosition(), new Vec3(pos.getX(), player.getEyeY(), pos.getZ()));
                        serverPlayer.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(EpicSkillsSounds.GAIN_ABILITY_POINTS.get()), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                        callback.accept(pos, serverPlayer);
                    }
                    BlockPos pos1 = posPair.second();
                    if(pos1 != null) {
                        tcrPlayer.playDirectionParticle(player.getEyePosition(), new Vec3(pos1.getX(), player.getEyeY(), pos1.getZ()));
                        WaypointUtil.sendWaypoint(serverPlayer, "bone_chimera_mark", Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtil.BONE_CHIMERA_STRUCTURE))), pos1, WaypointColor.YELLOW);
                        TCRQuests.BONE_CHIMERA_QUEST.start(serverPlayer, false);
                    }
                    //保险，俩都找到再消耗
                    if(pos != null && pos1 != null) {
                        itemStack.shrink(1);
                    }
                });
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}

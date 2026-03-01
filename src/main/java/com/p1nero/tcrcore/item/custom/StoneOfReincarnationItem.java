package com.p1nero.tcrcore.item.custom;

import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.OpenCustomDialogPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class StoneOfReincarnationItem extends SimpleDescriptionItem{
    public StoneOfReincarnationItem(Properties properties) {
        super(properties, true);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(player instanceof ServerPlayer serverPlayer && hand == InteractionHand.MAIN_HAND) {
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new OpenCustomDialogPacket(OpenCustomDialogPacket.RESET_GAME), serverPlayer);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}

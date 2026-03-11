package com.p1nero.tcrcore.network.packet.clientbound;

import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.compat.JourneyMapCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public record AddJourneyMapWaypointPacket(String id, Component name, BlockPos pos, int color) implements BasePacket {
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(id);
        buf.writeComponent(name);
        buf.writeBlockPos(pos);
        buf.writeInt(color);
    }

    public static AddJourneyMapWaypointPacket decode(FriendlyByteBuf buf) {
        String id = buf.readUtf();
        Component name = buf.readComponent();
        BlockPos blockPos = buf.readBlockPos();
        int color = buf.readInt();
        return new AddJourneyMapWaypointPacket(id, name, blockPos, color);
    }

    @Override
    public void execute(@Nullable Player playerEntity) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            JourneyMapCompat.createNewWaypoint(id, name, color, pos, Minecraft.getInstance().level.dimension());
        }
    }
}

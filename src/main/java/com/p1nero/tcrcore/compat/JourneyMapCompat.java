package com.p1nero.tcrcore.compat;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class JourneyMapCompat {

    public static void runInJourneyMapLoaded(Supplier<Runnable> handler) {
        if (TCRCoreMod.isJourneyMapLoaded()) {
            handler.get().run();
        }
    }

    public static void sendWaypoint(ServerPlayer player, String id, Component display, BlockPos pos, ChatFormatting color) {
        runInJourneyMapLoaded(() -> () -> JourneyMapWaypointHelper.sendWaypoint(player, id, display, pos, color));
    }

    public static void createNewWaypoint(String id, Component name, int color, BlockPos pos, ResourceKey<Level> dimension) {
        runInJourneyMapLoaded(() -> () -> JourneyMapWaypointHelper.createNewWaypoint(id, name, color, pos, dimension));
    }
}
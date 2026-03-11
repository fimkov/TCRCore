package com.p1nero.tcrcore.compat;

import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.AddJourneyMapWaypointPacket;
import com.p1nero.tudigong.TuDiGongMod;
import journeymap.client.JourneymapClient;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.event.ClientEvent;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.Locale;

@ParametersAreNonnullByDefault
@journeymap.client.api.ClientPlugin
public class JourneyMapWaypointHelper implements IClientPlugin {

    private static IClientAPI jmAPI;

    @Override
    public void initialize(IClientAPI iClientAPI) {
        jmAPI = iClientAPI;
    }

    @Override
    public String getModId() {
        return TCRCoreMod.MOD_ID;
    }

    @Override
    public void onEvent(ClientEvent clientEvent) {

    }

    public static void sendWaypoint(ServerPlayer player, String id, Component name, BlockPos pos, ChatFormatting formatting) {
        int color;
        if (formatting.getColor() == null) {
            color = ChatFormatting.WHITE.getColor();
        } else {
            color = formatting.getColor();
        }
        PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new AddJourneyMapWaypointPacket(id, name, pos, color), player);
    }

    public static void createNewWaypoint(String id, Component name, int color, BlockPos pos, ResourceKey<Level> dimension) {
        Waypoint waypoint;
        try {
            waypoint = new Waypoint(TCRCoreMod.MOD_ID, id.toLowerCase(Locale.ROOT), name.getString(), dimension, pos)
                    .setColor(color);

            jmAPI.show(waypoint);
            if(Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.displayClientMessage(TCRCoreMod.getInfo("map_pos_marked_press_to_open", JourneymapClient.getInstance().getKeyEvents().getHandler().kbFullscreenToggle.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)), false);
            }
        } catch (Throwable t) {
            TCRCoreMod.LOGGER.error(t.getMessage(), t);
        }
    }

    public static void removeWaypoint(String id, Component name, int color, BlockPos pos, ResourceKey<Level> dimension) {
        Waypoint waypoint;
        try {
            waypoint = new Waypoint(TCRCoreMod.MOD_ID, id.toLowerCase(Locale.ROOT), name.getString(), dimension, pos)
                    .setColor(color);
            jmAPI.remove(waypoint);

        } catch (Throwable t) {
            TCRCoreMod.LOGGER.error(t.getMessage(), t);
        }
    }
}
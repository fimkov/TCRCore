package com.p1nero.tcrcore.network;

import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.network.packet.clientbound.*;
import com.p1nero.tcrcore.network.packet.serverbound.EndScreenCallbackPacket;
import com.p1nero.tcrcore.network.packet.serverbound.ExecuteRiptidePacket;
import com.p1nero.tcrcore.network.packet.serverbound.ExitSpectatorPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public class TCRPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int index;

    public static synchronized void register() {
        if(TCRCoreMod.isXaeroMapLoaded()) {
            register(AddXaeroWaypointPacket.class, AddXaeroWaypointPacket::decode);
            register(RemoveXaeroWaypointPacket.class, RemoveXaeroWaypointPacket::decode);
        }

        if(TCRCoreMod.isJourneyMapLoaded()) {
            register(AddJourneyMapWaypointPacket.class, AddJourneyMapWaypointPacket::decode);
            register(RemoveJourneyWaypointPacket.class, RemoveJourneyWaypointPacket::decode);
        }

        register(PlayTitlePacket.class, PlayTitlePacket::decode);
        register(PersistentBoolDataSyncPacket.class, PersistentBoolDataSyncPacket::decode);
        register(PersistentDoubleDataSyncPacket.class, PersistentDoubleDataSyncPacket::decode);
        register(PersistentStringDataSyncPacket.class, PersistentStringDataSyncPacket::decode);

        register(CSTipPacket.class, CSTipPacket::decode);

        register(SyncTCRPlayerPacket.class, SyncTCRPlayerPacket::decode);
        register(AddAvlEntityAfterImageParticle.class, AddAvlEntityAfterImageParticle::decode);

        register(EndScreenCallbackPacket.class, EndScreenCallbackPacket::decode);
        register(ExecuteRiptidePacket.class, ExecuteRiptidePacket::decode);
        register(ExitSpectatorPacket.class, ExitSpectatorPacket::decode);

        register(RefreshClientQuestsPacket.class, RefreshClientQuestsPacket::decode);

        register(SetThirdPersonPacket.class, SetThirdPersonPacket::decode);
        register(OpenEndScreenPacket.class, OpenEndScreenPacket::decode);
        register(OpenCustomDialogPacket.class, OpenCustomDialogPacket::decode);
        register(PlayItemPickupParticlePacket.class, PlayItemPickupParticlePacket::decode);
    }

    private static <MSG extends BasePacket> void register(final Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
        INSTANCE.messageBuilder(packet, index++).encoder(BasePacket::encode).decoder(decoder).consumerMainThread(BasePacket::handle).add();
    }
}

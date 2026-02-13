package com.p1nero.tcrcore.network.packet.clientbound.helper;

import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.FirstEnterCloudlandScreenHandler;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.StartScreenHandler;
import com.p1nero.tcrcore.client.gui.screen.TCREndScreen;
import com.p1nero.tcrcore.client.gui.screen.TCRQuestScreen;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.serverbound.EndScreenCallbackPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TCRClientHandler {

    public static void syncBoolData(String key, boolean isLocked, boolean value) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                PlayerDataManager.putData(player, key + "isLocked", true);
                return;
            }
            PlayerDataManager.putData(player, key, value);
            PlayerDataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncDoubleData(String key, boolean isLocked, double value) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                PlayerDataManager.putData(player, key + "isLocked", true);
                return;
            }
            PlayerDataManager.putData(player, key, value);
            PlayerDataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncStringData(String key, boolean isLocked, String value){
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (isLocked) {
                PlayerDataManager.putData(player, key + "isLocked", true);
                return;
            }
            PlayerDataManager.putData(player, key, value);
            PlayerDataManager.putData(player, key + "isLocked", false);
        }
    }

    public static void syncTCRPlayer(CompoundTag compoundTag) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            TCRCapabilityProvider.getTCRPlayer(Minecraft.getInstance().player).loadNBTData(compoundTag);
        }
    }

    public static void openEndScreen(){
        if(Minecraft.getInstance().player != null) {
            Minecraft.getInstance().setScreen(new TCREndScreen(true, ()->{
                PacketRelay.sendToServer(TCRPacketHandler.INSTANCE, new EndScreenCallbackPacket());
                Minecraft.getInstance().player.connection.send(new ServerboundClientCommandPacket(ServerboundClientCommandPacket.Action.PERFORM_RESPAWN));
                Minecraft.getInstance().setScreen(null);
            }));
        }
    }

    public static void openStartScreen(){
        if(Minecraft.getInstance().player != null) {
            StartScreenHandler.addScreen();
        }
    }

    public static void openFirstEnterCloudlandScreen(){
        if(Minecraft.getInstance().player != null) {
            FirstEnterCloudlandScreenHandler.addScreen();
        }
    }

    public static void refreshQuestScreen() {
        TCRQuestScreen.refreshSelectedQuest();
    }
}

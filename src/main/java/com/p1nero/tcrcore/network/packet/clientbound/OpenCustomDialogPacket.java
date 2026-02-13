package com.p1nero.tcrcore.network.packet.clientbound;
import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.network.packet.clientbound.helper.DistHelper;
import com.p1nero.tcrcore.network.packet.clientbound.helper.TCRClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public record OpenCustomDialogPacket(int id) implements BasePacket {
    public static final int GAME_START = 0;
    public static final int FIRST_ENTER_CLOUDLAND = 1;
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }

    public static OpenCustomDialogPacket decode(FriendlyByteBuf buf) {
        return new OpenCustomDialogPacket(buf.readInt());
    }

    @Override
    public void execute(Player playerEntity) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            switch (id) {
                case GAME_START -> DistHelper.runClient(() -> TCRClientHandler::openStartScreen);
                case FIRST_ENTER_CLOUDLAND -> DistHelper.runClient(() -> TCRClientHandler::openFirstEnterCloudlandScreen);
            }

        }
    }
}
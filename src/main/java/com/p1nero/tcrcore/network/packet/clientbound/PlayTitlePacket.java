package com.p1nero.tcrcore.network.packet.clientbound;

import com.p1nero.dialog_lib.network.packet.BasePacket;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.wintercogs.beyonddimensions.client.init.BDShortKeys;
import com.yesman.epicskills.client.input.EpicSkillsKeyMappings;
import dev.ftb.mods.ftbquests.client.FTBQuestsClient;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import yesman.epicfight.client.input.EpicFightKeyMappings;

public record PlayTitlePacket(int id) implements BasePacket {

    public static final int DODGE_TUTORIAL = 1;
    public static final int PARRY_TUTORIAL = 2;
    public static final int LOCK_TUTORIAL = 3;
    public static final int RIPTIDE_TUTORIAL = 4;
    public static final int UNLOCK_NEW_CHAPTER = 5;
    public static final int OPEN_BACKPACK_TUTORIAL = 6;
    public static final int UNLOCK_NEW_SKILL = 7;
    public static final int TO_BE_CONTINUE = 8;

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }

    public static PlayTitlePacket decode(FriendlyByteBuf buf){
        return new PlayTitlePacket(buf.readInt());
    }

    @Override
    public void execute(Player player) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().level != null){
            switch (id) {
                case DODGE_TUTORIAL -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("dodge_tutorial", EpicFightKeyMappings.DODGE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("perfect_dodge_tutorial"));
                }
                case PARRY_TUTORIAL -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("parry_tutorial", EpicFightKeyMappings.GUARD.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("perfect_parry_tutorial"));
                }
                case LOCK_TUTORIAL -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("lock_tutorial", EpicFightKeyMappings.LOCK_ON.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("lock_tutorial_sub"));
                }
                case RIPTIDE_TUTORIAL -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("riptide_tutorial", TCRKeyMappings.RIPTIDE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                }
                case UNLOCK_NEW_CHAPTER -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("unlock_new_ftb_page_title"));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("unlock_new_ftb_page_subtitle", FTBQuestsClient.KEY_QUESTS.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                }
                case OPEN_BACKPACK_TUTORIAL -> {
                    Minecraft.getInstance().gui.setTitle(Component.literal(""));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("open_backpack_tutorial", BDShortKeys.OPEN_GUI_KEY.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                }
                case UNLOCK_NEW_SKILL -> {
                    Minecraft.getInstance().gui.setTitle(Component.literal(""));
                    Minecraft.getInstance().gui.setSubtitle(TCRCoreMod.getInfo("unlock_new_skill_sub", EpicSkillsKeyMappings.OPEN_SKILL_TREE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)));
                }
                case TO_BE_CONTINUE -> {
                    Minecraft.getInstance().gui.setTitle(TCRCoreMod.getInfo("to_be_continue"));
                }
            }
        }
    }
}

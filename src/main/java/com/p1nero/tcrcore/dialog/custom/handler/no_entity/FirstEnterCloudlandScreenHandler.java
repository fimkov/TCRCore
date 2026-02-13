package com.p1nero.tcrcore.dialog.custom.handler.no_entity;

import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.datagen.lang.TCRLangProvider;
import com.p1nero.tcrcore.entity.TCREntities;
import net.blay09.mods.waystones.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FirstEnterCloudlandScreenHandler {
    public static final String name = "first_enter_cloudland";

    public static void addScreen() {
        ScreenDialogueBuilder builder = new ScreenDialogueBuilder(TCRCoreMod.MOD_ID, name);
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if(localPlayer == null) {
            return;
        }
        StreamDialogueScreenBuilder screenBuilder = new StreamDialogueScreenBuilder(Component.empty(), "", TCRCoreMod.MOD_ID);

        screenBuilder.setCustomTitle(Component.literal("").append(localPlayer.getDisplayName().copy().withStyle(ChatFormatting.AQUA)).append(": \n"));

        screenBuilder.start(builder.ans(0))
                        .addOption(builder.opt(0), builder.ans(1, ModItems.warpStone.getDescription(), TCREntities.AINE_IRIS.get().getDescription()))
                                .addFinalOption(builder.opt(1));

        Minecraft.getInstance().setScreen(screenBuilder.build());
    }

    public static void onGenerateZH(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "这是什么地方？看起来像另一个世界。");
        generator.addScreenOpt(name, 0, "继续");
        generator.addScreenAns(name, 1, "我应该可以用[%s]或背包里的[传送卷轴]离开这里，等出去了再找%s问问这是怎么回事吧。");
        generator.addScreenOpt(name, 1, "结束对话");
    }

    public static void onGenerateEN(TCRLangProvider generator) {
        generator.addScreenAns(name, 0, "Where is this? It looks like another world.");
        generator.addScreenOpt(name, 0, "Continue");
        generator.addScreenAns(name, 1, "I should be able to leave here using a [%s] or the [teleport scroll] in my inventory. Once I'm out, I'll ask %s what's going on.");
        generator.addScreenOpt(name, 1, "End conversation");
    }

}

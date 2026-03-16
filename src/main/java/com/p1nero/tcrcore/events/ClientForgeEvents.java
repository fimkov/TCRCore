package com.p1nero.tcrcore.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.obscuria.obscureapi.api.BossBarsRenderManager;
import com.p1nero.battle_field1.worldgen.PBF1Dimensions;
import com.p1nero.dialog_lib.events.ClientNpcEntityDialogueEvent;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.client.gui.*;
import com.p1nero.tcrcore.dialog.custom.handler.HandleIronGolemDialog;
import com.p1nero.tcrcore.dialog.custom.handler.HandleVillagerDialog;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

import static net.minecraft.client.gui.components.BossHealthOverlay.GUI_BARS_LOCATION;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, value = Dist.CLIENT)
public class ClientForgeEvents {

    public static Set<Button> buttonsInCreateWorldScreen = new HashSet<>();

    public static final ResourceLocation BACKGROUND_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/light_dirt_background.png");

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event){
        if(event.phase == TickEvent.Phase.END) {
            CustomQuestOverlayRenderer.tick();
            BTSpawnerBlockIndicatorRenderer.tick();
            if(!buttonsInCreateWorldScreen.isEmpty()) {
                buttonsInCreateWorldScreen.forEach(button -> {
                    if(button.isHovered()) {
                        int dx = (int)(Math.random() * 100) - 50;
                        int dy = (int)(Math.random() * 100) - 50;
                        button.setPosition(button.getX() + dx, button.getY() + dy);
                    }
                });
            }
        }

    }

    /**
     * FIXME 官方要改的话记得删
     */
    @SubscribeEvent
    public static void onRenderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event){
        Optional<BossBarsRenderManager.Style> style = BossBarsRenderManager.getStyle(event.getBossEvent().getName());
        if (style.isPresent()) {
            Component component = event.getBossEvent().getName();
            if (style.get().shouldRenderBar()) {
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, GUI_BARS_LOCATION);
            }
            Minecraft minecraft = Minecraft.getInstance();
            style.get().getFunction().render(minecraft, event.getGuiGraphics(), event.getX(), event.getY(), event.getBossEvent(), component);
            if (style.get().shouldRenderName()) {
                int x = event.getWindow().getGuiScaledWidth() / 2 - minecraft.font.width(component) / 2;
                int y = event.getY() - 9;
                event.getGuiGraphics().drawString(minecraft.font, component, x, y, 16777215);
            }

            event.setIncrement(style.get().getIncrement(minecraft));
        }
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Pre event) {
        if(!Minecraft.getInstance().isPaused() && Minecraft.getInstance().screen == null && Minecraft.getInstance().player != null) {
            CustomQuestOverlayRenderer.render(Minecraft.getInstance().player, event.getGuiGraphics(), event.getPartialTick());
            BTSpawnerBlockIndicatorRenderer.render(Minecraft.getInstance().player, event.getGuiGraphics(), event.getPartialTick());
            if(Minecraft.getInstance().player.level().dimension() == PBF1Dimensions.SANCTUM_OF_THE_BATTLE_LEVEL_KEY && Minecraft.getInstance().player.isSpectator()) {
                MutableComponent component = TCRCoreMod.getInfo("exit_spectator_in_pbf1", TCRKeyMappings.EXIT_SPECTATOR.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD));
                GuiGraphics guiGraphics = event.getGuiGraphics();
                Font font = Minecraft.getInstance().font;
                PoseStack poseStack = guiGraphics.pose();
                poseStack.pushPose();
                guiGraphics.drawString(font, component, 10, 10, 0xFFFFFF, true);
                poseStack.popPose();
            }
        }
    }

    /**
     * 过时了，应该用dialog extension
     */
    @SubscribeEvent
    public static void onDialogSend(ClientNpcEntityDialogueEvent event) {
        if(event.getSelf() instanceof Villager villager) {
            HandleVillagerDialog.openDialogScreen(villager, event.getLocalPlayer(), event.getServerData());
        }
        if(event.getSelf() instanceof IronGolem ironGolem) {
            HandleIronGolemDialog.openDialogScreen(ironGolem, event.getLocalPlayer(), event.getServerData());
        }
    }

    @SubscribeEvent
    public static void onRenderBackground(ScreenEvent.BackgroundRendered event) {
        if(Minecraft.getInstance().level == null && !(event.getScreen() instanceof LevelLoadingScreen)) {
            event.getGuiGraphics().blit(BACKGROUND_LOCATION, 0, 0, 0, 0.0F, 0.0F, event.getScreen().width, event.getScreen().height, 32, 32);
//            event.getGuiGraphics().fill(0, 0, event.getScreen().width, event.getScreen().height, FastColor.ABGR32.color(255, 255, 255, 255));
        }

    }

    @SubscribeEvent
    public static void onFovChange(ComputeFovModifierEvent event) {
        if(event.getPlayer().level().dimension() == TCRDimensions.SANCTUM_LEVEL_KEY && event.getPlayer().hasEffect(MobEffects.MOVEMENT_SPEED)) {
            event.setNewFovModifier(1.0F);
        }
    }

}

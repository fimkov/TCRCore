package com.p1nero.tcrcore.client.gui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import com.p1nero.tcrcore.TCRClientConfig;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.client.gui.screen.TCRQuestScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class CustomQuestOverlayRenderer {
    private static long fadeStartTime = 0;
    private static boolean hasTask = false;
    private static boolean lastHasTask = false;
    private static float alpha = 0.0f;
    private static final int FADE_DURATION = 30; // 30 ticks = 1.5 seconds
    private static Component lastQuestShortDesc = Component.empty();
    private static Component hintText = Component.literal("按 %s 键查看任务列表。");
    private static int x;
    private static int y;
    private static int textX;
    private static int textY;
    private static long timeSinceStateChange;
    public static final ResourceLocation TASK_ICON = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/task_icon.png");
    public static final ResourceLocation QUEST_ARROW_ICON = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/quest_arrow.png");
    public static double QUEST_INDICATOR_MIN_DISTANCE = 5.0D;
    private static final int QUEST_ICON_SIZE = 12;
    private static final int QUEST_EDGE_MARGIN = 60;
    private static TCRQuestManager.Quest currentQuest;
    private static ResourceLocation currentQuestIcon;
    private static final List<QuestIndicatorInfo> QUEST_INDICATOR_INFOS = new ArrayList<>();

    private static class QuestIndicatorInfo {
        TCRQuestManager.Quest quest;
        ResourceLocation icon;
        float iconX;
        float iconY;
        float lastIconX;
        float lastIconY;
        boolean drawArrow;
        float arrowCenterX;
        float arrowCenterY;
        float lastArrowCenterX;
        float lastArrowCenterY;
        float arrowAngle;
        String distanceText;
    }

    public static void tick(LocalPlayer localPlayer) {
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();
        long currentTime = localPlayer.level().getGameTime();
        // Calculate alpha based on game time with partialTick interpolation
        timeSinceStateChange = currentTime - fadeStartTime;
        hasTask = TCRQuestManager.hasQuest(localPlayer);
        hintText = TCRCoreMod.getInfo("press_to_show_quest_ui", TCRKeyMappings.SHOW_QUESTS.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD));
        // Handle state changes
        if (hasTask != lastHasTask) {
            fadeStartTime = currentTime;
        }
        if (hasTask) {
            // Update task description when task appears

            int id = PlayerDataManager.currentQuestId.getInt(localPlayer);
            currentQuest = TCRQuestManager.getQuestById(id);
            lastQuestShortDesc = currentQuest.getShortDesc();
            currentQuestIcon = currentQuest.getIcon();
        }

        lastHasTask = hasTask;

        // Calculate position (golden ratio - left side, about 38.2% from top)
        int screenHeight = window.getGuiScaledHeight();
        int goldenRatioY = (int) (screenHeight * 0.382f);
        x = 10 + TCRClientConfig.TASK_UI_X.get(); // 10 pixels from left edge
        y = goldenRatioY + TCRClientConfig.TASK_UI_Y.get();
        // Draw text with shadow
        textX = x + 20; // 4 pixels spacing after icon
        textY = y + 4; // Vertically center with 16px icon

        updateQuestIndicators(localPlayer, window);
    }

    public static void render(LocalPlayer localPlayer, GuiGraphics guiGraphics, Window window, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        float interpolatedTime = timeSinceStateChange + partialTick;

        if (hasTask) {
            // Fade in
            if (interpolatedTime < FADE_DURATION) {
                alpha = interpolatedTime / FADE_DURATION;
            } else {
                alpha = 1.0f;
            }
        } else {
            // Fade out
            if (interpolatedTime < FADE_DURATION) {
                alpha = 1.0f - (interpolatedTime / FADE_DURATION);
            } else {
                alpha = 0.0f;
            }
        }

        // Only render if there's something to show and alpha > 0
        if (alpha <= 0.0f || lastQuestShortDesc == null || lastQuestShortDesc.getString().isEmpty()) {
            return;
        }
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.93F, 0.93F, 0.93F);
        // Set up alpha for rendering
        RenderSystem.enableBlend();

        // Draw icon (16x16)
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, alpha);

        ResourceLocation icon = currentQuestIcon == null ? TASK_ICON : currentQuestIcon;
        guiGraphics.blit(icon, x, y, 0, 0, 16, 16, 16, 16);

        // Draw text with shadow and alpha
        int textColor = (int) (alpha * 255) << 24 | 0xFFFFFF; // White text with alpha

        // Draw main text
        guiGraphics.drawString(minecraft.font, lastQuestShortDesc, textX, textY, textColor, true);

        // Draw hint text below in smaller size
        int hintTextColor = (int) (alpha * 255) << 24 | 0xAAAAAA;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(textX, textY + 12, 0);
        guiGraphics.pose().scale(0.7F, 0.7F, 0.7F);
        guiGraphics.drawString(minecraft.font, hintText, 0, 0, hintTextColor, true);
        guiGraphics.pose().popPose();

        // Reset color
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();
        guiGraphics.pose().popPose();

        renderQuestTargetIndicator(localPlayer, guiGraphics, window, partialTick);

    }

    /**
     * 画任务的目标指示器
     */
    public static void renderQuestTargetIndicator(LocalPlayer localPlayer, GuiGraphics guiGraphics, Window window, float partialTick) {
        if (localPlayer == null || QUEST_INDICATOR_INFOS.isEmpty()) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();

        RenderSystem.enableBlend();
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        for (QuestIndicatorInfo info : QUEST_INDICATOR_INFOS) {
            float iconX = Mth.lerp(partialTick, info.lastIconX, info.iconX);
            float iconY = Mth.lerp(partialTick, info.lastIconY, info.iconY);

            guiGraphics.blit(info.icon, (int) iconX, (int) iconY, 0, 0, QUEST_ICON_SIZE, QUEST_ICON_SIZE, QUEST_ICON_SIZE, QUEST_ICON_SIZE);

            int textWidth = minecraft.font.width(info.distanceText);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(iconX + QUEST_ICON_SIZE / 2.0F, iconY + QUEST_ICON_SIZE + 4.0F, 0.0F);
            guiGraphics.pose().scale(0.7F, 0.7F, 0.7F);
            guiGraphics.drawString(minecraft.font, info.distanceText, -textWidth / 2, 0, 0xFFFFFFFF, true);
            guiGraphics.pose().popPose();

            if (info.drawArrow) {
                float arrowCenterX = Mth.lerp(partialTick, info.lastArrowCenterX, info.arrowCenterX);
                float arrowCenterY = Mth.lerp(partialTick, info.lastArrowCenterY, info.arrowCenterY);

                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(arrowCenterX, arrowCenterY, 0.0F);
                guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(info.arrowAngle));
                guiGraphics.blit(QUEST_ARROW_ICON, -QUEST_ICON_SIZE / 2, -QUEST_ICON_SIZE / 2 - QUEST_ICON_SIZE, 0, 0, QUEST_ICON_SIZE, QUEST_ICON_SIZE, QUEST_ICON_SIZE, QUEST_ICON_SIZE);
                guiGraphics.pose().popPose();
            }
        }

        RenderSystem.disableBlend();
    }

    private static void updateQuestIndicators(LocalPlayer localPlayer, Window window) {
        if (localPlayer == null) {
            QUEST_INDICATOR_INFOS.clear();
            return;
        }

        List<QuestIndicatorInfo> previous = new ArrayList<>(QUEST_INDICATOR_INFOS);
        QUEST_INDICATOR_INFOS.clear();

        List<TCRQuestManager.Quest> quests = TCRQuestScreen.getQuests();
        TCRQuestManager.Quest selectedQuest = TCRQuestScreen.getSelectedQuest();
        if (quests == null || selectedQuest == null) {
            return;
        }

        BlockPos selectedQuestPos = selectedQuest.getTrackingPos();
        if (selectedQuestPos != null && localPlayer.level().dimension() == selectedQuest.getDimension()) {
            ResourceLocation icon = selectedQuest.getIcon();
            if (icon == null) {
                icon = TASK_ICON;
            }
            QuestIndicatorInfo info = computeQuestIndicator(localPlayer, selectedQuestPos, icon, window, true);
            if (info != null) {
                applyPreviousIndicatorInfo(info, selectedQuest, previous);
                QUEST_INDICATOR_INFOS.add(info);
            }
        }

        quests.forEach(quest -> {
            if (quest.equals(selectedQuest)) {
                return;
            }
            BlockPos trackingPos = quest.getTrackingPos();
            if (trackingPos != null && localPlayer.level().dimension() == quest.getDimension()) {
                ResourceLocation icon = quest.getIcon();
                if (icon == null) {
                    icon = TASK_ICON;
                }
                QuestIndicatorInfo info = computeQuestIndicator(localPlayer, trackingPos, icon, window, false);
                if (info != null) {
                    applyPreviousIndicatorInfo(info, quest, previous);
                    QUEST_INDICATOR_INFOS.add(info);
                }
            }
        });
    }

    private static void applyPreviousIndicatorInfo(QuestIndicatorInfo info, TCRQuestManager.Quest quest, List<QuestIndicatorInfo> previous) {
        info.quest = quest;
        QuestIndicatorInfo old = null;
        for (QuestIndicatorInfo prev : previous) {
            if (prev.quest != null && prev.quest.equals(quest)) {
                old = prev;
                break;
            }
        }
        if (old != null) {
            info.lastIconX = old.iconX;
            info.lastIconY = old.iconY;
            info.lastArrowCenterX = old.arrowCenterX;
            info.lastArrowCenterY = old.arrowCenterY;
        } else {
            info.lastIconX = info.iconX;
            info.lastIconY = info.iconY;
            info.lastArrowCenterX = info.arrowCenterX;
            info.lastArrowCenterY = info.arrowCenterY;
        }
    }

    private static QuestIndicatorInfo computeQuestIndicator(LocalPlayer localPlayer, BlockPos pos, ResourceLocation icon, Window window, boolean drawArrowWhenOffscreen) {
        if (localPlayer == null) {
            return null;
        }
        Minecraft minecraft = Minecraft.getInstance();
        Camera camera = minecraft.gameRenderer.getMainCamera();

        Vec3 camPos = camera.getPosition();
        Vec3 targetPos = Vec3.atCenterOf(pos);

        double distance = localPlayer.position().distanceTo(targetPos);
        if (distance < QUEST_INDICATOR_MIN_DISTANCE) {
            return null;
        }

        Vec3 camToTarget = targetPos.subtract(camPos);
        if (camToTarget.lengthSqr() < 1.0e-4) {
            return null;
        }

        Vec3 forward = Vec3.directionFromRotation(camera.getXRot(), camera.getYRot());
        Vec3 upWorld = new Vec3(0.0, 1.0, 0.0);
        Vec3 right = forward.cross(upWorld).normalize();
        Vec3 up = right.cross(forward).normalize();

        double xCam = camToTarget.dot(right);
        double yCam = camToTarget.dot(up);
        double zCam = camToTarget.dot(forward);

        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        float fovDegrees = minecraft.options.fov().get();
        double fovRad = Math.toRadians(fovDegrees);
        double tanHalfFovY = Math.tan(fovRad / 2.0);
        double aspect = (double) screenWidth / (double) screenHeight;
        double tanHalfFovX = tanHalfFovY * aspect;

        if (zCam <= 0.1) {
            if (!drawArrowWhenOffscreen) {
                return null;
            }
        }

        double nx = xCam / (zCam * tanHalfFovX);
        double ny = yCam / (zCam * tanHalfFovY);

        boolean inside = zCam > 0.1 && nx >= -1.0 && nx <= 1.0 && ny >= -1.0 && ny <= 1.0;

        QuestIndicatorInfo info = new QuestIndicatorInfo();
        info.icon = icon;
        info.distanceText = ((int) distance) + "m";

        if (inside) {
            int screenX = (int) ((nx * 0.5 + 0.5) * screenWidth) - QUEST_ICON_SIZE / 2;
            int screenY = (int) ((-ny * 0.5 + 0.5) * screenHeight) - QUEST_ICON_SIZE / 2;
            info.iconX = screenX;
            info.iconY = screenY;
            info.drawArrow = false;
            info.arrowCenterX = 0;
            info.arrowCenterY = 0;
            info.arrowAngle = 0.0F;
            return info;
        }

        if (!drawArrowWhenOffscreen) {
            return null;
        }

        double dx = nx;
        double dy = -ny;
        double len = Math.sqrt(dx * dx + dy * dy);
        if (len < 1.0e-3) {
            dx = 0.0;
            dy = -1.0;
            len = 1.0;
        } else {
            dx /= len;
            dy /= len;
        }

        double a = (screenWidth  / 2.0) - QUEST_EDGE_MARGIN;
        double b = (screenHeight / 2.0) - QUEST_EDGE_MARGIN;
        double denom = (dx * dx) / (a * a) + (dy * dy) / (b * b);
        double t = denom > 1.0e-6 ? 1.0 / Math.sqrt(denom) : 0.0;

        int edgeX = (int) Math.round(centerX + dx * t);
        int edgeY = (int) Math.round(centerY + dy * t);

        int iconX = edgeX - QUEST_ICON_SIZE / 2;
        int iconY = edgeY - QUEST_ICON_SIZE / 2;

        info.iconX = iconX;
        info.iconY = iconY;
        info.drawArrow = true;
        info.arrowCenterX = edgeX;
        info.arrowCenterY = edgeY;

        double dirX = edgeX - centerX;
        double dirY = edgeY - centerY;
        info.arrowAngle = (float) (Math.atan2(dirX, -dirY) * (180.0 / Math.PI));

        return info;
    }

    // Helper method to reset state when needed (e.g., when GUI is closed)
    public static void reset() {
        fadeStartTime = 0;
        alpha = 0.0f;
        hasTask = false;
        lastHasTask = false;
        lastQuestShortDesc = Component.empty();
    }
}
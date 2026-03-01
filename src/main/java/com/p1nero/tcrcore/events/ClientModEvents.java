package com.p1nero.tcrcore.events;

import com.almostreliable.summoningrituals.Registration;
import com.almostreliable.summoningrituals.altar.AltarRenderer;
import com.ao.tcrmeshes.TCRMeshes;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.hm.efn.registries.EFNItem;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.block.client.AltarBlockRenderer;
import com.p1nero.tcrcore.block.entity.TCRBlockEntities;
import com.p1nero.tcrcore.client.gui.BlockTooltipHandler;
import com.p1nero.tcrcore.client.item_renderer.RenderDualBokken;
import com.p1nero.tcrcore.client.item_renderer.RenderTheIncinerator;
import com.p1nero.tcrcore.client.ponder.TCRPonderPlugin;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.entity.custom.aine_iris.AineIrisRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_boss.FakeBossNpcRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_end_golem.FakeEndGolemRenderer;
import com.p1nero.tcrcore.entity.custom.fake_npc.fake_sky_golem.FakeSkyGolemRenderer;
import com.p1nero.tcrcore.entity.custom.ferry_girl.FerryGirlGeoRenderer;
import com.p1nero.tcrcore.entity.custom.chronos_sol.ChronosSolGeoRenderer;
import com.p1nero.tcrcore.entity.custom.mimic.PTCRMimicRenderer;
import com.p1nero.tcrcore.entity.custom.mimic.TCRMimicRenderer;
import com.p1nero.tcrcore.entity.custom.ornn.OrnnlGeoRenderer;
import com.p1nero.tcrcore.entity.custom.tutorial_golem.TutorialGolemRenderer;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.api.client.model.Meshes;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;
import yesman.epicfight.client.renderer.patched.entity.PIronGolemRenderer;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->{

            EntityRenderers.register(TCREntities.CUSTOM_COLOR_ITEM.get(), ItemEntityRenderer::new);

            EntityRenderers.register(TCREntities.CHRONOS_SOL.get(), ChronosSolGeoRenderer::new);
            EntityRenderers.register(TCREntities.FERRY_GIRL.get(), FerryGirlGeoRenderer::new);
            EntityRenderers.register(TCREntities.ORNN.get(), OrnnlGeoRenderer::new);
            EntityRenderers.register(TCREntities.AINE.get(), AineIrisRenderer::new);
            EntityRenderers.register(TCREntities.FAKE_SKY_GOLEM.get(), FakeSkyGolemRenderer::new);
            EntityRenderers.register(TCREntities.FAKE_END_GOLEM.get(), FakeEndGolemRenderer::new);
            EntityRenderers.register(TCREntities.TUTORIAL_GOLEM.get(), TutorialGolemRenderer::new);

            EntityRenderers.register(TCREntities.TCR_MIMIC.get(), TCRMimicRenderer::new);

            EntityRenderers.register(TCREntities.FAKE_MALEDICTUS_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.MALEDICTUS_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_IGNIS_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.IGNIS_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_NETHERITE_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.NETHERITE_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_SCYLLA_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.SCYLLA_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.ENDER_GUARDIAN_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_HARBINGER_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.HARBINGER_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_LEVIATHAN_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.LEVIATHAN_HUMANOID_TEXTURE));

            EntityRenderers.register(TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get(),
                    context -> new FakeBossNpcRenderer(context, TCRMeshes.ANCIENT_REMAIN_HUMANOID_TEXTURE));

            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.ABYSS_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.THE_LEVIATHAN.get().getDescription().copy().withStyle(ChatFormatting.BLUE), ModItems.TIDAL_CLAWS.get().getDescription().copy().withStyle(ChatFormatting.GOLD)),
                    TCRCoreMod.getInfo("related_loot", ModEntities.CORALSSUS.get().getDescription(), EFNItem.NF_CLAW.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.CURSED_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.MALEDICTUS.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), ModItems.SOUL_RENDER.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.DESERT_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.ANCIENT_REMNANT.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), ModItems.WRATH_OF_THE_DESERT.get().getDescription().copy().withStyle(ChatFormatting.GOLD)),
                    TCRCoreMod.getInfo("related_loot", ModEntities.KOBOLEDIATOR.get().getDescription(), EFNItem.EXSILIUMGLADIUS.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.FLAME_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.IGNIS.get().getDescription().copy().withStyle(ChatFormatting.RED), ModItems.THE_INCINERATOR.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.STORM_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", ModEntities.SCYLLA.get().getDescription().copy().withStyle(ChatFormatting.AQUA), ModItems.CERAUNUS.get().getDescription().copy().withStyle(ChatFormatting.GOLD)));

            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.VOID_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", "???", "???"));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.MECH_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", "???", "???"));
            BlockTooltipHandler.registerBlockTooltip(TCRBlocks.MONST_ALTAR_BLOCK,
                    TCRCoreMod.getInfo("altar_dim_info"),
                    TCRCoreMod.getInfo("related_loot", "???", "???"));

            PonderIndex.addPlugin(new TCRPonderPlugin());

        });
    }

    @SubscribeEvent
    public static void onRenderPatched(PatchedRenderersEvent.Add event) {
        EntityRendererProvider.Context context = event.getContext();
        event.addPatchedEntityRenderer(TCREntities.TUTORIAL_GOLEM.get(), (entityType) -> new PIronGolemRenderer(context, entityType).initLayerLast(context, entityType));
        event.addPatchedEntityRenderer(EntityType.DROWNED, (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));
        event.addPatchedEntityRenderer(TCREntities.FAKE_SKY_GOLEM.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));
        event.addPatchedEntityRenderer(TCREntities.FAKE_END_GOLEM.get(), (entityType -> new PHumanoidRenderer<>(Meshes.BIPED_OLD_TEX, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.TCR_MIMIC.get(), (entityType -> new PTCRMimicRenderer(Meshes.BIPED_OLD_TEX, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_MALEDICTUS_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.MALEDICTUS_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_IGNIS_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.IGNIS_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_NETHERITE_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.NETHERITE_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_SCYLLA_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.SCYLLA_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_ENDER_GUARDIAN_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.ENDER_GUARDIAN_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_HARBINGER_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.HARBINGER_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_LEVIATHAN_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.LEVIATHAN_HUMANOID, context, entityType)));

        event.addPatchedEntityRenderer(TCREntities.FAKE_ANCIENT_REMNANT_HUMANOID.get(), (entityType ->
                new PHumanoidRenderer<>(TCRMeshes.ANCIENT_REMAIN_HUMANOID, context, entityType)));
    }

    @SubscribeEvent
    public static void onRendererSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TCRBlockEntities.ABYSS_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.DESERT_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.STORM_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.FLAME_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.CURSED_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.MONST_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.VOID_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);
        event.registerBlockEntityRenderer(TCRBlockEntities.MECH_ALTAR_BLOCK_ENTITY.get(), AltarBlockRenderer::new);

        event.registerBlockEntityRenderer(Registration.ALTAR_ENTITY.get(), AltarRenderer::new);
    }


    @SubscribeEvent
    public static void registerItemRenderer(PatchedRenderersEvent.RegisterItemRenderer event) {
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "dual_bokken"), RenderDualBokken::new);
        event.addItemRenderer(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "incinerator"), RenderTheIncinerator::new);
    }
}

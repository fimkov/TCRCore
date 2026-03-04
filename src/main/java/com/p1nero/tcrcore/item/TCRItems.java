package com.p1nero.tcrcore.item;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.L_Ender.cataclysm.items.The_Incinerator;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.item.custom.*;
import com.p1nero.tcrcore.utils.WaypointUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import xaero.hud.minimap.waypoint.WaypointColor;

public class TCRItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TCRCoreMod.MOD_ID);
    public static final RegistryObject<Item> ANCIENT_ORACLE_FRAGMENT = REGISTRY.register("ancient_oracle_fragment", () -> new OracleItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> ARTIFACT_TICKET = REGISTRY.register("artifact_ticket", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant(), true));
    public static final RegistryObject<Item> RARE_ARTIFACT_TICKET = REGISTRY.register("rare_artifact_ticket", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> ABYSS_CORE = REGISTRY.register("abyss_core", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> VOID_CORE = REGISTRY.register("void_core", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> DUAL_BOKKEN = REGISTRY.register("dual_bokken", () -> new DualBokkenItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant()));
    public static final RegistryObject<Item> PROOF_OF_ADVENTURE = REGISTRY.register("proof_of_adventure", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> PROOF_OF_ADVENTURE_PLUS = REGISTRY.register("proof_of_adventure_plus", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));
    public static final RegistryObject<Item> CORE_FLINT = REGISTRY.register("core_flint", () -> new CoreFlintItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> DRAGON_FLUTE = REGISTRY.register("dragon_flute", () -> new DragonFluteItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> ABYSS_FRAGMENT = REGISTRY.register("abyss_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.LEVIATHAN_HUMANOID::get));
    public static final RegistryObject<Item> DESERT_FRAGMENT = REGISTRY.register("desert_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.ANCIENT_REMNANT_HUMANOID::get));
    public static final RegistryObject<Item> ENDER_FRAGMENT = REGISTRY.register("ender_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.ENDER_GUARDIAN_HUMANOID::get));
    public static final RegistryObject<Item> MECH_FRAGMENT = REGISTRY.register("mech_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.HARBINGER_HUMANOID::get));
    public static final RegistryObject<Item> NETHERITE_FRAGMENT = REGISTRY.register("netherite_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.NETHERITE_HUMANOID::get));
    public static final RegistryObject<Item> FLAME_FRAGMENT = REGISTRY.register("flame_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.IGNIS_HUMANOID::get));
    public static final RegistryObject<Item> STORM_FRAGMENT = REGISTRY.register("storm_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.SCYLLA_HUMANOID::get));
    public static final RegistryObject<Item> SOUL_FRAGMENT = REGISTRY.register("soul_fragment", () -> new CataclysmHumanoidBossDropItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), TCRBossEntities.MALEDICTUS_HUMANOID::get));

    public static final RegistryObject<Item> RESONANCE_STONE = REGISTRY.register("resonance_stone", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> LAND_RESONANCE_STONE = REGISTRY.register("land_resonance_stone",
            () -> new LandResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.LAND_GOLEM), ResonanceStoneItem.SURFACE, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_LAND_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) -> {
                        if(pos != null && TCRCoreMod.isIsXaeroLoaded()) {
                            WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.DESERT_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtil.LAND_GOLEM)))), pos, WaypointColor.YELLOW);
                        }
                        TCRQuests.USE_LAND_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_DESERT_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> OCEAN_RESONANCE_STONE = REGISTRY.register("ocean_resonance_stone",
            () -> new OceanResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.OCEAN_GOLEM), 63, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_OCEAN_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.ABYSS_EYE.get().getDescription(), Component.translatable(Util.makeDescriptionId("structure", ResourceLocation.parse(WorldUtil.OCEAN_GOLEM)))), pos, WaypointColor.BLUE);
                        TCRQuests.USE_OCEAN_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_OCEAN_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> CURSED_RESONANCE_STONE = REGISTRY.register("cursed_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.AQUAMIRAE_SHIP_STRUCTURE), 63, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_CURSED_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.CURSED_EYE.get().getDescription(), Component.translatable("structure.aquamirae.ice_maze")), pos, WaypointColor.DARK_GREEN);
                        TCRQuests.USE_CURSED_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_CURSED_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> CORE_RESONANCE_STONE = REGISTRY.register("core_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.CORE_GOLEM), -56, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_CORE_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.FLAME_EYE.get().getDescription(), Component.translatable("structure.ba_bt.core_tower")), pos, WaypointColor.RED);
                        TCRQuests.USE_CORE_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_FLAME_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> NETHER_RESONANCE_STONE = REGISTRY.register("nether_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.NETHER_GOLEM), 35, Level.NETHER, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_NETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.MONSTROUS_EYE.get().getDescription(), Component.translatable("structure.tcrcore.gate_of_disaster")), pos, WaypointColor.DARK_RED);
                        TCRQuests.USE_NETHER_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_MONST_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> SKY_RESONANCE_STONE = REGISTRY.register("sky_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.SKY_GOLEM), 160, AetherDimensions.AETHER_LEVEL, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_AETHER_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", ModItems.STORM_EYE.get().getDescription(), Component.translatable("structure.lost_aether_content.platinum_dungeon")), pos, WaypointColor.AQUA);
                        TCRQuests.USE_AETHER_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GET_STORM_EYE.start(serverPlayer);
                    }))
    );

    public static final RegistryObject<Item> END_RESONANCE_STONE = REGISTRY.register("end_resonance_stone",
            () -> new ResonanceStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), ResourceLocation.parse(WorldUtil.STRONG_HOLD), ResonanceStoneItem.SURFACE, Level.OVERWORLD, (serverPlayer) ->
                    TCRQuestManager.hasQuest(serverPlayer, TCRQuests.USE_END_RESONANCE_STONE) || serverPlayer.isCreative(),
                    ((pos, serverPlayer) ->
                    {
                        if(!TCRCoreMod.isIsXaeroLoaded()) {
                            return;
                        }
                        WaypointUtil.sendWaypoint(serverPlayer, "eye_pos_mark", TCRCoreMod.getInfo("eye_pos_mark", Blocks.END_PORTAL.getName(), Component.translatable("structure.integrated_stronghold.stronghold")), pos, WaypointColor.PURPLE);
                        TCRQuests.USE_END_RESONANCE_STONE.finish(serverPlayer, true);
                        TCRQuests.GO_TO_THE_END.start(serverPlayer);
                    }), false)
    );

    public static final RegistryObject<Item> STONE_OF_REINCARNATION = REGISTRY.register("stone_of_reincarnation", () -> new StoneOfReincarnationItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> MYSTERIOUS_WEAPONS = REGISTRY.register("mysterious_weapons", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> NECROMANCY_SCROLL = REGISTRY.register("necromancy_scroll", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> WITHER_SOUL_STONE = REGISTRY.register("wither_soul_stone", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), false));

    public static final RegistryObject<Item> WITHER_SOUL_STONE_ACTIVATED = REGISTRY.register("wither_soul_stone_activated", () -> new WitherSoulStoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> DIVINE_FRAGMENT = REGISTRY.register("divine_fragment", () -> new SimpleDescriptionItem(new Item.Properties().stacksTo(16).rarity(Rarity.EPIC).fireResistant(), true));

    public static final RegistryObject<Item> MAGIC_BOTTLE = REGISTRY.register("magic_bottle", () -> new BlueBottle(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final RegistryObject<Item> THE_INCINERATOR_SOUL = REGISTRY.register("the_incinerator_soul", () -> new The_Incinerator((new Item.Properties()).stacksTo(1).rarity(Rarity.EPIC).fireResistant()));
}

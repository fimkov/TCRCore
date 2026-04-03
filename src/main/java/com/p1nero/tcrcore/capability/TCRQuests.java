package com.p1nero.tcrcore.capability;

import com.brass_amber.ba_bt.init.BTEntityType;
import com.brass_amber.ba_bt.init.BTItems;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.alexthe668.domesticationinnovation.server.item.DIItemRegistry;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.hm.efn.registries.EFNItem;
import com.obscuria.aquamirae.registry.AquamiraeEntities;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager.Quest;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.yungnickyoung.minecraft.ribbits.module.EntityTypeModule;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class TCRQuests {

    public static final ResourceLocation SIDE_QUEST_1 = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/side_quest_1.png");
    public static final ResourceLocation SIDE_QUEST_2 = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/side_quest_2.png");

    //通用的一些任务
    //等共鸣石充能
    public static Quest WAIT_RESONANCE_STONE_CHARGE;
    //放置祭坛之上
    public static Quest PUT_DESERT_EYE_ON_ALTAR;
    public static Quest PUT_ABYSS_EYE_ON_ALTAR;
    public static Quest PUT_CURSED_EYE_ON_ALTAR;
    public static Quest PUT_FLAME_EYE_ON_ALTAR;
    public static Quest PUT_MECH_EYE_ON_ALTAR;
    public static Quest PUT_STORM_EYE_ON_ALTAR;
    public static Quest PUT_VOID_EYE_ON_ALTAR;
    public static Quest PUT_MONST_EYE_ON_ALTAR;
    //去女神像处祈福
    public static Quest BLESS_ON_THE_GODNESS_STATUE;
    //向安了解幻境
    public static Quest TALK_TO_AINE_CLOUDLAND;

    //序章
    public static Quest TALK_TO_AINE_0;
    public static Quest TALK_TO_CHRONOS_0;
    public static Quest TALK_TO_FERRY_GIRL_0;
    public static Quest TALK_TO_ORNN_0;

    //养龙支线
    public static Quest TAME_DRAGON;
    public static Quest TAME_DRAGON_BACK_TO_FERRY_GIRL;

    //前往获取大地之眼
    public static Quest USE_LAND_RESONANCE_STONE;
    public static Quest GET_DESERT_EYE;
    public static Quest TALK_TO_CHRONOS_1;
    //奇美拉支线
    public static Quest BONE_CHIMERA_QUEST;
    //百兵图给ornn
    public static Quest TALK_TO_ORNN_1;

    //获取海洋眼
    public static Quest TALK_TO_CHRONOS_2;
    //前往主世界-海洋眼
    public static Quest GO_TO_OVERWORLD_OCEAN;
    public static Quest USE_OCEAN_RESONANCE_STONE;
    public static Quest GET_OCEAN_EYE;
    public static Quest TALK_TO_CHRONOS_3;
    //呱呱支线
    public static Quest RIBBITS_QUEST;
    public static Quest GIVE_AMETHYST_BLOCK_TO_RIBBITS;

    //主线·灵魂之章
    public static Quest TALK_TO_AINE_ECHO;
    public static Quest TALK_TO_CHRONOS_4;
    public static Quest GO_TO_OVERWORLD_CURSED;
    public static Quest USE_CURSED_RESONANCE_STONE;
    public static Quest GET_CURSED_EYE;
    public static Quest TALK_TO_CHRONOS_5;

    //铁魔法开启
    public static Quest TALK_TO_AINE_MAGIC;
    public static Quest TRY_TO_LEARN_MAGIC;
    public static Quest TALK_TO_AINE_MAGIC_2;

    //主线·炉心傀儡
    public static Quest TALK_TO_CHRONOS_6;
    public static Quest GO_TO_OVERWORLD_CORE;
    public static Quest USE_CORE_RESONANCE_STONE;
    public static Quest GET_FLAME_EYE;
    public static Quest TALK_TO_CHRONOS_7;
    //间章，和安闲聊
    public static Quest TALK_TO_AINE_1;

    //主线·地狱傀儡
    public static Quest TALK_TO_CHRONOS_8;
    public static Quest GO_TO_NETHER;
    public static Quest USE_NETHER_RESONANCE_STONE;
    public static Quest GET_NETHER_MONOLITH_KEY_1;//制作铃铛，召唤boss，肘下界堡垒
    public static Quest GET_NETHER_MONOLITH_KEY_2;//肘铁魔法城堡
    public static Quest GET_MONST_EYE;
    public static Quest TALK_TO_CHRONOS_9;

    //主线·毁灭之章
    public static Quest GET_WITHER_EYE;
    public static Quest TALK_TO_CHRONOS_10;
    public static Quest TALK_TO_AINE_SAMSARA;
    public static Quest GO_TO_SAMSARA;

    //主线·天域之章
    public static Quest TALK_TO_CHRONOS_11;
    public static Quest GO_TO_AETHER;
    public static Quest USE_AETHER_RESONANCE_STONE;
    public static Quest GET_AETHER_MONOLITH_KEY_1;//肘女武神殿
    public static Quest GET_AETHER_MONOLITH_KEY_2;//肘烈阳殿
    public static Quest GET_STORM_EYE;
    public static Quest TALK_TO_SKY_GOLEM;
    public static Quest TALK_TO_CHRONOS_12;
    public static Quest TALK_TO_AINE_2;

    //主线·末地之章
    public static Quest GO_TO_OVERWORLD_END;
    public static Quest USE_END_RESONANCE_STONE;
    public static Quest GO_TO_THE_END;
    public static Quest GET_VOID_EYE;

    public static Quest TALK_TO_ORNN_YAMATO;

    //大结局
    public static Quest TALK_TO_CHRONOS_END;
    public static Quest KILL_MAD_CHRONOS;

    //后日谈
    public static Quest TALK_TO_AINE_GAME_CLEAR;

    public static void init() {

        WAIT_RESONANCE_STONE_CHARGE = TCRQuestManager.create("wait_resonance_stone_charge")
                .shortDescParam(TCRItems.RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription());

        PUT_DESERT_EYE_ON_ALTAR = TCRQuestManager.create("put_desert_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .withTrackingPos(new BlockPos(WorldUtil.DESERT_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_ABYSS_EYE_ON_ALTAR = TCRQuestManager.create("put_abyss_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .withTrackingPos(new BlockPos(WorldUtil.ABYSS_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_CURSED_EYE_ON_ALTAR = TCRQuestManager.create("put_cursed_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .withTrackingPos(new BlockPos(WorldUtil.CURSED_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_FLAME_EYE_ON_ALTAR = TCRQuestManager.create("put_flame_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .withTrackingPos(new BlockPos(WorldUtil.FLAME_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MECH_EYE_ON_ALTAR = TCRQuestManager.create("put_mech_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .withTrackingPos(new BlockPos(WorldUtil.MECH_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_STORM_EYE_ON_ALTAR = TCRQuestManager.create("put_storm_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .withTrackingPos(new BlockPos(WorldUtil.STORM_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_VOID_EYE_ON_ALTAR = TCRQuestManager.create("put_void_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .withTrackingPos(new BlockPos(WorldUtil.VOID_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MONST_EYE_ON_ALTAR = TCRQuestManager.create("put_monst_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .withTrackingPos(new BlockPos(WorldUtil.MONST_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        BLESS_ON_THE_GODNESS_STATUE = TCRQuestManager.create("bless_on_the_godness_statue")
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.GODNESS_STATUE_POS), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_CLOUDLAND = TCRQuestManager.create("talk_to_aine_cloudland")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_0 = TCRQuestManager.create("talk_to_aine_0")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_CHRONOS_0 = TCRQuestManager.create("talk_to_col_0")
                .descParam(TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_FERRY_GIRL_0 = TCRQuestManager.create("talk_to_ferry_girl_0")
                .shortDescParam(WorldUtil.OVERWORLD_NAME)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), WorldUtil.OVERWORLD_NAME)
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_ORNN_0 = TCRQuestManager.create("talk_to_ornn_0")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TAME_DRAGON = TCRQuestManager.create("tame_dragon")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(),
                        net.magister.bookofdragons.item.ModItems.BOOK_OF_DRAGONS.get().getDescription(),
                        Component.translatable("item.domesticationinnovation.collar_tag"),
                        Items.SADDLE.getDescription());

        TAME_DRAGON_BACK_TO_FERRY_GIRL = TCRQuestManager.create("tame_dragon_back_to_ferry_girl")
                .shortDescParam(TCREntities.FERRY_GIRL.get().getDescription())
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), TCREntities.FERRY_GIRL.get().getDescription(), Items.SADDLE.getDescription(), DIItemRegistry.COLLAR_TAG.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);

        USE_LAND_RESONANCE_STONE = TCRQuestManager.create("use_land_resonance_stone")
                .shortDescParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription())
                .descParam(WorldUtil.OVERWORLD_NAME, TCRItems.LAND_RESONANCE_STONE.get().getDescription());

        GET_DESERT_EYE = TCRQuestManager.create("get_desert_eye")
                .shortDescParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(),
                        ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW),
                        Items.SPAWNER.getDescription());

        BONE_CHIMERA_QUEST = TCRQuestManager.create("bone_chimera_quest")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription())
                .shortDescParam(WorldUtil.getStructureName(WorldUtil.BONE_CHIMERA_STRUCTURE));

        TALK_TO_ORNN_1 = TCRQuestManager.create("talk_to_ornn_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(ModEntities.BONE_CHIMERA.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription(),TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_1 = TCRQuestManager.create("talk_to_chronos_1")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_2 = TCRQuestManager.create("talk_to_chronos_2")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_OCEAN = TCRQuestManager.create("go_to_overworld_ocean")
                .shortDescParam(WorldUtil.OVERWORLD_NAME)
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), WorldUtil.OVERWORLD_NAME,
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        USE_OCEAN_RESONANCE_STONE = TCRQuestManager.create("use_ocean_resonance_stone")
                .shortDescParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription())
                .descParam(WorldUtil.OVERWORLD_NAME, TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        GET_OCEAN_EYE = TCRQuestManager.create("get_ocean_eye")
                .shortDescParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        Items.SPAWNER.getDescription());

        TALK_TO_CHRONOS_3 = TCRQuestManager.create("talk_to_chronos_3")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        TCREntities.CHRONOS_SOL.get().getDescription(),
                        AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        RIBBITS_QUEST = TCRQuestManager.create("ribbits_quest")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(WorldUtil.getStructureName(WorldUtil.RIBBIT_VILLAGE))
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(),
                        Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()),
                        artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());
        GIVE_AMETHYST_BLOCK_TO_RIBBITS = TCRQuestManager.create("give_amethyst_block_to_ribbits")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(Items.AMETHYST_BLOCK.getDescription(), EntityTypeModule.RIBBIT.get().getDescription())
                .descParam(ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE),
                        EntityTypeModule.RIBBIT.get().getDescription(),
                        Items.AMETHYST_BLOCK.getDescription(),
                        Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()),
                        artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());

        TALK_TO_AINE_ECHO = TCRQuestManager.create("talk_to_aine_echo")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_4 = TCRQuestManager.create("talk_to_chronos_4")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription(),
                        AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_CURSED = TCRQuestManager.create("go_to_overworld_cursed")
                .shortDescParam(WorldUtil.OVERWORLD_NAME)
                .descParam(AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        WorldUtil.OVERWORLD_NAME,
                        TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        USE_CURSED_RESONANCE_STONE = TCRQuestManager.create("use_cursed_resonance_stone")
                .shortDescParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        WorldUtil.OVERWORLD_NAME,
                        TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        GET_CURSED_EYE = TCRQuestManager.create("get_cursed_eye")
                .shortDescParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        AquamiraeEntities.CAPTAIN_CORNELIA.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));

        TALK_TO_CHRONOS_5 = TCRQuestManager.create("talk_to_chronos_5")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_MAGIC = TCRQuestManager.create("talk_to_aine_magic")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(AquamiraeEntities.CAPTAIN_CORNELIA.get().getDescription(),
                        TCRItems.NECROMANCY_SCROLL.get().getDescription(),
                        TCREntities.AINE.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TRY_TO_LEARN_MAGIC = TCRQuestManager.create("try_to_learn_magic")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCRItems.NECROMANCY_SCROLL.get().getDescription(), TCREntities.AINE.get().getDescription(),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_MAGIC_2 = TCRQuestManager.create("talk_to_aine_magic_2")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_6 = TCRQuestManager.create("talk_to_chronos_6")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_CORE = TCRQuestManager.create("go_to_overworld_core")
                .shortDescParam(WorldUtil.OVERWORLD_NAME)
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), WorldUtil.OVERWORLD_NAME,
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        USE_CORE_RESONANCE_STONE = TCRQuestManager.create("use_core_resonance_stone")
                .shortDescParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(WorldUtil.OVERWORLD_NAME, TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        GET_FLAME_EYE = TCRQuestManager.create("get_flame_eye")
                .shortDescParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        TALK_TO_CHRONOS_7 = TCRQuestManager.create("talk_to_chronos_7")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_1 = TCRQuestManager.create("talk_to_aine_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_8 = TCRQuestManager.create("talk_to_chronos_8")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_NETHER = TCRQuestManager.create("go_to_nether")
                .shortDescParam(WorldUtil.NETHER_NAME)
                .descParam(WorldUtil.OVERWORLD_NAME, WorldUtil.OVERWORLD_NAME, TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.CORE_FLINT.get().getDescription(), Blocks.NETHER_PORTAL.getName());

        USE_NETHER_RESONANCE_STONE = TCRQuestManager.create("use_nether_resonance_stone")
                .shortDescParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(WorldUtil.NETHER_NAME, TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED));

        GET_NETHER_MONOLITH_KEY_1 = TCRQuestManager.create("get_nether_monolith_key_1")
                .shortDescParam(ItemRegistry.CINDEROUS_SOULCALLER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        WorldUtil.getStructureName(WorldUtil.NETHER_KEY_1),
                        ItemRegistry.CINDEROUS_SOULCALLER.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        EntityRegistry.FIRE_BOSS.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        GET_NETHER_MONOLITH_KEY_2 = TCRQuestManager.create("get_nether_monolith_key_2")
                .shortDescParam(BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        BTItems.NETHER_MONOLITH_KEY.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        WorldUtil.getStructureName(WorldUtil.NETHER_KEY_2),
                        WorldUtil.getStructureName(WorldUtil.NETHER_KEY_2),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        EntityRegistry.FIRE_BOSS.get().getDescription().copy().withStyle(ChatFormatting.RED),
                        BTEntityType.NETHER_MONOLITH.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        GET_MONST_EYE = TCRQuestManager.create("get_monst_eye")
                .shortDescParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        TCRSkills.FIRE_AVOID.getDisplayName().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_9 = TCRQuestManager.create("talk_to_chronos_9")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GET_WITHER_EYE = TCRQuestManager.create("get_wither_eye")
                .shortDescParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), EntityType.WITHER.getDescription().copy().withStyle(ChatFormatting.GOLD),
                        ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_10 = TCRQuestManager.create("talk_to_chronos_10")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_SAMSARA = TCRQuestManager.create("talk_to_aine_samsara")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.WITHER_SOUL_STONE.get().getDescription(),
                        Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"),
                        Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_SAMSARA = TCRQuestManager.create("go_to_samsara")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1").withStyle(ChatFormatting.RED))
                .descParam(Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1").withStyle(ChatFormatting.RED),
                        TCREntities.AINE.get().getDescription().copy().withStyle(ChatFormatting.GOLD),
                        TCRItems.WITHER_SOUL_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));

        TALK_TO_CHRONOS_11 = TCRQuestManager.create("talk_to_chronos_11")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_AETHER = TCRQuestManager.create("go_to_aether")
                .shortDescParam(WorldUtil.AETHER_NAME)
                .descParam(Blocks.WATER.getName().withStyle(ChatFormatting.BLUE), Blocks.GLOWSTONE.getName().withStyle(ChatFormatting.YELLOW));

        USE_AETHER_RESONANCE_STONE = TCRQuestManager.create("use_aether_resonance_stone")
                .shortDescParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        GET_STORM_EYE = TCRQuestManager.create("get_storm_eye")
                .shortDescParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA))
                .descParam(TCRItems.SKY_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA));

        TALK_TO_SKY_GOLEM = TCRQuestManager.create("talk_to_sky_golem")
                .shortDescParam(BTEntityType.SKY_GOLEM.get().getDescription())
                .descParam(BTEntityType.SKY_GOLEM.get().getDescription(), BTEntityType.SKY_GOLEM.get().getDescription());

        TALK_TO_CHRONOS_12 = TCRQuestManager.create("talk_to_chronos_12")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_2 = TCRQuestManager.create("talk_to_aine_2")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(ModItems.STORM_EYE.get().getDescription().copy().withStyle(ChatFormatting.AQUA),
                        TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_END = TCRQuestManager.create("go_to_overworld_end")
                .shortDescParam(WorldUtil.OVERWORLD_NAME)
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        WorldUtil.END_NAME,
                        WorldUtil.OVERWORLD_NAME,
                        WorldUtil.END_NAME,
                        WorldUtil.getStructureName(WorldUtil.STRONG_HOLD),
                        WorldUtil.END_NAME);

        USE_END_RESONANCE_STONE = TCRQuestManager.create("use_end_resonance_stone")
                .shortDescParam(TCRItems.END_RESONANCE_STONE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(),
                        WorldUtil.END_NAME,
                        WorldUtil.OVERWORLD_NAME,
                        WorldUtil.END_NAME,
                        WorldUtil.getStructureName(WorldUtil.STRONG_HOLD),
                        WorldUtil.END_NAME);

        GO_TO_THE_END = TCRQuestManager.create("go_to_the_end")
                .shortDescParam(WorldUtil.END_NAME)
                .descParam(TCRItems.END_RESONANCE_STONE.get().getDescription(),
                        WorldUtil.getStructureName(WorldUtil.STRONG_HOLD),
                        WorldUtil.END_NAME);

        GET_VOID_EYE = TCRQuestManager.create("get_void_eye")
                .shortDescParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE),
                        WorldUtil.END_NAME);

        TALK_TO_ORNN_YAMATO = TCRQuestManager.create("talk_to_ornn_yamato")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(EFNItem.YAMATO_DMC_IN_SHEATH.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_END = TCRQuestManager.create("talk_to_chronos_end")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(ModItems.VOID_EYE.get().getDescription().copy().withStyle(ChatFormatting.LIGHT_PURPLE))
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        KILL_MAD_CHRONOS = TCRQuestManager.create("kill_mad_chronos")
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_GAME_CLEAR = TCRQuestManager.create("talk_to_aine_game_clear")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

    }
}
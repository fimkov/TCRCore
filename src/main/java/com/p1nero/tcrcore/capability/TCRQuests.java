package com.p1nero.tcrcore.capability;

import com.github.dodo.dodosmobs.init.ModEntities;
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
import net.magister.bookofdragons.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

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
    public static Quest GET_MONST_EYE;
    public static Quest TALK_TO_CHRONOS_9;

    //主线·毁灭之章
    public static Quest GET_WITHER_EYE;
    public static Quest TALK_TO_CHRONOS_10;
    public static Quest TALK_TO_AINE_SAMSARA;

    //主线·天域之章
    public static Quest TALK_TO_CHRONOS_11;
    public static Quest GO_TO_AETHER;
    public static Quest USE_AETHER_RESONANCE_STONE;
    public static Quest GET_STORM_EYE;
    public static Quest TALK_TO_CHRONOS_12;

    public static void init() {

        WAIT_RESONANCE_STONE_CHARGE = TCRQuestManager.create("wait_resonance_stone_charge")
                .shortDescParam(TCRItems.RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription());

        PUT_DESERT_EYE_ON_ALTAR = TCRQuestManager.create("put_desert_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .withTrackingPos(new BlockPos(WorldUtil.DESERT_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_ABYSS_EYE_ON_ALTAR = TCRQuestManager.create("put_abyss_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE))
                .withTrackingPos(new BlockPos(WorldUtil.ABYSS_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_CURSED_EYE_ON_ALTAR = TCRQuestManager.create("put_cursed_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .withTrackingPos(new BlockPos(WorldUtil.CURSED_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_FLAME_EYE_ON_ALTAR = TCRQuestManager.create("put_flame_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.FLAME_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MECH_EYE_ON_ALTAR = TCRQuestManager.create("put_mech_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.MECH_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.MECH_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.MECH_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_STORM_EYE_ON_ALTAR = TCRQuestManager.create("put_storm_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.STORM_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.STORM_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.STORM_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_VOID_EYE_ON_ALTAR = TCRQuestManager.create("put_void_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.VOID_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.VOID_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.VOID_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_MONST_EYE_ON_ALTAR = TCRQuestManager.create("put_monst_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription())
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
                .descParam(TCREntities.FERRY_GIRL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_ORNN_0 = TCRQuestManager.create("talk_to_ornn_0")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TAME_DRAGON = TCRQuestManager.create("tame_dragon")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), ModItems.BOOK_OF_DRAGONS.get().getDescription(), Component.translatable("item.domesticationinnovation.collar_tag"), Component.translatable("block.domesticationinnovation.pet_bed_white"));

        TAME_DRAGON_BACK_TO_FERRY_GIRL = TCRQuestManager.create("tame_dragon_back_to_ferry_girl")
                .shortDescParam(TCREntities.FERRY_GIRL.get().getDescription())
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), TCREntities.FERRY_GIRL.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);

        USE_LAND_RESONANCE_STONE = TCRQuestManager.create("use_land_resonance_stone")
                .shortDescParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription());

        GET_DESERT_EYE = TCRQuestManager.create("get_desert_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW))
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), Items.SPAWNER.getDescription());

        BONE_CHIMERA_QUEST = TCRQuestManager.create("bone_chimera_quest")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription())
                .shortDescParam(Component.translatable("structure.dodosmobs.jungle_prison"));

        TALK_TO_ORNN_1 = TCRQuestManager.create("talk_to_ornn_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(ModEntities.BONE_CHIMERA.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription(),TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_1 = TCRQuestManager.create("talk_to_chronos_1")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription().copy().withStyle(ChatFormatting.YELLOW), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_2 = TCRQuestManager.create("talk_to_chronos_2")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_OCEAN = TCRQuestManager.create("go_to_overworld_ocean")
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        USE_OCEAN_RESONANCE_STONE = TCRQuestManager.create("use_ocean_resonance_stone")
                .shortDescParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE));

        GET_OCEAN_EYE = TCRQuestManager.create("get_ocean_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE), TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE), Items.SPAWNER.getDescription());

        TALK_TO_CHRONOS_3 = TCRQuestManager.create("talk_to_chronos_3")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE), TCREntities.CHRONOS_SOL.get().getDescription(), AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        RIBBITS_QUEST = TCRQuestManager.create("ribbits_quest")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(Component.translatable("structure.ribbits.ribbit_village"))
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(), Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()), artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());
        GIVE_AMETHYST_BLOCK_TO_RIBBITS = TCRQuestManager.create("give_amethyst_block_to_ribbits")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(Items.AMETHYST_BLOCK.getDescription(), EntityTypeModule.RIBBIT.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription().copy().withStyle(ChatFormatting.BLUE), EntityTypeModule.RIBBIT.get().getDescription(), Items.AMETHYST_BLOCK.getDescription(),
                        Component.translatable(TCRSkills.WATER_AVOID.getTranslationKey()), artifacts.registry.ModItems.CHARM_OF_SINKING.get().getDescription());

        TALK_TO_AINE_ECHO = TCRQuestManager.create("talk_to_aine_echo")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_4 = TCRQuestManager.create("talk_to_chronos_4")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCREntities.AINE.get().getDescription(), AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_CURSED = TCRQuestManager.create("go_to_overworld_cursed")
                .descParam(AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA), com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        USE_CURSED_RESONANCE_STONE = TCRQuestManager.create("use_cursed_resonance_stone")
                .shortDescParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription().copy().withStyle(ChatFormatting.AQUA), com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), TCRItems.CURSED_RESONANCE_STONE.get().getDescription());

        GET_CURSED_EYE = TCRQuestManager.create("get_cursed_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN))
                .descParam(TCRItems.CURSED_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), AquamiraeEntities.CAPTAIN_CORNELIA.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN));

        TALK_TO_CHRONOS_5 = TCRQuestManager.create("talk_to_chronos_5")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_GREEN), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_MAGIC = TCRQuestManager.create("talk_to_aine_magic")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(AquamiraeEntities.CAPTAIN_CORNELIA.get().getDescription(), TCRItems.NECROMANCY_SCROLL.get().getDescription(), TCREntities.AINE.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TRY_TO_LEARN_MAGIC = TCRQuestManager.create("try_to_learn_magic")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCRItems.NECROMANCY_SCROLL.get().getDescription(), TCREntities.AINE.get().getDescription(), TCREntities.AINE.get().getDescription())
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
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        USE_CORE_RESONANCE_STONE = TCRQuestManager.create("use_core_resonance_stone")
                .shortDescParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED), com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        GET_FLAME_EYE = TCRQuestManager.create("get_flame_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED))
                .descParam(TCRItems.CORE_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.RED), com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED), com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED), com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED));

        TALK_TO_CHRONOS_7 = TCRQuestManager.create("talk_to_chronos_7")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.FLAME_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED), TCREntities.CHRONOS_SOL.get().getDescription())
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
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.CORE_FLINT.get().getDescription());

        USE_NETHER_RESONANCE_STONE = TCRQuestManager.create("use_nether_resonance_stone")
                .shortDescParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED), com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED));

        GET_MONST_EYE = TCRQuestManager.create("get_monst_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED))
                .descParam(TCRItems.NETHER_RESONANCE_STONE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED), com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED), com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.DARK_RED), TCRSkills.FIRE_AVOID.getDisplayName().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_9 = TCRQuestManager.create("talk_to_chronos_9")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.MONSTROUS_EYE.get().getDescription().copy().withStyle(ChatFormatting.RED), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GET_WITHER_EYE = TCRQuestManager.create("get_wither_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD))
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), EntityType.WITHER.getDescription().copy().withStyle(ChatFormatting.GOLD), com.github.L_Ender.cataclysm.init.ModItems.MECH_EYE.get().getDescription().copy().withStyle(ChatFormatting.GOLD));

        TALK_TO_CHRONOS_10 = TCRQuestManager.create("talk_to_chronos_10")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_SAMSARA = TCRQuestManager.create("talk_to_aine_samsara")
                .shortDescParam(TCREntities.AINE.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCRItems.WITHER_SOUL_STONE.get().getDescription(), Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"), Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"), TCREntities.AINE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

    }
}

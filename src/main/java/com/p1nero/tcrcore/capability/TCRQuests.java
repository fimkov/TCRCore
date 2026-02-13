package com.p1nero.tcrcore.capability;

import com.github.dodo.dodosmobs.init.ModEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager.Quest;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import net.magister.bookofdragons.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

    public static void init() {

        WAIT_RESONANCE_STONE_CHARGE = TCRQuestManager.create("wait_resonance_stone_charge")
                .shortDescParam(TCRItems.RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription());

        PUT_DESERT_EYE_ON_ALTAR = TCRQuestManager.create("put_desert_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.DESERT_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_ABYSS_EYE_ON_ALTAR = TCRQuestManager.create("put_abyss_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ABYSS_EYE_ALTAR_POS.above()), TCRDimensions.SANCTUM_LEVEL_KEY);

        PUT_CURSED_EYE_ON_ALTAR = TCRQuestManager.create("put_cursed_eye_on_altar")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription())
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
                .shortDescParam(TCREntities.AINE_IRIS.get().getDescription())
                .descParam(TCREntities.AINE_IRIS.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_AINE_0 = TCRQuestManager.create("talk_to_aine_0")
                .shortDescParam(TCREntities.AINE_IRIS.get().getDescription())
                .descParam(TCREntities.AINE_IRIS.get().getDescription(), TCREntities.AINE_IRIS.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.AINE_POS.above(2)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_CHRONOS_0 = TCRQuestManager.create("talk_to_col_0")
                .descParam(TCREntities.AINE_IRIS.get().getDescription(), TCREntities.AINE_IRIS.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_FERRY_GIRL_0 = TCRQuestManager.create("talk_to_ferry_girl_0")
                .descParam(TCREntities.FERRY_GIRL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);
        TALK_TO_ORNN_0 = TCRQuestManager.create("talk_to_ornn_0")
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(TCREntities.CHRONOS_SOL.get().getDescription(), TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TAME_DRAGON = TCRQuestManager.create("tame_dragon")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), ModItems.BOOK_OF_DRAGONS.get().getDescription());

        TAME_DRAGON_BACK_TO_FERRY_GIRL = TCRQuestManager.create("tame_dragon_back_to_ferry_girl")
                .shortDescParam(TCREntities.FERRY_GIRL.get().getDescription())
                .descParam(TCREntities.FERRY_GIRL.get().getDescription(), TCREntities.FERRY_GIRL.get().getDescription())
                .withIcon(SIDE_QUEST_1)
                .withTrackingPos(new BlockPos(WorldUtil.FERRY_GIRL_POS.above(1)), TCRDimensions.SANCTUM_LEVEL_KEY);

        USE_LAND_RESONANCE_STONE = TCRQuestManager.create("use_land_resonance_stone")
                .shortDescParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription());

        GET_DESERT_EYE = TCRQuestManager.create("get_desert_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription())
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription(), Items.SPAWNER.getDescription());

        BONE_CHIMERA_QUEST = TCRQuestManager.create("bone_chimera_quest")
                .withIcon(SIDE_QUEST_1)
                .descParam(TCRItems.LAND_RESONANCE_STONE.get().getDescription())
                .shortDescParam(Component.translatable("structure.dodosmobs.jungle_prison"));

        TALK_TO_ORNN_1 = TCRQuestManager.create("talk_to_ornn_1")
                .withIcon(SIDE_QUEST_1)
                .shortDescParam(TCREntities.ORNN.get().getDescription())
                .descParam(ModEntities.BONE_CHIMERA.get().getDescription(), TCRItems.MYSTERIOUS_WEAPONS.get().getDescription(),TCREntities.ORNN.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.ORNN_POS.above(3)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_1 = TCRQuestManager.create("talk_to_chronos_1")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.DESERT_EYE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        TALK_TO_CHRONOS_2 = TCRQuestManager.create("talk_to_chronos_2")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

        GO_TO_OVERWORLD_OCEAN = TCRQuestManager.create("go_to_overworld_ocean")
                .descParam(TCRItems.RESONANCE_STONE.get().getDescription());

        USE_OCEAN_RESONANCE_STONE = TCRQuestManager.create("use_ocean_resonance_stone")
                .shortDescParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription())
                .descParam(TCRItems.OCEAN_RESONANCE_STONE.get().getDescription());

        GET_OCEAN_EYE = TCRQuestManager.create("get_ocean_eye")
                .shortDescParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription(), TCRItems.OCEAN_RESONANCE_STONE.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription(), Items.SPAWNER.getDescription());

        TALK_TO_CHRONOS_3 = TCRQuestManager.create("talk_to_chronos_3")
                .shortDescParam(TCREntities.CHRONOS_SOL.get().getDescription())
                .descParam(com.github.L_Ender.cataclysm.init.ModItems.ABYSS_EYE.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription())
                .withTrackingPos(new BlockPos(WorldUtil.CHRONOS_SOL_BLOCK_POS.above(4)), TCRDimensions.SANCTUM_LEVEL_KEY);

    }
}

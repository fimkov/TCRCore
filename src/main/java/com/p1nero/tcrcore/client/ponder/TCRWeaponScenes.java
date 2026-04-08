package com.p1nero.tcrcore.client.ponder;

import com.nameless.falchion.gameasset.FalchionAnimations;
import com.p1nero.tcrcore.item.TCRItems;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.arc.epic_ponder.api.ponder.EpicFightSceneBuilder;
import org.arc.epic_ponder.client.ponder.EFPSceneUtils;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.item.EpicFightItems;

public class TCRWeaponScenes {
    public static void showcaseFalchionBasicAttackCombo(SceneBuilder baseScene, SceneBuildingUtil util) {
        EFPSceneUtils.showcaseStandardWeaponCombo(baseScene, util, 11, "tcrcore:falchion_basic_attack_combo",
                TCRItems.ASHEN_CRESCENT.get().getDefaultInstance());
    }

    public static void showcaseFalchionArt(SceneBuilder baseScene, SceneBuildingUtil util) {
        EpicFightSceneBuilder builder = new EpicFightSceneBuilder(baseScene);
        EpicFightSceneBuilder.EpicFightWorldInstructions world = builder.world();

        EFPSceneUtils.setupStandardScene(builder, 11, "falchion_art", "tcrcore.ponder.falchion_art.title");
        ElementLink<EntityElement> attacker = EFPSceneUtils.spawnDummyActor(builder, 5.5, 1, 5.5, 180, new ItemStack(TCRItems.ASHEN_CRESCENT.get()));
        world.modifyEntityMovement(attacker, true);
        //TODO 文本1
        builder.overlay().showText(50)
                .text("通过方向键使用不同技能")
                .pointAt(util.vector().centerOf(5, 2, 5))
                .placeNearTarget()
                .attachKeyFrame();
        builder.idle(40);

        //TODO 文本2:前进技能
        builder.overlay().showText(50)
                .text("前进键 + 技能: 二连挑击")
                .pointAt(util.vector().centerOf(5, 0, 5))
                .placeNearTarget()
                .attachKeyFrame();
        //待机 15tick
        builder.idle(15);
        world.playAnimation(attacker, FalchionAnimations.FALCHION_FORWARD, 0.0F);
        //等待到Inaction后
        world.waitForInaction(attacker);
        //待机 10tick
        builder.idle(10);
        //归位
        world.setPosition(attacker, 5.5, 1, 5.5);
        //待机 10tick
        builder.idle(10);

        //TODO 文本3:方向键左右技能
        builder.overlay().showText(50)
                .text("左右方向键 + 技能: 全程霸体的快速斩击")
                .pointAt(util.vector().centerOf(5, 0, 5))
                .placeNearTarget()
                .attachKeyFrame();
        //待机 15tick
        builder.idle(15);
        world.playAnimation(attacker, FalchionAnimations.FALCHION_SIDE, 0.0F);
        //等待到Inaction后
        world.waitForInaction(attacker);
        //待机 10tick
        builder.idle(10);
        //归位
        world.setPosition(attacker, 5.5, 1, 5.5);
        //待机 10tick
        builder.idle(10);

        //TODO 文本4:后退技能
        builder.overlay().showText(50)
                .text("后退键 + 技能: 可击倒目标的横扫劈砍连招")
                .pointAt(util.vector().centerOf(5, 0, 5))
                .placeNearTarget()
                .attachKeyFrame();
        //待机 15tick
        builder.idle(15);
        world.playAnimation(attacker, FalchionAnimations.FALCHION_BACKWARD, 0.0F);
        //等待到Inaction后
        world.waitForInaction(attacker);
        //待机 10tick
        builder.idle(10);
        //归位
        world.setPosition(attacker, 5.5, 1, 5.5);

        builder.idle(20);
        builder.markAsFinished();
    }
}

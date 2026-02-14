package com.p1nero.tcrcore.dialog.extension;

import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.p1nero.dialog_lib.api.component.DialogNode;
import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.entity.EntityDialogueExtension;
import com.p1nero.dialog_lib.api.entity.IEntityDialogueExtension;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@EntityDialogueExtension(modId = TCRCoreMod.MOD_ID)
public class BoneChimeraDialogExtension implements IEntityDialogueExtension<Bone_Chimera_Entity> {

    @Override
    public EntityType<Bone_Chimera_Entity> getEntityType() {
        return ModEntities.BONE_CHIMERA.get();
    }

    /**
     * 在追踪或完成过才能对话
     */
    @Override
    public boolean canInteractWith(Player player, Bone_Chimera_Entity boneChimeraEntity) {
        return !boneChimeraEntity.getPersistentData().getBoolean("fighting")
                && (TCRQuestManager.hasQuest(player, TCRQuests.BONE_CHIMERA_QUEST) || TCRQuestManager.hasFinished(player, TCRQuests.BONE_CHIMERA_QUEST));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public DialogueScreen getDialogScreen(StreamDialogueScreenBuilder streamDialogueScreenBuilder, LocalPlayer localPlayer, Bone_Chimera_Entity boneChimeraEntity, CompoundTag compoundTag) {
        DialogueComponentBuilder builder = new DialogueComponentBuilder(boneChimeraEntity, TCRCoreMod.MOD_ID);
        DialogNode root = new DialogNode(builder.ans(0), builder.opt(-1));

        DialogNode whyLock = new DialogNode(builder.ans(1), builder.opt(0))
                .addChild(root);

        DialogNode fight = new DialogNode(builder.ans(2), builder.opt(1))
                .addLeaf(builder.opt(2), 1)
                .addLeaf(builder.opt(3));

        root.addChild(whyLock)
                .addChild(fight);

        return streamDialogueScreenBuilder.buildWith(root);
    }

    @Override
    public void handleNpcInteraction(Bone_Chimera_Entity boneChimeraEntity, ServerPlayer serverPlayer, int i) {
        //开打
        if(i == 1) {
            boneChimeraEntity.getPersistentData().putBoolean("fighting", true);
            TCRQuests.BONE_CHIMERA_QUEST.finish(serverPlayer, true);
        }
        removeConservingPlayer(boneChimeraEntity);
    }
}

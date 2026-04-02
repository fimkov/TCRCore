package com.p1nero.tcrcore.datagen;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.yesman.epicskills.common.data.SkillTreeProvider;
import com.yesman.epicskills.skilltree.SkillTree;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.merlin204.efsiss.skill.EFSISSSkills;
import yesman.epicfight.api.utils.math.Vec2i;
import yesman.epicfight.skill.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TCRSkillTreeProvider extends SkillTreeProvider {

    public static final ResourceKey<SkillTree> MAGIC = ResourceKey.create(SkillTree.SKILL_TREE_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "magic"));

    public TCRSkillTreeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildSkillTreePages(Consumer<SkillTreePageBuilder> writer) {
        int centerX = 160;
        int centerY = 150;
        int radius = 100;
        int skillCount = 8;
        double angleStep = 2 * Math.PI / skillCount;

        Skill[] skills = {
                EFSISSSkills.BLOOD_INTO_MANA,
                EFSISSSkills.MAGIC_SWORD,
                EFSISSSkills.RAPID_CHANT,
                EFSISSSkills.BREATHE_AGAIN,
                EFSISSSkills.AGAINST_MAGIC,
                EFSISSSkills.RESERVE_MANA,
                EFSISSSkills.HASTY_CASTING,
                EFSISSSkills.MANA_SHIELD
        };

        SkillTreePageBuilder pageBuilder = newPage(TCRCoreMod.MOD_ID, "magic")
                .menuBarColor(37, 27, 18)
                .priority(999)
                .hiddenWhenLocked(true)
                .setLocked(null);

        // 存储每个技能的位置，用于后续计算中间点
        List<Vec2i> skillPositions = new ArrayList<>();

        for (int i = 0; i < skills.length; i++) {
            double angle = i * angleStep;
            int x = (int) Math.round(centerX + radius * Math.cos(angle));
            int y = (int) Math.round(centerY + radius * Math.sin(angle));
            skillPositions.add(new Vec2i(x, y));
            pageBuilder.newNode(skills[i])
                    .position(x, y)
                    .abilityPointsRequirement(3)
                    .done();
        }

        SkillTreePageBuilder.SkillTreeNodeBuilder nodeBuilder = pageBuilder.newNode(TCRSkills.REGEN_MANA)
                .position(centerX, centerY)
                .abilityPointsRequirement(5);

        // 为每个外围技能添加父节点，转折点设置在中心点与技能点的中点
        for (int i = 0; i < skills.length; i++) {
            Vec2i pos = skillPositions.get(i);
            int midX = (centerX + pos.x) / 2;
            int midY = (centerY + pos.y) / 2;
            nodeBuilder.addParent(skills[i], new Vec2i(midX, midY));
        }
        nodeBuilder.done();

        writer.accept(pageBuilder);
    }
}
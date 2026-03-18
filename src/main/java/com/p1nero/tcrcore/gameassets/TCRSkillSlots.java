package com.p1nero.tcrcore.gameassets;

import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillCategory;
import yesman.epicfight.skill.SkillSlot;

public enum TCRSkillSlots implements SkillSlot {
    PASSIVE4(SkillCategories.PASSIVE),
    PASSIVE5(SkillCategories.PASSIVE),
    PASSIVE6(SkillCategories.PASSIVE);
    final SkillCategory category;
    final int id;

    TCRSkillSlots(SkillCategories category){
        this.category = category;
        this.id = SkillSlot.ENUM_MANAGER.assign(this);
    }


    @Override
    public SkillCategory category() {
        return category;
    }

    @Override
    public int universalOrdinal() {
        return id;
    }
}

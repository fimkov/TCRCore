package com.p1nero.tcrcore.gameassets;

import com.hm.efn.skill.arts.AvatarSkill;
import com.p1nero.dpr.skill.ParryAndDodgeRewardSkill;
import com.p1nero.p1nero_ec.skills.PECWeaponInnateSkillBase;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.gameassets.skill.RegenManaSkill;
import com.p1nero.tcrcore.gameassets.skill.TheIncineratorInnateSkill;
import com.p1nero.tcrcore.skills.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.merlin204.efsiss.EFSISSMod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.passive.PassiveSkill;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCRSkills {

    public static Skill WATER_AVOID;
    public static Skill FIRE_AVOID;
    public static Skill THE_INCINERATOR_INNATE;
    public static Skill PERFECT_DODGE;//印度闪避
    public static Skill REGEN_MANA;//闪避招架回蓝

    @SubscribeEvent
    public static void buildSkills(SkillBuildEvent event) {
        SkillBuildEvent.ModRegistryWorker registryWorker = event.createRegistryWorker(TCRCoreMod.MOD_ID);
        WATER_AVOID = registryWorker.build("water_avoid", SimpleSkill::new, Skill.createBuilder().setCategory(SkillCategories.PASSIVE).setResource(Skill.Resource.NONE));
        FIRE_AVOID = registryWorker.build("fire_avoid", FireAvoidSkill::new, Skill.createBuilder().setCategory(SkillCategories.PASSIVE).setResource(Skill.Resource.NONE));
        THE_INCINERATOR_INNATE = registryWorker.build("the_incinerator_innate", TheIncineratorInnateSkill::new, PECWeaponInnateSkillBase.createBuilder());
        PERFECT_DODGE = registryWorker.build("perfect_dodge", AvatarSkill::new, PassiveSkill.createPassiveBuilder());
        REGEN_MANA = registryWorker.build("regen_mana", RegenManaSkill::new, ParryAndDodgeRewardSkill.createParryRewardSkill().setUuid(UUID.fromString("fdc09ee8-fcfc-19eb-9a03-0242ac224508")).setsKillTexture(ResourceLocation.fromNamespaceAndPath(EFSISSMod.MOD_ID, "textures/gui/skills/identity/connect_root.png")));
    }
}

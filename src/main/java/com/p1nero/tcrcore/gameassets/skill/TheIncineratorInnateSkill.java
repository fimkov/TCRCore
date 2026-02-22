package com.p1nero.tcrcore.gameassets.skill;

import com.p1nero.p1nero_ec.capability.PECPlayer;
import com.p1nero.p1nero_ec.client.KeyMappings;
import com.p1nero.p1nero_ec.effect.PECEffects;
import com.p1nero.p1nero_ec.gameassets.PECAnimations;
import com.p1nero.p1nero_ec.skills.PECWeaponInnateSkillBase;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.gameassets.TCRAnimations;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.client.animation.property.TrailInfo;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.EntityDecorations;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.List;

public class TheIncineratorInnateSkill extends PECWeaponInnateSkillBase {

    public TheIncineratorInnateSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    protected void tryExecuteSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if (PECPlayer.consumeSkillPoint(serverPlayerPatch.getOriginal(), 5)) {
            this.executeSkill1(serverPlayerPatch, container);
        } else {
            this.onSkillPointNotEnough(container, 5);
        }
    }

    @Override
    public void executeSkill1(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(TCRAnimations.SOLAR_BRASERO_OBSCURIDAD, 0.15F);
    }

    @Override
    protected void tryExecuteSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        if(serverPlayerPatch.getTarget() == null) {
            serverPlayerPatch.getOriginal().displayClientMessage(Component.translatable("info.p1nero_ec.need_target"), true);
            return;
        }
        super.tryExecuteSkill2(serverPlayerPatch, container);
    }

    @Override
    public void executeSkill2(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.THE_INCINERATOR_SKILL2, 0.15F);
    }

    @Override
    public void executeSkill3(ServerPlayerPatch serverPlayerPatch, SkillContainer container) {
        serverPlayerPatch.playAnimationSynchronized(PECAnimations.THE_INCINERATOR_SKILL3, 0.15F);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    protected List<KeyMapping> getAvailableKeys() {
        return List.of(KeyMappings.SKILL_1, KeyMappings.SKILL_2, KeyMappings.SKILL_3);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onInitiateClient(SkillContainer container) {
        container.getExecutor().getEntityDecorations().addTrailInfoModifier(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID,"modify_incinerator_trail"), new EntityDecorations.AnimationPropertyModifier<>() {
            @Override
            public TrailInfo getModifiedValue(TrailInfo original, CapabilityItem object) {
                if (container.getExecutor().getOriginal().hasEffect(PECEffects.SOUL_INCINERATOR.get())) {
                    TrailInfo.Builder builder = original.unpackAsBuilder();
                    builder.texture(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/trail/yz2.png"));
                    return builder.create();
                }
                return original;
            }

            @Override
            public boolean shouldRemove() {
                return container.getExecutor().getSkill(TheIncineratorInnateSkill.this) == null;
            }
        });
    }
}
package com.p1nero.tcrcore.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.UUID;

public class NetherMagicSwordItem extends SwordItem {

    private final float attackDamage;

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    protected static final UUID BASE_MAX_MANA_UUID = UUID.fromString("FA114E1C-4180-4865-B01B-BCCE9785ACA3");
    protected static final UUID BASE_MAGIC_BOOST_UUID = UUID.fromString("FA114E1C-4180-4865-B01B-BCCE9785ACA3");

    public NetherMagicSwordItem(Tier tier, int damage, float atkSpeed, Properties properties) {
        super(tier, damage, atkSpeed, properties);
        this.attackDamage = -1;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EpicFightAttributes.IMPACT.get(), new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", -0.5, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -4, AttributeModifier.Operation.ADDITION));
        builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(BASE_MAX_MANA_UUID, "Weapon modifier", 50, AttributeModifier.Operation.ADDITION));
        builder.put(AttributeRegistry.FIRE_SPELL_POWER.get(), new AttributeModifier(BASE_MAGIC_BOOST_UUID, "Weapon modifier", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(AttributeRegistry.BLOOD_SPELL_POWER.get(), new AttributeModifier(BASE_MAGIC_BOOST_UUID, "Weapon modifier", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        this.defaultModifiers = builder.build();
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

}

package com.p1nero.tcrcore.entity;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.entity.custom.CustomColorItemEntity;
import com.p1nero.tcrcore.entity.custom.aine_iris.AineIrisEntity;
import com.p1nero.tcrcore.entity.custom.ferry_girl.FerryGirlEntity;
import com.p1nero.tcrcore.entity.custom.chronos_sol.ChronosSolEntity;
import com.p1nero.tcrcore.entity.custom.ornn.OrnnEntity;
import com.p1nero.tcrcore.entity.custom.tutorial_golem.TutorialGolem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.mob.IronGolemPatch;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TCREntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TCRCoreMod.MOD_ID);

    public static final RegistryObject<EntityType<CustomColorItemEntity>> CUSTOM_COLOR_ITEM = register("custom_color_item",
            EntityType.Builder.<CustomColorItemEntity>of(CustomColorItemEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(6).updateInterval(20));

    public static final RegistryObject<EntityType<ChronosSolEntity>> CHRONOS_SOL = register("chronos_sol",
            EntityType.Builder.of(ChronosSolEntity::new, MobCategory.CREATURE).sized(0.8f, 3.0F).fireImmune());

    public static final RegistryObject<EntityType<FerryGirlEntity>> FERRY_GIRL = register("ferry_girl",
            EntityType.Builder.of(FerryGirlEntity::new, MobCategory.CREATURE).sized(0.6f, 1.9f).fireImmune());

    public static final RegistryObject<EntityType<AineIrisEntity>> AINE = register("aine",
            EntityType.Builder.of(AineIrisEntity::new, MobCategory.CREATURE).sized(0.8f, 3.0F).fireImmune());

    public static final RegistryObject<EntityType<OrnnEntity>> ORNN = register("ornn",
            EntityType.Builder.of(OrnnEntity::new, MobCategory.CREATURE).sized(4f, 4f).fireImmune());

    public static final RegistryObject<EntityType<TutorialGolem>> TUTORIAL_GOLEM = register("tutorial_golem",
            EntityType.Builder.of(TutorialGolem::new, MobCategory.CREATURE).sized(1.4F, 2.7f).fireImmune());

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(name, () -> entityTypeBuilder.build(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, name).toString()));
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(CHRONOS_SOL.get(), ChronosSolEntity.setAttributes());
        event.put(FERRY_GIRL.get(), ChronosSolEntity.setAttributes());
        event.put(ORNN.get(), ChronosSolEntity.setAttributes());
        event.put(AINE.get(), ChronosSolEntity.setAttributes());
        event.put(TUTORIAL_GOLEM.get(), TutorialGolem.setAttributes());

    }

    @SubscribeEvent
    public static void setPatch(EntityPatchRegistryEvent event) {
        //BOSS
        event.getTypeEntry().put(TUTORIAL_GOLEM.get(), (entity) -> IronGolemPatch::new);
    }

    @SubscribeEvent
    public static void setArmature(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Armatures.registerEntityTypeArmature(TUTORIAL_GOLEM.get(), Armatures.IRON_GOLEM);
        });
    }

}

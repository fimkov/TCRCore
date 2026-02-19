package com.p1nero.tcrcore.events;

import com.brass_amber.ba_bt.entity.hostile.golem.CoreGolem;
import com.brass_amber.ba_bt.entity.hostile.golem.LandGolem;
import com.brass_amber.ba_bt.entity.hostile.golem.NetherGolem;
import com.brass_amber.ba_bt.entity.hostile.golem.OceanGolem;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ender_Guardian_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.Ignis_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Harbinger_Entity;
import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.The_Leviathan_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Ancient_Remnant.Ancient_Remnant_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Maledictus.Maledictus_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.NewNetherite_Monstrosity.Netherite_Monstrosity_Entity;
import com.github.L_Ender.cataclysm.entity.InternalAnimationMonster.IABossMonsters.Scylla.Scylla_Entity;
import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.dodo.dodosmobs.entity.InternalAnimationMonster.IABossMonsters.Bone_Chimera_Entity;
import com.hm.efn.registries.EFNItem;
import com.merlin204.sg.item.SGItems;
import com.obscuria.aquamirae.Aquamirae;
import com.obscuria.aquamirae.AquamiraeUtils;
import com.obscuria.aquamirae.common.entities.CaptainCornelia;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import com.p1nero.cataclysm_dimension.worldgen.CataclysmDimensions;
import com.p1nero.entityrespawner.EntityRespawnerMod;
import com.p1nero.entityrespawner.entity.SoulEntity;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import com.p1nero.tcrcore.client.sound.CorneliaMusicPlayer;
import com.p1nero.tcrcore.client.sound.WraithonMusicPlayer;
import com.p1nero.tcrcore.gameassets.TCRSkills;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.save_data.TCRDimSaveData;
import com.p1nero.tcrcore.utils.EntityUtil;
import com.p1nero.tcrcore.utils.ItemUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import com.p1nero.tcrcore.worldgen.TCRDimensions;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import net.kenddie.fantasyarmor.item.FAItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.p1nero.ss.item.SwordSoaringItems;
import net.shelmarow.nightfall_invade.entity.spear_knight.Arterius;
import net.sonmok14.fromtheshadows.server.entity.mob.BulldrogiothEntity;
import org.merlin204.wraithon.entity.wraithon.WraithonEntity;
import org.merlin204.wraithon.worldgen.WraithonDimensions;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TCRCoreMod.MOD_ID)
public class LivingEntityEventListeners {

    public static final String TRIGGERED = "death_triggered";

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        Entity attacker = event.getSource().getEntity();
        //禁用原版攻击
        if(attacker instanceof Player player && player.level().isClientSide) {
            if(player.isLocalPlayer()) {
                EpicFightCapabilities.getUnparameterizedEntityPatch(player, PlayerPatch.class).ifPresent(playerPatch -> {
                    if (playerPatch.isVanillaMode()) {
                        event.setCanceled(true);
                        player.displayClientMessage(TCRCoreMod.getInfo("press_to_open_battle_mode", EpicFightKeyMappings.SWITCH_MODE.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.RED), true);
                    }
                });
            }
        }
        //骑士剑剑气
        if (attacker instanceof ServerPlayer serverPlayer) {
            EpicFightCapabilities.getUnparameterizedEntityPatch(serverPlayer, PlayerPatch.class).ifPresent(playerPatch -> {
                if (playerPatch.isVanillaMode()) {
                    event.setCanceled(true);
                }
            });

        }

        //重生保护
        if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (boneChimeraEntity.isDeadOrDying()) {
                event.setCanceled(true);
            }
        }

        if (event.getEntity() instanceof ServerPlayer serverPlayer) {

            //被打死重置状态
            if(event.getSource().getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
                boneChimeraEntity.getPersistentData().putBoolean("fighting", false);
            }

            EpicFightCapabilities.getUnparameterizedEntityPatch(serverPlayer, ServerPlayerPatch.class).ifPresent(serverPlayerPatch -> {
                AnimationPlayer player = serverPlayerPatch.getAnimator().getPlayerFor(null);
                //激流期间无敌
                if (player != null && player.getAnimation() == Animations.TSUNAMI_REINFORCED) {
                    event.setCanceled(true);
                }
            });

        }

    }

    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide) {

            if (entity instanceof IronGolem ironGolem && WorldUtil.isInStructure(ironGolem, WorldUtil.SKY_ISLAND)) {
                event.setCanceled(true);
            }

            if (entity instanceof Pillager) {
                if (entity.getRandom().nextFloat() < 0.2F) {
                    ItemUtil.addItemEntity(entity, Items.GOLD_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtil.addItemEntity(entity, Items.DIAMOND, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtil.addItemEntity(entity, Items.NETHERITE_INGOT, 1, 0xc000ff);
                }
                if (!event.getEntity().getLootTable().toString().endsWith("captain") && entity.hasGlowingTag()) {
                    ItemUtil.addItemEntity(entity, AquamiraeItems.SHELL_HORN.get(), 1, ChatFormatting.GOLD.getColor());
                }
            } else if (entity instanceof Enemy) {
                if (entity.getRandom().nextFloat() < 0.1F) {
                    ItemUtil.addItemEntity(entity, Items.IRON_INGOT, 1, 0xc000ff);
                }
                if (entity.getRandom().nextFloat() < 0.03F) {
                    ItemUtil.addItemEntity(entity, Items.AMETHYST_SHARD, 1, 0xc000ff);
                } else if (entity.getRandom().nextFloat() < 0.01F) {
                    ItemUtil.addItemEntity(entity, FAItems.MOON_CRYSTAL.get(), 1, 0xc000ff);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (event.isCanceled() || livingEntity.getPersistentData().getBoolean(TRIGGERED)) {
            return;
        }
        livingEntity.getPersistentData().putBoolean(TRIGGERED, true);
        Vec3 center = livingEntity.position();
        //附近玩家都获得
        livingEntity.level().getEntitiesOfClass(ServerPlayer.class, (new AABB(center, center)).inflate(30)).forEach(player -> {

            //击败祭坛内的boss
            if (livingEntity instanceof Scylla_Entity) {
                if(!PlayerDataManager.stormEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.stormEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof Ignis_Entity) {
                if(!PlayerDataManager.flameEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.flameEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof The_Leviathan_Entity) {
                if(!PlayerDataManager.abyssEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.abyssEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof Maledictus_Entity) {
                if(!PlayerDataManager.cursedEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.cursedEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof Ancient_Remnant_Entity) {
                if(!PlayerDataManager.desertEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.desertEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof Ender_Guardian_Entity) {
                if(!PlayerDataManager.voidEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.voidEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof Netherite_Monstrosity_Entity) {
                if(!PlayerDataManager.monstEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.monstEyeKilled.put(player, true);
                }
            }

            if (livingEntity instanceof The_Harbinger_Entity) {
                if(!PlayerDataManager.mechEyeKilled.get(player)) {
                    ItemUtil.addItemEntity(player, EpicSkillsItems.ABILIITY_STONE.get(), 5, ChatFormatting.GOLD.getColor());
                    PlayerDataManager.mechEyeKilled.put(player, true);
                }
            }

            //处理主线的boss掉落眼睛
            if (livingEntity instanceof LandGolem) {
                //捡起来之前都会掉
                if (!PlayerDataManager.desertEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.DESERT_EYE.get(), 1, ChatFormatting.YELLOW.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

            if (livingEntity instanceof OceanGolem) {
                if (!PlayerDataManager.abyssEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.ABYSS_EYE.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                    ItemUtil.addItemEntity(player, AquamiraeItems.SHIP_GRAVEYARD_ECHO.get(), 1, ChatFormatting.BLUE.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

            if (livingEntity instanceof CaptainCornelia) {
                if (!PlayerDataManager.cursedEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.CURSED_EYE.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                    ItemUtil.addItemEntity(player, TCRItems.NECROMANCY_SCROLL.get(), 1, ChatFormatting.DARK_GREEN.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

            if (livingEntity instanceof CoreGolem) {
                if (!PlayerDataManager.flameEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.FLAME_EYE.get(), 1, ChatFormatting.RED.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

            if (livingEntity instanceof NetherGolem) {
                if (!PlayerDataManager.monstEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.MONSTROUS_EYE.get(), 1, ChatFormatting.DARK_RED.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

            if (livingEntity instanceof WitherBoss) {
                if (!PlayerDataManager.mechEyeGotten.get(player)) {
                    ItemUtil.addItemEntity(player, ModItems.MECH_EYE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                    ItemUtil.addItemEntity(player, TCRItems.WITHER_SOUL_STONE.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                    player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundSource.PLAYERS, player.getX(), player.getY(), player.getZ(), 1.0F, 1.0F, player.getRandom().nextInt()));
                }
            }

        });

        if (livingEntity.level() instanceof ServerLevel serverLevel) {

            if (CataclysmDimensions.LEVELS.contains(serverLevel.dimension()) && livingEntity.getType().is(Tags.EntityTypes.BOSSES)) {
                TCRDimSaveData.get(serverLevel).setBossKilled(true);
                //发送回城按钮
                MutableComponent clickToReturn = TCRCoreMod.getInfo("click_to_return");
                clickToReturn.setStyle(Style.EMPTY
                        .applyFormat(ChatFormatting.GREEN)
                        .withBold(true)
                        .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/execute in " + TCRDimensions.SANCTUM_LEVEL_KEY.location() + " as @s run tp " + WorldUtil.START_POS.getX() + " " + WorldUtil.START_POS.getY() + " " + WorldUtil.START_POS.getZ()))
                );

                serverLevel.players().forEach((serverPlayer -> {
                    serverPlayer.displayClientMessage(TCRCoreMod.getInfo("boss_killed_ready_return"), false);
                    serverPlayer.displayClientMessage(clickToReturn, false);
                }));

            }

            //打似战灵处理通关
            if (livingEntity instanceof WraithonEntity wraithonEntity && !wraithonEntity.isDead()) {
                serverLevel.players().forEach(serverPlayer -> {
                    TCRCapabilityProvider.getTCRPlayer(serverPlayer).setTickAfterBossDieLeft(600);
                });
            }

            if (livingEntity instanceof IronGolem ironGolem && WorldUtil.isInStructure(livingEntity, WorldUtil.SKY_ISLAND) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                //秽土转生
                EntityRespawnerMod.addToRespawn(ironGolem, 60, true);
                ItemUtil.addItemEntity(livingEntity, SGItems.GOLEM_HEART.get(), 1, ChatFormatting.GOLD.getColor().intValue());
                livingEntity.getPersistentData().putBoolean("already_respawn", true);
            }

            if (livingEntity instanceof Bone_Chimera_Entity boneChimeraEntity && WorldUtil.isInStructure(livingEntity, WorldUtil.BONE_CHIMERA_STRUCTURE) && !livingEntity.getPersistentData().getBoolean("already_respawn")) {
                //偷懒，直接秽土转生
                SoulEntity soulEntity = EntityRespawnerMod.addToRespawn(boneChimeraEntity, 200, true);
                if(boneChimeraEntity.getPersistentData().contains("spawnX") && soulEntity != null) {
                    soulEntity.setPos(readSpawnPos(boneChimeraEntity));
                }
                livingEntity.getPersistentData().putBoolean("already_respawn", true);

                //掉百兵图
                ItemUtil.addItemEntity(livingEntity, TCRItems.MYSTERIOUS_WEAPONS.get(), 1, ChatFormatting.GOLD.getColor());
            }

            //彩蛋物品
            if (livingEntity instanceof Maledictus_Entity) {
                ItemUtil.addItemEntity(livingEntity, TCRItems.DUAL_BOKKEN.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor());
            }

        }

        if (livingEntity instanceof CaptainCornelia) {
            if (livingEntity.level().isClientSide) {
                //修它bgm播放bug，不知道现在修了没
                CorneliaMusicPlayer.stopBossMusic(livingEntity);
            } else {
                ItemUtil.addItemEntity(livingEntity, EFNItem.DEEPDARK_HEART.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor().intValue());
            }
        }

        if (livingEntity instanceof WraithonEntity wraithonEntity && !wraithonEntity.isDead()) {
            if (livingEntity.level().isClientSide) {
                WraithonMusicPlayer.stopBossMusic(livingEntity);
            } else {
                ItemUtil.addItemEntity(livingEntity, SwordSoaringItems.VATANSEVER.get(), 1, ChatFormatting.LIGHT_PURPLE.getColor().intValue());
                ServerLevel wraithonLevel = wraithonEntity.getServer().getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
                TCRDimSaveData.get(wraithonLevel).setBossSummoned(false);
            }
        }

        //似了换地狱傀儡出来
        if(livingEntity instanceof EnderDragon enderDragon) {
            enderDragon.discard();
            //TODO 生成终末傀儡以及出场动画
        }

        if (livingEntity instanceof ServerPlayer serverPlayer && !event.isCanceled()) {
            serverPlayer.displayClientMessage(TCRCoreMod.getInfo("death_info"), false);
            ServerLevel wraithonLevel = serverPlayer.server.getLevel(WraithonDimensions.SANCTUM_OF_THE_WRAITHON_LEVEL_KEY);
            if (wraithonLevel.players().isEmpty()) {
                wraithonLevel.getAllEntities().forEach(Entity::discard);
                TCRDimSaveData.get(wraithonLevel).setBossSummoned(false);
            }

            //防堆命机制
            if (event.getSource().getEntity() instanceof LivingEntity living) {
                if (living instanceof WraithonEntity wraithonEntity) {
                    if (!EntityUtil.getNearByPlayers(serverPlayer, 30).isEmpty()) {
                        return;
                    }
                    wraithonEntity.setPhase(WraithonEntity.START_PHASE);
                }
                living.setHealth(living.getMaxHealth());
                living.removeAllEffects();
                if (living instanceof Arterius arterius) {
                    arterius.resetBossStatus(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (TCRCoreMod.hasCheatMod()) {
            event.getEntity().setHealth(0);
        }
    }

    /**
     * 有避水咒就减少呼吸消耗
     */
    @SubscribeEvent
    public static void onLivingBreath(LivingBreatheEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            if (EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class).getSkillCapability().isEquipping(TCRSkills.WATER_AVOID)) {
                event.setCanBreathe(true);
                event.setCanRefillAir(true);
                event.setConsumeAirAmount(0);
                event.setRefillAirAmount(5);
            }
        }
    }

    /**
     * 出生地防刷怪
     */
    @SubscribeEvent
    public static void onLivingSpawn(MobSpawnEvent.PositionCheck event) {
        if (event.getLevel().getLevel().dimension() == TCRDimensions.SANCTUM_LEVEL_KEY && event.getEntity() instanceof Enemy) {
            event.setResult(Event.Result.DENY);
        }
    }

    public static Set<EntityType<?>> illegalEntityTypes = new HashSet<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMobSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (AquamiraeUtils.isInIceMaze(event.getEntity())) {
            if (event.getEntity() instanceof Pillager pillager && event.getEntity().getRandom().nextFloat() < 0.2) {
                pillager.getPersistentData().putString("DeathLootTable", Aquamirae.MODID + ":entities/maze_captain");
                pillager.setGlowingTag(true);
            }
            if (event.getEntity().getLootTable().toString().endsWith("captain")) {
                event.getEntity().setGlowingTag(true);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingJoin(EntityJoinLevelEvent event) {

        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof Arterius arterius) {
            arterius.setInBattle(false);
        }

        ServerLevel serverLevel = (ServerLevel) event.getEntity().level();

        if (event.getEntity() instanceof BulldrogiothEntity bulldrogiothEntity) {
            if (WorldUtil.isInStructure(bulldrogiothEntity, WorldUtil.RIBBIT_VILLAGE)) {
                bulldrogiothEntity.setGlowingTag(true);
                saveSpawnPos(bulldrogiothEntity);
            }
        }

        if (event.getEntity() instanceof Bone_Chimera_Entity boneChimeraEntity) {
            if (WorldUtil.isInStructure(boneChimeraEntity, WorldUtil.BONE_CHIMERA_STRUCTURE)) {
                saveSpawnPos(boneChimeraEntity);
            }
        }

        //交给incontrol
//        if (illegalEntityTypes.contains(event.getEntity().getType())) {
//            event.setCanceled(true);
//            return;
//        }

        //移除远古守卫者在海洋塔的生成
        if(event.getEntity() instanceof Guardian guardian && WorldUtil.isInStructure(guardian, WorldUtil.OCEAN_GOLEM)) {
            event.setCanceled(true);
        }

        UUID uuid = UUID.fromString("d4c3b2a1-f6e5-8b7a-0d9c-cba987654321");
        if (event.getEntity() instanceof IronGolem ironGolem) {
            if (WorldUtil.isInStructure(ironGolem, WorldUtil.SKY_ISLAND)) {
                ironGolem.setCustomName(TCRCoreMod.getInfo("iron_golem_name"));
                ironGolem.setCustomNameVisible(true);
                ironGolem.setGlowingTag(true);
            }
        }

        //末影龙血少，走个过场，似了以后换末地傀儡
        if (event.getEntity() instanceof EnderDragon enderDragon) {
            enderDragon.getAttribute(Attributes.MAX_HEALTH).removeModifier(uuid);
            AttributeModifier healthBoost = new AttributeModifier(uuid, "Dragon Health Boost", -0.6, AttributeModifier.Operation.MULTIPLY_BASE);
            enderDragon.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthBoost);
            enderDragon.setHealth(enderDragon.getMaxHealth());
        }

        //血给多点，假装很强大
        if (event.getEntity() instanceof WitherBoss witherBoss) {
            witherBoss.getAttribute(Attributes.MAX_HEALTH).removeModifier(uuid);
            AttributeModifier healthBoost = new AttributeModifier(uuid, "Wither Health Boost", 1, AttributeModifier.Operation.MULTIPLY_BASE);
            witherBoss.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(healthBoost);
            witherBoss.setHealth(witherBoss.getMaxHealth());
        }

    }

    public static void saveSpawnPos(Entity entity) {
        if(!entity.getPersistentData().contains("spawnX")) {
            entity.getPersistentData().putDouble("spawnX", entity.getX());
            entity.getPersistentData().putDouble("spawnY", entity.getY());
            entity.getPersistentData().putDouble("spawnZ", entity.getZ());
        }
    }

    public static Vec3 readSpawnPos(Entity entity) {
        return new Vec3(entity.getPersistentData().getDouble("spawnX"),
                entity.getPersistentData().getDouble("spawnY"),
                entity.getPersistentData().getDouble("spawnZ"));
    }

}
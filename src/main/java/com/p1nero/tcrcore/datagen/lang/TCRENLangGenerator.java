package com.p1nero.tcrcore.datagen.lang;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.hm.efn.registries.EFNItem;
import com.p1nero.p1nero_ec.effect.PECEffects;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.FirstEnterCloudlandScreenHandler;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.ResetGameProgressScreenHandler;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.StartScreenHandler;
import com.p1nero.tcrcore.effect.TCREffects;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.worldgen.TCRBiomes;
import com.yesman.epicskills.registry.entry.EpicSkillsItems;
import com.yungnickyoung.minecraft.ribbits.module.EntityTypeModule;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.sonmok14.fromtheshadows.server.utils.registry.ItemRegistry;

public class TCRENLangGenerator extends TCRLangProvider {
    public TCRENLangGenerator(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    protected void addTranslations() {

        this.add("unlock_tip.epicskills.acrobat.spider_techniques", " Slaught 50 Spider");
        this.add("unlock_tip.epicskills.acrobat.natural_sprinter", " Slaught 2 Lycanth and sprint 10 km");
        this.add("unlock_tip.epicskills.acrobat.aqua_maneuvre", " Slaught 3 Elder Guadian and swim 3 km");

        this.add("unlock_tip.epicskills.prodigy.shooting_style", " Slaught 2 Enderman");
        this.add("unlock_tip.epicskills.prodigy.punishment_kick", " Slaught 25 Enderman");

        this.add("unlock_tip.epicskills.prodigy.arrow_tenacity", " Slaught 5 skeleton");
        this.add("unlock_tip.epicskills.prodigy.dodge_master", " Slaught 50 skeleton");

        this.add("unlock_tip.epicskills.prodigy.ender_step", " Slaught 50 Enderman");
        this.add("unlock_tip.epicskills.prodigy.ender_obscuris", " Slaught 250 Enderman");
        this.add("unlock_tip.epicskills.prodigy.time_travel", " Slaught 1000 Enderman");

        this.add("unlock_tip.epicskills.prodigy.buster_parade", " Slaught 10 Hollow");
        this.add("unlock_tip.epicskills.prodigy.perfect_bulwark", " Slaught 50 guardian");
        this.add("unlock_tip.epicskills.prodigy.avatar_of_might", " Slaught 50 Hollow");

        this.add("unlock_tip.epicskills.prodigy.precise_roll", " Sprint 4 km");
        this.add("unlock_tip.epicskills.prodigy.bull_charge", " Slaught 20 Ravager");

        this.add("unlock_tip.epicskills.prodigy.shulker_cloak", " Slaught 36 shulker");
        this.add("unlock_tip.epicskills.prodigy.soul_protection", " Slaught 60 Wither Skeleton");
        this.add("unlock_tip.epicskills.prodigy.vampirize", " Slaught 20 Evil Skeleton");
        this.add("unlock_tip.epicskills.prodigy.dopamine", " Slaught 27 Lycanth");
        this.add("unlock_tip.epicskills.prodigy.lunatic_vivacity", " Slaught 9 Ghast");
        this.add("unlock_tip.epicskills.prodigy.voodoo_magic", " Slaught 10 Evoker");
        this.add("unlock_tip.epicskills.prodigy.manipulator", " Slaught 6 Saulomnk");

        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_you", " Slaught 100 Evil Skeleton");
        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_me", " Slaught 100 Saulomnk");

        this.add("unlock_tip.epicskills.prodigy.inner_growth", " Elimate the Ender Dragon");
        this.add("unlock_tip.epicskills.prodigy.shadow_step", " Elimate the Wither");

        this.add("unlock_tip.epicskills.prodigy.critical_knowledge", " Slaught 50 Creeper and 150 mobs");
        this.add("unlock_tip.epicskills.prodigy.dancing_blade", " Slaught 5 Witches and 150 mobs");

        this.add("travelerstitles.tcrcore.sanctum", "Realm of Dreams");
        this.add("travelerstitles.tcrcore.real", "The Unreal Reality");
        this.add("travelerstitles.minecraft.overworld", "Overworld");
        this.add("travelerstitles.aether.the_aether", "The Aether");
        this.add("travelerstitles.minecraft.the_nether", "The Nether");
        this.add("travelerstitles.minecraft.the_nether.color", "750909");
        this.add("travelerstitles.minecraft.the_end", "The End");
        this.add("travelerstitles.minecraft.the_end.color", "4f219e");
        this.add("travelerstitles.pbf1.sanctum_of_the_battle1", "Infinite Samsara");

        this.addBiome(TCRBiomes.AIR, "");
        this.addBiome(TCRBiomes.REAL, "");

        this.addEffect(TCREffects.INVULNERABLE, "Invulnerable");
        this.addEffect(TCREffects.SOUL_INCINERATOR, "Soul Fire");
        this.addEffect(PECEffects.SOUL_INCINERATOR, "Soul Fire");

        this.add("epicfight.skill_slot.passive4", "Passive 4");
        this.add("epicfight.skill_slot.passive5", "Passive 5");

        StartScreenHandler.onGenerateEN(this);
        FirstEnterCloudlandScreenHandler.onGenerateEN(this);
        ResetGameProgressScreenHandler.onGenerateEN(this);

        this.add("item.domesticationinnovation.collar_tag.tcr_info", "It allows for special enchantments to be applied to pets.");
        this.addTCRItemInfo(net.blay09.mods.waystones.item.ModItems.warpStone, "Click the §6[Scroll]§r button in the inventory to teleport to activated waystones!");
        this.addTCRItemInfo(ItemRegistry.BOTTLE_OF_BLOOD.get(), "Brewed using §c[Crystallized Blood Marrow]§r, a drop from §d[Nehemoth]§r");
        this.addTCRItemInfo(EFNItem.DEEPDARK_HEART.get(), "Obtained by defeating the §2[Warden]§r or §2[Captain Cornelia]§r");
        this.addTCRItemInfo(ModItems.CORAL_CHUNK.get(), "Obtained by defeating the §a[Coral Colossus]§r in the §dcloudland of The Leviathan§r");
        this.addTCRItemInfo(com.github.dodo.dodosmobs.init.ModItems.CHIERA_CLAW.get(), "Obtained by defeating the §e[Bone Chimera]§r");
        this.addTCRItemInfo(ModItems.CHITIN_CLAW.get(), "Obtained by defeating the §3[Giant Claw Guard]§r in the §3cloudland of Scylla§r");
        this.addTCRItemInfo(Items.DRAGON_EGG, "Obtained by defeating the §d[Ender Dragon]§r in the §dEnd§r");
        this.addTCRItemInfo(EpicSkillsItems.ABILIITY_STONE.get(), "Right-click to use and gain skill points");

        this.add("itemGroup.tcr.items", "The Casket of Reveries — Core Items");
        this.add("key.categories." + TCRCoreMod.MOD_ID, "The Casket of Reveries — Core");
        this.addKeyMapping(TCRKeyMappings.RIPTIDE, "Riptide");
        this.addKeyMapping(TCRKeyMappings.SHOW_QUESTS, "Show/Hide Task");

        this.add("skill_tree.sword_soaring.unlock_tip", "Unlocked by Advancing the main quest");
        this.add("unlock_tip.tcrcore.battleborn.water_avoid1", "Learned by trading with §6[Ribbit]§f using §d[Block of Amethyst]§f");
        this.add("unlock_tip.tcrcore.battleborn.fire_avoid", "Unlocked by Advancing the main quest");
        this.add("unlock_tip.tcrcore.get_vatansever", "Unlocks after obtaining the §d[Vatansever]§f");
        this.addSkill("water_avoid", "Water Avoidance Charm", "Allows breathing underwater!");
        this.addSkill("fire_avoid", "Fire Avoidance Charm", "Immunity to fire damage!");
        this.addSkill("perfect_dodge", "Dodge Effect", "Play a cool animation when perfect dodge!");

        this.add(TCRItems.ABYSS_FRAGMENT.get(), "Echo of the Abyssal Dirge");
        this.add(TCRItems.DESERT_FRAGMENT.get(), "Echo of Forgotten Sands");
        this.add(TCRItems.ENDER_FRAGMENT.get(), "Shard of the Final Verse");
        this.add(TCRItems.MECH_FRAGMENT.get(), "Remains of the Perpetual Gear");
        this.add(TCRItems.NETHERITE_FRAGMENT.get(), "Embers of the Quenched Molten Heart");
        this.add(TCRItems.FLAME_FRAGMENT.get(), "Echo of the First Flame");
        this.add(TCRItems.STORM_FRAGMENT.get(), "Shard of the Thunderous Roar");
        this.add(TCRItems.SOUL_FRAGMENT.get(), "Dust of the Cursed Bone Prison");
        this.add(TCRItems.STONE_OF_REINCARNATION.get(), "轮回之石");
        this.addItemUsageInfo(TCRItems.STONE_OF_REINCARNATION.get(), "After use, you will return to the beginning of the journey.");
        this.add(TCRItems.WITHER_SOUL_STONE.get(), "Wither Soul Stone");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE.get(), "It seems to have lost its magic for now, just a unique stone. Figure out how to reawaken it!");
        this.add(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Wither Soul Stone");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Can open a gateway to the §6[Infinite Samsara]§r.");
        this.add(TCRItems.MAGIC_BOTTLE.get(), "Magic Bottle");
        this.addItemUsageInfo(TCRItems.MAGIC_BOTTLE.get(), "Restores a certain percentage of mana when used. Once depleted, must be used in the main city to replenish MP.");
        this.add(TCRItems.MYSTERIOUS_WEAPONS.get(), "Mysterious Weapons Scroll");
        this.addItemUsageInfo(TCRItems.MYSTERIOUS_WEAPONS.get(), "It seems to record all manner of weapons from around the world. You should show it to someone who knows about such things.");
        this.add(TCRItems.NECROMANCY_SCROLL.get(), "Necromancy Scroll");
        this.addItemUsageInfo(TCRItems.NECROMANCY_SCROLL.get(), "It seems to hold the secrets of magic within. Show it to someone who understands it.");
        this.add(TCRItems.DRAGON_FLUTE.get(), "Dragon Flute");
        this.addItemUsageInfo(TCRItems.DRAGON_FLUTE.get(), "Right-click to capture a dragon; right-click again to release it.");
        this.add(TCRItems.RESONANCE_STONE.get(), "Resonance Stone");
        this.add(TCRItems.LAND_RESONANCE_STONE.get(), "Land Resonance Stone");
        this.add(TCRItems.OCEAN_RESONANCE_STONE.get(), "Ocean Resonance Stone");
        this.add(TCRItems.CURSED_RESONANCE_STONE.get(), "Cursed Resonance Stone");
        this.add(TCRItems.CORE_RESONANCE_STONE.get(), "Core Resonance Stone");
        this.add(TCRItems.NETHER_RESONANCE_STONE.get(), "Nether Resonance Stone");
        this.add(TCRItems.SKY_RESONANCE_STONE.get(), "Aether Resonance Stone");
        this.add(TCRItems.END_RESONANCE_STONE.get(), "End Resonance Stone");
        this.add(TCRItems.CORE_FLINT.get(), "Core Flint");
        this.addItemUsageInfo(TCRItems.CORE_FLINT.get(), "Use on an Obsidian Frame to open a Nether Portal.");
        this.add(TCRItems.PROOF_OF_ADVENTURE.get(), "Proof of Adventure");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE.get(), "Forged from the names of all foes vanquished by your blade. Your journey has reached its end, and your courage is now legend.");
        this.add(TCRItems.DUAL_BOKKEN.get(), "Bokeen");
        this.addItemUsageInfo(TCRItems.DUAL_BOKKEN.get(), "I may have skill issue but I'm not lacking on dedication so do you lacking in dedication?");
        this.add(TCRItems.VOID_CORE.get(), "Void Essence");
        this.addItemUsageInfo(TCRItems.VOID_CORE.get(), "Dropped by the [Ender Guardian] when defeated");
        this.add(TCRItems.ABYSS_CORE.get(), "Abyss Essence");
        this.addItemUsageInfo(TCRItems.ABYSS_CORE.get(), "Dropped by [The Leviathan] when defeated");
        this.add(TCRItems.ARTIFACT_TICKET.get(), "Artifact Essence");
        this.addItemUsageInfo(TCRItems.ARTIFACT_TICKET.get(), "Obtained from certain quests in the quest book. Can be used to refine artifact with the §3[Ferry girl]§r at the §d[Saint Harbor]§r");
        this.add(TCRItems.RARE_ARTIFACT_TICKET.get(), "Golden Artifact Essence");
        this.addItemUsageInfo(TCRItems.RARE_ARTIFACT_TICKET.get(), "Obtained from certain quests in the quest book. Can be used to refine rare artifact with the §3[Ferry girl]§r at the §d[Saint Harbor]§r");
        this.add(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "Oracle Fragment");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "It bears an ancient oracle hinting at the locations of scattered embers. Show it to the The Sanctuary Keeper in the The Sanctuary; it might aid your adventure!");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "§cIn multiplayer mode, do not occupy other players'! Everyone must submit their own!", 2);
        ;
        this.addInfo("quest_updated","Current Quest Updated!");
        this.addInfo("i18n_pack", "I18n pack, thanks to all translators!");
        this.addInfo("can_not_dodge", "Boss attacks cannot be dodged!");
        this.addInfo("can_not_guard", "Boss attacks cannot be blocked!");
        this.addInfo("pec_weapon_lock", "Weapon skill locked! Defeat [%s] in [%s] to unlock!");
        this.addInfo("resonance_stone_usage", "Can resonate with the location of the Angel's seal");
        this.addInfo("resonance_search_failed", "[ERROR]：Failed to Resonate! Please retry after restarting the game! [%s]");
        this.addInfo("yamato_skill_lock", "[%s] are locked. Requires special enchantment book to unlock!");
        this.addInfo("congratulation", "Congratulations!");
        this.addInfo("open_backpack_tutorial", "Press [%s] to view Backpack");
        this.addInfo("unlock_new_ftb_page_title", "§6New Chapter Unlocked!");
        this.addInfo("unlock_new_ftb_page_subtitle", "§aPress [%s§a] to view");
        this.addInfo("resonance_stone_working", "[%s] Resonating... Please wait patiently...");
        this.addInfo("containing_dragon", "Type: [%s]");
        this.addInfo("dragon_owner", "Owner: [%s]");
        this.addInfo("only_work_on_dragon", "Only can capture dragon.");
        this.addInfo("quest_map_mark", "Quest Pos");
        this.addInfo("map_pos_marked_press_to_open", "Labeled location, press [%s] to view the map.");
        this.addInfo("press_to_open_skill_tree", "Press %s to open Skill Tree");
        this.addInfo("press_to_show_quest_ui", "Press %s to view quests");
        this.addInfo("please_use_custom_flint_and_steel", "Please use [%s] to spawn portal");
        this.addInfo("exit_quest_screen", "Exit");
        this.addInfo("start_tracking_quest", "Select");
        this.addInfo("cancel_tracking_quest", "Stop");
        this.addInfo("no_quest", "No Quest");
        this.addInfo("tracking_quest", " [☆Tracking]");
        this.addInfo("require_item_to_wake", "Require [%s]...");
        this.addInfo("weapon_no_interact", "Cannot interact! Please press [%s] vanilla mode or other item.");
        this.addInfo("tudigong_gift", "[Gift]");
        this.addInfo("tudigong_gift_get", "§6[TuDi]§f: I have grown old and incapable, so Ill pass this treasure to you！");
        this.addInfo("need_to_unlock_waystone", "Some waystones remain inactive!");
        this.addInfo("nether_unlock", "Nether Unlocked!");
        this.addInfo("end_unlock", "End Unlocked!");
        this.addInfo("nothing_happen_after_bless", "§dNothing happened... The [Eye] has been used.");
        this.addInfo("dim_max_4_players", "§6Cloudland can only contain 4 players!");
        this.addInfo("dim_max_players", "§6Maximum capacity reached");
        this.addInfo("can_not_enter_before_finish", "§6You are not destined to enter this cloudland.");
        this.addInfo("can_not_do_this_too_early", "§6You are not destined to do this.");
        this.addInfo("captain_start_heal", "§cCornelia started healing! Increase your damage!");
        this.addInfo("illegal_item_tip", "§cIllegal Item!");
        this.addInfo("illegal_item_tip2", "§6Currently, you are not destined to use this item.");

        this.addInfo("shift_to_pic", "Attack when pressing Shift to pick-up");
        this.addInfo("no_place_to_ship", "No space for ship!");
        this.addInfo("boss_killed_ready_return", "§6Boss has been defeated! Block interaction unlocked!");
        this.addInfo("click_to_return", "§a[Click to return]");
        this.addInfo("cs_warning", "§c§l WARNING！Compute Shader is inactive now! You could enable it in Epic Fight config to get a better experience!");
        this.addInfo("wraithon_start_tip", "§d[Wraithon]: §6Outsider, your journey ends here!");
        this.addInfo("wraithon_end_tip", "§d[Wraithon]: §6This... is impossible...");
        this.addInfo("dim_block_no_interact", "§cBoss has not been defeated! Cannot interact with the cloudland blocks yet!");
        this.addInfo("altar_dim_info", "Cloudland Info:");
        this.addInfo("related_loot", "Monster: [%s] | Related Loot: [%s]");
        this.add(TCRBlocks.CURSED_ALTAR_BLOCK.get(), "Cursed Altar");
        this.add(TCRBlocks.ABYSS_ALTAR_BLOCK.get(), "Abyss Altar");
        this.add(TCRBlocks.STORM_ALTAR_BLOCK.get(), "Storm Altar");
        this.add(TCRBlocks.FLAME_ALTAR_BLOCK.get(), "Flame Altar");
        this.add(TCRBlocks.DESERT_ALTAR_BLOCK.get(), "Desert Altar");
        this.add(TCRBlocks.MONST_ALTAR_BLOCK.get(), "Monst Altar");
        this.add(TCRBlocks.VOID_ALTAR_BLOCK.get(), "Void Altar");
        this.add(TCRBlocks.MECH_ALTAR_BLOCK.get(), "Mech Altar");

        this.addInfo("attack_to_restart", "§cAttack to restart");
        this.addInfo("after_heal_stop_attack", "§6Stop attack to clear anggression.");
        this.addInfo("cloud_follow_me", "§6[Magic Cloud]: §fHi, follow me!");
        this.addInfo("dim_demending", "§6Rebuilding... Wait[%d§6]s");
        this.addInfo("to_be_continue2", "[P1nero]: §6Thank you for playing! More bosses are in the making, stay tuned for more!");
        this.addInfo("second_after_boss_die_left", "Returning to the Overworld in %d seconds");
        this.addInfo("press_to_open_battle_mode", "§cPress [%s] to enable Battle Mode!§r");
        this.addInfo("unlock_new_dim_girl", "§6New options unlocked at the Ferry girl!§r");
        this.addInfo("unlock_new_dim", "§c[Nether]§d[End]§6 unlocked!§r");
        this.addInfo("iron_golem_name", "Sky Island Guardian");

        this.addInfo("get_mimic_invite", "[%s]: Otherworldly one, I knew I was right about you! Take this §6[%s§6]§f!");
        this.addInfo("kill_arterius", "[%s]: Otherworlder, you are indeed formidable! It seems the prophecy is true! Then, I shall bestow these pieces of [%s] upon you!");

        this.addInfo("finish_all_eye", "§dAll altars are lit!§r");
        this.addInfo("time_to_altar", "The scattered embers have been found. It's time to return and light-up the altars...");
        this.addInfo("time_to_ask_godness_statue", "§d*This item can be used at the statue of the Goddess.");
        this.addInfo("time_to_end", "All altars are lit. It's time to find the The Sanctuary Keeper to perform the ritual...");

        this.addInfo("can_not_enter_dim", "It seems you have not yet gained the divine approval to enter... §6Continue collecting embers§r to receive more oracles!");
        this.addInfo("reset_when_no_player", "If no players remain in the Cloudland, leaving for too long will reset the Cloudland!");
        this.addInfo("on_full_set", "Full Set Effect");
        this.addInfo("unlock_new_ftb_page", "A new quest page has been unlocked. Please open the §6[Quest Book]§r to check");
        this.addInfo("unlock_new_skill_page", "A new skill book interface has been unlocked!");
        this.addInfo("unlock_new_skill", "Unlocked [%s]!");
        this.addInfo("unlock_new_skill_sub", "Press §6[%s]§r to check new skill");
        this.addInfo("hit_barrier", "This area is not available yet. Come back later!");

        this.addInfo("death_info", "§6When enemies are too powerful, try combining different skills!");
        this.addInfo("enter_dimension_tip", "Right-click the altar core to enter the Cloudland");
        this.addInfo("use_true_eye_tip", "Please use [%s] to right-click the altar core");

        this.addInfo("add_item_tip", "New item obtained: %s × %d!");
        this.addInfo("skill_point_lack", "This skill requires %d skill points to unlock");
        this.addInfo("press_to_open_portal_screen2", "Click the §6[Scroll]§r in the inventory to return to previously activated stones!");
        this.addInfo("press_to_show_progress", "Press §6[L]§f to view guidance!");
        this.addInfo("press_to_skill_tree", "Sufficient EXP available. Press §6[K]§f to allocate skill points!");
        this.addInfo("lock_tutorial", "§6[[%s§6]§r to lock on");
        this.addInfo("lock_tutorial_sub", "§cMove the mouse to switch targets!");
        this.addInfo("riptide_tutorial", "§6[[%s§6]§f to §bRiptide");
        this.addInfo("dodge_tutorial", "§6[[%s§6]§f to dodge");
        this.addInfo("weapon_innate_tutorial", "§6[[%s§6]§f to use your weapon's skill");
        this.addInfo("weapon_innate_charge_tutorial", "§6[Perfect Dodge]§c or §6[Perfect Parry]§c can charge certain weapons!");
        this.addInfo("perfect_dodge_tutorial", "§cDodge in time to Perfect Dodge!");
        this.addInfo("hurt_damage", "Dealt [ %s ] damage!");
        this.addInfo("parry_tutorial", "§6[[%s§6]§f to guard");
        this.addInfo("perfect_parry_tutorial", "§cBlock in time to Perfect Parry!");
        this.addInfo("you_pass", "§6You passed!!");

        this.addInfo("press_to_open_map", "§6[M]§f to view the map");

        this.addInfo("godness_statue_pos", "Goddess Statue");
        this.addInfo("eye_pos_mark", "Location of [%s]: [%s]");

        this.addAdvancement(TCRCoreMod.MOD_ID, "The Casket of Reveries", "Where the dream begins.");
        this.addAdvancement("unlock_weapon_armor_book", "Mysterious Weapons", "");
        this.addAdvancement("unlock_magic_and_boss", "Necromancy Scroll", "");
        this.addAdvancement("unlock_epic_boss", "Primal Arena", "");

        this.add(TCREntities.CHRONOS_SOL.get(), "Chronos Sol");
        this.add(TCREntities.FERRY_GIRL.get(), "Ferry girl");
        this.add(TCREntities.ORNN.get(), "Old Ornn");
        this.add(TCREntities.AINE.get(), "Aine");
        this.add(TCREntities.TUTORIAL_GOLEM.get(), "Training Golem");
        this.add(TCREntities.TCR_MIMIC.get(), "Me?");

        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), "Thalassa Mare");
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), "Letum Quietus");
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), "Nihil Vacuum");
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), "Ignis Ardens");
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), "Ignis Ardens Shield");
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), "Caelum Altum");
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), "Terra Montis");
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), "Anima Essentia");
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), "Infernus Abyssus");


        this.addDialogAnswer(EntityType.IRON_GOLEM, 0, "Man, are you ready？");
        this.addDialogOption(EntityType.IRON_GOLEM, 0, "Yes");
        this.addDialogOption(EntityType.IRON_GOLEM, 1, "Wait");
        this.addDialogAnswer(EntityType.VILLAGER, -2, "Mambo?");
        this.addDialogAnswer(EntityType.VILLAGER, -1, "!!!");
        this.addDialogAnswer(EntityType.VILLAGER, 0, "Mambo, mambo, oh my gilly, mambo~");
        this.addDialogAnswer(EntityType.VILLAGER, 1, "Zabu zabu~");
        this.addDialogAnswer(EntityType.VILLAGER, 2, "Wa i sha~ Mambo~");
        this.addDialogAnswer(EntityType.VILLAGER, 3, "Nanbei ludou~ Axi ga axi~");
        this.addDialogAnswer(EntityType.VILLAGER, 4, "Hakimi nanbei ludou~ Axi ga axi~");
        this.addDialogAnswer(EntityType.VILLAGER, 5, "Ding dong ji~ Ding dong ji~");
        this.addDialogAnswer(EntityType.VILLAGER, 6, "You da you da~");
        this.addDialogAnswer(EntityType.VILLAGER, 7, "Axi ga hayaku naru~ wow~");
        this.addDialogOption(EntityType.VILLAGER, -3, "[Try Emeralds?]");
        this.addDialogOption(EntityType.VILLAGER, -2, "[This villager shows no interest...]");
        this.addDialogOption(EntityType.VILLAGER, -1, "[Accept]");
        this.addDialogOption(EntityType.VILLAGER, 0, "[???]");
        this.addDialogOption(EntityType.VILLAGER, 1, "[It seems the local residents are heavily corrupted!]");
        this.addDialogOption(EntityType.VILLAGER, 2, "[What are you mumbling about?]");
        this.addDialogOption(EntityType.VILLAGER, 3, "[Why can't I understand the villagers' language...]");


        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 0, "Gu Gu ga ga!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 0, "Accept");
    }
}

package com.p1nero.tcrcore.datagen.lang;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.hm.efn.registries.EFNItem;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.client.TCRKeyMappings;
import com.p1nero.tcrcore.dialog.custom.handler.no_entity.FirstEnterCloudlandScreenHandler;
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
import net.unusual.blockfactorysbosses.init.BlockFactorysBossesModItems;

public class TCRZHLangGenerator extends TCRLangProvider {
    public TCRZHLangGenerator(PackOutput output) {
        super(output, "zh_cn");
    }

    @Override
    protected void addTranslations() {

        this.add("travelerstitles.minecraft.overworld", "记忆中的主世界");
        this.add("travelerstitles.aether.the_aether", "记忆中的天界");
        this.add("travelerstitles.minecraft.the_nether", "记忆中的下界");
        this.add("travelerstitles.minecraft.the_nether.color", "750909");
        this.add("travelerstitles.minecraft.the_end", "记忆中的终界");
        this.add("travelerstitles.minecraft.the_end.color", "4f219e");

        this.addQuest(TCRQuests.WAIT_RESONANCE_STONE_CHARGE, "间章", "等待[%s]充能", "[%s]的能量已经耗尽，重新充能需要一段时间。在充能完成之前先去做点别的事吧！\n\n§6[注意]：若不想等待，可使用\"/time add\"命令将时间增加6000。");
        this.addQuest(TCRQuests.PUT_DESERT_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_ABYSS_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_CURSED_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_FLAME_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_MECH_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_STORM_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_VOID_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.PUT_MONST_EYE_ON_ALTAR, "间章", "将[%s]置于祭坛上", "[%s]已经寻回，快将它归位到圣殿长廊的祭坛上吧！");
        this.addQuest(TCRQuests.BLESS_ON_THE_GODNESS_STATUE, "间章", "在女神像处祈福", "[神之眼]已经寻回，借助花园里的女神像，我们可以与神之眼共鸣，获得来自使徒们的力量。");

        this.addQuest(TCRQuests.TALK_TO_AINE_CLOUDLAND, "间章", "和%s对话", "当你接触祭坛后被卷入了一个神秘的世界，或许我们应该称之为幻境（Cloudland）。去找%s聊聊吧，或许她知道这究竟是什么地方。");

        //序章
        this.addQuest(TCRQuests.TALK_TO_AINE_0, "序章", "和%s对话", "和%s说好了一起来到这个世界，但是当你回过神来后%s已经找不着人影了，快去圣殿里找找她吧！你依稀记得她好像说有什么新时装要给你。");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_0, "序章", "和？对话", "和%s说好了一起来到这个世界，但是当你回过神来后%s已经找不着人影了。这里到底是什么地方？和圣殿长廊中那位端庄的女人交流看看吧！");
        this.addQuest(TCRQuests.TALK_TO_FERRY_GIRL_0, "序章", "前往Overworld", "你已经听说了这个世界的故事，准备好了就前往码头寻找 %s 吧！她将为我们打开前往Overworld的路！她似乎还有什么宝贝要送给你。");
        this.addQuest(TCRQuests.TALK_TO_ORNN_0, "序章", "和%s对话", "在%s的建议下，先去武库找%s武装一下我们自己吧！充分的武装才能保证我们顺利的冒险！");

        //驯龙支线
        this.addQuest(TCRQuests.TAME_DRAGON, "驯龙之章", "将龙养大", "%s送给了你一条龙，现在，按[%s]上说的办法，将它养成年吧！龙，可是帝王之征！\n\n§a[任务奖励]: §f[%s] [%s]");
        this.addQuest(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL, "驯龙之章", "和%s对话", "经过你精心呵护，龙已长大成年。%s之前说过，在龙养大后她有宝具要赠与我们。快回去找%s看看吧！\n\n§a[任务奖励]: §f[%s] [%s]");

        //主线·沙漠之眼
        this.addQuest(TCRQuests.USE_LAND_RESONANCE_STONE, "大地之章", "使用[%s]", "你终于来到了传说中的Overworld。在这里将会遇到什么样的冒险呢？快使用[%s]吧！它将指引我们寻回第一颗眼睛。");
        this.addQuest(TCRQuests.GET_DESERT_EYE, "大地之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:[%s§a]可能藏匿于方块之中！\n\n§4[注意]：若获取后无法完成任务，请尝试关闭可能自动拾取物品的插件，并扔出去后重新拾取！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_1, "大地之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");
        //奇美拉支线
        this.addQuest(TCRQuests.BONE_CHIMERA_QUEST, "大地之章", "前往[%s]", "[%s]似乎为我们标记了一个另一个地点，说不定有什么奇遇，快去看看吧！\n\n§a[任务奖励]: §f[%s§f]");
        this.addQuest(TCRQuests.TALK_TO_ORNN_1, "大地之章", "和%s对话", "从[%s]身上获得了[%s]。上面到底记载了什么秘密？带回主城找%s看看吧！");

        //主线·深渊之眼
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_2, "海洋之章", "和%s对话", "经过漫长的等待，%s已完成充能。快回去找%s吧！她在圣殿里等你。");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_OCEAN, "海洋之章", "前往Overworld", "[%s]已完成充能，该前往Overworld使用它了！它将为我们指引下一个眼睛的位置！");
        this.addQuest(TCRQuests.USE_OCEAN_RESONANCE_STONE, "海洋之章", "使用[%s]", "你已经抵达了主世界，快使用[%s]吧！它将为我们指引下一个眼睛的位置！到底有什么样的冒险在等着我们呢？");
        this.addQuest(TCRQuests.GET_OCEAN_EYE, "海洋之章", "寻回[%s]", "[%s]为我们标记了[%s]所散落的位置，快出发去寻回[%s]吧！\n\n§a[提示]:[%s§a]可能藏匿于方块之中！ \n\n§4[注意]：若获取后无法完成任务，请尝试关闭可能自动拾取物品的插件，并扔出去后重新拾取！");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_3, "海洋之章", "和%s对话", "[%s]已经寻回，快回主城找%s汇报吧！她将告诉我们下一步该做什么。");

        this.addQuest(TCRQuests.RIBBITS_QUEST, "海洋之章", "探索[%s]", "[%s]似乎为我们标记了一个另一个地点，说不定有什么奇遇，快去看看吧！\n\n§a[任务奖励]: §f[%s] [%s]\n\n§6[推荐优先完成]");
        this.addQuest(TCRQuests.GIVE_AMETHYST_BLOCK_TO_RIBBITS, "海洋之章", "将[%s]交给%s", "看来想要打探更多关于[%s]的线索，需要与%s们做个交易才行。收集好12个[%s]就回去找它们吧！\n\n§a[任务奖励]: §f[%s] [%s]\n\n§6[推荐优先完成]");

        this.addEffect(TCREffects.INVULNERABLE, "无敌");
        this.addEffect(TCREffects.SOUL_INCINERATOR, "灵魂火");

        this.add("epicfight.skill_slot.passive4", "被动4");
        this.add("epicfight.skill_slot.passive5", "被动5");
        this.add("travelerstitles.tcrcore.sanctum", "梦之领域");
        this.addBiome(TCRBiomes.AIR, "虚无之地");

        StartScreenHandler.onGenerateZH(this);
        FirstEnterCloudlandScreenHandler.onGenerateZH(this);

        this.add("item.domesticationinnovation.collar_tag.tcr_info", "可进行特殊附魔，并将附魔应用于宠物身上。");
        this.addTCRItemInfo(net.blay09.mods.waystones.item.ModItems.warpStone, "点击物品栏中的传送卷轴按钮以进行传送。");
        this.addTCRItemInfo("§c警告！此物品可能导致重要道具被吸入背包而失效！", net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems.ADVANCED_MAGNET_UPGRADE.get(), net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems.MAGNET_UPGRADE.get(), net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems.PICKUP_UPGRADE.get(), net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems.ADVANCED_PICKUP_UPGRADE.get());
        this.addTCRItemInfo(ItemRegistry.BOTTLE_OF_BLOOD.get(), "使用§d[尼赫莫斯]§r掉落物§c[晶化血髓]§r酿造获取");
        this.addTCRItemInfo(EFNItem.DEEPDARK_HEART.get(), "击败§2[监守者]§r或§2[可妮莉亚船长]§r获取");
        this.addTCRItemInfo(BlockFactorysBossesModItems.DRAGON_SKULL.get(), "击败§c[冥界骑士]§r或§4[炼狱魔龙]§r获取");
        this.addTCRItemInfo(ModItems.CORAL_CHUNK.get(), "于§d利维坦幻境§r击败§a[珊瑚巨像]§r获取");
        this.addTCRItemInfo(BlockFactorysBossesModItems.DRAGON_BONE.get(), "击败§c[冥界骑士]§r或§4[炼狱魔龙]§r获取");
        this.addTCRItemInfo(com.github.dodo.dodosmobs.init.ModItems.CHIERA_CLAW.get(), "击败§e[骸骨奇美拉]§r获取");
        this.addTCRItemInfo(ModItems.CHITIN_CLAW.get(), "于§3斯库拉幻境§r击败§3巨钳守卫§r获取");
        this.addTCRItemInfo(BlockFactorysBossesModItems.KNIGHT_SWORD.get(), "击败§c[冥界骑士]§r获取");
        this.addTCRItemInfo(Items.DRAGON_EGG, "于§d末地§r击败§d[末影龙]§r获取");
        this.addTCRItemInfo(EpicSkillsItems.ABILIITY_STONE.get(), "右键使用以获取技能点");

        this.add("itemGroup.tcr.items", "远梦之棺 —— 核心 物品");
        this.add("key.categories." + TCRCoreMod.MOD_ID, "远梦之棺 —— 核心");
        this.addKeyMapping(TCRKeyMappings.RIPTIDE, "激流");
        this.addKeyMapping(TCRKeyMappings.SHOW_QUESTS, "隐藏/显示指引");

        this.add("skill_tree.sword_soaring.unlock_tip", "与§6[天空岛]§r村民用绿宝石交易解锁");
        this.add("unlock_tip.tcrcore.battleborn.water_avoid1", "使用§d[紫水晶块]§f向§6[呱呱]§f交易习得");
        this.add("unlock_tip.tcrcore.battleborn.fire_avoid", "击败§6[骸骨奇美拉]§r习得");
        this.add("unlock_tip.tcrcore.get_vatansever", "获得§d「卫疆刃」§f后解锁");
        this.addSkill("water_avoid", "避水咒", "可在水下自由呼吸！");
        this.addSkill("fire_avoid", "避火咒", "免疫火焰伤害！");
        this.addSkill("perfect_dodge", "闪避特效", "完美闪避时将有帅气的动作！");

        this.add(TCRItems.MYSTERIOUS_WEAPONS.get(), "百兵图");
        this.addItemUsageInfo(TCRItems.MYSTERIOUS_WEAPONS.get(), "上面似乎记载了世间百般兵器，拿给了解的人看看吧。");
        this.add(TCRItems.DRAGON_FLUTE.get(), "龙之笛");
        this.addItemUsageInfo(TCRItems.DRAGON_FLUTE.get(), "右键可收服龙，再次右键可释放龙。");
        this.add(TCRItems.RESONANCE_STONE.get(), "共鸣石");
        this.add(TCRItems.LAND_RESONANCE_STONE.get(), "大地共鸣石");
        this.add(TCRItems.OCEAN_RESONANCE_STONE.get(), "海洋共鸣石");
        this.addInfo("resonance_stone_usage", "可与使徒封印的位置共鸣。");
        this.add(TCRItems.CORE_FLINT.get(), "炉心火石");
        this.addItemUsageInfo(TCRItems.CORE_FLINT.get(), "在黑曜石框架上使用，可打开地狱之门。");
        this.add(TCRItems.PROOF_OF_ADVENTURE.get(), "冒险之证");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE.get(), "此证乃是用所有败于你剑下的强敌之名铸就。你的冒险已抵达终点，你的勇气已化为传奇。");
        this.add(TCRItems.DUAL_BOKKEN.get(), "双持木棍");
        this.addItemUsageInfo(TCRItems.DUAL_BOKKEN.get(), "我或许能力不足，但绝不缺乏奉献精神，难道你缺少奉献精神吗？");
        this.add(TCRItems.VOID_CORE.get(), "虚空精华");
        this.addItemUsageInfo(TCRItems.VOID_CORE.get(), "击败[末影守卫]掉落");
        this.add(TCRItems.ABYSS_CORE.get(), "深渊精华");
        this.addItemUsageInfo(TCRItems.ABYSS_CORE.get(), "击败[利维坦]掉落");
        this.add(TCRItems.ARTIFACT_TICKET.get(), "饰品精华");
        this.addItemUsageInfo(TCRItems.ARTIFACT_TICKET.get(), "通过任务书某些任务获取。可在§d[圣殿港口]§r的§3[摆渡人]§r处提炼饰品");
        this.add(TCRItems.RARE_ARTIFACT_TICKET.get(), "稀有饰品精华");
        this.addItemUsageInfo(TCRItems.RARE_ARTIFACT_TICKET.get(), "通过任务书某些任务获取。可在§d[圣殿港口]§r的§3[摆渡人]§r处提炼稀有饰品");
        this.add(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "神谕残卷");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "上面写着古老的神谕，暗示了火种散落的地方。回主城给守望者看看吧，说不定对冒险有帮助！");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "§c多人模式请勿占据他人任务道具！每人都需各自提交！", 2);

        this.addInfo("unlock_new_ftb_page_title", "§6新图鉴解锁！");
        this.addInfo("unlock_new_ftb_page_subtitle", "§a按[%s§a]查看");
        this.addInfo("resonance_stone_working", "[%s]共鸣中...请耐心等待...");
        this.addInfo("containing_dragon", "物种：[%s]");
        this.addInfo("dragon_owner", "主人：[%s]");
        this.addInfo("only_work_on_dragon", "龙之球仅能作用于龙");
        this.addInfo("quest_map_mark", "任务点");
        this.addInfo("map_pos_marked_press_to_open", "已标记地点，按 [%s] 查看地图");
        this.addInfo("press_to_open_skill_tree", "按下 %s 以打开技能树");
        this.addInfo("press_to_show_quest_ui", "按 %s 键查看任务列表");
        this.addInfo("please_use_custom_flint_and_steel", "请使用[%s]开启地狱传送门");
        this.addInfo("exit_quest_screen", "退出");
        this.addInfo("start_tracking_quest", "开始追踪");
        this.addInfo("cancel_tracking_quest", "停止追踪");
        this.addInfo("no_quest", "暂无任务");
        this.addInfo("tracking_quest", " [☆追踪中]");
        this.addInfo("require_item_to_wake", "需要[%s]以唤醒...");
        this.addInfo("weapon_no_interact", "武器禁止交互！请使用其他物品或按[%s]切换非战斗模式");
        this.addInfo("tudigong_gift", "[见面礼]");
        this.addInfo("tudigong_gift_get", "§6[土地公]§f: 老夫不中用矣，此宝具赠予你罢！");
        this.addInfo("need_to_unlock_waystone", "仍有传送石碑未激活！");
        this.addInfo("nether_unlock", "地狱之门已解锁！");
        this.addInfo("end_unlock", "末地之门已解锁！");
        this.addInfo("dim_max_4_players", "§6幻境最多容纳4人！");
        this.addInfo("can_not_enter_before_finish", "§6当前没有进入幻镜的命运。");
        this.addInfo("can_not_do_this_too_early", "§6当前没有做此事的命运。");
        this.addInfo("after_heal_stop_attack", "§6停止攻击以取消傀儡仇恨");
        this.addInfo("cloud_follow_me", "§6[仙气]：§f嗨伙计，跟着我！");
        this.addInfo("shift_to_pic", "潜行时攻击以收回");
        this.addInfo("no_place_to_ship", "空间不足，无法摆放船只！");
        this.addInfo("boss_killed_ready_return", "§6幻境英灵已击败！已解锁方块交互！");
        this.addInfo("click_to_return", "§a[点击返回主城]");
        this.addInfo("cs_warning", "§c§l警告！Compute Shader未启用！建议在史诗战斗设置中开启以获得更流畅的体验！");
        this.addInfo("wraithon_start_tip", "§d[Wraithon]: §6外来之人，你的旅途到此结束！");
        this.addInfo("wraithon_end_tip", "§d[Wraithon]: §6这...不可能...");
        this.addInfo("dim_block_no_interact", "§cBoss未击败！暂时无法与幻境方块交互！");
        this.addInfo("altar_dim_info", "幻境信息：");
        this.addInfo("related_loot", "魔物： [%s] | 相关战利品：[%s]");
        this.add(TCRBlocks.CURSED_ALTAR_BLOCK.get(), "诅咒祭坛");
        this.add(TCRBlocks.ABYSS_ALTAR_BLOCK.get(), "深渊祭坛");
        this.add(TCRBlocks.STORM_ALTAR_BLOCK.get(), "风暴祭坛");
        this.add(TCRBlocks.FLAME_ALTAR_BLOCK.get(), "烈焰祭坛");
        this.add(TCRBlocks.DESERT_ALTAR_BLOCK.get(), "沙漠祭坛");
        this.add(TCRBlocks.MONST_ALTAR_BLOCK.get(), "恶兽祭坛");
        this.add(TCRBlocks.VOID_ALTAR_BLOCK.get(), "虚空祭坛");
        this.add(TCRBlocks.MECH_ALTAR_BLOCK.get(), "机械祭坛");

        this.addInfo("nothing_happen_after_bless", "§d无事发生...看来该[神之眼]已经祈福过了");
        this.addInfo("attack_to_restart", "§c攻击Boss以再次发起挑战");
        this.addInfo("captain_start_heal", "§c可妮莉亚船长开始回血！增大伤害击败她！");
        this.addInfo("illegal_item_tip", "§c检测到非法物品！");
        this.addInfo("illegal_item_tip2", "§6当前没有使用此物品的命运。");

        this.addInfo("dim_demending", "§6幻境重铸中...请等待[%d§6]秒");
        this.addInfo("to_be_continue2", "[P1nero]: §6感谢游玩！更多Boss持续制作中，未完待续！");

        this.addInfo("second_after_boss_die_left", "将在 %d 秒后返回主世界");

        this.addInfo("press_to_open_battle_mode", "§c请按[%s]开启战斗模式!§r");
        this.addInfo("unlock_new_dim_girl", "§6摆渡人处已解锁新选项!§r");
        this.addInfo("unlock_new_dim", "§c[地狱]§d[末地]§6已解锁!§r");
        this.addInfo("iron_golem_name", "天空岛之守卫");

        this.addInfo("get_mimic_invite", "[%s]: 异界之人，我果然没看错你！这封§6[%s§6]§f，你收下罢！");
        this.addInfo("kill_arterius", "[%s]: 异界之人，果然有几分本事！看来预言是对的！这几块[%s§f]，赠予你罢！");

        this.addInfo("finish_all_eye", "§d众祭坛已点亮！§r");
        this.addInfo("time_to_altar", "失散火种已寻，该回去点亮祭坛了...");
        this.addInfo("time_to_ask_godness_statue", "§d*可在女神像处祈求祝福");
        this.addInfo("time_to_end", "所有祭坛已点亮，该找守卫者举行仪式了...");

        this.addInfo("can_not_enter_dim", "看来当前还未能达到神之认可，无法进入...§6继续收集火种§r以获取更多神谕吧！");
        this.addInfo("reset_when_no_player", "当幻境内没有玩家存在时，离开幻境过久，幻境将会重置！");
        this.addInfo("on_full_set", "套装效果");
        this.addInfo("unlock_new_ftb_page", "解锁了新的任务界面，请打开§6[任务书]§r查看");
        this.addInfo("unlock_new_skill_page", "解锁了新的技能书界面，按§6[K]§r查看");
        this.addInfo("unlock_new_skill", "解锁了[%s]! 按§6[K]§r查看");
        this.addInfo("hit_barrier", "前面的区域，以后再来探索吧！");

        this.addInfo("death_info", "§6敌人过于强大时，可以尝试搭配不同技能组合！");
        this.addInfo("enter_dimension_tip", "右键祭坛核心以进入英灵幻境");
        this.addInfo("use_true_eye_tip", "请使用 [%s] 右键祭坛核心");

        this.addInfo("add_item_tip", "获得新物品：%s × %d！");
        this.addInfo("skill_point_lack", "释放该技能需 %d 技能点");
        this.addInfo("press_to_open_portal_screen2", "点击物品栏中的§6[卷轴]§r以回到曾经点亮过的石碑！");
        this.addInfo("press_to_show_progress", "按下§6[L]键§f以查看指引！");
        this.addInfo("press_to_skill_tree", "经验充足，按下§6[K]键§f以进行技能加点！");
        this.addInfo("lock_tutorial", "按下§6[%s§6]§r以锁定目标");
        this.addInfo("lock_tutorial_sub", "§c晃动鼠标以切换锁定目标！再次按下以解除锁定！");
        this.addInfo("riptide_tutorial", "在水中按下§6[%s§6]键§f以释放§b激流");
        this.addInfo("dodge_tutorial", "按下§6[%s§6]§f以释放闪避技能");
        this.addInfo("weapon_innate_tutorial", "按下§6[%s§6]键§f以释放武器技能");
        this.addInfo("weapon_innate_charge_tutorial", "§6[完美闪避]§c或§6[完美招架]§c可以对部分武器进行充能！");
        this.addInfo("perfect_dodge_tutorial", "§c抓住时机闪避以释放完美闪避！");
        this.addInfo("hurt_damage", "造成[ %s ]点伤害！");
        this.addInfo("parry_tutorial", "按下§6[%s§6]§f以进行格挡");
        this.addInfo("perfect_parry_tutorial", "§c抓住时机格挡以触发完美招架！");
        this.addInfo("you_pass", "§6你过关！！");

        this.addInfo("press_to_open_map", "按下§6[M]键§f以查看地图");

        this.addInfo("godness_statue_pos", "女神像");
        this.addInfo("eye_pos_mark", "[%s]之所在：[%s]");

        this.addAdvancement("dragon_tame", "驯龙高手", "驯服斯克里兹");
        this.addAdvancement(TCRCoreMod.MOD_ID, "远梦之棺", "梦开始的地方，前往主城寻找守护者。");
        this.addAdvancement(TCRCoreMod.MOD_ID + "_weapon", "王之宝库", "所有可获得的且进行过适配的武器或材料，可通过 [JEI] 查看获取方式及详细信息");
        this.addAdvancement("find_ymsw", "呱呱村庄", "抵达呱呱村庄");
        this.addAdvancement("aquamirae_weapon", "海灵物语-武器", "");
        this.addAdvancement("cataclysm_weapon", "灾变-武器", "");
        this.addAdvancement("legend_weapon", "传奇武器", "");
        this.addAdvancement("ef_legacy", "史诗战斗-武器", "皆可通过合成获取，拥有不同的武器技能和不同的动作模板，可在JEI查看合成方式及技能信息");
        this.addAdvancement("kill_pillager", "投名状", "任务已经完成，该回去找守护神了。");
        this.addAdvancement("mark_map", "标记地点", "守护神帮你标出了神之眼散落的地方，现在动身去夺回它们吧！");
        this.addAdvancement("storm_eye", "风暴之眼", "§a§o当战火撕裂云层，她以风暴为阶梯，为子民筑起悬空净土");
        this.addAdvancement("abyss_eye", "深渊之眼", "§a§o深渊吞噬陆地时，祂将自己缝进海床，血肉化成气泡之城");
        this.addAdvancement("flame_eye", "烈焰之眼", "§a§o岩浆奔涌之地，祂剜出心脏，铸成永不熄灭的烽火台");
        this.addAdvancement("desert_eye", "沙漠之眼", "§a§o守卫者不是怪物，它们是子民自愿化身的活体墓碑");
        this.addAdvancement("cursed_eye", "诅咒之眼", "§a§o当背叛者刺穿她的脊柱，冻泪瞬间冰封三千幽灵船");

        this.addAdvancement("flame_kill", "伊格尼斯(Ignis)之魂", "击败伊格尼斯(Ignis)，获得炎葬");
        this.addAdvancement("storm_kill", "斯库拉(Scylla)之魂", "击败斯库拉(Scylla)，获得庭浪锚戟");
        this.addAdvancement("abyss_kill", "利维坦(Leviathan)之魂", "击败利维坦(Leviathan)，获得潮汐利爪");
        this.addAdvancement("desert_kill", "远古遗魂(Ancient Remnant)之魂", "击败远古遗魂(Ancient Remnant)，获得沙暴之怒");
        this.addAdvancement("cursed_kill", "玛莱迪克特斯(Maledictus)之魂", "击败玛莱迪克特斯(Maledictus)，获得断魂战戟");

        this.addAdvancement("stage1", "阶段1","");
        this.addAdvancement("stage2", "阶段2","");
        this.addAdvancement("stage3", "阶段3","");
        this.addAdvancement("stage4", "阶段4","");
        this.addAdvancement("stage5", "阶段5","");

        this.add(TCREntities.CHRONOS_SOL.get(), "羲轮｜Chronos Sol");
        this.add(TCREntities.FERRY_GIRL.get(), "摆渡人");
        this.add(TCREntities.AINE_IRIS.get(), "安");
        this.add(TCREntities.ORNN.get(), "老奥恩");
        this.add(TCREntities.TUTORIAL_GOLEM.get(), "训练傀儡");

        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), "沧溟 | Thalassa Mare");
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), "归寂 | Letum Quietus");
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), "湮无 | Nihil Vacuum");
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), "烬煌 | Ignis Ardens");
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), "烬煌盾");
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), "穹霄 | Caelum Altum");
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), "坤岳 | Terra Montis");
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), "魂兮 | Anima Essentia");
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), "狱渊 | Infernus Abyssus");


        this.addDialogAnswer(EntityType.IRON_GOLEM, 0, "人类，准备好接受试炼了？");
        this.addDialogOption(EntityType.IRON_GOLEM, 0, "确定");
        this.addDialogOption(EntityType.IRON_GOLEM, 1, "再等等");
        this.addDialogAnswer(EntityType.VILLAGER, -2, "曼波？");
        this.addDialogAnswer(EntityType.VILLAGER, -1, "！！！");
        this.addDialogAnswer(EntityType.VILLAGER, 0, "曼波，曼波，哦嘛吉利，曼波~");
        this.addDialogAnswer(EntityType.VILLAGER, 1, "砸布砸布~");
        this.addDialogAnswer(EntityType.VILLAGER, 2, "瓦一夏~曼波~");
        this.addDialogAnswer(EntityType.VILLAGER, 3, "南北绿豆~阿西噶阿西~");
        this.addDialogAnswer(EntityType.VILLAGER, 4, "哈基米南北绿豆~阿西噶阿西~");
        this.addDialogAnswer(EntityType.VILLAGER, 5, "叮咚鸡~叮咚鸡~");
        this.addDialogAnswer(EntityType.VILLAGER, 6, "有哒有哒~");
        this.addDialogAnswer(EntityType.VILLAGER, 7, "阿西噶哈雅酷那路~ wow~");
        this.addDialogOption(EntityType.VILLAGER, -3, "[或许可以试试绿宝石？]");
        this.addDialogOption(EntityType.VILLAGER, -2, "[这位村民对该物品并没有兴趣...]");
        this.addDialogOption(EntityType.VILLAGER, -1, "[收下]");
        this.addDialogOption(EntityType.VILLAGER, 0, "[？？？]");
        this.addDialogOption(EntityType.VILLAGER, 1, "[看来，当地的居民被侵蚀的不轻！]");
        this.addDialogOption(EntityType.VILLAGER, 2, "[叽里咕噜说什么呢？]");
        this.addDialogOption(EntityType.VILLAGER, 3, "[为什么和村民就语言不通了...]");

        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -4, "§a收下[%s§a]");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -3, "返回");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 0, "阁下，冒险可还顺利？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 0, "关于这个世界");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 1, "千年以前，“我们”的世界受到未知的天外冲击，黑潮降临世间。面对无穷无尽的黑潮，“我们”无能为力。受黑潮影响，“我们”的记忆逐渐缺失...祂们一个接一个地被黑潮所吞噬，祂们的灵魂被封印在世界各地之中。当“我们”意识到“我们”的记忆正在如潮水般逐渐消退之时，“我们”用魔力写下了死海文书，用以提醒吾等，终有一位救世主自天外归来，将“我们”的力量统合，实现再创世，将黑潮击溃！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 2, "吾受黑潮的侵蚀程度最轻，祂们用尽最后的力气，将吾与脚下这座主城封入原始之海，以减缓黑潮之侵蚀，等待救世主归来。至于救世主是何人，为何有救世的力量，“我们”已无从得知。但在黑潮面前，“我们”只能相信这份古老的记忆，相信再创世的到来...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 3, "阁下说的可是在此处落脚的魔女阁下？她看起来和阁下一样掌握着不属于这个世界的力量，吾相信预言，吾相信阁下与魔女阁下能拯救圣殿，这里的一切都将为二位敞开！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 2, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 4, "自黑潮入侵以来，她在港口驻守千年，是此间与世界唯一的联系。她是“我们”所铸造的人偶，随着“我们”记忆的消退，她的力量也所剩无几了。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 3, "关于%s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 5, "祂是来自主世界的半神，掌管锻造与工艺。使徒们所用之神兵百胄皆出自祂之手。");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 6, "预言中的救世主啊，吾已在此恭候多时！唯有阁下可寻回众神之眼，恢复世界昔日之荣光！");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 4, "你是何人？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 7, "吾乃世界十大使徒之一，名为 %s，掌管岁月。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 5, "使徒？");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 8, "创世之初，圣殿便诞生了十位使徒，掌管世间万物，吾便是其中的一位。“我们”常共聚一堂，商议政要。若有需要“我们”出面之事，“我们”会选则“我们”之中的一位前去。");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 6, "§a关于接下来的行动");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 9, "阁下需寻回祂们的锚定之物——神之眼，将众使徒的灵魂归位到祭坛之上，届时，吾再献上自己的灵魂，便可实现死海文书所记载的再创世！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 10, "原始之海屏蔽了外界的气息，吾在此处无从得知祂们的位置。但借此§6共鸣石§f可再外界寻得神之眼散落的位置。我将这枚共鸣石给予阁下，待阁下到了主世界，共鸣石将引领你寻找使徒所在之处。切记，每个阶段仅能使用一次共鸣石！待阁下寻回神之眼后，吾方可利用神之眼铸造新的共鸣石。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 11, "阁下可以先去§6[武库]§f找 %s 取一样趁手的武器。准备好了就去港口寻找 %s 吧，她将带你前往旅程的起点。");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 12, "阁下，寻找[%s]的旅途可还顺利？");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 7, "§a我已寻回[%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 13, "阁下不愧是预言中的救世主！请阁下将神之眼归位到祭坛上吧！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 14, "神之眼也可在女神像处祈福，神之眼所蕴含的使徒（Angel）的部分力量将通过神像享与阁下。");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 15, "共鸣石充能仍需要点时间，还请阁下等待！");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 16, "共鸣石已完成充能，出发吧，阁下！");

        this.addDialogOption(TCREntities.AINE_IRIS.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 0, "%s, 你来了！我正在阅读这个世界的智库");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 0, "关于%s");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 1, "你已经和那个女人打过招呼啦？她应该就是这里的老大了，看来要解决这里的问题，我们暂时只能按她的话做了。");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 1, "关于这个世界");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 2, "你已经听过那女人讲的故事了吧？但是真是假，还得真正的踏上旅程才知道。来之前可说好了，要靠你自己的力量找回失去的记忆！加油，我会一直在你身边，做你坚强的后盾！");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 2, "§a关于时装");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 3, "看！我这身新衣服！漂亮吧？来到这个世界，也应该换一套新的行头才行！给，你也快换上吧！");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 3, "§a领取");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 4, "%s，有事找我？");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 5, "§a关于幻境");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 5, "幻境（Cloudland）？我找找...");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 6, "嗯...根据智库记载的资料，和我的经验来看，你所接触祭坛后进入的幻境，应该是使徒（Angel）们原先的精神世界，受到黑潮侵蚀后所融合的结果。");
        this.addDialogOption(TCREntities.AINE_IRIS.get(), 6, "精神世界？");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 7, "嗯，或许在幻境深处可以找到使徒（Angel）们的映射，并借此获取它们的力量！");
        this.addDialogAnswer(TCREntities.AINE_IRIS.get(), 8, "在幻境中探索看看吧！说不定会有不小的收获，也许还可以获取宝贵的忆质材料！");

        this.addDialogOption(TCREntities.ORNN.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.ORNN.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.ORNN.get(), 0, "火焰的温度、钢铁的韧性——这两样东西能解决世上大多数问题。");
        this.addDialogOption(TCREntities.ORNN.get(), 0, "你是何人？");
        this.addDialogAnswer(TCREntities.ORNN.get(), 1, "吾乃半神，主掌锻造与工艺，为圣殿铸神兵。黑潮来袭之前，本以为老夫所铸神兵天下无敌。现在想来，可笑，可笑。");
        this.addDialogOption(TCREntities.ORNN.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.ORNN.get(), 2, "守望者大人实在令人捉摸不透，但遵从她的安排便是。");
        this.addDialogAnswer(TCREntities.ORNN.get(), 3, "那小姑娘倒比老夫自在，老夫再也没法回到故土了。");
        this.addDialogOption(TCREntities.ORNN.get(), 2, "§6锻造委托");
        this.addDialogOption(TCREntities.ORNN.get(), 3, "§a见面礼");
        this.addDialogAnswer(TCREntities.ORNN.get(), 4, "这是一些边角碎料所铸成的，你暂且拿去防身吧。");
        this.addDialogOption(TCREntities.ORNN.get(), 4, "%s");
        //拿百兵图
        this.addDialogOption(TCREntities.ORNN.get(), 5, "§6展示[%s§6]");
        this.addDialogAnswer(TCREntities.ORNN.get(), 5, "锻造之神在上！这百兵图……记载了世界上所有高阶武器及其铸造之法！甚至还存在我未曾了解的武器！");
        this.addDialogAnswer(TCREntities.ORNN.get(), 6, "想必此百兵图，乃是是黑潮降临前大地使徒Montis大人所铸。");
        this.addDialogAnswer(TCREntities.ORNN.get(), 7, "交给我吧，我将为你展现这幅精美的画卷！");
        this.addDialogOption(TCREntities.ORNN.get(), 6, "§6解锁图鉴");

        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -3, "返回");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -2, "结束对话");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -1, "继续");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 0, "你好，阁下，有什么可以帮助您的吗？");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 0, "你是何人？");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 1, "吾乃圣殿摆渡人，受%s大人之令在此处护送阁下离开结界前往那方世界。吾也会接纳那方世界的亡魂，因此也留下了不少他们散落的饰品遗物。但需使用[%s]才能于原始之海中打捞。若是阁下有%s，也可交予我。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 1, "关于%s");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 2, "守望者大人日夜为拯救圣殿而操劳，实在令人敬佩。");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 3, "%s锻造之术此间无人可与之比肩，吾也常常将一些珍奇饰品交由他修复。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 2, "§6饰品提取");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 3, "§a前往主世界");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 4, "§6见面礼");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 4, "一份微薄的礼物，还望阁下笑纳。此灵宠孵化成年之后，可日行万里，希望对阁下主世界之旅有所帮助！§6当阁下将灵宠养大后，再来找我吧。");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 5, "§6选择[%s§6]");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 6, "§6我已将龙养大");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 5, "看来小家伙和阁下相处很愉快呢！这些礼物还请阁下收下！阁下可在图鉴中查看它们的用法。相信小家伙们可以给阁下的战斗带来更多的乐趣！");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 7, "收下");

        this.addDialogOption(ModEntities.BONE_CHIMERA, -1, "返回");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 0, "人类？竟能发现此地，可是共鸣石指引你前来此地？");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 0, "你为何囚禁于此？");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 1, "吾乃大地使徒之坐骑，高塔封印降临之时，大地使徒将吾传送至此，共鸣石得以寻吾之所在。");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 1, "释放灵魂");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 2, "此身携有不死诅咒，你若助我解脱，吾之尸骨可铸利器。准备好了吗？");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 2, "准备好了");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 3, "再等等");

        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -2, "离开");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -1, "继续");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 0, "呱！人类！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 0, "呱！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 1, "咕咕嘎嘎！");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 2, "§6交易");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 3, "§6关于[%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 4, "§6提交[%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 5, "§6收下");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 1, "想了解更多的话，带好多好多紫水晶块来换呱！要12个呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 2, "呱！成交呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 3, "呱，打发叫花子呱！我要12个呱！");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 4, "我们原是海洋村庄的人类，受到黑潮诅咒而变成这副模样呱。");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 5, "海洋高塔十分危险呱，念在紫水晶块的份上，这些宝具你暂且带着防身呱！");

    }
}

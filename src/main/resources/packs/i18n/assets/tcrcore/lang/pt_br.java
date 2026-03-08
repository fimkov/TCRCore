package com.p1nero.tcrcore.datagen.lang;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.github.dodo.dodosmobs.init.ModEntities;
import com.hm.efn.registries.EFNItem;
import com.p1nero.p1nero_ec.effect.PECEffects;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.block.TCRBlocks;
import com.p1nero.tcrcore.capability.TCRQuests;
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
        // Alterado para o código de idioma do Brasil
        super(output, "pt_br");
    }

    @Override
    protected void addTranslations() {

        this.add("skill_tree.tcrcore.magic", "Habilidades Mágicas");
        this.add("unlock_tip.epicskills.acrobat.spider_techniques", " Derrote 50 Aranhas");
        this.add("unlock_tip.epicskills.acrobat.natural_sprinter", " Derrote 2 Lycanths e corra 10 km");
        this.add("unlock_tip.epicskills.acrobat.aqua_maneuvre", " Derrote 3 Guardiões Anciões e nade 3 km");

        this.add("unlock_tip.epicskills.prodigy.shooting_style", " Derrote 2 Endermen");
        this.add("unlock_tip.epicskills.prodigy.punishment_kick", " Derrote 25 Endermen");

        this.add("unlock_tip.epicskills.prodigy.arrow_tenacity", " Derrote 5 Esqueletos");
        this.add("unlock_tip.epicskills.prodigy.dodge_master", " Derrote 50 Esqueletos");

        this.add("unlock_tip.epicskills.prodigy.ender_step", " Derrote 50 Endermen");
        this.add("unlock_tip.epicskills.prodigy.ender_obscuris", " Derrote 250 Endermen");
        this.add("unlock_tip.epicskills.prodigy.time_travel", " Derrote 1000 Endermen");

        this.add("unlock_tip.epicskills.prodigy.buster_parade", " Derrote 10 Hollows");
        this.add("unlock_tip.epicskills.prodigy.perfect_bulwark", " Derrote 50 Guardiões");
        this.add("unlock_tip.epicskills.prodigy.avatar_of_might", " Derrote 50 Hollows");

        this.add("unlock_tip.epicskills.prodigy.precise_roll", " Corra 4 km");
        this.add("unlock_tip.epicskills.prodigy.bull_charge", " Derrote 20 Devastadores");

        this.add("unlock_tip.epicskills.prodigy.shulker_cloak", " Derrote 36 Shulkers");
        this.add("unlock_tip.epicskills.prodigy.soul_protection", " Derrote 60 Esqueletos Wither");
        this.add("unlock_tip.epicskills.prodigy.vampirize", " Derrote 20 Esqueletos Malignos");
        this.add("unlock_tip.epicskills.prodigy.dopamine", " Derrote 27 Lycanths");
        this.add("unlock_tip.epicskills.prodigy.lunatic_vivacity", " Derrote 9 Ghasts");
        this.add("unlock_tip.epicskills.prodigy.voodoo_magic", " Derrote 10 Invocadores");
        this.add("unlock_tip.epicskills.prodigy.manipulator", " Derrote 6 Saulomnks");

        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_you", " Derrote 100 Esqueletos Malignos");
        this.add("unlock_tip.epicskills.prodigy.all_eyes_on_me", " Derrote 100 Saulomnks");

        this.add("unlock_tip.epicskills.prodigy.inner_growth", " Elimine o Ender Dragon");
        this.add("unlock_tip.epicskills.prodigy.shadow_step", " Elimine o Wither");

        this.add("unlock_tip.epicskills.prodigy.critical_knowledge", " Derrote 50 Creepers e 150 mobs");
        this.add("unlock_tip.epicskills.prodigy.dancing_blade", " Derrote 5 Bruxas e 150 mobs");

        this.add("travelerstitles.tcrcore.Sanctum", "Reino dos Sonhos");
        this.add("travelerstitles.tcrcore.real", "A Realidade Irreal");
        this.add("travelerstitles.minecraft.overworld", "Overworld");
        this.add("travelerstitles.aether.the_aether", "Aether");
        this.add("travelerstitles.minecraft.the_nether", "Nether");
        this.add("travelerstitles.minecraft.the_nether.color", "750909");
        this.add("travelerstitles.minecraft.the_end", "The End");
        this.add("travelerstitles.minecraft.the_end.color", "4f219e");
        this.add("travelerstitles.pbf1.Sanctum_of_the_battle1", "Samsara Infinito");

        this.addBiome(TCRBiomes.AIR, "");
        this.addBiome(TCRBiomes.REAL, "");

        this.addQuest(TCRQuests.WAIT_RESONANCE_STONE_CHARGE, "Interlúdio", "Aguarde [%s] carregar", "A energia de [%s] foi esgotada e recarregar levará algum tempo. Antes que termine de carregar, vá fazer outra coisa!");
        this.addQuest(TCRQuests.PUT_DESERT_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_ABYSS_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_CURSED_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_FLAME_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_MECH_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_STORM_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_VOID_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.PUT_MONST_EYE_ON_ALTAR, "Interlúdio", "Coloque [%s] no altar", "[%s] foi recuperado. Apresse-se e devolva-o ao altar no corredor do Santuário!");
        this.addQuest(TCRQuests.BLESS_ON_THE_GODNESS_STATUE, "Interlúdio", "Reze na estátua da deusa", "[O Olho de Deus] foi recuperado. Ao usar a estátua da deusa no jardim, podemos ressoar com o Olho de Deus e ganhar o poder do Anjo.");

        this.addQuest(TCRQuests.TALK_TO_AINE_CLOUDLAND, "Interlúdio", "Fale com %s", "Quando você tocou o altar, foi atraído para um mundo misterioso. Talvez devêssemos chamá-lo de Terra das Nuvens. Vá falar com %s; talvez ela saiba que lugar é este.");

        // Prologue
        this.addQuest(TCRQuests.TALK_TO_AINE_0, "Prólogo", "Fale com %s", "Você concordou em vir para este mundo com %s, mas quando recobrou os sentidos, %s não estava em lugar nenhum. Apresse-se e procure por ela no Santuário! Você se lembra vagamente dela dizendo que tinha uma roupa nova para te dar.");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_0, "Prólogo", "Fale com ?", "Você concordou em vir para este mundo com %s, mas quando recobrou os sentidos, %s não estava em lugar nenhum. Que lugar é este? Tente falar com a mulher imponente no corredor do Santuário!");
        this.addQuest(TCRQuests.TALK_TO_FERRY_GIRL_0, "Prólogo", "Vá para [%s]", "Você ouviu a história deste mundo. Quando estiver pronto, vá até as docas para encontrar %s! Ela abrirá o caminho para [%s] para nós! Ela também parece ter algum tesouro para lhe dar.");
        this.addQuest(TCRQuests.TALK_TO_ORNN_0, "Prólogo", "Fale com %s", "Por sugestão de %s, vá ao arsenal para se equipar com %s! O equipamento adequado garantirá uma aventura tranquila!");

        // Dragon Taming Side Quest
        this.addQuest(TCRQuests.TAME_DRAGON, "Capítulo de Domar Dragões", "Crie o dragão até a idade adulta", "%s lhe deu um dragão. Agora, siga o método descrito em [%s] para criá-lo até a fase adulta! Você pode alimentar seu dragão com qualquer comida!\n\n§a[Recompensa da Missão]: §f[%s] [%s]");
        this.addQuest(TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL, "Capítulo de Domar Dragões", "Fale com %s", "Com seu cuidado atencioso, o dragão cresceu até a idade adulta. %s mencionou antes que nos daria um tesouro quando o dragão crescesse. Volte rápido para %s para ver!\n\n§a[Recompensa da Missão]: §f[%s] [%s]");

        // Main Quest · Desert Eye
        this.addQuest(TCRQuests.USE_LAND_RESONANCE_STONE, "Capítulo da Terra", "Use [%s]", "Você finalmente chegou ao lendário [%s]. Que aventuras o aguardam aqui? Apresse-se e use [%s]! Ele nos guiará para recuperar o primeiro olho.");
        this.addQuest(TCRQuests.GET_DESERT_EYE, "Capítulo da Terra", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!\n\n§a[Dica]: Explore a torre para encontrar a chave para invocar o chefe! Note que [%s§a] pode estar escondido dentro de blocos!\n\n§4[Nota]: Se você não conseguir concluir a missão após obtê-la, tente desativar quaisquer plugins que possam pegar itens automaticamente, então solte e pegue o item novamente!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_1, "Capítulo da Terra", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");
        // Chimera Side Quest
        this.addQuest(TCRQuests.BONE_CHIMERA_QUEST, "Capítulo das Armas", "Vá para [%s]", "[%s] parece ter marcado outro local. Talvez haja uma aventura inesperada lá. Apresse-se e confira!\n\n§a[Recompensa da Missão]: §f[%s§f]");
        this.addQuest(TCRQuests.TALK_TO_ORNN_1, "Capítulo das Armas", "Fale com %s", "Você obteve [%s] de [%s]. Que segredos ele guarda? Leve-o de volta ao Santuário e mostre a %s!");

        // Main Quest · Abyss Eye
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_2, "Capítulo do Oceano", "Fale com %s", "Após uma longa espera, %s terminou de carregar. Volte depressa para %s! Ela está esperando por você no Santuário.");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_OCEAN, "Capítulo do Oceano", "Vá para [%s]", "[%s] terminou de carregar. É hora de ir para [%s] e usá-lo! Ele nos guiará até a localização de [%s]!");
        this.addQuest(TCRQuests.USE_OCEAN_RESONANCE_STONE, "Capítulo do Oceano", "Use [%s]", "Você chegou a [%s]. Apresse-se e use [%s]! Ele nos guiará até a localização de [%s]! Que aventuras nos aguardam?");
        this.addQuest(TCRQuests.GET_OCEAN_EYE, "Capítulo do Oceano", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!\n\n§a[Dica]: [%s§a] pode estar escondido dentro de blocos!\n\n§4[Nota]: Se você não conseguir concluir a missão após obtê-la, tente desativar quaisquer plugins que possam pegar itens automaticamente, então solte e pegue o item novamente!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_3, "Capítulo do Oceano", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.\n\nDesta vez, também recuperamos [%s]. Que segredos ele guarda? Pergunte a ela enquanto estiver lá!");

        this.addQuest(TCRQuests.RIBBITS_QUEST, "Capítulo do Oceano", "Explore [%s]", "[%s] parece ter marcado outro local. Talvez haja uma aventura inesperada lá. Apresse-se e confira!\n\n§a[Recompensa da Missão]: §f[%s] [%s]\n\n§6[Recomendado concluir primeiro]");
        this.addQuest(TCRQuests.GIVE_AMETHYST_BLOCK_TO_RIBBITS, "Capítulo do Oceano", "Entregue [%s] para %s", "Parece que para aprender mais sobre [%s], você precisará fazer um acordo com o %s. Reúna 12 [%s] e volte até eles!\n\n§a[Recompensa da Missão]: §f[%s] [%s]\n\n§6[Recomendado concluir primeiro]");

        // Main Quest · Cursed Eye
        this.addQuest(TCRQuests.TALK_TO_AINE_ECHO, "Capítulo da Alma", "Fale com %s", "%s também não sabe a origem de [%s]. Vá perguntar a %s! Talvez os arquivos guardem registros, e com magia, você possa decifrar seus segredos.");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_4, "Capítulo da Alma", "Fale com %s", "%s terminou de decifrar [%s]. Agora, vá contar as novidades a %s!");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CURSED, "Capítulo da Alma", "Vá para [%s]", "Você obteve inesperadamente a localização espalhada de [%s] a partir de [%s]. Apresse-se para [%s] para recuperá-lo! [%s] nos guiará até sua localização.");
        this.addQuest(TCRQuests.USE_CURSED_RESONANCE_STONE, "Capítulo da Alma", "Use [%s]", "Você obteve inesperadamente a localização espalhada de [%s] a partir de [%s]. Apresse-se para [%s] para recuperá-lo! [%s] nos guiará até sua localização.");
        this.addQuest(TCRQuests.GET_CURSED_EYE, "Capítulo da Alma", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!\n\n§a[Dica]: §fExplore para encontrar pistas para invocar [%s]!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_5, "Capítulo da Alma", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");

        // Iron Magic Side Quest
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC, "Capítulo da Magia", "Fale com %s", "[%s] derrubou um misterioso %s. Talvez %s, que entende de magia, possa decifrar seus segredos!");
        this.addQuest(TCRQuests.TRY_TO_LEARN_MAGIC, "Capítulo da Magia", "Tente Infusão de Feitiço", "[%s] revelou a magia deste mundo. Agora, siga as instruções de %s e tente infundir o feitiço do pergaminho em sua arma!\n\n§a[Dica]: %s tem novas opções de diálogo!");
        this.addQuest(TCRQuests.TALK_TO_AINE_MAGIC_2, "Capítulo da Magia", "Fale com %s", "Você aprendeu a infundir feitiços em sua arma. Vá falar com %s! Ela te ensinará a usar magia de forma eficaz!");

        // Main Quest · Flame Eye
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_6, "Capítulo da Chama", "Fale com %s", "Após uma longa espera, %s terminou de carregar. Volte depressa para %s! Ela está esperando por você no Santuário.");
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_CORE, "Capítulo da Chama", "Vá para [%s]", "[%s] terminou de carregar. É hora de ir para [%s] e usá-lo! Ele nos guiará até a localização de [%s]!");
        this.addQuest(TCRQuests.USE_CORE_RESONANCE_STONE, "Capítulo da Chama", "Use [%s]", "Você chegou a [%s]. Apresse-se e use [%s]! Ele nos guiará até a localização de [%s]! Que aventuras nos aguardam?");
        this.addQuest(TCRQuests.GET_FLAME_EYE, "Capítulo da Chama", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!\n\n§a[Dica]: §f[%s] pode estar escondido em uma torre subterrânea. Você precisará de poder para romper a barreira de obsidiana!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_7, "Capítulo da Chama", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");

        this.addQuest(TCRQuests.TALK_TO_AINE_1, "Interlúdio", "Fale com %s", "Você recuperou metade dos Olhos de Deus em sua jornada. Fale com %s!");

        // Main Quest · Nether Chapter
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_8, "Capítulo do Nether", "Fale com %s", "Após uma longa espera, %s terminou de carregar. Volte depressa para %s! Ela está esperando por você no Santuário.");
        this.addQuest(TCRQuests.GO_TO_NETHER, "Capítulo do Nether", "Vá para [%s]", "Todos os Olhos de Deus espalhados no [%s] foram recuperados. A Pedra de Ressonância pode não funcionar no [%s]. Como %s disse, use [%s] para acender a moldura de obsidiana e abrir o %s!");
        this.addQuest(TCRQuests.USE_NETHER_RESONANCE_STONE, "Capítulo do Nether", "Use [%s]", "Você chegou a [%s]. Apresse-se e use [%s]! Ele nos guiará até a localização de [%s]! Que aventuras nos aguardam?");
        this.addQuest(TCRQuests.GET_MONST_EYE, "Capítulo do Nether", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!\n\n§a[Dica]: É recomendado equipar [%s]");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_9, "Capítulo do Nether", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");

        // Main Quest · Destruction Chapter
        this.addQuest(TCRQuests.GET_WITHER_EYE, "Capítulo da Destruição", "Recupere [%s]", "A pesquisa sobre os ecos do Cemitério de Navios avançou! Devemos realizar um ritual antigo para invocar o Anjo da Morte! Apresse-se e siga as instruções de %s para invocar [%s] e recuperar [%s]!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_10, "Capítulo da Destruição", "Fale com %s", "[%s] foi recuperado. Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");
        this.addQuest(TCRQuests.TALK_TO_AINE_SAMSARA, "Interlúdio", "Fale com %s", "%s nos disse que [%s] é a chave para abrir [%s]. Que segredos [%s] guarda? Apresse-se e pergunte a %s!");
        this.addQuest(TCRQuests.GO_TO_SAMSARA, "Capítulo do Ciclo", "Vá para [%s]", "Que segredos jazem dentro de [%s]? Apresse-se e siga as instruções de %s para ativar [%s]!");

        // Main Quest · Sky Chapter
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_11, "Capítulo do Céu", "Fale com %s", "Após uma longa espera, %s terminou de carregar. Volte depressa para %s! Ela está esperando por você no Santuário.");
        this.addQuest(TCRQuests.GO_TO_AETHER, "Capítulo do Céu", "Vá para [%s]", "Regue a estrutura construída por %s com a fonte da vida (%s) para abrir os portões do paraíso!");
        this.addQuest(TCRQuests.USE_AETHER_RESONANCE_STONE, "Capítulo do Céu", "Use [%s]", "Em sua jornada para coletar os Olhos de Deus, restam apenas os olhos do Anjo do Céu e do Anjo do Vazio a serem recuperados...");
        this.addQuest(TCRQuests.GET_STORM_EYE, "Capítulo do Céu", "Recupere [%s]", "[%s] marcou os locais espalhados de [%s]. Parta para recuperar [%s]!");
        this.addQuest(TCRQuests.TALK_TO_SKY_GOLEM, "Capítulo do Céu", "Fale com %s", "%s parece... não totalmente dissipado. Parece ter recuperado a consciência. Tente falar com %s!");
        this.addQuest(TCRQuests.TALK_TO_CHRONOS_12, "Capítulo do Céu", "Fale com %s", "[%s] foi recuperado, e sua história foi compreendida... Volte depressa ao Santuário para relatar a %s! Ela nos dirá o que fazer em seguida.");
        this.addQuest(TCRQuests.TALK_TO_AINE_2, "Capítulo do Céu", "Fale com %s", "[%s] foi recuperado, e sua história foi compreendida... Fale com %s.");

        // Main Quest · Final Chapter
        this.addQuest(TCRQuests.GO_TO_OVERWORLD_END, "Capítulo Final", "Vá para [%s]", "%s disse que o último Olho de Deus está localizado no [%s]. Precisamos encontrar o [%s] que leva ao [%s] no [%s] para abrir o portão para o [%s]!");
        this.addQuest(TCRQuests.USE_END_RESONANCE_STONE, "Capítulo Final", "Use [%s]", "Precisamos encontrar o [%s] que leva ao [%s] no [%s] para abrir o portão para o [%s]! A Pedra de Ressonância nos guiará até a localização de [%s].");
        this.addQuest(TCRQuests.GO_TO_THE_END, "Capítulo Final", "Vá para [%s]", "[%s] nos guiou até a localização de [%s]. Agora, encontre uma maneira de chegar ao [%s]!");
        this.addQuest(TCRQuests.GET_VOID_EYE, "Capítulo Final", "Recupere [%s]", "[%s] está no [%s]. Dê o seu melhor para recuperá-lo!");
        this.addQuest(TCRQuests.TALK_TO_ORNN_YAMATO, "Capítulo das Armas", "Fale com %s", "Você obteve inesperadamente [%s], mas seu poder parece não estar totalmente liberado. Apresse-se e fale com %s! Talvez haja uma maneira de restaurar seu poder!");

        this.addQuest(TCRQuests.TALK_TO_CHRONOS_END, "Capítulo Final", "Fale com %s", "[%s] foi recuperado... Você finalmente coletou todos os 8 Olhos de Deus. É hora de se preparar para o ritual de recriação.");
        this.addQuest(TCRQuests.KILL_MAD_CHRONOS, "Capítulo Final", "Derrote [???]", "Um monstro gigantesco apareceu. Dê o seu melhor para derrotá-Lo!");

        this.addQuest(TCRQuests.TALK_TO_AINE_GAME_CLEAR, "Pós-História", "Fale com %s", "Você realmente compreendeu [O Cofre dos Devaneios]?");

        this.addEffect(TCREffects.INVULNERABLE, "Invulnerável");
        this.addEffect(TCREffects.SOUL_INCINERATOR, "Fogo Espiritual");
        this.addEffect(PECEffects.SOUL_INCINERATOR, "Fogo Espiritual");

        this.add("epicfight.skill_slot.passive4", "Passiva 4");
        this.add("epicfight.skill_slot.passive5", "Passiva 5");

        StartScreenHandler.onGenerateEN(this);
        FirstEnterCloudlandScreenHandler.onGenerateEN(this);
        ResetGameProgressScreenHandler.onGenerateEN(this);

        this.add("item.domesticationinnovation.collar_tag.tcr_info", "Permite que encantamentos especiais sejam aplicados a pets.");
        this.addTCRItemInfo(net.blay09.mods.waystones.item.ModItems.warpStone, "Clique no botão §6[Pergaminho]§r no inventário para se teletransportar para waystones ativadas!");
        this.addTCRItemInfo(ItemRegistry.BOTTLE_OF_BLOOD.get(), "Produzido usando §c[Medula Sanguínea Cristalizada]§r, um drop de §d[Nehemoth]§r");
        this.addTCRItemInfo(EFNItem.DEEPDARK_HEART.get(), "Obtido ao derrotar o §2[Warden]§r ou a §2[Capitã Cornelia]§r");
        this.addTCRItemInfo(ModItems.CORAL_CHUNK.get(), "Obtido ao derrotar o §a[Colosso de Coral]§r na §dTerra das Nuvens do Leviathan§r");
        this.addTCRItemInfo(com.github.dodo.dodosmobs.init.ModItems.CHIERA_CLAW.get(), "Obtido ao derrotar a §e[Quimera de Ossos]§r");
        this.addTCRItemInfo(ModItems.CHITIN_CLAW.get(), "Obtido ao derrotar a §3[Guarda da Garra Gigante]§r na §3Terra das Nuvens de Scylla§r");
        this.addTCRItemInfo(Items.DRAGON_EGG, "Obtido ao derrotar o §d[Ender Dragon]§r no §dThe End§r");
        this.addTCRItemInfo(EpicSkillsItems.ABILIITY_STONE.get(), "Clique com o botão direito para usar e ganhar pontos de habilidade");

        this.add("itemGroup.tcr.items", "O Cofre dos Devaneios — Itens Principais");
        this.add("key.categories." + TCRCoreMod.MOD_ID, "O Cofre dos Devaneios — Principal");
        this.addKeyMapping(TCRKeyMappings.RIPTIDE, "Correnteza");
        this.addKeyMapping(TCRKeyMappings.SHOW_QUESTS, "Mostrar/Ocultar Tarefas");
        this.addKeyMapping(TCRKeyMappings.EXIT_SPECTATOR, "Sair do Modo Espectador");

        this.add("skill_tree.sword_soaring.unlock_tip", "Desbloqueado ao avançar na missão principal");
        this.add("unlock_tip.tcrcore.battleborn.water_avoid1", "Aprendido trocando com §6[Ribbit]§f usando §d[Bloco de Ametista]§f");
        this.add("unlock_tip.tcrcore.battleborn.fire_avoid", "Desbloqueado ao avançar na missão principal");
        this.add("unlock_tip.tcrcore.get_vatansever", "Desbloqueia após obter o §d[Vatansever]§f");
        this.addSkill("water_avoid", "Amuleto de Evasão na Água", "Permite respirar debaixo d'água!");
        this.addSkill("fire_avoid", "Amuleto de Evasão de Fogo", "Imunidade a dano de fogo!");
        this.addSkill("perfect_dodge", "Efeito de Esquiva", "Reproduz uma animação legal ao esquivar perfeitamente!");

        this.add(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "A Verdadeira Prova de Aventura");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), "A glória suprema!");
        this.add(TCRItems.DIVINE_FRAGMENT.get(), "Fragmento Divino");
        this.addItemUsageInfo(TCRItems.DIVINE_FRAGMENT.get(), "Ele registra a vontade dos deuses.");
        this.add(TCRItems.ABYSS_FRAGMENT.get(), "Eco do Canto Abissal");
        this.add(TCRItems.DESERT_FRAGMENT.get(), "Eco das Areias Esquecidas");
        this.add(TCRItems.ENDER_FRAGMENT.get(), "Fragmento do Verso Final");
        this.add(TCRItems.MECH_FRAGMENT.get(), "Restos da Engrenagem Perpétua");
        this.add(TCRItems.NETHERITE_FRAGMENT.get(), "Brasas do Coração Derretido Extinto");
        this.add(TCRItems.FLAME_FRAGMENT.get(), "Eco da Primeira Chama");
        this.add(TCRItems.STORM_FRAGMENT.get(), "Fragmento do Rugido Estrondoso");
        this.add(TCRItems.SOUL_FRAGMENT.get(), "Poeira da Prisão de Ossos Amaldiçoada");
        this.add(TCRItems.STONE_OF_REINCARNATION.get(), "Pedra da Reencarnação");
        this.addItemUsageInfo(TCRItems.STONE_OF_REINCARNATION.get(), "Após o uso, você retornará ao início da jornada.");
        this.add(TCRItems.WITHER_SOUL_STONE.get(), "Pedra de Alma do Wither");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE.get(), "Parece ter perdido sua magia por enquanto, apenas uma pedra única. Descubra como despertá-la!");
        this.add(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Pedra de Alma do Wither");
        this.addItemUsageInfo(TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), "Pode abrir um portal para o §6[Samsara Infinito]§r.");
        this.add(TCRItems.MAGIC_BOTTLE.get(), "Frasco Mágico");
        this.addItemUsageInfo(TCRItems.MAGIC_BOTTLE.get(), "Restaura uma certa porcentagem de mana quando usado. Uma vez esgotado, deve ser usado no Santuário para reabastecer MP.");
        this.add(TCRItems.MYSTERIOUS_WEAPONS.get(), "Pergaminho de Armas Misteriosas");
        this.addItemUsageInfo(TCRItems.MYSTERIOUS_WEAPONS.get(), "Parece registrar todo tipo de armas ao redor do mundo. Você deveria mostrá-lo a alguém que entenda dessas coisas.");
        this.add(TCRItems.NECROMANCY_SCROLL.get(), "Pergaminho de Necromancia");
        this.addItemUsageInfo(TCRItems.NECROMANCY_SCROLL.get(), "Parece guardar os segredos da magia. Mostre a alguém que os entenda.");
        this.add(TCRItems.DRAGON_FLUTE.get(), "Flauta de Dragão");
        this.addItemUsageInfo(TCRItems.DRAGON_FLUTE.get(), "Clique com o botão direito para capturar um dragão; clique novamente para soltá-lo.");
        this.add(TCRItems.RESONANCE_STONE.get(), "Pedra de Ressonância");
        this.add(TCRItems.LAND_RESONANCE_STONE.get(), "Pedra de Ressonância da Terra");
        this.add(TCRItems.OCEAN_RESONANCE_STONE.get(), "Pedra de Ressonância do Oceano");
        this.add(TCRItems.CURSED_RESONANCE_STONE.get(), "Pedra de Ressonância Amaldiçoada");
        this.add(TCRItems.CORE_RESONANCE_STONE.get(), "Pedra de Ressonância do Núcleo");
        this.add(TCRItems.NETHER_RESONANCE_STONE.get(), "Pedra de Ressonância do Nether");
        this.add(TCRItems.SKY_RESONANCE_STONE.get(), "Pedra de Ressonância do Aether");
        this.add(TCRItems.END_RESONANCE_STONE.get(), "Pedra de Ressonância do End");
        this.add(TCRItems.CORE_FLINT.get(), "Sílex do Núcleo");
        this.addItemUsageInfo(TCRItems.CORE_FLINT.get(), "Use em uma Moldura de Obsidiana para abrir um Portal do Nether.");
        this.add(TCRItems.PROOF_OF_ADVENTURE.get(), "Prova de Aventura");
        this.addItemUsageInfo(TCRItems.PROOF_OF_ADVENTURE.get(), "Forjada a partir dos nomes de todos os inimigos derrotados pela sua lâmina. Sua jornada chegou ao fim, e sua coragem agora é uma lenda.");
        this.add(TCRItems.DUAL_BOKKEN.get(), "Bokken");
        this.addItemUsageInfo(TCRItems.DUAL_BOKKEN.get(), "Posso ter falta de habilidade, mas não me falta dedicação, e a você, falta dedicação?");
        this.add(TCRItems.VOID_CORE.get(), "Essência do Vazio");
        this.addItemUsageInfo(TCRItems.VOID_CORE.get(), "Derrubado pelo [Guardião do End] quando derrotado");
        this.add(TCRItems.ABYSS_CORE.get(), "Essência do Abismo");
        this.addItemUsageInfo(TCRItems.ABYSS_CORE.get(), "Derrubado por [The Leviathan] quando derrotado");
        this.add(TCRItems.ARTIFACT_TICKET.get(), "Essência de Artefato");
        this.addItemUsageInfo(TCRItems.ARTIFACT_TICKET.get(), "Obtido de certas missões no livro de missões. Pode ser usado para refinar artefatos com a §3[Garota da Barca]§r no §d[Porto Sagrado]§r");
        this.add(TCRItems.RARE_ARTIFACT_TICKET.get(), "Essência de Artefato Dourada");
        this.addItemUsageInfo(TCRItems.RARE_ARTIFACT_TICKET.get(), "Obtido de certas missões no livro de missões. Pode ser usado para refinar artefatos raros com a §3[Garota da Barca]§r no §d[Porto Sagrado]§r");
        this.add(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "Fragmento de Oráculo");
        this.addItemUsageInfo(TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), "§c§kI'm Your...");

        this.addInfo("only_work_on_dragon", "Funciona apenas em Dragões!");
        this.addInfo("creative_may_lost_progress", "Aviso: Derrotar o chefe no Modo Criativo não progredirá no jogo!");
        this.addInfo("exit_spectator_in_pbf1", "Pressione [%s] para sair do Modo Espectador e retornar ao Santuário.");
        this.addInfo("cataclysm_humanoid_drop_desc", "Derrubado em [%s] ao derrotar [%s]");
        this.addInfo("can_not_use_scroll_directly", "Por favor, encante o feitiço em uma arma para usá-lo!!");
        this.addInfo("quest_updated","Missão Atual Atualizada!");
        this.addInfo("i18n_pack", "Pacote I18n, obrigado a todos os tradutores!");
        this.addInfo("can_not_dodge", "Ataques de chefe não podem ser esquivados!");
        this.addInfo("can_not_guard", "Ataques de chefe não podem ser bloqueados!");
        this.addInfo("pec_weapon_lock", "Habilidade da arma bloqueada! Derrote [%s] em [%s] para desbloquear!");
        this.addInfo("resonance_stone_usage", "Pode ressoar com a localização do selo do Anjo");
        this.addInfo("resonance_search_failed", "[ERRO]: Falha ao Ressoar! Por favor, tente novamente após reiniciar o jogo! [%s]");
        this.addInfo("yamato_skill_lock", "[%s] estão bloqueadas. Requer livro de encantamento especial para desbloquear!");
        this.addInfo("congratulation", "Parabéns!");
        this.addInfo("open_backpack_tutorial", "Pressione [%s] para ver a Mochila");
        this.addInfo("unlock_new_ftb_page_title", "§6Novo Capítulo Desbloqueado!");
        this.addInfo("unlock_new_ftb_page_subtitle", "§aPressione [%s§a] para visualizar");
        this.addInfo("resonance_stone_working", "[%s] Ressoando... Por favor, aguarde pacientemente...");
        this.addInfo("containing_dragon", "Tipo: [%s]");
        this.addInfo("dragon_owner", "Dono: [%s]");
        this.addInfo("quest_map_mark", "Posição da Missão");
        this.addInfo("map_pos_marked_press_to_open", "Localização marcada, pressione [%s] para ver o mapa.");
        this.addInfo("press_to_open_skill_tree", "Pressione %s para abrir a Árvore de Habilidades");
        this.addInfo("press_to_show_quest_ui", "Pressione %s para ver as missões");
        this.addInfo("please_use_custom_flint_and_steel", "Por favor, use [%s] para gerar o portal");
        this.addInfo("exit_quest_screen", "Sair");
        this.addInfo("start_tracking_quest", "Selecionar");
        this.addInfo("cancel_tracking_quest", "Parar");
        this.addInfo("no_quest", "Sem Missão");
        this.addInfo("tracking_quest", " [☆Acompanhando]");
        this.addInfo("require_item_to_wake", "Requer [%s]...");
        this.addInfo("weapon_no_interact", "Não pode interagir! Por favor, pressione [%s] no modo vanilla ou outro item.");
        this.addInfo("tudigong_gift", "[Presente]");
        this.addInfo("tudigong_gift_get", "§6[TuDi]§f: Envelheci e me tornei incapaz, então passarei este tesouro para você!");
        this.addInfo("need_to_unlock_waystone", "Algumas waystones permanecem inativas!");
        this.addInfo("nether_unlock", "Nether Desbloqueado!");
        this.addInfo("end_unlock", "End Desbloqueado!");
        this.addInfo("nothing_happen_after_bless", "§dNada aconteceu... O [Olho] foi usado.");
        this.addInfo("dim_max_4_players", "§6A Terra das Nuvens só pode conter 4 jogadores!");
        this.addInfo("dim_max_players", "§6Capacidade máxima atingida");
        this.addInfo("can_not_enter_before_finish", "§6Você não está destinado a entrar nesta Terra das Nuvens.");
        this.addInfo("can_not_do_this_too_early", "§6Você não está destinado a fazer isso.");
        this.addInfo("captain_start_heal", "§cA Cornelia começou a curar! Aumente o seu dano!");
        this.addInfo("illegal_item_tip", "§cItem Ilegal!");
        this.addInfo("illegal_item_tip2", "§6No momento, você não está destinado a usar este item.");

        this.addInfo("shift_to_pic", "Ataque pressionando Shift para pegar");
        this.addInfo("no_place_to_ship", "Sem espaço para o navio!");
        this.addInfo("boss_killed_ready_return", "§6O Chefe foi derrotado! Interação com blocos desbloqueada!");
        this.addInfo("click_to_return", "§a[Clique para retornar]");
        this.addInfo("cs_warning", "§c§l AVISO! O Compute Shader está inativo agora! Você pode habilitá-lo na configuração do Epic Fight para ter uma experiência melhor!");
        this.addInfo("wraithon_start_tip", "§d[Wraithon]: §6Forasteiro, sua jornada termina aqui!");
        this.addInfo("wraithon_end_tip", "§d[Wraithon]: §6Isso... é impossível...");
        this.addInfo("dim_block_no_interact", "§cO Chefe ainda não foi derrotado! Não pode interagir com os blocos da Terra das Nuvens ainda!");
        this.addInfo("altar_dim_info", "Info da Terra das Nuvens:");
        this.addInfo("related_loot", "Monstro: [%s] | Saque Relacionado: [%s]");
        this.add(TCRBlocks.CURSED_ALTAR_BLOCK.get(), "Altar Amaldiçoado");
        this.add(TCRBlocks.ABYSS_ALTAR_BLOCK.get(), "Altar do Abismo");
        this.add(TCRBlocks.STORM_ALTAR_BLOCK.get(), "Altar da Tempestade");
        this.add(TCRBlocks.FLAME_ALTAR_BLOCK.get(), "Altar da Chama");
        this.add(TCRBlocks.DESERT_ALTAR_BLOCK.get(), "Altar do Deserto");
        this.add(TCRBlocks.MONST_ALTAR_BLOCK.get(), "Altar Monstruoso");
        this.add(TCRBlocks.VOID_ALTAR_BLOCK.get(), "Altar do Vazio");
        this.add(TCRBlocks.MECH_ALTAR_BLOCK.get(), "Altar Mecânico");

        this.addInfo("attack_to_restart", "§cAtaque para reiniciar");
        this.addInfo("after_heal_stop_attack", "§6Pare de atacar para limpar a agressão.");
        this.addInfo("cloud_follow_me", "§6[Nuvem Mágica]: §fOi, me siga!");
        this.addInfo("dim_demending", "§6Reconstruindo... Aguarde [%d§6]s");
        this.addInfo("to_be_continue2", "[P1nero]: §6Obrigado por jogar! Mais chefes estão sendo feitos, fique ligado para mais!");
        this.addInfo("second_after_boss_die_left", "Retornando ao Overworld em %d segundos");
        this.addInfo("press_to_open_battle_mode", "§cPressione [%s] para habilitar o Modo de Batalha!§r");
        this.addInfo("unlock_new_dim_girl", "§6Novas opções desbloqueadas na Garota da Barca!§r");
        this.addInfo("unlock_new_dim", "§c[Nether]§d[End]§6 desbloqueado!§r");
        this.addInfo("iron_golem_name", "Guardião da Ilha do Céu");

        this.addInfo("get_mimic_invite", "[%s]: Ser de outro mundo, eu sabia que estava certo sobre você! Pegue este §6[%s§6]§f!");
        this.addInfo("kill_arterius", "[%s]: Forasteiro, você é de fato formidável! Parece que a profecia é verdadeira! Então, concederei essas partes de [%s] a você!");

        this.addInfo("finish_all_eye", "§dTodos os altares estão acesos!§r");
        this.addInfo("time_to_altar", "As brasas espalhadas foram encontradas. É hora de voltar e acender os altares...");
        this.addInfo("time_to_ask_godness_statue", "§d*Este item pode ser usado na estátua da Deusa.");
        this.addInfo("time_to_end", "Todos os altares estão acesos. É hora de encontrar A Guardiã do Santuário para realizar o ritual...");

        this.addInfo("reset_when_no_player", "Se nenhum jogador permanecer na Terra das Nuvens, sair por muito tempo resetará a Terra das Nuvens!");
        this.addInfo("on_full_set", "Efeito de Conjunto Completo");
        this.addInfo("unlock_new_ftb_page", "Uma nova página de missões foi desbloqueada. Por favor, abra o §6[Livro de Missões]§r para verificar");
        this.addInfo("unlock_new_skill_page", "Uma nova interface do livro de habilidades foi desbloqueada!");
        this.addInfo("unlock_new_skill", "Desbloqueou [%s]!");
        this.addInfo("unlock_new_skill_sub", "Pressione §6[%s]§r para verificar a nova habilidade");
        this.addInfo("hit_barrier", "Esta área ainda não está disponível. Volte mais tarde!");

        this.addInfo("death_info", "§6Quando os inimigos forem muito poderosos, tente combinar habilidades diferentes!");
        this.addInfo("enter_dimension_tip", "Clique com o botão direito no núcleo do altar para entrar na Terra das Nuvens");
        this.addInfo("use_true_eye_tip", "Por favor, use [%s] para clicar com o botão direito no núcleo do altar");

        this.addInfo("add_item_tip", "Novo item obtido: %s × %d!");
        this.addInfo("skill_point_lack", "Esta habilidade requer %d pontos de habilidade para ser desbloqueada");
        this.addInfo("press_to_open_portal_screen2", "Clique no §6[Pergaminho]§r no inventário para retornar a pedras ativadas anteriormente!");
        this.addInfo("press_to_show_progress", "Pressione §6[L]§f para ver o guia!");
        this.addInfo("press_to_skill_tree", "EXP suficiente disponível. Pressione §6[K]§f para alocar pontos de habilidade!");
        this.addInfo("lock_tutorial", "§6[[%s§6]§r para travar a mira");
        this.addInfo("lock_tutorial_sub", "§cMova o mouse para mudar de alvo!");
        this.addInfo("riptide_tutorial", "§6[[%s§6]§f para §bCorrenteza");
        this.addInfo("dodge_tutorial", "§6[[%s§6]§f para esquivar");
        this.addInfo("weapon_innate_tutorial", "§6[[%s§6]§f para usar a habilidade da sua arma");
        this.addInfo("weapon_innate_charge_tutorial", "§6[Esquiva Perfeita]§c ou §6[Aparo Perfeito]§c podem carregar certas armas!");
        this.addInfo("perfect_dodge_tutorial", "§cEsquive no tempo certo para uma Esquiva Perfeita!");
        this.addInfo("hurt_damage", "Causou [ %s ] de dano!");
        this.addInfo("parry_tutorial", "§6[[%s§6]§f para bloquear");
        this.addInfo("perfect_parry_tutorial", "§cBloqueie no tempo certo para um Aparo Perfeito!");
        this.addInfo("you_pass", "§6Você passou!!");

        this.addInfo("press_to_open_map", "§6[M]§f para ver o mapa");

        this.addInfo("godness_statue_pos", "Estátua da Deusa");
        this.addInfo("eye_pos_mark", "Localização de [%s]: [%s]");

        this.addAdvancement(TCRCoreMod.MOD_ID, "O Cofre dos Devaneios", "Onde o sonho começa.");
        this.addAdvancement("unlock_weapon_armor_book", "Armas Misteriosas", "");
        this.addAdvancement("unlock_magic_and_boss", "Pergaminho de Necromancia", "");
        this.addAdvancement("unlock_epic_boss", "Arena Primordial", "");

        this.add(TCREntities.CHRONOS_SOL.get(), "Chronos Sol");
        this.add(TCREntities.FERRY_GIRL.get(), "Garota da Barca");
        this.add(TCREntities.ORNN.get(), "Velho Ornn");
        this.add(TCREntities.AINE.get(), "Aine");
        this.add(TCREntities.TUTORIAL_GOLEM.get(), "Golem de Treinamento");
        this.add(TCREntities.TCR_MIMIC.get(), "Eu?");

        this.add(TCRBossEntities.LEVIATHAN_HUMANOID.get(), "Thalassa Mare");
        this.add(TCRBossEntities.HARBINGER_HUMANOID.get(), "Letum Quietus");
        this.add(TCRBossEntities.ENDER_GUARDIAN_HUMANOID.get(), "Nihil Vacuum");
        this.add(TCRBossEntities.IGNIS_HUMANOID.get(), "Ignis Ardens");
        this.add(TCRBossEntities.IGNIS_SHIELD.get(), "Escudo de Ignis Ardens");
        this.add(TCRBossEntities.SCYLLA_HUMANOID.get(), "Caelum Altum");
        this.add(TCRBossEntities.ANCIENT_REMNANT_HUMANOID.get(), "Terra Montis");
        this.add(TCRBossEntities.MALEDICTUS_HUMANOID.get(), "Anima Essentia");
        this.add(TCRBossEntities.NETHERITE_HUMANOID.get(), "Infernus Abyssus");

        this.addDialogAnswer(EntityType.IRON_GOLEM, 0, "Cara, você está pronto?");
        this.addDialogOption(EntityType.IRON_GOLEM, 0, "Sim");
        this.addDialogOption(EntityType.IRON_GOLEM, 1, "Espere");
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
        this.addDialogOption(EntityType.VILLAGER, -3, "[Tentar Esmeraldas?]");
        this.addDialogOption(EntityType.VILLAGER, -2, "[Este aldeão não demonstra interesse...]");
        this.addDialogOption(EntityType.VILLAGER, -1, "[Aceitar]");
        this.addDialogOption(EntityType.VILLAGER, 0, "[???]");
        this.addDialogOption(EntityType.VILLAGER, 1, "[Parece que os moradores locais estão fortemente corrompidos!]");
        this.addDialogOption(EntityType.VILLAGER, 2, "[O que você está murmurando?]");
        this.addDialogOption(EntityType.VILLAGER, 3, "[Por que não consigo entender o idioma dos aldeões...]");

        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -4, "§aAceitar [%s§a]");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -3, "Retornar");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -2, "Encerrar Conversa");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), -1, "Continuar");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 0, "Viajante, a sua aventura está indo bem?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 0, "Sobre este mundo");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 1, "Mil anos atrás, o 'nosso' mundo foi atingido por uma calamidade desconhecida vinda além dos céus, e a Maré Negra desceu sobre o reino. Diante da Maré Negra sem fim, 'nós' fomos impotentes. Afetados pela Maré Negra, 'nossas' memórias desapareceram gradualmente... Um a um, Eles foram devorados pela Maré Negra, e suas almas foram seladas pelo mundo afora. Quando 'nós' percebemos que 'nossas' memórias estavam recuando como a maré, 'nós' usamos magia para inscrever os Manuscritos do Mar Morto, para nos lembrar de que um salvador eventualmente retornaria de além dos céus, uniria o 'nosso' poder, alcançaria a recriação e baniria a Maré Negra!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 2, "Eu fui a menos corroída pela Maré Negra. Eles usaram suas forças restantes para selar a mim e a este Santuário abaixo de nós no Mar Primordial, para retardar a erosão da Maré Negra e aguardar o retorno do salvador. Quanto a quem é o salvador, ou por que eles possuem o poder de nos salvar, 'nós' não temos como saber. Mas diante da Maré Negra, 'nós' só podemos acreditar nesta antiga memória e acreditar na chegada da recriação...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 1, "Sobre a %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 3, "Viajante, você está se referindo à bruxa que se estabeleceu aqui? Ela parece possuir poder além deste mundo, muito parecido com você. Eu acredito na profecia; acredito que você e a bruxa podem salvar o Santuário. Tudo aqui estará aberto a vocês dois!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 2, "Sobre a %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 4, "Desde a invasão da Maré Negra, ela tem montado guarda no porto por mil anos, a única ligação entre este lugar e o mundo. Ela é uma marionete que 'nós' criamos. À medida que 'nossas' memórias desbotavam, o poder dela também diminuía.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 3, "Sobre o %s");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 5, "Ele é um semideus do Overworld, mestre da forja e do artesanato. Todas as armas e armaduras divinas usadas pelos Anjos foram forjadas por suas mãos.");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 6, "Oh, salvador profetizado, eu aguardava sua chegada! Só você pode recuperar os Olhos dos Deuses e restaurar a antiga glória do mundo!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 4, "Quem é você?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 7, "Eu sou uma dos dez grandes Anjos do mundo, chamada %s, e eu governo o tempo.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 5, "Anjo?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 8, "No amanhecer da criação, dez Anjos nasceram no Santuário, governando todas as coisas no mundo. Eu sou uma deles. 'Nós' frequentemente nos reuníamos para discutir assuntos importantes. Quando uma situação exigia a 'nossa' intervenção, 'nós' escolhíamos um entre 'nós' para ir.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 6, "§aSobre o Próximo Passo");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 9, "Viajante, você deve recuperar suas âncoras — os Olhos dos Deuses — e retornar as almas dos Anjos aos altares. Então, oferecerei a minha própria alma, e poderemos realizar a recriação registrada nos Manuscritos do Mar Morto!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 10, "O Mar Primordial nos protege da aura do mundo exterior. Eu não posso sentir as localizações deles daqui. No entanto, esta §6Pedra de Ressonância§f pode ajudá-lo a encontrar onde os Olhos dos Deuses estão espalhados no mundo exterior. Eu te dou esta Pedra de Ressonância. Quando você chegar ao Overworld, ela o guiará às localizações dos Anjos. Lembre-se, a Pedra de Ressonância só pode ser usada uma vez por estágio! Depois que você recuperar um Olho de Deus, posso usá-lo para forjar uma nova Pedra de Ressonância.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 11, "Viajante, primeiro você pode ir ao §6[Arsenal]§f para conseguir uma arma adequada com o %s. Quando estiver pronto, vá para o porto encontrar a %s. Ela o levará ao ponto de partida da sua jornada.");

        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 12, "Viajante, a sua jornada para encontrar [%s] foi bem-sucedida?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 7, "§aEu recuperei [%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 13, "Verdadeiramente, você é o salvador profetizado! Por favor, coloque o Olho de Deus no altar!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 14, "Você também pode rezar na estátua da deusa com o Olho de Deus. Uma porção do poder do Anjo contido no Olho será compartilhada com você por meio da estátua.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 15, "A Pedra de Ressonância ainda precisa de tempo para recarregar. Por favor, aguarde, viajante!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 16, "A Pedra de Ressonância terminou de recarregar. Siga em frente, viajante!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 17, "Muito bem, viajante! Permita-me recarregar a Pedra de Ressonância. Por favor, descanse um pouco!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 8, "§aSobre [%s§a]");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 18, "[%s]? Eu não consigo encontrar nenhuma memória relacionada. Por favor, consulte a bruxa e procure nos arquivos com o poder dela!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 19, "Viajante, a Pedra de Ressonância ainda não terminou de recarregar. Por favor, seja paciente.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 20, "Isso é... %s?!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 21, "Sendo assim, esta memória pode restaurar a magia da Pedra de Ressonância prematuramente. Vou infundir este eco na Pedra de Ressonância. Você deve recuperar sua centelha!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 22, "Por favor, coloque o Olho de Deus no altar! O Anjo que governa o submundo acabou, no fim, entrando no submundo...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 23, "Por favor, coloque o Olho de Deus no altar! Você enterrou o código da ressurreição nas cinzas, derretendo a fechadura enferrujada em um rio dourado fluindo...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 24, "Viajante, todos os Olhos de Deus espalhados no Overworld foram recuperados. A Pedra de Ressonância pode não funcionar mais lá. Se ela não responder, talvez seja hora de tentar outras dimensões.");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 9, "Outras dimensões?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 25, "No amanhecer da criação, o mundo foi dividido em quatro dimensões: o Overworld, o Nether, o Aether e o The End. Cada um era governado por seu respectivo Anjo.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 26, "Após a invasão da Maré Negra, suas almas permaneceram lá para sempre. Eu usei o poder de [%s] para forjar [%s] para você. Use [%s] para acender a moldura de obsidiana e abrir o portão para o Nether!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 27, "Viajante, quando estiver pronto, vá para o Nether e use a Pedra de Ressonância! Antes de ir, aceite minha bênção para protegê-lo das chamas!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 28, "Viajante, você chegou na hora certa! A bruxa progrediu na pesquisa dos ecos do Cemitério de Navios! Precisamos realizar o ritual antigo para invocar o Anjo da Morte de volta!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 10, "Anjo da Morte?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 29, "O Anjo que governa a morte — %s. De acordo com informações deixadas pelo Anjo da Alma no Cemitério de Navios, sua alma está aprisionada no Rio Estige!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 30, "Devemos realizar o ritual de invocação para resgatá-los do Estige!");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 11, "Ritual de invocação?");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 31, "Viajante, você viu [%s] no Nether? Organize [%s] em forma de T, então coloque três [%s] no topo para invocá-los!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 32, "[%s]? Esta é a essência condensada do poder do Anjo da Morte. Pode abrir o portão para [%s]!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 33, "Mas me falta poder para abrir [%s] para você. Talvez você precise da ajuda da bruxa para ativar sua magia e abrir o portão para o Samsara.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 34, "Enquanto a Pedra de Ressonância recarrega, vá encontrar a %s e examinar [%s]!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 35, "*Tosse*, viajante, em seguida... *tosse*...");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 12, "%s, o que houve?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 13, "Você parece péssima");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 36, "*Tosse*, desde que os Olhos retornaram aos altares, sinto meu poder diminuindo... Talvez a maldição da Maré Negra esteja finalmente me alcançando...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 37, "Em sua jornada para retornar os Olhos, apenas os do Anjo do Céu e do Anjo do Vazio continuam sem retornar... Com o retorno do Olho do Anjo da Morte, os portões do Paraíso se abriram.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 38, "A lenda diz que, após a morte, os virtuosos ascendem ao Paraíso, os perversos descem ao Nether, e aqueles nem bons nem maus desaparecem no Vazio. O Anjo da Morte e o Anjo da Alma governam o Estige, organizando os destinos dos que partiram.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 39, "Após a Maré Negra, o equilíbrio entre yin e yang foi quebrado. Almas infindáveis invadem o Overworld, incapazes de entrar no ciclo de reencarnação.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 40, "Siga em frente, antes que meu poder desvaneça completamente. §6Regue a moldura construída de pedra luminosa com a fonte da vida para abrir os portões do Paraíso! §fQuando entrar no Aether, use a Pedra de Ressonância para encontrar a localização de [%s].");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 41, "O Aether é perigoso. Concedo-lhe a bênção de §6[Voar com a Espada]§f!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 42, "*Tosse*, viajante, você chegou. *Tosse*... A sua jornada para encontrar [%s] foi bem-sucedida?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 14, "%s,");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 15, "Eu sei quem eu sou");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 43, "...(Você conta a %s sobre o que aconteceu no %s)");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 44, "Então é isso... Então o salvador nos Manuscritos do Mar Morto é o Anjo Puro... Somente a pureza pode varrer toda a escuridão...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 45, "Não é à toa que sempre senti que você compartilhava nossa essência, viajante, e podia atravessar os três reinos.");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 46, "*Tosse*, agora resta apenas o passo final... iniciar o ritual... O Olho do Anjo do Vazio está no The End...");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 47, "Me falta poder para recarregar a Pedra de Ressonância. Sua energia restante é suficiente apenas para guiá-lo até [%s]... Pelo resto da jornada, por favor, tome cuidado, viajante!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 48, "......");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 16, "%s?");
        this.addDialogOption(TCREntities.CHRONOS_SOL.get(), 17, "%s!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 49, "Purus Absolutus.. Purus Absolutus...!");
        this.addDialogAnswer(TCREntities.CHRONOS_SOL.get(), 50, "");

        this.addDialogOption(TCREntities.AINE.get(), -4, "§6Infusão de Feitiço");
        this.addDialogOption(TCREntities.AINE.get(), -3, "§6Troca de Feitiços");
        this.addDialogOption(TCREntities.AINE.get(), -2, "Encerrar Conversa");
        this.addDialogOption(TCREntities.AINE.get(), -1, "Continuar");
        this.addDialogAnswer(TCREntities.AINE.get(), -2, "Qual escola de magia você gostaria de aprender?");
        this.addDialogAnswer(TCREntities.AINE.get(), -1, "%s, você chegou! O que você gostaria de fazer hoje?");
        this.addDialogAnswer(TCREntities.AINE.get(), 0, "%s, você chegou! Estou lendo os arquivos deste mundo");
        this.addDialogOption(TCREntities.AINE.get(), 0, "Sobre a %s");
        this.addDialogAnswer(TCREntities.AINE.get(), 1, "Você já falou com aquela mulher? Ela parece ser a líder aqui. Parece que para resolver os problemas deste lugar, teremos que seguir as instruções dela por enquanto.");
        this.addDialogOption(TCREntities.AINE.get(), 1, "Sobre este mundo");
        this.addDialogAnswer(TCREntities.AINE.get(), 2, "Você ouviu a história daquela mulher, não é? Mas se é verdade ou mentira, só saberemos quando realmente começarmos a nossa jornada. Lembre-se do que combinamos antes de vir para cá: você tem que contar com sua própria força para recuperar suas memórias perdidas! Continue assim, estarei sempre ao seu lado, seu forte apoio!");
        this.addDialogOption(TCREntities.AINE.get(), 2, "§aSobre a Roupa");
        this.addDialogAnswer(TCREntities.AINE.get(), 3, "Olha! Minha roupa nova! Linda, né? Vindo para este mundo, devíamos arrumar um conjunto novo de roupas também! Aqui, você deveria vestir o seu também!");
        this.addDialogOption(TCREntities.AINE.get(), 3, "§aAceitar");
        this.addDialogAnswer(TCREntities.AINE.get(), 4, "%s, precisa de algo?");
        this.addDialogOption(TCREntities.AINE.get(), 5, "§aSobre a Terra das Nuvens");
        this.addDialogAnswer(TCREntities.AINE.get(), 5, "Terra das Nuvens? Deixe-me verificar...");
        this.addDialogAnswer(TCREntities.AINE.get(), 6, "Hmm... Com base nas informações dos arquivos e em minha experiência, a Terra das Nuvens na qual você entrou após tocar o altar provavelmente é o resultado mesclado dos mundos mentais originais dos Anjos, erodidos pela Maré Negra.");
        this.addDialogOption(TCREntities.AINE.get(), 6, "Mundos mentais?");
        this.addDialogAnswer(TCREntities.AINE.get(), 7, "Sim, talvez nas profundezas da Terra das Nuvens, você possa encontrar reflexos dos Anjos e obter seus poderes!");
        this.addDialogAnswer(TCREntities.AINE.get(), 8, "Tente explorar a Terra das Nuvens! Você pode encontrar algo importante, talvez até materiais valiosos de essência de memória!");
        this.addDialogOption(TCREntities.AINE.get(), 7, "§aSobre [%s§a]");
        this.addDialogAnswer(TCREntities.AINE.get(), 9, "Isso é... uma essência de memória especial. Parece registrar as memórias de um grupo, não apenas de uma única vida. Vamos ver quais segredos ela guarda!");
        this.addDialogAnswer(TCREntities.AINE.get(), 10, "!!");
        this.addDialogAnswer(TCREntities.AINE.get(), 11, "Ele registra a história de aventura da Capitã Cornelia. E a partir da memória, depois que o Anjo da Alma %s foi erodido pela Maré Negra, ele possuiu a Capitã Cornelia! Apresse-se e mostre essa memória decifrada a %s!");
        this.addDialogOption(TCREntities.AINE.get(), 8, "%s? %s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 12, "O Anjo da Alma, %s, governa o mundo dos mortos. A frota da Capitã Cornelia era uma frota oceânica renomada mundialmente. Amaldiçoados pela Maré Negra, eles ficaram presos para sempre naquele labirinto de gelo junto com o [%s]!");
        this.addDialogAnswer(TCREntities.AINE.get(), 13, "[%s]? Deixe-me ver, outra memória especial...");
        this.addDialogAnswer(TCREntities.AINE.get(), 14, "Heh heh, parece que o Anjo da Alma, que governa o submundo, também dominava a magia dos vivos!");
        this.addDialogAnswer(TCREntities.AINE.get(), 15, "[%s] nos dá duas informações. §6Primeiro, retrata a maioria dos monstros no mundo erodidos pela Maré Negra!");
        this.addDialogAnswer(TCREntities.AINE.get(), 16, "§6Segundo, registra a magia deste mundo! Heh, a magia aqui é bastante semelhante à magia que eu conheço!");
        this.addDialogAnswer(TCREntities.AINE.get(), 17, "O pergaminho retrata essências de feitiço e seus feitiços correspondentes. §6Traga-me a essência do feitiço descrita no compêndio, e eu a converterei em um pergaminho de feitiço correspondente para você.");
        this.addDialogAnswer(TCREntities.AINE.get(), 18, "§6Dê-me a sua arma, e eu a encantarei com o feitiço registrado no pergaminho! §fAcontece que tenho um pergaminho aqui. Vá testar!");
        this.addDialogAnswer(TCREntities.AINE.get(), 19, "%s, parece que você aprendeu a encantar armas! Agora, deixe-me te ensinar a lançar feitiços! §6Preste atenção, só vou ensinar isso uma vez!");
        this.addDialogAnswer(TCREntities.AINE.get(), 20, "Ao segurar uma arma encantada, pressione [%s] para lançar o feitiço correspondente. Viu? Simples, não é?");
        this.addDialogAnswer(TCREntities.AINE.get(), 21, "Lançar feitiços consome mana. Quando a mana se esgota, você não pode lançar feitiços. §6Note, a mana só pode ser recuperada no Santuário! Uma vez que você saia do Santuário, não posso infundir magia em você. §fNo entanto, há mais uma coisa...");
        this.addDialogAnswer(TCREntities.AINE.get(), 22, "Mais uma coisa! Caso você não consiga recuperar mana fora do Santuário, eu preparei especialmente o [%s] para você! Com o [%s], você pode repor a mana a qualquer hora, em qualquer lugar!");
        this.addDialogOption(TCREntities.AINE.get(), 9, "§6Eu aprendi");
        this.addDialogOption(TCREntities.AINE.get(), 10, "§aDiga novamente");
        this.addDialogAnswer(TCREntities.AINE.get(), 23, "Entretanto, [%s] tem usos limitados! Quando acabar, apenas use-o no Santuário e ele irá reabastecer automaticamente! Embarque no caminho da magia, Sal—va—dor!");
        this.addDialogAnswer(TCREntities.AINE.get(), 24, "%s, a sua aventura está indo bem recentemente?");
        this.addDialogOption(TCREntities.AINE.get(), 11, "Moleza!");
        this.addDialogOption(TCREntities.AINE.get(), 12, "Estou exausto!");
        this.addDialogAnswer(TCREntities.AINE.get(), 25, "Estou exausta de pesquisar materiais aqui também. Se não tivéssemos concordado que você faria isso sozinho, eu adoraria sair em uma aventura com você.");
        this.addDialogOption(TCREntities.AINE.get(), 13, "%s...");
        this.addDialogAnswer(TCREntities.AINE.get(), 26, "Hmm?");
        this.addDialogOption(TCREntities.AINE.get(), 14, "Algo parece errado");
        this.addDialogAnswer(TCREntities.AINE.get(), 27, "O que houve?");
        this.addDialogOption(TCREntities.AINE.get(), 15, "Todos eles parecem me conhecer");
        this.addDialogAnswer(TCREntities.AINE.get(), 28, "Bobagem, como isso seria possível? Acho que você andou se aventurando demais ultimamente e está vendo coisas. Você deveria descansar mais!");
        this.addDialogOption(TCREntities.AINE.get(), 16, "Estou falando sério");
        this.addDialogOption(TCREntities.AINE.get(), 17, "%s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 29, "Tudo bem, tudo bem, quando retornarmos todos os Olhos de Deus, todas as perguntas certamente serão respondidas!");
        this.addDialogAnswer(TCREntities.AINE.get(), 30, "Hmm... Os arquivos dizem que infundir [%s] pode ativá-lo e abrir um portal para o [%s]!");
        this.addDialogAnswer(TCREntities.AINE.get(), 31, "O §6[%s§6] é um reino entre o submundo e o Overworld. Dentro do [%s§6], podemos usar altares de invocação para encontrar os Anjos do passado.");
        this.addDialogAnswer(TCREntities.AINE.get(), 32, "Talvez possamos entrar e dar uma olhada. Talvez possamos ganhar seus poderes a partir das memórias!");
        this.addDialogAnswer(TCREntities.AINE.get(), 33, "%s, você parece perturbado. O que aconteceu em %s?");
        this.addDialogOption(TCREntities.AINE.get(), 18, "%s, na verdade eu...");
        this.addDialogAnswer(TCREntities.AINE.get(), 34, "...(Você conta a %s sobre o que aconteceu no %s)");
        this.addDialogAnswer(TCREntities.AINE.get(), 35, "Então foi por isso que você disse antes que todos eles parecem te conhecer...");
        this.addDialogAnswer(TCREntities.AINE.get(), 36, "Antes que você perceba, resta apenas [%s] para ser devolvido... Continue assim!");
        this.addDialogAnswer(TCREntities.AINE.get(), 37, "%s, você chegou! Ou devo te chamar de... como eles te chamavam? Purus!");
        this.addDialogAnswer(TCREntities.AINE.get(), 38, "O tratamento acabou. Se você tiver alguma dúvida, sinta-se à vontade para perguntar! Responderei a todas!");
        this.addDialogOption(TCREntities.AINE.get(), 19, "Sobre você");
        this.addDialogOption(TCREntities.AINE.get(), 20, "Sobre os Anjos");
        this.addDialogOption(TCREntities.AINE.get(), 21, "Sobre a verdade da Maré Negra");
        this.addDialogOption(TCREntities.AINE.get(), 22, "Sobre o futuro");
        this.addDialogOption(TCREntities.AINE.get(), 23, "§aNão tenho mais perguntas");
        this.addDialogAnswer(TCREntities.AINE.get(), 39, "Eu sou sua médica assistente, responsável por tratar sua esquizofrenia. Quanto a por que eu tenho essa aparência, é porque estamos no seu mundo mental. Tudo aqui é como você imagina.");
        this.addDialogAnswer(TCREntities.AINE.get(), 40, "Não precisa se preocupar. Você já os matou com suas próprias mãos. Eles estão mortos para sempre em seus sonhos. Eles não voltarão.");
        this.addDialogAnswer(TCREntities.AINE.get(), 41, "A Maré Negra é, na verdade, o efeito da medicação Risperidona. Usamos medicação para controlar a atividade das suas várias personalidades. E você, como a personalidade mais gentil e pura, escolhemos você como nossa porta de entrada. As outras personalidades eram muito problemáticas, então só podíamos administrar a medicação para suprimi-las quando você estava no controle do corpo.");
        this.addDialogAnswer(TCREntities.AINE.get(), 42, "No seu mundo mental, a medicação se manifesta como a 'Maré Negra'. Ela nos ajuda a conter as outras personalidades. No entanto, no seu mundo, apenas você pode realmente afetá-las. Então, fabricamos a mentira da recriação para te guiar, passo a passo, a eliminá-las.");
        this.addDialogAnswer(TCREntities.AINE.get(), 43, "A partir de agora, você pode viver como uma pessoa normal! Claro, sempre que quiser voltar aqui, é só me dizer!");
        this.addDialogAnswer(TCREntities.AINE.get(), 44, "Então, vamos sair juntos!");
        this.addDialogOption(TCREntities.AINE.get(), 24, "Ir para onde?");
        this.addDialogAnswer(TCREntities.AINE.get(), 45, "Voltar para a realidade!");
        this.addDialogOption(TCREntities.AINE.get(), 25, "...");
        this.addDialogAnswer(TCREntities.AINE.get(), 46, "O que foi, %s?");
        this.addDialogAnswer(TCREntities.AINE.get(), 47, "...");
        this.addDialogOption(TCREntities.AINE.get(), 26, "Não, eu quero ficar aqui");
        this.addDialogAnswer(TCREntities.AINE.get(), 48, "Por quê?");
        this.addDialogOption(TCREntities.AINE.get(), 27, "Eu quero...");
        this.addDialogOption(TCREntities.AINE.get(), 28, "Para sempre...");
        this.addDialogOption(TCREntities.AINE.get(), 29 , "Estar com eles...");
        this.addDialogAnswer(TCREntities.AINE.get(), 49, "...Eu respeito sua escolha. Sempre que você quiser voltar para a realidade, pode vir me procurar!");

        this.addDialogOption(TCREntities.ORNN.get(), -4, "§6Forja Avançada");
        this.addDialogOption(TCREntities.ORNN.get(), -3, "§6[Desbloquear Novas Opções de Troca]");
        this.addDialogOption(TCREntities.ORNN.get(), -2, "Encerrar Conversa");
        this.addDialogOption(TCREntities.ORNN.get(), -1, "Continuar");
        this.addDialogAnswer(TCREntities.ORNN.get(), -1, "O calor do fogo, a dureza do aço — essas duas coisas podem resolver a maioria dos problemas no mundo. Mestre, qual é o seu comando?");
        this.addDialogAnswer(TCREntities.ORNN.get(), 0, "O calor do fogo, a dureza do aço — essas duas coisas podem resolver a maioria dos problemas no mundo.");
        this.addDialogOption(TCREntities.ORNN.get(), 0, "Quem é você?");
        this.addDialogAnswer(TCREntities.ORNN.get(), 1, "Eu sou um semideus, mestre da forja e do artesanato, forjando armas divinas para o Santuário. Antes da Maré Negra chegar, achei que as armas que forjei fossem incomparáveis no mundo. Pensando bem agora... risível, risível.");
        this.addDialogOption(TCREntities.ORNN.get(), 1, "Sobre a %s");
        this.addDialogAnswer(TCREntities.ORNN.get(), 2, "A Guardiã é realmente insondável, mas é melhor seguir seus arranjos.");
        this.addDialogAnswer(TCREntities.ORNN.get(), 3, "Aquela jovem é mais livre que eu. Eu nunca poderei retornar à minha terra natal.");
        this.addDialogOption(TCREntities.ORNN.get(), 2, "§6Comissão de Forja");
        this.addDialogOption(TCREntities.ORNN.get(), 3, "§aPresente de Boas-Vindas");
        this.addDialogAnswer(TCREntities.ORNN.get(), 4, "Estes foram forjados de restos e sucatas. Leve-os para se defender por enquanto.");
        this.addDialogOption(TCREntities.ORNN.get(), 4, "%s");
        // Show Armamentarium
        this.addDialogOption(TCREntities.ORNN.get(), 5, "§6Mostrar [%s§6]");
        this.addDialogAnswer(TCREntities.ORNN.get(), 5, "Deus da Forja nas alturas! Este Armamentarium... registra todas as armas de alto nível do mundo e como forjá-las! Há até armas que eu nunca conheci!");
        this.addDialogAnswer(TCREntities.ORNN.get(), 6, "Este Armamentarium deve ter sido forjado pelo Anjo da Terra, Montis, antes que a Maré Negra descesse.");
        this.addDialogAnswer(TCREntities.ORNN.get(), 7, "Deixe comigo. Desvendarei este tomo requintado para você!");
        this.addDialogOption(TCREntities.ORNN.get(), 6, "§6Desbloquear Compêndio");
        // Yamato trade
        this.addDialogAnswer(TCREntities.ORNN.get(), 8, "Esta é, a lendária %s! Você encontrou um belo tesouro, viajante!");
        this.addDialogAnswer(TCREntities.ORNN.get(), 9, "Você quer que eu te ajude a quebrar o selo nela? Hmm... Eu conheço um jeito de remover o selo. Quando tiver reunido os materiais necessários, volte até mim!");

        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -3, "Retornar");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -2, "Encerrar Conversa");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), -1, "Continuar");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), -1, "Olá, mestre. Como posso ajudá-lo?");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 0, "Olá, viajante. Como posso ajudá-lo?");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 0, "Quem é você?");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 1, "Eu sou a Barqueira do Santuário, ordenada pela Senhora %s para escoltá-lo para fora da barreira e em direção àquele mundo. Também recebo as almas daquele mundo, assim colecionei muitas das suas bugigangas e relíquias espalhadas. No entanto, você precisa de [%s] para recuperá-las do Mar Primordial. Se você tiver %s, também pode me dar.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 1, "Sobre a %s");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 2, "A Guardiã trabalha dia e noite para salvar o Santuário. É verdadeiramente admirável.");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 3, "A habilidade de forja de %s é incomparável aqui. Costumo incumbi-lo de consertar algumas bugigangas raras.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 2, "§6Extrair Bugigangas");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 3, "§aIr para o Overworld");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 4, "§6Presente de Boas-Vindas");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 4, "Um pequeno presente, espero que você aceite. Assim que este espírito chocar e amadurecer, ele poderá viajar milhares de quilômetros por dia. Espero que o ajude na sua jornada pelo Overworld! §6Volte a falar comigo assim que o tiver criado até a fase adulta.");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 5, "§6Escolher [%s§6]");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 6, "§6Eu criei o dragão");
        this.addDialogAnswer(TCREntities.FERRY_GIRL.get(), 5, "Parece que o pequenino está se dando muito bem com você! Por favor, aceite esses presentes! Você pode verificar como usá-los no compêndio. Acredito que trarão mais diversão para as suas batalhas!");
        this.addDialogOption(TCREntities.FERRY_GIRL.get(), 7, "Aceitar");

        this.addDialogOption(ModEntities.BONE_CHIMERA, -1, "Retornar");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 0, "Humano? Para encontrar este lugar... a Pedra de Ressonância te guiou até aqui?");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 0, "Por que você está aprisionado aqui?");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 1, "Eu sou a montaria do Anjo da Terra. Quando o selo da torre desceu, o Anjo da Terra me transportou para cá para que a Pedra de Ressonância pudesse encontrar minha localização.");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 1, "Libertar Alma");
        this.addDialogAnswer(ModEntities.BONE_CHIMERA, 2, "Este corpo carrega uma maldição imortal. Se você me ajudar a encontrar a libertação, meus ossos poderão ser forjados em uma bela arma. Você está pronto?");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 2, "Pronto");
        this.addDialogOption(ModEntities.BONE_CHIMERA, 3, "Espere um pouco");

        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -2, "Sair");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), -1, "Continuar");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 0, "Croac! Humano!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 0, "Croac!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 1, "Gurgle gaga!");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 2, "§6Trocar");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 3, "§6Sobre o [%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 4, "§6Entregar [%s§6]");
        this.addDialogOption(EntityTypeModule.RIBBIT.get(), 5, "§6Aceitar");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 1, "Se você quer saber mais, traga muitos e muitos blocos de ametista para trocar, croac! Preciso de 12, croac!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 2, "Croac! Negócio fechado, croac!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 3, "Croac, você chama isso de oferenda, croac? Eu preciso de 12, croac!");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 4, "Éramos originalmente humanos da vila do oceano, amaldiçoados pela Maré Negra nesta forma, croac.");
        this.addDialogAnswer(EntityTypeModule.RIBBIT.get(), 5, "A torre do oceano é muito perigosa, croac. Por causa dos blocos de ametista, pegue esses tesouros para se defender, croac!");

        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -2, "Retornar");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), -1, "Continuar");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus! Eu me lembro, eu me lembro de tudo!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 0, "Purus Absolutus?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 1, "O que isso significa?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 1, "Você não se lembra? Purus Absolutus!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 2, "Eu?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 3, "Meu nome?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 2, "Parece que você também perdeu suas memórias para a Maré Negra... Isso mesmo, você é Purus Absolutus, o Anjo Puro!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 4, "???");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 5, "O que é tudo isso?");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 3, "Coloquei a minha divindade em [%s] para protegê-la da erosão da Maré Negra.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 4, "A Pedra de Ressonância te guiou para recuperar [%s], o que permitiu que minhas memórias fossem restauradas! Purus Absolutus, você realmente não se lembra de nada?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 6, "Sinto muito");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 7, "Não me lembro de nada");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 5, "Entendo... Antes de ajudá-lo a recuperar suas memórias, me conte o que aconteceu. Por que você veio aqui?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 8, "...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 6, "...(Você conta a [%s] sobre tudo o que aconteceu antes)");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 7, "Então é isso... A %s verdadeiramente persistiu até o fim... Eu nunca pensei que você retornaria com a bruxa para salvar o mundo. Muito bem. O que você deseja saber sobre o passado? Responderei da melhor forma que puder.");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 9, "Quem sou eu, de verdade?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 10, "O que é a Maré Negra, de verdade?");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 11, "§6Não tenho mais perguntas");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 8, "Seu nome é Purus Absolutus. Você é o Anjo Puro, responsável por purificar as trevas do mundo. Como nós, você é um do Conselho dos Dez Governantes do Mundo.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 9, "Mil anos atrás, foi a sua vez de lidar com assuntos além dos céus. Mas logo após a sua partida, a Maré Negra desceu sobre o mundo.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 10, "Talvez privados do seu poder divino purificador, fomos impotentes contra a Maré Negra e só pudemos deixá-la nos erodir.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 11, "Eu não sei. Quando a Maré Negra caiu pela primeira vez, conduzimos extensas pesquisas sobre ela, mas não fizemos progresso.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 12, "A Maré Negra era originalmente um meio de codinome Risperidona. Nós não conseguíamos perceber sua existência especificamente. Mas todos os seres vivos afetados por ela gradualmente ficavam negros e morriam, então a chamamos de Maré Negra.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 13, "Nós só sabemos que a Maré Negra veio de um mundo além dos céus, mas seu alvo parecia muito claro, sempre visando as formas de vida lideradas por nós, Anjos.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 14, "Talvez você liderar-nos na recriação seja a única esperança de sobrevivência para a nossa civilização.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 15, "Então, você precisa do Olho de Deus, que representa a minha divindade, para ajudar a completar o ritual. Mas separar o Olho de Deus significa que abandonarei minha forma mortal.");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 16, "Ah, bem. Agora, estou coberto pela Maré Negra, não sou mais o meu corpo original... Sem o poder do Olho de Deus, não poderei manter a forma humana...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 17, "...Talvez este seja o nosso destino.");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 12, "Não diga isso...");
        this.addDialogAnswer(TCREntities.FAKE_SKY_GOLEM.get(), 18, "...Vejo você amanhã, Purus Absolutus! Que possamos nos encontrar no prometido novo mundo!");
        this.addDialogOption(TCREntities.FAKE_SKY_GOLEM.get(), 13, "§4Executar");

        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 0, "Os eventos passados... você realmente não se lembra?");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 0, "§4Executar");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 1, "Os eventos passados...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 2, "Você quer dizer o passado...");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 1, "Forjei minha divindade em %s para evitar a erosão da Maré Negra, mesmo que meu corpo divino já tenha se tornado uma marionete decaída há muito tempo...");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 3, "Continuar");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 2, "Quando você libertou meu corpo divino agora, recuperei parte do meu poder. Purus, esta lâmina foi forjada por você há muito tempo usando o poder da purificação.");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 3, "Pena que a erosão da Maré Negra deixou apenas um embrião bruto. Pegue. Que a ajude a varrer o último resquício da Maré Negra!");
        this.addDialogAnswer(TCREntities.FAKE_END_GOLEM.get(), 4, "Aceite-a, [%s]. Esperarei por você no fim do mundo!");
        this.addDialogOption(TCREntities.FAKE_END_GOLEM.get(), 4, "...")
    }
}

package com.p1nero.tcrcore.entity.custom.aine_iris;

import com.obscuria.aquamirae.registry.AquamiraeEntities;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import com.p1nero.dialog_lib.api.component.DialogNode;
import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.entity.custom.IEntityNpc;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.fast_tpa.network.PacketRelay;
import com.p1nero.tcr_bosses.entity.TCRBossEntities;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.datagen.TCRAdvancementData;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.item.TCRItems;
import com.p1nero.tcrcore.network.TCRPacketHandler;
import com.p1nero.tcrcore.network.packet.clientbound.PlayTitlePacket;
import com.p1nero.tcrcore.utils.EntityUtil;
import com.p1nero.tcrcore.utils.ItemUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import io.redspace.ironsspellbooks.player.KeyMappings;
import moe.plushie.armourers_workshop.init.ModItems;
import net.blay09.mods.waystones.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class AineEntity extends PathfinderMob implements IEntityNpc, GeoEntity, Merchant {
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final ResourceLocation MP_DESC = ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "textures/gui/mp_point.png");

    @Nullable
    private Player tradingPlayer;
    private MerchantOffers offers = new MerchantOffers();

    public AineEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean hurt(DamageSource source, float p_21017_) {
        return source.isCreativePlayer();
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = new CompoundTag();
            if(!PlayerDataManager.aineTalked.get(player)) {
                PlayerDataManager.aineTalked.put(player, true);
            }
            this.sendDialogTo(serverPlayer, tag);
        }
        return InteractionResult.sidedSuccess(level().isClientSide);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public DialogueScreen getDialogueScreen(CompoundTag serverData) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if(localPlayer == null) {
            return null;
        }
        TCRQuestManager.Quest currentQuest = TCRQuestManager.getCurrentQuest(localPlayer);
        StreamDialogueScreenBuilder dialogueScreenBuilder = new StreamDialogueScreenBuilder(this, TCRCoreMod.MOD_ID);
        DialogueComponentBuilder dBuilder = dialogueScreenBuilder.getComponentBuildr();

        //-1继续
        //-2结束对话
        DialogNode root = new DialogNode(dBuilder.ans(0, localPlayer.getDisplayName()));//你来了！我正在阅读这个世界的智库？

        DialogNode aboutChronos = new DialogNode(dBuilder.ans(1), dBuilder.opt(0, TCREntities.CHRONOS_SOL.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));

        DialogNode aboutThisWorld = new DialogNode(dBuilder.ans(2), dBuilder.opt(1))
                .addLeaf(dBuilder.opt(-2));

        DialogNode enterDim;//TODO 进入轮回绝境

        if(currentQuest.equals(TCRQuests.TALK_TO_AINE_0)) {
            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
                root.addChild(aboutThisWorld);
            }
            DialogNode aboutSkin = new DialogNode(dBuilder.ans(3), dBuilder.opt(2))
                    .addLeaf(dBuilder.opt(3), 1);
            root.addChild(aboutSkin);
        } else if(currentQuest.equals(TCRQuests.TALK_TO_AINE_CLOUDLAND)) {
            dialogueScreenBuilder.start(dBuilder.ans(4, localPlayer.getDisplayName()))
                    .addOption(5, 5)
                    .addOption(-1, 6)
                    .addOption(-1, 7)
                    .addOption(-1, 8)
                    .addFinalOption(-2, 2);
            return dialogueScreenBuilder.build();
        } else if(TCRQuests.TALK_TO_AINE_ECHO.equals(currentQuest)) {
            dialogueScreenBuilder.start(dBuilder.ans(4, localPlayer.getDisplayName()))
                    .addOption(dBuilder.opt(7, AquamiraeItems.SHIP_GRAVEYARD_ECHO.get().getDescription()), dBuilder.ans(9))
                    .addOption(-1, 10)
                    .thenExecute(3)
                    .addOption(dBuilder.opt(-1), dBuilder.ans(11, TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription(), TCREntities.CHRONOS_SOL.get().getDescription()))
                    .addOption(dBuilder.opt(8, AquamiraeEntities.CAPTAIN_CORNELIA.get().getDescription(), TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription()), dBuilder.ans(12, TCRBossEntities.MALEDICTUS_HUMANOID.get().getDescription(), com.github.L_Ender.cataclysm.init.ModItems.CURSED_EYE.get().getDescription()))
                    .addFinalOption(-2, 4);
            return dialogueScreenBuilder.build();
        } else if(TCRQuests.TALK_TO_AINE_MAGIC.equals(currentQuest)) {
            //学魔法
            dialogueScreenBuilder.start(dBuilder.ans(4, localPlayer.getDisplayName()))
                    .addOption(dBuilder.opt(7, TCRItems.NECROMANCY_SCROLL.get().getDescription()), dBuilder.ans(13, TCRItems.NECROMANCY_SCROLL.get().getDescription()))
                    .addOption(-1, 14)
                    .addOption(dBuilder.opt(-1), dBuilder.ans(15, TCRItems.NECROMANCY_SCROLL.get().getDescription()))
                    .addOption(-1, 16)
                    .addOption(-1, 17)
                    .addOption(-1, 18)
                    .addFinalOption(-2, 5);
            return dialogueScreenBuilder.build();
        } else if(TCRQuests.TALK_TO_AINE_MAGIC_2.equals(currentQuest)) {
            //介绍施法
            DialogNode learnt = new DialogNode(dBuilder.ans(22, TCRItems.MAGIC_BOTTLE.get().getDescription().copy().withStyle(ChatFormatting.AQUA), TCRItems.MAGIC_BOTTLE.get().getDescription().copy().withStyle(ChatFormatting.AQUA)), dBuilder.opt(9))
                    .addExecutable(dialogueScreen -> {
                        dialogueScreen.setPicture(null);
                    })
                    .addChild(new DialogNode(dBuilder.ans(23, TCRItems.MAGIC_BOTTLE.get().getDescription().copy().withStyle(ChatFormatting.AQUA)), dBuilder.opt(-1))
                            .addLeaf(dBuilder.opt(-2), 6));

            root = new DialogNode(dBuilder.ans(19, localPlayer.getDisplayName()), dBuilder.opt(10))
                    .addExecutable(dialogueScreen -> {
                        dialogueScreen.setPicture(null);
                    });

            DialogNode next = new DialogNode(dBuilder.ans(20, KeyMappings.SPELLBOOK_CAST_ACTIVE_KEYMAP.getTranslatedKeyMessage().copy().withStyle(ChatFormatting.GOLD)), dBuilder.opt(-1))
                    .addChild(new DialogNode(dBuilder.ans(21), dBuilder.opt(-1))
                            .addExecutable(dialogueScreen -> {
//                                dialogueScreen.setPicture(MP_DESC);
                                //TODO 调整插图
                            })
                            .addChild(learnt)
                            .addChild(root));
            root.addChild(next);

        } else if(TCRQuests.TALK_TO_AINE_1.equals(currentQuest)) {
            //聊聊最近的冒险（没啥用，纯增加氛围
            root = new DialogNode(dBuilder.ans(24, localPlayer.getDisplayName()));
            DialogNode easy = new DialogNode(dBuilder.ans(25), dBuilder.opt(11));
            DialogNode hard = new DialogNode(dBuilder.ans(25), dBuilder.opt(12));

            DialogNode next = new DialogNode(dBuilder.ans(26), dBuilder.opt(13, TCREntities.AINE.get().getDescription()))
                    .addChild(new DialogNode(dBuilder.ans(27), dBuilder.opt(14))
                            .addChild(new DialogNode(dBuilder.ans(28), dBuilder.opt(15))
                                    .addChild(new DialogNode(dBuilder.ans(29), dBuilder.opt(16))
                                            .addLeaf(dBuilder.opt(-2), 9))));
            easy.addChild(next);
            hard.addChild(next);

            root.addChild(easy)
                    .addChild(hard);
        } else if(TCRQuests.TALK_TO_AINE_SAMSARA.equals(currentQuest)) {
            //打开轮回绝境
            return dialogueScreenBuilder.start(dBuilder.ans(24))
                    .addOption(dBuilder.opt(7, TCRItems.WITHER_SOUL_STONE.get().getDescription()), dBuilder.ans(30))
                    .addOption(dBuilder.opt(17, Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1")), dBuilder.ans(31, Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1"), Component.translatable("travelerstitles.pbf1.sanctum_of_the_battle1")))
                    .addOption(-1, 32)
                    .addFinalOption(-2, 10)
                    .build();
        } else {
            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
                root.addChild(aboutThisWorld);
                root.addLeaf(dBuilder.opt(-2));
            }
            if(TCRQuests.TALK_TO_AINE_MAGIC.isFinished(localPlayer)) {
                root.addLeaf(dBuilder.opt(-3), 7);
                root.addLeaf(dBuilder.opt(-4), 8);
            }
        }

        return dialogueScreenBuilder.buildWith(root);
    }

    @Override
    public void handleNpcInteraction(ServerPlayer serverPlayer, int code) {
        //初次对话&领取时装
        if(code == 1) {
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_TEMPLATE.get(), 20, ChatFormatting.GOLD.getColor());
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_LIBRARY_GLOBAL.get().getDefaultInstance(), ChatFormatting.GOLD.getColor());
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_LIBRARY.get().getDefaultInstance(), ChatFormatting.GOLD.getColor());
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKINNING_TABLE.get().getDefaultInstance(), ChatFormatting.GOLD.getColor());
            TCRQuests.TALK_TO_AINE_0.finish(serverPlayer);
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayTitlePacket(PlayTitlePacket.UNLOCK_NEW_CHAPTER), serverPlayer);
        }
        //聊幻境
        if(code == 2) {
            TCRQuests.TALK_TO_AINE_CLOUDLAND.finish(serverPlayer);
        }
        //播放解读海船回响特效
        if(code == 3) {
            Vec3 dis = this.position().subtract(serverPlayer.position());
            Vec3 pos = serverPlayer.position().add(dis.scale(0.5F));
            serverPlayer.serverLevel().sendParticles(
                    ParticleTypes.SOUL,
                    pos.x,
                    pos.y,
                    pos.z,
                    10,
                    0, 0.1, 0,
                    0.1f
            );
            EntityUtil.playLocalSound(serverPlayer, SoundEvents.BEACON_ACTIVATE);
            return;
        }

        if(code == 4) {
            TCRQuests.TALK_TO_AINE_ECHO.finish(serverPlayer);
            TCRQuests.TALK_TO_CHRONOS_4.start(serverPlayer);
        }

        if(code == 5) {
            TCRQuests.TALK_TO_AINE_MAGIC.finish(serverPlayer);
            TCRQuests.TRY_TO_LEARN_MAGIC.start(serverPlayer);
            TCRAdvancementData.finishAdvancement("unlock_magic_and_boss", serverPlayer);
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayTitlePacket(PlayTitlePacket.UNLOCK_NEW_CHAPTER), serverPlayer);
        }

        if(code == 6) {
            TCRQuests.TALK_TO_AINE_MAGIC_2.finish(serverPlayer);
            ItemUtil.addItemEntity(serverPlayer, TCRItems.MAGIC_BOTTLE.get(), 1, ChatFormatting.AQUA.getColor());
        }

        if(code == 7) {
            //法术交易
            this.startTrade(serverPlayer);
        }

        if(code == 8) {
            //打开奥术铁砧
            BlockState blockState = serverPlayer.level().getBlockState(WorldUtil.ARCANE_ANVIL_BLOCK_POS);
            serverPlayer.openMenu(blockState.getMenuProvider(serverPlayer.level(), WorldUtil.ARCANE_ANVIL_BLOCK_POS));
            serverPlayer.awardStat(Stats.INTERACT_WITH_ANVIL);
        }

        if(code == 9) {
            //闲聊
            TCRQuests.TALK_TO_AINE_1.finish(serverPlayer);
        }

        if(code == 10) {
            TCRQuests.TALK_TO_AINE_SAMSARA.finish(serverPlayer);
            TCRAdvancementData.finishAdvancement("unlock_epic_boss", serverPlayer);
            PacketRelay.sendToPlayer(TCRPacketHandler.INSTANCE, new PlayTitlePacket(PlayTitlePacket.UNLOCK_NEW_CHAPTER), serverPlayer);
        }

        this.setConversingPlayer(null);
    }

    @Override
    public void tick() {
        super.tick();
        if(!level().isClientSide) {

            //位置矫正保险
            if(tickCount % 100 == 0) {
                BlockPos myPos = this.getOnPos();
                if(myPos.getX() != WorldUtil.AINE_POS.getX() || myPos.getZ() != WorldUtil.AINE_POS.getZ()) {
                    this.setPos(new BlockPos(WorldUtil.AINE_POS).getCenter());
                }
            }
            if(getConversingPlayer() != null && (getConversingPlayer().isRemoved() || getConversingPlayer().isDeadOrDying() || getConversingPlayer().distanceTo(this) > 5)) {
                setConversingPlayer(null);
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends AineEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * 开始交易
     * 需要改变交易表则去重写 {@link #getOffers()}
     */
    public void startTrade(ServerPlayer serverPlayer){
        setTradingPlayer(serverPlayer);
        openTradingScreen(serverPlayer, Component.empty(), 1);
    }

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        tradingPlayer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return tradingPlayer;
    }

    @Override
    public @NotNull MerchantOffers getOffers() {
        if(offers == null) {
            initOffers();
        }
        return offers;
    }

    public void initOffers() {
        offers = new MerchantOffers();
        //TODO 添加法术交易
        offers.add(new MerchantOffer(
                new ItemStack(Items.ENDER_EYE, 1),
                new ItemStack(ModBlocks.waystone, 1),
                142857, 0, 0.02f));
    }

    @Override
    public void overrideOffers(@NotNull MerchantOffers merchantOffers) {

    }

    @Override
    public void notifyTrade(@NotNull MerchantOffer merchantOffer) {

    }

    @Override
    public void notifyTradeUpdated(@NotNull ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public @NotNull SoundEvent getNotifyTradeSound() {
        return SoundEvents.EXPERIENCE_ORB_PICKUP;
    }

    @Override
    public boolean isClientSide() {
        return level().isClientSide;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

}

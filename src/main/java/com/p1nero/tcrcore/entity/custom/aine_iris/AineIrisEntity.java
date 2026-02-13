package com.p1nero.tcrcore.entity.custom.aine_iris;

import com.p1nero.dialog_lib.api.component.DialogNode;
import com.p1nero.dialog_lib.api.component.DialogueComponentBuilder;
import com.p1nero.dialog_lib.api.entity.custom.IEntityNpc;
import com.p1nero.dialog_lib.client.screen.DialogueScreen;
import com.p1nero.dialog_lib.client.screen.builder.StreamDialogueScreenBuilder;
import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.capability.PlayerDataManager;
import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.entity.TCREntities;
import com.p1nero.tcrcore.utils.ItemUtil;
import com.p1nero.tcrcore.utils.WorldUtil;
import moe.plushie.armourers_workshop.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
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

public class AineIrisEntity extends PathfinderMob implements IEntityNpc, GeoEntity, Merchant {
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Nullable
    private Player tradingPlayer;
    private MerchantOffers offers = new MerchantOffers();

    public AineIrisEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
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

        DialogNode enchantment;//TODO 法术淬灵

        if(currentQuest.equals(TCRQuests.TALK_TO_AINE_0)) {
            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
                root.addChild(aboutThisWorld);
            }
            DialogNode aboutSkin = new DialogNode(dBuilder.ans(3), dBuilder.opt(2))
                    .addLeaf(dBuilder.opt(3), 1);
            root.addChild(aboutSkin);
        } else if(currentQuest.equals(TCRQuests.TALK_TO_AINE_CLOUDLAND)) {
            dialogueScreenBuilder.start(dBuilder.opt(4, localPlayer.getDisplayName()))
                    .addOption(5, 5)
                    .addOption(-1, 6)
                    .addOption(-1, 7)
                    .addOption(-1, 8)
                    .addFinalOption(-2, 2);
            return dialogueScreenBuilder.build();
        } else  {
            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
                root.addChild(aboutThisWorld);
                root.addLeaf(dBuilder.opt(-2));
            }
        }

        return dialogueScreenBuilder.buildWith(root);
    }

    @Override
    public void handleNpcInteraction(ServerPlayer serverPlayer, int i) {
        //初次对话&领取时装
        if(i == 1) {
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_TEMPLATE.get(), 20);
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_LIBRARY_GLOBAL.get().getDefaultInstance());
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKIN_LIBRARY.get().getDefaultInstance());
            ItemUtil.addItemEntity(serverPlayer, ModItems.SKINNING_TABLE.get().getDefaultInstance());
            TCRQuests.TALK_TO_AINE_0.finish(serverPlayer);
        }
        if(i == 2) {
            TCRQuests.TALK_TO_AINE_CLOUDLAND.finish(serverPlayer);
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

    protected <E extends AineIrisEntity> PlayState deployAnimController(final AnimationState<E> state) {
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
        return offers == null ? new MerchantOffers() : offers;
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

package com.p1nero.tcrcore.entity.custom.ornn;

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
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import yesman.epicfight.world.item.EpicFightItems;

public class OrnnEntity extends PathfinderMob implements IEntityNpc, GeoEntity, Merchant {
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation FORGE = RawAnimation.begin().thenPlay("forge");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final int yRot = 0;

    @Nullable
    private Player tradingPlayer;
    private MerchantOffers offers = new MerchantOffers();

    public OrnnEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float p_21017_) {
        return source.isCreativePlayer();
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            if(!PlayerDataManager.ornnTalked.get(player)) {
                PlayerDataManager.ornnTalked.put(player, true);
            }
            CompoundTag tag = new CompoundTag();

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
        StreamDialogueScreenBuilder treeBuilder = new StreamDialogueScreenBuilder(this, TCRCoreMod.MOD_ID);
        DialogueComponentBuilder dBuilder = treeBuilder.getComponentBuildr();
        DialogNode root = new DialogNode(dBuilder.ans(0));
        DialogNode aboutChronos = new DialogNode(dBuilder.ans(2), dBuilder.opt(1, TCREntities.CHRONOS_SOL.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));
        DialogNode aboutFerryGirl = new DialogNode(dBuilder.ans(3), dBuilder.opt(1, TCREntities.FERRY_GIRL.get().getDescription()))
                .addLeaf(dBuilder.opt(-2));
        DialogNode smithHelp = new DialogNode.FinalNode(dBuilder.opt(2), 1);
        if(currentQuest.equals(TCRQuests.TALK_TO_ORNN_1)) {
            DialogNode whoAreU = new DialogNode(dBuilder.ans(1), dBuilder.opt(0))
                    .addLeaf(dBuilder.opt(-2));
            DialogNode firstMeetGift = new DialogNode(dBuilder.ans(4), dBuilder.opt(3))
                    .addLeaf(dBuilder.opt(4), 2)
                    .addLeaf(dBuilder.opt(5), 3)
                    .addLeaf(dBuilder.opt(6), 4)
                    .addLeaf(dBuilder.opt(7), 5)
                    .addLeaf(dBuilder.opt(8), 6)
                    .addLeaf(dBuilder.opt(9), 7);

            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
            }
            if(PlayerDataManager.ferryGirlTalked.get(localPlayer)) {
                root.addChild(aboutFerryGirl);
            }

            root.addChild(whoAreU)
                    .addChild(firstMeetGift);
        } else {
            if(PlayerDataManager.chonosTalked.get(localPlayer)) {
                root.addChild(aboutChronos);
            }
            if(PlayerDataManager.ferryGirlTalked.get(localPlayer)) {
                root.addChild(aboutFerryGirl);
            }
            root.addChild(smithHelp);
            root.addLeaf(dBuilder.opt(-2));
        }
        return treeBuilder.buildWith(root);
    }

    @Override
    public void handleNpcInteraction(ServerPlayer serverPlayer, int i) {
        if(i == 1) {
            serverPlayer.openMenu(new SimpleMenuProvider((p_277304_, p_277305_, p_277306_) ->
                    new SmithingMenu(p_277304_, p_277305_, ContainerLevelAccess.create(serverPlayer.level(), this.getOnPos())), Component.translatable("container.upgrade")));
        }

        if(i == 2) {
            ItemUtil.addItemEntity(serverPlayer, EpicFightItems.IRON_DAGGER.get().getDefaultInstance());
        }
        if(i == 3) {
            ItemUtil.addItemEntity(serverPlayer, Items.IRON_SWORD.getDefaultInstance());
        }
        if(i == 4) {
            ItemUtil.addItemEntity(serverPlayer, EpicFightItems.GOLDEN_LONGSWORD.get().getDefaultInstance());
        }
        if(i == 5) {
            ItemUtil.addItemEntity(serverPlayer, EpicFightItems.GOLDEN_TACHI.get().getDefaultInstance());
        }
        if(i == 6) {
            ItemUtil.addItemEntity(serverPlayer, EpicFightItems.GOLDEN_SPEAR.get().getDefaultInstance());
        }
        if(i == 7) {
            ItemUtil.addItemEntity(serverPlayer, EpicFightItems.WOODEN_GREATSWORD.get().getDefaultInstance());
        }

        //领了礼物才算结束
        if(i >= 2 && i <=7) {
            TCRQuests.TALK_TO_ORNN_1.finish(serverPlayer);
        }
        this.setConversingPlayer(null);
    }

    @Override
    public void tick() {
        super.tick();
        if(!level().isClientSide) {
            //2s播放一次
            if(tickCount % 40 == 0) {
                playForgeAnimation();
            }
            //播放后0.75s敲到
            if((tickCount - 15) % 40 == 0) {
                level().playSound(null, getX(), getY(), getZ(), SoundEvents.ANVIL_LAND, getSoundSource(), 1.0F, 1.0F);
            }
            if(getConversingPlayer() != null && (getConversingPlayer().isRemoved() || getConversingPlayer().isDeadOrDying() || getConversingPlayer().distanceTo(this) > 5)) {
                setConversingPlayer(null);
            }

            if(tickCount % 100 == 0) {
                BlockPos myPos = this.getOnPos();
                if(myPos.getX() != WorldUtil.ORNN_POS.getX() || myPos.getZ() != WorldUtil.ORNN_POS.getZ()) {
                    this.setPos(new BlockPos(WorldUtil.ORNN_POS).getCenter());
                }
            }
        }
        //转头就好了
        setYBodyRot(yRot);
        setYRot(yRot);
    }

    public void playForgeAnimation() {
        this.triggerAnim("Controller", "forge");
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
        controllers.add(new AnimationController<>(this, "Controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("forge", FORGE));
    }

    protected <E extends OrnnEntity> PlayState deployAnimController(final AnimationState<E> state) {
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

package com.p1nero.tcrcore.mixin;

import com.p1nero.tcrcore.capability.TCRQuestManager;
import com.p1nero.tcrcore.capability.TCRQuests;
import com.p1nero.tcrcore.item.TCRItems;
import net.magister.bookofdragons.entity.base.dragon.DragonBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import software.bernie.geckolib.animatable.GeoEntity;

@Mixin(DragonBase.class)
public abstract class DragonBaseMixin extends TamableAnimal implements GeoEntity, ContainerListener {

    @Shadow(remap = false)
    public abstract void setGrowthProgress(int progress);

    @Shadow(remap = false)
    public abstract int getGrowthProgress();

    protected DragonBaseMixin(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    /**
     * 调试养大用
     */
    @Inject(method = "mobInteract", at = @At("TAIL"))
    private void tcr$mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir) {
        if(pPlayer.getMainHandItem().is(TCRItems.DIVINE_FRAGMENT.get())) {
            this.setGrowthProgress(this.getGrowthProgress() + 6000);
            if(!pPlayer.isLocalPlayer()) {
                pPlayer.getMainHandItem().shrink(1);
            }
        }
    }

    /**
     * 龙养大后
     */
    @Inject(method = "setGrowthStage", at = @At("HEAD"), remap = false)
    private void tcr$setGrowthStage(int stage, CallbackInfo ci) {
        if(stage >= 2) {
            if(this.getOwner() instanceof ServerPlayer player) {
                if(!TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL.isFinished(player) && TCRQuestManager.hasQuest(player, TCRQuests.TAME_DRAGON)) {
                    TCRQuests.TAME_DRAGON.finish(player, true);
                    TCRQuests.TAME_DRAGON_BACK_TO_FERRY_GIRL.start(player);
                }
            }
        }
    }

}

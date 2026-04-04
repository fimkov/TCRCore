package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.entity.block.BTMonolith;
import com.brass_amber.ba_bt.init.BTEntityType;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 *  简化为一个钥匙即可
 */
@Mixin(value = BTMonolith.class)
public abstract class BTMonolithMixin extends Entity {

    @Shadow(remap = false)
    @Final
    private EntityType<?> monolithType;

    @Shadow(remap = false)
    @Final
    private Item correctMonolithKey;

    @Shadow(remap = false)
    public abstract void setEyeSlotDisplayed();

    @Shadow(remap = false) public abstract void setKeyCountInEntity(int count);

    @Shadow(remap = false) protected abstract void playKeyInteractionSound();

    @Shadow(remap = false)
    public abstract int getKeyCountInEntity();

    public BTMonolithMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void tcr$interact(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (this.monolithType != null) {
            Item itemInHand = player.getItemInHand(hand).getItem();
            if (itemInHand.equals(this.correctMonolithKey)) {
                int min = this.monolithType.equals(BTEntityType.LAND_MONOLITH.get()) ? 2 : 1;
                if(this.getKeyCountInEntity() == min) {
                    this.setKeyCountInEntity(3);
                    this.playKeyInteractionSound();
                    if (!player.isCreative()) {
                        player.getItemInHand(hand).shrink(1);
                    }
                    this.setEyeSlotDisplayed();
                    cir.setReturnValue(InteractionResult.sidedSuccess(this.getCommandSenderWorld().isClientSide()));
                }
            }

            if(this.monolithType.equals(BTEntityType.END_MONOLITH.get())) {
                //末地的不用钥匙，直接生，但是也可以损耗一下钥匙hhh
                if (!player.isCreative() && itemInHand.equals(this.correctMonolithKey)) {
                    player.getItemInHand(hand).shrink(1);
                }
                this.setKeyCountInEntity(3);
                this.setEyeSlotDisplayed();
                this.playKeyInteractionSound();
                cir.setReturnValue(InteractionResult.sidedSuccess(this.getCommandSenderWorld().isClientSide()));
            }
        }
    }

    @WrapOperation(method = "spawnGolem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"))
    private Explosion tcr$spawnGolem(Level instance, Entity p_256599_, double x, double y, double z, float r, Level.ExplosionInteraction p_256178_, Operation<Explosion> original) {
        if(instance instanceof ServerLevel serverLevel){
            serverLevel.sendParticles(ParticleTypes.EXPLOSION, getX(), getY(), getZ(), 10, 0.0D, 0.1D, 0.0D, 0.01);
            serverLevel.playSound(null, getX(), getY(), getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1, 1);
        }
        return null;
    }

}

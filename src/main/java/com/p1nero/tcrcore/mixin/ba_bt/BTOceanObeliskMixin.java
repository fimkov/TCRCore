package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.entity.block.BTAbstractObelisk;
import com.brass_amber.ba_bt.entity.block.BTOceanObelisk;
import com.brass_amber.ba_bt.util.BTUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTOceanObelisk.class)
public abstract class BTOceanObeliskMixin extends BTAbstractObelisk  {

    public BTOceanObeliskMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "removeAreaBlocks", at = @At("HEAD"), remap = false, cancellable = true)
    private void tcr$removeAreaBlocks(CallbackInfo ci) {
        int removeSize = this.toRemove.size();
        if (removeSize > 0) {
            for(int i = Math.min(removeSize, 2048); i > 0 ; --i) {
                this.level().setBlock(this.toRemove.remove(0), Blocks.WATER.defaultBlockState(), 2);
            }
            BTUtil.doNoOutputCommand(this, "/kill @e[distance=0..100,type=item]");
        } else {
            this.generationState = BTAbstractObelisk.GenerationState.ADD_AREA_FEATURES;
        }
        ci.cancel();
    }
}

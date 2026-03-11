package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.block.block.CoreMatterBlock;
import com.brass_amber.ba_bt.block.block.CoreMatterSlab;
import com.brass_amber.ba_bt.init.BTBlocks;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(BTBlocks.class)
public class BTBlocksMixin {

    /**
     * 补回碰撞
     */
    @WrapMethod(method = "registerBlock",remap = false)
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, int stackSize, Operation<RegistryObject<T>> original) {
        if(name.equals("core_matter_block")) {
            block = () -> (T)new CoreMatterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).forceSolidOn().strength(4.0F).pushReaction(PushReaction.DESTROY).lightLevel((blockState) -> 12).hasPostProcess(BTBlocksMixin::tcr$always).emissiveRendering(BTBlocksMixin::tcr$always));
        }
        if(name.equals("core_matter_slab")) {
            block = () -> (T)new CoreMatterSlab(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).forceSolidOn().strength(4.0F).pushReaction(PushReaction.DESTROY).lightLevel((blockState) -> 12).hasPostProcess(BTBlocksMixin::tcr$always).emissiveRendering(BTBlocksMixin::tcr$always));
        }
        return original.call(name, block, stackSize);
    }

    @Unique
    private static Boolean tcr$always(BlockState p_50810_, BlockGetter p_50811_, BlockPos p_50812_) {
        return true;
    }

}

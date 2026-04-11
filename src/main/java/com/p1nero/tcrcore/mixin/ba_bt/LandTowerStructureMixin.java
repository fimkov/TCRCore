package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.util.BTStatics;
import com.brass_amber.ba_bt.worldGen.structures.LandTower;
import com.brass_amber.ba_bt.worldGen.structures.TowerStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = LandTower.class)
public abstract class LandTowerStructureMixin implements TowerStructure {

    //改自TowerStructure#afterPlace
    @Redirect(
            method = "afterPlace",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/brass_amber/ba_bt/worldGen/structures/LandTower;afterPlaceBT(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/levelgen/structure/pieces/PiecesContainer;)V",
                    remap = false
            )
    )
    private void tcr$afterPlace(LandTower instance, WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource randomSource, BoundingBox chunkBoundingBox, ChunkPos chunkPos, PiecesContainer piecesContainer) {
        // After Place is called for every chunk that the structure occupies.
        BoundingBox boundingbox = piecesContainer.calculateBoundingBox();
        int bbYStart = boundingbox.minY();
        //--修改----------------------------------------------------
        int limitedMinY = bbYStart - 5;
        int worldMinY = worldGenLevel.getMinBuildHeight();
        int minBuildHeight = Math.max(limitedMinY, worldMinY);
        //---------------------------------------------------------
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        List<Block> towerBlocks = BTStatics.towerBlocks.get(getTowerId());
        BlockState baseBlock = BTStatics.towerBaseBlocks.get(getTowerId());

        for (int x = chunkBoundingBox.minX(); x <= chunkBoundingBox.maxX(); ++x) {
            for (int z = chunkBoundingBox.minZ(); z <= chunkBoundingBox.maxZ(); ++z) {
                blockpos$mutableblockpos.set(x, bbYStart, z);
                BlockState block = worldGenLevel.getBlockState(blockpos$mutableblockpos);
                if (!worldGenLevel.isEmptyBlock(blockpos$mutableblockpos) && boundingbox.isInside(blockpos$mutableblockpos) && (towerBlocks.contains(block.getBlock()) || block.is(BlockTags.DIRT))) {
                    for (int i1 = bbYStart - 1; i1 > minBuildHeight; --i1) {
                        blockpos$mutableblockpos.setY(i1);
                        if (!worldGenLevel.isEmptyBlock(blockpos$mutableblockpos) && !worldGenLevel.getBlockState(blockpos$mutableblockpos).getFluidState().isEmpty()) {
                            break;
                        }
                        if (block.is(Blocks.SAND) || block.is(Blocks.SANDSTONE)) {
                            worldGenLevel.setBlock(blockpos$mutableblockpos, Blocks.SAND.defaultBlockState(), 2);
                            break;
                        }
                        else {
                            worldGenLevel.setBlock(blockpos$mutableblockpos, baseBlock, 2);
                        }
                    }
                }
            }
        }
    }
}
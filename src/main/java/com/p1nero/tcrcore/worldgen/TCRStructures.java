package com.p1nero.tcrcore.worldgen;

import com.p1nero.tcrcore.TCRCoreMod;
import com.p1nero.tcrcore.worldgen.structure.NetherGateOfDisasterStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TCRStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES;
    public static final RegistryObject<StructureType<NetherGateOfDisasterStructure>> GATE_OF_DISASTER;
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE;
    public static final RegistryObject<StructurePieceType> GATE_OF_DISASTER_PIECE;

    static {
        STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, TCRCoreMod.MOD_ID);
        GATE_OF_DISASTER = STRUCTURE_TYPES.register("gate_of_disaster", () -> () -> NetherGateOfDisasterStructure.CODEC);
        STRUCTURE_PIECE = DeferredRegister.create(Registries.STRUCTURE_PIECE,  TCRCoreMod.MOD_ID);
        GATE_OF_DISASTER_PIECE = STRUCTURE_PIECE.register("gate_of_disaster_piece", () -> NetherGateOfDisasterStructure.Piece::new);
    }
}

package com.thesmartnoob880.gendustrated_bees.block.entity;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import com.thesmartnoob880.gendustrated_bees.block.modBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class modBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GendustratedBees.MODID);

    public static final RegistryObject<BlockEntityType<genomeExtractorBlockEntity>> GENOME_EXTRACTOR_BE =
            BLOCK_ENTITIES.register("genome_extractor_be", ()->
                    BlockEntityType.Builder.of(genomeExtractorBlockEntity::new,
                            modBlocks.GENOME_EXTRACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<geneticImprinterBlockEntity>> GENETIC_IMPRINTER_BE =
            BLOCK_ENTITIES.register("genetic_imprinter_be", ()->
                    BlockEntityType.Builder.of(geneticImprinterBlockEntity::new,
                            modBlocks.GENETIC_IMPRINTER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}

package com.thesmartnoob880.gendustrated_bees.util;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;

public class modTags {
    public static class Blocks{
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(GendustratedBees.MODID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> LABWARE = tag("labware_items");
        public static final TagKey<Item> GENE_SAMPLE = tag("gene_sample");
        public static final TagKey<Item> GENETIC_TEMPLATE = tag("genetic_template");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(GendustratedBees.MODID, name));
        }
    }
}

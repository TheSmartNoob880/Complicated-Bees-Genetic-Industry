package com.thesmartnoob880.genindustry_bees.util;

import com.thesmartnoob880.genindustry_bees.GenIndustryBees;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class modTags {
    public static class Blocks{
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(GenIndustryBees.MODID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> LABWARE = tag("labware_items");
        public static final TagKey<Item> GENE_SAMPLE = tag("gene_sample");
        public static final TagKey<Item> GENETIC_TEMPLATE = tag("genetic_template");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(GenIndustryBees.MODID, name));
        }
    }
}

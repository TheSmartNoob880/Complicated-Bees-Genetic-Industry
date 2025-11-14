package com.thesmartnoob880.gendustrated_bees.item;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class modItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GendustratedBees.MODID);

    public static final RegistryObject<geneSampleItem> GENE_SAMPLE = ITEMS.register("gene_sample", () -> new geneSampleItem(new Item.Properties()));
    public static final RegistryObject<geneticTemplateItem> GENETIC_TEMPLATE = ITEMS.register("genetic_template", () -> new geneticTemplateItem(new Item.Properties()));
    public static final RegistryObject<labwareItem> BASIC_LABWARE = ITEMS.register("basic_labware", () -> new labwareItem(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<labwareItem> IMPROVED_LABWARE = ITEMS.register("improved_labware", () -> new labwareItem(new Item.Properties().durability(16).setNoRepair()));
    public static final RegistryObject<labwareItem> ADVANCED_LABWARE = ITEMS.register("advanced_labware", () -> new labwareItem(new Item.Properties().durability(64).setNoRepair().fireResistant()));
    public static final RegistryObject<Item> HONEY_COMB_INGOT = ITEMS.register("honey_comb_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROYAL_INGOT = ITEMS.register("royal_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

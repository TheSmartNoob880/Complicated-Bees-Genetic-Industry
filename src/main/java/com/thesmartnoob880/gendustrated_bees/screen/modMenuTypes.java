package com.thesmartnoob880.gendustrated_bees.screen;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class modMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, GendustratedBees.MODID);

    public static final RegistryObject<MenuType<genomeExtractorMenu>> GENOME_EXTRACTOR_MENU =
            registerMenuType("genome_extractor_menu", genomeExtractorMenu::new);
    public static final RegistryObject<MenuType<geneticImprinterMenu>> GENETIC_IMPRINTER_MENU =
            registerMenuType("genetic_imprinter_menu", geneticImprinterMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}

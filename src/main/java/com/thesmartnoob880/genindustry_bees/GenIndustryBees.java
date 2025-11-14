package com.thesmartnoob880.genindustry_bees;

import com.accbdd.complicated_bees.ComplicatedBees;
import com.mojang.logging.LogUtils;
import com.thesmartnoob880.genindustry_bees.block.entity.modBlockEntities;
import com.thesmartnoob880.genindustry_bees.block.modBlocks;
import com.thesmartnoob880.genindustry_bees.config.Config;
import com.thesmartnoob880.genindustry_bees.item.geneSampleItem;
import com.thesmartnoob880.genindustry_bees.item.geneticTemplateItem;
import com.thesmartnoob880.genindustry_bees.item.modCreativeTab;
import com.thesmartnoob880.genindustry_bees.item.modItems;
import com.thesmartnoob880.genindustry_bees.recipe.modRecipes;
import com.thesmartnoob880.genindustry_bees.screen.geneticImprinterScreen;
import com.thesmartnoob880.genindustry_bees.screen.genomeExtractorScreen;
import com.thesmartnoob880.genindustry_bees.screen.modMenuTypes;
import com.thesmartnoob880.genindustry_bees.util.geneColors;
import com.thesmartnoob880.genindustry_bees.util.labwareHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GenIndustryBees.MODID)
public class GenIndustryBees {
    public static final String MODID = "genindustry_bees";
    public static final Logger LOGGER = LogUtils.getLogger();
    public GenIndustryBees(FMLJavaModLoadingContext context){
        IEventBus modEventBus = context.getModEventBus();

        modCreativeTab.register(modEventBus);
        modItems.register(modEventBus);
        modBlocks.register(modEventBus);
        modRecipes.register(modEventBus);
        modBlockEntities.register(modEventBus);
        modMenuTypes.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        labwareHelper.registerdefault();
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //Machine GUI Registering
            MenuScreens.register(modMenuTypes.GENOME_EXTRACTOR_MENU.get(), genomeExtractorScreen::new);
            MenuScreens.register(modMenuTypes.GENETIC_IMPRINTER_MENU.get(), geneticImprinterScreen::new);

            //Genetic Template Custom Model
            ItemProperties.register(modItems.GENETIC_TEMPLATE.get(), new ResourceLocation(MODID, "gene_count"),
                    (stack, level, holder, i)->  (1.0f / ComplicatedBees.GENE_REGISTRY.get().getKeys().size()) * geneticTemplateItem.getGeneCount(stack));
        }
        @SubscribeEvent
        public static void registerClientColors(RegisterColorHandlersEvent.Item event){
            event.register(
                    (itemStack, layer) ->
                         layer == 0 && itemStack.hasTag() ?
                                 geneColors.GENE_COLORS.getOrDefault(ComplicatedBees.GENE_REGISTRY.get().getValue(new ResourceLocation(itemStack.getTag().getString(geneSampleItem.KEY_GENE_NAME))), 0)
                                 :0xfff5f5f5
                        ,modItems.GENE_SAMPLE.get()
            );
        }
    }
}

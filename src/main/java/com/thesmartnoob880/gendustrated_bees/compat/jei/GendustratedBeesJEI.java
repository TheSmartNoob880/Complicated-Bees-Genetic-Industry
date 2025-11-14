package com.thesmartnoob880.gendustrated_bees.compat.jei;

import com.accbdd.complicated_bees.bees.Species;
import com.accbdd.complicated_bees.registry.SpeciesRegistration;
import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import com.thesmartnoob880.gendustrated_bees.block.modBlocks;
import com.thesmartnoob880.gendustrated_bees.item.modCreativeTab;
import com.thesmartnoob880.gendustrated_bees.item.modItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@JeiPlugin
public class GendustratedBeesJEI implements IModPlugin {
    public GendustratedBeesJEI(){

    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(GendustratedBees.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new genomeExtractorCategory(helper));
        registration.addRecipeCategories(new geneticImprinterCategory(helper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<Species> species = Minecraft.getInstance().getConnection().registryAccess().registry(SpeciesRegistration.SPECIES_REGISTRY_KEY).get().stream().toList();
        Stream<ItemStack> speciesTemplate = species.stream().map(JEIChromosomes::completeTemplate);
        ItemStack[] geneSamples = modCreativeTab.POSSIBLE_SAMPLES.get().getDisplayItems().toArray(new ItemStack[]{});
        registration.addRecipes(genomeExtractorCategory.TYPE, species);
        registration.addRecipes(geneticImprinterCategory.TYPE, species);
        registration.addRecipes(RecipeTypes.SMELTING, List.of(
                new SmeltingRecipe(new ResourceLocation(GendustratedBees.MODID, "jei.gene_recycle"), "", CookingBookCategory.MISC, Ingredient.of(geneSamples), modItems.GENE_SAMPLE.get().getDefaultInstance(), 0.1f, 200),
                new SmeltingRecipe(new ResourceLocation(GendustratedBees.MODID, "jei.template_recycle"), "", CookingBookCategory.MISC, Ingredient.of(speciesTemplate), modItems.GENETIC_TEMPLATE.get().getDefaultInstance(), 0.1f, 200)
        ));
        List<ItemStack> templateAndSamples = new ArrayList<>(List.of(modItems.GENETIC_TEMPLATE.get().getDefaultInstance()));
        templateAndSamples.addAll(List.of(geneSamples));
        registration.addIngredientInfo(templateAndSamples, VanillaTypes.ITEM_STACK, Component.translatable("gendustrated_bees.jei.info.add_gene_data"));
        registration.addIngredientInfo(List.of(modItems.GENE_SAMPLE.get().getDefaultInstance(), modItems.GENETIC_TEMPLATE.get().getDefaultInstance()), VanillaTypes.ITEM_STACK, Component.translatable("gendustrated_bees.jei.info.recycle"));
        registration.addIngredientInfo(List.of(modBlocks.GENETIC_IMPRINTER.get().asItem().getDefaultInstance()), VanillaTypes.ITEM_STACK, Component.translatable("gendustrated_bees.jei.info.genetic_imprinter.examples"));
        registration.addIngredientInfo(List.of(modBlocks.GENOME_EXTRACTOR.get().asItem().getDefaultInstance()), VanillaTypes.ITEM_STACK, Component.translatable("gendustrated_bees.jei.info.genome_extractor.examples"));
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        IModPlugin.super.onRuntimeAvailable(jeiRuntime);
        List<ResourceLocation> var1 = List.of(new ResourceLocation(GendustratedBees.MODID, "gene_recycle"), new ResourceLocation(GendustratedBees.MODID, "template_recycle")) ;
        jeiRuntime.getRecipeManager().hideRecipes(RecipeTypes.SMELTING, jeiRuntime.getRecipeManager().createRecipeLookup(RecipeTypes.SMELTING).get().filter(smeltingRecipe -> var1.contains(smeltingRecipe.getId())).toList());
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.useNbtForSubtypes(modItems.GENE_SAMPLE.get());
    }


    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(modBlocks.GENOME_EXTRACTOR.get().asItem(), genomeExtractorCategory.TYPE);
        registration.addRecipeCatalyst(modBlocks.GENETIC_IMPRINTER.get().asItem(), geneticImprinterCategory.TYPE);

    }

    @Override
    public void registerGuiHandlers(@NotNull IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }

    public static IDrawable createDrawable(final ResourceLocation location, final int uOffset, final int vOffset, final int width, final int height, final int textureWidth, final int textureHeight) {
        return new IDrawable() {
            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }

            public void draw(@NotNull GuiGraphics guiGraphics, int xOffset, int yOffset) {
                guiGraphics.blit(location, xOffset, yOffset, (float)uOffset, (float)vOffset, this.getWidth(), this.getHeight(), textureWidth, textureHeight);
            }
        };
    }
}
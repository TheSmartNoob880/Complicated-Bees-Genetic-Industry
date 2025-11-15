package com.thesmartnoob880.genindustry_bees.compat.jei;

import com.accbdd.complicated_bees.bees.Species;
import com.thesmartnoob880.genindustry_bees.GenIndustryBees;
import com.thesmartnoob880.genindustry_bees.block.modBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class geneticImprinterCategory implements IRecipeCategory<Species> {

    private static final ResourceLocation ID = new ResourceLocation(GenIndustryBees.MODID, "jei/genetic_imprinter");
    public static final RecipeType<Species> TYPE;
    private static final Component TITLE;
    private final IDrawable ICON;
    private final IDrawable BACKGROUND = GenIndustryBeesJEI.createDrawable(new ResourceLocation("genindustry_bees", "textures/gui/jei/genetic_imprinter_jei.png"), 0, 0, 143, 64, 143, 64);

    public geneticImprinterCategory(IGuiHelper helper){
        this.ICON = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(modBlocks.GENETIC_IMPRINTER.get().asItem()));
    }

    @Override
    public @NotNull RecipeType<Species> getRecipeType() {return TYPE;}

    @Override
    public @NotNull Component getTitle() {return TITLE;}

    @Override
    public IDrawable getBackground() {return this.BACKGROUND;}

    @Override
    public IDrawable getIcon() {return this.ICON;}

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, @NotNull Species species, @NotNull IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 17, 24).setSlotName("input_bees").addIngredients(VanillaTypes.ITEM_STACK, JEIChromosomes.allValidBees());
        builder.addSlot(RecipeIngredientRole.INPUT, 45, 24).setSlotName("example_template").addIngredient(VanillaTypes.ITEM_STACK, JEIChromosomes.completeTemplate(species));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 24).setSlotName("output_bee").addIngredients(VanillaTypes.ITEM_STACK, JEIChromosomes.validBees(species));
    }

    static {
        TYPE = new RecipeType<>(ID, Species.class);
        TITLE = Component.translatable("gui.genindustry_bees.jei.genetic_imprinter");
    }
}

package com.thesmartnoob880.genindustry_bees.recipe;

import com.thesmartnoob880.genindustry_bees.item.geneSampleItem;
import com.thesmartnoob880.genindustry_bees.item.modItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class RecipeGeneticTemplate extends CustomRecipe {
    public RecipeGeneticTemplate(ResourceLocation pID, CraftingBookCategory pCatagory) {
        super(pID, pCatagory);
    }


    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean hasSample= false;
        boolean hasTemplate=false;

        for (ItemStack stack: container.getItems()){
            if (stack.getItem()== modItems.GENE_SAMPLE.get()){
                if (hasSample) return false;
                hasSample=true;
            } else if (stack.getItem()== modItems.GENETIC_TEMPLATE.get()) {
                if (hasTemplate) return false;
                hasTemplate=true;
            }
        }
        return hasSample && hasTemplate;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack template = null;
        CompoundTag tag = null;
        for (ItemStack stack: container.getItems()) {
            if ((stack.is(modItems.GENE_SAMPLE.get())) && (stack.hasTag())){
                tag = stack.getTag();
            } else if (stack.is(modItems.GENETIC_TEMPLATE.get())) {
                template = stack;
            }
        }
        if (template != null && tag != null && tag.contains(geneSampleItem.KEY_GENE_NAME) && tag.contains(geneSampleItem.KEY_GENE_VALUE)){
            ItemStack templateCopy = template.copyWithCount(1);
            templateCopy.getOrCreateTag().put(tag.getString(geneSampleItem.KEY_GENE_NAME), tag.getCompound(geneSampleItem.KEY_GENE_VALUE));
            return templateCopy;
        }else{
            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return i >= 2 || i1 >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return modRecipes.GENETIC_TEMPLATE.get();
    }
}

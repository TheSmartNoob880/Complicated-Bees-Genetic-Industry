package com.thesmartnoob880.genindustry_bees.recipe;

import com.google.gson.JsonObject;
import com.thesmartnoob880.genindustry_bees.item.IGeneticHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class GeneWipingFurnaceRecipe extends SmeltingRecipe {
    public GeneWipingFurnaceRecipe(ResourceLocation pID, ItemStack pResult, int pCookTime) {
        super(pID, "", CookingBookCategory.MISC, Ingredient.of(pResult) , pResult, 0.1f, pCookTime);
    }

    @Override
    public boolean matches(Container pContainer, Level p_43749_) {
        ItemStack input = pContainer.getItem(0);
        return super.matches(pContainer, p_43749_)&& input.getItem() instanceof IGeneticHolder holder && holder.hasGeneticData(input);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return modRecipes.FURNACE_RECYCLING.get();
    }

    public static class Serializer implements RecipeSerializer<GeneWipingFurnaceRecipe> {

        @Override
        public GeneWipingFurnaceRecipe fromJson(ResourceLocation pLocation, JsonObject pDataFile) {
            return new GeneWipingFurnaceRecipe(pLocation, Ingredient.valueFromJson(pDataFile.getAsJsonObject("item")).getItems().stream().findFirst().get(), pDataFile.get("time").getAsInt());
        }

        @Override
        public @Nullable GeneWipingFurnaceRecipe fromNetwork(ResourceLocation pID, FriendlyByteBuf pBuf) {
            return new GeneWipingFurnaceRecipe(pID, pBuf.readItem(), pBuf.readInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuf, GeneWipingFurnaceRecipe pRecipe) {
            pBuf.writeItemStack(pRecipe.result, false);
            pBuf.writeInt(pRecipe.cookingTime);
        }
    }
}

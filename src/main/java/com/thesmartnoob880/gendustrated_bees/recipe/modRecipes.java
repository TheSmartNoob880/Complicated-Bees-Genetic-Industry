package com.thesmartnoob880.gendustrated_bees.recipe;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class modRecipes {
    public static final DeferredRegister<RecipeType<?>> TYPE_DEF_REG = DeferredRegister.create(Registries.RECIPE_TYPE, GendustratedBees.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> DEF_REG = DeferredRegister.create(Registries.RECIPE_SERIALIZER, GendustratedBees.MODID);

    public static final RegistryObject<RecipeSerializer<?>> GENETIC_TEMPLATE = DEF_REG.register("genetic_template_with_data", () -> new SimpleCraftingRecipeSerializer<>(RecipeGeneticTemplate::new));
    public static final RegistryObject<RecipeSerializer<?>> FURNACE_RECYCLING = DEF_REG.register("furnace_recycling", GeneWipingFurnaceRecipe.Serializer::new);
    public static void  register(IEventBus eventBus){
        DEF_REG.register(eventBus);
    }
}

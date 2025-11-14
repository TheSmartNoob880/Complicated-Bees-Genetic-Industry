package com.thesmartnoob880.gendustrated_bees.util;

import com.thesmartnoob880.gendustrated_bees.item.modItems;
import net.minecraft.world.item.Item;
import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class labwareHelper {
    private static final Map<Item, Double> labwareExtractionRates = new HashMap<>();

    public static void register(Item item, Double pdouble){
        if (labwareExtractionRates.containsKey(item))
            GendustratedBees.LOGGER.warn("item{} is already registered as labware. Previous Rate:{}, New Rate:{}", item.getDescriptionId(), labwareExtractionRates.get(item), pdouble);
        labwareExtractionRates.put(item,pdouble);
    }
    public static void registerdefault() {
        labwareHelper.register(modItems.BASIC_LABWARE.get(), 0.5d);
        labwareHelper.register(modItems.IMPROVED_LABWARE.get(), 0.75d);
        labwareHelper.register(modItems.ADVANCED_LABWARE.get(),1.0d);
    }
    public static double getLabwareRate(Item item){
        return labwareExtractionRates.getOrDefault(item,0.0d);
    }

    public static Set<Item> getItems(){
        return labwareExtractionRates.keySet();
    }
}

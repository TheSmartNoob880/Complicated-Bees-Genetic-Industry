package com.thesmartnoob880.genindustry_bees.util.enums;

import com.thesmartnoob880.genindustry_bees.GenIndustryBees;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public enum EnumErrorCodesExtractor {
    NO_RF("no_rf", 1),
    NO_BEES("no_bees", 2),
    NO_GENE("no_genes", 4),
    NO_LABWARE("no_labware", 8),
    OUTPUT_FULL("output_full", 16);

    public final String name;
    public final int value;

    EnumErrorCodesExtractor(String name, int value){
        this.name = name;
        this.value = value;
    }

    public Component getExtractorMessage(){
        return Component.translatable("gui."+ GenIndustryBees.MODID+".gui.genome_extractor.errors."+this.name.toLowerCase(Locale.ENGLISH));
    }
}

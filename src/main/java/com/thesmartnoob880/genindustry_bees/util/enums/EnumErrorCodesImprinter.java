package com.thesmartnoob880.genindustry_bees.util.enums;

import com.thesmartnoob880.genindustry_bees.GenIndustryBees;
import net.minecraft.network.chat.Component;

import java.util.Locale;

public enum EnumErrorCodesImprinter {
    NO_RF("no_rf", 1),
    NO_BEES("no_bees", 2),
    NO_TEMPLATE("no_template", 4);

    public final String name;
    public final int value;

    EnumErrorCodesImprinter(String name, int value){
        this.name = name;
        this.value = value;
    }

    public Component getImprinterMessage(){
        return Component.translatable("gui."+ GenIndustryBees.MODID+".gui.genetic_imprinter.errors."+this.name.toLowerCase(Locale.ENGLISH));
    }
}

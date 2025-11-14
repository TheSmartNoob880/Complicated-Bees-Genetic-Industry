package com.thesmartnoob880.gendustrated_bees.config;

import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = GendustratedBees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue IMPRINTER_RF_COST = BUILDER
            .comment("How much RF/t an unupgraded Genetic Imprinter should use while processing a recipe.")
            .comment("Will be capped at 1/2 the RF capacity.")
            .defineInRange("imprinterBaseRF", 1000, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue IMPRINTER_RF_CAPACITY = BUILDER
            .comment("Base RF capacity of the Genetic Imprinter.")
            .defineInRange("imprinterRFCapacity", 500000, 2, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue IMPRINTER_BASE_SPEED = BUILDER
            .comment("How many ticks an unupgraded Genetic Imprinter takes to complete a craft.")
            .defineInRange("imprinterBaseSpeed", 100, 2, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EXTRACTOR_RF_COST = BUILDER
            .comment("How much RF/t an unupgraded Genome Extractor should use while processing a recipe.")
            .comment("Will be capped at 1/2 the RF capacity.")
            .defineInRange("extractorBaseRF", 500, 1, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EXTRACTOR_RF_CAPACITY = BUILDER
            .comment("Base RF capacity of the Genome Extractor.")
            .defineInRange("extractorRFCapacity", 100000, 2, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EXTRACTOR_BASE_SPEED = BUILDER
            .comment("How many ticks an unupgraded Genome Extractor takes to complete a craft.")
            .defineInRange("extractorBaseSpeed", 100, 2, Integer.MAX_VALUE);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int imprinterBaseRF;
    public static int imprinterRFCapacity;
    public static int imprinterBaseSpeed;
    public static int extractorBaseRF;
    public static int extractorRFCapacity;
    public static int extractorBaseSpeed;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

        imprinterRFCapacity = IMPRINTER_RF_CAPACITY.get();
        imprinterBaseRF = Math.min(IMPRINTER_RF_COST.get(), imprinterRFCapacity/2);
        imprinterBaseSpeed = IMPRINTER_BASE_SPEED.get();
        extractorRFCapacity = EXTRACTOR_RF_CAPACITY.get();
        extractorBaseRF = Math.min(EXTRACTOR_RF_COST.get(), extractorRFCapacity/2);
        extractorBaseSpeed = EXTRACTOR_BASE_SPEED.get();
    }
}

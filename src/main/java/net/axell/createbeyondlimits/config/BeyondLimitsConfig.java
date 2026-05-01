package net.axell.createbeyondlimits.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class BeyondLimitsConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Mechanics Category
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_NATURAL_BREEDING;
    public static final ForgeConfigSpec.ConfigValue<Double> BREEDING_CHANCE_PERCENT;

    // Fragrance Category
    public static final ForgeConfigSpec.ConfigValue<Integer> FRAGRANCE_REBIND_DAMAGE;

    static {
        // --- ANIMAL MECHANICS ---
        BUILDER.push("Animal Mechanics");

        ENABLE_NATURAL_BREEDING = BUILDER
                .comment("Whether animals should randomly fall in love and breed on their own.")
                .define("enableNaturalBreeding", true);

        BREEDING_CHANCE_PERCENT = BUILDER
                .comment("The percentage chance (0.0 to 100.0) for an animal to seek a partner every 20 seconds.")
                .defineInRange("breedingChancePercent", 0.33, 0.0, 100.0);

        BUILDER.pop();

        // --- FRAGRANCE SETTINGS ---
        BUILDER.push("Fragrance System");

        FRAGRANCE_REBIND_DAMAGE = BUILDER
                .comment("The amount of damage (in half-hearts) dealt when trying to bind to a new fragrance while already bound.")
                .defineInRange("rebindDamage", 2, 0, 20);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "createbeyondlimits-common.toml");
    }
}
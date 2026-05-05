package net.axell.createbeyondlimits.effect;

import net.axell.createbeyondlimits.BeyondLimits;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    // This list tells Forge: "Here are the new status effects for our mod"
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BeyondLimits.MOD_ID);

    // This specifically registers our "Blessed" effect
    public static final RegistryObject<MobEffect> BLESSED = MOB_EFFECTS.register("blessed",
            BlessedEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
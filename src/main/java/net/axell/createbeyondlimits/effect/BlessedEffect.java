package net.axell.createbeyondlimits.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class BlessedEffect extends MobEffect {
    public BlessedEffect() {
        // A bright Golden/Yellow color for the particles (0xFFD700)
        super(MobEffectCategory.BENEFICIAL, 0xFFD700);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            // Resistance 4 (Hidden)
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 3, true, false, false));

            // Regeneration 3 (Hidden)
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 2, true, false, false));

            // Haste 2 (Hidden)
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 40, 1, true, false, false));

            // Speed 3 (Hidden)
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 1, true, false, false));

            // Strength 3 (Hidden)
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 2, true, false, false));

            // Absorption (Hidden)
            if (!entity.hasEffect(MobEffects.ABSORPTION)) {
                // We set the last 'false' so the blue shield/heart icon doesn't show up
                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1, true, false, false));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Run the logic above every 20 ticks (1 second)
        return duration % 20 == 0;
    }
}
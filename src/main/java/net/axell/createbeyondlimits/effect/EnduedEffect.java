package net.axell.createbeyondlimits.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class EnduedEffect extends MobEffect {
    public EnduedEffect(MobEffectCategory category, int color) {
        super(category, color);

        // 1. UNSTOPPABLE: 100% Knockback Resistance (Attribute ID is unique)
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                "7107DE5E-7CE8-4030-940E-514C1F160890", 1.0D, AttributeModifier.Operation.ADDITION);

        // 2. ZENITH STRENGTH: High damage boost (Equivalent to Strength II+)
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "648D1D34-F1D6-4A59-BB29-1F6B19E864A4", 6.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.clearFire();
            player.setRemainingFireTicks(0);

            // Use tags instead of .isFire()
            if (player.hurtTime > 0 && player.getLastDamageSource() != null &&
                    player.getLastDamageSource().is(net.minecraft.tags.DamageTypeTags.IS_FIRE)) {
                player.hurtTime = 0;
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // We want this to check every single tick for fire clearing
        return true;
    }
}
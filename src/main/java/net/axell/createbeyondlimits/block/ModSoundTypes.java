package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.sound.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class ModSoundTypes {
    public static final SoundType AROMA = new SoundType(
            1.0f,
            1.0f,
            SoundEvents.STONE_BREAK,
            SoundEvents.STONE_STEP,
            SoundEvents.STONE_PLACE,
            SoundEvents.STONE_HIT,
            SoundEvents.STONE_FALL
    );
}

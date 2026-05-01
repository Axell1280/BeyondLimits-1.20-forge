package net.axell.createbeyondlimits.sound;

import net.axell.createbeyondlimits.block.custom.AromaBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;

public class AromaAmbientSoundInstance extends AbstractTickableSoundInstance {
    private final BlockPos pos;
    private final double maxDistance;

    public AromaAmbientSoundInstance(BlockPos pos, double range) {
        super(ModSounds.AROMA_AMBIENT.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.pos = pos;
        this.maxDistance = range;

        // We still need the coordinates for the distance math!
        this.x = pos.getX() + 0.5D;
        this.y = pos.getY() + 0.5D;
        this.z = pos.getZ() + 0.5D;

        this.relative = true; // 2D Ambient (Stereo stays Stereo)
        this.looping = true;
        this.delay = 0;
        this.volume = 0.4F; // The "Atmosphere"
    }

    @Override
    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || !(mc.level.getBlockState(pos).getBlock() instanceof AromaBlock)) {
            this.stop();
            return;
        }

        double dist = Math.sqrt(mc.player.distanceToSqr(x, y, z));

        if (dist <= maxDistance) {
            double fadeStart = maxDistance * 0.9;
            if (dist <= fadeStart) {
                this.volume = 0.4F;
            } else {
                float fadeFactor = (float) ((maxDistance - dist) / (maxDistance - fadeStart));
                this.volume = fadeFactor * 0.4F;
            }
        } else {
            this.stop();
        }
    }
}

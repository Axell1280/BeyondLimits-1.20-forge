package net.axell.createbeyondlimits.sound;

import net.axell.createbeyondlimits.block.custom.AromaBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;

public class AromaCoreSoundInstance extends AbstractTickableSoundInstance {
    private final BlockPos pos;
    private final double maxDistance;

    public AromaCoreSoundInstance(BlockPos pos, double range) {
        super(ModSounds.AROMA_CORE.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
        this.pos = pos;
        this.maxDistance = range;

        // Physical position in the world
        this.x = pos.getX() + 0.5D;
        this.y = pos.getY() + 0.5D;
        this.z = pos.getZ() + 0.5D;

        this.relative = false; // 3D Directional
        this.looping = true;
        this.delay = 0;
        this.volume = 0.6F; // The "Body" of the sound
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
                this.volume = 0.6F;
            } else {
                float fadeFactor = (float) ((maxDistance - dist) / (maxDistance - fadeStart));
                this.volume = fadeFactor * 0.6F;
            }
        } else {
            this.stop();
        }
    }
}

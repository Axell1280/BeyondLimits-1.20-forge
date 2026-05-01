package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.custom.AromaBlock;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.axell.createbeyondlimits.sound.AromaCoreSoundInstance;
import net.axell.createbeyondlimits.sound.AromaAmbientSoundInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits", value = Dist.CLIENT)
public class AromaSoundHandler {

    // Now stores a List so we can track both the Core (Mono) and Ambient (Stereo) sounds per block
    private static final Map<BlockPos, List<AbstractTickableSoundInstance>> ACTIVE_SOUNDS = new HashMap<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        // Run check every 10 ticks to stay easy on your i3-4500 CPU
        if (mc.player.tickCount % 10 != 0) return;

        Set<BlockPos> positions = AromaRegistry.getPositions(mc.level.dimension());

        for (BlockPos pos : positions) {
            if (!mc.level.isLoaded(pos)) continue;

            BlockState state = mc.level.getBlockState(pos);
            if (state.getBlock() instanceof AromaBlock aromaBlock) {

                double range = (double) aromaBlock.getRadius();
                double distSq = mc.player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

                // Trigger if within the block's specific range (16, 32, or 64)
                if (distSq <= (range * range)) {
                    // Start sounds if they aren't already playing
                    if (!ACTIVE_SOUNDS.containsKey(pos) || !isGroupActive(mc, ACTIVE_SOUNDS.get(pos))) {

                        // 1. Create the Directional Mono Layer
                        AromaCoreSoundInstance core = new AromaCoreSoundInstance(pos, range);

                        // 2. Create the Wrapped Stereo Layer
                        AromaAmbientSoundInstance ambient = new AromaAmbientSoundInstance(pos, range);

                        // Start both in the Minecraft Sound Manager
                        mc.getSoundManager().play(core);
                        mc.getSoundManager().play(ambient);

                        // Track them as a pair
                        ACTIVE_SOUNDS.put(pos.immutable(), List.of(core, ambient));
                    }
                }
            }
        }

        // Clean up: remove the entry if the sounds have stopped or the block is gone
        ACTIVE_SOUNDS.entrySet().removeIf(entry -> !isGroupActive(mc, entry.getValue()));
    }

    /**
     * Helper to check if the sound group is still playing.
     * If the first sound (Core) stops, we consider the whole group finished.
     */
    private static boolean isGroupActive(Minecraft mc, List<AbstractTickableSoundInstance> sounds) {
        if (sounds == null || sounds.isEmpty()) return false;
        return mc.getSoundManager().isActive(sounds.get(0));
    }
}
package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits", value = Dist.CLIENT)
public class AromaSoundHandler {

    private static final int PLAY_INTERVAL = 40; // ticks between each sound (2 seconds)

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        ClientLevel level = mc.level;

        int time = (int) level.getGameTime();
        if (time % PLAY_INTERVAL != 0) return;

        // Get all Aroma blocks in this dimension
        Set<BlockPos> positions = AromaRegistry.getPositions(level.dimension());
        if (positions.isEmpty()) return;

        for (BlockPos pos : positions) {
            level.playLocalSound(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    net.axell.createbeyondlimits.sound.ModSounds.AROMA_AURA.get(), // your aroma_aura sound
                    SoundSource.BLOCKS,
                    0.5f,  // volume
                    1.0f,  // pitch
                    false  // distance delay
            );
        }
    }
}

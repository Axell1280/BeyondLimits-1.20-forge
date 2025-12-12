package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;

import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits", value = Dist.CLIENT)
public class AromaParticleHandler {

    private static final int PARTICLE_RATE = 6;
    private static final int RADIUS = 30;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        int time = (int) level.getGameTime();
        if (time % PARTICLE_RATE != 0) return;

        Set<BlockPos> positions = AromaRegistry.getPositions(level.dimension());

        for (BlockPos pos : positions) {

            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 1.1;
            double cz = pos.getZ() + 0.5;

            float r = 1, g = 1, b = 1;
            var block = level.getBlockState(pos).getBlock();

            if (block == ModBlocks.AROMA_SPEED_1.get() || block == ModBlocks.AROMA_SPEED_2.get())
                r = 0.3f; g = 0.6f; b = 1.0f;

            if (block == ModBlocks.AROMA_REGEN_1.get() || block == ModBlocks.AROMA_REGEN_2.get())
                r = 1.0f; g = 0.3f; b = 0.3f;

            if (block == ModBlocks.AROMA_STRENGTH_1.get() || block == ModBlocks.AROMA_STRENGTH_2.get())
                r = 0.7f; g = 0.2f; b = 1.0f;

            // Halo
            double radius = 1.1;
            int points = 20;

            for (int i = 0; i < points; i++) {
                double angle = (Math.PI * 2 * i) / points;

                double x = cx + Math.cos(angle) * radius;
                double y = pos.getY() + 0.1;
                double z = cz + Math.sin(angle) * radius;

                level.addParticle(
                        ParticleTypes.ENCHANT,
                        x, y, z,
                        0, 0, 0
                );
            }

            // Particles inside effect zone
            for (int i = 0; i < 5; i++) {
                double angle = Math.random() * Math.PI * 2;
                double dist = Math.random() * RADIUS;

                double x = cx + Math.cos(angle) * dist;
                double z = cz + Math.sin(angle) * dist;
                double y = cy + Math.random() * 2;

                level.addParticle(
                        new DustParticleOptions(new Vector3f(r, g, b), 1f),
                        x, y, z,
                        0, 0.01, 0
                );

                level.addParticle(
                        ParticleTypes.ELECTRIC_SPARK,
                        x, y, z,
                        0, 0.01, 0
                );
                        level.addParticle(
                        ParticleTypes.ENCHANT,
                        x, y, z,
                        0, 0.01, 0
                );
            }
        }
    }
}

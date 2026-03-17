package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;

import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits", value = Dist.CLIENT)
public class AromaParticleHandler {

    private static final int PARTICLE_RATE = 6;
    private static final int NORMAL_RADIUS = 30;
    private static final int SUPER_RADIUS = 64;

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
            BlockState state = level.getBlockState(pos);

            // Default to white
            float r = 1.0f, g = 1.0f, b = 1.0f;
            boolean isSupercharged = state.is(ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get());

            // Color Selection Logic
            if (state.is(ModBlocks.AROMA_SPEED_1.get()) || state.is(ModBlocks.AROMA_SPEED_2.get())) {
                r = 0.3f; g = 0.6f; b = 1.0f; // Blue
            } else if (state.is(ModBlocks.AROMA_REGEN_1.get()) || state.is(ModBlocks.AROMA_REGEN_2.get())) {
                r = 1.0f; g = 0.3f; b = 0.3f; // Red
            } else if (state.is(ModBlocks.AROMA_STRENGTH_1.get()) || state.is(ModBlocks.AROMA_STRENGTH_2.get())) {
                r = 0.7f; g = 0.2f; b = 1.0f; // Purple
            } else if (isSupercharged) {
                r = 1.0f; g = 0.85f; b = 0.1f; // Radiant Gold
            }

            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 1.1;
            double cz = pos.getZ() + 0.5;

            // 1. Halo Effect (Around the block base)
            double haloRadius = 1.1;
            int haloPoints = 20;
            for (int i = 0; i < haloPoints; i++) {
                double angle = (Math.PI * 2 * i) / haloPoints;
                double x = cx + Math.cos(angle) * haloRadius;
                double z = cz + Math.sin(angle) * haloRadius;

                level.addParticle(ParticleTypes.ENCHANT, x, pos.getY() + 0.1, z, 0, 0, 0);
            }

            // 2. Zone Particles (Floating in the air)
            int particleCount = isSupercharged ? 15 : 5; // Triple particles for Sanctum
            int currentRadius = isSupercharged ? SUPER_RADIUS : NORMAL_RADIUS;

            for (int i = 0; i < particleCount; i++) {
                double angle = Math.random() * Math.PI * 2;
                double dist = Math.random() * currentRadius;

                double px = cx + Math.cos(angle) * dist;
                double pz = cz + Math.sin(angle) * dist;
                double py = cy + Math.random() * 2.5;

                // Main colored dust
                level.addParticle(
                        new DustParticleOptions(new Vector3f(r, g, b), 1.2f),
                        px, py, pz, 0, 0.02, 0
                );

                // Add Sparks and extra magic if it's the Sanctum
                if (isSupercharged) {
                    level.addParticle(ParticleTypes.ELECTRIC_SPARK, px, py, pz, 0, 0.05, 0);
                    if (Math.random() > 0.7) {
                        level.addParticle(ParticleTypes.GLOW, px, py, pz, 0, 0.01, 0);
                    }
                } else {
                    // Normal magic sparkles
                    level.addParticle(ParticleTypes.ENCHANT, px, py, pz, 0, 0.01, 0);
                }
            }
        }
    }
}
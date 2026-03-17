package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits")
public class AromaTickHandler {

    private static final int NORMAL_RADIUS = 30;
    private static final int SUPER_RADIUS = 64; // Massive radius for the Sanctum
    private static final int EFFECT_DURATION = 200;

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        Level lvl = event.level;
        if (lvl.isClientSide() || event.phase != TickEvent.Phase.START) return;
        if (!(lvl instanceof ServerLevel serverLevel)) return;

        Set<BlockPos> positions = AromaRegistry.getPositions(serverLevel.dimension());
        if (positions.isEmpty()) return;

        BlockPos[] copy = positions.toArray(new BlockPos[0]);
        for (BlockPos pos : copy) {
            if (!serverLevel.isLoaded(pos)) continue;

            BlockState state = serverLevel.getBlockState(pos);
            Block block = state.getBlock();

            MobEffect effectToApply = null;
            int amplifier = 0;
            int currentRadius = NORMAL_RADIUS;

            // Logic for Sanctum
            if (block == ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get()) {
                effectToApply = ModEffects.BLESSED.get();
                amplifier = 0;
                currentRadius = SUPER_RADIUS;
            }
            // Logic for Speed
            else if (block == ModBlocks.AROMA_SPEED_1.get()) { effectToApply = MobEffects.MOVEMENT_SPEED; amplifier = 0; }
            else if (block == ModBlocks.AROMA_SPEED_2.get()) { effectToApply = MobEffects.MOVEMENT_SPEED; amplifier = 1; }
            // Logic for Regen
            else if (block == ModBlocks.AROMA_REGEN_1.get()) { effectToApply = MobEffects.REGENERATION; amplifier = 0; }
            else if (block == ModBlocks.AROMA_REGEN_2.get()) { effectToApply = MobEffects.REGENERATION; amplifier = 1; }
            // Logic for Strength
            else if (block == ModBlocks.AROMA_STRENGTH_1.get()) { effectToApply = MobEffects.DAMAGE_BOOST; amplifier = 0; }
            else if (block == ModBlocks.AROMA_STRENGTH_2.get()) { effectToApply = MobEffects.DAMAGE_BOOST; amplifier = 1; }

            if (effectToApply == null) {
                // This was deleting your Sanctum because it didn't recognize it!
                AromaRegistry.remove(serverLevel.dimension(), pos);
                continue;
            }

            // AABB Box using the dynamic radius
            AABB area = new AABB(pos).inflate(currentRadius);
            var players = serverLevel.getEntitiesOfClass(Player.class, area);

            for (Player p : players) {
                MobEffectInstance current = p.getEffect(effectToApply);
                // Apply if missing or about to expire
                if (current == null || current.getDuration() <= 40) {
                    p.addEffect(new MobEffectInstance(effectToApply, EFFECT_DURATION, amplifier, true, false, true));
                }
            }
        }
    }
}
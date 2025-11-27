package net.axell.createbeyondlimits.event;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

@Mod.EventBusSubscriber(modid = "createbeyondlimits")
public class AromaTickHandler {

    private static final int RADIUS = 30;
    private static final int EFFECT_DURATION = 200; // 10 seconds in ticks

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        Level lvl = event.level;
        if (lvl.isClientSide() || event.phase != TickEvent.Phase.START) return;
        if (!(lvl instanceof ServerLevel serverLevel)) return;

        // Get registered aroma block positions for this dimension
        Set<BlockPos> positions = AromaRegistry.getPositions(serverLevel.dimension());
        if (positions.isEmpty()) return;

        BlockPos[] copy = positions.toArray(new BlockPos[0]);
        for (BlockPos pos : copy) {
            if (!serverLevel.isLoaded(pos)) continue;

            BlockState state = serverLevel.getBlockState(pos);
            Block block = state.getBlock();

            MobEffectInstance effectInstance = null;
            int amplifier = 0;

            if (block == ModBlocks.AROMA_SPEED_1.get()) amplifier = 0;
            else if (block == ModBlocks.AROMA_SPEED_2.get()) amplifier = 1;
            else if (block == ModBlocks.AROMA_REGEN_1.get()) amplifier = 0;
            else if (block == ModBlocks.AROMA_REGEN_2.get()) amplifier = 1;
            else if (block == ModBlocks.AROMA_STRENGTH_1.get()) amplifier = 0;
            else if (block == ModBlocks.AROMA_STRENGTH_2.get()) amplifier = 1;

            if (block == ModBlocks.AROMA_SPEED_1.get() || block == ModBlocks.AROMA_SPEED_2.get())
                effectInstance = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, EFFECT_DURATION, amplifier, true, false, true);
            else if (block == ModBlocks.AROMA_REGEN_1.get() || block == ModBlocks.AROMA_REGEN_2.get())
                effectInstance = new MobEffectInstance(MobEffects.REGENERATION, EFFECT_DURATION, amplifier, true, false, true);
            else if (block == ModBlocks.AROMA_STRENGTH_1.get() || block == ModBlocks.AROMA_STRENGTH_2.get())
                effectInstance = new MobEffectInstance(MobEffects.DAMAGE_BOOST, EFFECT_DURATION, amplifier, true, false, true);

            if (effectInstance == null) {
                AromaRegistry.remove(serverLevel.dimension(), pos);
                continue;
            }

            // Apply to players inside radius only if the effect is missing or about to expire
            var players = serverLevel.getEntitiesOfClass(Player.class,
                    new AABB(pos.getX() - RADIUS, pos.getY() - RADIUS, pos.getZ() - RADIUS,
                            pos.getX() + RADIUS + 1, pos.getY() + RADIUS + 1, pos.getZ() + RADIUS + 1));

            if (!players.isEmpty()) {
                for (Player p : players) {
                    MobEffectInstance current = p.getEffect(effectInstance.getEffect());
                    if (current == null || current.getDuration() <= 20) { // reapply if <1s remaining
                        p.addEffect(new MobEffectInstance(effectInstance.getEffect(),
                                EFFECT_DURATION, amplifier, true, false, true));
                    }
                }

                // Debug one message per aroma block when it affects players
                players.get(0).sendSystemMessage(Component.literal(
                        "AromaBlock " + ForgeRegistries.BLOCKS.getKey(block) + " applied effect to " + players.size() + " player(s)"
                ));
            }
        }
    }
}

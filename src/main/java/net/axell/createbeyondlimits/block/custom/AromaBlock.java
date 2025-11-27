package net.axell.createbeyondlimits.block.custom;

import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AromaBlock extends Block {

    private final MobEffect effect;
    private final int amplifier;
    private final int radius;

    public AromaBlock(Properties properties, MobEffect effect, int amplifier, int radius) {
        super(properties);
        this.effect = effect;
        this.amplifier = amplifier;
        this.radius = radius;
    }

    public MobEffect getEffect() { return effect; }
    public int getAmplifier() { return amplifier; }
    public int getRadius() { return radius; }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide()) {
            ResourceKey<Level> dim = level.dimension();
            AromaRegistry.add(dim, pos);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
        if (!level.isClientSide()) {
            ResourceKey<Level> dim = level.dimension();
            // If the block changed to the same block, don't remove (vanilla behavior)
            if (state.getBlock() != newState.getBlock()) {
                AromaRegistry.remove(dim, pos);
            }
        }
    }
}

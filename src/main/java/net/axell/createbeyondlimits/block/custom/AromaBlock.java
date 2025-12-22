package net.axell.createbeyondlimits.block.custom;

import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AromaBlock extends Block {

    public final VoxelShape shape = Shapes.or(
        Block.box(0D, 0D, 0D, 16D, 2D, 16D),
        Block.box(2D, 2D, 2D, 14D, 13D, 14D),
        Block.box(0D, 13D, 0D, 16D, 15D, 16D)
    );
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

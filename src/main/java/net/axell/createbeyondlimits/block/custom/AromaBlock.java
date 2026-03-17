package net.axell.createbeyondlimits.block.custom;

import net.axell.createbeyondlimits.client.AromaBlockEntity;
import net.axell.createbeyondlimits.util.AromaRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AromaBlock extends Block {


    public final VoxelShape shape = Shapes.or(
            // Base: 16x16 and 2 units high (Y: 0 to 2)
            Block.box(0D, 0D, 0D, 16D, 2D, 16D),

            // Middle: 14x14 and 7 units high (Y: 2 to 10)
            Block.box(1D, 2D, 1D, 15D, 10D, 15D),

            // Top: 16x16 and 3 units high (Y: 10 to 14)
            Block.box(0D, 10D, 0D, 16D, 14D, 16D)
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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide && placer instanceof Player player) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof AromaBlockEntity aromaBE) {
                aromaBE.setOwner(player.getUUID());
            }
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

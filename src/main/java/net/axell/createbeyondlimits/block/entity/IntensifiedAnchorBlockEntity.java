package net.axell.createbeyondlimits.block.entity;

import net.axell.createbeyondlimits.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IntensifiedAnchorBlockEntity extends BlockEntity {
    public IntensifiedAnchorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.INTENSIFIED_ANCHOR.get(), pPos, pBlockState);
    }
}
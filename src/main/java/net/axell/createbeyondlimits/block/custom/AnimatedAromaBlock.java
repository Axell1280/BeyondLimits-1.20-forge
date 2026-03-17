package net.axell.createbeyondlimits.block.custom;

import net.axell.createbeyondlimits.block.ModBlockEntities;
import net.axell.createbeyondlimits.client.AromaBlockEntity;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class AnimatedAromaBlock extends AromaBlock implements EntityBlock {
    // We change the type to Supplier<MobEffect> so it can handle both Vanilla and Custom effects safely
    private final Supplier<MobEffect> effectSupplier;

    public AnimatedAromaBlock(Properties properties, Supplier<MobEffect> effectSupplier, int amplifier, int radius) {
        super(properties, null, amplifier, radius); // Pass null to parent, we handle it here
        this.effectSupplier = effectSupplier;
    }

    // Overloaded constructor so you don't have to change all your vanilla aroma blocks!
    public AnimatedAromaBlock(Properties properties, MobEffect vanillaEffect, int amplifier, int radius) {
        super(properties, vanillaEffect, amplifier, radius);
        this.effectSupplier = () -> vanillaEffect;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.shape;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        MobEffect currentEffect = effectSupplier.get();

        if (currentEffect == ModEffects.BLESSED.get()) {
            return new AromaBlockEntity(ModBlockEntities.SUPERCHARGED_AROMA_BE.get(), pos, state);
        } else if (currentEffect == MobEffects.REGENERATION) {
            return new AromaBlockEntity(ModBlockEntities.AROMA_REGEN.get(), pos, state);
        } else if (currentEffect == MobEffects.MOVEMENT_SPEED) {
            return new AromaBlockEntity(ModBlockEntities.AROMA_SPEED.get(), pos, state);
        } else {
            return new AromaBlockEntity(ModBlockEntities.AROMA_STRENGTH.get(), pos, state);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
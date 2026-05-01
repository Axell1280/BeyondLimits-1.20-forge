package net.axell.createbeyondlimits.block.custom;

import net.axell.createbeyondlimits.block.ModBlockEntities;
import net.axell.createbeyondlimits.world.SoulLinkData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class IntensifiedAnchorBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 24.0D, 15.0D);

    public IntensifiedAnchorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide && pPlacer instanceof Player player && pLevel instanceof ServerLevel serverLevel) {
            // Dimension Check First
            if (pLevel.dimension() != Level.NETHER) {
                pLevel.destroyBlock(pPos, true);
                return;
            }

            SoulLinkData data = SoulLinkData.get(serverLevel);

            data.addLink(player.getUUID(), "anchor", pPos);
            player.displayClientMessage(Component.literal("§dThe Anchor is set."), true);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.INTENSIFIED_ANCHOR.get().create(pPos, pState);
    }
}
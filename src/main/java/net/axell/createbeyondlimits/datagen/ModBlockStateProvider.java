package net.axell.createbeyondlimits.datagen;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BeyondLimits.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AROMA_REGEN_1);
        blockWithItem(ModBlocks.AROMA_REGEN_2);

        blockWithItem(ModBlocks.AROMA_SPEED_1);
        blockWithItem(ModBlocks.AROMA_SPEED_2);

        blockWithItem(ModBlocks.AROMA_STRENGTH_1);
        blockWithItem(ModBlocks.AROMA_STRENGTH_2);
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

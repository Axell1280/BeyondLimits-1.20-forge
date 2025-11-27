package net.axell.createbeyondlimits.datagen.loot;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.AROMA_REGEN_1.get());
        this.dropSelf(ModBlocks.AROMA_REGEN_2.get());
        this.dropSelf(ModBlocks.AROMA_SPEED_1.get());
        this.dropSelf(ModBlocks.AROMA_SPEED_2.get());
        this.dropSelf(ModBlocks.AROMA_STRENGTH_1.get());
        this.dropSelf(ModBlocks.AROMA_STRENGTH_2.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

package net.axell.createbeyondlimits.datagen;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BeyondLimits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {



        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AROMA_REGEN_1.get(),
                        ModBlocks.AROMA_REGEN_2.get(),
                        ModBlocks.AROMA_SPEED_1.get(),
                        ModBlocks.AROMA_SPEED_2.get(),
                        ModBlocks.AROMA_STRENGTH_1.get(),
                        ModBlocks.AROMA_STRENGTH_2.get(),
                        ModBlocks.PHOSPHATE_ORE.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.PHOSPHATE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AROMA_REGEN_1.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.AROMA_REGEN_2.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AROMA_SPEED_1.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.AROMA_SPEED_2.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AROMA_STRENGTH_1.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.AROMA_STRENGTH_2.get());
    }
}

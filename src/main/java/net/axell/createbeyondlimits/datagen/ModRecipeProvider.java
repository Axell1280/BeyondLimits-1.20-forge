package net.axell.createbeyondlimits.datagen;

import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModBlocks.AROMA_REGEN_1.get())
                .pattern(" A ")
                .pattern("ASA")
                .pattern(" A ")
                .define('A', Moditems.NETHER_STAR_FRAGMENT.get())
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Moditems.NETHER_STAR_FRAGMENT.get()),
                        has(Moditems.NETHER_STAR_FRAGMENT.get()))
                .save(pWriter);

    }
}

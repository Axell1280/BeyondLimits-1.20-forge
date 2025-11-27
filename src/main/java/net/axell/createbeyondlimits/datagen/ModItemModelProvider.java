package net.axell.createbeyondlimits.datagen;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BeyondLimits.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(Moditems.AROMA_REGEN_1_ITEM);
        simpleItem(Moditems.AROMA_REGEN_2_ITEM);
        simpleItem(Moditems.AROMA_SPEED_1_ITEM);
        simpleItem(Moditems.AROMA_SPEED_2_ITEM);
        simpleItem(Moditems.AROMA_STRENGTH_1_ITEM);
        simpleItem(Moditems.AROMA_STRENGTH_2_ITEM);
        simpleItem(Moditems.NETHER_STAR_FRAGMENT);
        simpleItem(Moditems.INCOMPLETE_COPPER_PIECE);
        simpleItem(Moditems.INCOMPLETE_FORGED_NETHERITE);
        simpleItem(Moditems.DRAINED_NETHER_STAR_FRAGMENT);
        simpleItem(Moditems.UNSTABLE_QUARTZ);
        simpleItem(Moditems.SIGNAL_MASSAGE_MUSIC_DISC);
        simpleItem(Moditems.PRESSURE_CORE);
        simpleItem(Moditems.FABRICATED_ELYTRA_PIECE);
        simpleItem(Moditems.WING);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(BeyondLimits.MOD_ID, "item/" + item.getId().getPath()));

    }

}

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
        simpleItem(Moditems.TOTEM_REGEN);
        simpleItem(Moditems.TOTEM_SPEED);
        simpleItem(Moditems.TOTEM_STRENGTH);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(BeyondLimits.MOD_ID, "item/" + item.getId().getPath()));

    }

}

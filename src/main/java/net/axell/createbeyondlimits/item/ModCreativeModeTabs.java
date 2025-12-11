package net.axell.createbeyondlimits.item;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondLimits.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BEYOND_LIMITS_TAB = CREATIVE_MODE_TABS.register("beyond_limits_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.BEYOND_LIMITS.get()))
                    .title(Component.translatable("creativetab.beyond_limits_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                       output.accept(Moditems.NETHER_STAR_FRAGMENT.get());
                       output.accept(Moditems.SIGNAL_MASSAGE_MUSIC_DISC.get());
                       output.accept(Moditems.DRAINED_NETHER_STAR_FRAGMENT.get());
                       output.accept(Moditems.PRESSURE_CORE.get());
                       output.accept(Moditems.FABRICATED_ELYTRA_PIECE.get());
                       output.accept(Moditems.WING.get());
                       output.accept(ModBlocks.AROMA_SPEED_1.get().asItem());
                       output.accept(ModBlocks.AROMA_SPEED_2.get().asItem());
                       output.accept(ModBlocks.AROMA_REGEN_1.get().asItem());
                       output.accept(ModBlocks.AROMA_REGEN_2.get().asItem());
                       output.accept(ModBlocks.AROMA_STRENGTH_1.get().asItem());
                       output.accept(ModBlocks.AROMA_STRENGTH_2.get().asItem());
                       output.accept(Moditems.TOTEM_REGEN.get().asItem());
                       output.accept(Moditems.TOTEM_STRENGTH.get().asItem());
                       output.accept(Moditems.TOTEM_SPEED.get().asItem());
                    })
                    .build());

    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TABS.register(eventbus);
    }
}

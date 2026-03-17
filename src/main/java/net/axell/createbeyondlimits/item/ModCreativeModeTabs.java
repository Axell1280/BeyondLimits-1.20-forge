package net.axell.createbeyondlimits.item;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent; // Added
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent; // Added
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BeyondLimits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondLimits.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BEYOND_LIMITS_TAB = CREATIVE_MODE_TABS.register("beyond_limits_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.BEYOND_LIMITS.get()))
                    .title(Component.translatable("creativetab.beyond_limits_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Keep your custom tab items here if you want them in BOTH tabs
                        output.accept(Moditems.RAW_PHOSPHATE.get());
                        output.accept(Moditems.SUPERPHOSPHATE.get());
                        output.accept(Moditems.NETHER_STAR_FRAGMENT.get());
                        output.accept(Moditems.DRAINED_NETHER_STAR_FRAGMENT.get());
                        output.accept(Moditems.PRESSURE_CORE.get());
                        output.accept(Moditems.FABRICATED_ELYTRA_PIECE.get());
                        output.accept(Moditems.WING.get());
                        output.accept(ModBlocks.AROMA_SPEED_1.get());
                        output.accept(ModBlocks.AROMA_SPEED_2.get());
                        output.accept(ModBlocks.AROMA_REGEN_1.get());
                        output.accept(ModBlocks.AROMA_REGEN_2.get());
                        output.accept(ModBlocks.AROMA_STRENGTH_1.get());
                        output.accept(ModBlocks.AROMA_STRENGTH_2.get());
                        output.accept(ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get());
                        output.accept(Moditems.TOTEM_SPEED.get());
                        output.accept(Moditems.TOTEM_REGEN.get());
                        output.accept(Moditems.TOTEM_STRENGTH.get());
                        output.accept(ModBlocks.CHEESE_BLOCK.get());
                        output.accept(ModBlocks.BLUE_CHEESE_BLOCK.get());
                        output.accept(ModBlocks.PARMESAN_CHEESE_BLOCK.get());
                        output.accept(Moditems.CHEESE_SLICE.get());
                        output.accept(Moditems.BLUE_CHEESE_SLICE.get());
                        output.accept(Moditems.PARMESAN_CHEESE_SLICE.get());

                        if (Moditems.SUNFLOWER_SEEDS.isPresent()) {
                            output.accept(Moditems.SUNFLOWER_SEEDS.get());
                        }
                        if (Moditems.SUNFLOWER_KERNAL.isPresent()) {
                            output.accept(Moditems.SUNFLOWER_KERNAL.get());
                        }
                    })
                    .build());

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        // NATURAL BLOCKS TAB (Ores)
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.PHOSPHATE_ORE.get());
            event.accept(ModBlocks.DEEPSLATE_PHOSPHATE_ORE.get());
        }

        // INGREDIENTS TAB (Crafting Components)
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(Moditems.RAW_PHOSPHATE.get());
            event.accept(Moditems.SUPERPHOSPHATE.get());
            event.accept(Moditems.NETHER_STAR_FRAGMENT.get());
            event.accept(Moditems.DRAINED_NETHER_STAR_FRAGMENT.get());
            event.accept(Moditems.PRESSURE_CORE.get());
            event.accept(Moditems.FABRICATED_ELYTRA_PIECE.get());
            event.accept(Moditems.WING.get());
            if (Moditems.SUNFLOWER_SEEDS.isPresent()) {
                event.accept(Moditems.SUNFLOWER_SEEDS.get());
            }
        }

        // FOOD & DRINKS Tab (Cheese)
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModBlocks.CHEESE_BLOCK.get());
            event.accept(ModBlocks.BLUE_CHEESE_BLOCK.get());
            event.accept(ModBlocks.PARMESAN_CHEESE_BLOCK.get());
            event.accept(Moditems.CHEESE_SLICE.get());
            event.accept(Moditems.BLUE_CHEESE_SLICE.get());
            event.accept(Moditems.PARMESAN_CHEESE_SLICE.get());
            if (Moditems.SUNFLOWER_KERNAL.isPresent()) {
                event.accept(Moditems.SUNFLOWER_KERNAL.get());
            }
        }

        // TOOLS & UTILITIES TAB (Totems)
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(Moditems.TOTEM_SPEED.get());
            event.accept(Moditems.TOTEM_REGEN.get());
            event.accept(Moditems.TOTEM_STRENGTH.get());
        }
    }

    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TABS.register(eventbus);
    }
}
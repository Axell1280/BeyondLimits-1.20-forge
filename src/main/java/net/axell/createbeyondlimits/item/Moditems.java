package net.axell.createbeyondlimits.item;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.item.custom.NetherStarFragmentItem;
import net.axell.createbeyondlimits.item.custom.PressureCoreItem;
import net.axell.createbeyondlimits.item.custom.WingItem;
import net.axell.createbeyondlimits.item.custom.RegenTotemItem;
import net.axell.createbeyondlimits.item.custom.StrengthTotemItem;
import net.axell.createbeyondlimits.item.custom.SpeedTotemItem;
import net.axell.createbeyondlimits.item.custom.SuperphosphateItem;
import net.axell.createbeyondlimits.sound.ModSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BeyondLimits.MOD_ID);

    // Example normal items
    public static final RegistryObject<Item> NETHER_STAR_FRAGMENT = ITEMS.register("nether_star_fragment",
            () -> new NetherStarFragmentItem(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.EPIC)
            ));

    public static final RegistryObject<Item> DRAINED_NETHER_STAR_FRAGMENT = ITEMS.register("drained_nether_star_fragment",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.RARE)
            ));

    public static final RegistryObject<Item> UNSTABLE_QUARTZ = ITEMS.register("unstable_quartz",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BEYOND_LIMITS = ITEMS.register("beyond_limits",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INCOMPLETE_FORGED_NETHERITE = ITEMS.register("incomplete_forged_netherite",
            () -> new Item(new Item.Properties()
                    .fireResistant()
            ));

    public static final RegistryObject<Item> PRESSURE_CORE = ITEMS.register("pressure_core",
            () -> new PressureCoreItem(new Item.Properties()
                    .stacksTo(8)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final RegistryObject<Item> INCOMPLETE_COPPER_PIECE = ITEMS.register("incomplete_copper_piece",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> FABRICATED_ELYTRA_PIECE = ITEMS.register("fabricated_elytra_piece",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
            ));

    public static final RegistryObject<Item> WING =ITEMS.register("wing",
            () -> new WingItem(new Item.Properties()
                    .stacksTo(64)
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> TOTEM_REGEN = ITEMS.register("totem_regen",
            () -> new RegenTotemItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> TOTEM_STRENGTH = ITEMS.register("totem_strength",
            () -> new StrengthTotemItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));
    public static final RegistryObject<Item> TOTEM_SPEED = ITEMS.register("totem_speed",
            () -> new SpeedTotemItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    // --- Block Items ---
    public static final RegistryObject<Item> AROMA_SPEED_1_ITEM = ITEMS.register("aroma_speed_1",
            () -> new BlockItem(ModBlocks.AROMA_SPEED_1.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AROMA_SPEED_2_ITEM = ITEMS.register("aroma_speed_2",
            () -> new BlockItem(ModBlocks.AROMA_SPEED_2.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AROMA_REGEN_1_ITEM = ITEMS.register("aroma_regen_1",
            () -> new BlockItem(ModBlocks.AROMA_REGEN_1.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AROMA_REGEN_2_ITEM = ITEMS.register("aroma_regen_2",
            () -> new BlockItem(ModBlocks.AROMA_REGEN_2.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AROMA_STRENGTH_1_ITEM = ITEMS.register("aroma_strength_1",
            () -> new BlockItem(ModBlocks.AROMA_STRENGTH_1.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AROMA_STRENGTH_2_ITEM = ITEMS.register("aroma_strength_2",
            () -> new BlockItem(ModBlocks.AROMA_STRENGTH_2.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> PHOSPHATE_ORE_ITEM = ITEMS.register("phosphate_ore",
            () -> new BlockItem(ModBlocks.PHOSPHATE_ORE.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> PHOSPHATE_POWDER = ITEMS.register("phosphate_powder",
            () -> new Item(new Item.Properties()
                    .stacksTo(64)
            ));

    public static final RegistryObject<Item> SUPERPHOSPHATE = ITEMS.register("superphosphate",
            () -> new SuperphosphateItem(new Item.Properties()
                    .stacksTo(64)
            ));

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}

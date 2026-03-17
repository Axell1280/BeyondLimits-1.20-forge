package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.custom.*;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BeyondLimits.MOD_ID);

public static final RegistryObject<Block> SUPERCHARGED_AROMA_SANCTUM = BLOCKS.register("supercharged_aroma",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .mapColor(MapColor.GOLD) // Shows up as Gold on maps
                            .strength(3.0f, 6.0f)   // A bit tougher than the normal ones
                            .requiresCorrectToolForDrops()
                            .lightLevel(state -> 15)
                            .noOcclusion()
                            .sound(SoundType.AMETHYST_CLUSTER),
                    ModEffects.BLESSED,
                    0,
                    64
            ));

public static final RegistryObject<Block> AROMA_SPEED_1 = BLOCKS.register("aroma_speed_1",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.MOVEMENT_SPEED,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_SPEED_2 = BLOCKS.register("aroma_speed_2",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.MOVEMENT_SPEED,
                    1,
                    30
            ));

    public static final RegistryObject<Block> AROMA_REGEN_1 = BLOCKS.register("aroma_regen_1",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_RED)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.REGENERATION,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_REGEN_2 = BLOCKS.register("aroma_regen_2",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_RED)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.REGENERATION,
                    1,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_1 = BLOCKS.register("aroma_strength_1",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_GRAY)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.DAMAGE_BOOST,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_2 = BLOCKS.register("aroma_strength_2",
            () -> new AnimatedAromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_GRAY)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(SoundType.AMETHYST_CLUSTER),
                    MobEffects.DAMAGE_BOOST,
                    1,
                    30
            ));

    public static final RegistryObject<Block> BLUE_CHEESE_BLOCK = BLOCKS.register("blue_cheese_block",
            () -> new CheeseBlock(
                    Block.Properties.of()
                            .strength(0.5f)
                            .sound(SoundType.WOOL)
                            .mapColor(MapColor.COLOR_BLUE),
                    CheeseType.BLUE
            ));

    public static final RegistryObject<Block> CHEESE_BLOCK = BLOCKS.register("cheese_block",
            () -> new CheeseBlock(
                    Block.Properties.of()
                            .strength(0.5f)
                            .sound(SoundType.WOOL)
                            .mapColor(MapColor.COLOR_ORANGE),
                    CheeseType.NORMAL
            ));

    public static final RegistryObject<Block> PARMESAN_CHEESE_BLOCK = BLOCKS.register("parmesan_cheese_block",
            () -> new CheeseBlock(
                    Block.Properties.of()
                            .strength(0.5f)
                            .sound(SoundType.WOOL)
                            .mapColor(MapColor.COLOR_YELLOW),
                    CheeseType.PARMESAN
            ));

    public static final RegistryObject<Block> DOMESTIC_SUNFLOWER = BLOCKS.register("domestic_sunflower",
            () -> new DomesticatedSunflowerBlock(
                    BlockBehaviour.Properties.copy(Blocks.WHEAT)
                            .noOcclusion()
                            .sound(SoundType.CROP)
            ));
    public static final RegistryObject<Block> ROSEMARY = BLOCKS.register("rosemary",
            () -> new RosemaryBlock(
                    BlockBehaviour.Properties.copy(Blocks.WHEAT)
                            .noOcclusion()
                            .sound(SoundType.CROP)
            ));

    public static final RegistryObject<Block> PHOSPHATE_ORE = BLOCKS.register("phosphate_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy((Blocks.STONE))
                    .strength(2.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(2, 4)
            ));
    public static final RegistryObject<Block> DEEPSLATE_PHOSPHATE_ORE = BLOCKS.register("deepslate_phosphate_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(4, 8)

            ));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}

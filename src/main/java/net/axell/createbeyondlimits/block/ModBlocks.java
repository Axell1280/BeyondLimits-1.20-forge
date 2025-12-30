package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.custom.AromaBlock;
import net.axell.createbeyondlimits.block.custom.CheeseBlock;
import net.axell.createbeyondlimits.block.custom.CheeseType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BeyondLimits.MOD_ID);

    public static final RegistryObject<Block> AROMA_SPEED_1 = BLOCKS.register("aroma_speed_1",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.MOVEMENT_SPEED,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_SPEED_2 = BLOCKS.register("aroma_speed_2",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.MOVEMENT_SPEED,
                    1,
                    30
            ));

    public static final RegistryObject<Block> AROMA_REGEN_1 = BLOCKS.register("aroma_regen_1",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_RED)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.REGENERATION,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_REGEN_2 = BLOCKS.register("aroma_regen_2",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_RED)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.REGENERATION,
                    1,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_1 = BLOCKS.register("aroma_strength_1",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_GRAY)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.DAMAGE_BOOST,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_2 = BLOCKS.register("aroma_strength_2",
            () -> new AromaBlock(
                    Block.Properties.of()
                            .requiresCorrectToolForDrops()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_LIGHT_GRAY)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.DAMAGE_BOOST,
                    1,
                    30
            ));

    public static final RegistryObject<Block> PHOSPHATE_ORE = BLOCKS.register("phosphate_ore",
            () -> new Block(Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .mapColor(MapColor.COLOR_PURPLE)
                    .sound(SoundType.STONE)
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

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}

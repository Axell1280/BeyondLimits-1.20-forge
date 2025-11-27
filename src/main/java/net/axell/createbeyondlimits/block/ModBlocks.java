package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.BeyondLimits;
import net.axell.createbeyondlimits.block.custom.AromaBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
                    BlockBehaviour.Properties.of()
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
                    BlockBehaviour.Properties.of()
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
                    BlockBehaviour.Properties.of()
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
                    BlockBehaviour.Properties.of()
                            .strength(1.5f).mapColor(MapColor.COLOR_RED)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.REGENERATION,
                    1,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_1 = BLOCKS.register("aroma_strength_1",
            () -> new AromaBlock(
                    BlockBehaviour.Properties.of()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_PURPLE)
                            .noOcclusion()
                            .lightLevel(state -> 6)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.DAMAGE_BOOST,
                    0,
                    30
            ));

    public static final RegistryObject<Block> AROMA_STRENGTH_2 = BLOCKS.register("aroma_strength_2",
            () -> new AromaBlock(
                    BlockBehaviour.Properties.of()
                            .strength(1.5f)
                            .mapColor(MapColor.COLOR_PURPLE)
                            .noOcclusion()
                            .lightLevel(state -> 12)
                            .sound(ModSoundTypes.AROMA),
                    MobEffects.DAMAGE_BOOST,
                    1,
                    30
            ));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}

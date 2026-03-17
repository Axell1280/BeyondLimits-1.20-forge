package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.client.AromaBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "createbeyondlimits");

    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_REGEN =
            BLOCK_ENTITIES.register("aroma_regen", () ->
                    BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_REGEN.get(), pos, state),
                            ModBlocks.AROMA_REGEN_1.get(), ModBlocks.AROMA_REGEN_2.get()).build(null));

    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_SPEED =
            BLOCK_ENTITIES.register("aroma_speed", () ->
                    BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_SPEED.get(), pos, state),
                            ModBlocks.AROMA_SPEED_1.get(), ModBlocks.AROMA_SPEED_2.get()).build(null));

    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_STRENGTH =
            BLOCK_ENTITIES.register("aroma_strength", () ->
                    BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_STRENGTH.get(), pos, state),
                            ModBlocks.AROMA_STRENGTH_1.get(), ModBlocks.AROMA_STRENGTH_2.get()).build(null));
    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> SUPERCHARGED_AROMA_BE =
            BLOCK_ENTITIES.register("supercharged_aroma_be", () ->
                    BlockEntityType.Builder.of((pos, state) ->
                                    new AromaBlockEntity(ModBlockEntities.SUPERCHARGED_AROMA_BE.get(), pos, state),
                            ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
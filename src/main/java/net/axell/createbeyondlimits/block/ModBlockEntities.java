package net.axell.createbeyondlimits.block;

import net.axell.createbeyondlimits.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "createbeyondlimits");

    // Standard Aroma Entities
    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_REGEN = BLOCK_ENTITIES.register("aroma_regen", () -> BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_REGEN.get(), pos, state), ModBlocks.AROMA_REGEN_1.get(), ModBlocks.AROMA_REGEN_2.get()).build(null));
    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_SPEED = BLOCK_ENTITIES.register("aroma_speed", () -> BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_SPEED.get(), pos, state), ModBlocks.AROMA_SPEED_1.get(), ModBlocks.AROMA_SPEED_2.get()).build(null));
    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> AROMA_STRENGTH = BLOCK_ENTITIES.register("aroma_strength", () -> BlockEntityType.Builder.of((pos, state) -> new AromaBlockEntity(ModBlockEntities.AROMA_STRENGTH.get(), pos, state), ModBlocks.AROMA_STRENGTH_1.get(), ModBlocks.AROMA_STRENGTH_2.get()).build(null));
    public static final RegistryObject<BlockEntityType<AromaBlockEntity>> SUPERCHARGED_AROMA_BE =
            BLOCK_ENTITIES.register("supercharged_aroma_be", () ->
                    BlockEntityType.Builder.of((pos, state) ->
                                    new AromaBlockEntity(ModBlockEntities.SUPERCHARGED_AROMA_BE.get(), pos, state),
                            ModBlocks.SUPERCHARGED_AROMA_SANCTUM.get()).build(null));

    // Fragrance Entities[cite: 16]
    public static final RegistryObject<BlockEntityType<MaliceFragranceBlockEntity>> MALICE_FRAGRANCE =
            BLOCK_ENTITIES.register("malice_fragrance", () -> BlockEntityType.Builder.of(MaliceFragranceBlockEntity::new, ModBlocks.FRAGRANCE_MALICE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CinderFragranceBlockEntity>> CINDER_FRAGRANCE =
            BLOCK_ENTITIES.register("cinder_fragrance", () -> BlockEntityType.Builder.of(CinderFragranceBlockEntity::new, ModBlocks.FRAGRANCE_CINDER.get()).build(null));

    public static final RegistryObject<BlockEntityType<BastionFragranceBlockEntity>> BASTION_FRAGRANCE =
            BLOCK_ENTITIES.register("bastion_fragrance", () -> BlockEntityType.Builder.of(BastionFragranceBlockEntity::new, ModBlocks.FRAGRANCE_BASTION.get()).build(null));

    public static final RegistryObject<BlockEntityType<IntensifiedAnchorBlockEntity>> INTENSIFIED_ANCHOR =
            BLOCK_ENTITIES.register("intensified_anchor",
                    () -> BlockEntityType.Builder.of(IntensifiedAnchorBlockEntity::new,
                            ModBlocks.INTENSIFIED_ANCHOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
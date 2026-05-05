package net.axell.createbeyondlimits;

import com.mojang.logging.LogUtils;
import net.axell.createbeyondlimits.block.ModBlockEntities;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.client.AromaRegenModel;
import net.axell.createbeyondlimits.client.AromaSpeedModel;
import net.axell.createbeyondlimits.client.AromaStrengthModel;
import net.axell.createbeyondlimits.client.AromaSuperchargedModel;
import net.axell.createbeyondlimits.config.ModConfigs;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.axell.createbeyondlimits.item.ModCreativeModeTabs;
import net.axell.createbeyondlimits.item.Moditems;
import net.axell.createbeyondlimits.networking.ModNetworking;
import net.axell.createbeyondlimits.ponder.BeyondLimitsPonderPlugin;
import net.axell.createbeyondlimits.sound.ModSounds;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BeyondLimits.MOD_ID)
public class BeyondLimits {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "createbeyondlimits";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static int breedingChance = 300;
    public static GameRules.Key<GameRules.BooleanValue> RULE_NATURAL_BREEDING;

    public BeyondLimits(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModNetworking.register();

        ModEffects.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModBlocks.register(modEventBus);

        Moditems.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModSounds.register(modEventBus);

        ModConfigs.register();

        modEventBus.addListener(this::commonSetup);
        
        // Register additional setup for world generation
        modEventBus.addListener(this::registerWorldGen);


        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Correct way to register a GameRule in 1.20.1 Forge
            RULE_NATURAL_BREEDING = GameRules.register("doNaturalBreeding",
                    GameRules.Category.SPAWNING, GameRules.BooleanValue.create(true));
        });
    }


    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(net.minecraft.commands.Commands.literal("beyondlimits")
                .then(net.minecraft.commands.Commands.literal("breedingFrequency")
                        .requires(source -> source.hasPermission(2))
                        .then(net.minecraft.commands.Commands.argument("value", com.mojang.brigadier.arguments.IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    int newValue = com.mojang.brigadier.arguments.IntegerArgumentType.getInteger(context, "value");
                                    // Update the variable in this class
                                    BeyondLimits.breedingChance = newValue;

                                    context.getSource().sendSuccess(() ->
                                            net.minecraft.network.chat.Component.literal("§bBreeding chance is now 1 in " + newValue), true);
                                    return 1;
                                })
                        )
                )
        );
    }


    private void registerWorldGen(final FMLCommonSetupEvent event) {
        // Register world generation
        event.enqueueWork(() -> {
            // World generation is now handled by datapack loading
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {

                PonderIndex.addPlugin(new BeyondLimitsPonderPlugin());
                // This fixes the "Cursed" transparency and the crash
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.INTENSIFIED_ANCHOR.get(), RenderType.cutout());

                // Add these so your other fragrance blocks look right too:
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.FRAGRANCE_BASTION.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.FRAGRANCE_CINDER.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.FRAGRANCE_MALICE.get(), RenderType.cutout());
            });
        }


        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.AROMA_REGEN.get(),
                    context -> new GeoBlockRenderer<>(new AromaRegenModel()));
            event.registerBlockEntityRenderer(ModBlockEntities.AROMA_SPEED.get(),
                    context -> new GeoBlockRenderer<>(new AromaSpeedModel()));
            event.registerBlockEntityRenderer(ModBlockEntities.AROMA_STRENGTH.get(),
                    context -> new GeoBlockRenderer<>(new AromaStrengthModel()));
            event.registerBlockEntityRenderer(ModBlockEntities.SUPERCHARGED_AROMA_BE.get(),
                    context -> new GeoBlockRenderer<>(new AromaSuperchargedModel()));
        }
    }
}

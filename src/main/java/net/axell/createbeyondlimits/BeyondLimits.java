package net.axell.createbeyondlimits;

import com.mojang.logging.LogUtils;
import net.axell.createbeyondlimits.block.ModBlockEntities;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.client.AromaRegenModel;
import net.axell.createbeyondlimits.client.AromaSpeedModel;
import net.axell.createbeyondlimits.client.AromaStrengthModel;
import net.axell.createbeyondlimits.client.AromaSuperchargedModel;
import net.axell.createbeyondlimits.effect.ModEffects;
import net.axell.createbeyondlimits.item.ModCreativeModeTabs;
import net.axell.createbeyondlimits.item.Moditems;
import net.axell.createbeyondlimits.networking.ModNetworking;
import net.axell.createbeyondlimits.sound.ModSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
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

    public BeyondLimits(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModNetworking.register();

        ModEffects.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModBlocks.register(modEventBus);

        Moditems.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        
        // Register additional setup for world generation
        modEventBus.addListener(this::registerWorldGen);


        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

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

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            // In GeckoLib 4, the constructor for GeoBlockRenderer only needs the model.
            // Minecraft's registration provides the context automatically.
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

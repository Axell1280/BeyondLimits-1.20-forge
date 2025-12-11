package net.axell.createbeyondlimits;

import com.mojang.logging.LogUtils;
import net.axell.createbeyondlimits.block.ModBlocks;
import net.axell.createbeyondlimits.item.ModCreativeModeTabs;
import net.axell.createbeyondlimits.item.Moditems;
import net.axell.createbeyondlimits.networking.ModNetworking;
import net.axell.createbeyondlimits.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

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

        ModCreativeModeTabs.register(modEventBus);

        Moditems.register(modEventBus);

        ModSounds.register(modEventBus);

        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(Moditems.NETHER_STAR_FRAGMENT);
        }
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
    }
}

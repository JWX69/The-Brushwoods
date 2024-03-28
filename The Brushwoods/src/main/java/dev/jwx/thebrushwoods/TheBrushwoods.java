package dev.jwx.thebrushwoods;

import com.mojang.logging.LogUtils;
import dev.jwx.thebrushwoods.client.render.BrushwoodsRenderer;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import dev.jwx.thebrushwoods.core.BrushwoodsCreativeModeTabs;
import dev.jwx.thebrushwoods.core.BrushwoodsFeatures;
import dev.jwx.thebrushwoods.core.BrushwoodsItems;
import dev.jwx.thebrushwoods.world.dimension.BrushwoodsSurfaceRuleManager;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheBrushwoods.MODID)
public class TheBrushwoods {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "brushwoods";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "TheBrushwoods" namespace

    public TheBrushwoods() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BrushwoodsBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        BrushwoodsItems.register(modEventBus);
        BrushwoodsFeatures.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        BrushwoodsCreativeModeTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModDimensions.register();
        modEventBus.addListener(this::addCreativeTab);
        subscribeToEvents();
    }
    private void subscribeToEvents() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(BrushwoodsRenderer::veieldAbbysTick);
        bus.addListener(BrushwoodsRenderer::renderVeiledAbysFog);
        bus.addListener(BrushwoodsSurfaceRuleManager::playerSneak);
    }
    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == BrushwoodsCreativeModeTabs.TAB.get()) {
            event.accept(BrushwoodsBlocks.LUMENELLA.get().asItem());
            for (RegistryObject<Item> object: BrushwoodsItems.ITEMS.getEntries()) {
                event.accept(object.get());
            }
        }
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        event.enqueueWork(()-> {
            BrushwoodsSurfaceRuleManager.setup();
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BrushwoodsRenderer.setupSegments();
        }
    }
}

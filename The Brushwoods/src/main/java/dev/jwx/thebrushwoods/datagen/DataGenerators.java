package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TheBrushwoods.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new BrushwoodsWorldGenProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new BrushwoodsRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), BrushwoodsLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new BrushwoodsBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new BrushwoodsItemModelProvider(packOutput, existingFileHelper));

        BrushwoodsBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new BrushwoodsBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
    }
}

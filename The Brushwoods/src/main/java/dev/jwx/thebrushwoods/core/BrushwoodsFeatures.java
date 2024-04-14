package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.features.ElmTreeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BrushwoodsFeatures {
    
    public static final DeferredRegister<Feature<?>> FEATURES =
        DeferredRegister.create(ForgeRegistries.FEATURES, TheBrushwoods.MODID);
    
    public static final RegistryObject<ElmTreeFeature> ELM_TREE = FEATURES.register("elm_tree", () -> new ElmTreeFeature(ElmTreeFeature.CODEC));
    
    public static void register(IEventBus modEventBus) {
        FEATURES.register(modEventBus);
    }
    
}

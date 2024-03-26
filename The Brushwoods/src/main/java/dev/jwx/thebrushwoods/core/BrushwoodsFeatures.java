package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.features.BrushwoodTreeFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BrushwoodsFeatures {
    
    public static final DeferredRegister<Feature<?>> FEATURES =
        DeferredRegister.create(ForgeRegistries.FEATURES, TheBrushwoods.MODID);
    
    public static final RegistryObject<BrushwoodTreeFeature> BRUSHWOOD_TREE = FEATURES.register("brushwood_tree", () -> new BrushwoodTreeFeature(BrushwoodTreeFeature.CODEC));
    
    public static void register(IEventBus modEventBus) {
        FEATURES.register(modEventBus);
    }
    
}

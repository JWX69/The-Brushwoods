package dev.jwx.thebrushwoods.world.dimension;

import dev.jwx.thebrushwoods.core.TheBrushwoods;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;


public class ModDimensions {
    public static final ResourceKey<Level> BW_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(TheBrushwoods.MODID, "brushwoods"));
    public static final ResourceKey<DimensionType> BW_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, BW_KEY.registry());
    
    public static void register() {
        System.out.println("Registering ModDimensions for" + TheBrushwoods.MODID);
    }
}


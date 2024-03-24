package dev.jwx.thebrushwoods.world.dimension;

import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;


public class ModDimensions {
    public static final ResourceKey<Level> BW_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TheBrushwoods.MODID, "brushwoods"));
    public static final ResourceKey<DimensionType> BW_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, BW_KEY.registry());
    
    public static void register() {
        System.out.println("Registering ModDimensions for" + TheBrushwoods.MODID);
    }
}


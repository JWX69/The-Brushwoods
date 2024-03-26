package dev.jwx.thebrushwoods.world.dimension;

import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> VERDANT_CANOPY = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(TheBrushwoods.MODID, "verdant_canopy"));
}

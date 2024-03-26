package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BrushwoodsWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);
//            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);

    public BrushwoodsWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(TheBrushwoods.MODID));
    }
}

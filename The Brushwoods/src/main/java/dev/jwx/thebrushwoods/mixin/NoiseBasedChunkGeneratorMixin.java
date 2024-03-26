package dev.jwx.thebrushwoods.mixin;
import dev.jwx.thebrushwoods.world.dimension.BrushwoodsSurfaceRuleManager;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.function.Function;

@Mixin(NoiseBasedChunkGenerator.class) // This class is from Citadel
public class NoiseBasedChunkGeneratorMixin {

    @Unique
    private static final boolean REMAPREFS = true;
    private final Function<SurfaceRules.RuleSource, SurfaceRules.RuleSource> rulesToMerge = Util.memoize(BrushwoodsSurfaceRuleManager::mergeBrushwoodRules);
    private final HashMap<NoiseGeneratorSettings, SurfaceRules.RuleSource> mergedRulesMap = new HashMap<>();

    @Redirect(
            method = {"Lnet/minecraft/world/level/levelgen/NoiseBasedChunkGenerator;buildSurface(Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/WorldGenerationContext;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/core/Registry;Lnet/minecraft/world/level/levelgen/blending/Blender;)V"},
            remap = REMAPREFS,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;surfaceRule()Lnet/minecraft/world/level/levelgen/SurfaceRules$RuleSource;")
    )
    private SurfaceRules.RuleSource citadel_buildSurface_surfaceRuleRedirect(NoiseGeneratorSettings noiseGeneratorSettings) {
        return getMergedRulesFor(noiseGeneratorSettings);
    }

    @Redirect(
            method = {"Lnet/minecraft/world/level/levelgen/NoiseBasedChunkGenerator;applyCarvers(Lnet/minecraft/server/level/WorldGenRegion;JLnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/GenerationStep$Carving;)V"},
            remap = REMAPREFS,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;surfaceRule()Lnet/minecraft/world/level/levelgen/SurfaceRules$RuleSource;")
    )
    private SurfaceRules.RuleSource citadel_applyCarvers_surfaceRuleRedirect(NoiseGeneratorSettings noiseGeneratorSettings) {
        return getMergedRulesFor(noiseGeneratorSettings);
    }

    private SurfaceRules.RuleSource getMergedRulesFor(NoiseGeneratorSettings settings){
        SurfaceRules.RuleSource merged = mergedRulesMap.get(settings);
        if(merged == null){
            merged = rulesToMerge.apply(settings.surfaceRule());
            mergedRulesMap.put(settings, merged);
        }
        return merged;
    }
}
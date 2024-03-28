package dev.jwx.thebrushwoods.world.dimension;

import com.google.common.collect.ImmutableList;
import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BrushwoodsSurfaceRuleManager { // Some From Citadel
    private static final List<SurfaceRules.RuleSource> BRUSHWOODS_REGISTRY = new ArrayList();
    public static void registerBrushwoodSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        registerBrushwoodSurfaceRule(SurfaceRules.ifTrue(condition, rule));
    }
    public static void registerBrushwoodSurfaceRule(SurfaceRules.RuleSource rule) {
        BRUSHWOODS_REGISTRY.add(rule);
    }
    public static final ResourceKey<NormalNoise.NoiseParameters> NOISE = ResourceKey.create(Registries.NOISE,
            new ResourceLocation(TheBrushwoods.MODID, "abyss_noise"));

    public static void setup() {
        TheBrushwoods.LOGGER.info("Reloading Surface Rules!");
        BRUSHWOODS_REGISTRY.clear();
        registerBrushwoodSurfaceRule(SurfaceRules.isBiome(ModBiomes.VEILED_ABYSS),createVeiledAbyssRules());
        registerBrushwoodSurfaceRule(SurfaceRules.isBiome(ModBiomes.VERDANT_CANOPY),createVerdantCanopyRules());
        registerBrushwoodSurfaceRule(SurfaceRules.isBiome(ModBiomes.VERDANT_SHORE),createVerdantCanopyRules());
        registerBrushwoodSurfaceRule(SurfaceRules.isBiome(ModBiomes.ERM),createErmRules());
    }
    public static SurfaceRules.RuleSource conditional(SurfaceRules.ConditionSource conditionSource, SurfaceRules.RuleSource ruleSource) {
        return SurfaceRules.ifTrue(conditionSource,ruleSource);
    }
    public static SurfaceRules.RuleSource createVeiledAbyssRules() {
//        SurfaceRules.RuleSource air = conditional(SurfaceRules.waterBlockCheck(), SurfaceRules.state(Blocks.AIR.defaultBlockState()));
        SurfaceRules.RuleSource bedrock = conditional(SurfaceRules.verticalGradient("bedrock", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState()));
        SurfaceRules.RuleSource silt = conditional(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SurfaceRules.state(BrushwoodsBlocks.SILT_BLOCK.get().defaultBlockState()));
        SurfaceRules.RuleSource siltFloor = SurfaceRules.state(BrushwoodsBlocks.SILT_BLOCK.get().defaultBlockState());
        return SurfaceRules.sequence(bedrock);
    }

    public static SurfaceRules.RuleSource createVerdantCanopyRules() {
        SurfaceRules.RuleSource sylvanMoss = conditional(SurfaceRules.ON_FLOOR, SurfaceRules.state(BrushwoodsBlocks.SYLVAN_MOSS.get().defaultBlockState()));
        SurfaceRules.RuleSource shale = SurfaceRules.state(BrushwoodsBlocks.SHALE.get().defaultBlockState());
        return SurfaceRules.sequence(sylvanMoss,shale);
    }
    public static SurfaceRules.RuleSource createErmRules() {
        SurfaceRules.RuleSource sylvanMoss = conditional(SurfaceRules.ON_FLOOR, SurfaceRules.state(BrushwoodsBlocks.SYLVAN_MOSS.get().defaultBlockState()));
        SurfaceRules.RuleSource shale = SurfaceRules.state(BrushwoodsBlocks.SHALE.get().defaultBlockState());
        return SurfaceRules.sequence(sylvanMoss,shale);
    }
    public static SurfaceRules.RuleSource mergeRules(SurfaceRules.RuleSource prev, List<SurfaceRules.RuleSource> toMerge) {
        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        builder.addAll(toMerge);
        builder.add(prev);
        return SurfaceRules.sequence(builder.build().toArray((size) -> new SurfaceRules.RuleSource[size]));
    }
    public static boolean startedSneaking = false;
    public static void playerSneak(PlayerInteractEvent event) {
        if (event.getSide().isClient())
            return;
        if (event.getEntity().isCrouching()) {
            if (!startedSneaking)
                setup();
            startedSneaking = true;
        } else {
            startedSneaking = false;
        }
    }

    public static SurfaceRules.RuleSource mergeBrushwoodRules(SurfaceRules.RuleSource rulesIn) {
        return mergeRules(rulesIn, BRUSHWOODS_REGISTRY);
    }
}

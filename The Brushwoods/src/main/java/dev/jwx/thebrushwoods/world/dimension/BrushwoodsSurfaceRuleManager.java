package dev.jwx.thebrushwoods.world.dimension;

import com.google.common.collect.ImmutableList;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.ArrayList;
import java.util.List;

public class BrushwoodsSurfaceRuleManager {
    private static final List<SurfaceRules.RuleSource> BRUSHWOODS_REGISTRY = new ArrayList();
    public static void registerBrushwoodSurfaceRule(SurfaceRules.ConditionSource condition, SurfaceRules.RuleSource rule) {
        registerBrushwoodSurfaceRule(SurfaceRules.ifTrue(condition, rule));
    }
    public static void registerBrushwoodSurfaceRule(SurfaceRules.RuleSource rule) {
        BRUSHWOODS_REGISTRY.add(rule);
    }

    public static void setup() {
        registerBrushwoodSurfaceRule(SurfaceRules.isBiome(ModBiomes.VERDANT_CANOPY),createVerdantCanopyRules());
    }
    public static SurfaceRules.RuleSource conditional(SurfaceRules.ConditionSource conditionSource, SurfaceRules.RuleSource ruleSource) {
        return SurfaceRules.ifTrue(conditionSource,ruleSource);
    }

    public static SurfaceRules.RuleSource createVerdantCanopyRules() {
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

    public static SurfaceRules.RuleSource mergeBrushwoodRules(SurfaceRules.RuleSource rulesIn) {
        return mergeRules(rulesIn, BRUSHWOODS_REGISTRY);
    }
}
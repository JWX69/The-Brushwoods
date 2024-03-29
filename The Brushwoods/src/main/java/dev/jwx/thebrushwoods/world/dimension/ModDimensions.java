package dev.jwx.thebrushwoods.world.dimension;

import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;


public class ModDimensions {
    public static final ResourceKey<Level> BW_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(TheBrushwoods.MODID, "brushwoods"));
    public static final ResourceKey<DimensionType> BW_TYPE_RESOURCE =
            ResourceKey.create(Registries.DIMENSION_TYPE, BW_KEY.registry());
    public static final DimensionType DW_TYPE = new  DimensionType(
            OptionalLong.empty(), // fixedTime
                false, // hasSkylight
                        false, // hasCeiling
                        false, // ultraWarm
                        true, // natural
                        1.0, // coordinateScale
                        true, // bedWorks
                        false, // respawnAnchorWorks
                        -64, // minY
                        384, // height
                        384, // logicalHeight
    BlockTags.INFINIBURN_OVERWORLD, // infiniburn
    BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
            0.1f, // ambientLight
            new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0));
    public static final ResourceLocation BW_EFFECTS = new ResourceLocation(TheBrushwoods.MODID, "brushwoods");

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(BW_TYPE_RESOURCE, DW_TYPE);
    }
    
    public static void register() {
        System.out.println("Registering ModDimensions for" + TheBrushwoods.MODID);
    }
}


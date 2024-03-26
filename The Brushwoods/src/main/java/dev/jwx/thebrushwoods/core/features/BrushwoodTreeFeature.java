package dev.jwx.thebrushwoods.core.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class BrushwoodTreeFeature extends Feature<BrushwoodTreeFeature.BrushwoodTreeConfiguration> {
    
    public static final Codec<BrushwoodTreeConfiguration> CODEC = RecordCodecBuilder.create((p_225468_) -> {
        return p_225468_.group(
            BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_161248_) -> {
            return p_161248_.trunkProvider;
        }), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((p_161246_) -> {
            return p_161246_.trunkPlacer;
        }), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((p_161244_) -> {
            return p_161244_.foliageProvider;
        }), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((p_191357_) -> {
            return p_191357_.foliagePlacer;
        }), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter((p_225478_) -> {
            return p_225478_.rootPlacer;
        }), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((p_225476_) -> {
            return p_225476_.dirtProvider;
        }), FeatureSize.CODEC.fieldOf("minimum_size").forGetter((p_225474_) -> {
            return p_225474_.minimumSize;
        }), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_225472_) -> {
            return p_225472_.decorators;
        }), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((p_161232_) -> {
            return p_161232_.ignoreVines;
        }), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((p_225470_) -> {
            return p_225470_.forceDirt;
        })).apply(p_225468_, BrushwoodTreeConfiguration::new);
    });
    
    public BrushwoodTreeFeature(Codec<BrushwoodTreeConfiguration> pCodec) {
        super(pCodec);
    }
    @Override
    public boolean place(FeaturePlaceContext<BrushwoodTreeConfiguration> pContext) {
        pContext.level().setBlock(pContext.origin(), Blocks.ACACIA_PLANKS.defaultBlockState(), 19);
        return true;
    }
    
    static class BrushwoodTreeConfiguration extends TreeConfiguration {
    
        protected BrushwoodTreeConfiguration(BlockStateProvider p_225457_, TrunkPlacer p_225458_, BlockStateProvider p_225459_, FoliagePlacer p_225460_, Optional<RootPlacer> p_225461_, BlockStateProvider p_225462_, FeatureSize p_225463_, List<TreeDecorator> p_225464_, boolean p_225465_, boolean p_225466_) {
            super(p_225457_, p_225458_, p_225459_, p_225460_, p_225461_, p_225462_, p_225463_, p_225464_, p_225465_, p_225466_);
        }
    
    }
    
}

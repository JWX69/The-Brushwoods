package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BrushwoodsBlockTagGenerator extends BlockTagsProvider {
    public BrushwoodsBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheBrushwoods.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                BrushwoodsBlocks.SHALE.get(),
                BrushwoodsBlocks.SHALE_BRICKS.get(),
                BrushwoodsBlocks.SHALE_TILES.get(),
                BrushwoodsBlocks.SHALE_TRIM.get(),
                BrushwoodsBlocks.POLISHED_SHALE.get(),
                BrushwoodsBlocks.SILTSTONE.get(),
                BrushwoodsBlocks.SILTSTONE_BRICKS.get(),
                BrushwoodsBlocks.SILTSTONE_TILES.get(),
                BrushwoodsBlocks.SILTSTONE_TRIM.get(),
                BrushwoodsBlocks.POLISHED_SILTSTONE.get(),
                BrushwoodsBlocks.SERPENTINE.get(),
                BrushwoodsBlocks.SERPENTINE_BRICKS.get(),
                BrushwoodsBlocks.SERPENTINE_TILES.get(),
                BrushwoodsBlocks.SERPENTINE_TRIM.get(),
                BrushwoodsBlocks.POLISHED_SERPENTINE.get()
                );
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(
                BrushwoodsBlocks.ASHWOOD_LOG.get(),
                BrushwoodsBlocks.ASHWOOD.get(),
                BrushwoodsBlocks.ASHWOOD_PLANKS.get()
                );
        this.tag(BlockTags.PLANKS)
                .add(BrushwoodsBlocks.ASHWOOD_PLANKS.get());
    }
}

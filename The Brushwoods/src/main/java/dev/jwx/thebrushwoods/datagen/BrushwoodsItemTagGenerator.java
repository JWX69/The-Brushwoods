package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class BrushwoodsItemTagGenerator extends ItemTagsProvider {
    public BrushwoodsItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags) {
        super(pOutput, pLookupProvider, pBlockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(
                BrushwoodsBlocks.ASHWOOD_LOG.get().asItem(),
                BrushwoodsBlocks.ELM_LOG.get().asItem()
                );
    }
}

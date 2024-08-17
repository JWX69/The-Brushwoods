package dev.jwx.thebrushwoods.datagen.loot;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class BrushwoodsBlockLootTables extends BlockLootSubProvider {
    public BrushwoodsBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BrushwoodsBlocks.SHADOWROOT.get());
    }

    protected Iterable<Block> getKnownBlocks() {
        return BrushwoodsBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

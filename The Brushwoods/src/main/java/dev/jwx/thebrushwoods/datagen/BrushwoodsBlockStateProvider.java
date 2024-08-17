package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BrushwoodsBlockStateProvider extends BlockStateProvider {
    public BrushwoodsBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TheBrushwoods.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BrushwoodsBlocks.SHADOWROOT);
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll((blockRegistryObject.get())));
    }
}

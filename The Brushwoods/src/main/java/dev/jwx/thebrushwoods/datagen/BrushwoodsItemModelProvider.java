package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BrushwoodsItemModelProvider extends ItemModelProvider {
    public BrushwoodsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TheBrushwoods.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(BrushwoodsBlocks.SHADOWROOT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheBrushwoods.MODID,"item/" + item.getId().getPath()));
    }
}

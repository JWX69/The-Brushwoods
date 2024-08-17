package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.datagen.loot.BrushwoodsBlockLootTables;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class BrushwoodsLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        Object ModBlockLootTables;
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BrushwoodsBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}

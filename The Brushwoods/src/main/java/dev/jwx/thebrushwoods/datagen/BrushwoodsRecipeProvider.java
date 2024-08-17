package dev.jwx.thebrushwoods.datagen;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class BrushwoodsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public BrushwoodsRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

    }
}

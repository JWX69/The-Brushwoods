package dev.jwx.thebrushwoods.mixin;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.core.Holder;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkAccess.class)
public class ChunkAccessMixin {
    @Inject(method = "getNoiseBiome", at = @At("HEAD"), cancellable = true)
    private void getNoiseBiome(int pX, int pY, int pZ, CallbackInfoReturnable<Holder<Biome>> cir) {
        LevelAccessor levelAccessor = (((ChunkAccess)((Object)this)).getWorldForge());
        if (levelAccessor != null && levelAccessor.dimensionType() == ModDimensions.DW_TYPE && pY < 0) {
            TheBrushwoods.LOGGER.info("ABYSS");
        }
    }

}

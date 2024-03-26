package dev.jwx.thebrushwoods.mixin;

import dev.jwx.thebrushwoods.client.render.BrushwoodsRenderer;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin {
    @Shadow @Final private ResourceKey<Level> dimension;

    @Inject(method = "isDay", at = @At("RETURN"), cancellable = true)
    public void isDay(CallbackInfoReturnable<Boolean> cir) {
        if (this.dimension == (ModDimensions.BW_KEY)) {
            if (BrushwoodsRenderer.isUmbra((Level) (Object) this) && BrushwoodsRenderer.getLevelDarkess((Level) (Object) this) > .3f) {
                cir.setReturnValue(true);
            }
            cir.setReturnValue(false);
        }
    }
}

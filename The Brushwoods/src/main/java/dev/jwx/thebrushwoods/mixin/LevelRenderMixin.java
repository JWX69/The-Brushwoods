package dev.jwx.thebrushwoods.mixin;

import com.mojang.blaze3d.vertex.*;
import dev.jwx.thebrushwoods.client.render.BrushwoodsRenderer;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LevelRenderer.class)
public class LevelRenderMixin {
    @Shadow @Nullable private ClientLevel level;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Nullable private VertexBuffer starBuffer;

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    public void renderSky(PoseStack pPoseStack, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean pIsFoggy, Runnable pSkyFogSetup, CallbackInfo ci) {
        assert this.level != null;
        if (this.level.dimension() == (ModDimensions.BW_KEY)) {
            BrushwoodsRenderer.renderBrushwoodsSky(this.minecraft,this.level,pPoseStack, pProjectionMatrix, pPartialTick, pCamera, pIsFoggy, pSkyFogSetup, this.starBuffer);
            ci.cancel();
        }
    }
}

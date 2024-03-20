package dev.jwx.thebrushwoods.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import dev.jwx.thebrushwoods.core.TheBrushwoods;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

import static net.minecraft.client.renderer.blockentity.TheEndPortalRenderer.END_SKY_LOCATION;

@Mixin(LevelRenderer.class)
public class LevelRenderMixin {
    private static final ResourceLocation MOON_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/luma_phases.png");
    @Shadow @Nullable private ClientLevel level;

    @Shadow @Final private Minecraft minecraft;

    @Shadow @Nullable private VertexBuffer darkBuffer;

    @Shadow @Nullable private VertexBuffer starBuffer;

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    public void renderSky(PoseStack pPoseStack, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean p_202428_, Runnable pSkyFogSetup, CallbackInfo ci) {
        if (this.level.dimension() == (ModDimensions.BW_KEY)) {
            renderBrushwoodsSky(pPoseStack, pProjectionMatrix, pPartialTick, pCamera, p_202428_, pSkyFogSetup);
            ci.cancel();
        }
    }
    @Unique
    public void renderBrushwoodsSky(PoseStack pPoseStack, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean p_202428_, Runnable pSkyFogSetup){
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, END_SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        for(int i = 0; i < 6; ++i) {
            pPoseStack.pushPose();
            if (i == 1) {
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            }

            if (i == 2) {
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            }

            if (i == 3) {
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            }

            if (i == 4) {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }

            if (i == 5) {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
            }

            Matrix4f matrix4f = pPoseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, -100.0F).uv(0.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, 100.0F).uv(0.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, 100.0F).uv(16.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, -100.0F).uv(16.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            tesselator.end();
            pPoseStack.popPose();
        }
        Vec3 vec3 = this.level.getSkyColor(this.minecraft.gameRenderer.getMainCamera().getPosition(), pPartialTick);
        float f = (float)vec3.x;
        float f1 = (float)vec3.y;
        float f2 = (float)vec3.z;

        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        float[] afloat = this.level.effects().getSunriseColor(this.level.getTimeOfDay(pPartialTick), pPartialTick);
        float f11 = 0;
        float f12 = 0;
        float f7;
        float f8;
        float f9;
        if (afloat != null) {
            float f4 = afloat[0];
            f12 = afloat[1];
            float f6 = afloat[2];
        }
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(this.level.getTimeOfDay(pPartialTick) * 360.0F));
        Matrix4f matrix4f1 = pPoseStack.last().pose();

        RenderSystem.setShaderTexture(0, MOON_LOCATION);
        int k = this.level.getMoonPhase();
        int l = k % 4;
        int i1 = k / 4 % 2;
        float f13 = (float)(l + 0) / 4.0F;
        f7 = (float)(i1 + 0) / 2.0F;
        f8 = (float)(l + 1) / 4.0F;
        f9 = (float)(i1 + 1) / 2.0F;
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f8, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f7).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f8, f7).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableTexture();
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(this.level.getTimeOfDay(pPartialTick) * -360.0F));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));

    }

}

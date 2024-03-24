package dev.jwx.thebrushwoods.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class BrushwoodsRenderer{
    private static final ResourceLocation LUMA_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/luma_phases.png");
    private static final ResourceLocation UMBRA_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/umbra_phases.png");
    private static final ResourceLocation SKY_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/brushwoods_sky.png");

    public static float getDayTime(boolean cycle24, ClientLevel level) {
        boolean isNatural = level.dimensionType()
                .natural();
        int dayTime = (int) ((level.getDayTime() * (isNatural ? 1 : 24)) % 24000);
        int hours = (dayTime / 1000 + 6) % 24;
        return (float) (-360 / (cycle24 ? 24f : 12f) * (hours % (cycle24 ? 24 : 12)));
    }
    private static final float[] sunriseCol = new float[4];
    public static float[] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
        float f = 0.4F;
        float f1 = Mth.cos(pTimeOfDay * 6.2831855F) - 0.0F;
        float f2 = -0.0F;
        if (f1 >= -0.4F && f1 <= 0.4F) {
            float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
            float f4 = 1.0F - (1.0F - Mth.sin(f3 * 3.1415927F)) * 0.99F;
            f4 *= f4;
            sunriseCol[0] = f3 * 0.3F + 0F;
            sunriseCol[1] = f3 * f3 * 0.7F + 0.2F;
            sunriseCol[2] = f3 * f3 * 0.0F + 0.2F;
            sunriseCol[3] = f4;
            return sunriseCol;
        } else {
            return null;
        }
    }
    public static void renderBrushwoodsSky(Minecraft minecraft, ClientLevel level, PoseStack pPoseStack, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean p_202428_, Runnable pSkyFogSetup){
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();



        Vec3 vec3 = level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), pPartialTick);
        float f = (float)vec3.x;
        float f1 = (float)vec3.y;
        float f2 = (float)vec3.z;

        RenderSystem.depthMask(true);
        RenderSystem.enableBlend();
        VertexBuffer.unbind();
        float[] afloat = getSunriseColor(level.getTimeOfDay(pPartialTick), pPartialTick);
        float f11;
        float f12 = 30f;
        float f7;
        float f8;
        float f9;
        if (afloat != null) {
            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            pPoseStack.pushPose();
            pPoseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(90.0F));
            f11 = Mth.sin(level.getSunAngle(pPartialTick)) < 0.0F ? 180.0F : 0.0F;
            pPoseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(f11));
            pPoseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90.0F));
            float f4 = afloat[0];
            float f13 = afloat[1];
            float f6 = afloat[2];
            Matrix4f matrix4f = pPoseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
            bufferbuilder.vertex(matrix4f, 0.0F, 100.0F, 0.0F).color(f4, f13, f6, afloat[3]).endVertex();

            for(int j = 0; j <= 16; ++j) {
                f7 = (float)j * 6.2831855F / 16.0F;
                f8 = Mth.sin(f7);
                f9 = Mth.cos(f7);
                bufferbuilder.vertex(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * afloat[3]).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }
            RenderSystem.setShaderFogColor(afloat[0], afloat[1], afloat[2]);

            BufferUploader.drawWithShader(bufferbuilder.end());
            pPoseStack.popPose();
        }
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(pPartialTick) * 360.0F));
        Matrix4f matrix4f1 = pPoseStack.last().pose();
        f11 = 1.0F - level.getRainLevel(pPartialTick);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f11);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int k = (int) Math.abs(((180-Math.abs(getDayTime(false,level)))/180)*7);
//        TheBrushwoods.LOGGER.info(String.valueOf(k));
        int l = k % 4;
        int i1 = k / 4 % 2;
        float f13 = (float)(l + 0) / 4.0F;
        f7 = (float)(i1 + 0) / 2.0F;
        f8 = (float)(l + 1) / 4.0F;
        f9 = (float)(i1 + 1) / 2.0F;
        RenderSystem.setShaderTexture(0, UMBRA_LOCATION);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, f12).uv(f8, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, f12).uv(f13, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, -100.0F, -f12).uv(f13, f7).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, -100.0F, -f12).uv(f8, f7).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());

        RenderSystem.setShaderTexture(0, LUMA_LOCATION);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(f8, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(f13, f9).endVertex();
        bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(f13, f7).endVertex();
        bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(f8, f7).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        pPoseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(pPartialTick) * -360.0F));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
    }
}






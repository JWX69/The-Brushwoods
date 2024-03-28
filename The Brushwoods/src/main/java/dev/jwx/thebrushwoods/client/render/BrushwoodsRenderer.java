package dev.jwx.thebrushwoods.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class BrushwoodsRenderer{
    private static final ResourceLocation LUMA_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/luma_phases.png");
    private static final ResourceLocation FOG_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/abyss_fog.png");
    private static final ResourceLocation UMBRA_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/umbra_phases.png");
    private static final ResourceLocation SKY_LOCATION = new ResourceLocation(TheBrushwoods.MODID, "environment/brushwoods_sky.png");
    public static float[] fogData = new float[2];
    public static float fogOffset = 0f;
    private static final float[] sunriseCol = new float[4];
    public static int veildAbbysTicks = 0;
    public static float fogShiftAmount = 0;

    public static float getDayTime(Level level) {
        boolean isNatural = level.dimensionType()
                .natural();
        int dayTime = (int) ((level.getDayTime() * (isNatural ? 1 : 24)) % 24000);
        float angle = (float) dayTime /24000 * 360;
        return angle > 180 ? (float) (angle - 200) : angle-30;
    }
    public static float getFogDarkness() {
        return Math.abs(.5f-getMoonPhase(Minecraft.getInstance().level, true));
    }
    public static float getLevelDarkess(Level level) {
        return Math.abs(.5f-getMoonPhase(level, true));
    }
    public static boolean isUmbra(Level level) {
        boolean isNatural = level.dimensionType()
                .natural();
        int dayTime = (int) ((level.getDayTime() * (isNatural ? 1 : 24)) % 24000);
        float angle = (float) dayTime /24000 * 360;
        return angle < 180;
    }
    public static float[] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
        float brightness = 1f;
        float f = 0.4F;
        float f1 = Mth.cos(pTimeOfDay * 6.2831855F) - 0.0F;
        float f2 = -0.0F;
        if (f1 >= -0.4F && f1 <= 0.4F) {
            float f3 = (f1 - -0.0F) / 0.4F * 0.5F + 0.5F;
            float f4 = 1.0F - (1.0F - Mth.sin(f3 * 3.1415927F)) * 0.99F;
            f4 *= f4;
            sunriseCol[0] = (f3 * 0.3F + .1F) * brightness;
            sunriseCol[1] = (f3 * f3 * 0.3F + 0.2F) * brightness;
            sunriseCol[2] = (f3 * f3 * 0.1F + 0.2F) * brightness;
            sunriseCol[3] = (f4) * brightness;
            return sunriseCol;
        } else {
            sunriseCol[0] = .2f;
            sunriseCol[1] = .29f;
            sunriseCol[2] = .17f;
            sunriseCol[3] = 0;
            return null;
        }
    }
    public static float[] getFogDistance() {
        return fogData;
    }
    public static void veieldAbbysTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().level == null) {
            return;
        }
        fogShiftAmount = fogShiftAmount +.01f;
        if (fogShiftAmount > 100)
            fogShiftAmount = 0;
        if (Minecraft.getInstance().level.dimension() == ModDimensions.BW_KEY && Minecraft.getInstance().player.getY() < 0) {
            veildAbbysTicks++;
        } else {
            veildAbbysTicks = 0;
        }
    }
    public static void renderBrushwoodsSky(Minecraft minecraft, ClientLevel level, PoseStack pPoseStack, Matrix4f pProjectionMatrix, float pPartialTick, Camera pCamera, boolean p_202428_, Runnable pSkyFogSetup, VertexBuffer starBuffer){
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        fogData[0] = -0.0F;
        fogData[1] = (float) (46 + (Minecraft.getInstance().player.getY())/8) - (Minecraft.getInstance().player.getY() < 7 ? 30: 0);
        if (Minecraft.getInstance().player.isScoping() || Minecraft.getInstance().player.isSpectator()) {
            fogData[1] = fogData[1] + 200;
        }

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
            FogRenderer.levelFogColor();

            BufferUploader.drawWithShader(bufferbuilder.end());
            pPoseStack.popPose();
        }
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(pPartialTick) * 360.0F));
        Matrix4f matrix4f1 = pPoseStack.last().pose();
        f11 = 1.0F - level.getRainLevel(pPartialTick);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int k = Mth.clamp((int) (getMoonPhase(level,false) * 7),0,7);
//        TheBrushwoods.LOGGER.info(String.valueOf(getMoonPhase(level,true)));
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
        float f10 = Math.abs(.5f-Math.abs(.5F-getMoonPhase(Minecraft.getInstance().level, false)))-.1f;
        if (f10 > 0.0F) {
            RenderSystem.setShaderColor(f10, f10, f10, f10);
            FogRenderer.setupNoFog();
            starBuffer.bind();
            starBuffer.drawWithShader(pPoseStack.last().pose(), pProjectionMatrix, GameRenderer.getPositionShader());
            VertexBuffer.unbind();
            pSkyFogSetup.run();
        }
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.mulPose(Axis.XP.rotationDegrees(level.getTimeOfDay(pPartialTick) * -360.0F));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));

//        pPoseStack.mulPose(Axis.XN.rotationDegrees((float) Math.sin((double) veildAbbysTicks / 60) * 4));
//        pPoseStack.mulPose(Axis.ZN.rotationDegrees((float) Math.sin((double) veildAbbysTicks / 65) * 4));
    }
    @NotNull
    private static Camera getCamera() {
        return Minecraft.getInstance().gameRenderer.getMainCamera();
    }
    public static class AbyssFogSegment {
        AbyssFogSegment(int x, int z) {
            this.x = x;
            this.z = z;
        }
        public float x = 0;
        public float z = 0;
        public static int mainChunkX = 0;
        public static int mainChunkZ = 0;
    }
    public static AbyssFogSegment[] abyysFogSegments = new AbyssFogSegment[9];
    public static void setupSegments() {
        abyysFogSegments[0] = new AbyssFogSegment(-100,-100);
        abyysFogSegments[1] = new AbyssFogSegment(-100,0);
        abyysFogSegments[2] = new AbyssFogSegment(-100,100);
        abyysFogSegments[3] = new AbyssFogSegment(0,-100);
        abyysFogSegments[4] = new AbyssFogSegment(0,0);
        abyysFogSegments[5] = new AbyssFogSegment(0,100);
        abyysFogSegments[6] = new AbyssFogSegment(100,-100);
        abyysFogSegments[7] = new AbyssFogSegment(100,0);
        abyysFogSegments[8] = new AbyssFogSegment(100,100);
    }
    public static void renderVeiledAbysFog(RenderLevelStageEvent event) {
        if (event.getStage()  != RenderLevelStageEvent.Stage.AFTER_WEATHER || Minecraft.getInstance().level.dimension() != ModDimensions.BW_KEY)
            return;
        PoseStack poseStack = event.getPoseStack();
        Player player = Minecraft.getInstance().player;
        AbyssFogSegment.mainChunkX = (((int) Math.round(player.getX()/100))*100);
        AbyssFogSegment.mainChunkZ = ((int) Math.round(player.getZ()/100))*100;
        Camera camera = getCamera();
        poseStack.mulPoseMatrix(new Matrix4f().rotation(camera.rotation()));
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(Minecraft.useShaderTransparency());
        Vec3 camPos = camera.getPosition().scale(-1);
        float fogBrightness = 0.5f;
//        (poseStack).mulPose(Direction.UP, (float) Math.PI);
//        (poseStack).translate(camPos.x,camPos.y, camPos.z);
        if (camera.getPosition().y > -1) {
            for (float i = -7; i < 0; i = i + .151f) {
                for (AbyssFogSegment fogSegment : abyysFogSegments) {
                    RenderSystem.setShaderColor(fogBrightness, fogBrightness, fogBrightness, Math.min(1, Math.abs((i + 5) / 14)));
                    Entity cameraEntity = Minecraft.getInstance().cameraEntity;

                    poseStack.mulPose(Axis.XP.rotationDegrees(180));
                    poseStack.translate(camera.getPosition().x, camera.getPosition().y - 101.5 + i, -camera.getPosition().z);
                    poseStack.translate(-fogSegment.x, 0, fogSegment.z);
                    poseStack.translate(-AbyssFogSegment.mainChunkX-fogShiftAmount, 0, AbyssFogSegment.mainChunkZ);

                    Level level = Minecraft.getInstance().level;
                    Tesselator tesselator = Tesselator.getInstance();
                    BufferBuilder bufferbuilder = tesselator.getBuilder();
                    Matrix4f matrix4f1 = poseStack.last().pose();

                    float f12 = 50f;
                    RenderSystem.setShaderTexture(0, FOG_LOCATION);
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0f, 1f).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1f, 1f).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1f, 0f).endVertex();
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0f, 0f).endVertex();
                    BufferUploader.drawWithShader(bufferbuilder.end());

                    poseStack.translate(AbyssFogSegment.mainChunkX+fogShiftAmount, 0, -AbyssFogSegment.mainChunkZ);
                    poseStack.translate(fogSegment.x, 0, -fogSegment.z);
                    poseStack.translate(-camera.getPosition().x, -camera.getPosition().y + 101.5 - i, camera.getPosition().z);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-180));
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
        if (camera.getPosition().y < 9) {
            for (float i = -5; i < 0; i = i + .151f) {
                for (AbyssFogSegment fogSegment : abyysFogSegments) {
                    RenderSystem.setShaderColor(fogBrightness, fogBrightness, fogBrightness, Math.min(1, Math.abs((i + 5) / 14)));
                    Entity cameraEntity = Minecraft.getInstance().cameraEntity;

//                poseStack.mulPose(Axis.XP.rotationDegrees(180));
                    poseStack.translate(camera.getPosition().x, -camera.getPosition().y - 92.5 + i, camera.getPosition().z);
                    poseStack.translate(-fogSegment.x, 0, fogSegment.z);
                    poseStack.translate(-AbyssFogSegment.mainChunkX-fogShiftAmount, 0, -AbyssFogSegment.mainChunkZ);

                    Level level = Minecraft.getInstance().level;
                    Tesselator tesselator = Tesselator.getInstance();
                    BufferBuilder bufferbuilder = tesselator.getBuilder();
                    Matrix4f matrix4f1 = poseStack.last().pose();

                    float f12 = 50f;
                    RenderSystem.setShaderTexture(0, FOG_LOCATION);
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, -f12).uv(0f, 1f).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, -f12).uv(1f, 1f).endVertex();
                    bufferbuilder.vertex(matrix4f1, f12, 100.0F, f12).uv(1f, 0f).endVertex();
                    bufferbuilder.vertex(matrix4f1, -f12, 100.0F, f12).uv(0f, 0f).endVertex();
                    BufferUploader.drawWithShader(bufferbuilder.end());

                    poseStack.translate(AbyssFogSegment.mainChunkX+fogShiftAmount, 0, AbyssFogSegment.mainChunkZ);
                    poseStack.translate(fogSegment.x, 0, -fogSegment.z);
                    poseStack.translate(-camera.getPosition().x, camera.getPosition().y + 92.5 - i, -camera.getPosition().z);
//                  poseStack.mulPose(Axis.XP.rotationDegrees(-180));
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
    public static float getMoonPhase(Level level, boolean invert) {
//        TheBrushwoods.LOGGER.info(String.valueOf(getDayTime(false,level)));\
        if (invert)
            return dev.jwx.thebrushwoods.util.Mth.getFromRange(0,180,0,1,Math.abs(getDayTime(level)));
        else
            return dev.jwx.thebrushwoods.util.Mth.getFromRange(0,180,1,0,Math.abs(getDayTime(level)));
    }
    @OnlyIn(Dist.CLIENT)
    public static class FogData {
        public final FogRenderer.FogMode mode;
        public float start;
        public float end;
        public FogShape shape;

        public FogData(FogRenderer.FogMode pMode) {
            this.shape = FogShape.SPHERE;
            this.mode = pMode;
        }
    }
}






package net.povstalec.sgjourney.client.render.level;

import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.povstalec.sgjourney.StargateJourney;

public abstract class SGJourneyDimensionSpecialEffects extends DimensionSpecialEffects
{
	public static final ResourceLocation OVERWORLD_EFFECTS = new ResourceLocation("overworld");
	
	public static final ResourceLocation ABYDOS_EFFECTS = new ResourceLocation(StargateJourney.MODID, "abydos");
	public static final ResourceLocation CHULAK_EFFECTS = new ResourceLocation(StargateJourney.MODID, "chulak");
	public static final ResourceLocation LANTEA_EFFECTS = new ResourceLocation(StargateJourney.MODID, "lantea");
	
	public SGJourneyDimensionSpecialEffects(float cloudLevel, boolean hasGround, SkyType skyType, 
			boolean forceBrightLightmap, boolean constantAmbientLight)
	{
		super(cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
	}

	@Override
	public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight)
	{
		return biomeFogColor.multiply((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
	}

	@Override
	public boolean isFoggyAt(int x, int y)
	{
		return false;
	}

	@Override
	public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix)
    {
        return false;
    }
	
	@Override
	public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
    {
        return false;
    }
	
	@Override
	public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ)
    {
        return false;
    }
	
	public static class Abydos extends SGJourneyDimensionSpecialEffects
	{
		public Abydos()
		{
			super(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
		}
		
		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
	    {
			AbydosSkyRenderer skyRenderer = new AbydosSkyRenderer();
			skyRenderer.renderSky(level, partialTick, poseStack, camera, projectionMatrix, setupFog);
			
	        return true;
	    }
	}
	
	public static class Chulak extends SGJourneyDimensionSpecialEffects
	{
		public Chulak()
		{
			super(192.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
		}
		
		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
	    {
			ChulakSkyRenderer skyRenderer = new ChulakSkyRenderer();
			skyRenderer.renderSky(level, partialTick, poseStack, camera, projectionMatrix, setupFog);
			
	        return true;
	    }
	}
	
	public static class Lantea extends SGJourneyDimensionSpecialEffects
	{
		public Lantea()
		{
			super(290.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false);
		}
		
		@Override
		public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
	    {
			LanteaSkyRenderer skyRenderer = new LanteaSkyRenderer();
			skyRenderer.renderSky(level, partialTick, poseStack, camera, projectionMatrix, setupFog);
			
	        return true;
	    }
	}
}

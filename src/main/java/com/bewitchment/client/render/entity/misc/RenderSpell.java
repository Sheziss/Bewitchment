package com.bewitchment.client.render.entity.misc;

import java.util.Random;

import com.bewitchment.common.entity.misc.EntitySpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEndRod;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public class RenderSpell extends Render<EntitySpell>
{
	private static final Random rand = new Random();
	
	public RenderSpell(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySpell entity)
	{
		return null;
	}
	
	@Override
	public void doRender(EntitySpell entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		double cx = (entity.posX - entity.lastTickPosX) * partialTicks + entity.lastTickPosX;
		double cy = (entity.posY - entity.lastTickPosY) * partialTicks + entity.lastTickPosY;
		double cz = (entity.posZ - entity.lastTickPosZ) * partialTicks + entity.lastTickPosZ;
		ParticleEndRod part = new ParticleEndRod(Minecraft.getMinecraft().world, cx, cy, cz, 0.02 * rand.nextGaussian(), 0.02 * rand.nextGaussian(), 0.02 * rand.nextGaussian());
		part.setMaxAge(14);
		Minecraft.getMinecraft().effectRenderer.addEffect(part);
		part = new ParticleEndRod(Minecraft.getMinecraft().world, cx, cy, cz, 0.02 * rand.nextGaussian(), 0.02 * rand.nextGaussian(), 0.02 * rand.nextGaussian());
		part.setMaxAge(5);
		Minecraft.getMinecraft().effectRenderer.addEffect(part);
		Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SPELL_WITCH, cx, cy, cz, 0, 0, 0);
	}
}
package com.bewitchment.registry;

import com.bewitchment.client.particle.ParticleBee;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;

public enum ModParticles
{
	BEE(new ParticleBee.Factory());
	
	private final IParticleFactory factory;
	
	ModParticles(IParticleFactory factory)
	{
		this.factory = factory;
	}
	
	public Particle newInstance(double x, double y, double z)
	{
		return factory.createParticle(ordinal(), Minecraft.getMinecraft().world, x, y, z, 0, 0, 0);
	}
}
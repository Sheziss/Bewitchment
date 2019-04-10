package com.bewitchment.client.particle;

import com.bewitchment.Bewitchment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleBee extends Particle {
	public static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "particle/bee");

	private final double x, y, z;

	public ParticleBee(World world, double posX, double posY, double posZ) {
		super(world, posX, posY, posZ);
		particleScale = 0.2f;
		particleMaxAge = 200;
		x = posX;
		y = posY;
		z = posZ;
		setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(TEX.toString()));
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void onUpdate() {
		motionY *= 0.6;
		move(motionX, motionY, motionZ);
		if (particleMaxAge > 0) {
			particleMaxAge--;
			if (rand.nextBoolean()) {
				motionX += (Math.signum(x + 0.5 - posX) * 0.5 - motionX) * 0.1;
				motionY += (Math.signum(y + 0.1 - posY) * 0.7 - motionY) * 0.1;
				motionZ += (Math.signum(z + 0.5 - posZ) * 0.5 - motionZ) * 0.1;
			} else {
				if (rand.nextBoolean()) {
					motionX -= (Math.signum(x + 0.5 - posX) * 0.5 - motionX) * 0.1;
					motionY -= (Math.signum(y + 0.1 - posY) * 0.7 - motionY) * 0.1;
					motionZ -= (Math.signum(z + 0.5 - posZ) * 0.5 - motionZ) * 0.1;
				}
			}
		} else setExpired();
	}

	public static class Factory implements IParticleFactory {
		@Override
		public Particle createParticle(int id, World world, double x, double y, double z, double speedX, double speedY, double speedZ, int... args) {
			return new ParticleBee(world, x, y, z);
		}
	}
}
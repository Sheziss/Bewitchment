package com.bewitchment.common.entity.misc;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModParticles;
import com.bewitchment.registry.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBeeSwarm extends Entity {
	public EntityBeeSwarm(World world) {
		super(world);
		setSize(0, 0);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void onUpdate() {
		if (ticksExisted >= 150) setDead();
		for (EntityLivingBase living : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPosition()).grow(2))) {
			living.attackEntityFrom(DamageSource.CACTUS, 1);
			living.addPotionEffect(new PotionEffect(MobEffects.POISON, 40));
		}
		if (ticksExisted == 1) for (int i = 0; i < 512; i++)
			Bewitchment.proxy.spawnParticle(ModParticles.BEE, posX - 0.5 + rand.nextDouble(), posY - 0.5 + rand.nextDouble(), posZ - 0.5 + rand.nextDouble());
		if (ticksExisted % 3 == 0)
			world.playSound(null, getPosition(), ModSounds.BUZZ, SoundCategory.BLOCKS, 1, (float) (1 + (rand.nextGaussian() * 0.05)));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
	}
}
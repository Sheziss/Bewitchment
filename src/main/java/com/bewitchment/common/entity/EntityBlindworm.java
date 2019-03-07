package com.bewitchment.common.entity;

import com.bewitchment.common.entity.util.ModEntityAnimal;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBlindworm extends ModEntityAnimal
{	
	public EntityBlindworm(World world)
	{
		super(world, "blindworm");
		setSize(1, 0.3f);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntityBlindworm(world);
	}
	
	@Override
	public boolean canMateWith(EntityAnimal other)
	{
		if (other == this || !(other instanceof EntityBlindworm)) return false;
		return isInLove() && other.isInLove();
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return stack.getItem() == Items.SPIDER_EYE;
	}
	
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}
	
	@Override
	public void onStruckByLightning(EntityLightningBolt bolt)
	{
		if (!world.isRemote && !isDead)
		{
			EntityPig entity = new EntityPig(world);
			entity.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			entity.setNoAI(isAIDisabled());
			if (hasCustomName())
			{
				entity.setCustomNameTag(getCustomNameTag());
				entity.setAlwaysRenderNameTag(getAlwaysRenderNameTag());
			}
			world.spawnEntity(entity);
			setDead();
		}
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55);
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.addTask(0, new EntityAIPanic(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 1.25));
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(0, new EntityAIMate(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() / 2));
        tasks.addTask(1, new EntityAIWatchClosest2(this, EntityPlayer.class, 5, 1));
        tasks.addTask(2, new EntityAIFollowParent(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
        tasks.addTask(2, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 2 / 3));
        tasks.addTask(2, new EntityAILookIdle(this));
	}
}
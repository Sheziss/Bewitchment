package com.bewitchment.common.entity.living;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.util.ModEntityAnimal;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNewt extends ModEntityAnimal
{
	public EntityNewt(World world)
	{
		super(world, new ResourceLocation(Bewitchment.MOD_ID, "entities/newt"));
		setSize(1, 0.3f);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntityNewt(world);
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return stack.getItem() == Items.SPIDER_EYE;
	}
	
	@Override
	public boolean canMateWith(EntityAnimal other)
	{
		if (other == this || !(other instanceof EntityNewt)) return false;
		return isInLove() && other.isInLove();
	}
	
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}
	
	@Override
	protected int getSkinTypes()
	{
		return 4;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5);
//		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(6);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.85);
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.addTask(0, new EntityAIPanic(this, 0.5));
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIMate(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() / 2));
		tasks.addTask(1, new EntityAIAttackMelee(this, getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue(), false));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 5, 1));
		tasks.addTask(2, new EntityAIFollowParent(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(4, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 2 / 3));
	}
}
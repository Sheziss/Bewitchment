package com.bewitchment.common.entity;

import com.bewitchment.common.entity.util.ModEntityTameable;
import com.bewitchment.common.registry.ModSounds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityOwl extends ModEntityTameable
{
	public EntityOwl(World world)
	{
		super(world, "owl", Items.RABBIT, Items.CHICKEN);
		setSize(0.4f, 0.9f);
		moveHelper = new EntityFlyHelper(this);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntityOwl(world);
	}
	
	@Override
	protected int getSkinTypes()
	{
		return 4;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
    {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (source.getTrueSource() != null && !(source.getTrueSource() instanceof EntityPlayer) && !(source.getTrueSource() instanceof EntityArrow)) amount = (amount + 1) / 2f;
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean canMateWith(EntityAnimal other)
	{
		if (other == this || !(other instanceof EntityOwl)) return false;
		return isTamed() && isInLove() && ((EntityTameable) other).isTamed() && other.isInLove() && !((EntityTameable) other).isSitting();
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return stack.getItem() == Items.RABBIT;
	}
	
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}
	
	@Override
	public void fall(float distance, float damageMultiplier)
	{
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (!onGround && motionY <= 0) motionY *= 0.6;
	}
	
	@Override
	protected PathNavigate createNavigator(World world)
	{
		PathNavigateFlying path = new PathNavigateFlying(this, world);
		path.setCanEnterDoors(true);
		path.setCanFloat(true);
		path.setCanOpenDoors(false);
		return path;
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.OWL_HOOT;
	}
	
	@Override
	protected float getSoundVolume()
	{
		return 0.5f;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5);
		getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.8);
//		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(6);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6);
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIFleeSun(this, 1));
		tasks.addTask(2, new EntityAIMate(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() / 2));
		tasks.addTask(2, new EntityAIAttackMelee(this, getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue(), false));
		tasks.addTask(3, new EntityAIWatchClosest2(this, EntityPlayer.class, 5, 1));
		tasks.addTask(3, aiSit);
		tasks.addTask(3, new EntityAIFollowParent(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
        tasks.addTask(3, new EntityAIWanderAvoidWaterFlying(this, getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).getAttributeValue()));
        tasks.addTask(4, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).getAttributeValue()));
        tasks.addTask(5, new EntityAIFollowOwnerFlying(this, 0.5, 2, 24));
        targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(0, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(2, new EntityAITargetNonTamed<EntityLivingBase>(this, EntityLivingBase.class, false, e -> e instanceof EntityBat || e instanceof EntityBlindworm || e instanceof EntityChicken || e instanceof EntityLizard || e instanceof EntityParrot || e instanceof EntityRabbit));
	}
	
	@Override
	protected void updateFallState(double y, boolean grounded, IBlockState state, BlockPos pos)
	{
	}
}
package com.bewitchment.common.entity.hostile;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.entity.util.ModEntityMob;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntitySerpent extends ModEntityMob
{
	public static final Animation BITE = Animation.create(10);
	
	public EntitySerpent(World world)
	{
		super(world, new ResourceLocation(Bewitchment.MOD_ID, "entities/snake"));
		setSize(1, 0.85f);
		isImmuneToFire = true;
		setPathPriority(PathNodeType.WATER, -1);
		setPathPriority(PathNodeType.LAVA, 8);
		setPathPriority(PathNodeType.DANGER_FIRE, 0);
		setPathPriority(PathNodeType.DAMAGE_FIRE, 0);
		experienceValue = 20;
	}
	
	@Override
	public Animation[] getAnimations()
	{
		return new Animation[] {IAnimatedEntity.NO_ANIMATION, BITE};
	}
	
	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return BewitchmentAPI.DEMON;
	}
	
	@Override
	protected int getSkinTypes()
	{
		return 9;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		super.attackEntityAsMob(entity);
		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		if (flag)
		{
			applyEnchantments(this, entity);
			if (entity instanceof EntityLivingBase && getAnimation() != BITE)
			{
				setAnimation(BITE);
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 0, false, false));
			}
		}
		return flag;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean flag = super.attackEntityFrom(source, amount);
		if (!world.isRemote && flag && source == DamageSource.DROWN && hurtTime == 1)
		{
			for (int i = 0; i < 20; i++) ((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + (rand.nextDouble() - 0.5) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5) * width, 1, 0, 0, 0);
			playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);
		}
		return flag;
	}
	
	@Override
	public boolean getCanSpawnHere()
	{
		return (world.provider.doesWaterVaporize() || world.provider.isNether()) && !world.containsAnyLiquid(getEntityBoundingBox()) && super.getCanSpawnHere();
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (isWet()) attackEntityFrom(DamageSource.DROWN, 2.5f);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64);
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 0.5, false));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 5, 1));
		tasks.addTask(3, new EntityAILookIdle(this));
		tasks.addTask(3, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * (2 / 3d)));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 10, false, false, p -> p.getDistanceSq(this) < 2));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 10, false, false, e -> e instanceof EntityAnimal || (!e.isImmuneToFire() && e.getCreatureAttribute() != BewitchmentAPI.DEMON && e.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD)));
	}
}
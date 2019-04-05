package com.bewitchment.common.entity.spirits.demons;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.entity.spirits.demons.EntitySerpent;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHellhound extends ModEntityMob
{
	public static final Animation BITE = Animation.create(10);
	
	public EntityHellhound(World world)
	{
		this(world, new ResourceLocation(Bewitchment.MOD_ID, "entities/hellhound"));
	}
	
	protected EntityHellhound(World world, ResourceLocation lootTableLocation)
	{
		super(world, lootTableLocation);
		setSize(0.6f, 0.85f);
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
		return 6;
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
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2000, 1, false, false));
				entity.setFire(5);
			}
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
		if (isWet() && !isNonBoss()) attackEntityFrom(DamageSource.DROWN, 2.5f);
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.25);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6);
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
		targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, 10, false, false, e -> e instanceof EntitySerpent || (!e.isImmuneToFire() && e.getCreatureAttribute() != BewitchmentAPI.DEMON && e.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD)));
	}
}
package com.bewitchment.common.entity.spirits.demons;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.entity.util.ModEntityMob;
import com.bewitchment.registry.ModObjects;

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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntitySerpent extends ModEntityMob
{
	public static final Animation BITE = Animation.create(10);
	
	private int milkTimer;
	
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
	public boolean getCanSpawnHere()
	{
		return (world.provider.doesWaterVaporize() || world.provider.isNether()) && !world.containsAnyLiquid(getEntityBoundingBox()) && super.getCanSpawnHere();
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		if (!world.isRemote && (getAttackTarget() == null || getAttackTarget().isDead || getRevengeTarget() == null || getRevengeTarget().isDead))
		{
			ItemStack stack = player.getHeldItem(hand);
			if (stack.getItem() == ModObjects.glass_jar)
			{
				if (milkTimer == 0 && getRNG().nextBoolean())
				{
					stack.shrink(1);
					if (stack.isEmpty()) player.setHeldItem(hand, new ItemStack(ModObjects.liquid_wroth));
					else if (!player.inventory.addItemStackToInventory(new ItemStack(ModObjects.liquid_wroth))) player.dropItem(new ItemStack(ModObjects.liquid_wroth), false);
					milkTimer = 6660;
					return true;
				}
				else
				{
					setAttackTarget(player);
					setRevengeTarget(player);
				}
			}
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (isWet())
		{
			attackEntityFrom(DamageSource.DROWN, 2.5f);
			if (hurtTime == 1)
			{
				for (int i = 0; i < 20; i++) world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX + (rand.nextDouble() - 0.5) * width, posY + rand.nextDouble() * height, posZ + (rand.nextDouble() - 0.5) * width, 0, 0, 0);
				world.playSound(null, getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.HOSTILE, 1, 1);
			}
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("milk_timer", milkTimer);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		milkTimer = tag.getInteger("milk_timer");
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65);
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
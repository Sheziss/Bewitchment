package com.bewitchment.common.entity;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModItems;

import moriyashiine.froglib.common.entity.FLEntityTameable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntitySnake extends FLEntityTameable
{
	public int animation_timer = 0;
	private int milk_timer = 0;
	
	public EntitySnake(World world)
	{
		super(world, new ResourceLocation(Bewitchment.MOD_ID, "snake"), Items.CHICKEN, Items.RABBIT);
		setSize(1, 0.3f);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return new EntitySnake(world);
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return stack.getItem() == Items.RABBIT;
	}
	
	@Override
	protected int getSkinTypes()
	{
		return 6;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
    {
		if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()))
		{
			this.applyEnchantments(this, entity);
			if (entity instanceof EntityLivingBase) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 2000, 1, false, false));
		}
		return super.attackEntityAsMob(entity);
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
		if (other == this || !(other instanceof EntitySnake)) return false;
		return isTamed() && isInLove() && ((EntityTameable) other).isTamed() && other.isInLove() && !((EntityTameable) other).isSitting();
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		if (getAttackTarget() == null || getAttackTarget().isDead || getRevengeTarget() == null || getRevengeTarget().isDead)
		{
			ItemStack stack = player.getHeldItem(hand);
			if (stack.getItem() == ModItems.glass_jar)
			{
				if (milk_timer == 0 && getRNG().nextBoolean())
				{
					if (getGrowingAge() >= 0 && !player.isCreative())
					{
						stack.shrink(1);
						if (stack.isEmpty()) player.setHeldItem(hand, new ItemStack(ModItems.snake_venom));
						else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.snake_venom))) player.dropItem(new ItemStack(ModItems.snake_venom), false);
						milk_timer = 3600;
						return true;
					}
					else
					{
						setAttackTarget(player);
						setRevengeTarget(player);
					}
				}
			}
		}
		return super.processInteract(player, hand);
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
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (milk_timer > 0) milk_timer--;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55);
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(0, new EntityAIMate(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() / 2));
		tasks.addTask(1, new EntityAIAttackMelee(this, getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue(), false));
		tasks.addTask(2, new EntityAIWatchClosest2(this, EntityPlayer.class, 5, 1));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAIFollowParent(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
		tasks.addTask(3, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 2 / 3));
		tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIFollowOwner(this, 0.5, 2, 24));
        targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(0, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(2, new EntityAITargetNonTamed<>(this, EntityPlayer.class, true, e -> e.getDistanceSq(this) < 1));
		targetTasks.addTask(3, new EntityAITargetNonTamed<EntityLivingBase>(this, EntityLivingBase.class, false, e -> e instanceof EntityBlindworm || e instanceof EntityChicken || e instanceof EntityLizard || e instanceof EntityRabbit));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		tag.setInteger("animation_timer", animation_timer);
		tag.setInteger("milk_timer", milk_timer);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		animation_timer = tag.getInteger("animation_timer");
		milk_timer = tag.getInteger("milk_timer");
	}
}
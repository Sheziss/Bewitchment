package com.bewitchment.common.entity.util;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public abstract class FLEntityTameable extends EntityTameable
{
	public static final DataParameter<Integer> SKIN = EntityDataManager.createKey(FLEntityTameable.class, DataSerializers.VARINT);
	
	private final ResourceLocation loot_table;
	
	private final Set<Item> tame_items;
	
	public FLEntityTameable(World world, ResourceLocation loot_table_location, Item... tame_items)
	{
		super(world);
		this.tame_items = Sets.newHashSet(tame_items);
		this.loot_table = loot_table_location;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (isEntityInvulnerable(source)) return false;
		if (aiSit != null) aiSit.setSitting(false);
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	protected void collideWithEntity(Entity entity)
	{
		if (!entity.equals(getOwner())) super.collideWithEntity(entity);
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		aiSit = new EntityAISit(this);
		if (getSkinTypes() > 1) dataManager.register(SKIN, rand.nextInt(getSkinTypes()));
	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return loot_table;
	}
	
	protected int getSkinTypes()
	{
		return 1;
	}
	
	@Override
	public abstract boolean isBreedingItem(ItemStack stack);
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data)
    {
		dataManager.set(SKIN, rand.nextInt(getSkinTypes()));
		return super.onInitialSpawn(difficulty, data);
    }
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (!isTamed() && tame_items.contains(stack.getItem()))
		{
			if (!player.isCreative()) stack.shrink(1);
			if (!isSilent()) world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_PARROT_EAT, getSoundCategory(), 1, 1 + (rand.nextFloat() - rand.nextFloat()) * 0.2f);
			if (!world.isRemote)
			{
				if (rand.nextInt(5) == 0 && !ForgeEventFactory.onAnimalTame(this, player))
				{
					setTamedBy(player);
					playTameEffect(true);
					world.setEntityState(this, (byte) 7);
				}
				else
				{
					playTameEffect(false);
					world.setEntityState(this, (byte) 6); 
				}
			}
			return true;
		}
		else if (!player.isSneaking() && isTamed()) setSitting(!isSitting());
		return super.processInteract(player, hand);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		if (getSkinTypes() > 1) dataManager.set(SKIN, tag.getInteger("skin"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		if (getSkinTypes() > 1)
		{
			tag.setInteger("skin", dataManager.get(SKIN));
			dataManager.setDirty(SKIN);
		}
	}
}
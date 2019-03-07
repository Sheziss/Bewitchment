package com.bewitchment.common.entity.util;

import javax.annotation.Nullable;

import com.bewitchment.Bewitchment;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class ModEntityAnimal extends EntityAnimal
{
	public static final DataParameter<Integer> SKIN = EntityDataManager.createKey(ModEntityAnimal.class, DataSerializers.VARINT);
	
	private final ResourceLocation loot_table;
	
	public ModEntityAnimal(World world, String name)
	{
		super(world);
		this.loot_table = new ResourceLocation(Bewitchment.MOD_ID, "entities/" + name);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data)
    {
		if (getSkinTypes() > 1) dataManager.set(SKIN, rand.nextInt(getSkinTypes()));
		return super.onInitialSpawn(difficulty, data);
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
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		if (getSkinTypes() > 1) dataManager.set(SKIN, tag.getInteger("skin"));
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		if (getSkinTypes() > 1) dataManager.register(SKIN, 0);
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
}
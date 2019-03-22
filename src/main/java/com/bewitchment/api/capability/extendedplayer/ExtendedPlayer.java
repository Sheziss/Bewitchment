package com.bewitchment.api.capability.extendedplayer;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.Fortune;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ExtendedPlayer implements ICapabilitySerializable<NBTTagCompound>, IStorage<ExtendedPlayer>
{
	@CapabilityInject(ExtendedPlayer.class)
	public static final Capability<ExtendedPlayer> CAPABILITY = null;
	
	private TransformationType transformation;
	private Fortune fortune;
	
	private boolean fortuneActive, fortuneRemoveable;
	
	public TransformationType getTransformation()
	{
		return transformation == null ? TransformationType.NONE : transformation;
	}
	
	public void setTransformation(TransformationType transformation)
	{
		this.transformation = transformation;
	}
	
	public Fortune getFortune()
	{
		return fortune;
	}
	
	public void setFortune(Fortune fortune)
	{
		this.fortune = fortune;
		setFortuneActive(false);
		setFortuneRemoveable(false);
	}
	
	public boolean isFortuneActive()
	{
		return fortuneActive;
	}
	
	public void setFortuneActive(boolean active)
	{
		this.fortuneActive = active;
	}
	
	public boolean isFortuneRemoveable()
	{
		return fortuneRemoveable;
	}
	
	public void setFortuneRemoveable(boolean removeable)
	{
		this.fortuneRemoveable = removeable;
	}
	
	@Override
	public NBTBase writeNBT(Capability<ExtendedPlayer> capability, ExtendedPlayer instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("transformation", instance.getTransformation().ordinal());
		tag.setString("fortune", fortune == null ? "" : instance.getFortune().getRegistryName().toString());
		tag.setBoolean("fortuneActive", instance.isFortuneActive());
		tag.setBoolean("fortuneRemoveable", instance.isFortuneRemoveable());
		return tag;
	}
	
	@Override
	public void readNBT(Capability<ExtendedPlayer> capability, ExtendedPlayer instance, EnumFacing side, NBTBase nbt)
	{
		NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setTransformation(TransformationType.values()[tag.getInteger("transformation")]);
		instance.setFortune(tag.getString("fortune").isEmpty() ? null : BewitchmentAPI.REGISTRY_FORTUNE.getValue(new ResourceLocation(tag.getString("fortune"))));
		instance.setFortuneActive(tag.getBoolean("fortuneActive"));
		instance.setFortuneRemoveable(tag.getBoolean("fortuneRemoveable"));
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == CAPABILITY ? CAPABILITY.cast(this) : null;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CAPABILITY;
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		return (NBTTagCompound) CAPABILITY.getStorage().writeNBT(CAPABILITY, this, null);
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		CAPABILITY.getStorage().readNBT(CAPABILITY, this, null, nbt);
	}
	
	public enum TransformationType
	{
		NONE(true), WEREWOLF(false), VAMPIRE(false), SPECTRE(false), HUNTER(false);
		
		public final boolean canCrossSalt;
		
		private TransformationType(boolean canCrossSalt)
		{
			this.canCrossSalt = canCrossSalt;
		}
	}
}
package com.bewitchment.api.capability.transformation;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class Transformation implements ICapabilitySerializable<NBTTagCompound>, IStorage<Transformation>
{
	@CapabilityInject(Transformation.class)
	public static final Capability<Transformation> CAPABILITY = null;
	
	public enum TransformationType
	{
		NONE(true), WEREWOLF(false), VAMPIRE(false), SPECTRE(false), HUNTER(false);
		
		public final boolean canCrossSalt;
		
		private TransformationType(boolean canCrossSalt)
		{
			this.canCrossSalt = canCrossSalt;
		}
	}
	
	private TransformationType transformation;
	
	public TransformationType getTransformation()
	{
		return transformation == null ? TransformationType.NONE : transformation;
	}
	
	public void setTransformation(TransformationType transformation)
	{
		this.transformation = transformation;
	}
	
	@Override
	public NBTBase writeNBT(Capability<Transformation> capability, Transformation instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("transformation", instance.getTransformation().ordinal());
		return tag;
	}
	
	@Override
	public void readNBT(Capability<Transformation> capability, Transformation instance, EnumFacing side, NBTBase nbt)
	{
		instance.setTransformation(TransformationType.values()[((NBTTagCompound) nbt).getInteger("transformation")]);
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
}
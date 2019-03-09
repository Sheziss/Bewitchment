package com.bewitchment.api.capability.transformation;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TransformationProvider implements ICapabilitySerializable<NBTTagCompound>
{
	@CapabilityInject(TransformationCapability.class)
	public static final Capability<TransformationCapability> TRANSFORMATION = null;
	
	private TransformationCapability instance = TRANSFORMATION.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == TRANSFORMATION;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == TRANSFORMATION ? TRANSFORMATION.cast(instance) : null;
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		return (NBTTagCompound) TRANSFORMATION.getStorage().writeNBT(TRANSFORMATION, instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		TRANSFORMATION.getStorage().readNBT(TRANSFORMATION, instance, null, nbt);
	}
}
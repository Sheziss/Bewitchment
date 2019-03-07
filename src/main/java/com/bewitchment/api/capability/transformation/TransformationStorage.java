package com.bewitchment.api.capability.transformation;

import com.bewitchment.api.capability.transformation.TransformationCapability.Transformation;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TransformationStorage implements IStorage<TransformationCapability>
{
	@Override
	public NBTBase writeNBT(Capability<TransformationCapability> capability, TransformationCapability instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("transformation", instance.getTransformation().ordinal());
		return tag;
	}
	
	@Override
	public void readNBT(Capability<TransformationCapability> capability, TransformationCapability instance, EnumFacing side, NBTBase nbt)
	{
		instance.setTransformation(Transformation.values()[((NBTTagCompound)nbt).getInteger("transformation")]);
	}
}
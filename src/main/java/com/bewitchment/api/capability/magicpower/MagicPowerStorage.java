package com.bewitchment.api.capability.magicpower;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class MagicPowerStorage implements IStorage<MagicPowerCapability>
{
	@Override
	public NBTBase writeNBT(Capability<MagicPowerCapability> capability, MagicPowerCapability instance, EnumFacing side)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("amount", instance.getAmount());
		tag.setInteger("max_amount", instance.getMaxAmount());
		tag.setInteger("bonux_amount", instance.getBonusAmount());
		return tag;
	}
	
	@Override
	public void readNBT(Capability<MagicPowerCapability> capability, MagicPowerCapability instance, EnumFacing side, NBTBase nbt)
	{
		instance.setAmount(((NBTTagCompound)nbt).getInteger("amount"));
		instance.setMaxAmount(((NBTTagCompound)nbt).getInteger("max_amount"));
		instance.setBonusAmount(((NBTTagCompound)nbt).getInteger("bonux_amount"));
	}
}
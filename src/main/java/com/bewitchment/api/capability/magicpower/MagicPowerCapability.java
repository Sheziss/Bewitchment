package com.bewitchment.api.capability.magicpower;

import com.bewitchment.common.block.BlockWitchesAltar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class MagicPowerCapability
{
	private int amount = 0, max_amount = 0;
	
	public boolean drainAltarFirst(EntityPlayer player, BlockPos pos, int amount)
	{
		if (amount == 0) return true;
		if (BlockWitchesAltar.getNearestAltar(player.world, pos) != null) return player.world.getTileEntity(BlockWitchesAltar.getNearestAltar(player.world, pos)).getCapability(MagicPowerProvider.CAPABILITY, null).drain(amount);
		return player.getCapability(MagicPowerProvider.CAPABILITY, null).drain(amount);
	}
	
	public boolean drain(int amount)
	{
		if (getAmount() > 0)
		{
			setAmount(Math.max(0, getAmount()) - amount);
			return true;
		}
		return false;
	}
	
	public boolean fill(int amount)
	{
		if (getAmount() < getMaxAmount())
		{
			setAmount(Math.min(getAmount() + amount, getMaxAmount()));
			return true;
		}
		return false;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public int getMaxAmount()
	{
		return max_amount;
	}
	
	public MagicPowerCapability setAmount(int amount)
	{
		this.amount = amount;
		return this;
	}
	
	public MagicPowerCapability setMaxAmount(int max_amount)
	{
		this.max_amount = max_amount;
		return this;
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("amount", getAmount());
		tag.setInteger("max_amount", getMaxAmount());
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		setAmount(tag.getInteger("amount"));
		setMaxAmount(tag.getInteger("max_amount"));
	}
}
package com.bewitchment.api.capability.magicpower;

import com.bewitchment.common.block.BlockWitchesAltar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MagicPower implements ICapabilitySerializable<NBTTagCompound>, IStorage<MagicPower>
{
	@CapabilityInject(MagicPower.class)
	public static final Capability<MagicPower> CAPABILITY = null;
	
	private int amount = 0, max_amount = 0, bonus_amount;
	
	public boolean drainAltarFirst(EntityPlayer player, BlockPos pos, int amount)
	{
		if (amount == 0) return true;
		if (BlockWitchesAltar.getNearestAltar(player.world, pos) != null) return player.world.getTileEntity(BlockWitchesAltar.getNearestAltar(player.world, pos)).getCapability(CAPABILITY, null).drain(amount);
		return player.getCapability(CAPABILITY, null).drain(amount);
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
	
	public int getBonusAmount()
	{
		return bonus_amount;
	}
	
	public MagicPower setAmount(int amount)
	{
		this.amount = amount;
		return this;
	}
	
	public MagicPower setMaxAmount(int max_amount)
	{
		this.max_amount = max_amount;
		return this;
	}
	
	public MagicPower setBonusAmount(int bonus_amount)
	{
		this.bonus_amount = bonus_amount;
		return this;
	}
	
	public NBTTagCompound serialize(NBTTagCompound tag)
	{
		return serialize(tag, this);
	}
	
	public void deserialize(NBTTagCompound tag)
	{
		deserialize(tag, this);
	}
	
	private NBTTagCompound serialize(NBTTagCompound tag, MagicPower instance)
	{
		tag.setInteger("amount", instance.getAmount());
		tag.setInteger("max_amount", instance.getMaxAmount());
		tag.setInteger("bonus_amount", instance.getBonusAmount());
		return tag;
	}
	
	private void deserialize(NBTTagCompound tag, MagicPower instance)
	{
		instance.setAmount(tag.getInteger("amount"));
		instance.setMaxAmount(tag.getInteger("max_amount"));
		instance.setBonusAmount(tag.getInteger("bonus_amount"));
	}
	
	@Override
	public NBTBase writeNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side)
	{
		return serialize(new NBTTagCompound(), instance);
	}
	
	@Override
	public void readNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side, NBTBase nbt)
	{
		deserialize((NBTTagCompound) nbt, instance);
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
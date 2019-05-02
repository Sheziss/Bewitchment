package com.bewitchment.api.capability.magicpower;

import com.bewitchment.Util;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class MagicPower implements ICapabilitySerializable<NBTTagCompound>, IStorage<MagicPower> {
	@CapabilityInject(MagicPower.class)
	public static final Capability<MagicPower> CAPABILITY = null;

	private int amount = 0, maxAmount = 0;

	public static boolean attemptDrain(TileEntity tile, EntityPlayer player, int amount) {
		if (amount == 0) return true;
		if (tile instanceof TileEntityWitchesAltar) return tile.getCapability(CAPABILITY, null).drain(amount);
		if (player != null) {
			for (ItemStack stack : Util.getEntireInventory(player)) {
				if (stack.getItem() == ModObjects.grimoire_magia && stack.getCapability(CAPABILITY, null).drain(amount))
					return true;
			}
		}
		return false;
	}

	public boolean drain(int amount) {
		if (getAmount() - amount >= 0) {
			setAmount(Math.max(0, getAmount() - amount));
			return true;
		}
		return false;
	}

	public boolean fill(int amount) {
		if (getAmount() <= getMaxAmount()) {
			setAmount(Math.min(getAmount() + amount, getMaxAmount()));
			return true;
		}
		return false;
	}

	public int getAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public MagicPower setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public MagicPower setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
		return this;
	}

	@Override
	public NBTBase writeNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("amount", instance.getAmount());
		tag.setInteger("maxAmount", instance.getMaxAmount());
		return tag;
	}

	@Override
	public void readNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt;
		if (tag.hasKey("amount")) instance.setAmount(tag.getInteger("amount"));
		if (tag.hasKey("maxAmount")) instance.setMaxAmount(tag.getInteger("maxAmount"));
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		return capability == CAPABILITY ? CAPABILITY.cast(this) : null;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face) {
		return capability == CAPABILITY;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAPABILITY.getStorage().writeNBT(CAPABILITY, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound tag) {
		CAPABILITY.getStorage().readNBT(CAPABILITY, this, null, tag);
	}
}
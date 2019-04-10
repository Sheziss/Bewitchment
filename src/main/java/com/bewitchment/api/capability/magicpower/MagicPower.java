package com.bewitchment.api.capability.magicpower;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants.NBT;

import java.util.HashMap;
import java.util.Map;

public class MagicPower implements ICapabilitySerializable<NBTTagCompound>, IStorage<MagicPower> {
	@CapabilityInject(MagicPower.class)
	public static final Capability<MagicPower> CAPABILITY = null;

	private final Map<String, Integer> upgrades = new HashMap<>();

	private int amount = 0, maxAmount = 0, bonusAmount = 0;

	public static boolean attemptDrain(World world, EntityPlayer player, BlockPos pos, int amount) {
		if (amount == 0) return true;
		if (pos != null && world.getTileEntity(pos) instanceof TileEntityWitchesAltar)
			return world.getTileEntity(pos).getCapability(CAPABILITY, null).drain(amount);
		return player != null && player.getCapability(CAPABILITY, null).drain(amount);
	}

	public boolean drain(int amount) {
		if (getAmount() - amount > 0) {
			setAmount(Math.max(0, (getAmount() + getBonusAmount()) - amount));
			return true;
		}
		return false;
	}

	public boolean fill(int amount) {
		if (getAmount() <= getMaxAmount()) {
			setAmount(Math.min(getAmount() + amount, getMaxAmount() + getBonusAmount()));
			return true;
		}
		return false;
	}

	public void addBonus(String id, int amount) {
		if (!upgrades.containsKey(id)) upgrades.put(id, amount);
	}

	public void removeBonus(String id) {
		if (upgrades.containsKey(id)) upgrades.remove(id);
	}

	public int getAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public int getBonusAmount() {
		return bonusAmount;
	}

	public MagicPower setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public MagicPower setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
		return this;
	}

	public MagicPower setBonusAmount(int bonusAmount) {
		this.bonusAmount = bonusAmount;
		return this;
	}

	@Override
	public NBTBase writeNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side) {
		return serialize(instance, new NBTTagCompound());
	}

	@Override
	public void readNBT(Capability<MagicPower> capability, MagicPower instance, EnumFacing side, NBTBase tag) {
		deserialize(instance, (NBTTagCompound) tag);
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

	public NBTTagCompound serialize(NBTTagCompound tag) {
		return serialize(this, tag);
	}

	public void deserialize(NBTTagCompound tag) {
		deserialize(this, tag);
	}

	public NBTTagCompound serialize(MagicPower instance, NBTTagCompound tag) {
		NBTTagCompound tag_upgrades = new NBTTagCompound();
		for (String value : upgrades.keySet()) {
			int i = 0;
			tag_upgrades.setString("upgrade" + i, value);
			tag_upgrades.setInteger("value" + i, upgrades.get(value));
			i++;
		}
		tag.setTag("upgrades", tag_upgrades);
		tag.setInteger("amount", instance.getAmount());
		tag.setInteger("maxAmount", instance.getMaxAmount());
		tag.setInteger("bonusAmount", instance.getBonusAmount());
		return tag;
	}

	public void deserialize(MagicPower instance, NBTTagCompound tag) {
		for (NBTBase nbt0 : tag.getTagList("upgrades", NBT.TAG_COMPOUND)) {
			int i = 0;
			upgrades.put(((NBTTagCompound) nbt0).getString("upgrade" + i), ((NBTTagCompound) nbt0).getInteger("value" + i));
			i++;
		}
		instance.setAmount(tag.getInteger("amount"));
		instance.setMaxAmount(tag.getInteger("maxAmount"));
		instance.setBonusAmount(tag.getInteger("bonusAmount"));
	}
}
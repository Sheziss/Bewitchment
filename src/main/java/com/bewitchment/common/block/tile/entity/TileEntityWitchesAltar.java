package com.bewitchment.common.block.tile.entity;

import com.bewitchment.Util;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.BlockWitchesAltar;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityWitchesAltar extends ModTileEntity implements ITickable {
	private static final int RADIUS = 18;
	public final MagicPower magicPower = MagicPower.CAPABILITY.getDefaultInstance();
	public int gain = 1;
	public double multiplier = 1;

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		return capability == MagicPower.CAPABILITY ? MagicPower.CAPABILITY.cast(magicPower) : super.getCapability(capability, face);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face) {
		return capability == MagicPower.CAPABILITY || super.hasCapability(capability, face);
	}

	@Override
	public void update() {
		if (!world.isRemote && world.getTotalWorldTime() % 20 == 0) {
			refreshUpgrades();
			double power = 0;
			for (int x = -RADIUS; x < RADIUS; x++) {
				for (int y = -RADIUS; y < RADIUS; y++) {
					for (int z = -RADIUS; z < RADIUS; z++) {
						Block block = world.getBlockState(pos.add(x, y, z)).getBlock();
						double value = BewitchmentAPI.ALTAR_NATURE_VALUES.getOrDefault(block, BewitchmentAPI.NaturePower.NONE).ordinal() / 8d;
						if (value != 0) power += value;
					}
				}
			}
			magicPower.setAmount(Math.min(magicPower.getAmount(), magicPower.getMaxAmount()));
			magicPower.setMaxAmount((int) (power * multiplier));
			magicPower.fill(gain);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("magicPower", magicPower.serializeNBT());
		tag.setInteger("gain", gain);
		tag.setDouble("multiplier", multiplier);
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		magicPower.deserializeNBT(tag.getCompoundTag("magicPower"));
		gain = tag.getInteger("gain");
		multiplier = tag.getDouble("multiplier");
	}

	public void refreshUpgrades() {
		gain = 1;
		multiplier = 1;
		boolean foundSword = false, foundCup = false, foundWand = false, foundPentacle = false;
		for (BlockPos pos : BlockWitchesAltar.getAltarPositions(world, getPos())) {
			Block block = world.getBlockState(pos.up()).getBlock();
			if (!foundSword && checkUpgrades(BewitchmentAPI.UpgradeType.SWORD, block)) foundSword = true;
			if (!foundCup && checkUpgrades(BewitchmentAPI.UpgradeType.CUP, block)) foundCup = true;
			if (!foundWand && checkUpgrades(BewitchmentAPI.UpgradeType.WAND, block)) foundWand = true;
			if (!foundPentacle && checkUpgrades(BewitchmentAPI.UpgradeType.PENTACLE, block)) foundPentacle = true;
			if (block == ModObjects.placed_item) {
				Item placedItem = ((TileEntityPlacedItem) world.getTileEntity(pos.up())).inventory.getStackInSlot(0).getItem();
				if (!foundSword && checkUpgrades(BewitchmentAPI.UpgradeType.SWORD, placedItem)) foundSword = true;
				if (!foundCup && checkUpgrades(BewitchmentAPI.UpgradeType.CUP, placedItem)) foundCup = true;
				if (!foundWand && checkUpgrades(BewitchmentAPI.UpgradeType.WAND, placedItem)) foundWand = true;
				if (!foundPentacle && checkUpgrades(BewitchmentAPI.UpgradeType.PENTACLE, placedItem))
					foundPentacle = true;
				if (placedItem == ModObjects.athame) {
					for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos()).grow(5))) {
						for (ItemStack stack : Util.getEntireInventory(player)) {
							if (stack.getItem() == ModObjects.grimoire_magia) {
								MagicPower cap = stack.getCapability(MagicPower.CAPABILITY, null);
								if (magicPower.drain(100)) cap.fill(10);
								break;
							}
						}
					}
				}
			}
		}
		if (gain < 0) gain = 0;
		if (multiplier < 0) multiplier = 0;
	}

	private boolean checkUpgrades(BewitchmentAPI.UpgradeType type, Item item) {
		if (BewitchmentAPI.isAltarUpgrade(type, item)) {
			gain += BewitchmentAPI.getAltarUpgradeGain(type, item);
			multiplier *= BewitchmentAPI.getAltarUpgradeMultiplier(type, item);
			return true;
		}
		return false;
	}

	private boolean checkUpgrades(BewitchmentAPI.UpgradeType type, Block block) {
		return checkUpgrades(type, Item.getItemFromBlock(block));
	}
}
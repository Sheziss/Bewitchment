package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.BlockWitchesAltar;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
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
			if (world.getTotalWorldTime() % 200 == 0)
				for (BlockPos pos : BlockWitchesAltar.getAltarPositions(world, getPos())) refreshUpgrades(pos.up());
			int natureValue = 0, variety = 1;
			for (int x = -RADIUS; x < RADIUS; x++) {
				for (int y = -RADIUS; y < RADIUS; y++) {
					for (int z = -RADIUS; z < RADIUS; z++) {
						Block block = world.getBlockState(pos.add(x, y, z)).getBlock();
						int value = BewitchmentAPI.ALTAR_NATURE_VALUES.getOrDefault(block, 0);
						if (value != 0) {
							natureValue += value;
							variety++;
						}
					}
				}
			}
			magicPower.setAmount(Math.min(magicPower.getAmount(), magicPower.getMaxAmount()));
			magicPower.setMaxAmount((int) ((natureValue / variety) * multiplier));
			magicPower.fill(gain);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		magicPower.serialize(tag);
		tag.setInteger("gain", gain);
		tag.setDouble("multiplier", multiplier);
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		magicPower.deserialize(tag);
		gain = tag.getInteger("gain");
		multiplier = tag.getDouble("multiplier");
	}

	public void refreshUpgrades(BlockPos pos) {
		gain = 1;
		multiplier = 1;
		Block block = world.getBlockState(pos).getBlock();
		Item item = Item.getItemFromBlock(block);
		gain += BewitchmentAPI.ALTAR_GAIN_VALUES.getOrDefault(item, 0d);
		multiplier += BewitchmentAPI.ALTAR_MULTIPLIER_VALUES.getOrDefault(item, 0);
		//		if (block == ModObjects.placed_item) {
		//			Item placedItem = ((TileEntityPlacedItem) world.getTileEntity(pos)).inventory.getStackInSlot(0).getItem();
		//			if (placedItem == ModObjects.athame) {
		//				for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos).grow(5))) {
		//					MagicPower cap = player.getCapability(MagicPower.CAPABILITY, null);
		//					int amount = Math.min(20, (cap.getMaxAmount() + cap.getBonusAmount()) - cap.getAmount());
		//					if (magicPower.drain(amount)) cap.fill(amount / 10);
		//				}
		//			}
		//		}

	}
}
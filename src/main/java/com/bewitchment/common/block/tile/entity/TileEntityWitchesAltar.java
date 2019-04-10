package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.*;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import java.util.HashMap;
import java.util.Map;

public class TileEntityWitchesAltar extends ModTileEntity implements ITickable {
	public static final Map<Block, Integer> SCAN_VALUES = new HashMap<>();
	public static final Map<Item, Double> SWORD_MULTIPLIER_VALUES = new HashMap<>();
	public static final Map<Item, Integer> SWORD_RADIUS_VALUES = new HashMap<>();

	private static final int RADIUS = 18;
	public final MagicPower magic_power = MagicPower.CAPABILITY.getDefaultInstance();
	public int gain = 1, multiplier = 1;

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		return capability == MagicPower.CAPABILITY ? MagicPower.CAPABILITY.cast(magic_power) : super.getCapability(capability, face);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face) {
		return capability == MagicPower.CAPABILITY || super.hasCapability(capability, face);
	}

	@Override
	public void update() {
		if (!world.isRemote && world.getTotalWorldTime() % 20 == 0) {
			BlockPos altarPos = BlockWitchesAltar.getAltarPosition(world, getPos());
			multiplier = 1;
			gain = 1;
			int variety = 0, radiusAlter = 0;
			double varietyMultiplier = 1;
			// Upgrades
			boolean found_pentacle = false, found_skull = false, found_sword = false, found_light = false, found_pot = false, found_goblet = false, found_bowl = false;
			for (BlockPos pos0 : BlockWitchesAltar.getAltarPositions(world, getPos())) {
				IBlockState state = world.getBlockState(pos0.up());
				if (state.getBlock() instanceof BlockSkull && !found_skull) {
					int type = ((TileEntitySkull) world.getTileEntity(pos0.up())).getSkullType();
					if (type == 1 || type == 3) {
						multiplier += 2;
						varietyMultiplier += 0.2;
					}
					if (type == 0 || type == 2 || type == 4) {
						multiplier++;
						varietyMultiplier += 0.05;
					}
					if (type == 5) {
						multiplier += 2;
						varietyMultiplier += 0.4;
					}
					found_skull = true;
				}
				if (state.getBlock() instanceof BlockGoblet && !found_goblet) {
					varietyMultiplier += state.getValue(BlockGoblet.FULL) ? 0.25 : 0.05;
					found_goblet = true;
				}
				if (!found_light) {
					if (state.getBlock() instanceof BlockTorch) {
						multiplier += 1;
						found_light = true;
					}
					if (state.getBlock() instanceof BlockCandleBase) {
						multiplier += state.getBlock() instanceof BlockLantern ? (state.getValue(BlockCandleBase.LIT) ? 3 : 2) : (state.getValue(BlockCandleBase.LIT) ? 2 : 1);
						found_light = true;
					}
				}
				if (state.getBlock() instanceof BlockFlowerPot && !found_pot) {
					if (state.getBlock().hasTileEntity(state)) {
						TileEntityFlowerPot tile = (TileEntityFlowerPot) world.getTileEntity(pos0.up());
						varietyMultiplier += tile.getFlowerItemStack().isEmpty() ? 0.05 : 0.1;
						found_pot = true;
					}
				}
				if (state.getBlock() instanceof BlockGemBowl && !found_bowl) {
					TileEntityGemBowl tile = (TileEntityGemBowl) world.getTileEntity(pos0.up());
					if (tile.getGemValue() != 0) {
						varietyMultiplier += 0.05 * tile.getGemValue();
						found_bowl = true;
					}
				}
				if (state.getBlock() == ModObjects.placed_item) {
					Item item = ((TileEntityPlacedItem) world.getTileEntity(pos0.up())).inventory.getStackInSlot(0).getItem();
					if (item == ModObjects.pentacle && !found_pentacle) {
						multiplier += 3;
						varietyMultiplier -= 0.2;
						found_pentacle = true;
					}
					if ((SWORD_MULTIPLIER_VALUES.containsKey(item) || SWORD_RADIUS_VALUES.containsKey(item)) && !found_sword) {
						varietyMultiplier += BewitchmentAPI.getAltarSwordMultiplierValue(item);
						radiusAlter += BewitchmentAPI.getAltarSwordRadiusValue(item);
						if (item == ModObjects.athame) {
							for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos).grow(5))) {
								MagicPower cap = player.getCapability(MagicPower.CAPABILITY, null);
								int amount = Math.min(20, (cap.getMaxAmount() + cap.getBonusAmount()) - cap.getAmount());
								if (magic_power.drain(amount)) cap.fill(amount / 10);
							}
						}
						found_sword = true;
					}
				}
			}
			// Plants
			int radius = RADIUS / 2 + radiusAlter;
			for (int x = -radius; x < radius; x++) {
				for (int y = -radius; y < radius; y++) {
					for (int z = -radius; z < radius; z++) {
						Block block = world.getBlockState(altarPos.add(x, y, z)).getBlock();
						int value = BewitchmentAPI.getAltarScanValue(block);
						if (value != 0) {
							gain += value;
							variety++;
						}
					}
				}
			}
			gain /= Math.max(1, variety);
			gain *= multiplier;
			magic_power.setMaxAmount((int) (variety * varietyMultiplier));
			magic_power.setAmount(Math.min(magic_power.getAmount(), magic_power.getMaxAmount()));
			magic_power.fill(gain);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		magic_power.serialize(tag);
		tag.setInteger("gain", gain);
		tag.setInteger("multiplier", multiplier);
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		magic_power.deserialize(tag);
		gain = tag.getInteger("gain");
		multiplier = tag.getInteger("multiplier");
	}
}
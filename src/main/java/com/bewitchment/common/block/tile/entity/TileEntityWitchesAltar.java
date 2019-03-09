package com.bewitchment.common.block.tile.entity;

import java.util.HashMap;
import java.util.Map;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPowerCapability;
import com.bewitchment.api.capability.magicpower.MagicPowerProvider;
import com.bewitchment.common.block.BlockWitchesAltar;
import com.bewitchment.common.block.BlockWitchesAltar.AltarType;
import com.bewitchment.common.registry.ModBlocks;
import com.bewitchment.common.registry.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityWitchesAltar extends TileEntity implements ITickable
{
	public static final Map<Block, Integer> SCAN_VALUES = new HashMap<Block, Integer>();
	public static final Map<Item, Double> SWORD_VALUES = new HashMap<Item, Double>();
	
	private static final int RADIUS = 18;
	
	public int color = EnumDyeColor.RED.ordinal(), gain = 1, multiplier = 1;
	
	private MagicPowerCapability magic_power = MagicPowerProvider.CAPABILITY.getDefaultInstance();
	
	@Override
	public void update()
	{
		if (!world.isRemote && world.getTotalWorldTime() % 20 == 0)
		{
			BlockPos altar_pos = BlockWitchesAltar.getAltarPosition(world, getPos());
			multiplier = 1;
			gain = 1;
			int variety = 0;
			double variety_multiplier = 1;
			//Sword
			for (BlockPos pos0 : BlockWitchesAltar.getAltarPositions(world, pos))
			{
				TileEntityPlacedItem tile = (TileEntityPlacedItem) world.getTileEntity(pos0.up());
				if (tile != null)
				{
					Item item = tile.getItem().getItem();
					if (BewitchmentAPI.getAltarSwordUpgradeValue(item) != 0)
					{
						variety_multiplier = BewitchmentAPI.getAltarSwordUpgradeValue(item);
						break;
					}
					if (item == ModItems.athame)
					{
						for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos).grow(5)))
						{
							MagicPowerCapability cap = player.getCapability(MagicPowerProvider.CAPABILITY, null);
							int amount = Math.min(20, cap.getMaxAmount() - cap.getAmount());
							if (magic_power.drain(amount)) cap.fill(amount / 10);
						}
					}
				}
			}
			//Plants
			for (int x = -RADIUS / 2; x < RADIUS / 2; x++)
			{
				for (int y = -RADIUS / 2; y < RADIUS / 2; y++)
				{
					for (int z = -RADIUS / 2; z < RADIUS / 2; z++)
					{
						Block block = world.getBlockState(altar_pos.add(x, y, z)).getBlock();
						int value = BewitchmentAPI.getAltarScanValue(block);
						if (value != 0)
						{
							gain += value;
							variety++;
						}
					}
				}
			}
			gain /= Math.max(1, variety);
			gain *= multiplier;
			//Upgrades
			magic_power.setMaxAmount((int) (variety * variety_multiplier));
			magic_power.setAmount(Math.min(magic_power.getAmount(), magic_power.getMaxAmount()));
			magic_power.fill(gain);
			markDirty();
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("amount", magic_power.getAmount());
		tag.setInteger("max_amount", magic_power.getMaxAmount());
		tag.setInteger("color", color);
		tag.setInteger("gain", gain);
		tag.setInteger("multiplier", multiplier);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		magic_power.setAmount(tag.getInteger("amount"));
		magic_power.setMaxAmount(tag.getInteger("max_amount"));
		color = tag.getInteger("color");
		gain = tag.getInteger("gain");
		multiplier = tag.getInteger("multiplier");
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == MagicPowerProvider.CAPABILITY || super.hasCapability(capability, face);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == MagicPowerProvider.CAPABILITY ? MagicPowerProvider.CAPABILITY.cast(magic_power) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
		return newState.getBlock() != ModBlocks.witches_altar || newState.getValue(BlockWitchesAltar.TYPE) != AltarType.TILE;
    }
}
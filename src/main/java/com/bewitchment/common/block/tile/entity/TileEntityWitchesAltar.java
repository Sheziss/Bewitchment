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

import moriyashiine.froglib.common.block.tile.ModTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityWitchesAltar extends ModTileEntity implements ITickable
{
	public static final Map<Block, Integer> SCAN_VALUES = new HashMap<Block, Integer>();
	public static final Map<Item, Double> SWORD_MULTIPLIER_VALUES = new HashMap<Item, Double>();
	public static final Map<Item, Integer> SWORD_RADIUS_VALUES = new HashMap<Item, Integer>();
	
	private static final int RADIUS = 18;
	
	public int color = EnumDyeColor.RED.ordinal(), gain = 1, multiplier = 1;
	
	private MagicPowerCapability magic_power = MagicPowerProvider.CAPABILITY.getDefaultInstance();
	
	public TileEntityWitchesAltar()
	{
		super(0);
	}
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			BlockPos altar_pos = BlockWitchesAltar.getAltarPosition(world, getPos());
			for (BlockPos pos0 : BlockWitchesAltar.getAltarPositions(world, pos))
			{
				IBlockState state = world.getBlockState(pos0);
				world.setBlockState(pos0, state.withProperty(BlockWitchesAltar.COLOR, color), 2);
				world.notifyBlockUpdate(pos0, state, state.withProperty(BlockWitchesAltar.COLOR, color), 2);
			}
			if (world.getTotalWorldTime() % 20 == 0)
			{
				multiplier = 1;
				gain = 1;
				int variety = 0, radius_alter = 0;
				double variety_multiplier = 1;
				//Upgrades
				for (BlockPos pos0 : BlockWitchesAltar.getAltarPositions(world, pos))
				{
					TileEntityPlacedItem tile = (TileEntityPlacedItem) world.getTileEntity(pos0.up());
					if (tile != null)
					{
						Item item = tile.getStackInSlot(0).getItem();
						if (SWORD_MULTIPLIER_VALUES.containsKey(item) || SWORD_RADIUS_VALUES.containsKey(item))
						{
							variety_multiplier = BewitchmentAPI.getAltarSwordMultiplierValue(item);
							radius_alter = BewitchmentAPI.getAltarSwordRadiusValue(item);
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
				int radius = RADIUS / 2 + radius_alter;
				for (int x = -radius; x < radius; x++)
				{
					for (int y = -radius; y < radius; y++)
					{
						for (int z = -radius; z < radius; z++)
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
				magic_power.setMaxAmount((int) (variety * variety_multiplier));
				magic_power.setAmount(Math.min(magic_power.getAmount(), magic_power.getMaxAmount()));
				magic_power.fill(gain);
				markDirty();
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		magic_power.writeToNBT(tag);
		tag.setInteger("color", color);
		tag.setInteger("gain", gain);
		tag.setInteger("multiplier", multiplier);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		magic_power.readFromNBT(tag);
		color = tag.getInteger("color");
		gain = tag.getInteger("gain");
		multiplier = tag.getInteger("multiplier");
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
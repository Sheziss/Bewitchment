package com.bewitchment.common.block.tile.entity;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityApiary extends ModTileEntity implements ITickable
{
	public TileEntityApiary()
	{
		super(18);
	}
	
	@Override
	public void update()
	{
		if (!world.isRemote && world.getTotalWorldTime() % 20 == 0 && !isEmpty())
		{
			List<BlockPos> crops = new ArrayList<>(), flowers = new ArrayList<>();
			for (BlockPos pos : BlockPos.getAllInBox(getPos().add(-2, -2, -2), getPos().add(2, 2, 2)))
			{
				if (world.getBlockState(pos).getBlock() instanceof BlockCrops) crops.add(pos);
				if (world.getBlockState(pos).getBlock() instanceof BlockFlower) flowers.add(pos);
			}
			if (!flowers.isEmpty())
			{
				for (BlockPos pos : crops)
				{
					if (world.rand.nextInt(5) == 0)
					{
						IBlockState state = world.getBlockState(pos);
						BlockCrops crop = (BlockCrops) state.getBlock();
						if (crop.canUseBonemeal(world, world.rand, pos, state)) crop.grow(world, pos, state);
					}
				}
				for (BlockPos pos : flowers)
				{
					if (world.rand.nextInt(100) == 0)
					{
						IBlockState state = world.getBlockState(pos);
						BlockFlower flower = (BlockFlower) state.getBlock();
						BlockPos offset = pos.offset(EnumFacing.random(world.rand));
						if (flower.canPlaceBlockAt(world, offset) && MagicPower.drainAltarFirst(world, null, getPos(), 30)) world.setBlockState(offset, state);
					}
				}
				boolean update = false;
				for (int i = 0; i < getSlots(); i++)
				{
					if (world.rand.nextInt(100) == 0)
					{
						ItemStack oldStack = getStackInSlot(i), newStack = growItem(i);
						if (oldStack != newStack && MagicPower.drainAltarFirst(world, null, getPos(), 30))
						{
							setStackInSlot(i, newStack);
							update = true;
						}
					}
				}
				if (update)
				{
					markDirty();
					world.notifyBlockUpdate(pos, world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
				}
			}
		}
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack)
	{
		return stack.getItem() == ModObjects.empty_honeycomb || stack.getItem() == ModObjects.honeycomb || stack.getItem() == Items.ITEM_FRAME;
	}
	
	@Override
	public int getSlotLimit(int slot)
	{
		return 1;
	}
	
	private ItemStack growItem(int i)
	{
		ItemStack stack = getStackInSlot(i);
		if (stack.isEmpty() && world.rand.nextInt(3) == 0)
		{
			for (int j : getNeighbors(i)) if (getStackInSlot(j).isEmpty()) return new ItemStack(ModObjects.empty_honeycomb);
		}
		if (stack.getItem() == ModObjects.empty_honeycomb) return new ItemStack(ModObjects.honeycomb);
		if (stack.getItem() == Items.ITEM_FRAME) return new ItemStack(ModObjects.empty_honeycomb);
		return stack;
	}
	
	private List<Integer> getNeighbors(int i)
	{
		int x = i % 6, y = i / 6;
		List<Integer> res = new ArrayList<>();
		if (x > 0) res.add(i - 1);
		if (i < 5) res.add(i + 1);
		if (y > 0) res.add(i - 6);
		if (y < 2) res.add(i + 6);
		return res;
	}
}
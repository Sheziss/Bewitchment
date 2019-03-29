package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillery extends ModTileEntity implements ITickable
{
	public int burn_time, progress, recipe_time;
	
	public final ItemStackHandler inventory_up = new ItemStackHandler(6);
	public final ItemStackHandler inventory_down = new ItemStackHandler(6)
	{
		@Override
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return false;
		}
	};
	public final ItemStackHandler inventory_side = new ItemStackHandler(1)
	{
		@Override
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return stack.getItem() == Items.BLAZE_POWDER;
		}
	};
	
	@Override
	public void update()
	{
		if (burn_time > 0) burn_time--;
		DistilleryRecipe recipe = BewitchmentAPI.REGISTRY_DISTILLERY.getValuesCollection().parallelStream().filter(p -> p.matches(inventory_up)).findFirst().orElse(null);
		if (recipe == null) progress = 0;
		else if (recipe.canOutputFit(inventory_down))
		{
			if (burn_time == 0 && !inventory_side.getStackInSlot(0).isEmpty())
			{
				burn_time = 1200;
				recipe_time = recipe.getTime();
				if (!world.isRemote) inventory_side.extractItem(0, 1, false);
			}
			else if (burn_time > 0)
			{
				if (MagicPower.drainAltarFirst(world, world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), pos, 2)) progress++;
				if (progress >= recipe.getTime())
				{
					progress = 0;
					recipe_time = 0;
					if (!world.isRemote) recipe.giveOutput(inventory_up, inventory_down);
				}
			}
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(face == EnumFacing.DOWN ? inventory_down : face == EnumFacing.UP ? inventory_up : inventory_side) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, face);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("burn_time", burn_time);
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		burn_time = tag.getInteger("burn_time");
		progress = tag.getInteger("progress");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory_up, inventory_down, inventory_side};
	}
}
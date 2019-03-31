package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityOven extends ModTileEntity implements ITickable
{
	public int burn_time, fuel_burn_time, progress;
	
	private OvenRecipe recipe;
	
	public final ItemStackHandler inventory_up = new ItemStackHandler(3)
	{
		@Override
		public boolean isItemValid(int index, ItemStack stack)
		{
			return index == 0 ? TileEntityFurnace.isItemFuel(stack) : index == 1 ? stack.getItem() == ModObjects.empty_jar : true;
		}
		
		@Override
		protected void onContentsChanged(int index)
		{
			recipe = BewitchmentAPI.REGISTRY_OVEN.getValuesCollection().parallelStream().filter(p -> p.matches(getStackInSlot(2))).findFirst().orElse(null);
		}
	};
	public final ItemStackHandler inventory_down = new ItemStackHandler(2)
	{
		@Override
		public boolean isItemValid(int index, ItemStack stack)
		{
			return false;
		}
	};
	
	@Override
	public void update()
	{
		if (burn_time > 0) burn_time--;
		if (recipe == null) progress = 0;
		else if (recipe.canOutputFit(inventory_down))
		{
			if (burn_time == 0 && !inventory_up.getStackInSlot(0).isEmpty())
			{
				burn_time = TileEntityFurnace.getItemBurnTime(inventory_up.getStackInSlot(0));
				fuel_burn_time = burn_time;
				if (!world.isRemote) inventory_up.extractItem(0, 1, false);
			}
			else if (burn_time > 0)
			{
				progress++;
				if (progress >= 100)
				{
					progress = 0;
					if (!world.isRemote) recipe.giveOutput(world.rand, inventory_up, inventory_down);
				}
			}
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(face == EnumFacing.DOWN ? inventory_down : inventory_up) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, face);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setString("recipe", recipe == null ? "" : recipe.getRegistryName().toString());
		tag.setInteger("burn_time", burn_time);
		tag.setInteger("fuel_burn_time", fuel_burn_time);
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		recipe = tag.getString("recipe").isEmpty() ? null : BewitchmentAPI.REGISTRY_OVEN.getValue(new ResourceLocation(tag.getString("recipe")));
		burn_time = tag.getInteger("burn_time");
		fuel_burn_time = tag.getInteger("fuel_burn_time");
		progress = tag.getInteger("progress");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory_up, inventory_down};
	}
}
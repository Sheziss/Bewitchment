package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityOven extends ModTileEntity implements ITickable
{
	public int burn_time, fuel_burn_time, progress;
	
	public final ItemStackHandler inventory = new ItemStackHandler(5)
	{
		@Override
		public boolean isItemValid(int index, ItemStack stack)
		{
			return index == 0 ? TileEntityFurnace.isItemFuel(stack) : index == 1 ? stack.getItem() == ModObjects.empty_jar : index == 2;
		}
		
		@Override
		protected void onContentsChanged(int slot)
	    {
			markDirty();
	    }
	};
	
	@Override
	public void update()
	{
		OvenRecipe recipe = BewitchmentAPI.REGISTRY_OVEN.getValuesCollection().parallelStream().filter(p -> p.matches(inventory.getStackInSlot(2))).findFirst().orElse(null);
		if (recipe == null) progress = 0;
		else if (recipe.canOutputFit(inventory.getStackInSlot(3), inventory.getStackInSlot(4)))
		{
			if (burn_time > 0) burn_time--;
			if (burn_time == 0 && !inventory.getStackInSlot(0).isEmpty())
			{
				burn_time = TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0));
				fuel_burn_time = burn_time;
				if (!world.isRemote) inventory.extractItem(0, 1, false);
			}
			else if (burn_time > 0)
			{
				progress++;
				if (progress >= 100)
				{
					progress = 0;
					if (!world.isRemote)
					{
						inventory.extractItem(2, 1, false);
						inventory.insertItem(3, recipe.getOutput(), false);
						if (world.rand.nextFloat() < recipe.getByproductChance() && !inventory.getStackInSlot(1).isEmpty())
						{
							inventory.extractItem(1, 1, false);
							inventory.insertItem(4, recipe.getByproduct(), false);
						}
					}
				}
			}
		}
		markDirty();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setTag("inventory", inventory.serializeNBT());
		tag.setInteger("burn_time", burn_time);
		tag.setInteger("fuel_burn_time", fuel_burn_time);
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
		burn_time = tag.getInteger("burn_time");
		fuel_burn_time = tag.getInteger("fuel_burn_time");
		progress = tag.getInteger("progress");
	}
}
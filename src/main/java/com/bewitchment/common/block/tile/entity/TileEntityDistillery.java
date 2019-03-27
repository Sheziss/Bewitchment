package com.bewitchment.common.block.tile.entity;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillery extends ModTileEntity implements ITickable
{
	public static final int BURN_TIME = 1200;
	
	private String current_recipe = "";
	
	public int burn_time, progress, recipe_time;
	
	public final ItemStackHandler inventory = new ItemStackHandler(13)
	{
		@Override
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return slot == 0 ? stack.getItem() == Items.BLAZE_POWDER : slot < 7;
		}
		
		@Override
		public int getSlotLimit(int slot)
		{
			return 1;
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
		if (burn_time > 0) burn_time--;
		if (!current_recipe.isEmpty())
		{
			if (burn_time == 0 && !inventory.getStackInSlot(0).isEmpty())
			{
				burn_time = BURN_TIME;
				inventory.extractItem(0, 1, false);
			}
			else if (burn_time > 0)
			{
				if (progress < recipe_time && MagicPower.drainAltarFirst(world, world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), pos, 2)) progress++;
				else progress = 0;
			}
		}
		checkRecipe();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setTag("inventory", inventory.serializeNBT());
		tag.setString("current_recipe", current_recipe);
		tag.setInteger("burn_time", burn_time);
		tag.setInteger("progress", progress);
		tag.setInteger("recipe_time", recipe_time);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
		current_recipe = tag.getString("current_recipe");
		burn_time = tag.getInteger("burn_time");
		progress = tag.getInteger("progress");
		recipe_time = tag.getInteger("recipe_time");
	}
	
	private boolean canOutputFit(DistilleryRecipe recipe)
	{
		ItemStackHandler sim = new ItemStackHandler(6);
		for (int i = 0; i < sim.getSlots(); i++) sim.setStackInSlot(i, inventory.getStackInSlot(i + 7).copy());
		for (ItemStack stack : recipe.getOutput())
		{
			ItemStack sim0 = stack.copy();
			for (int i = 0; i < sim.getSlots() && !sim0.isEmpty(); i++) sim0 = sim.insertItem(i, sim0, false);
			if (!sim0.isEmpty()) return false;
		}
		return true;
	}
	
	private void checkRecipe()
	{
		List<ItemStack> inputStacks = new ArrayList<>();
		for (int i = 1; i < 7; i++) inputStacks.add(inventory.getStackInSlot(i));
		DistilleryRecipe recipe = BewitchmentAPI.REGISTRY_DISTILLERY.getValuesCollection().parallelStream().filter(p -> p.matches(inputStacks)).findFirst().orElse(null);
		if (recipe == null)
		{
			current_recipe = "";
			progress = 0;
			recipe_time = -1;
		}
		else if (!current_recipe.equals(recipe.getRegistryName().toString()) && canOutputFit(recipe))
		{
			current_recipe = recipe.getRegistryName().toString();
			recipe_time = recipe.getTime();
		}
		if (recipe != null && progress >= recipe_time)
		{
			int i = 0;
			for (i = 1; i < 7; i++) inventory.extractItem(i, 1, false);
			for (ItemStack stack : recipe.getOutput()) inventory.insertItem(inventory.getStackInSlot(i).getItem() != stack.getItem() || inventory.getStackInSlot(i).getMetadata() != stack.getMetadata() || inventory.getStackInSlot(i).getCount() >= inventory.getStackInSlot(i).getMaxStackSize() ? i++ : i, stack, false);
		}
		markDirty();
	}
}
package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityLoom extends TileEntityAltarStorage implements ITickable
{
	public int progress;
	
	private LoomRecipe recipe;
	
	public final ItemStackHandler inventory_up = new ItemStackHandler(4)
	{
		@Override
		protected void onContentsChanged(int index)
		{
			recipe = BewitchmentAPI.REGISTRY_LOOM.getValuesCollection().parallelStream().filter(p -> p.matches(this)).findFirst().orElse(null);
		}
	};
	public final ItemStackHandler inventory_down = new ItemStackHandler(1)
	{
		@Override
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return false;
		}
	};
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			if (recipe == null || !recipe.isValid(inventory_up, inventory_down)) progress = 0;
			else
			{
				if (MagicPower.attemptDrain(world, world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), getAltarPosition(), 6)) progress++;
				if (progress >= 200)
				{
					progress = 0;
					recipe.giveOutput(inventory_up, inventory_down);
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
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		recipe = tag.getString("recipe").isEmpty() ? null : BewitchmentAPI.REGISTRY_LOOM.getValue(new ResourceLocation(tag.getString("recipe")));
		progress = tag.getInteger("progress");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory_up, inventory_down};
	}
}
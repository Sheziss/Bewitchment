package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityLoom extends ModTileEntity implements ITickable
{
	public int progress;
	
	public final ItemStackHandler inventory_up = new ItemStackHandler(4);
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
		LoomRecipe recipe = BewitchmentAPI.REGISTRY_LOOM.getValuesCollection().parallelStream().filter(p -> p.matches(inventory_up)).findFirst().orElse(null);
		if (recipe == null) progress = 0;
		else if (recipe.canOutputFit(inventory_down))
		{
			if (MagicPower.drainAltarFirst(world, world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), pos, 6)) progress++;
			if (progress >= 200)
			{
				progress = 0;
				if (!world.isRemote) recipe.giveOutput(inventory_up, inventory_down);
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
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		progress = tag.getInteger("progress");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory_up, inventory_down};
	}
}
package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillery extends TileEntityAltarStorage implements ITickable {
	public final ItemStackHandler inventory_down = new ItemStackHandler(6) {
		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			return false;
		}
	};
	public final ItemStackHandler inventory_side = new ItemStackHandler(1) {
		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			return stack.getItem() == Items.BLAZE_POWDER;
		}
	};
	public int burnTime, progress, recipeTime;
	private DistilleryRecipe recipe;
	public final ItemStackHandler inventory_up = new ItemStackHandler(6) {
		@Override
		protected void onContentsChanged(int index) {
			recipe = BewitchmentAPI.REGISTRY_DISTILLERY.getValuesCollection().stream().filter(p -> p.matches(this)).findFirst().orElse(null);
		}
	};

	@Override
	public void update() {
		if (!world.isRemote) {
			if (burnTime > 0) burnTime--;
			else if (progress > 0) {
				progress -= 2;
				if (progress < 0) progress = 0;
			}
			if (recipe == null || !recipe.isValid(inventory_up, inventory_down)) progress = 0;
			else {
				if (burnTime == 0 && !inventory_side.getStackInSlot(0).isEmpty() && !isEmpty(inventory_up)) {
					burnTime = 1200;
					recipeTime = recipe.getTime();
					inventory_side.extractItem(0, 1, false);
				} else if (burnTime > 0) {
					if (getAltarPosition() != null && MagicPower.attemptDrain(world.getTileEntity(getAltarPosition()), world.getClosestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, false), 2))
						progress++;
					if (progress >= recipe.getTime()) {
						progress = 0;
						recipeTime = 0;
						recipe.giveOutput(inventory_up, inventory_down);
					}
				}
			}
		}
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(face == EnumFacing.DOWN ? inventory_down : face == EnumFacing.UP ? inventory_up : inventory_side) : super.getCapability(capability, face);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, face);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setString("recipe", recipe == null ? "" : recipe.getRegistryName().toString());
		tag.setInteger("burnTime", burnTime);
		tag.setInteger("progress", progress);
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		recipe = tag.getString("recipe").isEmpty() ? null : BewitchmentAPI.REGISTRY_DISTILLERY.getValue(new ResourceLocation(tag.getString("recipe")));
		burnTime = tag.getInteger("burnTime");
		progress = tag.getInteger("progress");
	}

	@Override
	public ItemStackHandler[] getInventories() {
		return new ItemStackHandler[]{inventory_up, inventory_down, inventory_side};
	}
}
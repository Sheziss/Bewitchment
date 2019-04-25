package com.bewitchment.api.registry;

import java.util.Random;

import com.bewitchment.Util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class OvenRecipe extends IForgeRegistryEntry.Impl<OvenRecipe> {
	private final ItemStack input, output, byproduct;
	private final float byproductChance;

	public OvenRecipe(String modid, String name, ItemStack input, ItemStack output, ItemStack byproduct, float byproductChance) {
		this.setRegistryName(new ResourceLocation(modid, name));
		this.input = input;
		this.output = output;
		this.byproduct = byproduct;
		this.byproductChance = byproductChance;
	}

	/**
	 * @return the ItemStack to be given as a byproduct output.
	 */
	public ItemStack getByproduct() {
		return byproduct;
	}

	/**
	 * @return the chance for the byproduct to appear.
	 */
	public float getByproductChance() {
		return byproductChance;
	}

	/**
	 * @return the ItemStack to be used as input.
	 */
	public ItemStack getInput() {
		return input;
	}

	/**
	 * @return the ItemStack to be given as output.
	 */
	public ItemStack getOutput() {
		return output;
	}

	public final boolean matches(ItemStack input) {
		return Util.areStacksEqual(input, getInput());
	}

	public final boolean isValid(ItemStackHandler input, ItemStackHandler output) {
		boolean outputValid = output.getStackInSlot(0).isEmpty() || (Util.areStacksEqual(output.getStackInSlot(0), getOutput()) && output.getStackInSlot(0).getCount() < output.getStackInSlot(0).getMaxStackSize());
		boolean byproductValid = output.getStackInSlot(1).isEmpty() || (Util.areStacksEqual(output.getStackInSlot(1), getByproduct()) && output.getStackInSlot(1).getCount() < output.getStackInSlot(1).getMaxStackSize() - (getByproduct().getCount() - 1));
		return !input.getStackInSlot(2).isEmpty() && outputValid && byproductValid;
	}

	public final void giveOutput(Random rand, ItemStackHandler input, ItemStackHandler output) {
		input.extractItem(2, 1, false);
		output.insertItem(0, getOutput().copy(), false);
		if (!getByproduct().isEmpty() && rand.nextFloat() < getByproductChance() && !input.getStackInSlot(1).isEmpty()) {
			input.extractItem(1, 1, false);
			output.insertItem(1, getByproduct().copy(), false);
		}
	}
}
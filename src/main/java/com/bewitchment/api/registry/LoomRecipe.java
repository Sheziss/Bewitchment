package com.bewitchment.api.registry;

import com.bewitchment.Util;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

public class LoomRecipe extends IForgeRegistryEntry.Impl<LoomRecipe> {
	private final List<Ingredient> input;
	private final ItemStack output;

	public LoomRecipe(String modid, String name, List<Ingredient> input, ItemStack output) {
		this.setRegistryName(new ResourceLocation(modid, name));
		this.input = input;
		this.output = output;
	}

	/**
	 * @return the list of Ingredients to be used as input.
	 */
	public List<Ingredient> getInput() {
		return input;
	}

	/**
	 * @return the output ItemStack.
	 */
	public ItemStack getOutput() {
		return output;
	}

	public final boolean matches(ItemStackHandler handler) {
		List<ItemStack> checklist = new ArrayList<>();
		for (int i = 0; i < handler.getSlots(); i++)
			if (!handler.getStackInSlot(i).isEmpty()) checklist.add(handler.extractItem(i, 1, true));
		return Util.areISListsEqual(getInput(), checklist);
	}

	public final boolean isValid(ItemStackHandler input, ItemStackHandler output) {
		return !ModTileEntity.isEmpty(input) && (output.getStackInSlot(0).isEmpty() || (Util.areStacksEqual(output.getStackInSlot(0), getOutput()) && output.getStackInSlot(0).getCount() < output.getStackInSlot(0).getMaxStackSize()));
	}

	public final void giveOutput(ItemStackHandler input, ItemStackHandler output) {
		for (int i = 0; i < input.getSlots(); i++) input.extractItem(i, 1, false);
		output.insertItem(0, getOutput().copy(), false);
	}
}
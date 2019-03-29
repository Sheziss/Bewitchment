package com.bewitchment.api.registry;

import java.util.Random;

import com.bewitchment.Bewitchment;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class OvenRecipe extends IForgeRegistryEntry.Impl<OvenRecipe>
{
	private final ItemStack input, output, byproduct;
	private final float byproductChance;
	
	public OvenRecipe(String modid, String name, ItemStack input, ItemStack output, ItemStack byproduct, float byproductChance)
	{
		this.setRegistryName(new ResourceLocation(modid, name));
		this.input = input;
		this.output = output;
		this.byproduct = byproduct;
		this.byproductChance = byproductChance;
	}
	
	/**
	 * @return the ItemStack to be given as a byproduct output.
	 */
	public ItemStack getByproduct()
	{
		return byproduct;
	}
	
	/**
	 * @return the chance for the byproduct to appear.
	 */
	public float getByproductChance()
	{
		return byproductChance;
	}
	
	/**
	 * @return the ItemStack to be used as input.
	 */
	public ItemStack getInput()
	{
		return input;
	}
	
	/**
	 * @return the ItemStack to be given as output.
	 */
	public ItemStack getOutput()
	{
		return output;
	}
	
	public boolean matches(ItemStack input)
	{
		return Bewitchment.proxy.areStacksEqual(input, getInput());
	}
	
	public boolean canOutputFit(ItemStackHandler handler)
	{
		boolean outputValid = handler.getStackInSlot(0).isEmpty() || (Bewitchment.proxy.areStacksEqual(handler.getStackInSlot(0), getOutput()) && handler.getStackInSlot(0).getCount() < handler.getStackInSlot(0).getMaxStackSize());
		boolean byproductValid = handler.getStackInSlot(1).isEmpty() || (Bewitchment.proxy.areStacksEqual(handler.getStackInSlot(1), getByproduct()) && handler.getStackInSlot(1).getCount() < handler.getStackInSlot(1).getMaxStackSize() - (getByproduct().getCount() - 1));
		return outputValid && byproductValid;
	}
	
	public void giveOutput(Random rand, ItemStackHandler input, ItemStackHandler output)
	{
		input.extractItem(2, 1, false);
		output.insertItem(0, getOutput().copy(), false);
		if (rand.nextFloat() < getByproductChance() && !input.getStackInSlot(1).isEmpty())
		{
			input.extractItem(1, 1, false);
			output.insertItem(1, getByproduct().copy(), false);
		}
	}
}
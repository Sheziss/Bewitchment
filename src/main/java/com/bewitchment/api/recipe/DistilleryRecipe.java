package com.bewitchment.api.recipe;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class DistilleryRecipe extends IForgeRegistryEntry.Impl<DistilleryRecipe>
{
	private ItemStack[] input , output;
	private int running_power, time;
	
	public DistilleryRecipe(String name, ItemStack[] input, ItemStack[] output, int running_power, int time)
	{
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.input = input;
		this.output = output;
		this.running_power = running_power;
		this.time = time;
	}
	
	/**
	 * @return the list of ItemStacks to be used as an input.
	 */
	public ItemStack[] getInput()
	{
		return input;
	}
	
	/**
	 * @return the list of ItemStacks to be given as an output.
	 */
	public ItemStack[] getOutput()
	{
		return output;
	}
	
	/**
	 * @return how much power is required to keep the recipe going.
	 */
	public int getRunningPower()
	{
		return running_power;
	}
	
	/**
	 * @return how long the recipe should take to finish.
	 */
	public int getTime()
	{
		return time;
	}
	
	public boolean matches(List<ItemStack> inputStacks)
	{
		int nonEmpty = 0;
		for (ItemStack stack : inputStacks) if (stack.getCount() > 0) nonEmpty++;
		if (nonEmpty != getInput().length) return false;
		boolean[] found = new boolean[getInput().length];
		ArrayList<ItemStack> comp = new ArrayList<ItemStack>(inputStacks);
		for (int i = 0; i < getInput().length; i++)
		{
			for (int j = 0; j < comp.size(); j++)
			{
				ItemStack stack = comp.get(j);
				if (getInput()[i].getItem() == stack.getItem() && (getInput()[i].getMetadata() == stack.getMetadata() || getInput()[i].getMetadata() == 32767))
				{
					found[i] = true;
					comp.set(j, ItemStack.EMPTY);
					break;
				}
			}
		}
		for (boolean b : found) if (!b) return false;
		return true;
	}
}
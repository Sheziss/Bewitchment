package com.bewitchment.registry.crafting;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class DistilleryRecipe extends IForgeRegistryEntry.Impl<DistilleryRecipe>
{
	private List<ItemStack> inputs = null, outputs = null;
	private int time = -1;
	
	public boolean Matches(List<ItemStack> list)
	{
		int nonEmpty = 0;
		for (ItemStack stack : list) if (!stack.isEmpty()) nonEmpty++;
		if (nonEmpty != inputs.size()) return false;
		boolean[] found = new boolean[inputs.size()];
		List<ItemStack> comp = new ArrayList<ItemStack>(list);
		for (int i = 0; i < inputs.size(); i++)
		{
			ItemStack current = inputs.get(i);
			for (int j = 0; j < comp.size(); j++)
			{
				if (current.getItem() == comp.get(i).getItem())
				{
					found[i] = true;
					comp.set(j, ItemStack.EMPTY);
					break;
				}
			}
		}
		for (boolean bool : found) if (!bool) return false;
		return true;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public List<ItemStack> getInputs()
	{
		return inputs;
	}
	
	public List<ItemStack> getOutputs()
	{
		return outputs;
	}
	
	public static class Factory
	{
		private DistilleryRecipe recipe = new DistilleryRecipe();
		
		private Factory(String name)
		{
			recipe.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		}
		
		public static Factory start(String name)
		{
			return new Factory(name);
		}
		
		public Factory setNoOutput()
		{
			recipe.outputs = NonNullList.create();
			return this;
		}
		
		public Factory withInput(ItemStack... stacks)
		{
			if (stacks.length > 6) throw new IllegalArgumentException("Recipes cannot have more than 6 ingredients");
			recipe.inputs = NonNullList.from(ItemStack.EMPTY, stacks);
			return this;
		}
		
		public Factory withOutput(ItemStack... stacks)
		{
			if (stacks.length > 6) throw new IllegalArgumentException("Too many outputs, you can only use 6 at most");
			recipe.outputs = NonNullList.from(ItemStack.EMPTY, stacks);
			return this;
		}
		
		public Factory withBaseProcessingTime(int time)
		{
			if (time <= 0) throw new IllegalArgumentException("Time must be a positive integer");
			recipe.time = time;
			return this;
		}
		
		public DistilleryRecipe build()
		{
			if (recipe.time <= 0) throw new IllegalStateException("Time was not set properly");
			if (recipe.inputs == null) throw new IllegalStateException("Time was not set properly");
			if (recipe.outputs == null) throw new IllegalStateException("Time was not set properly");
			return recipe;
		}
	}
}
package com.bewitchment.api.registry;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
	
	public static boolean areStacksEqual(ItemStack stack0, ItemStack stack1)
	{
		return stack0.getItem() == stack1.getItem() && (stack0.getMetadata() == stack1.getMetadata() || stack1.getMetadata() == Short.MAX_VALUE);
	}
	
	public boolean matches(ItemStack input)
	{
		return areStacksEqual(input, getInput());
	}
	
	public boolean canOutputFit(ItemStack output, ItemStack byproduct)
	{
		boolean outputValid = output.isEmpty() || (areStacksEqual(output, getOutput()) && output.getCount() < output.getMaxStackSize());
		boolean byproductValid = byproduct.isEmpty() || (areStacksEqual(byproduct, getByproduct()) && byproduct.getCount() < byproduct.getMaxStackSize() - (getByproduct().getCount() - 1));
		return outputValid && byproductValid;
	}
	
	public void giveOutput(Random rand, ItemStack input, ItemStack jar, ItemStack output, ItemStack byproduct)
	{
		input.shrink(1);
		if (areStacksEqual(output, getOutput())) output.grow(getOutput().getCount());
		else output = getOutput();
		System.out.println(output);
		if (rand.nextFloat() < getByproductChance() && !jar.isEmpty())
		{
			jar.shrink(1);
			if (areStacksEqual(byproduct, getByproduct())) byproduct.grow(getByproduct().getCount());
			else byproduct = getByproduct();
		}
	}
}
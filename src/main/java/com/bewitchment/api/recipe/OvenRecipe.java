package com.bewitchment.api.recipe;

import com.bewitchment.Bewitchment;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class OvenRecipe extends IForgeRegistryEntry.Impl<OvenRecipe>
{
	private ItemStack input, output, byproduct;
	private float byproduct_chance;
	
	public OvenRecipe(String name, ItemStack input, ItemStack output, ItemStack byproduct, float byproduct_chance)
	{
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.input = input;
		this.output = output;
		this.byproduct = byproduct;
		this.byproduct_chance = byproduct_chance;
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
		return byproduct_chance;
	}
	
	public boolean matches(ItemStack inputStack)
	{
		return inputStack.getItem() == getInput().getItem() && (inputStack.getMetadata() == getInput().getMetadata() || getInput().getMetadata() == 32767);
	}
}
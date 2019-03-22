package com.bewitchment.api.registry;

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
	
	public boolean matches(ItemStack inputStack)
	{
		return inputStack.getItem() == getInput().getItem() && (inputStack.getMetadata() == getInput().getMetadata() || getInput().getMetadata() == 32767);
	}
}
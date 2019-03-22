package com.bewitchment.api.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LoomRecipe extends IForgeRegistryEntry.Impl<LoomRecipe>
{
	private final ItemStack[] input;
	private final ItemStack output;
	
	public LoomRecipe(String modid, String name, ItemStack[] input, ItemStack output)
	{
		this.setRegistryName(new ResourceLocation(modid, name));
		this.input = input;
		this.output = output;
	}
	
	/**
	 * @return the list of ItemStacks to be used as input.
	 */
	public ItemStack[] getInput()
	{
		return input;
	}
	
	/**
	 * @return the output ItemStack.
	 */
	public ItemStack getOutput()
	{
		return output;
	}
}
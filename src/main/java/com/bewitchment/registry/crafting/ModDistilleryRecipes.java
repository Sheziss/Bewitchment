package com.bewitchment.registry.crafting;

import com.bewitchment.core.Bewitchment;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class ModDistilleryRecipes
{
	public static final IForgeRegistry<DistilleryRecipe> REGISTRY = new RegistryBuilder<DistilleryRecipe>().disableSaving().setName(new ResourceLocation(Bewitchment.MOD_ID, "distillery")).setType(DistilleryRecipe.class).create();
	
	public static void init()
	{
	}
}
package com.bewitchment.api;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.recipe.DistilleryRecipe;
import com.bewitchment.api.recipe.LoomRecipe;
import com.bewitchment.api.recipe.OvenRecipe;
import com.bewitchment.api.recipe.Ritual;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * The Bewitchment API, use this for creating addons.
 */
public class BewitchmentAPI
{
	public static final IForgeRegistry<DistilleryRecipe> REGISTRY_DISTILLERY = new RegistryBuilder<DistilleryRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "distillery")).setType(DistilleryRecipe.class).create();
	public static final IForgeRegistry<LoomRecipe> REGISTRY_LOOM = new RegistryBuilder<LoomRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "loom")).setType(LoomRecipe.class).create();
	public static final IForgeRegistry<OvenRecipe> REGISTRY_OVEN = new RegistryBuilder<OvenRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "oven")).setType(OvenRecipe.class).create();
	public static final IForgeRegistry<Ritual> REGISTRY_RITUAL = new RegistryBuilder<Ritual>().setName(new ResourceLocation(Bewitchment.MOD_ID, "ritual")).setType(Ritual.class).create();
	
	/**
	 * The Demon creature attribute.
	 */
	public static EnumCreatureAttribute DEMON;
	
	/**
	 * The Spirit creature attribute.
	 */
	public static EnumCreatureAttribute SPIRIT;
	
	/**
	 * Registers a new DistilleryRecipe, for use in the Distillery.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final DistilleryRecipe registerDistilleryRecipe(DistilleryRecipe recipe)
	{
		REGISTRY_DISTILLERY.register(recipe);
		return recipe;
	}
	
	/**
	 * Registers a new OvenRecipe, for use in the Oven.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final OvenRecipe registerOvenRecipe(OvenRecipe recipe)
	{
		REGISTRY_OVEN.register(recipe);
		return recipe;
	}
	
	/**
	 * Registers a new Ritual, for use with rituals.
	 * 
	 * @param ritual the ritual to register
	 * @return the ritual registered
	 */
	public static final Ritual registerRitual(Ritual ritual)
	{
		REGISTRY_RITUAL.register(ritual);
		return ritual;
	}
	
	/**
	 * Registers a new LoomRecipe, for use with the Loom.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final LoomRecipe registerLoomRecipe(LoomRecipe recipe)
	{
		REGISTRY_LOOM.register(recipe);
		return recipe;
	}
}
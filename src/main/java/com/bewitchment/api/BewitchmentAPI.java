package com.bewitchment.api;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.recipe.DistilleryRecipe;
import com.bewitchment.api.recipe.LoomRecipe;
import com.bewitchment.api.recipe.OvenRecipe;
import com.bewitchment.api.recipe.Ritual;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;
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
	
	/**
	 * Registers a new scan value for the Witches' Altar.
	 * 
	 * @param block the block to be registered
	 * @param power the value associated with the block
	 */
	public static final void registerAltarScanValue(Block block, int power)
	{
		TileEntityWitchesAltar.SCAN_VALUES.put(block, power);
	}
	
	/**
	 * Gets the scan value associated with a block
	 * 
	 * @param block the block to be checked
	 * @return the scan value of the block
	 */
	public static final int getAltarScanValue(Block block)
	{
		return TileEntityWitchesAltar.SCAN_VALUES.getOrDefault(block, 0);
	}
	
	/**
	 * Registers a new sword multiplier value for the Witches' Altar.
	 * 
	 * @param item the item to be registered
	 * @param variety_multiplier the multiplier associated with the item
	 */
	public static final void registerAltarSwordMultiplier(Item item, double variety_multiplier)
	{
		TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.put(item, variety_multiplier);
	}
	
	/**
	 * Gets the sword multiplier value associated with a block
	 * 
	 * @param item the item to be checked
	 * @return the multiplier value of the item
	 */
	public static final double getAltarSwordMultiplierValue(Item item)
	{
		return TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.getOrDefault(item, 0d);
	}
	
	/**
	 * Registers a new sword radius value for the Witches' Altar.
	 * 
	 * @param item the item to be registered
	 * @param radius the radius associated with the item
	 */
	public static final void registerAltarSwordRadius(Item item, int radius)
	{
		TileEntityWitchesAltar.SWORD_RADIUS_VALUES.put(item, radius);
	}
	
	/**
	 * Gets the sword radius value associated with a block
	 * 
	 * @param item the item to be checked
	 * @return the radius value of the item
	 */
	public static final int getAltarSwordRadiusValue(Item item)
	{
		return TileEntityWitchesAltar.SWORD_RADIUS_VALUES.getOrDefault(item, 0);
	}
}
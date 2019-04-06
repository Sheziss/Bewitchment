package com.bewitchment.client.integration.jei;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.client.integration.jei.category.DistilleryCategory;
import com.bewitchment.client.integration.jei.category.DistilleryCategory.DistilleryWrapper;
import com.bewitchment.client.integration.jei.category.LoomCategory;
import com.bewitchment.client.integration.jei.category.LoomCategory.LoomWrapper;
import com.bewitchment.client.integration.jei.category.OvenCategory;
import com.bewitchment.client.integration.jei.category.OvenCategory.OvenWrapper;
import com.bewitchment.client.integration.jei.category.RitualCategory;
import com.bewitchment.client.integration.jei.category.RitualCategory.RitualWrapperFactory;
import com.bewitchment.registry.ModObjects;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class BewitchmentJEI implements IModPlugin
{
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		registry.addRecipeCategories(new DistilleryCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new OvenCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new LoomCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new RitualCategory(registry.getJeiHelpers().getGuiHelper()));
	}
	
	@Override
	public void register(IModRegistry registry)
	{
		registry.handleRecipes(DistilleryRecipe.class, DistilleryWrapper::new, DistilleryCategory.UID);
		registry.addRecipes(BewitchmentAPI.REGISTRY_DISTILLERY.getValuesCollection(), DistilleryCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(ModObjects.distillery), DistilleryCategory.UID);
		
		registry.handleRecipes(OvenRecipe.class, OvenWrapper::new, OvenCategory.UID);
		registry.addRecipes(BewitchmentAPI.REGISTRY_OVEN.getValuesCollection(), OvenCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(ModObjects.oven), OvenCategory.UID);
		
		registry.handleRecipes(LoomRecipe.class, LoomWrapper::new, LoomCategory.UID);
		registry.addRecipes(BewitchmentAPI.REGISTRY_LOOM.getValuesCollection(), LoomCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(ModObjects.loom), LoomCategory.UID);
		
		registry.handleRecipes(Ritual.class, new RitualWrapperFactory(registry.getJeiHelpers().getGuiHelper()), RitualCategory.UID);
		registry.addRecipes(BewitchmentAPI.REGISTRY_RITUAL.getValuesCollection(), RitualCategory.UID);
		registry.addRecipeCatalyst(new ItemStack(ModObjects.chalk_golden), RitualCategory.UID);
	}
}
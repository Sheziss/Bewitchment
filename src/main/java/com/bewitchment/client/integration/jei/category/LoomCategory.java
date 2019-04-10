package com.bewitchment.client.integration.jei.category;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.client.integration.jei.category.LoomCategory.LoomWrapper;
import com.bewitchment.registry.ModObjects;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoomCategory implements IRecipeCategory<LoomWrapper> {
	public static final String UID = ModObjects.loom.getTranslationKey() + ".name";

	private IDrawable bg;

	public LoomCategory(IGuiHelper helper) {
		bg = helper.drawableBuilder(new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/jei_loom.png"), 0, 0, 90, 36).setTextureSize(90, 36).build();
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return I18n.format(UID);
	}

	@Override
	public String getModName() {
		return Bewitchment.MOD_NAME;
	}

	@Override
	public IDrawable getBackground() {
		return bg;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, LoomWrapper recipeWrapper, IIngredients ingredients) {
		for (int i = 0; i < recipeWrapper.input.size(); i++) {
			recipeLayout.getItemStacks().init(i + 1, true, (i % 2) * 18, (i / 2) * 18);
			recipeLayout.getItemStacks().set(i + 1, Arrays.asList(recipeWrapper.input.get(i).getMatchingStacks()));
		}
		recipeLayout.getItemStacks().init(0, false, 72, 9);
		recipeLayout.getItemStacks().set(0, recipeWrapper.output);
	}

	public static class LoomWrapper implements IRecipeWrapper {
		private List<Ingredient> input;
		private ItemStack output;

		public LoomWrapper(LoomRecipe recipe) {
			input = recipe.getInput();
			output = recipe.getOutput();
		}

		@Override
		public void getIngredients(IIngredients ingredients) {
			List<List<ItemStack>> lists = new ArrayList<List<ItemStack>>();
			for (Ingredient ing : input) lists.add(Arrays.asList(ing.getMatchingStacks()));
			ingredients.setInputLists(VanillaTypes.ITEM, lists);
			ingredients.setOutput(VanillaTypes.ITEM, output);
		}
	}
}
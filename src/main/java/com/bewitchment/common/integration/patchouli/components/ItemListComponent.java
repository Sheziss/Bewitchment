package com.bewitchment.common.integration.patchouli.components;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.Ritual;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.VariableHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemListComponent implements ICustomComponent {
	@VariableHolder
	public int slots;

	@VariableHolder
	public String registry, name, list_type;

	private transient List<Ingredient> ingredients;

	private int x, y;

	private static List<Ingredient> getInputsFromRegistry(String registry, String name, String type) {
		if (registry.equals("bewitchment:rituals")) {
			Ritual ritual = BewitchmentAPI.REGISTRY_RITUAL.getValue(new ResourceLocation(name));
			if (type.equals("input")) return ritual.getInputItems();
			else if (type.equals("output")) {
				List<Ingredient> out = new ArrayList<>();
				for (ItemStack stack : ritual.getOutput(null)) out.add(Ingredient.fromStacks(stack));
				return out;
			}
		}
		return new ArrayList<>();
	}

	@Override
	public void build(int x, int y, int page) {
		ingredients = getInputsFromRegistry(registry, name, list_type);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(IComponentRenderContext context, float ticks, int mouseX, int mouseY) {
		if (ingredients.size() > 0) {
			int remaining = ingredients.size();
			int row = 0;
			int border = slots <= ingredients.size() ? 0 : ((slots - ingredients.size()) * 17) / 2;
			GlStateManager.pushMatrix();
			while (remaining > 0) {
				for (int i = 0; i < Math.min(slots, remaining); i++)
					context.renderIngredient(border + x + (17 * i), y + (17 * row), mouseX, mouseY, ingredients.get((row * slots) + i));
				row++;
				remaining -= slots;
			}
			GlStateManager.popMatrix();
		}
	}
}
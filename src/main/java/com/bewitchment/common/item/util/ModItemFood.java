package com.bewitchment.common.item.util;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModItemFood extends ItemFood implements IOreDictionaryContainer {
	private final List<String> oreDictionaryNames = new ArrayList<String>();

	public ModItemFood(String name, int amount, float saturation, boolean wolfFood, String... oreDictionaryNames) {
		super(amount, saturation, wolfFood);
		Bewitchment.proxy.registerValues(this, name, oreDictionaryNames);
	}

	@Override
	public List<String> getOreDictionaryNames() {
		return oreDictionaryNames;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(TextFormatting.GRAY + I18n.format(tip));
	}
}
package com.bewitchment.common.item.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.util.IOreDictionaryContainer;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItem extends Item implements IOreDictionaryContainer
{
	private final List<String> oreDictionaryNames = new ArrayList<String>();
	
	protected ModItem()
	{
	}
	
	public ModItem(String name, String... oreDictionaryNames)
	{
		Bewitchment.proxy.registerValues(this, name, oreDictionaryNames);
	}

	@Override
	public List<String> getOreDictionaryNames()
	{
		return oreDictionaryNames;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(getTooltipColor() + I18n.format(tip));
	}
	
	protected TextFormatting getTooltipColor()
	{
		return TextFormatting.GRAY;
	}
}
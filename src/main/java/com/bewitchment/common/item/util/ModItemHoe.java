package com.bewitchment.common.item.util;

import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItemHoe extends ItemHoe
{
	public ModItemHoe(String name, ToolMaterial mat)
	{
		super(mat);
		Bewitchment.proxy.registerValues(this, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(TextFormatting.GRAY + I18n.format(tip));
	}
}
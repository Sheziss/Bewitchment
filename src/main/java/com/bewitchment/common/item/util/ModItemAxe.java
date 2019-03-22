package com.bewitchment.common.item.util;

import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItemAxe extends ItemAxe
{
	public ModItemAxe(String name, ToolMaterial mat)
	{
		super(mat, mat.getAttackDamage(), -3.1f);
		Bewitchment.proxy.registerValues(this, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(TextFormatting.GRAY + I18n.format(tip));
	}
}
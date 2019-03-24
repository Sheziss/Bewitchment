package com.bewitchment.common.item.util;

import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItemPickaxe extends ItemPickaxe
{
	public ModItemPickaxe(String name, ToolMaterial mat)
	{
		super(mat);
		Bewitchment.proxy.registerValues(this, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(getTooltipColor() + I18n.format("tooltip." + "tool_description_" + toolMaterial.name()));
	}
	
	protected TextFormatting getTooltipColor()
	{
		return TextFormatting.GRAY;
	}
}
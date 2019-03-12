package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.common.item.FLItemArmor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColdIronArmor extends FLItemArmor
{
	public ItemColdIronArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(Bewitchment.MOD_ID, name, Bewitchment.proxy.tab, mat, slot);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "armor_description_" + getArmorMaterial().name()));
	}
}
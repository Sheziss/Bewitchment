package com.bewitchment.common.item.util;

import com.bewitchment.Bewitchment;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ModItemArmor extends ItemArmor {
	public ModItemArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot) {
		super(mat, 0, slot);
		Bewitchment.proxy.registerValues(this, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(getTooltipColor() + I18n.format("tooltip." + "armor_description_" + getArmorMaterial().name()));
	}

	protected TextFormatting getTooltipColor() {
		return TextFormatting.GRAY;
	}
}
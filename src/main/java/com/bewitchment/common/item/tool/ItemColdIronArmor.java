package com.bewitchment.common.item.tool;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.common.item.ModItemArmor;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemColdIronArmor extends ModItemArmor
{
	public ItemColdIronArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(Bewitchment.MOD_ID, name, Bewitchment.proxy.tab, mat, slot);
	}
}
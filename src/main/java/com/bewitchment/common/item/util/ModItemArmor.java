package com.bewitchment.common.item.util;

import com.bewitchment.Bewitchment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ModItemArmor extends ItemArmor
{
	public ModItemArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(mat, 0, slot);
		Bewitchment.proxy.registerValues(this, name);
	}
}
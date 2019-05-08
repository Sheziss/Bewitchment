package com.bewitchment.common.item.equipment;

import com.bewitchment.common.item.util.ModItemArmor;
import com.bewitchment.registry.ModObjects;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemWitchRobes extends ModItemArmor {
	public ItemWitchRobes(String name, EntityEquipmentSlot slot) {
		super(name, ModObjects.ARMOR_BEWITCHED_LEATHER, slot);
	}
}
package com.bewitchment.common.item.equipment;

import com.bewitchment.common.item.util.ModItemArmor;
import com.bewitchment.registry.ModObjects;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by Joseph on 5/7/2019.
 */
public class ItemWitchCowl extends ModItemArmor {
	public ItemWitchCowl(String name, EntityEquipmentSlot slot) {
		super(name, ModObjects.ARMOR_BEWITCHED_LEATHER, slot);
	}
}

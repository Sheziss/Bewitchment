package com.bewitchment.common.item.equipment;

import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.common.item.util.ModItemArmor;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemWitchRobes extends ModItemArmor {
	public ItemWitchRobes(String name, EntityEquipmentSlot slot) {
		super(name, ModObjects.ARMOR_BEWITCHED_LEATHER, slot);
	}
}
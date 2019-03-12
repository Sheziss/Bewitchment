package com.bewitchment.common.item.tool;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.transformation.TransformationCapability.Transformation;
import com.bewitchment.api.capability.transformation.TransformationProvider;

import moriyashiine.froglib.common.item.FLItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemSilverArmor extends FLItemArmor
{
	public ItemSilverArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(Bewitchment.MOD_ID, name, Bewitchment.proxy.tab, mat, slot);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (player.getCapability(TransformationProvider.TRANSFORMATION, null).getTransformation() == Transformation.WEREWOLF) player.attackEntityFrom(DamageSource.MAGIC, 1);
	}
}
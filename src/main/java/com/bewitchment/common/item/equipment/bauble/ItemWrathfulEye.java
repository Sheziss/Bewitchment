package com.bewitchment.common.item.equipment.bauble;

import com.bewitchment.common.item.util.ModItemBauble;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;

public class ItemWrathfulEye extends ModItemBauble
{
	public ItemWrathfulEye()
	{
		super("wrathful_eye");
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack stack)
	{
		return BaubleType.AMULET;
	}
	
	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living)
	{
		living.world.playSound(null, living.getPosition(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.PLAYERS, 0.75f, 1.9f);
	}
	
	@Override
	protected TextFormatting getTooltipColor()
	{
		return TextFormatting.RED;
	}
}
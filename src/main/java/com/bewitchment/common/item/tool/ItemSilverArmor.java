package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.api.capability.transformation.Transformation;
import com.bewitchment.api.capability.transformation.Transformation.TransformationType;
import com.bewitchment.common.item.util.ModItemArmor;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSilverArmor extends ModItemArmor
{
	public ItemSilverArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(name, mat, slot);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "armor_description_" + getArmorMaterial().name()));
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (player.getCapability(Transformation.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF) player.attackEntityFrom(DamageSource.MAGIC, 1);
	}
}
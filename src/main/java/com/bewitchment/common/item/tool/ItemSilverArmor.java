package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.transformation.TransformationCapability.Transformation;
import com.bewitchment.api.capability.transformation.TransformationProvider;

import moriyashiine.froglib.common.item.FLItemArmor;
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

public class ItemSilverArmor extends FLItemArmor
{
	public ItemSilverArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(Bewitchment.MOD_ID, name, Bewitchment.proxy.tab, mat, slot);
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
		if (player.getCapability(TransformationProvider.TRANSFORMATION, null).getTransformation() == Transformation.WEREWOLF) player.attackEntityFrom(DamageSource.MAGIC, 1);
	}
}
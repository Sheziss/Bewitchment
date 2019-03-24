package com.bewitchment.common.item.equipment.bauble;

import java.util.List;

import com.bewitchment.common.enchantment.util.ModEnchantment;
import com.bewitchment.common.item.util.ModItemBauble;

import baubles.api.BaubleType;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTalisman extends ModItemBauble
{
	private final BaubleType type;
	
	public ItemTalisman(String name, BaubleType type)
	{
		super(name);
		this.type = type;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack stack)
	{
		return type;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		if (!stack.isItemEnchanted()) tooltip.add(getTooltipColor() + I18n.format("tooltip.bewitchment.talisman"));
	}
	
	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living)
	{
		living.world.playSound(null, living.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.PLAYERS, 0.75f, 1.9f);
		for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) if (enchantment instanceof ModEnchantment) ((ModEnchantment) enchantment).onEquipped(living);
	}
	
	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase living)
	{
		for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) if (enchantment instanceof ModEnchantment) ((ModEnchantment) enchantment).onUnequipped(living);
	}
	
	@Override
	protected TextFormatting getTooltipColor()
	{
		return TextFormatting.AQUA;
	}
}
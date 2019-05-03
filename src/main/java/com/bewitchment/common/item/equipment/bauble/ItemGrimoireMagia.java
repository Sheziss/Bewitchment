package com.bewitchment.common.item.equipment.bauble;

import baubles.api.BaubleType;
import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.item.util.ModItemBauble;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemGrimoireMagia extends ModItemBauble {
	public ItemGrimoireMagia() {
		super("grimoire_magia");
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.TRINKET;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound tag) {
		return new MagicPower().setMaxAmount(Bewitchment.proxy.config.maxGrimoirePower);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		MagicPower cap = stack.getCapability(MagicPower.CAPABILITY, null);
		tooltip.add(getTooltipColor() + I18n.format("tooltip.bewitchment.grimoire_magia", cap.getAmount(), cap.getMaxAmount()));
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living) {
		living.world.playSound(null, living.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.PLAYERS, 0.75f, 1.9f);
	}

	@Override
	protected TextFormatting getTooltipColor() {
		return TextFormatting.DARK_PURPLE;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		MagicPower cap = stack.getCapability(MagicPower.CAPABILITY, null);
		return cap.getAmount() < cap.getMaxAmount();
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		MagicPower cap = stack.getCapability(MagicPower.CAPABILITY, null);
		return 1 - (double) cap.getAmount() / cap.getMaxAmount();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (isInCreativeTab(tab)) {
			list.add(new ItemStack(this));
			ItemStack full = new ItemStack(this);
			full.getCapability(MagicPower.CAPABILITY, null).setAmount(Bewitchment.proxy.config.maxGrimoirePower);
			list.add(full);
		}
	}
}
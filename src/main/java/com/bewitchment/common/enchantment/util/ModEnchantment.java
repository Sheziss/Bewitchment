package com.bewitchment.common.enchantment.util;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantment extends Enchantment {
	private final int maxLevel;

	public ModEnchantment(String name, Rarity rarity, int maxLevel, EnumEnchantmentType type, EntityEquipmentSlot... slots) {
		super(rarity, type, slots);
		setRegistryName(new ResourceLocation(Bewitchment.MODID, name));
		setName(getRegistryName().toString().replace(":", "."));
		this.maxLevel = maxLevel;
		ModEnchantments.REGISTRY.add(this);
	}

	@Override
	public List<ItemStack> getEntityEquipment(EntityLivingBase living) {
		List<ItemStack> result = new ArrayList<>();
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
			for (int i = 0; i < handler.getSlots(); i++)
				if (!handler.getStackInSlot(i).isEmpty()) result.add(handler.getStackInSlot(i));
		}
		return result;
	}

	@Override
	public int getMaxLevel() {
		return maxLevel;
	}

	public int getMaxLevelOnPlayer(EntityPlayer player) {
		int max = 0;
		for (ItemStack stack : getEntityEquipment(player))
			if (EnchantmentHelper.getEnchantmentLevel(this, stack) > max)
				max = EnchantmentHelper.getEnchantmentLevel(this, stack);
		return max;
	}

	public int getTotalLevelOnPlayer(EntityPlayer player) {
		int total = 0;
		for (ItemStack stack : getEntityEquipment(player)) total += EnchantmentHelper.getEnchantmentLevel(this, stack);
		return total;
	}
}
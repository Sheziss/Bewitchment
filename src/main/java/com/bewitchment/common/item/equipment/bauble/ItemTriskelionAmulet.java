package com.bewitchment.common.item.equipment.bauble;

import baubles.api.BaubleType;
import com.bewitchment.common.item.util.ModItemBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;

public class ItemTriskelionAmulet extends ModItemBauble {
	public ItemTriskelionAmulet() {
		super("triskelion_amulet");
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.AMULET;
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living) {
		living.world.playSound(null, living.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundCategory.PLAYERS, 0.75f, 1.9f);
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase living) {
		if (living.ticksExisted % 40 == 0) {
			living.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 120));
			living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 120));
		}
	}

	@Override
	protected TextFormatting getTooltipColor() {
		return TextFormatting.GOLD;
	}
}
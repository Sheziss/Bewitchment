package com.bewitchment.common.item.equipment.bauble;

import baubles.api.BaubleType;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.item.util.ModItemBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;

public class ItemTokenOfRemedies extends ModItemBauble {
	public ItemTokenOfRemedies() {
		super("token_of_remedies");
		setMaxDamage(8);
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.TRINKET;
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living) {
		living.world.playSound(null, living.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 0.75f, 1.9f);
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase living) {
		if (living.ticksExisted % 40 == 0 && living instanceof EntityPlayer) {
			if ((living.isPotionActive(MobEffects.BLINDNESS) || living.isPotionActive(MobEffects.NAUSEA) || living.isPotionActive(MobEffects.POISON) || living.isPotionActive(MobEffects.WEAKNESS) || living.isPotionActive(MobEffects.WITHER)) && living.getCapability(MagicPower.CAPABILITY, null).drain(50)) {
				living.removePotionEffect(MobEffects.BLINDNESS);
				living.removePotionEffect(MobEffects.NAUSEA);
				living.removePotionEffect(MobEffects.POISON);
				living.removePotionEffect(MobEffects.WEAKNESS);
				living.removePotionEffect(MobEffects.WITHER);
				stack.damageItem(1, living);
			}
		}
	}

	@Override
	protected TextFormatting getTooltipColor() {
		return TextFormatting.AQUA;
	}
}
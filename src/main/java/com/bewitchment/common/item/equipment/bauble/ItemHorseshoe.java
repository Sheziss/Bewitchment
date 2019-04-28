package com.bewitchment.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.item.util.ModItemBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemHorseshoe extends ModItemBauble {
	public ItemHorseshoe() {
		super("horseshoe");
		MinecraftForge.EVENT_BUS.register(this);
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
		if (living.ticksExisted % 40 == 0) living.addPotionEffect(new PotionEffect(MobEffects.LUCK, 120));
	}

	@Override
	protected TextFormatting getTooltipColor() {
		return TextFormatting.AQUA;
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.getSource().getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase) event.getSource().getTrueSource()).getCreatureAttribute() == BewitchmentAPI.SPIRIT && event.getEntityLiving() instanceof EntityPlayer && hasAmulet((EntityPlayer) event.getEntityLiving()))
			event.setAmount(event.getAmount() * 0.8f);
	}

	private boolean hasAmulet(EntityPlayer player) {
		for (int i = 0; i < BaublesApi.getBaublesHandler(player).getSlots(); i++)
			if (BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem() instanceof ItemHorseshoe) return true;
		return false;
	}
}
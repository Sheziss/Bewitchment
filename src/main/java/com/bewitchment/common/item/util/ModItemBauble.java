package com.bewitchment.common.item.util;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class ModItemBauble extends ModItem implements IBauble {
	public ModItemBauble(String name) {
		super(name);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
			for (int i = 0; i < handler.getSlots(); i++) {
				if (handler.getStackInSlot(i).isEmpty() && handler.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
					handler.setStackInSlot(i, player.getHeldItem(hand));
					if (!player.isCreative()) player.getHeldItem(hand).shrink(1);
					onEquipped(player.getHeldItem(hand), player);
					break;
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.BINDING_CURSE;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase living) {
		return !EnchantmentHelper.hasBindingCurse(stack);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
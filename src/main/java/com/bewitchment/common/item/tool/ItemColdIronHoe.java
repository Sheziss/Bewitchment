package com.bewitchment.common.item.tool;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.item.util.ModItemHoe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemColdIronHoe extends ModItemHoe {
	public ItemColdIronHoe(ToolMaterial mat) {
		super("hoe_cold_iron", mat);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if (target.getCreatureAttribute() == BewitchmentAPI.DEMON || target.getCreatureAttribute() == BewitchmentAPI.SPIRIT || target instanceof EntityBlaze || target instanceof EntityEnderman || target instanceof EntityVex) {
				target.attackEntityFrom(attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer) attacker) : DamageSource.causeMobDamage(attacker), 12);
				stack.damageItem(5, attacker);
			} else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}
}
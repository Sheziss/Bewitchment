package com.bewitchment.common.item.tool;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.common.item.ModItemSpade;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemSilverSpade extends ModItemSpade
{
	public ItemSilverSpade(ToolMaterial mat)
	{
		super(Bewitchment.MOD_ID, "spade_silver", Bewitchment.proxy.tab, mat);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (!target.world.isRemote)
		{
			if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
			{
				target.attackEntityFrom(attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)attacker) : DamageSource.causeMobDamage(attacker), 12);
				stack.damageItem(25, attacker);
			}
			else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}
}
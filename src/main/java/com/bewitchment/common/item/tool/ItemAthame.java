package com.bewitchment.common.item.tool;

import com.bewitchment.common.item.util.ModItemSword;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemAthame extends ModItemSword
{
	public ItemAthame(ToolMaterial mat)
	{
		super("athame", mat);
		setMaxDamage(600);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (!target.world.isRemote)
		{
			if (target instanceof EntityEnderman && attacker instanceof EntityPlayer)
			{
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 20);
				stack.damageItem(50, attacker);
			}
			else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}
}
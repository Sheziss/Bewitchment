package com.bewitchment.common.item.tool;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.common.item.FLItemSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemAthame extends FLItemSword
{
	public ItemAthame(ToolMaterial mat)
	{
		super(Bewitchment.MOD_ID, "athame", Bewitchment.proxy.tab, mat);
		this.setMaxDamage(600);
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
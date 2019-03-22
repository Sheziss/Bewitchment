package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.item.util.ModItemSword;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColdIronSword extends ModItemSword
{
	public ItemColdIronSword(ToolMaterial mat)
	{
		super("sword_cold_iron", mat);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (!target.world.isRemote)
		{
			if (target.getCreatureAttribute() == BewitchmentAPI.DEMON || target.getCreatureAttribute() == BewitchmentAPI.SPIRIT || target instanceof EntityBlaze || target instanceof EntityEnderman || target instanceof EntityVex)
			{
				target.attackEntityFrom(attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer) attacker) : DamageSource.causeMobDamage(attacker), 12);
				stack.damageItem(5, attacker);
			}
			else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "tool_description_" + getToolMaterialName()));
	}
}
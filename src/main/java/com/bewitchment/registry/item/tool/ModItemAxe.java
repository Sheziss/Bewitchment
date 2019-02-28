package com.bewitchment.registry.item.tool;

import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.ModItems;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItemAxe extends ItemAxe
{
	public ModItemAxe(String name, ToolMaterial mat)
	{
		super(mat, mat.getAttackDamage(), -3.1f);
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(Bewitchment.proxy.tab);
		ModItems.REGISTRY.add(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "tool_description_" + toolMaterial.name()));
	}
	
	public static class ColdIron extends ModItemAxe
	{
		public ColdIron(String name, ToolMaterial mat)
		{
			super(name, mat);
		}
		
		@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
		{
			if (!target.world.isRemote)
			{
				if (target.getCreatureAttribute() == Bewitchment.proxy.DEMON || target.getCreatureAttribute() == Bewitchment.proxy.SPIRIT || target instanceof EntityBlaze || target instanceof EntityEnderman || target instanceof EntityVex)
				{
					target.attackEntityFrom(attacker instanceof EntityPlayer ? DamageSource.causePlayerDamage((EntityPlayer)attacker) : DamageSource.causeMobDamage(attacker), 12);
					stack.damageItem(5, attacker);
				}
				else return super.hitEntity(stack, target, attacker);
			}
			return true;
		}
	}
	
	public static class Silver extends ModItemAxe
	{
		public Silver(String name, ToolMaterial mat)
		{
			super(name, mat);
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
}
package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.FrogLib;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBoline extends ItemShears
{
	public ItemBoline()
	{
		super();
		FrogLib.proxy.registerItem(this, Bewitchment.MOD_ID, "boline", Bewitchment.proxy.tab);
		setMaxDamage(600);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("biome_id")) tooltip.add(Biome.getBiome(stack.getTagCompound().getInteger("biome_id")).getBiomeName());
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("biome_id", Biome.getIdForBiome(world.getBiome(player.getPosition())));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (!target.world.isRemote)
		{
			if (attacker instanceof EntityPlayer)
			{
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 5);
				stack.damageItem(1, attacker);
			}
			else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}
}
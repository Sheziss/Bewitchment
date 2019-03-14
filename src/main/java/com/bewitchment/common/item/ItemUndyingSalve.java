package com.bewitchment.common.item;

import com.bewitchment.common.item.util.ModItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUndyingSalve extends ModItem
{
	public ItemUndyingSalve()
	{
		super("undying_salve");
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BOW;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 60;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		player.setActiveHand(hand);
//		player.getCapability(MagicPowerProvider.CAPABILITY, null)
		return EnumActionResult.SUCCESS;
	}
}
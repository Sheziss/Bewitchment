package com.bewitchment.common.item;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModBlocks;

import moriyashiine.froglib.common.item.FLItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSalt extends FLItem
{
	public ItemSalt()
	{
		super(Bewitchment.MOD_ID, "salt", Bewitchment.proxy.tab, "salt", "dustSalt", "materialSalt", "ingredientSalt", "listAllsalt", "foodSalt", "lumpSalt", "pinchSalt", "portionSalt");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		BlockPos pos0 = world.getBlockState(pos).getBlock().isReplaceable(world, pos) ? pos : pos.offset(face);
		ItemStack stack = player.getHeldItem(hand);
		if (player.canPlayerEdit(pos0, face, stack) && world.mayPlace(world.getBlockState(pos0).getBlock(), pos0, false, face, player) && ModBlocks.salt_barrier.canPlaceBlockAt(world, pos0))
		{
			stack.shrink(1);
			world.setBlockState(pos0, ModBlocks.salt_barrier.getDefaultState());
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
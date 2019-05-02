package com.bewitchment.common.item;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.registry.ModObjects;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLantern extends ItemBlock {
	public ItemLantern(Block block) {
		super(block);
	}

	//TODO: change bedrock / make another recipe
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		if (!player.getHeldItem(hand).hasTagCompound() && world.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
			player.getHeldItem(hand).setTagCompound(new NBTTagCompound());
			player.getHeldItem(hand).getTagCompound().setBoolean("lit", true);
			world.setBlockToAir(pos);
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(player, world, pos, hand, face, hitX, hitY, hitZ);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking()) return super.onItemUseFirst(player, world, pos, face, hitX, hitY, hitZ, hand);
		if (player.getHeldItem(hand).hasTagCompound() && player.getHeldItem(hand).getTagCompound().getBoolean("lit") && world.getBlockState(pos.offset(face)).getBlock().isReplaceable(world, pos.offset(face)) && MagicPower.attemptDrain(null, player, 50)) {
			world.setBlockState(pos.offset(face), ModObjects.witches_light.getDefaultState());
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return (stack.hasTagCompound() && stack.getTagCompound().getBoolean("lit")) || super.hasEffect(stack);
	}
}
package com.bewitchment.common.item;

import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModSounds;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChalk extends ModItem {
	public ItemChalk(String name) {
		super(name);
		setMaxStackSize(1);
		setMaxDamage(40);
		setNoRepair();
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		boolean isReplacing = world.getBlockState(pos).getBlock() == ModObjects.glyph && world.getBlockState(pos).getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN;
		if (!world.isRemote && (face == EnumFacing.UP && ModObjects.glyph.canPlaceBlockAt(world, pos.up()) || isReplacing)) {
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();
			if (!player.isCreative()) stack.damageItem(1, player);
			world.setBlockState(isReplacing ? pos : pos.up(), ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, GlyphType.values()[item == ModObjects.chalk_normal ? GlyphType.NORMAL.ordinal() : item == ModObjects.chalk_golden ? GlyphType.GOLDEN.ordinal() : item == ModObjects.chalk_nether ? GlyphType.NETHER.ordinal() : item == ModObjects.chalk_ender ? GlyphType.ENDER.ordinal() : -1]).withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[player.getRNG().nextInt(4)]));
			world.playSound(null, pos, ModSounds.CHALK_SCRIBBLE, SoundCategory.BLOCKS, 0.5f, 1 + 0.5f * player.getRNG().nextFloat());
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean canItemEditBlocks() {
		return true;
	}
}
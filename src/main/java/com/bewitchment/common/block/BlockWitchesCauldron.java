package com.bewitchment.common.block;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesCauldron;
import com.bewitchment.common.block.util.ModBlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.List;

@SuppressWarnings("deprecation")
public class BlockWitchesCauldron extends ModBlockContainer {
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.0625, 0, 0.0625, 15 * 0.0625, 11 * 0.0625, 15 * 0.0625);
	private static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0, 0, 0, 1, 0.3125, 1);
	private static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0, 0, 0, 1, 11 * 0.0625, 0.125);
	private static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0, 0, 0.875, 1, 11 * 0.0625, 1);
	private static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875, 0, 0, 1, 11 * 0.0625, 1);
	private static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0, 0, 0, 0.125, 11 * 0.0625, 1);

	public BlockWitchesCauldron() {
		super(null, "witches_cauldron", Material.IRON, SoundType.METAL, 5, 5, "pickaxe", 0, -1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityWitchesCauldron();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOX;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		TileEntityWitchesCauldron tile = ((TileEntityWitchesCauldron) world.getTileEntity(pos));
		if (player.getHeldItem(hand).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			if (FluidUtil.interactWithFluidHandler(player, hand, world, pos, face)) tile.markDirty();
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB box, List<AxisAlignedBB> boxes, Entity entity, boolean wut) {
		addCollisionBoxToList(pos, box, boxes, AABB_LEGS);
		addCollisionBoxToList(pos, box, boxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, box, boxes, AABB_WALL_NORTH);
		addCollisionBoxToList(pos, box, boxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, box, boxes, AABB_WALL_SOUTH);
	}
}
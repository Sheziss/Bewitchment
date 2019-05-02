package com.bewitchment.common.block;

import com.bewitchment.common.block.tile.entity.TileEntityCrystalBall;
import com.bewitchment.common.block.util.ModBlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlockCrystalBall extends ModBlockContainer {
	private static final AxisAlignedBB BOX = new AxisAlignedBB(3d / 16, 0, 3d / 16, 13d / 16, 12d / 16, 13d / 16);

	public BlockCrystalBall() {
		super(null, "crystal_ball", Material.GLASS, SoundType.GLASS, 1, 1, "pickaxe", 0, -1);
		setLightOpacity(0);
		setLightLevel(0.3f);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCrystalBall();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOX;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		return !player.isSneaking() ? ((TileEntityCrystalBall) world.getTileEntity(pos)).activate(player) : super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}
}
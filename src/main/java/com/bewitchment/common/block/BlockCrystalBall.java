package com.bewitchment.common.block;

import java.util.List;
import java.util.stream.Collectors;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.Fortune;
import com.bewitchment.common.block.util.ModBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrystalBall extends ModBlock
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(3d / 16, 0, 3d / 16, 13d / 16, 12d / 16, 13d / 16);
	
	public BlockCrystalBall()
	{
		super("crystal_ball", Material.GLASS, SoundType.GLASS, 1, 1, "pickaxe", 0);
		setLightOpacity(0);
		setLightLevel(0.3f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BOX;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
		{
			if (!world.isRemote)
			{
				Fortune fortune = player.getCapability(ExtendedPlayer.CAPABILITY, null).getFortune();
				if (fortune != null) player.sendStatusMessage(new TextComponentTranslation("fortune.fortune_exists" + new TextComponentTranslation(fortune.getRegistryName().toString())), true);
				else if (MagicPower.drainAltarFirst(world, player, pos, 3000))
				{
					List<Fortune> valid = BewitchmentAPI.REGISTRY_FORTUNE.getValuesCollection().stream().filter(f -> f.canBeUsed(player)).collect(Collectors.toList());
					if (!valid.isEmpty())
					{
						Fortune set = null;
						while (set == null)
						{
							for (Fortune f : valid)
							{
								if (world.rand.nextInt(100) <= f.getChance())
								{
									set = f;
									break;
								}
							}
						}
						player.sendStatusMessage(new TextComponentTranslation(set.getRegistryName().toString()), true);
						player.getCapability(ExtendedPlayer.CAPABILITY, null).setFortune(set);
					}
					else player.sendStatusMessage(new TextComponentTranslation("fortune.no_fortune"), true);
				}
				else player.sendStatusMessage(new TextComponentTranslation("magic.no_power"), true);
			}
			return true;
		}
		return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}
}
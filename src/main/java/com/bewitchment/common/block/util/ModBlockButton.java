package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockButton extends BlockButton implements IOreDictionaryContainer
{
	private final List<String> oreDictionaryNames = new ArrayList<String>();
	
	public ModBlockButton(String name, Block base, String... oreDictionaryNames)
	{
		super(base.getDefaultState().getMaterial() == Material.WOOD);
		Bewitchment.proxy.registerValues(this, name, base, oreDictionaryNames);
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return oreDictionaryNames;
	}
	
	@Override
	protected void playClickSound(EntityPlayer player, World world, BlockPos pos)
	{
		world.playSound(player, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3f, 0.6f);
	}
	
	@Override
	protected void playReleaseSound(World worldIn, BlockPos pos)
	{
		worldIn.playSound(null, pos, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3f, 0.5f);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
}
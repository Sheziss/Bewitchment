package com.bewitchment.common.block;

import com.bewitchment.common.block.util.ModBlockBush;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

import java.util.Random;

@SuppressWarnings("deprecation")
@Optional.Interface(iface = "thaumcraft.api.crafting.IInfusionStabiliserExt", modid = "thaumcraft")
public class BlockMoonbell extends ModBlockBush implements IInfusionStabiliserExt {
	public static final PropertyBool PLACED = PropertyBool.create("placed");

	public BlockMoonbell() {
		super("moonbell");
		setLightOpacity(0).setLightLevel(0.5f);
		setDefaultState(blockState.getBaseState().withProperty(PLACED, false));
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return player == null ? world.getBlockState(pos).getValue(PLACED) : super.canHarvestBlock(world, pos, player) && !player.world.isDaytime() && player.world.getMoonPhase() == 4 || world.getBlockState(pos).getValue(PLACED);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (rand.nextDouble() < 0.2)
			world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + 0.5 + rand.nextGaussian() * 0.2, 0.1 + pos.getY() + rand.nextGaussian() * 0.2, pos.getZ() + 0.5 + rand.nextGaussian() * 0.2, 0, 0.1, 0);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && !state.getValue(PLACED) && world.isDaytime()) {
			world.setBlockToAir(pos);
			for (int i = 0; i < 7; i++)
				world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + rand.nextDouble(), pos.getY() + rand.nextDouble(), pos.getZ() + rand.nextDouble(), rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1, rand.nextGaussian() * 0.1);
		}
	}

	@Override
	@Optional.Method(modid = "thaumcraft")
	public boolean canStabaliseInfusion(World world, BlockPos pos) {
		return true;
	}

	@Override
	@Optional.Method(modid = "thaumcraft")
	public float getStabilizationAmount(World world, BlockPos pos) {
		return 0.1f;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase living, EnumHand hand) {
		return getDefaultState().withProperty(PLACED, true);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(PLACED, meta == 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PLACED) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PLACED);
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		if (!world.isRemote) {
			if (world.getTotalWorldTime() % 200 == 0) {
				Random rand = player.getRNG();
				if (world.provider instanceof WorldProviderSurface) {
					if (world.provider.getMoonPhase(world.getWorldTime()) == 4 && !world.isDaytime() && rand.nextDouble() < 0.2) {
						BlockPos pos = player.getPosition().add(rand.nextInt(7) - 3, rand.nextInt(7) - 3, rand.nextInt(7) - 3);
						IBlockState state = world.getBlockState(pos);
						if (state.getMaterial().isReplaceable()) {
							if (canBlockStay(world, pos, state)) {
								world.setBlockState(pos, getDefaultState());
								return;
							}
						}
					}
				}
			}
		}
	}
}
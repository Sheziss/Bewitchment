package com.bewitchment.common.block;

import com.bewitchment.common.block.util.ModBlockBush;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

import java.util.Random;

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
		if (!state.getValue(PLACED) && world.isDaytime()) {
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
		if (event.side == Side.SERVER && event.phase == Phase.START) {
			World world = event.player.world;
			if (world.getTotalWorldTime() % 20 == 0 && BiomeDictionary.hasType(world.getBiome(event.player.getPosition()), Type.FOREST)) {
				Random rand = event.player.getRNG();
				if (world.provider.getDimension() == 0 && world.provider.getMoonPhase(world.getWorldTime()) == 4 && !world.isDaytime() && rand.nextDouble() < 0.2) {
					MutableBlockPos pos = new MutableBlockPos(event.player.getPosition().add((rand.nextInt(7) - 3) * 10, 0, (rand.nextInt(7) - 3) * 10));
					int y = pos.getY();
					for (int i = -5; i <= 5; i++) {
						pos.setY(y + i);
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && world.getBlockState(pos.down()).getBlock() instanceof BlockGrass)
							world.setBlockState(pos, ModObjects.moonbell.getDefaultState());
					}
				}
			}
		}
	}
}
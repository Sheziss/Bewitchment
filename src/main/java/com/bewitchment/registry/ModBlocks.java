package com.bewitchment.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.block.ModBlock;
import com.bewitchment.registry.block.ModBlockCrop;
import com.bewitchment.registry.block.ModBlockDoor;
import com.bewitchment.registry.block.ModBlockExp;
import com.bewitchment.registry.block.ModBlockFence;
import com.bewitchment.registry.block.ModBlockFenceGate;
import com.bewitchment.registry.block.ModBlockLeaves;
import com.bewitchment.registry.block.ModBlockPillar;
import com.bewitchment.registry.block.ModBlockSapling;
import com.bewitchment.registry.block.ModBlockSlab;
import com.bewitchment.registry.block.ModBlockStairs;
import com.bewitchment.registry.block.ModBlockTrapDoor;
import com.bewitchment.registry.block.tile.BlockDistillery;
import com.bewitchment.registry.block.tile.BlockOven;
import com.bewitchment.registry.block.util.BlockMoonbell;
import com.bewitchment.registry.block.util.BlockSaltBarrier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModBlocks
{
	public static final List<Block> REGISTRY = new ArrayList<Block>();
	private static final List<Fluid> FLUID_REGISTRY = new ArrayList<Fluid>();
	
	//Fluids
	public static final Fluid honey = registerFluid("honey", Material.WATER, 0, 10, 1500, 8000, true, false);
	public static final Fluid oil_mundane = registerFluid("oil_mundane", Material.WATER, 0, 0, 800, 4000, true, true);
	
	//No Item
	public static final Block salt_barrier = new BlockSaltBarrier("salt_barrier");
	public static final ModBlockCrop crop_aconitum = new ModBlockCrop("crop_aconitum", 3);
	public static final ModBlockCrop crop_asphodel = new ModBlockCrop("crop_asphodel", 4);
	public static final ModBlockCrop crop_belladonna = new ModBlockCrop.Belladonna("crop_belladonna", 4);
	public static final ModBlockCrop crop_chrysanthemum = new ModBlockCrop("crop_chrysanthemum", 3);
	public static final ModBlockCrop crop_garlic = new ModBlockCrop("crop_garlic", 3);
	public static final ModBlockCrop crop_ginger = new ModBlockCrop("crop_ginger", 3);
	public static final ModBlockCrop crop_hellebore = new ModBlockCrop("crop_hellebore", 3);
	public static final ModBlockCrop crop_infested_wheat = new ModBlockCrop("crop_infested_wheat", 6);
	public static final ModBlockCrop crop_kelp = new ModBlockCrop.Kelp("crop_kelp", 7);
	public static final ModBlockCrop crop_kenaf = new ModBlockCrop.Kenaf("crop_kenaf", 4);
	public static final ModBlockCrop crop_lavender = new ModBlockCrop("crop_lavender", 3);
	public static final ModBlockCrop crop_mandrake = new ModBlockCrop("crop_mandrake", 3);
	public static final ModBlockCrop crop_mint = new ModBlockCrop.Mint("crop_mint", 3);
	public static final ModBlockCrop crop_sagebrush = new ModBlockCrop("crop_sagebrush", 3);
	public static final ModBlockCrop crop_silphium = new ModBlockCrop.Silphium("crop_silphium", 5);
	public static final ModBlockCrop crop_thistle = new ModBlockCrop.Thistle("crop_thistle", 3);
	public static final ModBlockCrop crop_tulsi = new ModBlockCrop("crop_tulsi", 3);
	public static final ModBlockCrop crop_white_sage = new ModBlockCrop("crop_white_sage", 3);
	public static final ModBlockCrop crop_wormwood = new ModBlockCrop("crop_wormwood", 6);
	
	//Devices
	public static final Block distillery = registerTileEntity("distillery", BlockDistillery.class, BlockDistillery.Tile.class);
	public static final Block oven = registerTileEntity("oven", BlockOven.class, BlockOven.Tile.class);
	
	//Material Blocks
	public static final Block block_cold_iron = new ModBlock("block_cold_iron", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 1, "blockColdIron");
	public static final Block block_silver = new ModBlock("block_silver", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 1, "blockSilver");
	public static final Block block_alexandrite = new ModBlock("block_alexandrite", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockAlexandrite");
	public static final Block block_amethyst = new ModBlock("block_amethyst", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockAmethyst");
	public static final Block block_bloodstone = new ModBlock("block_bloodstone", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockBloodstone");
	public static final Block block_garnet = new ModBlock("block_garnet", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockGarnet");
	public static final Block block_jasper = new ModBlock("block_jasper", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockJasper");
	public static final Block block_malachite = new ModBlock("block_malachite", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockMalachite");
	public static final Block block_nuummite = new ModBlock("block_nuummite", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockNuummite");
	public static final Block block_tigers_eye = new ModBlock("block_tigers_eye", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockTigersEye");
	public static final Block block_tourmaline = new ModBlock("block_tourmaline", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 1, "blockTourmaline");
	
	public static final Block perpetual_ice = new ModBlock("perpetual_ice", Material.ICE, SoundType.GLASS, 0.5f, 2.5f, "", 0);
	public static final Block nethersteel = new ModBlock("nethersteel", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 1, "blockNethersteel");
	
	//Ores
	public static final Block ore_silver = new ModBlock("ore_silver", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreSilver");
	public static final Block ore_salt = new ModBlockExp("ore_salt", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreSalt");
	public static final Block ore_alexandrite = new ModBlockExp("ore_alexandrite", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreAlexandrite");
	public static final Block ore_amethyst = new ModBlockExp("ore_amethyst", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreAmethyst");
	public static final Block ore_bloodstone = new ModBlockExp("ore_bloodstone", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreBloodstone");
	public static final Block ore_garnet = new ModBlockExp("ore_garnet", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreGarnet");
	public static final Block ore_jasper = new ModBlockExp("ore_jasper", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreJasper");
	public static final Block ore_malachite = new ModBlockExp("ore_malachite", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreMalachite");
	public static final Block ore_nuummite = new ModBlockExp("ore_nuummite", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreNuummite");
	public static final Block ore_tigers_eye = new ModBlockExp("ore_tigers_eye", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreTigersEye");
	public static final Block ore_tourmaline = new ModBlockExp("ore_tourmaline", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreTourmaline");
	
	public static final Block coquina = new ModBlock("coquina", Material.ROCK, SoundType.STONE, 5, 30, "pickaxe", 0, "coquina");
	
	//Trees
	public static final Block log_cypress = new ModBlockPillar("log_cypress", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_elder = new ModBlockPillar("log_elder", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_juniper = new ModBlockPillar("log_juniper", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_yew = new ModBlockPillar("log_yew", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final ModBlockLeaves leaves_cypress = new ModBlockLeaves("leaves_cypress", "treeLeaves");
	public static final ModBlockLeaves leaves_elder = new ModBlockLeaves("leaves_elder", "treeLeaves");
	public static final ModBlockLeaves leaves_juniper = new ModBlockLeaves("leaves_juniper", "treeLeaves");
	public static final ModBlockLeaves leaves_yew = new ModBlockLeaves("leaves_yew", "treeLeaves");
	public static final Block planks_cypress = new ModBlock("planks_cypress", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_elder = new ModBlock("planks_elder", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_juniper = new ModBlock("planks_juniper", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_yew = new ModBlock("planks_yew", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block sapling_cypress = new ModBlockSapling("sapling_cypress", "treeSapling");
	public static final Block sapling_elder = new ModBlockSapling("sapling_elder", "treeSapling");
	public static final Block sapling_juniper = new ModBlockSapling("sapling_juniper", "treeSapling");
	public static final Block sapling_yew = new ModBlockSapling("sapling_yew", "treeSapling");
	
	//Ingredients
	public static final Block moonbell = new BlockMoonbell("moonbell");
	
	//Decoration
	public static final ModBlockDoor door_block_cypress = new ModBlockDoor("door_block_cypress", planks_cypress);
	public static final ModBlockDoor door_block_elder = new ModBlockDoor("door_block_elder", planks_elder);
	public static final ModBlockDoor door_block_juniper = new ModBlockDoor("door_block_juniper", planks_juniper);
	public static final ModBlockDoor door_block_yew = new ModBlockDoor("door_block_yew", planks_yew);
	
	public static final Block stairs_cypress = new ModBlockStairs("stairs_cypress", planks_cypress, "axe", 0, "stairWood");
	public static final Block stairs_elder = new ModBlockStairs("stairs_elder", planks_elder, "axe", 0, "stairWood");
	public static final Block stairs_juniper = new ModBlockStairs("stairs_juniper", planks_juniper, "axe", 0, "stairWood");
	public static final Block stairs_yew = new ModBlockStairs("stairs_yew", planks_yew, "axe", 0, "stairWood");
	public static final Block stairs_perpetual_ice = new ModBlockStairs("stairs_perpetual_ice", perpetual_ice, "", 0);
	
	public static final ModBlockSlab slab_cypress = new ModBlockSlab("slab_cypress", planks_cypress, false, "slabWood");
	public static final ModBlockSlab slab_cypress_double = new ModBlockSlab("slab_cypress_double", planks_cypress, true);
	public static final ModBlockSlab slab_elder = new ModBlockSlab("slab_elder", planks_elder, false, "slabWood");
	public static final ModBlockSlab slab_elder_double = new ModBlockSlab("slab_elder_double", planks_elder, true);
	public static final ModBlockSlab slab_juniper = new ModBlockSlab("slab_juniper", planks_juniper, false, "slabWood");
	public static final ModBlockSlab slab_juniper_double = new ModBlockSlab("slab_juniper_double", planks_juniper, true);
	public static final ModBlockSlab slab_yew = new ModBlockSlab("slab_yew", planks_yew, false, "slabWood");
	public static final ModBlockSlab slab_yew_double = new ModBlockSlab("slab_yew_double", planks_yew, true);
	public static final ModBlockSlab slab_perpetual_ice = new ModBlockSlab("slab_perpetual_ice", perpetual_ice, false);
	public static final ModBlockSlab slab_perpetual_ice_double = new ModBlockSlab("slab_perpetual_ice_double", perpetual_ice, true);
	
	public static final Block trapdoor_cypress = new ModBlockTrapDoor("trapdoor_cypress", planks_cypress);
	public static final Block trapdoor_elder = new ModBlockTrapDoor("trapdoor_elder", planks_elder);
	public static final Block trapdoor_juniper = new ModBlockTrapDoor("trapdoor_juniper", planks_juniper);
	public static final Block trapdoor_yew = new ModBlockTrapDoor("trapdoor_yew", planks_yew);
	
	public static final Block fence_gate_cypress = new ModBlockFenceGate("fence_gate_cypress", planks_cypress);
	public static final Block fence_gate_elder = new ModBlockFenceGate("fence_gate_elder", planks_elder);
	public static final Block fence_gate_juniper = new ModBlockFenceGate("fence_gate_juniper", planks_juniper);
	public static final Block fence_gate_yew = new ModBlockFenceGate("fence_gate_yew", planks_yew);
	
	public static final Block fence_cypress = new ModBlockFence("fence_cypress", planks_cypress);
	public static final Block fence_elder = new ModBlockFence("fence_elder", planks_elder);
	public static final Block fence_juniper = new ModBlockFence("fence_juniper", planks_juniper);
	public static final Block fence_yew = new ModBlockFence("fence_yew", planks_yew);
	public static final Block fence_perpetual_ice = new ModBlockFence("fence_perpetual_ice", perpetual_ice);
	
	@SubscribeEvent
	public static void register(Register<Block> event)
	{
		for (Block block : REGISTRY) event.getRegistry().register(block);
		Bewitchment.proxy.ignoreProperty(crop_kelp, BlockLiquid.LEVEL);
		Bewitchment.proxy.ignoreProperty(door_block_cypress, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_block_elder, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_block_juniper, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_block_yew, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_cypress, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_elder, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_juniper, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_yew, BlockFenceGate.POWERED);
		
		door_block_cypress.drop = ModItems.door_cypress;
		door_block_elder.drop = ModItems.door_elder;
		door_block_juniper.drop = ModItems.door_juniper;
		door_block_yew.drop = ModItems.door_yew;
	}
	
	public static List<Fluid> registerFluids()
	{
		return FLUID_REGISTRY;
	}
	
	private static Block registerTileEntity(String name, Class<? extends Block> block, Class<? extends TileEntity> tile)
	{
		Block block0 = null;
		try {block0 = block.getDeclaredConstructor(String.class).newInstance(name);}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {e.printStackTrace();}
		GameRegistry.registerTileEntity(tile, new ResourceLocation(Bewitchment.MOD_ID, name));
		return block0;
	}
	
	private static Fluid registerFluid(String name, Material mat, int temperature, int luminosity, int density, int viscosity, boolean useBucket, boolean useFlowTexture)
	{
		if (!FluidRegistry.isFluidRegistered(name))
		{
			Fluid fluid = new Fluid(name, new ResourceLocation(Bewitchment.MOD_ID, "blocks/fluid/" + name + "_still"), new ResourceLocation(Bewitchment.MOD_ID, "blocks/fluid/" + name + (useFlowTexture ? "_flowing" : "_still"))).setTemperature(temperature).setLuminosity(luminosity).setDensity(density).setViscosity(viscosity);
			FluidRegistry.registerFluid(fluid);
			Block block = new BlockFluidClassic(fluid, mat).setTemperature(temperature).setDensity(density).setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, "fluid_" + name)).setTranslationKey(fluid.getUnlocalizedName());
			fluid.setBlock(block);
			if (useBucket) FluidRegistry.addBucketForFluid(fluid);
			Bewitchment.proxy.registerTexture(fluid);
			ForgeRegistries.BLOCKS.register(block);
			FLUID_REGISTRY.add(fluid);
		}
		return FluidRegistry.getFluid(name);
	}
}
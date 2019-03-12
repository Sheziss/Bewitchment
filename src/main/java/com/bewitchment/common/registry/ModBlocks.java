package com.bewitchment.common.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockDistillery;
import com.bewitchment.common.block.BlockMoonbell;
import com.bewitchment.common.block.BlockOven;
import com.bewitchment.common.block.BlockPlacedItem;
import com.bewitchment.common.block.BlockSaltBarrier;
import com.bewitchment.common.block.BlockWitchesAltar;
import com.bewitchment.common.block.crop.BlockCropBelladonna;
import com.bewitchment.common.block.crop.BlockCropKelp;
import com.bewitchment.common.block.crop.BlockCropKenaf;
import com.bewitchment.common.block.crop.BlockCropMint;
import com.bewitchment.common.block.crop.BlockCropSilphium;
import com.bewitchment.common.block.crop.BlockCropThistle;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;
import com.bewitchment.common.block.tile.entity.TileEntityOven;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.common.world.gen.tree.WorldGenCypressTree;
import com.bewitchment.common.world.gen.tree.WorldGenElderTree;
import com.bewitchment.common.world.gen.tree.WorldGenJuniperTree;
import com.bewitchment.common.world.gen.tree.WorldGenYewTree;

import moriyashiine.froglib.FrogLib;
import moriyashiine.froglib.common.block.ModBlock;
import moriyashiine.froglib.common.block.ModBlockCrop;
import moriyashiine.froglib.common.block.ModBlockDoor;
import moriyashiine.froglib.common.block.ModBlockExp;
import moriyashiine.froglib.common.block.ModBlockFence;
import moriyashiine.froglib.common.block.ModBlockFenceGate;
import moriyashiine.froglib.common.block.ModBlockLeaves;
import moriyashiine.froglib.common.block.ModBlockPillar;
import moriyashiine.froglib.common.block.ModBlockSapling;
import moriyashiine.froglib.common.block.ModBlockSlab;
import moriyashiine.froglib.common.block.ModBlockStairs;
import moriyashiine.froglib.common.block.ModBlockTrapDoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModBlocks
{
	public static final List<Block> REGISTRY = new ArrayList<Block>();
	
	//Fluids
	public static final Fluid honey = FrogLib.registerFluid(Bewitchment.MOD_ID, "honey", Material.WATER, 0, 10, 1500, 8000, true, false);
	public static final Fluid oil_mundane = FrogLib.registerFluid(Bewitchment.MOD_ID, "oil_mundane", Material.WATER, 0, 0, 800, 4000, true, true);
	
	//No Item
	public static final Block glyph = null;
	public static final Block placed_item = reg(registerTileEntity(new BlockPlacedItem(), TileEntityPlacedItem.class));
	public static final Block salt_barrier = reg(new BlockSaltBarrier());
	public static final Block crop_aconitum = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_aconitum", Bewitchment.proxy.tab, ModItems.seed_aconitum, new ItemStack(ModItems.aconitum), 3));
	public static final Block crop_asphodel = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_asphodel", Bewitchment.proxy.tab, ModItems.seed_asphodel, new ItemStack(ModItems.asphodel), 4));
	public static final Block crop_belladonna = reg(new BlockCropBelladonna());
	public static final Block crop_chrysanthemum = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_chrysanthemum", Bewitchment.proxy.tab, ModItems.seed_chrysanthemum, new ItemStack(ModItems.chrysanthemum), 3));
	public static final Block crop_garlic = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_garlic", Bewitchment.proxy.tab, ModItems.seed_garlic, new ItemStack(ModItems.garlic), 3));
	public static final Block crop_ginger = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_ginger", Bewitchment.proxy.tab, ModItems.seed_ginger, new ItemStack(ModItems.ginger), 3));
	public static final Block crop_hellebore = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_hellebore", Bewitchment.proxy.tab, ModItems.seed_hellebore, new ItemStack(ModItems.hellebore), 3));
	public static final Block crop_kelp = reg(new BlockCropKelp());
	public static final Block crop_kenaf = reg(new BlockCropKenaf());
	public static final Block crop_lavender = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_lavender", Bewitchment.proxy.tab, ModItems.seed_lavender, new ItemStack(ModItems.lavender), 3));
	public static final Block crop_mandrake = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_mandrake", Bewitchment.proxy.tab, ModItems.seed_mandrake, new ItemStack(ModItems.mandrake_root), 3));
	public static final Block crop_mint = reg(new BlockCropMint());
	public static final Block crop_sagebrush = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_sagebrush", Bewitchment.proxy.tab, ModItems.seed_sagebrush, new ItemStack(ModItems.sagebrush), 3));
	public static final Block crop_silphium = reg(new BlockCropSilphium());
	public static final Block crop_thistle = reg(new BlockCropThistle());
	public static final Block crop_tulsi = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_tulsi", Bewitchment.proxy.tab, ModItems.seed_tulsi, new ItemStack(ModItems.tulsi), 3));
	public static final Block crop_white_sage = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_white_sage", Bewitchment.proxy.tab, ModItems.seed_white_sage, new ItemStack(ModItems.white_sage), 3));
	public static final Block crop_wormwood = reg(new ModBlockCrop(Bewitchment.MOD_ID, "crop_wormwood", Bewitchment.proxy.tab, ModItems.seed_wormwood, new ItemStack(ModItems.wormwood), 6));
	
	//Devices
	public static final Block witches_altar = reg(registerTileEntity(new BlockWitchesAltar(), TileEntityWitchesAltar.class));
	public static final Block distillery = reg(registerTileEntity(new BlockDistillery(), TileEntityDistillery.class));
	public static final Block oven = reg(registerTileEntity(new BlockOven(), TileEntityOven.class));
	
	//Material Blocks
	public static final Block block_cold_iron = reg(new ModBlock(Bewitchment.MOD_ID, "block_cold_iron", Material.IRON, SoundType.METAL, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockColdIron"));
	public static final Block block_silver = reg(new ModBlock(Bewitchment.MOD_ID, "block_silver", Material.IRON, SoundType.METAL, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockSilver"));
	public static final Block block_alexandrite = reg(new ModBlock(Bewitchment.MOD_ID, "block_alexandrite", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockAlexandrite"));
	public static final Block block_amethyst = reg(new ModBlock(Bewitchment.MOD_ID, "block_amethyst", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockAmethyst"));
	public static final Block block_bloodstone = reg(new ModBlock(Bewitchment.MOD_ID, "block_bloodstone", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockBloodstone"));
	public static final Block block_garnet = reg(new ModBlock(Bewitchment.MOD_ID, "block_garnet", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockGarnet"));
	public static final Block block_jasper = reg(new ModBlock(Bewitchment.MOD_ID, "block_jasper", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockJasper"));
	public static final Block block_malachite = reg(new ModBlock(Bewitchment.MOD_ID, "block_malachite", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockMalachite"));
	public static final Block block_nuummite = reg(new ModBlock(Bewitchment.MOD_ID, "block_nuummite", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockNuummite"));
	public static final Block block_tigers_eye = reg(new ModBlock(Bewitchment.MOD_ID, "block_tigers_eye", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockTigersEye"));
	public static final Block block_tourmaline = reg(new ModBlock(Bewitchment.MOD_ID, "block_tourmaline", Material.GLASS, SoundType.GLASS, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockTourmaline"));
	
	public static final Block perpetual_ice = reg(new ModBlock(Bewitchment.MOD_ID, "perpetual_ice", Material.ICE, SoundType.GLASS, Bewitchment.proxy.tab, 0.5f, 2.5f, "", 0));
	public static final Block nethersteel = reg(new ModBlock(Bewitchment.MOD_ID, "nethersteel", Material.IRON, SoundType.METAL, Bewitchment.proxy.tab, 5, 30, "pickaxe", 1, "blockNethersteel"));
	
	//Ores
	public static final Block ore_silver = reg(new ModBlock(Bewitchment.MOD_ID, "ore_silver", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreSilver"));
	public static final Block ore_salt = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_salt", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreSalt"));
	public static final Block ore_alexandrite = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_alexandrite", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreAlexandrite"));
	public static final Block ore_amethyst = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_amethyst", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreAmethyst"));
	public static final Block ore_bloodstone = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_bloodstone", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreBloodstone"));
	public static final Block ore_garnet = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_garnet", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreGarnet"));
	public static final Block ore_jasper = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_jasper", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreJasper"));
	public static final Block ore_malachite = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_malachite", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreMalachite"));
	public static final Block ore_nuummite = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_nuummite", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreNuummite"));
	public static final Block ore_tigers_eye = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_tigers_eye", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreTigersEye"));
	public static final Block ore_tourmaline = reg(new ModBlockExp(Bewitchment.MOD_ID, "ore_tourmaline", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 3, 15, "pickaxe", 1, "oreTourmaline"));
	
	public static final Block coquina = reg(new ModBlock(Bewitchment.MOD_ID, "coquina", Material.ROCK, SoundType.STONE, Bewitchment.proxy.tab, 5, 30, "pickaxe", 0, "coquina"));
	
	//Trees
	public static final Block log_cypress = reg(new ModBlockPillar(Bewitchment.MOD_ID, "log_cypress", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 10, "axe", 0, "logWood"));
	public static final Block log_elder = reg(new ModBlockPillar(Bewitchment.MOD_ID, "log_elder", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 10, "axe", 0, "logWood"));
	public static final Block log_juniper = reg(new ModBlockPillar(Bewitchment.MOD_ID, "log_juniper", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 10, "axe", 0, "logWood"));
	public static final Block log_yew = reg(new ModBlockPillar(Bewitchment.MOD_ID, "log_yew", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 10, "axe", 0, "logWood"));
	public static final Block leaves_cypress = reg(new ModBlockLeaves(Bewitchment.MOD_ID, "leaves_cypress", Bewitchment.proxy.tab, new ItemStack(ModBlocks.sapling_cypress), "treeLeaves"));
	public static final Block leaves_elder = reg(new ModBlockLeaves(Bewitchment.MOD_ID, "leaves_elder", Bewitchment.proxy.tab, new ItemStack(ModBlocks.sapling_elder), "treeLeaves"));
	public static final Block leaves_juniper = reg(new ModBlockLeaves(Bewitchment.MOD_ID, "leaves_juniper", Bewitchment.proxy.tab, new ItemStack(ModBlocks.sapling_juniper), "treeLeaves"));
	public static final Block leaves_yew = reg(new ModBlockLeaves(Bewitchment.MOD_ID, "leaves_yew", Bewitchment.proxy.tab, new ItemStack(ModBlocks.sapling_yew), "treeLeaves"));
	public static final Block planks_cypress = reg(new ModBlock(Bewitchment.MOD_ID, "planks_cypress", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 15, "axe", 0, "plankWood"));
	public static final Block planks_elder = reg(new ModBlock(Bewitchment.MOD_ID, "planks_elder", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 15, "axe", 0, "plankWood"));
	public static final Block planks_juniper = reg(new ModBlock(Bewitchment.MOD_ID, "planks_juniper", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 15, "axe", 0, "plankWood"));
	public static final Block planks_yew = reg(new ModBlock(Bewitchment.MOD_ID, "planks_yew", Material.WOOD, SoundType.WOOD, Bewitchment.proxy.tab, 2, 15, "axe", 0, "plankWood"));
	public static final Block sapling_cypress = reg(new ModBlockSapling(Bewitchment.MOD_ID, "sapling_cypress", Bewitchment.proxy.tab, WorldGenCypressTree.class, "treeSapling"));
	public static final Block sapling_elder = reg(new ModBlockSapling(Bewitchment.MOD_ID, "sapling_elder", Bewitchment.proxy.tab, WorldGenElderTree.class, "treeSapling"));
	public static final Block sapling_juniper = reg(new ModBlockSapling(Bewitchment.MOD_ID, "sapling_juniper", Bewitchment.proxy.tab, WorldGenJuniperTree.class, "treeSapling"));
	public static final Block sapling_yew = reg(new ModBlockSapling(Bewitchment.MOD_ID, "sapling_yew", Bewitchment.proxy.tab, WorldGenYewTree.class, "treeSapling"));
	
	//Ingredients
	public static final Block moonbell = reg(new BlockMoonbell("moonbell"));
	
	//Decoration
	public static final Block door_block_cypress = reg(new ModBlockDoor(Bewitchment.MOD_ID, "door_block_cypress", planks_cypress, Bewitchment.proxy.tab, new ItemStack(ModItems.door_cypress)));
	public static final Block door_block_elder = reg(new ModBlockDoor(Bewitchment.MOD_ID, "door_block_elder", planks_elder, Bewitchment.proxy.tab, new ItemStack(ModItems.door_elder)));
	public static final Block door_block_juniper = reg(new ModBlockDoor(Bewitchment.MOD_ID, "door_block_juniper", planks_juniper, Bewitchment.proxy.tab, new ItemStack(ModItems.door_juniper)));
	public static final Block door_block_yew = reg(new ModBlockDoor(Bewitchment.MOD_ID, "door_block_yew", planks_yew, Bewitchment.proxy.tab, new ItemStack(ModItems.door_yew)));
	
	public static final Block stairs_cypress = reg(new ModBlockStairs(Bewitchment.MOD_ID, "stairs_cypress", planks_cypress, Bewitchment.proxy.tab, "stairWood"));
	public static final Block stairs_elder = reg(new ModBlockStairs(Bewitchment.MOD_ID, "stairs_elder", planks_elder, Bewitchment.proxy.tab, "stairWood"));
	public static final Block stairs_juniper = reg(new ModBlockStairs(Bewitchment.MOD_ID, "stairs_juniper", planks_juniper, Bewitchment.proxy.tab, "stairWood"));
	public static final Block stairs_yew = reg(new ModBlockStairs(Bewitchment.MOD_ID, "stairs_yew", planks_yew, Bewitchment.proxy.tab, "stairWood"));
	public static final Block stairs_perpetual_ice = reg(new ModBlockStairs(Bewitchment.MOD_ID, "stairs_perpetual_ice", perpetual_ice, Bewitchment.proxy.tab));
	
	public static final ModBlockSlab slab_cypress = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_cypress", planks_cypress, Bewitchment.proxy.tab, false, "slabWood"));
	public static final ModBlockSlab slab_cypress_double = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_cypress_double", planks_cypress, Bewitchment.proxy.tab, true));
	public static final ModBlockSlab slab_elder = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_elder", planks_elder, Bewitchment.proxy.tab, false, "slabWood"));
	public static final ModBlockSlab slab_elder_double = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_elder_double", planks_elder, Bewitchment.proxy.tab, true));
	public static final ModBlockSlab slab_juniper = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_juniper", planks_juniper, Bewitchment.proxy.tab, false, "slabWood"));
	public static final ModBlockSlab slab_juniper_double = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_juniper_double", planks_juniper, Bewitchment.proxy.tab, true));
	public static final ModBlockSlab slab_yew = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_yew", planks_yew, Bewitchment.proxy.tab, false, "slabWood"));
	public static final ModBlockSlab slab_yew_double = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_yew_double", planks_yew, Bewitchment.proxy.tab, true));
	public static final ModBlockSlab slab_perpetual_ice = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_perpetual_ice", perpetual_ice, Bewitchment.proxy.tab, false));
	public static final ModBlockSlab slab_perpetual_ice_double = reg(new ModBlockSlab(Bewitchment.MOD_ID, "slab_perpetual_ice_double", perpetual_ice, Bewitchment.proxy.tab, true));
	
	public static final Block trapdoor_cypress = reg(new ModBlockTrapDoor(Bewitchment.MOD_ID, "trapdoor_cypress", planks_cypress, Bewitchment.proxy.tab));
	public static final Block trapdoor_elder = reg(new ModBlockTrapDoor(Bewitchment.MOD_ID, "trapdoor_elder", planks_elder, Bewitchment.proxy.tab));
	public static final Block trapdoor_juniper = reg(new ModBlockTrapDoor(Bewitchment.MOD_ID, "trapdoor_juniper", planks_juniper, Bewitchment.proxy.tab));
	public static final Block trapdoor_yew = reg(new ModBlockTrapDoor(Bewitchment.MOD_ID, "trapdoor_yew", planks_yew, Bewitchment.proxy.tab));
	
	public static final Block fence_gate_cypress = reg(new ModBlockFenceGate(Bewitchment.MOD_ID, "fence_gate_cypress", planks_cypress, Bewitchment.proxy.tab));
	public static final Block fence_gate_elder = reg(new ModBlockFenceGate(Bewitchment.MOD_ID, "fence_gate_elder", planks_elder, Bewitchment.proxy.tab));
	public static final Block fence_gate_juniper = reg(new ModBlockFenceGate(Bewitchment.MOD_ID, "fence_gate_juniper", planks_juniper, Bewitchment.proxy.tab));
	public static final Block fence_gate_yew = reg(new ModBlockFenceGate(Bewitchment.MOD_ID, "fence_gate_yew", planks_yew, Bewitchment.proxy.tab));
	
	public static final Block fence_cypress = reg(new ModBlockFence(Bewitchment.MOD_ID, "fence_cypress", planks_cypress, Bewitchment.proxy.tab));
	public static final Block fence_elder = reg(new ModBlockFence(Bewitchment.MOD_ID, "fence_elder", planks_elder, Bewitchment.proxy.tab));
	public static final Block fence_juniper = reg(new ModBlockFence(Bewitchment.MOD_ID, "fence_juniper", planks_juniper, Bewitchment.proxy.tab));
	public static final Block fence_yew = reg(new ModBlockFence(Bewitchment.MOD_ID, "fence_yew", planks_yew, Bewitchment.proxy.tab));
	public static final Block fence_perpetual_ice = reg(new ModBlockFence(Bewitchment.MOD_ID, "fence_perpetual_ice", perpetual_ice, Bewitchment.proxy.tab));
	
	@SubscribeEvent
	public static void register(Register<Block> event)
	{
		for (Block block : REGISTRY) event.getRegistry().register(block);
		FrogLib.proxy.ignoreProperty(crop_kelp, BlockLiquid.LEVEL);
		FrogLib.proxy.ignoreProperty(door_block_cypress, BlockDoor.POWERED);
		FrogLib.proxy.ignoreProperty(door_block_elder, BlockDoor.POWERED);
		FrogLib.proxy.ignoreProperty(door_block_juniper, BlockDoor.POWERED);
		FrogLib.proxy.ignoreProperty(door_block_yew, BlockDoor.POWERED);
		FrogLib.proxy.ignoreProperty(fence_gate_cypress, BlockFenceGate.POWERED);
		FrogLib.proxy.ignoreProperty(fence_gate_elder, BlockFenceGate.POWERED);
		FrogLib.proxy.ignoreProperty(fence_gate_juniper, BlockFenceGate.POWERED);
		FrogLib.proxy.ignoreProperty(fence_gate_yew, BlockFenceGate.POWERED);
	}
	
	private static Block registerTileEntity(Block block, Class<? extends TileEntity> tile)
	{
		GameRegistry.registerTileEntity(tile, block.getRegistryName());
		return block;
	}
	
	private static Block reg(Block block)
	{
		REGISTRY.add(block);
		return block;
	}
	
	private static ModBlockSlab reg(ModBlockSlab block)
	{
		REGISTRY.add(block);
		return block;
	}
}
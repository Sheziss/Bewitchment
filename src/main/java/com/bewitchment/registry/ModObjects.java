package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockCandle;
import com.bewitchment.common.block.BlockCandleBase;
import com.bewitchment.common.block.BlockCrystalBall;
import com.bewitchment.common.block.BlockDistillery;
import com.bewitchment.common.block.BlockGemBowl;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGoblet;
import com.bewitchment.common.block.BlockLantern;
import com.bewitchment.common.block.BlockLoom;
import com.bewitchment.common.block.BlockMoonbell;
import com.bewitchment.common.block.BlockOven;
import com.bewitchment.common.block.BlockPlacedItem;
import com.bewitchment.common.block.BlockPurifyingEarth;
import com.bewitchment.common.block.BlockSaltBarrier;
import com.bewitchment.common.block.BlockTarotTable;
import com.bewitchment.common.block.BlockWitchFire;
import com.bewitchment.common.block.BlockWitchesAltar;
import com.bewitchment.common.block.BlockWitchesCauldron;
import com.bewitchment.common.block.BlockWitchesLight;
import com.bewitchment.common.block.crop.BlockCropBelladonna;
import com.bewitchment.common.block.crop.BlockCropKelp;
import com.bewitchment.common.block.crop.BlockCropKenaf;
import com.bewitchment.common.block.crop.BlockCropMint;
import com.bewitchment.common.block.crop.BlockCropSilphium;
import com.bewitchment.common.block.crop.BlockCropThistle;
import com.bewitchment.common.block.tile.entity.TileEntityCrystalBall;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;
import com.bewitchment.common.block.tile.entity.TileEntityGemBowl;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.common.block.tile.entity.TileEntityLoom;
import com.bewitchment.common.block.tile.entity.TileEntityOven;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesCauldron;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;
import com.bewitchment.common.block.util.ModBlock;
import com.bewitchment.common.block.util.ModBlockBush;
import com.bewitchment.common.block.util.ModBlockButton;
import com.bewitchment.common.block.util.ModBlockCrop;
import com.bewitchment.common.block.util.ModBlockExp;
import com.bewitchment.common.block.util.ModBlockFence;
import com.bewitchment.common.block.util.ModBlockFenceGate;
import com.bewitchment.common.block.util.ModBlockLeaves;
import com.bewitchment.common.block.util.ModBlockPillar;
import com.bewitchment.common.block.util.ModBlockPressurePlate;
import com.bewitchment.common.block.util.ModBlockSapling;
import com.bewitchment.common.block.util.ModBlockSlab;
import com.bewitchment.common.block.util.ModBlockStairs;
import com.bewitchment.common.block.util.ModBlockTrapdoor;
import com.bewitchment.common.integration.chisel.ModBlockChisel;
import com.bewitchment.common.item.ItemChalk;
import com.bewitchment.common.item.ItemCypressBroom;
import com.bewitchment.common.item.ItemElderBroom;
import com.bewitchment.common.item.ItemJuniperBroom;
import com.bewitchment.common.item.ItemLantern;
import com.bewitchment.common.item.ItemSalt;
import com.bewitchment.common.item.ItemTarotsDeck;
import com.bewitchment.common.item.ItemUndyingSalve;
import com.bewitchment.common.item.ItemWaystone;
import com.bewitchment.common.item.ItemYewBroom;
import com.bewitchment.common.item.equipment.ItemColdIronArmor;
import com.bewitchment.common.item.equipment.ItemSilverArmor;
import com.bewitchment.common.item.equipment.bauble.ItemGirdleOfTheDryad;
import com.bewitchment.common.item.equipment.bauble.ItemHellishBauble;
import com.bewitchment.common.item.equipment.bauble.ItemHorseshoe;
import com.bewitchment.common.item.equipment.bauble.ItemNazar;
import com.bewitchment.common.item.equipment.bauble.ItemTalisman;
import com.bewitchment.common.item.equipment.bauble.ItemTokenOfRemedies;
import com.bewitchment.common.item.equipment.bauble.ItemTriskelionAmulet;
import com.bewitchment.common.item.equipment.bauble.ItemWrathfulEye;
import com.bewitchment.common.item.food.ItemAconitum;
import com.bewitchment.common.item.food.ItemAsphodel;
import com.bewitchment.common.item.food.ItemBelladonna;
import com.bewitchment.common.item.food.ItemChrysanthemum;
import com.bewitchment.common.item.food.ItemGarlic;
import com.bewitchment.common.item.food.ItemGinger;
import com.bewitchment.common.item.food.ItemHeart;
import com.bewitchment.common.item.food.ItemHellebore;
import com.bewitchment.common.item.food.ItemKelp;
import com.bewitchment.common.item.food.ItemKenaf;
import com.bewitchment.common.item.food.ItemLavender;
import com.bewitchment.common.item.food.ItemMandrakeRoot;
import com.bewitchment.common.item.food.ItemMint;
import com.bewitchment.common.item.food.ItemSagebrush;
import com.bewitchment.common.item.food.ItemSilphium;
import com.bewitchment.common.item.food.ItemThistle;
import com.bewitchment.common.item.food.ItemTulsi;
import com.bewitchment.common.item.food.ItemWhiteSage;
import com.bewitchment.common.item.food.ItemWormwood;
import com.bewitchment.common.item.tool.ItemAthame;
import com.bewitchment.common.item.tool.ItemBoline;
import com.bewitchment.common.item.tool.ItemColdIronAxe;
import com.bewitchment.common.item.tool.ItemColdIronHoe;
import com.bewitchment.common.item.tool.ItemColdIronPickaxe;
import com.bewitchment.common.item.tool.ItemColdIronSpade;
import com.bewitchment.common.item.tool.ItemColdIronSword;
import com.bewitchment.common.item.tool.ItemSilverAxe;
import com.bewitchment.common.item.tool.ItemSilverHoe;
import com.bewitchment.common.item.tool.ItemSilverPickaxe;
import com.bewitchment.common.item.tool.ItemSilverSpade;
import com.bewitchment.common.item.tool.ItemSilverSword;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.common.item.util.ModItemDoor;
import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.common.item.util.ModItemSeed;
import com.bewitchment.common.world.gen.tree.WorldGenCypressTree;
import com.bewitchment.common.world.gen.tree.WorldGenElderTree;
import com.bewitchment.common.world.gen.tree.WorldGenJuniperTree;
import com.bewitchment.common.world.gen.tree.WorldGenYewTree;

import baubles.api.BaubleType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import team.chisel.api.carving.CarvingUtils;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModObjects {
	public static final List<Object> REGISTRY = new ArrayList<>();

	public static final ArmorMaterial ARMOR_COLD_IRON = EnumHelper.addArmorMaterial("cold_iron", Bewitchment.MOD_ID + ":" + "cold_iron", 18, new int[]{2, 6, 7, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.45f);
	public static final ArmorMaterial ARMOR_SILVER = EnumHelper.addArmorMaterial("silver", Bewitchment.MOD_ID + ":" + "silver", 12, new int[]{1, 4, 5, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5f);

	public static final ToolMaterial TOOL_COLD_IRON = EnumHelper.addToolMaterial("cold_iron", 2, 850, 7, 3, 8);
	public static final ToolMaterial TOOL_SILVER = EnumHelper.addToolMaterial("silver", 1, 215, 10, 2.5f, 24);

	public static final ArmorMaterial ARMOR_BEWITCHED_LEATHER = EnumHelper.addArmorMaterial("bewitched_leather", Bewitchment.MOD_ID + ":" + "bewitched_leather", 24, new int[]{1, 4, 5, 1}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5f);
	public static final ArmorMaterial ARMOR_VAMPIRE = EnumHelper.addArmorMaterial("vampire", Bewitchment.MOD_ID + ":" + "vampire", 9, new int[]{2, 6, 7, 1}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.25f);

	public static final ToolMaterial TOOL_RITUAL = EnumHelper.addToolMaterial("ritual", 2, 300, 2, 1.5f, 30);

	// Fluids
	public static final Fluid fluid_oil_mundane = createFluid("oil_mundane", Material.WATER, 0, 0, 800, 4000, true, true);

	// No Item
	public static final Block glyph = createTileEntity(new BlockGlyph(), TileEntityGlyph.class);
	public static final Block placed_item = createTileEntity(new BlockPlacedItem(), TileEntityPlacedItem.class);
	public static final Block witchfire = new BlockWitchFire("witchfire");
	public static final Block endfire = new BlockWitchFire("endfire");
	public static final Block frostfire = new BlockWitchFire("frostfire");
	public static final Block sightfire = new BlockWitchFire("sightfire");
	public static final Block witches_light = new BlockWitchesLight();
	public static final Block salt_barrier = new BlockSaltBarrier();
	public static final Block crop_belladonna = new BlockCropBelladonna();
	public static final Block crop_kelp = new BlockCropKelp();
	public static final Block crop_kenaf = new BlockCropKenaf();
	public static final Block crop_mint = new BlockCropMint();
	public static final Block crop_silphium = new BlockCropSilphium();
	public static final Block crop_thistle = new BlockCropThistle();
	// Devices
	public static final Block witches_altar_unformed = createTileEntity(new BlockWitchesAltar(""), TileEntityWitchesAltar.class);
	public static final Block witches_altar_white = new BlockWitchesAltar("white").setCreativeTab(null);
	public static final Block witches_altar_orange = new BlockWitchesAltar("orange").setCreativeTab(null);
	public static final Block witches_altar_magenta = new BlockWitchesAltar("magenta").setCreativeTab(null);
	public static final Block witches_altar_light_blue = new BlockWitchesAltar("light_blue").setCreativeTab(null);
	public static final Block witches_altar_yellow = new BlockWitchesAltar("yellow").setCreativeTab(null);
	public static final Block witches_altar_lime = new BlockWitchesAltar("lime").setCreativeTab(null);
	public static final Block witches_altar_pink = new BlockWitchesAltar("pink").setCreativeTab(null);
	public static final Block witches_altar_gray = new BlockWitchesAltar("gray").setCreativeTab(null);
	public static final Block witches_altar_light_gray = new BlockWitchesAltar("light_gray").setCreativeTab(null);
	public static final Block witches_altar_cyan = new BlockWitchesAltar("cyan").setCreativeTab(null);
	public static final Block witches_altar_purple = new BlockWitchesAltar("purple").setCreativeTab(null);
	public static final Block witches_altar_blue = new BlockWitchesAltar("blue").setCreativeTab(null);
	public static final Block witches_altar_brown = new BlockWitchesAltar("brown").setCreativeTab(null);
	public static final Block witches_altar_green = new BlockWitchesAltar("green").setCreativeTab(null);
	public static final Block witches_altar_red = new BlockWitchesAltar("red").setCreativeTab(null);
	public static final Block witches_altar_black = new BlockWitchesAltar("black").setCreativeTab(null);
	public static final Block oven = createTileEntity(new BlockOven(), TileEntityOven.class);
	public static final Block distillery = createTileEntity(new BlockDistillery(), TileEntityDistillery.class);
	public static final Block cauldron = createTileEntity(new BlockWitchesCauldron(), TileEntityWitchesCauldron.class);
	public static final Block loom = createTileEntity(new BlockLoom(), TileEntityLoom.class);
	public static final Block tarot_table = createTileEntity(new BlockTarotTable(), TileEntityAltarStorage.class);
	public static final Item tarots_deck = new ItemTarotsDeck();
	public static final Block crystal_ball = createTileEntity(new BlockCrystalBall(), TileEntityCrystalBall.class);
	public static final Block gem_bowl = createTileEntity(new BlockGemBowl(), TileEntityGemBowl.class);
	// Material Blocks
	public static final Block block_cold_iron = new ModBlock("block_cold_iron", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 2, "blockColdIron");
	public static final Block[] block_cold_iron_chiseled = createChiselBlocks(block_cold_iron, "symbol", "bevel", "sun", "moon", "sword", "cup", "wand", "pentacle", "pentagram");
	public static final Block block_silver = new ModBlock("block_silver", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 0, "blockSilver");
	public static final Block[] block_silver_chiseled = createChiselBlocks(block_silver, "symbol", "bevel", "sun", "moon", "sword", "cup", "wand", "pentacle", "pentagram");
	public static final Block block_amethyst = new ModBlock("block_amethyst", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 2, "blockAmethyst");
	public static final Block block_garnet = new ModBlock("block_garnet", Material.GLASS, SoundType.GLASS, 5, 30, "pickaxe", 2, "blockGarnet");
	// Ores
	public static final Block ore_silver = new ModBlock("ore_silver", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 1, "oreSilver");
	public static final Block ore_salt = new ModBlockExp("ore_salt", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 0, "oreSalt");
	public static final Block ore_amethyst = new ModBlockExp("ore_amethyst", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 2, "oreAmethyst");
	public static final Block ore_garnet = new ModBlockExp("ore_garnet", Material.ROCK, SoundType.STONE, 3, 15, "pickaxe", 2, "oreGarnet");
	// Misc
	public static final Block perpetual_ice = new ModBlock("perpetual_ice", Material.ICE, SoundType.GLASS, 0.5f, 2.5f, "", 0);
	public static final Block nethersteel = new ModBlock("nethersteel", Material.IRON, SoundType.METAL, 5, 30, "pickaxe", 1, "blockNethersteel");
	public static final Block[] nethersteel_chiseled = createChiselBlocks(nethersteel, "symbol", "bevel", "polished", "sentient", "pentacle", "pentagram", "skull", "eye", "watching_eye", "hellish", "watching_hellish");
	public static final Block scorned_bricks = new ModBlock("scorned_bricks", Material.ROCK, SoundType.STONE, 5, 5, "pickaxe", 3);
	public static final Block[] scorned_bricks_chiseled = createChiselBlocks(scorned_bricks, "raw", "cracked", "smooth_cracked", "symbol", "bevel", "hellish", "circular");
	public static final Block stairs_scorned_bricks = new ModBlockStairs("stairs_scorned_bricks", scorned_bricks);
	public static final Block slab_scorned_bricks = new ModBlockSlab("slab_scorned_bricks", scorned_bricks);
	public static final Block fence_scorned_bricks = new ModBlockFence("fence_scorned_bricks", scorned_bricks);
	public static final Block coquina = new ModBlock("coquina", Material.ROCK, SoundType.STONE, 5, 30, "pickaxe", 0, "coquina");
	public static final Block[] coquina_chiseled = createChiselBlocks(coquina, "smooth", "bricks", "chiseled", "shell");
	public static final Block purifying_earth = new BlockPurifyingEarth();
	public static final Block graveyard_dirt = new ModBlock("graveyard_dirt", Material.GROUND, SoundType.GROUND, 0.5f, 2.5f, "shovel", 0);
	// Goblet + Lanterns
	public static final Block goblet = new BlockGoblet();
	public static final Block lantern_white = new BlockLantern("white");
	public static final Block lantern_orange = new BlockLantern("orange");
	public static final Block lantern_magenta = new BlockLantern("magenta");
	public static final Block lantern_light_blue = new BlockLantern("light_blue");
	public static final Block lantern_yellow = new BlockLantern("yellow");
	public static final Block lantern_lime = new BlockLantern("lime");
	public static final Block lantern_pink = new BlockLantern("pink");
	public static final Block lantern_gray = new BlockLantern("gray");
	public static final Block lantern_light_gray = new BlockLantern("light_gray");
	public static final Block lantern_cyan = new BlockLantern("cyan");
	public static final Block lantern_purple = new BlockLantern("purple");
	public static final Block lantern_blue = new BlockLantern("blue");
	public static final Block lantern_brown = new BlockLantern("brown");
	public static final Block lantern_green = new BlockLantern("green");
	public static final Block lantern_red = new BlockLantern("red");
	public static final Block lantern_black = new BlockLantern("black");
	// Candles
	public static final Block candle_white = new BlockCandle("candle_white");
	public static final Block candle_orange = new BlockCandle("candle_orange");
	public static final Block candle_magenta = new BlockCandle("candle_magenta");
	public static final Block candle_light_blue = new BlockCandle("candle_light_blue");
	public static final Block candle_yellow = new BlockCandle("candle_yellow");
	public static final Block candle_lime = new BlockCandle("candle_lime");
	public static final Block candle_pink = new BlockCandle("candle_pink");
	public static final Block candle_gray = new BlockCandle("candle_gray");
	public static final Block candle_light_gray = new BlockCandle("candle_light_gray");
	public static final Block candle_cyan = new BlockCandle("candle_cyan");
	public static final Block candle_purple = new BlockCandle("candle_purple");
	public static final Block candle_blue = new BlockCandle("candle_blue");
	public static final Block candle_brown = new BlockCandle("candle_brown");
	public static final Block candle_green = new BlockCandle("candle_green");
	public static final Block candle_red = new BlockCandle("candle_red");
	public static final Block candle_black = new BlockCandle("candle_black");
	// Trees
	public static final Block sapling_cypress = new ModBlockSapling("sapling_cypress", WorldGenCypressTree.class, "treeSapling");
	public static final Block sapling_elder = new ModBlockSapling("sapling_elder", WorldGenElderTree.class, "treeSapling");
	public static final Block sapling_juniper = new ModBlockSapling("sapling_juniper", WorldGenJuniperTree.class, "treeSapling");
	public static final Block sapling_yew = new ModBlockSapling("sapling_yew", WorldGenYewTree.class, "treeSapling");
	public static final Block log_cypress = new ModBlockPillar("log_cypress", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_elder = new ModBlockPillar("log_elder", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_juniper = new ModBlockPillar("log_juniper", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block log_yew = new ModBlockPillar("log_yew", Material.WOOD, SoundType.WOOD, 2, 10, "axe", 0, "logWood");
	public static final Block leaves_cypress = new ModBlockLeaves("leaves_cypress", new ItemStack(sapling_cypress), "treeLeaves");
	public static final Block leaves_elder = new ModBlockLeaves("leaves_elder", new ItemStack(sapling_elder), "treeLeaves");
	public static final Block leaves_juniper = new ModBlockLeaves("leaves_juniper", new ItemStack(sapling_juniper), "treeLeaves");
	public static final Block leaves_yew = new ModBlockLeaves("leaves_yew", new ItemStack(sapling_yew), "treeLeaves");
	public static final Block planks_cypress = new ModBlock("planks_cypress", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_elder = new ModBlock("planks_elder", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_juniper = new ModBlock("planks_juniper", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	public static final Block planks_yew = new ModBlock("planks_yew", Material.WOOD, SoundType.WOOD, 2, 15, "axe", 0, "plankWood");
	// Ingredients
	public static final Block moonbell = new BlockMoonbell();
	// Decoration
	public static final ModItemDoor door_cypress = new ModItemDoor("door_cypress", planks_cypress);
	public static final ModItemDoor door_elder = new ModItemDoor("door_elder", planks_elder);
	public static final ModItemDoor door_juniper = new ModItemDoor("door_juniper", planks_juniper);
	public static final ModItemDoor door_yew = new ModItemDoor("door_yew", planks_yew);
	public static final Block stairs_cypress = new ModBlockStairs("stairs_cypress", planks_cypress, "stairWood");
	public static final Block stairs_elder = new ModBlockStairs("stairs_elder", planks_elder, "stairWood");
	public static final Block stairs_juniper = new ModBlockStairs("stairs_juniper", planks_juniper, "stairWood");
	public static final Block stairs_yew = new ModBlockStairs("stairs_yew", planks_yew, "stairWood");
	public static final Block stairs_perpetual_ice = new ModBlockStairs("stairs_perpetual_ice", perpetual_ice);
	public static final Block slab_cypress = new ModBlockSlab("slab_cypress", planks_cypress, "slabWood");
	public static final Block slab_elder = new ModBlockSlab("slab_elder", planks_elder, "slabWood");
	public static final Block slab_juniper = new ModBlockSlab("slab_juniper", planks_juniper, "slabWood");
	public static final Block slab_yew = new ModBlockSlab("slab_yew", planks_yew, "slabWood");
	public static final Block slab_perpetual_ice = new ModBlockSlab("slab_perpetual_ice", perpetual_ice);
	public static final Block trapdoor_cypress = new ModBlockTrapdoor("trapdoor_cypress", planks_cypress);
	public static final Block trapdoor_elder = new ModBlockTrapdoor("trapdoor_elder", planks_elder);
	public static final Block trapdoor_juniper = new ModBlockTrapdoor("trapdoor_juniper", planks_juniper);
	public static final Block trapdoor_yew = new ModBlockTrapdoor("trapdoor_yew", planks_yew);
	public static final Block fence_gate_cypress = new ModBlockFenceGate("fence_gate_cypress", planks_cypress, "fenceGateWood");
	public static final Block fence_gate_elder = new ModBlockFenceGate("fence_gate_elder", planks_elder, "fenceGateWood");
	public static final Block fence_gate_juniper = new ModBlockFenceGate("fence_gate_juniper", planks_juniper, "fenceGateWood");
	public static final Block fence_gate_yew = new ModBlockFenceGate("fence_gate_yew", planks_yew, "fenceGateWood");
	public static final Block fence_cypress = new ModBlockFence("fence_cypress", planks_cypress, "fenceWood");
	public static final Block fence_elder = new ModBlockFence("fence_elder", planks_elder, "fenceWood");
	public static final Block fence_juniper = new ModBlockFence("fence_juniper", planks_juniper, "fenceWood");
	public static final Block fence_yew = new ModBlockFence("fence_yew", planks_yew, "fenceWood");
	public static final Block fence_perpetual_ice = new ModBlockFence("fence_perpetual_ice", perpetual_ice);
	public static final Block pressure_plate_cypress = new ModBlockPressurePlate("pressure_plate_cypress", planks_cypress);
	public static final Block pressure_plate_elder = new ModBlockPressurePlate("pressure_plate_elder", planks_elder);
	public static final Block pressure_plate_juniper = new ModBlockPressurePlate("pressure_plate_juniper", planks_juniper);
	public static final Block pressure_plate_yew = new ModBlockPressurePlate("pressure_plate_yew", planks_yew);
	public static final Block button_cypress = new ModBlockButton("button_cypress", planks_cypress);
	public static final Block button_elder = new ModBlockButton("button_elder", planks_elder);
	public static final Block button_juniper = new ModBlockButton("button_juniper", planks_juniper);
	public static final Block button_yew = new ModBlockButton("button_yew", planks_yew);
	// Baubles
	public static final Item girdle_of_the_dryad = new ItemGirdleOfTheDryad();
	public static final Item hellish_bauble = new ItemHellishBauble();
	public static final Item horseshoe = new ItemHorseshoe();
	public static final Item nazar = new ItemNazar();
	public static final Item token_of_remedies = new ItemTokenOfRemedies();
	public static final Item triskelion_amulet = new ItemTriskelionAmulet();
	public static final Item wrathful_eye = new ItemWrathfulEye();
	public static final Item aquamarine_crown = new ItemTalisman("aquamarine_crown", BaubleType.HEAD);
	public static final Item emerald_pendant = new ItemTalisman("emerald_pendant", BaubleType.AMULET);
	public static final Item everwatching_eye = new ItemTalisman("everwatching_eye", BaubleType.CHARM);
	public static final Item ring_of_the_adamantine_star = new ItemTalisman("ring_of_the_adamantine_star", BaubleType.RING);
	public static final Item scarlet_orb = new ItemTalisman("scarlet_orb", BaubleType.BELT);
	// Armor
	public static final Item helmet_cold_iron = new ItemColdIronArmor("helmet_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.HEAD);
	public static final Item chestplate_cold_iron = new ItemColdIronArmor("chestplate_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.CHEST);
	public static final Item leggings_cold_iron = new ItemColdIronArmor("leggings_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.LEGS);
	public static final Item boots_cold_iron = new ItemColdIronArmor("boots_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.FEET);
	public static final Item helmet_silver = new ItemSilverArmor("helmet_silver", ARMOR_SILVER, EntityEquipmentSlot.HEAD);
	public static final Item chestplate_silver = new ItemSilverArmor("chestplate_silver", ARMOR_SILVER, EntityEquipmentSlot.CHEST);
	public static final Item leggings_silver = new ItemSilverArmor("leggings_silver", ARMOR_SILVER, EntityEquipmentSlot.LEGS);
	public static final Item boots_silver = new ItemSilverArmor("boots_silver", ARMOR_SILVER, EntityEquipmentSlot.FEET);
	// Tools
	public static final Item sword_cold_iron = new ItemColdIronSword(TOOL_COLD_IRON);
	public static final Item pickaxe_cold_iron = new ItemColdIronPickaxe(TOOL_COLD_IRON);
	public static final Item axe_cold_iron = new ItemColdIronAxe(TOOL_COLD_IRON);
	public static final Item spade_cold_iron = new ItemColdIronSpade(TOOL_COLD_IRON);
	public static final Item hoe_cold_iron = new ItemColdIronHoe(TOOL_COLD_IRON);
	public static final Item sword_silver = new ItemSilverSword(TOOL_SILVER);
	public static final Item pickaxe_silver = new ItemSilverPickaxe(TOOL_SILVER);
	public static final Item axe_silver = new ItemSilverAxe(TOOL_SILVER);
	public static final Item spade_silver = new ItemSilverSpade(TOOL_SILVER);
	public static final Item hoe_silver = new ItemSilverHoe(TOOL_SILVER);
	public static final Item athame = new ItemAthame(TOOL_RITUAL);
	public static final Item boline = new ItemBoline();

	//	public static final Item witcharmor
	public static final Item waystone = new ItemWaystone();
	public static final Item chalk_normal = new ItemChalk("chalk_normal");
	public static final Item chalk_golden = new ItemChalk("chalk_golden");
	public static final Item chalk_nether = new ItemChalk("chalk_nether");
	public static final Item chalk_ender = new ItemChalk("chalk_ender");
	// Brooms
	public static final Item broom = new ModItem("broom").setMaxStackSize(1);
	public static final Item broom_cypress = new ItemCypressBroom();
	public static final Item broom_elder = new ItemElderBroom();
	public static final Item broom_juniper = new ItemJuniperBroom();
	public static final Item broom_yew = new ItemYewBroom();
	// Material Items
	public static final Item ingot_cold_iron = new ModItem("ingot_cold_iron", "ingotColdIron");
	public static final Item ingot_silver = new ModItem("ingot_silver", "ingotSilver");
	public static final Item nugget_cold_iron = new ModItem("nugget_cold_iron", "nuggetColdIron");
	public static final Item nugget_silver = new ModItem("nugget_silver", "nuggetSilver");
	public static final Item gem_amethyst = new ModItem("gem_amethyst", "gemAmethyst", "gemAll");
	public static final Item gem_garnet = new ModItem("gem_garnet", "gemGarnet", "gemAll");
	public static final Item salt = new ItemSalt();
	public static final Item pentacle = new ModItem("pentacle");
	public static final Item tallow = new ModItem("tallow", "materialWax", "materialBeeswax", "materialPressedWax", "itemBeeswax", "wax", "tallow", "clumpWax", "beeswax", "itemWax");
	// Oven Jars
	public static final Item unfired_jar = new ModItem("unfired_jar");
	public static final Item empty_jar = new ModItem("empty_jar");
	public static final Item oak_spirit = new ModItem("oak_spirit");
	public static final Item spruce_heart = new ModItem("spruce_heart");
	public static final Item birch_soul = new ModItem("birch_soul");
	public static final Item cloudy_oil = new ModItem("cloudy_oil");
	public static final Item acacia_resin = new ModItem("acacia_resin");
	public static final Item ebb_of_death = new ModItem("ebb_of_death");
	public static final Item droplet_of_wisdom = new ModItem("droplet_of_wisdom");
	public static final Item liquid_witchcraft = new ModItem("liquid_witchcraft");
	public static final Item essence_of_vitality = new ModItem("essence_of_vitality");
	// Distillery Jars
	public static final Item cleansing_balm = new ModItem("cleansing_balm");
	public static final Item demonic_elixir = new ModItem("demonic_elixir");
	public static final Item everchanging_dew = new ModItem("everchanging_dew");
	public static final Item fiery_unguent = new ModItem("fiery_unguent");
	public static final Item heaven_extract = new ModItem("heaven_extract");
	public static final Item otherworldly_tears = new ModItem("otherworldly_tears");
	public static final Item philter_of_dishonesty = new ModItem("philter_of_dishonesty");
	public static final Item stone_ichor = new ModItem("stone_ichor");
	public static final Item swirl_of_the_depths = new ModItem("swirl_of_the_depths");
	public static final Item undying_salve = new ItemUndyingSalve();
	// Loom
	public static final Item diabolical_vein = new ModItem("diabolical_vein");
	public static final Item golden_thread = new ModItem("golden_thread");
	public static final Item pure_filament = new ModItem("pure_filament");
	public static final Item regal_silk = new ModItem("regal_silk");
	public static final Item sanguine_fabric = new ModItem("sanguine_fabric");
	public static final Item soul_string = new ModItem("soul_string");
	public static final Item witches_stitching = new ModItem("witches_stitching");
	// Ingredients
	public static final Item aconitum = new ItemAconitum();
	public static final Item asphodel = new ItemAsphodel();
	public static final Item belladonna = new ItemBelladonna();
	public static final Item chrysanthemum = new ItemChrysanthemum();
	public static final Item garlic = new ItemGarlic();
	public static final Item ginger = new ItemGinger();
	public static final Item hellebore = new ItemHellebore();
	public static final Item kelp = new ItemKelp();
	public static final Item kenaf = new ItemKenaf();
	public static final Item lavender = new ItemLavender();
	public static final Item mandrake_root = new ItemMandrakeRoot();
	public static final Item mint = new ItemMint();
	public static final Item sagebrush = new ItemSagebrush();
	public static final Item silphium = new ItemSilphium();
	public static final Item thistle = new ItemThistle();
	public static final Item tulsi = new ItemTulsi();
	public static final Item white_sage = new ItemWhiteSage();
	public static final Item witchweed = new ModItem("witchweed");
	public static final Item wormwood = new ItemWormwood();
	public static final Block crop_aconitum = new ModBlockCrop("crop_aconitum", ModObjects.seed_aconitum, new ItemStack(ModObjects.aconitum), 3);
	// Seeds
	public static final ModItemSeed seed_aconitum = new ModItemSeed("seed_aconitum", ModObjects.crop_aconitum, Blocks.FARMLAND);
	public static final Block crop_asphodel = new ModBlockCrop("crop_asphodel", ModObjects.seed_asphodel, new ItemStack(ModObjects.asphodel), 4);
	public static final ModItemSeed seed_asphodel = new ModItemSeed("seed_asphodel", ModObjects.crop_asphodel, Blocks.FARMLAND);
	public static final ModItemSeed seed_belladonna = new ModItemSeed("seed_belladonna", ModObjects.crop_belladonna, Blocks.FARMLAND);
	public static final Block crop_chrysanthemum = new ModBlockCrop("crop_chrysanthemum", ModObjects.seed_chrysanthemum, new ItemStack(ModObjects.chrysanthemum), 3);
	public static final ModItemSeed seed_chrysanthemum = new ModItemSeed("seed_chrysanthemum", ModObjects.crop_chrysanthemum, Blocks.FARMLAND);
	public static final Block crop_garlic = new ModBlockCrop("crop_garlic", ModObjects.seed_garlic, new ItemStack(ModObjects.garlic), 3);
	public static final ModItemSeed seed_garlic = new ModItemSeed("seed_garlic", ModObjects.crop_garlic, Blocks.FARMLAND);
	public static final Block crop_ginger = new ModBlockCrop("crop_ginger", ModObjects.seed_ginger, new ItemStack(ModObjects.ginger), 3);
	public static final ModItemSeed seed_ginger = new ModItemSeed("seed_ginger", ModObjects.crop_ginger, Blocks.FARMLAND);
	public static final Block crop_hellebore = new ModBlockCrop("crop_hellebore", ModObjects.seed_hellebore, new ItemStack(ModObjects.hellebore), 3);
	public static final ModItemSeed seed_hellebore = new ModItemSeed("seed_hellebore", ModObjects.crop_hellebore, Blocks.FARMLAND);
	public static final ModItemSeed seed_kelp = new ModItemSeed("seed_kelp", ModObjects.crop_kelp, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL);
	public static final ModItemSeed seed_kenaf = new ModItemSeed("seed_kenaf", ModObjects.crop_kenaf, Blocks.FARMLAND);
	public static final Block crop_lavender = new ModBlockCrop("crop_lavender", ModObjects.seed_lavender, new ItemStack(ModObjects.lavender), 3);
	public static final ModItemSeed seed_lavender = new ModItemSeed("seed_lavender", ModObjects.crop_lavender, Blocks.FARMLAND);
	public static final Block crop_mandrake = new ModBlockCrop("crop_mandrake", ModObjects.seed_mandrake, new ItemStack(ModObjects.mandrake_root), 3);
	public static final ModItemSeed seed_mandrake = new ModItemSeed("seed_mandrake", ModObjects.crop_mandrake, Blocks.FARMLAND);
	public static final ModItemSeed seed_mint = new ModItemSeed("seed_mint", ModObjects.crop_mint, Blocks.FARMLAND);
	public static final Block crop_sagebrush = new ModBlockCrop("crop_sagebrush", ModObjects.seed_sagebrush, new ItemStack(ModObjects.sagebrush), 3);
	public static final ModItemSeed seed_sagebrush = new ModItemSeed("seed_sagebrush", ModObjects.crop_sagebrush, Blocks.FARMLAND);
	public static final ModItemSeed seed_silphium = new ModItemSeed("seed_silphium", ModObjects.crop_silphium, Blocks.FARMLAND);
	public static final ModItemSeed seed_thistle = new ModItemSeed("seed_thistle", ModObjects.crop_thistle, Blocks.FARMLAND);
	public static final Block crop_tulsi = new ModBlockCrop("crop_tulsi", ModObjects.seed_tulsi, new ItemStack(ModObjects.tulsi), 3);
	public static final ModItemSeed seed_tulsi = new ModItemSeed("seed_tulsi", ModObjects.crop_tulsi, Blocks.FARMLAND);
	public static final Block crop_white_sage = new ModBlockCrop("crop_white_sage", ModObjects.seed_white_sage, new ItemStack(ModObjects.white_sage), 3);
	public static final ModItemSeed seed_white_sage = new ModItemSeed("seed_white_sage", ModObjects.crop_white_sage, Blocks.FARMLAND);
	public static final Block crop_wormwood = new ModBlockCrop("crop_wormwood", ModObjects.seed_wormwood, new ItemStack(ModObjects.wormwood), 6);
	public static final ModItemSeed seed_wormwood = new ModItemSeed("seed_wormwood", ModObjects.crop_wormwood, Blocks.FARMLAND);
	// Food
	public static final Item juniper_berries = new ModItemFood("juniper_berries", 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f);
	public static final Item yew_aril = new ModItemFood("yew_aril", 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f);

	// Mod Mob Drops
	public static final Item blindworms_sting = new ModItem("blindworms_sting");
	public static final Item lizard_leg = new ModItem("lizard_leg");
	public static final Item eye_of_newt = new ModItem("eye_of_newt");
	public static final Item owlets_wing = new ModItem("owlets_wing");
	public static final Item ravens_feather = new ModItem("ravens_feather");
	public static final Item adders_fork = new ModItem("adders_fork");
	public static final Item fillet_of_fenny_snake = new ModItem("fillet_of_fenny_snake");
	public static final Item toe_of_frog = new ModItem("toe_of_frog");
	public static final Item hellhound_horn = new ModItem("hellhound_horn");
	public static final Item heart = new ItemHeart();
	public static final Item demonic_heart = new ModItem("demonic_heart");
	public static final Item glass_jar = new ModItem("glass_jar");
	public static final Item snake_venom = new ModItem("snake_venom");
	public static final Item liquid_wroth = new ModItem("liquid_wroth");

	// Athame, Etc
	public static final Item carnivorous_tooth = new ModItem("carnivorous_tooth");
	public static final Item chromatic_quill = new ModItem("chromatic_quill");
	public static final Item envenomed_fang = new ModItem("envenomed_fang");
	public static final Item hoof = new ModItem("hoof");
	public static final Item eye_of_old = new ModItem("eye_of_old");
	public static final Item silver_scales = new ModItem("silver_scales");
	public static final Item tongue_of_dog = new ModItem("tongue_of_dog");
	public static final Item wool_of_bat = new ModItem("wool_of_bat");

	// Misc
	public static final Item dimensional_sand = new ModItem("dimensional_sand");
	public static final Item ectoplasm = new ModItem("ectoplasm");
	public static final Item graveyard_dust = new ModItem("graveyard_dust");
	public static final Item oak_apple_gall = new ModItem("oak_apple_gall");
	public static final Item spectral_dust = new ModItem("spectral_dust");
	public static final Item wood_ash = new ModItem("wood_ash");

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		for (Object obj : REGISTRY) {
			if (obj instanceof Block) event.getRegistry().register((Block) obj);
			if (obj instanceof BlockCandleBase) Bewitchment.proxy.ignoreProperty((Block) obj, BlockCandleBase.LIT);
		}
		Bewitchment.proxy.ignoreProperty(crop_kelp, BlockLiquid.LEVEL);
		Bewitchment.proxy.ignoreProperty(door_cypress.door, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_elder.door, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_juniper.door, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(door_yew.door, BlockDoor.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_cypress, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_elder, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_juniper, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(fence_gate_yew, BlockFenceGate.POWERED);
		Bewitchment.proxy.ignoreProperty(moonbell, BlockMoonbell.PLACED);
		Bewitchment.proxy.ignoreProperty(sapling_cypress, ModBlockSapling.READY);
		Bewitchment.proxy.ignoreProperty(sapling_elder, ModBlockSapling.READY);
		Bewitchment.proxy.ignoreProperty(sapling_juniper, ModBlockSapling.READY);
		Bewitchment.proxy.ignoreProperty(sapling_yew, ModBlockSapling.READY);
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		for (Object obj : REGISTRY) {
			if (obj instanceof Block) {
				Block block = (Block) obj;
				if (!(block instanceof BlockWitchFire) && !(block instanceof BlockWitchesLight) && !(block instanceof BlockGlyph) && !(block instanceof BlockSaltBarrier) && !(block instanceof BlockCrops) && !(block instanceof BlockDoor) && !(block instanceof BlockSlab) && !(block instanceof IFluidBlock)) {
					Item itemBlock = block instanceof BlockLantern ? new ItemLantern(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey()) : new ItemBlock(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey());
					event.getRegistry().register(itemBlock);
					Bewitchment.proxy.registerTexture(itemBlock, block instanceof ModBlockBush ? "inventory" : "normal");
				}
			}
			if (obj instanceof Item) {
				Item item = (Item) obj;
				event.getRegistry().register(item);
				if (obj == ModObjects.eye_of_old) Bewitchment.proxy.registerTextureEyeOfOld();
				else if (obj == ModObjects.sword_cold_iron) Bewitchment.proxy.registerTextureColdIronSword();
				else if (obj == ModObjects.waystone) Bewitchment.proxy.registerTextureWaystone();
				else Bewitchment.proxy.registerTexture(item, "normal");
			}
		}
	}

	private static final Block[] createChiselBlocks(Block base, String... names) {
		List<Block> list = new ArrayList<>();
		if (Loader.isModLoaded("chisel")) {
			String groupName = base.getRegistryName().toString();
			if (!groupName.contains("silver"))
				CarvingUtils.getChiselRegistry().addVariation(groupName, CarvingUtils.variationFor(base.getDefaultState(), 0));
			for (String name : names) {
				Block block = new ModBlockChisel(name, base);
				if (!groupName.contains("silver"))
					CarvingUtils.getChiselRegistry().addVariation(groupName, CarvingUtils.variationFor(block.getDefaultState(), list.size() + 1));
				list.add(block);
			}
		}
		return list.toArray(new Block[list.size()]);
	}

	private static final Fluid createFluid(String name, Material mat, int temperature, int luminosity, int density, int viscosity, boolean useBucket, boolean useFlowTexture) {
		if (!FluidRegistry.isFluidRegistered(name)) {
			Fluid fluid = new Fluid(name, new ResourceLocation(Bewitchment.MOD_ID, "blocks/fluid/" + name + "_still"), new ResourceLocation(Bewitchment.MOD_ID, "blocks/fluid/" + name + (useFlowTexture ? "_flowing" : "_still"))).setTemperature(temperature).setLuminosity(luminosity).setDensity(density).setViscosity(viscosity);
			FluidRegistry.registerFluid(fluid);
			Block block = new BlockFluidClassic(fluid, mat).setTemperature(temperature).setDensity(density).setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, "fluid_" + name)).setTranslationKey(fluid.getUnlocalizedName());
			fluid.setBlock(block);
			if (useBucket) FluidRegistry.addBucketForFluid(fluid);
			Bewitchment.proxy.registerTexture(fluid);
			ForgeRegistries.BLOCKS.register(block);
		}
		return FluidRegistry.getFluid(name);
	}

	private static final Block createTileEntity(Block block, Class<? extends TileEntity> tile) {
		GameRegistry.registerTileEntity(tile, block.getRegistryName());
		return block;
	}
}
package com.bewitchment.common;

import com.bewitchment.Bewitchment;
import com.bewitchment.ModConfig;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPowerCapability;
import com.bewitchment.api.capability.magicpower.MagicPowerHandler;
import com.bewitchment.api.capability.magicpower.MagicPowerStorage;
import com.bewitchment.api.capability.transformation.TransformationCapability;
import com.bewitchment.api.capability.transformation.TransformationHandler;
import com.bewitchment.api.capability.transformation.TransformationStorage;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.common.handler.BlockDropHandler;
import com.bewitchment.common.handler.EventHandler;
import com.bewitchment.common.handler.GuiHandler;
import com.bewitchment.common.world.gen.WorldGenCoquina;
import com.bewitchment.common.world.gen.WorldGenOres;
import com.bewitchment.registry.IOreDictionaryContainer;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{
	public enum ModGui
	{
		APIARY, DISTILLERY, OVEN, TAROT, THREAD_SPINNER;
	}
	
	public ModConfig config;
	
	public final CreativeTabs tab = new CreativeTabs(Bewitchment.MOD_ID)
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ModObjects.witches_altar);
		}
	};
	
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ModConfig(event.getSuggestedConfigurationFile());
		registerCapabilities();
	}
	
	public void init(FMLInitializationEvent event)
	{
		for (Block block : ForgeRegistries.BLOCKS) if (block instanceof IOreDictionaryContainer) for (String ore : ((IOreDictionaryContainer) block).getOreDictionaryNames()) OreDictionary.registerOre(ore, block);
		for (Item item : ForgeRegistries.ITEMS) if (item instanceof IOreDictionaryContainer) for (String ore : ((IOreDictionaryContainer) item).getOreDictionaryNames()) OreDictionary.registerOre(ore, item);
		NetworkRegistry.INSTANCE.registerGuiHandler(Bewitchment.instance, new GuiHandler());
		registerEventHandlers();
		registerRecipes();
		registerWorldGenerators();
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		registerAltarValues();
	}
	
	private void registerAltarValues()
	{
		for (final Block block : ForgeRegistries.BLOCKS)
		{
			if (!(block instanceof BlockGrass) && (block instanceof IPlantable || block instanceof IGrowable || block instanceof BlockMelon || block instanceof BlockPumpkin)) BewitchmentAPI.registerAltarScanValue(block, 30);
			if (block instanceof BlockLog) BewitchmentAPI.registerAltarScanValue(block, 15);
			if (block instanceof BlockLeaves) BewitchmentAPI.registerAltarScanValue(block, 8);
		}
		BewitchmentAPI.registerAltarSwordRadius(ModObjects.boline, 2);
		BewitchmentAPI.registerAltarSwordMultiplier(ModObjects.sword_silver, 1.275);
		BewitchmentAPI.registerAltarSwordRadius(ModObjects.sword_cold_iron, -1);
		BewitchmentAPI.registerAltarSwordMultiplier(ModObjects.sword_cold_iron, 1.425);
		BewitchmentAPI.registerAltarSwordRadius(ModObjects.sword_cold_iron, -1);
		BewitchmentAPI.registerAltarSwordMultiplier(ModObjects.athame, 1.625);
		BewitchmentAPI.registerAltarSwordRadius(ModObjects.athame, 1);
		BewitchmentAPI.registerAltarSwordMultiplier(Items.IRON_SWORD, 1.275);
		BewitchmentAPI.registerAltarSwordRadius(Items.IRON_SWORD, -1);
		BewitchmentAPI.registerAltarSwordMultiplier(Items.GOLDEN_SWORD, 1.325);
		BewitchmentAPI.registerAltarSwordRadius(Items.GOLDEN_SWORD, 2);
		BewitchmentAPI.registerAltarSwordMultiplier(Items.DIAMOND_SWORD, 1.575);
		BewitchmentAPI.registerAltarSwordRadius(Items.DIAMOND_SWORD, -2);
	}
	
	private void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(TransformationCapability.class, new TransformationStorage(), TransformationCapability::new);
		MinecraftForge.EVENT_BUS.register(new TransformationHandler());
		CapabilityManager.INSTANCE.register(MagicPowerCapability.class, new MagicPowerStorage(), MagicPowerCapability::new);
		MinecraftForge.EVENT_BUS.register(new MagicPowerHandler());
	}
	
	private void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new BlockDropHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	private void registerRecipes()
	{
		// Furnace
		GameRegistry.addSmelting(ModObjects.ore_silver, new ItemStack(ModObjects.ingot_silver), 0.35f);
		GameRegistry.addSmelting(Blocks.SAPLING, new ItemStack(ModObjects.wood_ash, 4), 0.15f);
		GameRegistry.addSmelting(Items.MELON, new ItemStack(ModObjects.grilled_watermelon), 0.45f);
		GameRegistry.addSmelting(ModObjects.ore_alexandrite, new ItemStack(ModObjects.gem_alexandrite), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_amethyst, new ItemStack(ModObjects.gem_amethyst), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_bloodstone, new ItemStack(ModObjects.gem_bloodstone), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_garnet, new ItemStack(ModObjects.gem_garnet), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_jasper, new ItemStack(ModObjects.gem_jasper), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_malachite, new ItemStack(ModObjects.gem_malachite), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_nuummite, new ItemStack(ModObjects.gem_nuummite), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_tigers_eye, new ItemStack(ModObjects.gem_tigers_eye), 0.35f);
		GameRegistry.addSmelting(ModObjects.ore_tourmaline, new ItemStack(ModObjects.gem_tourmaline), 0.35f);
		GameRegistry.addSmelting(ModObjects.golden_thread, new ItemStack(Items.GOLD_NUGGET), 1);
		GameRegistry.addSmelting(ModObjects.unfired_jar, new ItemStack(ModObjects.empty_jar), 0.45f);
		
		// Distillery
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("cleansing_balm",
				new ItemStack[] {new ItemStack(ModObjects.acacia_resin), new ItemStack(ModObjects.sagebrush), new ItemStack(ModObjects.tulsi), new ItemStack(ModObjects.white_sage)},
				new ItemStack[] {new ItemStack(ModObjects.cleansing_balm), new ItemStack(ModObjects.wood_ash)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("demonic_elixir",
				new ItemStack[] {new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModObjects.cloudy_oil), new ItemStack(ModObjects.demonic_heart), new ItemStack(ModObjects.graveyard_dust)},
				new ItemStack[] {new ItemStack(ModObjects.demonic_elixir) }, 0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("everchanging_dew",
				new ItemStack[] {new ItemStack(Items.DYE, 1, 32767), new ItemStack(ModObjects.essence_of_vitality), new ItemStack(Items.PAPER)},
				new ItemStack[] {new ItemStack(ModObjects.empty_jar), new ItemStack(ModObjects.everchanging_dew)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("fiery_unguent",
				new ItemStack[] {new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModObjects.cloudy_oil), new ItemStack(Blocks.OBSIDIAN), new ItemStack(ModObjects.wood_ash)},
				new ItemStack[] {new ItemStack(ModObjects.diabolical_vein, 2), new ItemStack(ModObjects.fiery_unguent)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("heaven_extract",
				new ItemStack[] {new ItemStack(ModObjects.birch_soul), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(ModObjects.gem_jasper), new ItemStack(ModObjects.quartz_powder)},
				new ItemStack[] {new ItemStack(ModObjects.heaven_extract)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("otherworldly_tears",
				new ItemStack[] {new ItemStack(ModObjects.birch_soul), new ItemStack(Items.ENDER_PEARL), new ItemStack(ModObjects.lapis_powder)},
				new ItemStack[] {new ItemStack(ModObjects.dimensional_sand, 2), new ItemStack(ModObjects.otherworldly_tears)},
				0, 600));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("philter_of_dishonesty",
				new ItemStack[] {new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModObjects.graveyard_dust), new ItemStack(ModObjects.liquid_witchcraft), new ItemStack(ModObjects.oak_apple_gall)},
				new ItemStack[] {new ItemStack(ModObjects.philter_of_dishonesty)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("stone_ichor",
				new ItemStack[] {new ItemStack(ModObjects.coquina), new ItemStack(ModObjects.liquid_witchcraft), new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.STONE)},
				new ItemStack[] {new ItemStack(ModObjects.stone_ichor)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("swirl_of_the_depths",
				new ItemStack[] {new ItemStack(ModObjects.coquina), new ItemStack(ModObjects.kelp), new ItemStack(ModObjects.lapis_powder), new ItemStack(ModObjects.otherworldly_tears)},
				new ItemStack[] {new ItemStack(Items.SLIME_BALL, 2), new ItemStack(ModObjects.swirl_of_the_depths)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("undying_salve",
				new ItemStack[] {new ItemStack(ModObjects.ectoplasm), new ItemStack(ModObjects.ebb_of_death), new ItemStack(ModObjects.essence_of_vitality)},
				new ItemStack[] {new ItemStack(ModObjects.ectoplasm, 2), new ItemStack(ModObjects.undying_salve)},
				0, 300));
		
		// Oven
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_0", new ItemStack(Blocks.SAPLING), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.oak_spirit), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_1", new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.spruce_heart), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_2", new ItemStack(Blocks.SAPLING, 1, 2), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.birch_soul), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_3", new ItemStack(Blocks.SAPLING, 1, 3), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_4", new ItemStack(Blocks.SAPLING, 1, 4), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.acacia_resin), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_5", new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.oak_spirit), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("rotten_flesh", new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.LEATHER), new ItemStack(ModObjects.ectoplasm, 3), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_0", new ItemStack(Blocks.LOG), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_1", new ItemStack(Blocks.LOG, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_2", new ItemStack(Blocks.LOG, 1, 2), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_3", new ItemStack(Blocks.LOG, 1, 3), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log2_0", new ItemStack(Blocks.LOG2), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log2_1", new ItemStack(Blocks.LOG2, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModObjects.wood_ash, 3), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("bone", new ItemStack(Items.BONE), new ItemStack(Items.DYE, 1, 15), new ItemStack(ModObjects.ectoplasm), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("wheat", new ItemStack(Items.WHEAT), new ItemStack(Items.BREAD), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("rabbit", new ItemStack(Items.RABBIT), new ItemStack(Items.COOKED_RABBIT), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("porkchop", new ItemStack(Items.PORKCHOP), new ItemStack(Items.COOKED_PORKCHOP), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("beef", new ItemStack(Items.BEEF), new ItemStack(Items.COOKED_BEEF), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("chicken", new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("fish_0", new ItemStack(Items.FISH), new ItemStack(Items.COOKED_FISH), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("fish_1", new ItemStack(Items.COOKED_FISH, 1, 1), new ItemStack(Items.COOKED_FISH), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("melon", new ItemStack(Items.MELON), new ItemStack(ModObjects.grilled_watermelon), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("cactus", new ItemStack(Blocks.CACTUS), new ItemStack(Items.DYE, 1, 2), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("chorus_fruit", new ItemStack(Items.CHORUS_FRUIT), new ItemStack(Items.CHORUS_FRUIT_POPPED), new ItemStack(ModObjects.dimensional_sand, 2), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("mandrake_root", new ItemStack(ModObjects.mandrake_root), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("unfired_jar", new ItemStack(ModObjects.unfired_jar), new ItemStack(ModObjects.empty_jar), new ItemStack(ModObjects.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_6", new ItemStack(ModObjects.sapling_cypress), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.ebb_of_death), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_7", new ItemStack(ModObjects.sapling_elder), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.droplet_of_wisdom), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_8", new ItemStack(ModObjects.sapling_juniper), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.liquid_witchcraft), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_9", new ItemStack(ModObjects.sapling_yew), new ItemStack(ModObjects.wood_ash), new ItemStack(ModObjects.essence_of_vitality), 0.85f));
		
		// Tool Repair
		ModObjects.TOOL_COLD_IRON.setRepairItem(new ItemStack(ModObjects.ingot_cold_iron));
		ModObjects.TOOL_SILVER.setRepairItem(new ItemStack(ModObjects.ingot_silver));
		ModObjects.ARMOR_COLD_IRON.setRepairItem(new ItemStack(ModObjects.ingot_cold_iron));
		ModObjects.ARMOR_SILVER.setRepairItem(new ItemStack(ModObjects.ingot_silver));
		ModObjects.TOOL_RITUAL.setRepairItem(new ItemStack(ModObjects.ingot_silver));
	}
	
	private void registerWorldGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCoquina(), 0);
	}
	
	@SuppressWarnings("deprecation")
	public void registerValues(Block block, String name, Block base, String... ore_dictionary_names)
	{
		registerValues(block, name, base.getDefaultState().getMaterial(), base.getSoundType(), base.getBlockHardness(null, null, null), base.getExplosionResistance(null) * 5, base.getHarvestTool(base.getDefaultState()), base.getHarvestLevel(base.getDefaultState()), ore_dictionary_names);
	}
	
	@SuppressWarnings("deprecation")
	public void registerValues(Block block, String name, Material mat, SoundType sound, float hardness, float resistance, String tool, int level, String... ore_dictionary_names)
	{
		block.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		block.setTranslationKey(block.getRegistryName().toString());
		block.setCreativeTab(tab);
		ObfuscationReflectionHelper.setPrivateValue(Block.class, block, sound, 16);
		block.setHardness(hardness);
		block.setResistance(resistance);
		block.setHarvestLevel(tool, level);
		if (mat == Material.CARPET) Blocks.FIRE.setFireInfo(block, 60, 20);
		if (mat == Material.CLOTH || mat == Material.LEAVES) Blocks.FIRE.setFireInfo(block, 30, 60);
		if (mat == Material.PLANTS) Blocks.FIRE.setFireInfo(block, 60, 100);
		if (mat == Material.TNT || mat == Material.VINE) Blocks.FIRE.setFireInfo(block, 15, 100);
		if (mat == Material.WOOD) Blocks.FIRE.setFireInfo(block, 5, 20);
		if (mat == Material.ICE) block.setDefaultSlipperiness(0.98f);
		if (block instanceof IOreDictionaryContainer) for (String ore : ore_dictionary_names) ((IOreDictionaryContainer) block).getOreDictionaryNames().add(ore);
		ModObjects.REGISTRY.add(block);
	}
	
	public void registerValues(Item item, String name, String... ore_dictionary_names)
	{
		item.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		item.setTranslationKey(item.getRegistryName().toString());
		item.setCreativeTab(tab);
		if (item instanceof IOreDictionaryContainer) for (String ore : ore_dictionary_names) ((IOreDictionaryContainer) item).getOreDictionaryNames().add(ore);
		ModObjects.REGISTRY.add(item);
	}
	
	public boolean isFancyGraphicsEnabled()
	{
		return false;
	}
	
	public void ignoreProperty(Block block, IProperty<?>... properties)
	{
	}
	
	public void registerTexture(Item item)
	{
	}
	
	public void registerTexture(Fluid fluid)
	{
	}
}
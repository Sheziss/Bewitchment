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
import com.bewitchment.api.recipe.DistilleryRecipe;
import com.bewitchment.api.recipe.OvenRecipe;
import com.bewitchment.common.entity.EntityBlindworm;
import com.bewitchment.common.entity.EntityLizard;
import com.bewitchment.common.entity.EntityNewt;
import com.bewitchment.common.entity.EntityOwl;
import com.bewitchment.common.handler.BlockDropHandler;
import com.bewitchment.common.handler.EventHandler;
import com.bewitchment.common.handler.GuiHandler;
import com.bewitchment.common.registry.IOreName;
import com.bewitchment.common.registry.ModBlocks;
import com.bewitchment.common.registry.ModItems;
import com.bewitchment.common.world.gen.WorldGenCoquina;
import com.bewitchment.common.world.gen.WorldGenOres;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{
	public ModConfig config;
	
	public enum ModGui
	{
		APIARY, DISTILLERY, OVEN, TAROT, THREAD_SPINNER;
	}
	
	public final CreativeTabs tab = new CreativeTabs(Bewitchment.MOD_ID)
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ModBlocks.log_elder);
		}
	};
	
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new ModConfig(event.getSuggestedConfigurationFile());
		registerEntities();
		registerCapabilities();
	}
	
	public void init(FMLInitializationEvent event)
	{
		for (Block block : ModBlocks.REGISTRY) if (block instanceof IOreName) for (String ore : ((IOreName) block).getOreNames()) OreDictionary.registerOre(ore, block);
		for (Item item : ModItems.REGISTRY) if (item instanceof IOreName) for (String ore : ((IOreName) item).getOreNames()) OreDictionary.registerOre(ore, item);
		ModBlocks.crop_aconitum.setSeed(ModItems.seed_aconitum).setCrop(ModItems.aconitum);
		ModBlocks.crop_asphodel.setSeed(ModItems.seed_asphodel).setCrop(ModItems.asphodel);
		ModBlocks.crop_belladonna.setSeed(ModItems.seed_belladonna).setCrop(ModItems.belladonna);
		ModBlocks.crop_chrysanthemum.setSeed(ModItems.seed_chrysanthemum).setCrop(ModItems.chrysanthemum);
		ModBlocks.crop_garlic.setSeed(ModItems.seed_garlic).setCrop(ModItems.garlic);
		ModBlocks.crop_ginger.setSeed(ModItems.seed_ginger).setCrop(ModItems.ginger);
		ModBlocks.crop_hellebore.setSeed(ModItems.seed_hellebore).setCrop(ModItems.hellebore);
		ModBlocks.crop_kelp.setSeed(ModItems.seed_kelp).setCrop(ModItems.kelp);
		ModBlocks.crop_kenaf.setSeed(ModItems.seed_kenaf).setCrop(ModItems.kenaf);
		ModBlocks.crop_lavender.setSeed(ModItems.seed_lavender).setCrop(ModItems.lavender);
		ModBlocks.crop_mandrake.setSeed(ModItems.seed_mandrake).setCrop(ModItems.mandrake_root);
		ModBlocks.crop_mint.setSeed(ModItems.seed_mint).setCrop(ModItems.mint);
		ModBlocks.crop_sagebrush.setSeed(ModItems.seed_sagebrush).setCrop(ModItems.sagebrush);
		ModBlocks.crop_silphium.setSeed(ModItems.seed_silphium).setCrop(ModItems.silphium);
		ModBlocks.crop_thistle.setSeed(ModItems.seed_thistle).setCrop(ModItems.thistle);
		ModBlocks.crop_tulsi.setSeed(ModItems.seed_tulsi).setCrop(ModItems.tulsi);
		ModBlocks.crop_white_sage.setSeed(ModItems.seed_white_sage).setCrop(ModItems.white_sage);
		ModBlocks.crop_wormwood.setSeed(ModItems.seed_wormwood).setCrop(ModItems.wormwood);
		ModBlocks.leaves_cypress.drop = Item.getItemFromBlock(ModBlocks.sapling_cypress);
		ModBlocks.leaves_elder.drop = Item.getItemFromBlock(ModBlocks.sapling_elder);
		ModBlocks.leaves_juniper.drop = Item.getItemFromBlock(ModBlocks.sapling_juniper);
		ModBlocks.leaves_yew.drop = Item.getItemFromBlock(ModBlocks.sapling_yew);
		ModBlocks.registerFluids();
		NetworkRegistry.INSTANCE.registerGuiHandler(Bewitchment.instance, new GuiHandler());
		registerEventHandlers();
		registerRecipes();
		registerWorldGenerators();
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		registerAltarValues();
	}
	
	private void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(TransformationCapability.class, new TransformationStorage(), TransformationCapability::new);
		MinecraftForge.EVENT_BUS.register(new TransformationHandler());
		CapabilityManager.INSTANCE.register(MagicPowerCapability.class, new MagicPowerStorage(), MagicPowerCapability::new);
		MinecraftForge.EVENT_BUS.register(new MagicPowerHandler());
	}
	
	protected void registerEntities()
	{
		int id = 0;
		EntityRegistry.registerModEntity(new ResourceLocation(Bewitchment.MOD_ID, "blindworm"), EntityBlindworm.class, "blindworm", id++, Bewitchment.instance, 64, 1, true, 0x826644, 0xD2B48C);
		EntityRegistry.registerModEntity(new ResourceLocation(Bewitchment.MOD_ID, "lizard"), EntityLizard.class, "lizard", id++, Bewitchment.instance, 64, 1, true, 0x568203, 0x0070BB);
		EntityRegistry.registerModEntity(new ResourceLocation(Bewitchment.MOD_ID, "newt"), EntityNewt.class, "newt", id++, Bewitchment.instance, 64, 1, true, 0x000000, 0xFFD300);
		EntityRegistry.registerModEntity(new ResourceLocation(Bewitchment.MOD_ID, "owl"), EntityOwl.class, "owl", id++, Bewitchment.instance, 64, 1, true, 0xAF813F, 0x6E5127);

		for (Biome biome : Biome.REGISTRY)
		{
			if (BiomeDictionary.hasType(biome, Type.FOREST))
			{
				EntityRegistry.addSpawn(EntityBlindworm.class, 20, 1, 4, EnumCreatureType.CREATURE, biome);
				EntityRegistry.addSpawn(EntityLizard.class, 20, 1, 4, EnumCreatureType.CREATURE, biome);
				if (BiomeDictionary.hasType(biome, Type.DENSE)) EntityRegistry.addSpawn(EntityOwl.class, 20, 1, 4, EnumCreatureType.CREATURE, biome);
			}
			if (BiomeDictionary.hasType(biome, Type.SWAMP))
			{
				EntityRegistry.addSpawn(EntityNewt.class, 20, 1, 4, EnumCreatureType.CREATURE, biome);
			}
		}
	}
	
	private void registerAltarValues()
	{
		for (Block block : ForgeRegistries.BLOCKS)
		{
			if (!(block instanceof BlockGrass) && (block instanceof IPlantable || block instanceof IGrowable || block instanceof BlockMelon || block instanceof BlockPumpkin)) BewitchmentAPI.registerAltarScanValue(block, 30);
			if (block instanceof BlockLog) BewitchmentAPI.registerAltarScanValue(block, 15);
			if (block instanceof BlockLeaves) BewitchmentAPI.registerAltarScanValue(block, 8);
		}
		BewitchmentAPI.registerAltarSwordUpgrade(ModItems.sword_silver, 1.275);
		BewitchmentAPI.registerAltarSwordUpgrade(ModItems.sword_cold_iron, 1.425);
		BewitchmentAPI.registerAltarSwordUpgrade(ModItems.athame, 1.625);
		BewitchmentAPI.registerAltarSwordUpgrade(Items.IRON_SWORD, 1.275);
		BewitchmentAPI.registerAltarSwordUpgrade(Items.GOLDEN_SWORD, 1.325);
		BewitchmentAPI.registerAltarSwordUpgrade(Items.DIAMOND_SWORD, 1.575);
	}
	
	private void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new BlockDropHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	private void registerRecipes()
	{
		//Furnace
		GameRegistry.addSmelting(ModBlocks.ore_silver, new ItemStack(ModItems.ingot_silver), 0.35f);
		GameRegistry.addSmelting(Blocks.SAPLING, new ItemStack(ModItems.wood_ash, 4), 0.15f);
		GameRegistry.addSmelting(Items.MELON, new ItemStack(ModItems.grilled_watermelon), 0.45f);
		GameRegistry.addSmelting(ModBlocks.ore_alexandrite, new ItemStack(ModItems.gem_alexandrite), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_amethyst, new ItemStack(ModItems.gem_amethyst), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_bloodstone, new ItemStack(ModItems.gem_bloodstone), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_garnet, new ItemStack(ModItems.gem_garnet), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_jasper, new ItemStack(ModItems.gem_jasper), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_malachite, new ItemStack(ModItems.gem_malachite), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_nuummite, new ItemStack(ModItems.gem_nuummite), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_tigers_eye, new ItemStack(ModItems.gem_tigers_eye), 0.35f);
		GameRegistry.addSmelting(ModBlocks.ore_tourmaline, new ItemStack(ModItems.gem_tourmaline), 0.35f);
		GameRegistry.addSmelting(ModItems.golden_thread, new ItemStack(Items.GOLD_NUGGET), 1);
		GameRegistry.addSmelting(ModItems.unfired_jar, new ItemStack(ModItems.empty_jar), 0.45f);
		
		//Distillery
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("cleansing_balm",
				new ItemStack[]{new ItemStack(ModItems.acacia_resin), new ItemStack(ModItems.sagebrush), new ItemStack(ModItems.tulsi), new ItemStack(ModItems.white_sage)},
				new ItemStack[]{new ItemStack(ModItems.cleansing_balm), new ItemStack(ModItems.wood_ash)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("demonic_elixir",
				new ItemStack[]{new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.cloudy_oil), new ItemStack(ModItems.demonic_heart), new ItemStack(ModItems.graveyard_dust)},
				new ItemStack[]{new ItemStack(ModItems.demonic_elixir)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("everchanging_dew",
				new ItemStack[]{new ItemStack(Items.DYE, 1, 32767), new ItemStack(ModItems.essence_of_vitality), new ItemStack(Items.PAPER)},
				new ItemStack[]{new ItemStack(ModItems.empty_jar), new ItemStack(ModItems.everchanging_dew)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("fiery_unguent",
				new ItemStack[]{new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.cloudy_oil), new ItemStack(Blocks.OBSIDIAN), new ItemStack(ModItems.wood_ash)},
				new ItemStack[]{new ItemStack(ModItems.diabolical_vein, 2), new ItemStack(ModItems.fiery_unguent)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("heaven_extract",
				new ItemStack[]{new ItemStack(ModItems.birch_soul), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(ModItems.gem_jasper), new ItemStack(ModItems.quartz_powder)},
				new ItemStack[]{new ItemStack(ModItems.heaven_extract)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("otherworldly_tears",
				new ItemStack[]{new ItemStack(ModItems.birch_soul), new ItemStack(Items.ENDER_PEARL), new ItemStack(ModItems.lapis_powder)},
				new ItemStack[]{new ItemStack(ModItems.dimensional_sand, 2), new ItemStack(ModItems.otherworldly_tears)},
				0, 600));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("philter_of_dishonesty",
				new ItemStack[]{new ItemStack(Items.BLAZE_POWDER), new ItemStack(ModItems.graveyard_dust), new ItemStack(ModItems.liquid_witchcraft), new ItemStack(ModItems.oak_apple_gall)},
				new ItemStack[]{new ItemStack(ModItems.philter_of_dishonesty)},
				0, 300));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("stone_ichor",
				new ItemStack[]{new ItemStack(ModBlocks.coquina), new ItemStack(ModItems.liquid_witchcraft), new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.STONE)},
				new ItemStack[]{new ItemStack(ModItems.stone_ichor)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("swirl_of_the_depths",
				new ItemStack[]{new ItemStack(ModBlocks.coquina), new ItemStack(ModItems.kelp), new ItemStack(ModItems.lapis_powder), new ItemStack(ModItems.otherworldly_tears)},
				new ItemStack[]{new ItemStack(Items.SLIME_BALL, 2), new ItemStack(ModItems.swirl_of_the_depths)},
				0, 900));
		BewitchmentAPI.registerDistilleryRecipe(new DistilleryRecipe("undying_salve",
				new ItemStack[]{new ItemStack(ModItems.ectoplasm), new ItemStack(ModItems.ebb_of_death), new ItemStack(ModItems.essence_of_vitality)},
				new ItemStack[]{new ItemStack(ModItems.ectoplasm, 2), new ItemStack(ModItems.undying_salve)},
				0, 300));
		
		//Oven
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_0", new ItemStack(Blocks.SAPLING), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.oak_spirit), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_1", new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.spruce_heart), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_2", new ItemStack(Blocks.SAPLING, 1, 2), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.birch_soul), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_3", new ItemStack(Blocks.SAPLING, 1, 3), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_4", new ItemStack(Blocks.SAPLING, 1, 4), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.acacia_resin), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_5", new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.oak_spirit), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("rotten_flesh", new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.LEATHER), new ItemStack(ModItems.ectoplasm, 3), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_0", new ItemStack(Blocks.LOG), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_1", new ItemStack(Blocks.LOG, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_2", new ItemStack(Blocks.LOG, 1, 2), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log_3", new ItemStack(Blocks.LOG, 1, 3), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log2_0", new ItemStack(Blocks.LOG2), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("log2_1", new ItemStack(Blocks.LOG2, 1, 1), new ItemStack(Items.COAL, 1, 1), new ItemStack(ModItems.wood_ash, 3), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("bone", new ItemStack(Items.BONE), new ItemStack(Items.DYE, 1, 15), new ItemStack(ModItems.ectoplasm), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("wheat", new ItemStack(Items.WHEAT), new ItemStack(Items.BREAD), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("rabbit", new ItemStack(Items.RABBIT), new ItemStack(Items.COOKED_RABBIT), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("porkchop", new ItemStack(Items.PORKCHOP), new ItemStack(Items.COOKED_PORKCHOP), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("beef", new ItemStack(Items.BEEF), new ItemStack(Items.COOKED_BEEF), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("chicken", new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("fish_0", new ItemStack(Items.FISH), new ItemStack(Items.COOKED_FISH), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("fish_1", new ItemStack(Items.COOKED_FISH, 1, 1), new ItemStack(Items.COOKED_FISH), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("melon", new ItemStack(Items.MELON), new ItemStack(ModItems.grilled_watermelon), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("cactus", new ItemStack(Blocks.CACTUS), new ItemStack(Items.DYE, 1, 2), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("chorus_fruit", new ItemStack(Items.CHORUS_FRUIT), new ItemStack(Items.CHORUS_FRUIT_POPPED), new ItemStack(ModItems.dimensional_sand, 2), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("mandrake_root", new ItemStack(ModItems.mandrake_root), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.cloudy_oil), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("unfired_jar", new ItemStack(ModItems.unfired_jar), new ItemStack(ModItems.empty_jar), new ItemStack(ModItems.wood_ash), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_6", new ItemStack(ModBlocks.sapling_cypress), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.ebb_of_death), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_7", new ItemStack(ModBlocks.sapling_elder), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.droplet_of_wisdom), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_8", new ItemStack(ModBlocks.sapling_juniper), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.liquid_witchcraft), 0.85f));
		BewitchmentAPI.registerOvenRecipe(new OvenRecipe("sapling_9", new ItemStack(ModBlocks.sapling_yew), new ItemStack(ModItems.wood_ash), new ItemStack(ModItems.essence_of_vitality), 0.85f));

		//Tool Repair
		ModItems.TOOL_COLD_IRON.setRepairItem(new ItemStack(ModItems.ingot_cold_iron));
		ModItems.TOOL_SILVER.setRepairItem(new ItemStack(ModItems.ingot_silver));
		ModItems.ARMOR_COLD_IRON.setRepairItem(new ItemStack(ModItems.ingot_cold_iron));
		ModItems.ARMOR_SILVER.setRepairItem(new ItemStack(ModItems.ingot_silver));
		ModItems.TOOL_RITUAL.setRepairItem(new ItemStack(ModItems.ingot_silver));
	}
	
	private void registerWorldGenerators()
	{
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCoquina(), 0);
	}
	
	public void registerTexture(Item item)
	{
	}
	
	public void registerTexture(Fluid fluid)
	{
	}
	
	public boolean isFancyGraphicsEnabled()
	{
		return false;
	}
	
	public void ignoreProperty(Block block, IProperty<?>... property)
	{
	}
}
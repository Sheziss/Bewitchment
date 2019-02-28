package com.bewitchment.core;

import com.bewitchment.registry.IOreName;
import com.bewitchment.registry.ModBlocks;
import com.bewitchment.registry.ModItems;
import com.bewitchment.registry.capability.IMagicPower;
import com.bewitchment.registry.capability.ITransformation;
import com.bewitchment.registry.gen.WorldGenCoquina;
import com.bewitchment.registry.gen.WorldGenOres;
import com.bewitchment.registry.handler.BlockDropHandler;
import com.bewitchment.registry.handler.EventHandler;
import com.bewitchment.registry.handler.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{
	public ModConfig config;
	
	public EnumCreatureAttribute DEMON, SPIRIT;
	
	public enum ModGui
	{
		APIARY, OVEN, THREAD_SPINNER, TAROT, DISTILLERY;
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
		this.config = new ModConfig(event.getSuggestedConfigurationFile());
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
		this.registerEventHandlers();
		this.registerRecipes();
		this.registerWorldGenerators();
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
	}
	
	private void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(ITransformation.class, new ITransformation.Storage(), ITransformation.Cap::new);
		MinecraftForge.EVENT_BUS.register(new ITransformation.Handler());
		CapabilityManager.INSTANCE.register(IMagicPower.class, new IMagicPower.Storage(), IMagicPower.Cap::new);
		MinecraftForge.EVENT_BUS.register(new IMagicPower.Handler());
	}
	
	private void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new BlockDropHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	private void registerRecipes()
	{
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
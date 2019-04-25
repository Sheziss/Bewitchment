package com.bewitchment.common;

import com.bewitchment.Bewitchment;
import com.bewitchment.ModConfig;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayerHandler;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.handler.BlockDropHandler;
import com.bewitchment.common.handler.EventHandler;
import com.bewitchment.common.handler.GuiHandler;
import com.bewitchment.common.integration.patchouli.BewitchmentPatchouli;
import com.bewitchment.common.integration.thaumcraft.ThaumcraftCompat;
import com.bewitchment.common.world.gen.WorldGenCoquina;
import com.bewitchment.common.world.gen.WorldGenOres;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModRecipes;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.function.Predicate;

public class CommonProxy {
	public static final SimpleNetworkWrapper WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(Bewitchment.MODID);
	public final CreativeTabs tab = new CreativeTabs(Bewitchment.MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModObjects.witches_altar_red);
		}
	};
	public ModConfig config;

	public void preInit(FMLPreInitializationEvent event) {
		config = new ModConfig(event.getSuggestedConfigurationFile());
		// Capability
		CapabilityManager.INSTANCE.register(ExtendedPlayer.class, new ExtendedPlayer(), ExtendedPlayer::new);
		MinecraftForge.EVENT_BUS.register(new ExtendedPlayerHandler());
		CapabilityManager.INSTANCE.register(MagicPower.class, new MagicPower(), MagicPower::new);
	}

	public void init(FMLInitializationEvent event) {
		for (Block block : ForgeRegistries.BLOCKS)
			if (block instanceof IOreDictionaryContainer)
				for (String ore : ((IOreDictionaryContainer) block).getOreDictionaryNames())
					OreDictionary.registerOre(ore, block);
		for (Item item : ForgeRegistries.ITEMS)
			if (item instanceof IOreDictionaryContainer)
				for (String ore : ((IOreDictionaryContainer) item).getOreDictionaryNames())
					OreDictionary.registerOre(ore, item);
		// Handlers
		NetworkRegistry.INSTANCE.registerGuiHandler(Bewitchment.instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new BlockDropHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		if (Loader.isModLoaded("thaumcraft")) MinecraftForge.EVENT_BUS.register(new ThaumcraftCompat());

		ModRecipes.initFurnace();
		ModRecipes.initDistillery();
		ModRecipes.initLoom();
		ModRecipes.initOven();
		ModRecipes.initRitual();
		ModRecipes.initFortune();

		// Tool Repair
		ModObjects.TOOL_COLD_IRON.setRepairItem(new ItemStack(ModObjects.cold_iron_ingot));
		ModObjects.TOOL_SILVER.setRepairItem(new ItemStack(ModObjects.silver_ingot));
		ModObjects.ARMOR_COLD_IRON.setRepairItem(new ItemStack(ModObjects.cold_iron_ingot));
		ModObjects.ARMOR_SILVER.setRepairItem(new ItemStack(ModObjects.silver_ingot));
		ModObjects.ARMOR_BEWITCHED_LEATHER.setRepairItem(new ItemStack(ModObjects.witches_stitching));
		ModObjects.ARMOR_VAMPIRE.setRepairItem(new ItemStack(ModObjects.sanguine_fabric));

		// World
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCoquina(), 0);

		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_aconitum), 3);
		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_belladonna), 3);
		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_chrysanthemum), 3);
		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_hellebore), 3);
		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_mandrake), 3);
		MinecraftForge.addGrassSeed(new ItemStack(ModObjects.seed_wormwood), 3);

		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "chests/books"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "chests/materials"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "chests/saplings"));

		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/blindworm"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/lizard"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/newt"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/owl"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/snake"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/raven"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/toad"));

		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/black_dog"));

		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/hellhound"));
		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/alpha_hellhound"));

		LootTableList.register(new ResourceLocation(Bewitchment.MODID, "entities/demon"));

		BewitchmentPatchouli.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModRecipes.postInitOven();
		ModRecipes.postInitAthame();
		// Altar Values
		for (final Block block : ForgeRegistries.BLOCKS) {
			int amount = 0;
			if (block instanceof IPlantable || block instanceof IGrowable || block instanceof BlockMelon || block instanceof BlockPumpkin)
				amount = 30;
			if (block instanceof BlockLog) amount = 15;
			if (block instanceof BlockLeaves) amount = 8;
			BewitchmentAPI.ALTAR_NATURE_VALUES.put(block, amount);
		}
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(ModObjects.silver_sword, 1.275);
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(ModObjects.cold_iron_sword, 1.425);
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(ModObjects.athame, 1.625);
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(Items.IRON_SWORD, 1.275);
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(Items.GOLDEN_SWORD, 1.325);
		BewitchmentAPI.ALTAR_GAIN_VALUES.put(Items.DIAMOND_SWORD, 1.575);
	}

	public boolean isFancyGraphicsEnabled() {
		return false;
	}

	public void ignoreProperty(Block block, IProperty<?>... properties) {
	}

	public void registerTexture(Item item, String variant) {
	}

	public void registerTextureWithVariant(Item item, List<Predicate<ItemStack>> predicates) {
	}

	public void registerTexture(Fluid fluid) {
	}

	public enum ModGui {
		DISTILLERY, LOOM, OVEN, TAROT;
	}
}
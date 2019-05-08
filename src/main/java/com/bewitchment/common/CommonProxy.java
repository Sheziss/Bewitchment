package com.bewitchment.common;

import com.bewitchment.Bewitchment;
import com.bewitchment.ModConfig;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayerHandler;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.BlockCandle;
import com.bewitchment.common.block.BlockLantern;
import com.bewitchment.common.handler.BlockDropHandler;
import com.bewitchment.common.handler.EventHandler;
import com.bewitchment.common.handler.GuiHandler;
import com.bewitchment.common.integration.patchouli.BewitchmentPatchouli;
import com.bewitchment.common.integration.thaumcraft.BewitchmentThaumcraft;
import com.bewitchment.common.world.gen.WorldGenerators;
import com.bewitchment.registry.*;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
		ModEntities.preInit();
		ModObjects.preInit();
		ModEnchantments.preInit();
		ModSounds.preInit();
		ModPotions.preInit();
		ModRecipes.preInitFurnace();
		ModRecipes.preInitDistillery();
		ModRecipes.preInitLoom();
		ModRecipes.preInitOven();
		ModRecipes.preInitRitual();
		ModRecipes.preInitFortune();
		ModRecipes.preInitCauldron();
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
		if (Loader.isModLoaded("thaumcraft")) MinecraftForge.EVENT_BUS.register(new BewitchmentThaumcraft());
		BewitchmentPatchouli.init();

		// Tool Repair
		ModObjects.TOOL_COLD_IRON.setRepairItem(new ItemStack(ModObjects.cold_iron_ingot));
		ModObjects.TOOL_SILVER.setRepairItem(new ItemStack(ModObjects.silver_ingot));
		ModObjects.ARMOR_COLD_IRON.setRepairItem(new ItemStack(ModObjects.cold_iron_ingot));
		ModObjects.ARMOR_SILVER.setRepairItem(new ItemStack(ModObjects.silver_ingot));
		ModObjects.ARMOR_BEWITCHED_LEATHER.setRepairItem(new ItemStack(ModObjects.witches_stitching));
		ModObjects.ARMOR_VAMPIRE.setRepairItem(new ItemStack(ModObjects.sanguine_fabric));

		// World
		GameRegistry.registerWorldGenerator(new WorldGenerators(), 0);

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
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModRecipes.postInitOven();
		ModRecipes.postInitAthame();
		// Altar Values
		for (Block block : ForgeRegistries.BLOCKS) {
			BewitchmentAPI.NaturePower power = BewitchmentAPI.NaturePower.NONE;
			if (block instanceof IPlantable || block instanceof IGrowable || block instanceof BlockMelon || block instanceof BlockPumpkin)
				power = BewitchmentAPI.NaturePower.STRONG;
			if (block instanceof BlockLog) power = BewitchmentAPI.NaturePower.NORMAL;
			if (block instanceof BlockLeaves) power = BewitchmentAPI.NaturePower.WEAK;
			if (power != BewitchmentAPI.NaturePower.NONE) BewitchmentAPI.ALTAR_NATURE_VALUES.put(block, power);

			if (block instanceof BlockTorch || block instanceof BlockCandle)
				BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.WAND, block, 1);
			if (block instanceof BlockLantern)
				BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.WAND, block, 2);
		}
		BewitchmentAPI.registerAltarUpgradeMultiplier(BewitchmentAPI.UpgradeType.SWORD, ModObjects.athame, 0.8d);
		BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.CUP, ModObjects.goblet, 1);
		BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.CUP, ModObjects.filled_goblet, 2);
		BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.PENTACLE, Blocks.SKULL, 2);
		BewitchmentAPI.registerAltarUpgradeGain(BewitchmentAPI.UpgradeType.PENTACLE, ModObjects.pentacle, -1);
		BewitchmentAPI.registerAltarUpgradeMultiplier(BewitchmentAPI.UpgradeType.PENTACLE, ModObjects.pentacle, 1.2d);
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
		DISTILLERY, LOOM, OVEN, TAROT
	}
}
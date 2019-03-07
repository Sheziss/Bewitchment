package com.bewitchment.common.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockSaltBarrier;
import com.bewitchment.common.block.util.ModBlockSlab;
import com.bewitchment.common.item.ItemAthame;
import com.bewitchment.common.item.ItemBoline;
import com.bewitchment.common.item.ItemSalt;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.common.item.util.ModItemArmor;
import com.bewitchment.common.item.util.ModItemAxe;
import com.bewitchment.common.item.util.ModItemDoor;
import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.common.item.util.ModItemHoe;
import com.bewitchment.common.item.util.ModItemPickaxe;
import com.bewitchment.common.item.util.ModItemSeed;
import com.bewitchment.common.item.util.ModItemSpade;
import com.bewitchment.common.item.util.ModItemSword;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModItems
{
	public static final List<Item> REGISTRY = new ArrayList<Item>();
	
	public static final ArmorMaterial ARMOR_COLD_IRON = EnumHelper.addArmorMaterial("cold_iron", Bewitchment.MOD_ID + ":" + "cold_iron", 18, new int[]{2, 6, 7, 2}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.45F);
	public static final ArmorMaterial ARMOR_SILVER = EnumHelper.addArmorMaterial("silver", Bewitchment.MOD_ID + ":" + "silver", 12, new int[]{1, 4, 5, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F);
	
	public static final ToolMaterial TOOL_COLD_IRON = EnumHelper.addToolMaterial("cold_iron", 2, 850, 7, 3, 8);
	public static final ToolMaterial TOOL_SILVER = EnumHelper.addToolMaterial("silver", 1, 215, 10, 2.5f, 24);

	public static final ArmorMaterial ARMOR_BEWITCHED_LEATHER = EnumHelper.addArmorMaterial("bewitched_leather", Bewitchment.MOD_ID + ":" + "bewitched_leather", 24, new int[]{1, 4, 5, 1}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F);
	public static final ArmorMaterial ARMOR_VAMPIRE = EnumHelper.addArmorMaterial("vampire", Bewitchment.MOD_ID + ":" + "vampire", 9, new int[]{2, 6, 7, 1}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.25F);

	public static final ToolMaterial TOOL_RITUAL = EnumHelper.addToolMaterial("chalk", 2, 300, 2F, 1.5F, 30);
	
	//Door
	public static final Item door_cypress = new ModItemDoor("door_cypress", ModBlocks.door_block_cypress);
	public static final Item door_elder = new ModItemDoor("door_elder", ModBlocks.door_block_elder);
	public static final Item door_juniper = new ModItemDoor("door_juniper", ModBlocks.door_block_juniper);
	public static final Item door_yew = new ModItemDoor("door_yew", ModBlocks.door_block_yew);
	
	//Armor
	public static final Item helmet_cold_iron = new ModItemArmor.ColdIron("helmet_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.HEAD);
	public static final Item chestplate_cold_iron = new ModItemArmor.ColdIron("chestplate_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.CHEST);
	public static final Item leggings_cold_iron = new ModItemArmor.ColdIron("leggings_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.LEGS);
	public static final Item boots_cold_iron = new ModItemArmor.ColdIron("boots_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.FEET);
	
	public static final Item helmet_silver = new ModItemArmor.Silver("helmet_silver", ARMOR_SILVER, EntityEquipmentSlot.HEAD);
	public static final Item chestplate_silver = new ModItemArmor.Silver("chestplate_silver", ARMOR_SILVER, EntityEquipmentSlot.CHEST);
	public static final Item leggings_silver = new ModItemArmor.Silver("leggings_silver", ARMOR_SILVER, EntityEquipmentSlot.LEGS);
	public static final Item boots_silver = new ModItemArmor.Silver("boots_silver", ARMOR_SILVER, EntityEquipmentSlot.FEET);
	
	//Tools
	public static final Item sword_cold_iron = new ModItemSword.ColdIron("sword_cold_iron", TOOL_COLD_IRON);
	public static final Item pickaxe_cold_iron = new ModItemPickaxe.ColdIron("pickaxe_cold_iron", TOOL_COLD_IRON);
	public static final Item axe_cold_iron = new ModItemAxe.ColdIron("axe_cold_iron", TOOL_COLD_IRON);
	public static final Item shovel_cold_iron = new ModItemSpade.ColdIron("shovel_cold_iron", TOOL_COLD_IRON);
	public static final Item hoe_cold_iron = new ModItemHoe.ColdIron("hoe_cold_iron", TOOL_COLD_IRON);
	
	public static final Item sword_silver = new ModItemSword.Silver("sword_silver", TOOL_SILVER);
	public static final Item pickaxe_silver = new ModItemPickaxe.Silver("pickaxe_silver", TOOL_SILVER);
	public static final Item axe_silver = new ModItemAxe.Silver("axe_silver", TOOL_SILVER);
	public static final Item shovel_silver = new ModItemSpade.Silver("shovel_silver", TOOL_SILVER);
	public static final Item hoe_silver = new ModItemHoe.Silver("hoe_silver", TOOL_SILVER);
	
	public static final Item athame = new ItemAthame("athame", TOOL_RITUAL);
	public static final Item boline = new ItemBoline("boline");
	
	//Material Items
	public static final Item ingot_cold_iron = new ModItem("ingot_cold_iron", "ingotColdIron");
	public static final Item ingot_silver = new ModItem("ingot_silver", "ingotSilver");
	public static final Item nugget_cold_iron = new ModItem("nugget_cold_iron", "nuggetColdIron");
	public static final Item nugget_silver = new ModItem("nugget_silver", "nuggetSilver");
	public static final Item gem_alexandrite = new ModItem("gem_alexandrite", "gemAlexandrite", "gemAll");
	public static final Item gem_amethyst = new ModItem("gem_amethyst", "gemAmethyst", "gemAll");
	public static final Item gem_bloodstone = new ModItem("gem_bloodstone", "gemBloodstone", "gemAll");
	public static final Item gem_garnet = new ModItem("gem_garnet", "gemGarnet", "gemAll");
	public static final Item gem_jasper = new ModItem("gem_jasper", "gemJasper", "gemAll");
	public static final Item gem_malachite = new ModItem("gem_malachite", "gemMalachite", "gemAll");
	public static final Item gem_nuummite = new ModItem("gem_nuummite", "gemNuummite", "gemAll");
	public static final Item gem_tigers_eye = new ModItem("gem_tigers_eye", "gemTigersEye", "gemAll");
	public static final Item gem_tourmaline = new ModItem("gem_tourmaline", "gemTourmaline", "gemAll");
	
	public static final Item salt = new ItemSalt("salt", "salt", "dustSalt", "materialSalt", "ingredientSalt", "listAllsalt", "foodSalt", "lumpSalt", "pinchSalt", "portionSalt");
	
	//Jars
	public static final Item unfired_jar = new ModItem("unfired_jar");
	public static final Item empty_jar = new ModItem("empty_jar");
	public static final Item acacia_resin = new ModItem("acacia_resin");
	public static final Item birch_soul = new ModItem("birch_soul");
	public static final Item cleansing_balm = new ModItem("cleansing_balm");
	public static final Item cloudy_oil = new ModItem("cloudy_oil");
	public static final Item demonic_elixir = new ModItem("demonic_elixir");
	public static final Item droplet_of_wisdom = new ModItem("droplet_of_wisdom");
	public static final Item ebb_of_death = new ModItem("ebb_of_death");
	public static final Item everchanging_dew = new ModItem("everchanging_dew");
	public static final Item essence_of_vitality = new ModItem("essence_of_vitality");
	public static final Item fiery_unguent = new ModItem("fiery_unguent");
	public static final Item heaven_extract = new ModItem("heaven_extract");
	public static final Item liquid_witchcraft = new ModItem("liquid_witchcraft");
	public static final Item oak_spirit = new ModItem("oak_spirit");
	public static final Item otherworldly_tears = new ModItem("otherworldly_tears");
	public static final Item philter_of_dishonesty = new ModItem("philter_of_dishonesty");
	public static final Item spruce_heart = new ModItem("spruce_heart");
	public static final Item stone_ichor = new ModItem("stone_ichor");
	public static final Item swirl_of_the_depths = new ModItem("swirl_of_the_depths");
	public static final Item undying_salve = new ModItem("undying_salve");
	
	//Ingredients
	public static final Item aconitum = new ModItem("aconitum", "cropAconitum");
	public static final Item asphodel = new ModItem("asphodel", "cropAsphodel");
	public static final Item belladonna = new ModItem("belladonna", "cropBelladonna");
	public static final Item chrysanthemum = new ModItem("chrysanthemum");
	public static final Item garlic = new ModItem("garlic", "cropGarlic", "listAllherb");
	public static final Item ginger = new ModItem("ginger", "cropGinger", "listAllspice");
	public static final Item hellebore = new ModItem("hellebore");
	public static final Item infested_wheat = new ModItem("infested_wheat");
	public static final Item kelp = new ModItem("kelp", "cropKelp", "cropSeaweed", "listAllveggie", "listAllgreenveggie");
	public static final Item kenaf = new ModItem("kenaf", "cropKenaf");
	public static final Item lavender = new ModItem("lavender", "cropLavender", "listAllherb");
	public static final Item mandrake_root = new ModItem("mandrake_root", "cropMandrake");
	public static final Item mint = new ModItem("mint", "cropMint", "cropSpiceleaf", "listAllspice", "listAllgreenveggie");
	public static final Item sagebrush = new ModItem("sagebrush");
	public static final Item silphium = new ModItem("silphium", "cropSilphium", "listAllherb", "listAllspice", "listAllgreenveggie");
	public static final Item thistle = new ModItem("thistle", "cropThistle");
	public static final Item tulsi = new ModItem("tulsi", "cropTulsi", "listAllherb");
	public static final Item white_sage = new ModItem("white_sage", "cropWhiteSage");
	public static final Item witchweed = new ModItem("witchweed");
	public static final Item wormwood = new ModItem("wormwood", "cropWormwood", "listAllspice");
	
	public static final Item blindworms_sting = new ModItem("blindworms_sting");
	public static final Item demonic_heart = new ModItem("demonic_heart");
	public static final Item diabolical_vein = new ModItem("diabolical_vein");
	public static final Item dimensional_sand = new ModItem("dimensional_sand");
	public static final Item ectoplasm = new ModItem("ectoplasm");
	public static final Item envenomed_fang = new ModItem("envenomed_fang");
	public static final Item eye_of_newt = new ModItem("eye_of_newt");
	public static final Item glass_jar = new ModItem("glass_jar");
	public static final Item graveyard_dust = new ModItem("graveyard_dust");
	public static final Item grilled_watermelon = new ModItemFood("grilled_watermelon", 4, 2.5f, false);
	public static final Item golden_thread = new ModItem("golden_thread");
	public static final Item juniper_berries = new ModItemFood("juniper_berries", 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f);
	public static final Item lapis_powder = new ModItem("lapis_powder");
	public static final Item lizard_leg = new ModItem("lizard_leg");
	public static final Item oak_apple_gall = new ModItem("oak_apple_gall");
	public static final Item owlets_wing = new ModItem("owlets_wing");
	public static final Item quartz_powder = new ModItem("quartz_powder");
	public static final Item silver_scales = new ModItem("silver_scales");
	public static final Item wood_ash = new ModItem("wood_ash");
	public static final Item yew_aril = new ModItemFood("yew_aril", 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f);
	
	//Seeds
	public static final ModItemSeed seed_aconitum = new ModItemSeed("seed_aconitum", ModBlocks.crop_aconitum, Blocks.FARMLAND);
	public static final ModItemSeed seed_asphodel = new ModItemSeed("seed_asphodel", ModBlocks.crop_asphodel, Blocks.FARMLAND);
	public static final ModItemSeed seed_belladonna = new ModItemSeed("seed_belladonna", ModBlocks.crop_belladonna, Blocks.FARMLAND);
	public static final ModItemSeed seed_chrysanthemum = new ModItemSeed("seed_chrysanthemum", ModBlocks.crop_chrysanthemum, Blocks.FARMLAND);
	public static final ModItemSeed seed_garlic = new ModItemSeed("seed_garlic", ModBlocks.crop_garlic, Blocks.FARMLAND);
	public static final ModItemSeed seed_ginger = new ModItemSeed("seed_ginger", ModBlocks.crop_ginger, Blocks.FARMLAND);
	public static final ModItemSeed seed_hellebore = new ModItemSeed("seed_hellebore", ModBlocks.crop_hellebore, Blocks.FARMLAND);
	public static final ModItemSeed seed_kelp = new ModItemSeed("seed_kelp", ModBlocks.crop_kelp, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL);
	public static final ModItemSeed seed_kenaf = new ModItemSeed("seed_kenaf", ModBlocks.crop_kenaf, Blocks.FARMLAND);
	public static final ModItemSeed seed_lavender = new ModItemSeed("seed_lavender", ModBlocks.crop_lavender, Blocks.FARMLAND);
	public static final ModItemSeed seed_mandrake = new ModItemSeed("seed_mandrake", ModBlocks.crop_mandrake, Blocks.FARMLAND);
	public static final ModItemSeed seed_mint = new ModItemSeed("seed_mint", ModBlocks.crop_mint, Blocks.FARMLAND);
	public static final ModItemSeed seed_sagebrush = new ModItemSeed("seed_sagebrush", ModBlocks.crop_sagebrush, Blocks.FARMLAND);
	public static final ModItemSeed seed_silphium = new ModItemSeed("seed_silphium", ModBlocks.crop_silphium, Blocks.FARMLAND);
	public static final ModItemSeed seed_thistle = new ModItemSeed("seed_thistle", ModBlocks.crop_thistle, Blocks.FARMLAND);
	public static final ModItemSeed seed_tulsi = new ModItemSeed("seed_tulsi", ModBlocks.crop_tulsi, Blocks.FARMLAND);
	public static final ModItemSeed seed_white_sage = new ModItemSeed("seed_white_sage", ModBlocks.crop_white_sage, Blocks.FARMLAND);
	public static final ModItemSeed seed_wormwood = new ModItemSeed("seed_wormwood", ModBlocks.crop_wormwood, Blocks.FARMLAND);

	@SubscribeEvent
	public static void register(Register<Item> event)
	{
		for (Block block : ModBlocks.REGISTRY)
		{
			if (!(block instanceof BlockCrops) && !(block instanceof BlockDoor) && !(block instanceof BlockSlab) && !(block instanceof BlockSaltBarrier) && !(block instanceof IFluidBlock))
			{
				Item itemBlock = new ItemBlock(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey());
				event.getRegistry().register(itemBlock);
				Bewitchment.proxy.registerTexture(itemBlock);
			}
		}
		registerSlab(event, ModBlocks.slab_cypress, ModBlocks.slab_cypress_double);
		registerSlab(event, ModBlocks.slab_elder, ModBlocks.slab_elder_double);
		registerSlab(event, ModBlocks.slab_juniper, ModBlocks.slab_juniper_double);
		registerSlab(event, ModBlocks.slab_yew, ModBlocks.slab_yew_double);
		registerSlab(event, ModBlocks.slab_perpetual_ice, ModBlocks.slab_perpetual_ice_double);
		for (Item item : REGISTRY)
		{
			event.getRegistry().register(item);
			Bewitchment.proxy.registerTexture(item);
		}
	}
	
	private static void registerSlab(Register<Item> event, ModBlockSlab half, ModBlockSlab full)
	{
		Item itemSlab = new ItemSlab(half, half, full).setRegistryName(half.getRegistryName()).setTranslationKey(half.getTranslationKey());
		Item itemSlabFull = new ItemSlab(full, half, full).setRegistryName(half.getRegistryName().toString() + "_double").setTranslationKey(half.getTranslationKey());
		half.half = half;
		full.half = half;
		event.getRegistry().register(itemSlab);
		Bewitchment.proxy.registerTexture(itemSlab);
		event.getRegistry().register(itemSlabFull);
		Bewitchment.proxy.registerTexture(itemSlabFull);
	}
}
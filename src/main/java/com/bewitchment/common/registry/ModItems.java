package com.bewitchment.common.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockSaltBarrier;
import com.bewitchment.common.item.ItemCypressBroom;
import com.bewitchment.common.item.ItemElderBroom;
import com.bewitchment.common.item.ItemJuniperBroom;
import com.bewitchment.common.item.ItemSalt;
import com.bewitchment.common.item.ItemYewBroom;
import com.bewitchment.common.item.tool.ItemAthame;
import com.bewitchment.common.item.tool.ItemBoline;
import com.bewitchment.common.item.tool.ItemColdIronArmor;
import com.bewitchment.common.item.tool.ItemColdIronAxe;
import com.bewitchment.common.item.tool.ItemColdIronHoe;
import com.bewitchment.common.item.tool.ItemColdIronPickaxe;
import com.bewitchment.common.item.tool.ItemColdIronSpade;
import com.bewitchment.common.item.tool.ItemColdIronSword;
import com.bewitchment.common.item.tool.ItemSilverArmor;
import com.bewitchment.common.item.tool.ItemSilverAxe;
import com.bewitchment.common.item.tool.ItemSilverHoe;
import com.bewitchment.common.item.tool.ItemSilverPickaxe;
import com.bewitchment.common.item.tool.ItemSilverSpade;
import com.bewitchment.common.item.tool.ItemSilverSword;

import moriyashiine.froglib.FrogLib;
import moriyashiine.froglib.common.item.ModItem;
import moriyashiine.froglib.common.item.ModItemDoor;
import moriyashiine.froglib.common.item.ModItemFood;
import moriyashiine.froglib.common.item.ModItemSeed;
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
	public static final Item door_cypress = reg(new ModItemDoor(Bewitchment.MOD_ID, "door_cypress", ModBlocks.door_block_cypress, Bewitchment.proxy.tab));
	public static final Item door_elder = reg(new ModItemDoor(Bewitchment.MOD_ID, "door_elder", ModBlocks.door_block_elder, Bewitchment.proxy.tab));
	public static final Item door_juniper = reg(new ModItemDoor(Bewitchment.MOD_ID, "door_juniper", ModBlocks.door_block_juniper, Bewitchment.proxy.tab));
	public static final Item door_yew = reg(new ModItemDoor(Bewitchment.MOD_ID, "door_yew", ModBlocks.door_block_yew, Bewitchment.proxy.tab));
	
	//Armor
	public static final Item helmet_cold_iron = reg(new ItemColdIronArmor("helmet_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.HEAD));
	public static final Item chestplate_cold_iron = reg(new ItemColdIronArmor("chestplate_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.CHEST));
	public static final Item leggings_cold_iron = reg(new ItemColdIronArmor("leggings_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.LEGS));
	public static final Item boots_cold_iron = reg(new ItemColdIronArmor("boots_cold_iron", ARMOR_COLD_IRON, EntityEquipmentSlot.FEET));
	
	public static final Item helmet_silver = reg(new ItemSilverArmor("helmet_silver", ARMOR_SILVER, EntityEquipmentSlot.HEAD));
	public static final Item chestplate_silver = reg(new ItemSilverArmor("chestplate_silver", ARMOR_SILVER, EntityEquipmentSlot.CHEST));
	public static final Item leggings_silver = reg(new ItemSilverArmor("leggings_silver", ARMOR_SILVER, EntityEquipmentSlot.LEGS));
	public static final Item boots_silver = reg(new ItemSilverArmor("boots_silver", ARMOR_SILVER, EntityEquipmentSlot.FEET));
	
	//Tools
	public static final Item sword_cold_iron = reg(new ItemColdIronSword(TOOL_COLD_IRON));
	public static final Item pickaxe_cold_iron = reg(new ItemColdIronPickaxe(TOOL_COLD_IRON));
	public static final Item axe_cold_iron = reg(new ItemColdIronAxe(TOOL_COLD_IRON));
	public static final Item spade_cold_iron = reg(new ItemColdIronSpade(TOOL_COLD_IRON));
	public static final Item hoe_cold_iron = reg(new ItemColdIronHoe(TOOL_COLD_IRON));
	
	public static final Item sword_silver = reg(new ItemSilverSword(TOOL_SILVER));
	public static final Item pickaxe_silver = reg(new ItemSilverPickaxe(TOOL_SILVER));
	public static final Item axe_silver = reg(new ItemSilverAxe(TOOL_SILVER));
	public static final Item spade_silver = reg(new ItemSilverSpade(TOOL_SILVER));
	public static final Item hoe_silver = reg(new ItemSilverHoe(TOOL_SILVER));
	
	public static final Item athame = reg(new ItemAthame(TOOL_RITUAL));
	public static final Item boline = reg(new ItemBoline());
	
	//Brooms
	public static final Item broom = reg(new ModItem(Bewitchment.MOD_ID, "broom", Bewitchment.proxy.tab).setMaxStackSize(1));
	public static final Item broom_cypress = reg(new ItemCypressBroom());
	public static final Item broom_elder = reg(new ItemElderBroom());
	public static final Item broom_juniper = reg(new ItemJuniperBroom());
	public static final Item broom_yew = reg(new ItemYewBroom());
	
	//Material Items
	public static final Item ingot_cold_iron = reg(new ModItem(Bewitchment.MOD_ID, "ingot_cold_iron", Bewitchment.proxy.tab, "ingotColdIron"));
	public static final Item ingot_silver = reg(new ModItem(Bewitchment.MOD_ID, "ingot_silver", Bewitchment.proxy.tab, "ingotSilver"));
	public static final Item nugget_cold_iron = reg(new ModItem(Bewitchment.MOD_ID, "nugget_cold_iron", Bewitchment.proxy.tab, "nuggetColdIron"));
	public static final Item nugget_silver = reg(new ModItem(Bewitchment.MOD_ID, "nugget_silver", Bewitchment.proxy.tab, "nuggetSilver"));
	public static final Item gem_alexandrite = reg(new ModItem(Bewitchment.MOD_ID, "gem_alexandrite", Bewitchment.proxy.tab, "gemAlexandrite", "gemAll"));
	public static final Item gem_amethyst = reg(new ModItem(Bewitchment.MOD_ID, "gem_amethyst", Bewitchment.proxy.tab, "gemAmethyst", "gemAll"));
	public static final Item gem_bloodstone = reg(new ModItem(Bewitchment.MOD_ID, "gem_bloodstone", Bewitchment.proxy.tab, "gemBloodstone", "gemAll"));
	public static final Item gem_garnet = reg(new ModItem(Bewitchment.MOD_ID, "gem_garnet", Bewitchment.proxy.tab, "gemGarnet", "gemAll"));
	public static final Item gem_jasper = reg(new ModItem(Bewitchment.MOD_ID, "gem_jasper", Bewitchment.proxy.tab, "gemJasper", "gemAll"));
	public static final Item gem_malachite = reg(new ModItem(Bewitchment.MOD_ID, "gem_malachite", Bewitchment.proxy.tab, "gemMalachite", "gemAll"));
	public static final Item gem_nuummite = reg(new ModItem(Bewitchment.MOD_ID, "gem_nuummite", Bewitchment.proxy.tab, "gemNuummite", "gemAll"));
	public static final Item gem_tigers_eye = reg(new ModItem(Bewitchment.MOD_ID, "gem_tigers_eye", Bewitchment.proxy.tab, "gemTigersEye", "gemAll"));
	public static final Item gem_tourmaline = reg(new ModItem(Bewitchment.MOD_ID, "gem_tourmaline", Bewitchment.proxy.tab, "gemTourmaline", "gemAll"));
	
	public static final Item salt = reg(new ItemSalt());
	
	//Jars
	public static final Item unfired_jar = reg(new ModItem(Bewitchment.MOD_ID, "unfired_jar", Bewitchment.proxy.tab));
	public static final Item empty_jar = reg(new ModItem(Bewitchment.MOD_ID, "empty_jar", Bewitchment.proxy.tab));
	public static final Item acacia_resin = reg(new ModItem(Bewitchment.MOD_ID, "acacia_resin", Bewitchment.proxy.tab));
	public static final Item birch_soul = reg(new ModItem(Bewitchment.MOD_ID, "birch_soul", Bewitchment.proxy.tab));
	public static final Item cleansing_balm = reg(new ModItem(Bewitchment.MOD_ID, "cleansing_balm", Bewitchment.proxy.tab));
	public static final Item cloudy_oil = reg(new ModItem(Bewitchment.MOD_ID, "cloudy_oil", Bewitchment.proxy.tab));
	public static final Item demonic_elixir = reg(new ModItem(Bewitchment.MOD_ID, "demonic_elixir", Bewitchment.proxy.tab));
	public static final Item droplet_of_wisdom = reg(new ModItem(Bewitchment.MOD_ID, "droplet_of_wisdom", Bewitchment.proxy.tab));
	public static final Item ebb_of_death = reg(new ModItem(Bewitchment.MOD_ID, "ebb_of_death", Bewitchment.proxy.tab));
	public static final Item everchanging_dew = reg(new ModItem(Bewitchment.MOD_ID, "everchanging_dew", Bewitchment.proxy.tab));
	public static final Item essence_of_vitality = reg(new ModItem(Bewitchment.MOD_ID, "essence_of_vitality", Bewitchment.proxy.tab));
	public static final Item fiery_unguent = reg(new ModItem(Bewitchment.MOD_ID, "fiery_unguent", Bewitchment.proxy.tab));
	public static final Item heaven_extract = reg(new ModItem(Bewitchment.MOD_ID, "heaven_extract", Bewitchment.proxy.tab));
	public static final Item liquid_witchcraft = reg(new ModItem(Bewitchment.MOD_ID, "liquid_witchcraft", Bewitchment.proxy.tab));
	public static final Item oak_spirit = reg(new ModItem(Bewitchment.MOD_ID, "oak_spirit", Bewitchment.proxy.tab));
	public static final Item otherworldly_tears = reg(new ModItem(Bewitchment.MOD_ID, "otherworldly_tears", Bewitchment.proxy.tab));
	public static final Item philter_of_dishonesty = reg(new ModItem(Bewitchment.MOD_ID, "philter_of_dishonesty", Bewitchment.proxy.tab));
	public static final Item spruce_heart = reg(new ModItem(Bewitchment.MOD_ID, "spruce_heart", Bewitchment.proxy.tab));
	public static final Item stone_ichor = reg(new ModItem(Bewitchment.MOD_ID, "stone_ichor", Bewitchment.proxy.tab));
	public static final Item swirl_of_the_depths = reg(new ModItem(Bewitchment.MOD_ID, "swirl_of_the_depths", Bewitchment.proxy.tab));
	public static final Item undying_salve = reg(new ModItem(Bewitchment.MOD_ID, "undying_salve", Bewitchment.proxy.tab));
	
	//Ingredients
	public static final Item aconitum = reg(new ModItem(Bewitchment.MOD_ID, "aconitum", Bewitchment.proxy.tab, "cropAconitum"));
	public static final Item asphodel = reg(new ModItem(Bewitchment.MOD_ID, "asphodel", Bewitchment.proxy.tab, "cropAsphodel"));
	public static final Item belladonna = reg(new ModItem(Bewitchment.MOD_ID, "belladonna", Bewitchment.proxy.tab, "cropBelladonna"));
	public static final Item chrysanthemum = reg(new ModItem(Bewitchment.MOD_ID, "chrysanthemum", Bewitchment.proxy.tab));
	public static final Item garlic = reg(new ModItem(Bewitchment.MOD_ID, "garlic", Bewitchment.proxy.tab, "cropGarlic", "listAllherb"));
	public static final Item ginger = reg(new ModItem(Bewitchment.MOD_ID, "ginger", Bewitchment.proxy.tab, "cropGinger", "listAllspice"));
	public static final Item hellebore = reg(new ModItem(Bewitchment.MOD_ID, "hellebore", Bewitchment.proxy.tab));
	public static final Item infested_wheat = reg(new ModItem(Bewitchment.MOD_ID, "infested_wheat", Bewitchment.proxy.tab));
	public static final Item kelp = reg(new ModItem(Bewitchment.MOD_ID, "kelp", Bewitchment.proxy.tab, "cropKelp", "cropSeaweed", "listAllveggie", "listAllgreenveggie"));
	public static final Item kenaf = reg(new ModItem(Bewitchment.MOD_ID, "kenaf", Bewitchment.proxy.tab, "cropKenaf"));
	public static final Item lavender = reg(new ModItem(Bewitchment.MOD_ID, "lavender", Bewitchment.proxy.tab, "cropLavender", "listAllherb"));
	public static final Item mandrake_root = reg(new ModItem(Bewitchment.MOD_ID, "mandrake_root", Bewitchment.proxy.tab, "cropMandrake"));
	public static final Item mint = reg(new ModItem(Bewitchment.MOD_ID, "mint", Bewitchment.proxy.tab, "cropMint", "cropSpiceleaf", "listAllspice", "listAllgreenveggie"));
	public static final Item sagebrush = reg(new ModItem(Bewitchment.MOD_ID, "sagebrush", Bewitchment.proxy.tab));
	public static final Item silphium = reg(new ModItem(Bewitchment.MOD_ID, "silphium", Bewitchment.proxy.tab, "cropSilphium", "listAllherb", "listAllspice", "listAllgreenveggie"));
	public static final Item thistle = reg(new ModItem(Bewitchment.MOD_ID, "thistle", Bewitchment.proxy.tab, "cropThistle"));
	public static final Item tulsi = reg(new ModItem(Bewitchment.MOD_ID, "tulsi", Bewitchment.proxy.tab, "cropTulsi", "listAllherb"));
	public static final Item white_sage = reg(new ModItem(Bewitchment.MOD_ID, "white_sage", Bewitchment.proxy.tab, "cropWhiteSage"));
	public static final Item witchweed = reg(new ModItem(Bewitchment.MOD_ID, "witchweed", Bewitchment.proxy.tab));
	public static final Item wormwood = reg(new ModItem(Bewitchment.MOD_ID, "wormwood", Bewitchment.proxy.tab, "cropWormwood", "listAllspice"));
	
	public static final Item adders_fork = reg(new ModItem(Bewitchment.MOD_ID, "adders_fork", Bewitchment.proxy.tab));
	public static final Item blindworms_sting = reg(new ModItem(Bewitchment.MOD_ID, "blindworms_sting", Bewitchment.proxy.tab));
	public static final Item demonic_heart = reg(new ModItem(Bewitchment.MOD_ID, "demonic_heart", Bewitchment.proxy.tab));
	public static final Item diabolical_vein = reg(new ModItem(Bewitchment.MOD_ID, "diabolical_vein", Bewitchment.proxy.tab));
	public static final Item dimensional_sand = reg(new ModItem(Bewitchment.MOD_ID, "dimensional_sand", Bewitchment.proxy.tab));
	public static final Item ectoplasm = reg(new ModItem(Bewitchment.MOD_ID, "ectoplasm", Bewitchment.proxy.tab));
	public static final Item envenomed_fang = reg(new ModItem(Bewitchment.MOD_ID, "envenomed_fang", Bewitchment.proxy.tab));
	public static final Item eye_of_newt = reg(new ModItem(Bewitchment.MOD_ID, "eye_of_newt", Bewitchment.proxy.tab));
	public static final Item fillet_of_fenny_snake = reg(new ModItem(Bewitchment.MOD_ID, "fillet_of_fenny_snake", Bewitchment.proxy.tab));
	public static final Item glass_jar = reg(new ModItem(Bewitchment.MOD_ID, "glass_jar", Bewitchment.proxy.tab));
	public static final Item graveyard_dust = reg(new ModItem(Bewitchment.MOD_ID, "graveyard_dust", Bewitchment.proxy.tab));
	public static final Item grilled_watermelon = reg(new ModItemFood(Bewitchment.MOD_ID, "grilled_watermelon", Bewitchment.proxy.tab, 4, 2.5f, false));
	public static final Item golden_thread = reg(new ModItem(Bewitchment.MOD_ID, "golden_thread", Bewitchment.proxy.tab));
	public static final Item heart = reg(new ModItem(Bewitchment.MOD_ID, "heart", Bewitchment.proxy.tab));
	public static final Item juniper_berries = reg(new ModItemFood(Bewitchment.MOD_ID, "juniper_berries", Bewitchment.proxy.tab, 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f));
	public static final Item lapis_powder = reg(new ModItem(Bewitchment.MOD_ID, "lapis_powder", Bewitchment.proxy.tab));
	public static final Item lizard_leg = reg(new ModItem(Bewitchment.MOD_ID, "lizard_leg", Bewitchment.proxy.tab));
	public static final Item oak_apple_gall = reg(new ModItem(Bewitchment.MOD_ID, "oak_apple_gall", Bewitchment.proxy.tab));
	public static final Item owlets_wing = reg(new ModItem(Bewitchment.MOD_ID, "owlets_wing", Bewitchment.proxy.tab));
	public static final Item pentacle = reg(new ModItem(Bewitchment.MOD_ID, "pentacle", Bewitchment.proxy.tab));
	public static final Item quartz_powder = reg(new ModItem(Bewitchment.MOD_ID, "quartz_powder", Bewitchment.proxy.tab));
	public static final Item silver_scales = reg(new ModItem(Bewitchment.MOD_ID, "silver_scales", Bewitchment.proxy.tab));
	public static final Item snake_venom = reg(new ModItem(Bewitchment.MOD_ID, "snake_venom", Bewitchment.proxy.tab));
	public static final Item wood_ash = reg(new ModItem(Bewitchment.MOD_ID, "wood_ash", Bewitchment.proxy.tab));
	public static final Item yew_aril = reg(new ModItemFood(Bewitchment.MOD_ID, "yew_aril", Bewitchment.proxy.tab, 1, 0.5f, false).setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0), 0.1f));
	
	//Seeds
	public static final ModItemSeed seed_aconitum = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_aconitum", Bewitchment.proxy.tab, ModBlocks.crop_aconitum, Blocks.FARMLAND));
	public static final ModItemSeed seed_asphodel = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_asphodel", Bewitchment.proxy.tab, ModBlocks.crop_asphodel, Blocks.FARMLAND));
	public static final ModItemSeed seed_belladonna = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_belladonna", Bewitchment.proxy.tab, ModBlocks.crop_belladonna, Blocks.FARMLAND));
	public static final ModItemSeed seed_chrysanthemum = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_chrysanthemum", Bewitchment.proxy.tab, ModBlocks.crop_chrysanthemum, Blocks.FARMLAND));
	public static final ModItemSeed seed_garlic = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_garlic", Bewitchment.proxy.tab, ModBlocks.crop_garlic, Blocks.FARMLAND));
	public static final ModItemSeed seed_ginger = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_ginger", Bewitchment.proxy.tab, ModBlocks.crop_ginger, Blocks.FARMLAND));
	public static final ModItemSeed seed_hellebore = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_hellebore", Bewitchment.proxy.tab, ModBlocks.crop_hellebore, Blocks.FARMLAND));
	public static final ModItemSeed seed_kelp = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_kelp", Bewitchment.proxy.tab, ModBlocks.crop_kelp, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL));
	public static final ModItemSeed seed_kenaf = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_kenaf", Bewitchment.proxy.tab, ModBlocks.crop_kenaf, Blocks.FARMLAND));
	public static final ModItemSeed seed_lavender = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_lavender", Bewitchment.proxy.tab, ModBlocks.crop_lavender, Blocks.FARMLAND));
	public static final ModItemSeed seed_mandrake = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_mandrake", Bewitchment.proxy.tab, ModBlocks.crop_mandrake, Blocks.FARMLAND));
	public static final ModItemSeed seed_mint = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_mint", Bewitchment.proxy.tab, ModBlocks.crop_mint, Blocks.FARMLAND));
	public static final ModItemSeed seed_sagebrush = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_sagebrush", Bewitchment.proxy.tab, ModBlocks.crop_sagebrush, Blocks.FARMLAND));
	public static final ModItemSeed seed_silphium = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_silphium", Bewitchment.proxy.tab, ModBlocks.crop_silphium, Blocks.FARMLAND));
	public static final ModItemSeed seed_thistle = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_thistle", Bewitchment.proxy.tab, ModBlocks.crop_thistle, Blocks.FARMLAND));
	public static final ModItemSeed seed_tulsi = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_tulsi", Bewitchment.proxy.tab, ModBlocks.crop_tulsi, Blocks.FARMLAND));
	public static final ModItemSeed seed_white_sage = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_white_sage", Bewitchment.proxy.tab, ModBlocks.crop_white_sage, Blocks.FARMLAND));
	public static final ModItemSeed seed_wormwood = reg(new ModItemSeed(Bewitchment.MOD_ID, "seed_wormwood", Bewitchment.proxy.tab, ModBlocks.crop_wormwood, Blocks.FARMLAND));

	@SubscribeEvent
	public static void register(Register<Item> event)
	{
		for (Block block : ModBlocks.REGISTRY)
		{
			if (!(block instanceof BlockSaltBarrier) && !(block instanceof BlockCrops) && !(block instanceof BlockDoor) && !(block instanceof BlockSlab) && !(block instanceof IFluidBlock))
			{
				Item itemBlock = new ItemBlock(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey());
				event.getRegistry().register(itemBlock);
				FrogLib.proxy.registerTexture(itemBlock);
			}
		}
		for (Item item : REGISTRY)
		{
			event.getRegistry().register(item);
			FrogLib.proxy.registerTexture(item);
		}
		
		FrogLib.registerSlab(event, ModBlocks.slab_cypress, ModBlocks.slab_cypress_double);
		FrogLib.registerSlab(event, ModBlocks.slab_elder, ModBlocks.slab_elder_double);
		FrogLib.registerSlab(event, ModBlocks.slab_juniper, ModBlocks.slab_juniper_double);
		FrogLib.registerSlab(event, ModBlocks.slab_yew, ModBlocks.slab_yew_double);
		FrogLib.registerSlab(event, ModBlocks.slab_perpetual_ice, ModBlocks.slab_perpetual_ice_double);
	}
	
	private static Item reg(Item item)
	{
		REGISTRY.add(item);
		return item;
	}
	
	private static ModItemSeed reg(ModItemSeed item)
	{
		REGISTRY.add(item);
		return item;
	}
}
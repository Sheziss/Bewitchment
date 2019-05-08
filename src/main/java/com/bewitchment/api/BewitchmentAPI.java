package com.bewitchment.api;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.api.registry.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The Bewitchment API, use this for creating addons.
 */
public class BewitchmentAPI {
	//	public static final IForgeRegistry<CauldronRecipe> REGISTRY_CAULDRON = new RegistryBuilder<CauldronRecipe>().setName(new ResourceLocation(Bewitchment.MODID, "cauldron_recipe")).setType(CauldronRecipe.class).create();
	public static final IForgeRegistry<DistilleryRecipe> REGISTRY_DISTILLERY = new RegistryBuilder<DistilleryRecipe>().setName(new ResourceLocation(Bewitchment.MODID, "distillery_recipe")).setType(DistilleryRecipe.class).create();
	public static final IForgeRegistry<LoomRecipe> REGISTRY_LOOM = new RegistryBuilder<LoomRecipe>().setName(new ResourceLocation(Bewitchment.MODID, "loom_recipe")).setType(LoomRecipe.class).create();
	public static final IForgeRegistry<OvenRecipe> REGISTRY_OVEN = new RegistryBuilder<OvenRecipe>().setName(new ResourceLocation(Bewitchment.MODID, "oven_recipe")).setType(OvenRecipe.class).create();
	public static final IForgeRegistry<Ritual> REGISTRY_RITUAL = new RegistryBuilder<Ritual>().setName(new ResourceLocation(Bewitchment.MODID, "ritual")).setType(Ritual.class).create();
	public static final IForgeRegistry<Spell> REGISTRY_SPELL = new RegistryBuilder<Spell>().setName(new ResourceLocation(Bewitchment.MODID, "spell")).setType(Spell.class).create();

	public static final IForgeRegistry<Fortune> REGISTRY_FORTUNE = new RegistryBuilder<Fortune>().setName(new ResourceLocation(Bewitchment.MODID, "fortune")).setType(Fortune.class).create();

	public static final Map<EntityEntry, Collection<ItemStack>> ATHAME_LOOT = new HashMap<>();
	public static final Map<Block, NaturePower> ALTAR_NATURE_VALUES = new HashMap<>();
	private static final Map<Item, Integer> SWORD_GAIN_VALUES = new HashMap<>();
	private static final Map<Item, Double> SWORD_MULTIPLIER_VALUES = new HashMap<>();
	private static final Map<Item, Integer> CUP_GAIN_VALUES = new HashMap<>();
	private static final Map<Item, Double> CUP_MULTIPLIER_VALUES = new HashMap<>();
	private static final Map<Item, Integer> WAND_GAIN_VALUES = new HashMap<>();
	private static final Map<Item, Double> WAND_MULTIPLIER_VALUES = new HashMap<>();
	private static final Map<Item, Integer> PENTACLE_GAIN_VALUES = new HashMap<>();
	private static final Map<Item, Double> PENTACLE_MULTIPLIER_VALUES = new HashMap<>();

	/**
	 * The Demon creature attribute.
	 */
	public static EnumCreatureAttribute DEMON = EnumHelper.addCreatureAttribute("DEMON");

	/**
	 * The Spirit creature attribute.
	 */
	public static EnumCreatureAttribute SPIRIT = EnumHelper.addCreatureAttribute("SPIRIT");

	public static void registerAltarUpgradeGain(UpgradeType type, Item item, int amount)
	{
		if (type == UpgradeType.SWORD) SWORD_GAIN_VALUES.put(item, amount);
		else if (type == UpgradeType.CUP) CUP_GAIN_VALUES.put(item, amount);
		else if (type == UpgradeType.WAND) WAND_GAIN_VALUES.put(item, amount);
		else PENTACLE_GAIN_VALUES.put(item, amount);
	}

	public static void registerAltarUpgradeGain(UpgradeType type, Block block, int amount)
	{
		registerAltarUpgradeGain(type, Item.getItemFromBlock(block), amount);
	}

	public static void registerAltarUpgradeMultiplier(UpgradeType type, Item item, double amount)
	{
		if (type == UpgradeType.SWORD) SWORD_MULTIPLIER_VALUES.put(item, amount);
		else if (type == UpgradeType.CUP) CUP_MULTIPLIER_VALUES.put(item, amount);
		else if (type == UpgradeType.WAND) WAND_MULTIPLIER_VALUES.put(item, amount);
		else PENTACLE_MULTIPLIER_VALUES.put(item, amount);
	}

	public static void registerAltarUpgradeMultiplier(UpgradeType type, Block block, double amount)
	{
		registerAltarUpgradeMultiplier(type, Item.getItemFromBlock(block), amount);
	}

	public static int getAltarUpgradeGain(UpgradeType type, Item item)
	{
		if (type == UpgradeType.SWORD) return SWORD_GAIN_VALUES.getOrDefault(item, 0);
		else if (type == UpgradeType.CUP) return CUP_GAIN_VALUES.getOrDefault(item, 0);
		else if (type == UpgradeType.WAND) return WAND_GAIN_VALUES.getOrDefault(item, 0);
		else return PENTACLE_GAIN_VALUES.getOrDefault(item, 0);
	}

	public static int getAltarUpgradeGain(UpgradeType type, Block block)
	{
		return getAltarUpgradeGain(type, Item.getItemFromBlock(block));
	}

	public static double getAltarUpgradeMultiplier(UpgradeType type, Item item)
	{
		if (type == UpgradeType.SWORD) return SWORD_MULTIPLIER_VALUES.getOrDefault(item, 0d);
		else if (type == UpgradeType.CUP) return CUP_MULTIPLIER_VALUES.getOrDefault(item, 0d);
		else if (type == UpgradeType.WAND) return WAND_MULTIPLIER_VALUES.getOrDefault(item, 0d);
		else return PENTACLE_MULTIPLIER_VALUES.getOrDefault(item, 0d);
	}

	public static double getAltarUpgradeMultiplier(UpgradeType type, Block block)
	{
		return getAltarUpgradeMultiplier(type, Item.getItemFromBlock(block));
	}

	public static boolean isAltarUpgrade(UpgradeType type, Item item)
	{
		return item != Items.AIR && (getAltarUpgradeGain(type, item) != 0 || getAltarUpgradeMultiplier(type, item) != 0);
	}

	public static boolean isAltarUpgrade(Item item)
	{
		for (UpgradeType type : UpgradeType.values()) if (isAltarUpgrade(type, item)) return true;
		return false;
	}

	public static boolean isAltarUpgrade(UpgradeType type, Block block)
	{
		return isAltarUpgrade(type, Item.getItemFromBlock(block));
	}

	public static boolean isAltarUpgrade(Block block)
	{
		return isAltarUpgrade(Item.getItemFromBlock(block));
	}

	/**
	 * @param entity the entity to check
	 * @return true if the entity is a vampire, false otherwise
	 */
	public static final boolean isVampire(EntityLivingBase entity) {
		return entity instanceof EntityPlayer && entity.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.VAMPIRE;
	}

	/**
	 * @param entity the entity to check
	 * @return true if the entity is a werewolf, false otherwise
	 */
	public static final boolean isWerewolf(EntityLivingBase entity) {
		return entity instanceof EntityPlayer && entity.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF;
	}

	/**
	 * @param entity the entity to check
	 * @return true if the entity is weak to cold iron, false otherwise
	 */
	public static final boolean isWeakToColdIron(EntityLivingBase entity) {
		return entity.getCreatureAttribute() == DEMON || entity.getCreatureAttribute() == SPIRIT || entity instanceof EntityBlaze || entity instanceof EntityEnderman || entity instanceof EntityVex;
	}

	/**
	 * @param entity the entity to check
	 * @return true if the entity is weak to silver, false otherwise
	 */
	public static final boolean isWeakToSilver(EntityLivingBase entity) {
		return isWerewolf(entity) || isVampire(entity) || entity.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
	}

	public enum UpgradeType
	{
		SWORD, CUP, WAND, PENTACLE
	}

	public enum NaturePower
	{
		NONE, WEAK, NORMAL, STRONG
	}
}
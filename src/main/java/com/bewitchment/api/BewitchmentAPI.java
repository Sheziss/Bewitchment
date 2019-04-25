package com.bewitchment.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.api.registry.Fortune;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.api.registry.Spell;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * The Bewitchment API, use this for creating addons.
 */
public class BewitchmentAPI {
	public static final IForgeRegistry<DistilleryRecipe> REGISTRY_DISTILLERY = new RegistryBuilder<DistilleryRecipe>().setName(ModObjects.distillery.getRegistryName()).setType(DistilleryRecipe.class).create();
	public static final IForgeRegistry<LoomRecipe> REGISTRY_LOOM = new RegistryBuilder<LoomRecipe>().setName(ModObjects.loom.getRegistryName()).setType(LoomRecipe.class).create();
	public static final IForgeRegistry<OvenRecipe> REGISTRY_OVEN = new RegistryBuilder<OvenRecipe>().setName(ModObjects.oven.getRegistryName()).setType(OvenRecipe.class).create();
	public static final IForgeRegistry<Ritual> REGISTRY_RITUAL = new RegistryBuilder<Ritual>().setName(ModObjects.glyph.getRegistryName()).setType(Ritual.class).create();
	public static final IForgeRegistry<Spell> REGISTRY_SPELL = new RegistryBuilder<Spell>().setName(new ResourceLocation(Bewitchment.MODID, "spell")).setType(Spell.class).create();

	public static final IForgeRegistry<Fortune> REGISTRY_FORTUNE = new RegistryBuilder<Fortune>().setName(ModObjects.crystal_ball.getRegistryName()).setType(Fortune.class).create();

	public static final Map<EntityEntry, Collection<ItemStack>> ATHAME_LOOT = new HashMap<>();
	public static final Map<Block, Integer> ALTAR_NATURE_VALUES = new HashMap<>();
	public static final Map<Item, Double> ALTAR_GAIN_VALUES = new HashMap<>();
	public static final Map<Item, Integer> ALTAR_MULTIPLIER_VALUES = new HashMap<>();
	
	/**
	 * The Demon creature attribute.
	 */
	public static EnumCreatureAttribute DEMON = EnumHelper.addCreatureAttribute("DEMON");

	/**
	 * The Spirit creature attribute.
	 */
	public static EnumCreatureAttribute SPIRIT = EnumHelper.addCreatureAttribute("SPIRIT");

	
	/**
	 * @param entity the entity to check
	 * @return true if the entity is a vampire, false otherwise
	 */
	public static final boolean isVampire(EntityLivingBase entity)
	{
		return entity instanceof EntityPlayer && entity.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.VAMPIRE;
	}
	
	/**
	 * @param entity the entity to check
	 * @return true if the entity is a werewolf, false otherwise
	 */
	public static final boolean isWerewolf(EntityLivingBase entity)
	{
		return entity instanceof EntityPlayer && entity.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF;
	}
	
	/**
	 * @param entity the entity to check
	 * @return true if the entity is weak to cold iron, false otherwise
	 */
	public static final boolean isWeakToColdIron(EntityLivingBase entity)
	{
		return entity.getCreatureAttribute() == DEMON || entity.getCreatureAttribute() == SPIRIT || entity instanceof EntityBlaze || entity instanceof EntityEnderman || entity instanceof EntityVex;
	}
	
	/**
	 * @param entity the entity to check
	 * @return true if the entity is weak to silver, false otherwise
	 */
	public static final boolean isWeakToSilver(EntityLivingBase entity)
	{
		return isWerewolf(entity) || isVampire(entity) || entity.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD;
	}
}
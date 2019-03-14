package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.EntityBlindworm;
import com.bewitchment.common.entity.EntitySnake;
import com.bewitchment.common.entity.misc.EntityBroom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEntities
{
	public static final List<EntityEntry> REGISTRY = new ArrayList<>();
	
	private static int entity_id = 0;
	
	public static final EntityEntry entity_blindworm = createEntityEntry(EntityBlindworm.class, Bewitchment.MOD_ID, "blindworm", entity_id++, 0x826644, 0xD2B48C, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry entity_lizard = createEntityEntry(EntityBlindworm.class, Bewitchment.MOD_ID, "lizard", entity_id++, 0x568203, 0x0070BB, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry entity_newt = createEntityEntry(EntityBlindworm.class, Bewitchment.MOD_ID, "newt", entity_id++, 0x000000, 0xFFD300, EnumCreatureType.CREATURE, 20, 1, 4, Type.SWAMP);
	public static final EntityEntry entity_owl = createEntityEntry(EntityBlindworm.class, Bewitchment.MOD_ID, "owl", entity_id++, 0xAF813F, 0x6E5127, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST, Type.DENSE);
	public static final EntityEntry entity_snake = createEntityEntry(EntitySnake.class, Bewitchment.MOD_ID, "snake", entity_id++, 0x8F9779, 0x696969, EnumCreatureType.CREATURE, 20, 1, 4, Type.PLAINS, Type.HILLS);
	public static final EntityEntry entity_broom = createEntityEntry(EntityBroom.class, Bewitchment.MOD_ID, "broom", entity_id++);
	
	@SubscribeEvent
	public static void registerEntities(Register<EntityEntry> event)
	{
		for (EntityEntry entry : REGISTRY) event.getRegistry().register(entry);
	}
	
	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String mod_id, String name, int id)
	{
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(mod_id, name), id++).name(name).tracker(64, 1, true).build();
		REGISTRY.add(entry);
		return entry;
	}
	
	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String mod_id, String name, int id, int solidColor, int spotColor, EnumCreatureType type, int weight, int min, int max, Type... biomeTypes)
	{
		Set<Biome> biomes = new HashSet<>();
		for (Biome biome : ForgeRegistries.BIOMES) for (Type biomeType : biomeTypes) if (BiomeDictionary.hasType(biome, biomeType)) biomes.add(biome);
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(mod_id, name), id++).name(name).tracker(64, 1, true).egg(solidColor, spotColor).spawn(type, weight, min, max, biomes).build();
		REGISTRY.add(entry);
		return entry;
	}
}
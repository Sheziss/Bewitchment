package com.bewitchment.common.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.EntityBlindworm;
import com.bewitchment.common.entity.EntitySnake;
import com.bewitchment.common.entity.misc.EntityBroom;
import com.google.common.collect.Sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModEntities
{
	public static final List<EntityEntry> REGISTRY = new ArrayList<EntityEntry>();
	
	private static int id = 0;
	
	public static final EntityEntry blindworm = registerEntity(EntityBlindworm.class, "blindworm", 0x826644, 0xD2B48C, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry lizard = registerEntity(EntityBlindworm.class, "lizard", 0x568203, 0x0070BB, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry newt = registerEntity(EntityBlindworm.class, "newt", 0x000000, 0xFFD300, EnumCreatureType.CREATURE, 20, 1, 4, Type.SWAMP);
	public static final EntityEntry owl = registerEntity(EntityBlindworm.class, "owl", 0xAF813F, 0x6E5127, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST, Type.DENSE);
	public static final EntityEntry snake = registerEntity(EntitySnake.class, "snake", 0x8F9779, 0x696969, EnumCreatureType.CREATURE, 20, 1, 4, Type.PLAINS, Type.HILLS);
	
	public static final EntityEntry broom = registerEntity(EntityBroom.class, "broom");
	
	@SubscribeEvent
	public static void register(Register<EntityEntry> event)
	{
		for (EntityEntry entry : REGISTRY) event.getRegistry().register(entry);
	}
	
	private static EntityEntry registerEntity(Class<? extends Entity> clazz, String name)
	{
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MOD_ID, name), id++).name(name).tracker(64, 1, true).build();
		REGISTRY.add(entry);
		return entry;
	}
	
	private static EntityEntry registerEntity(Class<? extends Entity> clazz, String name, int solidColor, int spotColor, EnumCreatureType type, int weight, int min, int max, Type... biomeTypes)
	{
		Set<Biome> biomes = Sets.newHashSet();
		for (Biome biome : ForgeRegistries.BIOMES) for (Type biomeType : biomeTypes) if (BiomeDictionary.hasType(biome, biomeType)) biomes.add(biome);
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MOD_ID, name), id++).name(name).tracker(64, 1, true).egg(solidColor, spotColor).spawn(type, weight, min, max, biomes).build();
		REGISTRY.add(entry);
		return entry;
	}
}
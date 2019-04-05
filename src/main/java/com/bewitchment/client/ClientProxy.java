package com.bewitchment.client;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.block.tile.render.RenderTileEntityGemBowl;
import com.bewitchment.client.block.tile.render.RenderTileEntityPlacedItem;
import com.bewitchment.client.handler.ClientHandler;
import com.bewitchment.client.particle.ParticleBee;
import com.bewitchment.client.render.entity.living.RenderBlindworm;
import com.bewitchment.client.render.entity.living.RenderLizard;
import com.bewitchment.client.render.entity.living.RenderNewt;
import com.bewitchment.client.render.entity.living.RenderOwl;
import com.bewitchment.client.render.entity.living.RenderRaven;
import com.bewitchment.client.render.entity.living.RenderSnake;
import com.bewitchment.client.render.entity.living.RenderToad;
import com.bewitchment.client.render.entity.misc.RenderBeeSwarm;
import com.bewitchment.client.render.entity.misc.RenderBroom;
import com.bewitchment.client.render.entity.misc.RenderSpell;
import com.bewitchment.client.render.entity.spirits.demons.RenderAlphaHellhound;
import com.bewitchment.client.render.entity.spirits.demons.RenderDemon;
import com.bewitchment.client.render.entity.spirits.demons.RenderDemoness;
import com.bewitchment.client.render.entity.spirits.demons.RenderHellhound;
import com.bewitchment.client.render.entity.spirits.demons.RenderSerpent;
import com.bewitchment.client.render.entity.spirits.ghosts.RenderBlackDog;
import com.bewitchment.common.CommonProxy;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGemBowl;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.entity.spirits.demons.EntityAlphaHellhound;
import com.bewitchment.common.entity.spirits.ghosts.EntityBlackDog;
import com.bewitchment.common.entity.spirits.demons.EntityDemon;
import com.bewitchment.common.entity.spirits.demons.EntityDemoness;
import com.bewitchment.common.entity.spirits.demons.EntityHellhound;
import com.bewitchment.common.entity.spirits.demons.EntitySerpent;
import com.bewitchment.common.entity.living.EntityBlindworm;
import com.bewitchment.common.entity.living.EntityLizard;
import com.bewitchment.common.entity.living.EntityNewt;
import com.bewitchment.common.entity.living.EntityOwl;
import com.bewitchment.common.entity.living.EntityRaven;
import com.bewitchment.common.entity.living.EntitySnake;
import com.bewitchment.common.entity.living.EntityToad;
import com.bewitchment.common.entity.misc.EntityBeeSwarm;
import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.entity.misc.EntitySpell;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModParticles;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		RenderingRegistry.registerEntityRenderingHandler(EntityBeeSwarm.class, RenderBeeSwarm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpell.class, RenderSpell::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, RenderBroom::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBlindworm.class, RenderBlindworm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, RenderLizard::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNewt.class, RenderNewt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, RenderOwl::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRaven.class, RenderRaven::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySnake.class, RenderSnake::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, RenderToad::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackDog.class, RenderBlackDog::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHellhound.class, RenderHellhound::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAlphaHellhound.class, RenderAlphaHellhound::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySerpent.class, RenderSerpent::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, RenderDemon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDemoness.class, RenderDemoness::new);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlacedItem.class, new RenderTileEntityPlacedItem());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGemBowl.class, new RenderTileEntityGemBowl());
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor()
		{
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex)
			{
				GlyphType type = state.getValue(BlockGlyph.TYPE);
				return type == GlyphType.GOLDEN ? 0xe3dc3c : type == GlyphType.NETHER ? 0xbb0000 : type == GlyphType.ENDER ? 0x770077 : 0xffffff;
			}
		}, ModObjects.glyph);
	}
	
	@SubscribeEvent
	public static void stitchTexture(TextureStitchEvent.Pre event)
	{
		event.getMap().registerSprite(ParticleBee.TEX);
	}
	
	@Override
	protected void registerEventHandlers()
	{
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
	}
	
	@Override
	public boolean isFancyGraphicsEnabled()
	{
		return Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}
	
	@Override
	public void ignoreProperty(Block block, IProperty<?>... properties)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(properties).build());
	}
	
	@Override
	public void registerTexture(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	@Override
	public void registerTexture(Fluid fluid)
	{
		StateMapper mapper = new StateMapper(fluid);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(fluid.getBlock()));
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.getBlock()), mapper);
		ModelLoader.setCustomStateMapper(fluid.getBlock(), mapper);
	}
	
	@Override
	public void registerTextureEyeOfOld()
	{
		ModelBakery.registerItemVariants(ModObjects.eye_of_old, new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_normal"), new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_haru"), new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_izu"));
		ModelLoader.setCustomMeshDefinition(ModObjects.eye_of_old, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				if (stack.getDisplayName().equalsIgnoreCase("Haru") || stack.getDisplayName().equalsIgnoreCase("Haruspex") || stack.getDisplayName().equalsIgnoreCase("H4rv5p3x")) return new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_haru"), "inventory");
				if (stack.getDisplayName().equalsIgnoreCase("Izuxe") || stack.getDisplayName().equalsIgnoreCase("Izu") || stack.getDisplayName().equalsIgnoreCase("Izuxe43ui520815")) return new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_izu"), "inventory");
				return new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "eye_of_old_normal"), "inventory");
			}
		});
	}
	
	@Override
	public void registerTextureWaystone()
	{
		ModelBakery.registerItemVariants(ModObjects.waystone, new ResourceLocation(Bewitchment.MOD_ID, "waystone_normal"), new ResourceLocation(Bewitchment.MOD_ID, "waystone_bound"));
		ModelLoader.setCustomMeshDefinition(ModObjects.waystone, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey("location")) return new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "waystone_bound"), "inventory");
				return new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "waystone_normal"), "inventory");
			}
		});
	}
	
	@Override
	public void spawnParticle(ModParticles particle, double x, double y, double z)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().isClient() && Math.random() <= (Minecraft.getMinecraft().gameSettings.particleSetting == 1 ? 0.6f : 0.2f)) Minecraft.getMinecraft().effectRenderer.addEffect(particle.newInstance(x, y, z));
	}
	
	private static class StateMapper extends StateMapperBase implements ItemMeshDefinition
	{
		private final ModelResourceLocation location;
		
		public StateMapper(Fluid fluid)
		{
			this.location = new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "fluid"), fluid.getName());
		}
		
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack)
		{
			return location;
		}
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state)
		{
			return location;
		}
	}
}
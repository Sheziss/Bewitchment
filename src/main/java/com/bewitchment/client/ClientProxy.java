package com.bewitchment.client;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.block.tile.render.RenderTileEntityGemBowl;
import com.bewitchment.client.block.tile.render.RenderTileEntityPlacedItem;
import com.bewitchment.client.block.tile.render.RenderTileEntityWitchesCauldron;
import com.bewitchment.client.handler.ClientHandler;
import com.bewitchment.client.render.entity.living.*;
import com.bewitchment.client.render.entity.misc.*;
import com.bewitchment.client.render.entity.spirits.demons.*;
import com.bewitchment.client.render.entity.spirits.ghosts.RenderBlackDog;
import com.bewitchment.common.CommonProxy;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGemBowl;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesCauldron;
import com.bewitchment.common.entity.living.*;
import com.bewitchment.common.entity.misc.*;
import com.bewitchment.common.entity.spirits.demons.*;
import com.bewitchment.common.entity.spirits.ghosts.EntityBlackDog;
import com.bewitchment.registry.ModObjects;
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
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.function.Predicate;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpell.class, RenderSpell::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCypressBroom.class, RenderCypressBroom::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityElderBroom.class, RenderElderBroom::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityJuniperBroom.class, RenderJuniperBroom::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityYewBroom.class, RenderYewBroom::new);

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
		RenderingRegistry.registerEntityRenderingHandler(EntityImp.class, RenderImp::new);

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWitchesCauldron.class, new RenderTileEntityWitchesCauldron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlacedItem.class, new RenderTileEntityPlacedItem());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGemBowl.class, new RenderTileEntityGemBowl());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
				GlyphType type = state.getValue(BlockGlyph.TYPE);
				return type == GlyphType.GOLDEN ? 0xe3dc3c : type == GlyphType.NETHER ? 0xbb0000 : type == GlyphType.ENDER ? 0x770077 : 0xffffff;
			}
		}, ModObjects.glyph);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return tintIndex == 0 ? 0xe6c44f : 0xffffff;
			}
		}, ModObjects.snake_venom);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return tintIndex == 0 ? 0x717d39 : 0xffffff;
			}
		}, ModObjects.liquid_wroth);
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
	}

	@Override
	public boolean isFancyGraphicsEnabled() {
		return Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}

	@Override
	public void ignoreProperty(Block block, IProperty<?>... properties) {
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(properties).build());
	}

	@Override
	public void registerTexture(Item item, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	@Override
	public void registerTextureWithVariant(Item item, List<Predicate<ItemStack>> predicates) {
		ResourceLocation[] names = new ResourceLocation[predicates.size() + 1];
		for (int i = 0; i <= predicates.size(); i++)
		     names[i] = new ResourceLocation(item.getRegistryName().toString() + (i == 0 ? "" : "_variant" + (predicates.size() == 1 ? "" : (i - 1))));
		ModelBakery.registerItemVariants(item, names);
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				for (int i = 0; i < predicates.size(); i++)
					if (predicates.get(i).test(stack)) return new ModelResourceLocation(names[i + 1], "inventory");
				return new ModelResourceLocation(item.getRegistryName(), "inventory");
			}
		});
	}

	@Override
	public void registerTexture(Fluid fluid) {
		StateMapper mapper = new StateMapper(fluid);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(fluid.getBlock()));
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.getBlock()), mapper);
		ModelLoader.setCustomStateMapper(fluid.getBlock(), mapper);
	}

	private static class StateMapper extends StateMapperBase implements ItemMeshDefinition {
		private final ModelResourceLocation location;

		public StateMapper(Fluid fluid) {
			this.location = new ModelResourceLocation(new ResourceLocation(Bewitchment.MODID, "fluid"), fluid.getName());
		}

		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return location;
		}

		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return location;
		}
	}
}
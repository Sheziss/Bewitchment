package com.bewitchment.client;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.entity.render.RenderBlindworm;
import com.bewitchment.client.entity.render.RenderLizard;
import com.bewitchment.client.entity.render.RenderNewt;
import com.bewitchment.client.entity.render.RenderOwl;
import com.bewitchment.common.CommonProxy;
import com.bewitchment.common.entity.EntityBlindworm;
import com.bewitchment.common.entity.EntityLizard;
import com.bewitchment.common.entity.EntityNewt;
import com.bewitchment.common.entity.EntityOwl;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	protected void registerEntities()
	{
		super.registerEntities();
		RenderingRegistry.registerEntityRenderingHandler(EntityBlindworm.class, RenderBlindworm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, RenderLizard::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNewt.class, RenderNewt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, RenderOwl::new);
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
	public void ignoreProperty(Block block, IProperty<?>... property)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(property).build());
	}
	
	@Override
	public boolean isFancyGraphicsEnabled()
	{
		return Minecraft.getMinecraft().gameSettings.fancyGraphics;
	}
	
	private static class StateMapper extends StateMapperBase implements ItemMeshDefinition
	{
		private final ModelResourceLocation location;
		
		public StateMapper(Fluid fluid)
		{
			this.location = new ModelResourceLocation(new ResourceLocation(Bewitchment.MOD_ID, "fluid"), fluid.getName());
		}
		
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state)
		{
			return location;
		}
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack)
		{
			return location;
		}
	}
}
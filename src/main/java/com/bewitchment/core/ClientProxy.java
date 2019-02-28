package com.bewitchment.core;

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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
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
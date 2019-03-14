package com.bewitchment.client.block.tile.render;

import org.lwjgl.opengl.GL11;

import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTileEntityPlacedItem extends TileEntitySpecialRenderer<TileEntityPlacedItem>
{
	@Override
	public void render(TileEntityPlacedItem tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		if (tile.getWorld().getBlockState(tile.getPos()).getBlock() == ModObjects.placed_item && !tile.getStackInSlot(0).isEmpty())
		{
			ItemStack stack = tile.getStackInSlot(0);
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.1625, z + 0.5);
			float rot = tile.getWorld().getBlockState(tile.getPos()).getValue(BlockHorizontal.FACING).getHorizontalAngle();
			if (rot == 0 || rot == 180) rot += 180;
			GlStateManager.rotate(rot, 0, 1, 0);
			GlStateManager.translate(0, -0.125, 0);
			GlStateManager.rotate(-90, 1, 0, 0);
			GlStateManager.translate(0, -0.125, 0);
			IBakedModel model = ForgeHooksClient.handleCameraTransforms(Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, tile.getWorld(), null), TransformType.GROUND, false);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
}
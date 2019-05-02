package com.bewitchment.client.block.tile.render;

import com.bewitchment.common.block.tile.entity.TileEntityWitchesCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderTileEntityWitchesCauldron extends TileEntitySpecialRenderer<TileEntityWitchesCauldron> {
	@Override
	public void render(TileEntityWitchesCauldron tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		FluidTank tank = (FluidTank) tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		if (tank.getFluid() != null) {
			FluidStack stack = tank.getFluid();
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.disableAlpha();
			//			System.out.println(tank.getFluidAmount());
			GlStateManager.translate(x, (y + 3d / 5) - ((double) Fluid.BUCKET_VOLUME / tank.getFluidAmount()) + 1, z);
			GlStateManager.translate(0.125, 0, 0.125);
			GlStateManager.rotate(90, 1, 0, 0);
			GlStateManager.scale(0.0460425, 0.0460425, 0.0460425);

			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(stack.getFluid().getStill().toString());
			Tessellator tessellator = Tessellator.getInstance();
			tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			tessellator.getBuffer().pos(0, 16, 0).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();
			tessellator.getBuffer().pos(16, 16, 0).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
			tessellator.getBuffer().pos(16, 0, 0).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
			tessellator.getBuffer().pos(0, 0, 0).tex(sprite.getMinU(), sprite.getMinV()).endVertex();
			tessellator.draw();

			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.popMatrix();
		}
	}
}
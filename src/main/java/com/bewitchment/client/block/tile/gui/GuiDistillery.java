package com.bewitchment.client.block.tile.gui;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.tile.container.ContainerDistillery;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDistillery extends GuiContainer
{
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/distillery.png");
	
	private final ContainerDistillery container;
	
	public GuiDistillery(ContainerDistillery container)
	{
		super(container);
		this.container = container;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1, 1, 1);
		mc.getTextureManager().bindTexture(TEX);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		drawTexturedModalRect(x + 76, y + 16, 176, 0, container.tile.progress * 24 / Math.max(1, container.tile.recipe_time) + 1, 17);
		int burnProgress = 14 - (int) Math.ceil(14 * (container.tile.burn_time / (double) TileEntityDistillery.BURN_TIME));
		drawTexturedModalRect(x + 81, y + 36 + burnProgress, 242, burnProgress, 14, 14 - burnProgress);
	}
}
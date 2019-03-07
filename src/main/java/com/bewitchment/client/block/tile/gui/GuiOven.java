package com.bewitchment.client.block.tile.gui;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.tile.container.ContainerOven;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiOven extends GuiContainer
{
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/oven.png");
	
	private final InventoryPlayer inventory;
	private final ContainerOven container;
	
	public GuiOven(ContainerOven container, InventoryPlayer inventory)
	{
		super(container);
		this.container = container;
		this.inventory = inventory;
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
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		if (container.tile.burn_time > 0)
		{
			int time = ((container.tile.burn_time) * 13) / container.tile.fuel_burn_time;
			this.drawTexturedModalRect(x + 44, (y + 50) - time, 176, 12 - time, 14, time + 1);
		}
		this.drawTexturedModalRect(x + 76, y + 19, 176, 14, ((container.tile.progress * 24) / 100) + 1, 16);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String name = container.tile.getName();
		fontRenderer.drawString(name, (xSize / 2) - (fontRenderer.getStringWidth(name) / 2), 6, 0x404040);
		fontRenderer.drawString(inventory.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
	}
}
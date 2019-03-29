package com.bewitchment.client.block.tile.gui;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.tile.container.ContainerApiary;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiApiary extends GuiContainer
{
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/apiary.png");
	
	public GuiApiary(ContainerApiary container)
	{
		super(container);
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
		mc.getTextureManager().bindTexture(TEX);
		drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
	}
}
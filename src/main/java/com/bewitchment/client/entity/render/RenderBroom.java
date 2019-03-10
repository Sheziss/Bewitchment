package com.bewitchment.client.entity.render;

import com.bewitchment.client.entity.model.ModelBroom;
import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.item.ItemBroom;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBroom extends Render<EntityBroom>
{
	private static final ModelBroom model = new ModelBroom();
	
	public RenderBroom(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBroom entity)
	{
		return ItemBroom.TEX.get(entity.getType() - 1);
	}
	
	@Override
	public void doRender(EntityBroom entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);

		float smoothYaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks;

		GlStateManager.translate(x, y - 0.5d, z);
		GlStateManager.scale(0.0625d, 0.0625d, 0.0625d);
		GlStateManager.rotate(90 - smoothYaw, 0, 1, 0);
		GlStateManager.disableLighting();
		model.render(entity, 0f, 0f, 0f, 0f, 0f, 1f);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
}
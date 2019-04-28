package com.bewitchment.client.render.entity.spirits.demons;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.model.entity.spirits.demons.ModelAlphaHellhound;
import com.bewitchment.common.entity.spirits.demons.EntityAlphaHellhound;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAlphaHellhound extends RenderLiving<EntityAlphaHellhound> {
	private static final ResourceLocation[] TEX = {new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_0.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_1.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_2.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_3.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_4.png"), new ResourceLocation(Bewitchment.MODID, "textures/entity/alpha_hellhound_5.png")};

	public RenderAlphaHellhound(RenderManager manager) {
		super(manager, new ModelAlphaHellhound(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAlphaHellhound entity) {
		return TEX[entity.getDataManager().get(EntityAlphaHellhound.SKIN)];
	}

	@Override
	protected void preRenderCallback(EntityAlphaHellhound entity, float partialTickTime) {
		super.preRenderCallback(entity, partialTickTime);
		GlStateManager.scale(1.6, 1.6, 1.6);
	}
}
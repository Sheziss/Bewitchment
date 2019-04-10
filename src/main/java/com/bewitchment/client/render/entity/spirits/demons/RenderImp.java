package com.bewitchment.client.render.entity.spirits.demons;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.model.entity.spirits.demons.ModelImp;
import com.bewitchment.common.entity.spirits.demons.EntityImp;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderImp extends RenderLiving<EntityImp> {
	private static final ResourceLocation[] TEX = {
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_0.png"),
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_1.png"),
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_2.png"),
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_3.png"),
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_4.png"),
			new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/imp_5.png")};

	public RenderImp(RenderManager manager) {
		super(manager, new ModelImp(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityImp entity) {
		return TEX[entity.getDataManager().get(EntityImp.SKIN)];
	}

	@Override
	protected void preRenderCallback(EntityImp entity, float partialTickTime) {
		super.preRenderCallback(entity, partialTickTime);
		GlStateManager.scale(1.6, 1.6, 1.6);
	}
}
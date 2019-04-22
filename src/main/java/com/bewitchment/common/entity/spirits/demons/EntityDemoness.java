package com.bewitchment.common.entity.spirits.demons;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDemoness extends EntityDemon {
	public EntityDemoness(World world) {
		super(world);
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender()
	{
		return 15728880;
	}


	public float getBrightness() {
		return 0.3F;
	}
}
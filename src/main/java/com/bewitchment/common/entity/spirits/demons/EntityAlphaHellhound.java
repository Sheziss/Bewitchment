package com.bewitchment.common.entity.spirits.demons;

import com.bewitchment.Bewitchment;

import com.bewitchment.common.entity.spirits.demons.EntityHellhound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityAlphaHellhound extends EntityHellhound
{
	private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
	
	public EntityAlphaHellhound(World world)
	{
		super(world, new ResourceLocation(Bewitchment.MOD_ID, "entities/alpha_hellhound"));
		setSize(0.96f, 1.36f);
		experienceValue = 65;
		bossInfo.setName(getDisplayName());
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		boolean flag = super.attackEntityAsMob(entity);
		if (flag)
		{
			if (entity instanceof EntityLivingBase && getAnimation() != BITE)
			{
				setAnimation(BITE);
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2000, 3, false, false));
				entity.setFire(20);
			}
		}
		return flag;
	}
	
	@Override
	public boolean isNonBoss()
	{
		return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}
	
	@Override
	public void addTrackingPlayer(EntityPlayerMP player)
	{
		super.addTrackingPlayer(player);
		bossInfo.addPlayer(player);
	}
	
	@Override
	public void removeTrackingPlayer(EntityPlayerMP player)
	{
		super.removeTrackingPlayer(player);
		bossInfo.removePlayer(player);
	}
	
	@Override
	public void setCustomNameTag(String name)
	{
		super.setCustomNameTag(name);
		bossInfo.setName(getDisplayName());
	}
	
	@Override
	protected void updateAITasks()
	{
		if (ticksExisted % 20 == 0) heal(2.5f);
		bossInfo.setPercent(getHealth() / getMaxHealth());
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.5);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.675);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48);
	}
}
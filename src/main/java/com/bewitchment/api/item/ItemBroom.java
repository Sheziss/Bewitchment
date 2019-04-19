package com.bewitchment.api.item;

import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModSounds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemBroom extends ModItem {
	public static final List<ResourceLocation> TEX = new ArrayList<>();

	public final int type;

	public ItemBroom(String modid, String name, CreativeTabs tab, ResourceLocation entity_texture, String... oreDictionaryNames) {
		super();
		setRegistryName(new ResourceLocation(modid, name));
		setTranslationKey(getRegistryName().toString().replace(":", "."));
		setCreativeTab(tab);
		for (String ore : oreDictionaryNames) getOreDictionaryNames().add(ore);
		ModObjects.REGISTRY.add(this);
		setCreativeTab(tab);
		setMaxStackSize(1);
		TEX.add(entity_texture);
		type = TEX.size();
		MinecraftForge.EVENT_BUS.register(this);
	}

	public abstract void handleMovement(EntityBroom broom, Vec3d look, float front, float strafe, float up);

	public abstract void onDismount(EntityBroom broom, EntityLivingBase mounter);

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hity, float hitZ) {
		if (world.getBlockState(pos).getBlock() == ModObjects.glyph || world.getBlockState(pos).getBlock() == ModObjects.salt_barrier) {
			world.setBlockToAir(pos);
			player.swingArm(hand);
			world.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSounds.BROOM_SWEEP, SoundCategory.BLOCKS, 0.8f, world.rand.nextFloat() * 0.4f + 0.8f, false);
			for (int i = 0; i < 1; i++)
				world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.1, pos.getZ() + world.rand.nextDouble(), 0, 0, 0);
		} else if (!world.isRemote) {
			EntityBroom entity = new EntityBroom(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (ItemBroom) player.getHeldItem(hand).getItem());
			entity.setRotationYawHead(player.rotationYaw);
			world.spawnEntity(entity);
			player.getHeldItem(hand).shrink(1);
		}
		return EnumActionResult.SUCCESS;
	}

	@SubscribeEvent(receiveCanceled = false, priority = EventPriority.LOWEST)
	public void unmount(EntityMountEvent event) {
		if (event.getEntityBeingMounted() instanceof EntityBroom && event.isDismounting()) {
			EntityBroom broom = (EntityBroom) event.getEntityBeingMounted();
			EntityPlayer source = (EntityPlayer) event.getEntityMounting();
			if (!source.isDead) {
				if (broom.item != null) ((ItemBroom) broom.item.getItem()).onDismount(broom, source);
				if (!broom.world.isRemote) {
					EntityItem entity = new EntityItem(broom.world, source.posX, source.posY, source.posZ, broom.item);
					broom.world.spawnEntity(entity);
					broom.setDead();
					entity.onCollideWithPlayer(source);
				}
			}
		}
	}
}
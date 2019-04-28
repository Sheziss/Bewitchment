package com.bewitchment.api.item;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.entity.misc.EntityBroom;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class ItemBroom extends ModItem {
	private final EntityEntry entry;

	public ItemBroom(String modid, String name, CreativeTabs tab, EntityEntry entry) {
		super();
		setRegistryName(new ResourceLocation(modid, name));
		setTranslationKey(getRegistryName().toString().replace(":", "."));
		setCreativeTab(tab);
		ModObjects.REGISTRY.add(this);
		setCreativeTab(tab);
		if (entry != null) setMaxStackSize(1);
		this.entry = entry;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (Bewitchment.proxy.config.broomSweepables.contains(block.getTranslationKey())) {
			if (!world.isRemote) {
				block.dropBlockAsItem(world, pos, state, 0);
				world.setBlockToAir(pos);
				player.swingArm(hand);
				world.playSound(null, pos, ModSounds.BROOM_SWEEP, SoundCategory.BLOCKS, 0.8f, world.rand.nextFloat() * 0.4f + 0.8f);
			}
			else for (int i = 0; i < 1; i++)
			          world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.1, pos.getZ() + world.rand.nextDouble(), 0, 0, 0);
			return EnumActionResult.SUCCESS;
		}
		else if (entry != null) {
			Entity entity = entry.newInstance(world);
			entity.processInitialInteract(player, hand);
			if (!world.isRemote) {
				entity.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				world.spawnEntity(entity);
			}
			return EnumActionResult.SUCCESS;
		}
		return super.onItemUse(player, world, pos, hand, face, hitX, hitY, hitZ);
	}

	@SubscribeEvent
	public void unmount(EntityMountEvent event) {
		if (!event.getWorldObj().isRemote && event.getEntityBeingMounted() instanceof EntityBroom && event.isDismounting()) event.getEntityBeingMounted().setDead();
	}
}
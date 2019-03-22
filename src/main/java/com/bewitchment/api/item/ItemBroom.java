package com.bewitchment.api.item;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.item.util.ModItem;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModSounds;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class ItemBroom extends ModItem
{
	public static final List<ResourceLocation> TEX = new ArrayList<>();
	
	public final int type;
	
	public ItemBroom(String modid, String name, CreativeTabs tab, ResourceLocation entity_texture, String... oreDictionaryNames)
	{
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
	}
	
	public abstract void handleMovement(EntityBroom broom, Vec3d look, float front, float strafe, float up);
	
	public abstract void onDismount(EntityBroom broom, EntityLivingBase mounter);
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing face, float hitX, float hity, float hitZ)
	{
		if (world.getBlockState(pos).getBlock() == ModObjects.glyph || world.getBlockState(pos).getBlock() == ModObjects.salt_barrier)
		{
			world.setBlockToAir(pos);
			player.swingArm(hand);
			world.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSounds.BROOM_SWEEP, SoundCategory.BLOCKS, 0.8f, world.rand.nextFloat() * 0.4f + 0.8f, false);
			for (int i = 0; i < 1; i++) world.spawnParticle(EnumParticleTypes.SWEEP_ATTACK, pos.getX() + world.rand.nextDouble(), pos.getY() + 0.1, pos.getZ() + world.rand.nextDouble(), 0, 0, 0);
		}
		else if (!world.isRemote)
		{
			EntityBroom entity = new EntityBroom(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, (ItemBroom) player.getHeldItem(hand).getItem());
			entity.setRotationYawHead(player.rotationYaw);
			world.spawnEntity(entity);
			player.getHeldItem(hand).shrink(1);
		}
		return EnumActionResult.SUCCESS;
	}
}
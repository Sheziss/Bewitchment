package com.bewitchment.common.block.tile.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityGlyph extends ModTileEntity implements ITickable
{
	public static final int[][] small = {
			{0,0,1,1,1,0,0},
			{0,1,0,0,0,1,0},
			{1,0,0,0,0,0,1},
			{1,0,0,0,0,0,1},
			{1,0,0,0,0,0,1},
			{0,1,0,0,0,1,0},
			{0,0,1,1,1,0,0}};
	public static final int[][] medium = {
			{0,0,0,1,1,1,1,1,0,0,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,1,0,0,0,0,0,0,0,1,0},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{0,1,0,0,0,0,0,0,0,1,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,0,0,1,1,1,1,1,0,0,0}};
	public static final int[][] large = {
			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0}};
	
	public Ritual ritual;
	private BlockPos effective_pos = getPos();
	private UUID caster;
	private int cooldown = -1;
	
	public final ItemStackHandler inventory = new ItemStackHandler(Byte.MAX_VALUE);
	
	@Override
	public void update()
	{
		if (!world.isRemote && ritual != null && caster != null)
		{
			EntityPlayer player = world.getPlayerEntityByUUID(caster);
			if (MagicPower.attemptDrain(world, player, getPos(), ritual.getRunningPower() * (getEffectivePosition() == getPos() ? 1 : MathHelper.ceil(getEffectivePosition().distanceSq(getPos()) / 400))))
			{
				ritual.onUpdate(this, player);
				cooldown--;
			}
			else if (ritual.onLowPower(this, player)) stopRitual(player, false);
			if (cooldown <= 0 && ritual.getTime() >= 0) stopRitual(player, true);
		}
		else if (world.isRemote && ritual != null) ritual.onRandomDisplayTick(this);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setString("ritual", ritual == null ? "" : ritual.getRegistryName().toString());
		tag.setIntArray("effective_pos", new int[] {getEffectivePosition().getX(), getEffectivePosition().getY(), getEffectivePosition().getZ()});
		tag.setString("caster", caster == null ? "" : caster.toString());
		tag.setInteger("cooldown", cooldown);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		ritual = tag.getString("ritual") == "" ? null : BewitchmentAPI.REGISTRY_RITUAL.getValue(new ResourceLocation(tag.getString("ritual")));
		int[] poses = tag.getIntArray("effective_pos");
		setEffectivePosition(new BlockPos(poses[0], poses[1], poses[2]));
		caster = tag.getString("caster") == "" ? null : UUID.fromString(tag.getString("caster"));
		cooldown = tag.getInteger("cooldown");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory};
	}
	
	public void startRitual(EntityPlayer player)
	{
		if (!world.isRemote && cooldown < 0)
		{
			world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
			ritual = null;
			List<ItemStack> items_on_ground = new ArrayList<>();
			for (EntityItem item : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos()).grow(3, 0, 3))) items_on_ground.add(item.getItem().copy().splitStack(1));
			List<EntityLivingBase> living_on_ground = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
			for (Ritual rit : BewitchmentAPI.REGISTRY_RITUAL)
			{
				boolean valid = true;
				for (int x = 0; x < small.length; x++)
				{
					for (int z = 0; z < small.length; z++)
					{
						IBlockState state = world.getBlockState(getPos().add(x - small.length / 2, 0, z - small.length / 2));
						if (small[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != rit.getCircles()[0] && rit.getCircles()[0] != GlyphType.ANY)))) valid = false;
					}
				}
				if (valid)
				{
					if (rit.getCircles()[1] != null)
					{
						for (int x = 0; x < medium.length; x++)
						{
							for (int z = 0; z < medium.length; z++)
							{
								IBlockState state = world.getBlockState(getPos().add(x - medium.length / 2, 0, z - medium.length / 2));
								if (medium[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != rit.getCircles()[1] && rit.getCircles()[1] != GlyphType.ANY)))) valid = false;
							}
						}
					}
					if (valid)
					{
						if (rit.getCircles()[2] != null)
						{
							for (int x = 0; x < large.length; x++)
							{
								for (int z = 0; z < large.length; z++)
								{
									IBlockState state = world.getBlockState(getPos().add(x - large.length / 2, 0, z - large.length / 2));
									if (large[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != rit.getCircles()[2] && rit.getCircles()[2] != GlyphType.ANY)))) valid = false;
								}
							}
						}
						if (valid)
						{
							if (items_on_ground.size() != rit.getInputItems().size()) valid = false;
							if (valid)
							{
								if (Bewitchment.proxy.areISListsEqual(rit.getInputItems(), items_on_ground))
								{
									if (rit.getInputEntities().length != 0)
									{
										boolean found = false;
										for (EntityLivingBase entity : living_on_ground) if (Arrays.stream(rit.getInputEntities()).anyMatch(EntityRegistry.getEntry(entity.getClass())::equals)) found = true;
										valid = found;
									}
									if (valid)
									{
										ritual = rit;
										break;
									}
								}
							}
						}
					}
				}
			}
			if (ritual != null)
			{
				if (ritual.isValid(this, player))
				{
					if (MagicPower.attemptDrain(world, player, getPos(), ritual.getStartingPower() * (getEffectivePosition() == getPos() ? 1 : MathHelper.ceil(getEffectivePosition().distanceSq(getPos()) / 400))))
					{
						caster = player.getPersistentID();
						cooldown = ritual.getTime();
						ritual.onStarted(this, player);
						world.playSound(null, getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.7f, 0.7f);
						player.sendStatusMessage(new TextComponentTranslation(ritual.getRegistryName().toString()), true);
						for (ItemStack stack : items_on_ground) inventory.insertItem(getFirstEmptySlot(inventory), stack.splitStack(1), false);
						if (ritual.getInputEntities().length != 0)
						{
							for (EntityLivingBase entity : living_on_ground)
							{
								if (Arrays.stream(ritual.getInputEntities()).anyMatch(EntityRegistry.getEntry(entity.getClass())::equals))
								{
									entity.attackEntityFrom(DamageSource.MAGIC, Integer.MAX_VALUE);
									break;
								}
							}
						}
					}
					else player.sendStatusMessage(new TextComponentTranslation("magic.no_power"), true);
				}
				else player.sendStatusMessage(new TextComponentTranslation("ritual.precondition"), true);
			}
			else player.sendStatusMessage(new TextComponentTranslation("ritual.invalid_input"), true);
		}
	}
	
	public void stopRitual(EntityPlayer player, boolean finished)
	{
		world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
		if (ritual != null)
		{
			if (finished)
			{
				ritual.onFinished(this, player);
				for (ItemStack stack : ritual.getOutput(this)) InventoryHelper.spawnItemStack(world, getPos().getX(), getPos().getY(), getPos().getZ(), stack);
			}
			else
			{
				ritual.onStopped(this, player);
				for (int i = 0; i < inventory.getSlots(); i++) InventoryHelper.spawnItemStack(world, getPos().getX(), getPos().getY(), getPos().getZ(), inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false));
			}
		}
		clear(inventory);
		setEffectivePosition(getPos());
		ritual = null;
		caster = null;
		cooldown = -1;
	}
	
	public BlockPos getEffectivePosition()
	{
		return effective_pos;
	}
	
	public void setEffectivePosition(BlockPos pos)
	{
		effective_pos = pos;
	}
}
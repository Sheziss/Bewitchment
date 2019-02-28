package com.bewitchment.registry.block.tile;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.core.Bewitchment.API.DistilleryRecipe;
import com.bewitchment.core.CommonProxy.ModGui;
import com.bewitchment.registry.block.ModBlock;
import com.bewitchment.registry.capability.IMagicPower;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class BlockDistillery extends ModBlock implements ITileEntityProvider
{
	private static final AxisAlignedBB BBOX_X = new AxisAlignedBB(0.125, 0, 0, 0.875, 0.6875, 1), BBOX_Z = new AxisAlignedBB(0, 0, 0.125, 1, 0.6875, 0.875);
	
	public BlockDistillery(String name)
	{
		super(name, Material.IRON, SoundType.METAL, 5, 5, "pickaxe", 0);
		this.setDefaultState(blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new Tile();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		player.openGui(Bewitchment.instance, ModGui.DISTILLERY.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && hasTileEntity(state) && world.getTileEntity(pos) instanceof Tile)
		{
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) world.getTileEntity(pos));
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(entity.rotationYaw));
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[meta & 3]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}
	
	public static class Tile extends TileEntity implements ISidedInventory, ITickable
	{
		public static final int BURN_TIME = 1200;
		
		private IMagicPower magic_power = IMagicPower.Provider.CAPABILITY.getDefaultInstance();
		private DistilleryRecipe recipe;
		
		private int progress, burn_time, recipe_time;
		
		public final NonNullList<ItemStack> inventory = NonNullList.withSize(13, ItemStack.EMPTY);
		
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound tag)
		{
			markDirty();
			ItemStackHelper.loadAllItems(tag, inventory);
			tag.setInteger("progress", progress);
			tag.setInteger("recipe_time", recipe == null ? 0 : recipe.getTime());
			tag.setInteger("burn_time", burn_time);
			tag.setInteger("power", magic_power.getAmount());
			return super.writeToNBT(tag);
		}
		
		@Override
		public void readFromNBT(NBTTagCompound tag)
		{
			super.readFromNBT(tag);
			ItemStackHelper.saveAllItems(tag, inventory);
			progress = tag.getInteger("progress");
			recipe_time = tag.getInteger("recipe_time");
			burn_time = tag.getInteger("burn_time");
			magic_power.setAmount(tag.getInteger("power"));
		}
		
		@Override
		public SPacketUpdateTileEntity getUpdatePacket()
		{
			return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
		}
		
		@Override
		public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
		{
			readFromNBT(packet.getNbtCompound());
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == IMagicPower.Provider.CAPABILITY || super.hasCapability(capability, face);
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (face == world.getBlockState(pos).getValue(BlockHorizontal.FACING) ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new SidedInvWrapper(this, face)) : CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new InvWrapper(this))) : capability == IMagicPower.Provider.CAPABILITY ? IMagicPower.Provider.CAPABILITY.cast(magic_power) : super.getCapability(capability, face);
		}
		
		@Override
		public void update()
		{
			if (burn_time > 0)
			{
				burn_time--;
				markDirty();
			}
			if (recipe != null)
			{
				if (burn_time == 0) inventory.get(0).shrink(1);
				else if (burn_time > 0)
				{
					if (progress > 0)
					{
						if (true) // mp.drainAltarFirst(this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 5, false), this.getPos(), this.world.provider.getDimension(), 2)) {
						{
							progress--;
							markDirty();
						}
					}
					else
					{
						progress = 0;
						checkRecipe();
					}
				}
				markDirty();
			}
		}
		
		private void checkRecipe()
		{
			for (DistilleryRecipe recipe : Bewitchment.API.REGISTRY_DISTILLERY)
			{
				List<ItemStack> inputStacks = new ArrayList<ItemStack>();
				for (int i = 1; i < 8; i++) inputStacks.add(inventory.get(i));
				if (recipe.getInput().containsAll(inputStacks) && recipe.getInput().size() == inputStacks.size())
				{
					this.recipe = recipe;
					progress = recipe.getTime();
					for (int i = 1; i < 8; i++) inventory.get(i).shrink(1);
					for (ItemStack stack : recipe.getOutput()) for (int i = 8; i < inventory.size(); i++) inventory.add(i, stack);
				}
			}
		}
		
		@Override
		public int getSizeInventory()
		{
			return inventory.size();
		}
		
		@Override
		public boolean isEmpty()
		{
			return !inventory.isEmpty();
		}
		
		@Override
		public ItemStack getStackInSlot(int index)
		{
			return inventory.get(index);
		}
		
		@Override
		public ItemStack decrStackSize(int index, int count)
		{
			return ItemStackHelper.getAndSplit(inventory, index, count);
		}
		
		@Override
		public ItemStack removeStackFromSlot(int index)
		{
			return ItemStackHelper.getAndRemove(inventory, index);
		}
		
		@Override
		public void setInventorySlotContents(int index, ItemStack stack)
		{
			inventory.set(index, stack);
		}
		
		@Override
		public int getInventoryStackLimit()
		{
			return 64;
		}
		
		@Override
		public boolean isUsableByPlayer(EntityPlayer player)
		{
			return !player.isSpectator();
		}
		
		@Override
		public void openInventory(EntityPlayer player)
		{
		}
		
		@Override
		public void closeInventory(EntityPlayer player)
		{
		}
		
		@Override
		public boolean isItemValidForSlot(int index, ItemStack stack)
		{
			return index == 0 ? stack.getItem() == Items.BLAZE_POWDER : index < 7;
		}
		
		@Override
		public int getField(int id)
		{
			return 0;
		}
		
		@Override
		public void setField(int id, int value)
		{
		}
		
		@Override
		public int getFieldCount()
		{
			return 0;
		}
		
		@Override
		public void clear()
		{
			inventory.clear();
		}
		
		@Override
		public String getName()
		{
			return world.getBlockState(pos).getBlock().getTranslationKey();
		}
		
		@Override
		public boolean hasCustomName()
		{
			return false;
		}
		
		@Override
		public int[] getSlotsForFace(EnumFacing face)
		{
			return face == world.getBlockState(pos).getValue(BlockHorizontal.FACING) ? new int[]{0} : new int[]{1, 2, 3, 4, 5, 6};
		}
		
		@Override
		public boolean canInsertItem(int index, ItemStack stack, EnumFacing face)
		{
			return isItemValidForSlot(index, stack);
		}
		
		@Override
		public boolean canExtractItem(int index, ItemStack stack, EnumFacing face)
		{
			return isItemValidForSlot(index, stack);
		}
	}
	
	public static class Container extends net.minecraft.inventory.Container
	{
		public final Tile tile;
		
		public Container(InventoryPlayer inventory, Tile tile)
		{
			this.tile = tile;
			int index = 0;
			addSlotToContainer(new ModSlot(tile, index++, 80, 58));
			for (int i = 0; i < 3; i++)
			{
				addSlotToContainer(new ModSlot(tile, index++, 18, (18 * (i + 1)) - 1));
				addSlotToContainer(new ModSlot(tile, index++, 36, (18 * (i + 1)) - 1));
			}
			for (int i = 0; i < 3; i++)
			{
				addSlotToContainer(new ModSlot(tile, index++, 124, (18 * (i + 1)) - 1));
				addSlotToContainer(new ModSlot(tile, index++, 142, (18 * (i + 1)) - 1));
			}
			for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			for (int i = 0; i < 9; i++) addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
		
		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int index)
	    {
			return ItemStack.EMPTY;
	    }
		
		@Override
		public boolean canInteractWith(EntityPlayer player)
		{
			return tile.isUsableByPlayer(player);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class Gui extends GuiContainer
	{
		private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/distillery.png");
		
		private final Container container;
		
		public Gui(Container container)
		{
			super(container);
			this.container = container;
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
			GlStateManager.color(1, 1, 1);
			mc.getTextureManager().bindTexture(TEX);
			int x = (width - xSize) / 2;
			int y = (height - ySize) / 2;
			drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
			drawTexturedModalRect(x + 76, y + 16, 176, 0, (container.tile.progress * 24 / Math.max(1, container.tile.recipe_time)) + 1, 17);
			int burnProgress = 14 - (int) Math.ceil((14 * (container.tile.burn_time / (double) Tile.BURN_TIME)));
			drawTexturedModalRect(x + 81, y + 36 + burnProgress, 242, burnProgress, 14, 14 - burnProgress);
		}
	}
}
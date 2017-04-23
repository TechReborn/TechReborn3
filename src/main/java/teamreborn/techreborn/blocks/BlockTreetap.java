package teamreborn.techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;
import teamreborn.techreborn.tile.TileTreetap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockTreetap extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private static final AxisAlignedBB northAABB = new AxisAlignedBB(0.40625, 0.1250, 0, 0.65625, 0.375, 0.3975);
	private static final AxisAlignedBB westAABB = new AxisAlignedBB(0, 0.1250, 0.59375, 0.3975, 0.375, 0.34375);
	private static final AxisAlignedBB southAABB = new AxisAlignedBB(0.59375, 0.1250, 1, 0.34375, 0.375, 0.6025);
	private static final AxisAlignedBB eastAABB = new AxisAlignedBB(1, 0.1250, 0.40625, 0.6025, 0.375, 0.65625);
	@BlockRegistry(itemBlock = "teamreborn.techreborn.item.ItemTreetap")
	public static BlockTreetap treetap;

	public BlockTreetap() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "treetap"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		setSoundType(SoundType.WOOD);
		TechReborn.blockModelsToRegister.add(this);
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileTreetap();
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (placer.getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
			placer.getHeldItem(EnumHand.OFF_HAND).damageItem(1, placer);
		}
		if (placer.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
			placer.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, placer);
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, 0);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<>();
		list.add(ItemStack.EMPTY);
		return list;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(FACING, facing.getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(FACING) == EnumFacing.NORTH) {
			return northAABB;
		}
		if (state.getValue(FACING) == EnumFacing.EAST) {
			return eastAABB;
		}
		if (state.getValue(FACING) == EnumFacing.SOUTH) {
			return southAABB;
		}
		if (state.getValue(FACING) == EnumFacing.WEST) {
			return westAABB;
		}
		return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}

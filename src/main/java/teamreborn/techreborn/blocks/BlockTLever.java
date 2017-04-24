package teamreborn.techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.reborncore.transmission.EnumFrequency;
import teamreborn.reborncore.transmission.ITransmissionReciever;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

import javax.annotation.Nullable;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockTLever extends Block {

	public static final PropertyEnum<BlockLever.EnumOrientation> FACING = PropertyEnum.<BlockLever.EnumOrientation>create("facing", BlockLever.EnumOrientation.class);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	protected static final AxisAlignedBB LEVER_NORTH_AABB = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.625D, 0.6875D, 0.800000011920929D, 1.0D);
	protected static final AxisAlignedBB LEVER_SOUTH_AABB = new AxisAlignedBB(0.3125D, 0.20000000298023224D, 0.0D, 0.6875D, 0.800000011920929D, 0.375D);
	protected static final AxisAlignedBB LEVER_WEST_AABB = new AxisAlignedBB(0.625D, 0.20000000298023224D, 0.3125D, 1.0D, 0.800000011920929D, 0.6875D);
	protected static final AxisAlignedBB LEVER_EAST_AABB = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3125D, 0.375D, 0.800000011920929D, 0.6875D);
	protected static final AxisAlignedBB LEVER_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.6000000238418579D, 0.75D);
	protected static final AxisAlignedBB LEVER_DOWN_AABB = new AxisAlignedBB(0.25D, 0.4000000059604645D, 0.25D, 0.75D, 1.0D, 0.75D);

	@BlockRegistry
	public static BlockTLever transmission_lever;

	public BlockTLever() {
		super(Material.CIRCUITS);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "transmission_lever"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(0.2F);
		TechReborn.blockModelsToRegister.add(this);
	}

	protected static boolean canAttachTo(World worldIn, BlockPos pos, EnumFacing direction) {
		BlockPos blockpos = pos.offset(direction);
		return worldIn.getBlockState(blockpos).isSideSolid(worldIn, blockpos, direction.getOpposite());
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		state = state.cycleProperty(POWERED);
		worldIn.setBlockState(pos, state, 3);
		float f = ((Boolean) state.getValue(POWERED)).booleanValue() ? 0.6F : 0.5F;
		worldIn.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
		for (EnumFacing offset : EnumFacing.values()) {
			if (worldIn.getTileEntity(pos.offset(offset)) instanceof ITransmissionReciever) {
				TileEntity tileEntity = worldIn.getTileEntity(pos.offset(offset));
				((ITransmissionReciever) tileEntity).setPowered(EnumFrequency.ALPHA, state.getValue(POWERED));
			}
		}
		return true;
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState iblockstate = this.getDefaultState().withProperty(POWERED, Boolean.valueOf(false));

		if (canAttachTo(worldIn, pos, facing.getOpposite())) {
			return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(facing, placer.getHorizontalFacing()));
		} else {
			for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
				if (enumfacing != facing && canAttachTo(worldIn, pos, enumfacing.getOpposite())) {
					return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(enumfacing, placer.getHorizontalFacing()));
				}
			}

			if (worldIn.getBlockState(pos.down()).isFullyOpaque()) {
				return iblockstate.withProperty(FACING, BlockLever.EnumOrientation.forFacings(EnumFacing.UP, placer.getHorizontalFacing()));
			} else {
				return iblockstate;
			}
		}
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (this.checkCanSurvive(worldIn, pos, state) && !canAttachTo(worldIn, pos, ((BlockLever.EnumOrientation) state.getValue(FACING)).getFacing().getOpposite())) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}

	private boolean checkCanSurvive(World p_181091_1_, BlockPos p_181091_2_, IBlockState p_181091_3_) {
		if (this.canPlaceBlockAt(p_181091_1_, p_181091_2_)) {
			return true;
		} else {
			this.dropBlockAsItem(p_181091_1_, p_181091_2_, p_181091_3_, 0);
			p_181091_1_.setBlockToAir(p_181091_2_);
			return false;
		}
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((BlockLever.EnumOrientation) state.getValue(FACING)) {
			case EAST:
			default:
				return LEVER_EAST_AABB;
			case WEST:
				return LEVER_WEST_AABB;
			case SOUTH:
				return LEVER_SOUTH_AABB;
			case NORTH:
				return LEVER_NORTH_AABB;
			case UP_Z:
			case UP_X:
				return LEVER_UP_AABB;
			case DOWN_X:
			case DOWN_Z:
				return LEVER_DOWN_AABB;
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, BlockLever.EnumOrientation.byMetadata(meta & 7)).withProperty(POWERED, Boolean.valueOf((meta & 8) > 0));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((BlockLever.EnumOrientation) state.getValue(FACING)).getMetadata();

		if (((Boolean) state.getValue(POWERED)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
			case CLOCKWISE_180:

				switch ((BlockLever.EnumOrientation) state.getValue(FACING)) {
					case EAST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.WEST);
					case WEST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.EAST);
					case SOUTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.NORTH);
					case NORTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.SOUTH);
					default:
						return state;
				}

			case COUNTERCLOCKWISE_90:

				switch ((BlockLever.EnumOrientation) state.getValue(FACING)) {
					case EAST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.NORTH);
					case WEST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.SOUTH);
					case SOUTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.EAST);
					case NORTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.WEST);
					case UP_Z:
						return state.withProperty(FACING, BlockLever.EnumOrientation.UP_X);
					case UP_X:
						return state.withProperty(FACING, BlockLever.EnumOrientation.UP_Z);
					case DOWN_X:
						return state.withProperty(FACING, BlockLever.EnumOrientation.DOWN_Z);
					case DOWN_Z:
						return state.withProperty(FACING, BlockLever.EnumOrientation.DOWN_X);
				}

			case CLOCKWISE_90:

				switch ((BlockLever.EnumOrientation) state.getValue(FACING)) {
					case EAST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.SOUTH);
					case WEST:
						return state.withProperty(FACING, BlockLever.EnumOrientation.NORTH);
					case SOUTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.WEST);
					case NORTH:
						return state.withProperty(FACING, BlockLever.EnumOrientation.EAST);
					case UP_Z:
						return state.withProperty(FACING, BlockLever.EnumOrientation.UP_X);
					case UP_X:
						return state.withProperty(FACING, BlockLever.EnumOrientation.UP_Z);
					case DOWN_X:
						return state.withProperty(FACING, BlockLever.EnumOrientation.DOWN_Z);
					case DOWN_Z:
						return state.withProperty(FACING, BlockLever.EnumOrientation.DOWN_X);
				}

			default:
				return state;
		}
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	 * blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(((BlockLever.EnumOrientation) state.getValue(FACING)).getFacing()));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, POWERED });
	}

	private boolean canAttach(World world, BlockPos pos, EnumFacing side) {
		return world.isSideSolid(pos, side);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/**
	 * Check whether this Block can be placed on the given side
	 */
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return canAttachTo(worldIn, pos, side.getOpposite());
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.values()) {
			if (canAttachTo(worldIn, pos, enumfacing)) {
				return true;
			}
		}

		return false;
	}
}

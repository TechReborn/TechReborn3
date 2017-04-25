package teamreborn.techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;
import teamreborn.techreborn.fluids.TechRebornFluids;
import teamreborn.techreborn.tile.TileBarrel;

import javax.annotation.Nullable;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockBarrel extends Block implements ITileEntityProvider {

	public static final PropertyInteger FILLED_HEIGHT = PropertyInteger.create("filled", 0, 4);

	@BlockRegistry
	public static BlockBarrel barrel;

	public BlockBarrel() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "barrel"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setHarvestLevel("axe", 0);
		setHardness(2.0F);
		setResistance(10);
		setSoundType(SoundType.WOOD);
		TechReborn.blockModelsToRegister.add(this);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileBarrel tile = (TileBarrel) worldIn.getTileEntity(pos);
		return super.getActualState(state, worldIn, pos);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;
		((TileBarrel) worldIn.getTileEntity(pos)).tank.fill(new FluidStack(TechRebornFluids.SAP.fluid, 100), true);
		playerIn.sendMessage(new TextComponentString(TextFormatting.GRAY + "Sap Amount: " + TextFormatting.GOLD + ((TileBarrel) worldIn.getTileEntity(pos)).tank.getFluidAmount()));
		return true;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBarrel();
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0, 0.125, 0.875, 1, 0.875);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}

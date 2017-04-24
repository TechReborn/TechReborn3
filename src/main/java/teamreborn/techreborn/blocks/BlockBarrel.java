package teamreborn.techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;
import teamreborn.techreborn.tile.TileBarrel;

import javax.annotation.Nullable;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockBarrel extends Block implements ITileEntityProvider {

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

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileBarrel();
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

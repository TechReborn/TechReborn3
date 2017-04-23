package teamreborn.techreborn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import teamreborn.reborncore.api.registry.LoadOrderedRegistry;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockBarrel extends Block {

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

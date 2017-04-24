package teamreborn.techreborn.blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

import java.util.List;
import java.util.Random;

/**
 * Created by modmuss50 on 24/04/2017.
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockRubberLeaves extends BlockLeaves {

	@BlockRegistry
	public static BlockRubberLeaves rubberLeaves;

	public BlockRubberLeaves() {
		super();
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_leaves"));
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		this.setDefaultState(this.getDefaultState().withProperty(CHECK_DECAY, true)
			.withProperty(DECAYABLE, true));
		Blocks.FIRE.setFireInfo(this, 30, 60);
		TechReborn.blockModelsToRegister.add(this);
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		List<ItemStack> list = new java.util.ArrayList<>();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube() {
		return false;
	}


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) == 0)
			.withProperty(CHECK_DECAY, (meta & 2) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;
		if (!state.getValue(DECAYABLE)) {
			meta |= 1;
		}
		if (state.getValue(CHECK_DECAY)) {
			meta |= 2;
		}
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state) {
		return 16777215;
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return 16777215;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.SAPLING);
	}
}

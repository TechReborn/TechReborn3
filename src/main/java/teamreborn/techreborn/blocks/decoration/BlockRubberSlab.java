package teamreborn.techreborn.blocks.decoration;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamreborn.reborncore.api.registry.LoadOrderedRegistry;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.CustomRegistry;
import teamreborn.reborncore.block.RebornBlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

import java.util.Random;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
@LoadOrderedRegistry("after:teamreborn.techreborn.blocks.decoration.BlockRubberPlanks")
public abstract class BlockRubberSlab extends BlockSlab {
	public static final PropertyEnum<BlockRubberSlab.Variant> VARIANT = PropertyEnum.<BlockRubberSlab.Variant>create("variant", BlockRubberSlab.Variant.class);
	public static BlockRubberSlab.Half rubber_slab;
	public static BlockRubberSlab.Double double_rubber_slab;

	public BlockRubberSlab() {
		super(Material.WOOD, MapColor.WOOD);
		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
			setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_slab"));
		} else {
			setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_double_slab"));
		}
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setHarvestLevel("axe", 0);
		setHardness(2.0F);
		setResistance(15);
		setSoundType(SoundType.WOOD);
		TechReborn.blockModelsToRegister.add(this);

		this.setDefaultState(iblockstate.withProperty(VARIANT, BlockRubberSlab.Variant.DEFAULT));
	}

	@CustomRegistry
	public static void init() {
		rubber_slab = new BlockRubberSlab.Half();
		double_rubber_slab = new BlockRubberSlab.Double();
		RebornBlockRegistry.registerBlockNoItemBlock(rubber_slab);
		RebornBlockRegistry.registerBlockNoItemBlock(double_rubber_slab);
		GameRegistry.register(new ItemSlab(rubber_slab, rubber_slab, double_rubber_slab), rubber_slab.getRegistryName());

	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(rubber_slab);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(rubber_slab);
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockRubberSlab.Variant.DEFAULT);

		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
			i |= 8;
		}

		return i;
	}

	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] { VARIANT }) : new BlockStateContainer(this, new IProperty[] { HALF, VARIANT });
	}

	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	public Comparable<?> getTypeForItem(ItemStack stack) {
		return BlockRubberSlab.Variant.DEFAULT;
	}

	public static enum Variant implements IStringSerializable {
		DEFAULT;

		public String getName() {
			return "default";
		}
	}

	public static class Double extends BlockRubberSlab {
		public boolean isDouble() {
			return true;
		}
	}

	public static class Half extends BlockRubberSlab {
		public boolean isDouble() {
			return false;
		}
	}
}
package teamreborn.techreborn.blocks.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
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
@LoadOrderedRegistry("before:teamreborn.techreborn.blocks.decoration.BlockRubberStairs")
public class BlockRubberPlanks extends Block {

	@BlockRegistry
	public static BlockRubberPlanks rubber_planks;

	public BlockRubberPlanks() {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_planks"));
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setDefaultState(blockState.getBaseState());
		setHarvestLevel("axe", 0);
		setHardness(2.0F);
		setResistance(15);
		setSoundType(SoundType.WOOD);
		TechReborn.blockModelsToRegister.add(this);
	}

	public MapColor getMapColor(IBlockState state) {
		return MapColor.WOOD;
	}
}

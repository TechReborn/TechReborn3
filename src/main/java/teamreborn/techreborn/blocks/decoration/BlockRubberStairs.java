package teamreborn.techreborn.blocks.decoration;

import net.minecraft.block.BlockStairs;
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
@LoadOrderedRegistry("after:teamreborn.techreborn.blocks.decoration.BlockRubberPlanks")
public class BlockRubberStairs extends BlockStairs {

	@BlockRegistry
	public static BlockRubberStairs rubber_stairs;

	public BlockRubberStairs() {
		super(BlockRubberPlanks.rubber_planks.getDefaultState());
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_stairs"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		TechReborn.blockModelsToRegister.add(this);
	}
}
package teamreborn.techreborn.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechRebornCreativeTab;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(modID = TRConstants.MOD_ID)
public class BlockCable extends Block{

	@BlockRegistry
	public static BlockCable cable;

	public BlockCable() {
		super(Material.ROCK);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "cable"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
	}
}

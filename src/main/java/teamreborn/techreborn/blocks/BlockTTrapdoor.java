package teamreborn.techreborn.blocks;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;
import teamreborn.techreborn.tile.TileTTrapdoor;

import javax.annotation.Nullable;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class BlockTTrapdoor extends BlockTrapDoor implements ITileEntityProvider {

	@BlockRegistry
	public static BlockTTrapdoor transmission_trapdoor;

	public BlockTTrapdoor() {
		super(Material.CIRCUITS);
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "transmission_trapdoor"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(1.5F);
		setResistance(10);
		setSoundType(SoundType.WOOD);
		TechReborn.blockModelsToRegister.add(this);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTTrapdoor();
	}
}

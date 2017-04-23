package teamreborn.techreborn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import teamreborn.reborncore.api.power.IGridConnection;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;
import teamreborn.techreborn.tile.TileTestProvider;
import teamreborn.techreborn.tile.TileTestReciever;

import javax.annotation.Nullable;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(TRConstants.MOD_ID)
public class TestPoweredBlock extends BlockContainer {

	@BlockRegistry(param = "provider")
	public static TestPoweredBlock provider;

	@BlockRegistry(param = "reciever")
	public static TestPoweredBlock reciever;

	String type;

	public TestPoweredBlock(String type) {
		super(Material.ROCK);
		this.type = type;
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, type));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		TechReborn.blockModelsToRegister.add(this);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if (type.equals("reciever")) {
			return new TileTestReciever();
		}
		return new TileTestProvider();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
			if (tileEntity instanceof IGridConnection) {
				if (((IGridConnection) tileEntity).getPowerGrid() != null) {
					playerIn.sendMessage(new TextComponentString(((IGridConnection) tileEntity).getPowerGrid().name));
				} else {
					playerIn.sendMessage(new TextComponentString("null"));
				}

			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}

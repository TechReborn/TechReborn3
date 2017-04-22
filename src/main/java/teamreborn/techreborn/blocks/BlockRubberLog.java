package teamreborn.techreborn.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.BlockRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(modID = TRConstants.MOD_ID)
public class BlockRubberLog extends BlockLog {

	public static PropertyEnum<EnumSapSide> SAP_SIDE = PropertyEnum.create("sapside", EnumSapSide.class);

	@BlockRegistry
	public static BlockRubberLog rubber_log;

	public BlockRubberLog() {
		super();
		setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubber_log"));
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		setUnlocalizedName(getRegistryName().toString());
		setDefaultState(this.blockState.getBaseState().withProperty(SAP_SIDE, EnumSapSide.NONE).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		TechReborn.blockModelsToRegister.add(this);

	}

	public static EnumSapSide getSideFromFacing(EnumFacing facing) {
		switch (facing) {
			case EAST:
				return EnumSapSide.EAST;
			case WEST:
				return EnumSapSide.WEST;
			case NORTH:
				return EnumSapSide.NORTH;
			case SOUTH:
				return EnumSapSide.SOUTH;
			default:
				return EnumSapSide.NONE;
		}
	}

	protected BlockStateContainer createBlockState() { return new BlockStateContainer(this, new IProperty[] { LOG_AXIS, SAP_SIDE }); }

	public int damageDropped(IBlockState state) {
		return 0;
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		switch (state.getValue(LOG_AXIS)) {
			case X:
				break;
			case Z:
				i = 1;
				break;
			case Y:
				switch (state.getValue(SAP_SIDE)) {
					case NONE:
						i = 2;
						break;
					case NORTH:
						i = 3;
						break;
					case SOUTH:
						i = 4;
						break;
					case EAST:
						i = 5;
						break;
					case WEST:
						i = 6;
						break;
				}
				break;
			case NONE:
				i = 7;

		}
		return i;
	}

	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

		EnumSapSide side;
		switch (meta) {
			case 0:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.X);
				break;
			case 1:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Z);
				break;
			case 2:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y).withProperty(SAP_SIDE, EnumSapSide.NONE);
				break;
			case 3:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y).withProperty(SAP_SIDE, EnumSapSide.NORTH);
				break;
			case 4:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y).withProperty(SAP_SIDE, EnumSapSide.SOUTH);
				break;
			case 5:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y).withProperty(SAP_SIDE, EnumSapSide.EAST);
				break;
			case 6:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y).withProperty(SAP_SIDE, EnumSapSide.WEST);
				break;
			case 7:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.NONE);
				break;
			default:
				iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.NONE);
		}

		return iblockstate;
	}

	public enum EnumSapSide implements IStringSerializable {
		NONE, NORTH, SOUTH, EAST, WEST;

		@Override
		public String getName() {
			return this.toString().toLowerCase();
		}
	}
}

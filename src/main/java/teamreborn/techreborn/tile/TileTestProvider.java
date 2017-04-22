package teamreborn.techreborn.tile;

import net.minecraft.tileentity.TileEntity;
import teamreborn.reborncore.api.power.IGridProvider;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(modID = TRConstants.MOD_ID)
@TileRegistry(name = "tileTestProvider")
public class TileTestProvider extends TileEntity implements IGridProvider {
	@Override
	public double providePower() {
		return 100;
	}
}

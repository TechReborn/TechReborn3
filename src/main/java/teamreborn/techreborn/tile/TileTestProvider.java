package teamreborn.techreborn.tile;

import teamreborn.reborncore.api.power.IGridProvider;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.reborncore.grid.TileGridConnection;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "tileTestProvider")
public class TileTestProvider extends TileGridConnection implements IGridProvider {

	@Override
	public double providePower() {
		return 100;
	}

}

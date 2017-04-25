package teamreborn.techreborn.tile;

import teamreborn.reborncore.api.power.IGridReciever;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.reborncore.grid.TileGridConnection;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "tileTestReciever")
public class TileTestReciever extends TileGridConnection implements IGridReciever {

	@Override
	public void handlePower(double power, double powerRatio) {
		//System.out.println("power: " + power + " ratio: " + powerRatio);
	}

	@Override
	public double requestPower() {
		return 25;
	}
}

package teamreborn.techreborn.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import teamreborn.reborncore.api.power.GridJoinEvent;
import teamreborn.reborncore.api.power.IGridReciever;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.reborncore.grid.PowerGrid;
import teamreborn.techreborn.TRConstants;

import javax.annotation.Nullable;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "tileTestReciever")
public class TileTestReciever extends TileEntity implements IGridReciever {

	PowerGrid grid;

	@Nullable
	@Override
	public PowerGrid getPowerGrid() {
		return grid;
	}

	@Override
	public void setPowerGrid(
		@Nullable
			PowerGrid powerGrid) {
		this.grid = powerGrid;
	}

	@Override
	public void handlePower(double power, double powerRatio) {
		//System.out.println("power: " + power + " ratio: " + powerRatio);
	}

	@Override
	public double requestPower() {
		return 25;
	}

	@Override
	public void onLoad() {
		super.onLoad();
		MinecraftForge.EVENT_BUS.post(new GridJoinEvent(world, pos, this));
	}
}

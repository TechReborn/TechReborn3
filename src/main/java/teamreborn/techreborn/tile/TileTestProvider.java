package teamreborn.techreborn.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import teamreborn.reborncore.api.power.GridJoinEvent;
import teamreborn.reborncore.api.power.IGridProvider;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.reborncore.grid.PowerGrid;
import teamreborn.techreborn.TRConstants;

import javax.annotation.Nullable;

/**
 * Created by Mark on 22/04/2017.
 */
@RebornRegistry(modID = TRConstants.MOD_ID)
@TileRegistry(name = "tileTestProvider")
public class TileTestProvider extends TileEntity implements IGridProvider {


	PowerGrid grid;

	@Override
	public double providePower() {
		return 100;
	}

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
	public void onLoad() {
		super.onLoad();
		MinecraftForge.EVENT_BUS.post(new GridJoinEvent(world, pos, this));
	}
}

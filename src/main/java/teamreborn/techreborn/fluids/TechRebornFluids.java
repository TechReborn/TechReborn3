package teamreborn.techreborn.fluids;

import teamreborn.reborncore.api.registry.FluidFactoryContainer;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.FluidRegistry;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class TechRebornFluids {

	@FluidRegistry(name = "sap", path = "block/fluid/", density = 2000, viscosity = 5000)
	public static FluidFactoryContainer SAP;

	@FluidRegistry(name = "biomass", path = "block/fluid/", viscosity = 2000)
	public static FluidFactoryContainer BIOMASS;
}

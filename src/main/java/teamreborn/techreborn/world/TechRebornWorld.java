package teamreborn.techreborn.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.CustomRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.world.biomes.RubberPlantationBiome;

@RebornRegistry(TRConstants.MOD_ID)
public class TechRebornWorld {

	public static RubberPlantationBiome rubberPlantationBiome;

	@CustomRegistry
	public static void init() {
		Biome.BiomeProperties properties = new Biome.BiomeProperties("Rubber Plantation");
		properties.setTemperature(0.95F);
		properties.setRainfall(0.95F);
		properties.setBaseHeight(0.45F);
		properties.setHeightVariation(0.4F);

		rubberPlantationBiome = new RubberPlantationBiome(properties);
		rubberPlantationBiome.setRegistryName(new ResourceLocation(TRConstants.MOD_ID, "rubberPlantation"));

		GameRegistry.register(rubberPlantationBiome);

		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(rubberPlantationBiome, 20));
		BiomeManager.addSpawnBiome(rubberPlantationBiome);
		BiomeProvider.allowedBiomes.add(rubberPlantationBiome);
	}

}

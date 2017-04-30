package teamreborn.techreborn.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

/**
 * Created by Mark on 23/04/2017.
 */
public class RubberPlantationBiome extends Biome {
	public RubberPlantationBiome(BiomeProperties properties) {
		super(properties);
	}

	@Override
	public int getWaterColorMultiplier() {
		return 0xFF1F3E87;
	}

	@Override
	public int getModdedBiomeGrassColor(int original) {
		return 0xFF84C809;
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return 0xFF7CBACC;
	}

	@Override
	public int getModdedBiomeFoliageColor(int original) {
		return 0xFF277F07;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new RubberPlantationDecorator();
	}
}

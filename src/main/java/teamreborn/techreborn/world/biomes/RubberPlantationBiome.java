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
		return 17777215;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new RubberPlantationDecorator();
	}
}

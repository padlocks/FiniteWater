package tfar.finitewater;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Config {

	public static final ServerConfig SERVER;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		COMMON_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	public static class ServerConfig {

		public static ForgeConfigSpec.ConfigValue<List<? extends String>> infinite_water_biomes;
		public static ForgeConfigSpec.ConfigValue<List<? extends String>> infinite_lava_biomes;

		ServerConfig(ForgeConfigSpec.Builder builder) {
			builder.push("general");

			infinite_water_biomes = builder
							.comment("Biomes that water is infinite in")
							.translation("text.finitewater.config.is_water_finite")
							.defineList("infinite water biomes", Lists.newArrayList(
											Biomes.COLD_OCEAN.getRegistryName().toString(),
											Biomes.DEEP_COLD_OCEAN.getRegistryName().toString(),
											Biomes.DEEP_FROZEN_OCEAN.getRegistryName().toString(),
											Biomes.DEEP_LUKEWARM_OCEAN.getRegistryName().toString(),
											Biomes.DEEP_OCEAN.getRegistryName().toString(),
											Biomes.DEEP_WARM_OCEAN.getRegistryName().toString(),
											Biomes.FROZEN_OCEAN.getRegistryName().toString(),
											Biomes.FROZEN_RIVER.getRegistryName().toString(),
											Biomes.OCEAN.getRegistryName().toString(),
											Biomes.RIVER.getRegistryName().toString(),
											Biomes.WARM_OCEAN.getRegistryName().toString()
							), String.class::isInstance);

			infinite_lava_biomes = builder
							.comment("Biomes that lava is infinite in")
							.translation("text.finitewater.config.is_lava_infinite")
							.defineList("infinite lava biomes",
											Lists.newArrayList(
															Biomes.CRIMSON_FOREST.getRegistryName().toString(),
															Biomes.NETHER_WASTES.getRegistryName().toString(),
															Biomes.SOUL_SAND_VALLEY.getRegistryName().toString(),
															Biomes.WARPED_FOREST.getRegistryName().toString()
											), String.class::isInstance);
			builder.pop();
		}
	}
}

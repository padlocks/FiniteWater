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

		public static ForgeConfigSpec.BooleanValue is_water_whitelist;
		public static ForgeConfigSpec.BooleanValue is_lava_whitelist;


		ServerConfig(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			builder.push("lava");
			is_lava_whitelist = builder.comment("Is the list below a whitelist, changing to false will make it a blacklist instead").define("whitelist",true);
			infinite_lava_biomes = builder
					.comment("Biomes that lava is infinite in")
					.translation("text.finitewater.config.is_lava_infinite")
					.defineList("infinite lava biomes",
							Lists.newArrayList(
									Biomes.CRIMSON_FOREST.getLocation().toString(),
									Biomes.NETHER_WASTES.getLocation().toString(),
									Biomes.SOUL_SAND_VALLEY.getLocation().toString(),
									Biomes.WARPED_FOREST.getLocation().toString()
							), String.class::isInstance);


			builder.pop();
			builder.push("water");
			is_water_whitelist = builder.comment("Is the list below a whitelist, changing to false will make it a blacklist instead").define("whitelist",true);

			infinite_water_biomes = builder
					.comment("Biomes that water is infinite in")
					.translation("text.finitewater.config.is_water_finite")
					.defineList("infinite water biomes", Lists.newArrayList(
							Biomes.COLD_OCEAN.getLocation().toString(),
							Biomes.DEEP_COLD_OCEAN.getLocation().toString(),
							Biomes.DEEP_FROZEN_OCEAN.getLocation().toString(),
							Biomes.DEEP_LUKEWARM_OCEAN.getLocation().toString(),
							Biomes.DEEP_OCEAN.getLocation().toString(),
							Biomes.DEEP_WARM_OCEAN.getLocation().toString(),
							Biomes.FROZEN_OCEAN.getLocation().toString(),
							Biomes.FROZEN_RIVER.getLocation().toString(),
							Biomes.OCEAN.getLocation().toString(),
							Biomes.RIVER.getLocation().toString(),
							Biomes.WARM_OCEAN.getLocation().toString()
					), String.class::isInstance);
			builder.pop(2);
		}
	}
}

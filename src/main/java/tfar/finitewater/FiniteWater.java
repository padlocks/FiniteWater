package tfar.finitewater;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FiniteWater.MODID)
public class FiniteWater {
  public static final String MODID = "finitewater";

  public FiniteWater() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_SPEC);
  }

  public static boolean isWaterInfinite() {
    return Config.ServerConfig.infinite_water_biomes.get().contains(biome.getRegistryName().toString());
  }

  public static boolean isLavaInfinite() {
    return Config.ServerConfig.infinite_lava_biomes.get().contains(biome.getRegistryName().toString());
  }

  public static Biome biome;

  public static void capture(IWorldReader worldIn, BlockPos pos, BlockState blockStateIn) {
    biome = worldIn.getBiome(pos);
  }
}

package com.tfar.finitewater;

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
    return !Config.ServerConfig.is_water_finite.get();
  }

  public static boolean isLavaInfinite() {
    return Config.ServerConfig.is_lava_infinite.get();
  }

}

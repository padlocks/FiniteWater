package com.tfar.finitewater;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

  public static final ServerConfig SERVER;
  public static final ForgeConfigSpec COMMON_SPEC;

  static {
    final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
    COMMON_SPEC = specPair.getRight();
    SERVER = specPair.getLeft();
  }

  public static class ServerConfig {

    public static ForgeConfigSpec.BooleanValue is_water_finite;
    public static ForgeConfigSpec.BooleanValue is_lava_infinite;

    ServerConfig(ForgeConfigSpec.Builder builder) {
      builder.push("general");

      is_water_finite = builder
              .comment("Disable Water being infinite")
              .translation("text.finitewater.config.is_water_finite")
              .define("finite water",true);

      is_lava_infinite = builder
              .comment("Disable Water being infinite")
              .translation("text.finitewater.config.is_lava_infinite")
              .define("infinite lava",false);
      builder.pop();
    }
  }
}

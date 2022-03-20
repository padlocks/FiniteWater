package tfar.finitewater;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import tfar.finitewater.config.ConfigHandler;

public class FiniteWaterUtils {
    public static boolean isWaterInfinite(Holder<Biome> biome) {
        return biome.is(WaterTags.IS_INFINITE_WATER_BIOME);
    }

    public static boolean formWaterSource(Fluid fluid, Holder<Biome> biome){
        boolean form = true;
        if (fluid == Fluids.WATER) {
            if (isWaterInfinite(biome) != ConfigHandler.CONFIG_HANDLER.getConfig().isWaterWhitelist()) {
                form = false;
            }
        }
        return form;
    }
}

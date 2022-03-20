package tfar.finitewater.fabric;

import net.fabricmc.api.ModInitializer;
import tfar.finitewater.config.ConfigHandler;

public class FiniteWater implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigHandler.CONFIG_HANDLER.save();
    }
}

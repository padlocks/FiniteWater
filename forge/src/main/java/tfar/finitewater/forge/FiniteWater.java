package tfar.finitewater.forge;


import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfar.finitewater.FiniteWaterUtils;
import tfar.finitewater.config.ConfigHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FiniteWater.MODID)
public class FiniteWater {
    public static final String MODID = "finitewater";

    public FiniteWater() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.addListener(this::isInfinite);
    }
    private void setup(final FMLCommonSetupEvent e) {
        ConfigHandler.CONFIG_HANDLER.save();
    }

    private void isInfinite(BlockEvent.CreateFluidSourceEvent e) {
        Fluid fluid = e.getState().getFluidState().getType();
        Holder<Biome> biome = e.getWorld().getBiome(e.getPos());
        if (!FiniteWaterUtils.formWaterSource(fluid, biome)){
            e.setResult(Event.Result.DENY);
        }
    }
}

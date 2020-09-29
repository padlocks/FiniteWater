package tfar.finitewater;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FiniteWater.MODID)
public class FiniteWater {
    public static final String MODID = "finitewater";

    public FiniteWater() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.COMMON_SPEC);
        MinecraftForge.EVENT_BUS.addListener(this::isInfinite);
    }

    public static boolean isWaterInfinite(MinecraftServer server,Biome biome) {
        return Config.ServerConfig.is_water_whitelist.get() == Config.ServerConfig.infinite_water_biomes.get().contains(from(server,biome).toString());
    }

    public static boolean isLavaInfinite(MinecraftServer server,Biome biome) {
        return Config.ServerConfig.is_lava_whitelist.get() == Config.ServerConfig.infinite_lava_biomes.get().contains(from(server,biome).toString());
    }

    public static ResourceLocation from(MinecraftServer server, Biome biome) {
        return server.func_244267_aX().getRegistry(Registry.BIOME_KEY).getKey(biome);
    }

    private void isInfinite(BlockEvent.CreateFluidSourceEvent e) {
        Fluid fluid = e.getState().getFluidState().getFluid();
        Biome biome = e.getWorld().getBiome(e.getPos());
        MinecraftServer server = ((ServerWorld)e.getWorld()).getServer();
        if (fluid == Fluids.WATER) {
            if (Config.ServerConfig.is_water_whitelist.get()) {
                if (isWaterInfinite(server,biome)) {
                } else {
                    e.setResult(Event.Result.DENY);
                }
            }
        } else if (fluid == Fluids.LAVA) {
            if (isLavaInfinite(server,biome)) {
                e.setResult(Event.Result.ALLOW);
            }
        }
    }
}

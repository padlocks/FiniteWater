package tfar.finitewater;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class WaterTags {
    public static final TagKey<Biome> IS_INFINITE_WATER_BIOME = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("finitewater", "is_infinite_water_biome"));
}

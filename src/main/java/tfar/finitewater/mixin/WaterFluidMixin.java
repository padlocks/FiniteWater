package tfar.finitewater.mixin;

import net.minecraft.fluid.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tfar.finitewater.FiniteWater;

@Mixin(WaterFluid.class)
public class WaterFluidMixin {
	@Overwrite
	public boolean canSourcesMultiply() {
		return FiniteWater.isWaterInfinite();
	}
}

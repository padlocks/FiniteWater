package tfar.finitewater.mixin;

import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import tfar.finitewater.FiniteWater;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
	@Overwrite
	public boolean canSourcesMultiply() {
		return FiniteWater.isLavaInfinite();
	}
}

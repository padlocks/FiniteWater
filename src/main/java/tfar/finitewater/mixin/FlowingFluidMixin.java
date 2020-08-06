package tfar.finitewater.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.finitewater.FiniteWater;

@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {

	@Inject(method = "calculateCorrectFlowingState",at = @At(value = "INVOKE",target = "Lnet/minecraft/fluid/FlowingFluid;canSourcesMultiply()Z"))
	private void captureBiome(IWorldReader worldIn, BlockPos pos, BlockState blockStateIn, CallbackInfoReturnable<FluidState> cir) {
		FiniteWater.capture(worldIn,pos,blockStateIn);
	}
}

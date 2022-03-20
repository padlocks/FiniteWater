package tfar.finitewater.mixin.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.finitewater.FiniteWaterUtils;

@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {
    @Redirect(method = "getNewLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;isSource()Z"))
    private boolean finitewater_isInfinite(FluidState instance, LevelReader levelReader, BlockPos blockPos, BlockState blockState){
        if (!FiniteWaterUtils.formWaterSource(instance.getType(), levelReader.getBiome(blockPos))) return false;
        return instance.isSource();
    }
}

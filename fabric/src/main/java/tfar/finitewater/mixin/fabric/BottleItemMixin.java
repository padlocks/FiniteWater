package tfar.finitewater.mixin.fabric;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.finitewater.config.ConfigHandler;

@Mixin(BottleItem.class)
public abstract class BottleItemMixin extends Item {
  public BottleItemMixin (Item.Properties properties) {
    super(properties);
  }
  @Inject(
      method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;",
      at = @At(value = "INVOKE", ordinal = 1,
          target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
      ),
      cancellable = true
  )
  private void removeWaterBlock(
      Level world,
      Player playerEntity,
      InteractionHand hand,
      CallbackInfoReturnable<ItemStack> ci) {
    if (ConfigHandler.CONFIG_HANDLER.getConfig().bottlesRemoveWaterSource()) {
      ItemStack itemStack = playerEntity.getItemInHand(hand);
      if (itemStack.is(Items.GLASS_BOTTLE)) {
        HitResult hitResult = getPlayerPOVHitResult(world, playerEntity, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
          BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
          //if (playerEntity.blockActionRestricted(world, blockPos, GameType.SURVIVAL)) { // error. null
          BlockState blockState = world.getBlockState(blockPos);
          if (blockState.getFluidState().isSourceOfType(Fluids.WATER)) {
            //world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
            if (world instanceof ServerLevel _level)
              _level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), Vec2.ZERO, _level,
                      4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), "setblock ~ ~ ~ air replace");
            //if (!world.getBlockState(blockPos).is(Blocks.WATER) && !blockState.getFluidState().isSourceOfType(Fluids.WATER)) {
              //return;
              //}
            //}
            return;
          }
        }
      }
      ci.setReturnValue(itemStack);
    }
  }
}
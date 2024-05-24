package net.trungpro200.tnttweak.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import java.util.Optional;

@Mixin(ExplosionBehavior.class)
public abstract class ExplosionMixin {
    @Inject(at = @At("HEAD"), method = "getBlastResistance(Lnet/minecraft/world/explosion/Explosion;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/fluid/FluidState;)Ljava/util/Optional;", cancellable = true)
    public void getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, CallbackInfoReturnable<Optional<Float>> cir){
        if (fluidState.isEmpty()){
            return;
        }

        if (!(fluidState.getFluid() == Fluids.LAVA || fluidState.getFluid() == Fluids.FLOWING_LAVA)){
            return;
        }

        cir.setReturnValue(Optional.of(Float.valueOf(0.0F)));
    }
}

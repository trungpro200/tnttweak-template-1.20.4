package net.trungpro200.tnttweak.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Mixin(TntEntity.class)
public abstract class TtnMixin
extends Entity
{
    public TtnMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    @Inject(method = "tick()V", at = @At("HEAD"))
    public void injected(CallbackInfo ci){
        TntEntity tnt = (TntEntity) (Object) this;
        World world = tnt.getWorld();
        Vec3d temp = tnt.getPos();
        BlockPos pos = BlockPos.ofFloored(temp.x, temp.y, temp.z);
        Fluid fluid = world.getFluidState(pos).getFluid();
        
        if (fluid == Fluids.LAVA || fluid == Fluids.FLOWING_LAVA){
            tnt.setFuse(0);
        }
    }
}

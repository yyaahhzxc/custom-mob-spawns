package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.passive.ChickenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntity.class)
public class MixinChickenEntity {
    // Overrides AnimalEntity despawn check due to potential for jockeys
    @Inject(method = "canImmediatelyDespawn", at = @At("HEAD"), cancellable = true)
    private void injectDespawn(double distance, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(!CustomMobSpawns.SPAWNS_CONFIG.passivePersistent);
    }
    
}

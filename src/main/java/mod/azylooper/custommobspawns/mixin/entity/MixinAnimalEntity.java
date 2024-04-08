package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public class MixinAnimalEntity {
    @Inject(method = "canImmediatelyDespawn", at = @At("HEAD"), cancellable = true)
    private void injectDespawn(double distance, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(!CustomMobSpawns.SPAWNS_CONFIG.passivePersistent);
    }
}

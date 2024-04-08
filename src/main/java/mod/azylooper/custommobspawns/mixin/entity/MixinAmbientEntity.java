package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AmbientEntity.class)
public abstract class MixinAmbientEntity extends MobEntity {

    protected MixinAmbientEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !CustomMobSpawns.SPAWNS_CONFIG.ambientPersistent;
    }
    
}

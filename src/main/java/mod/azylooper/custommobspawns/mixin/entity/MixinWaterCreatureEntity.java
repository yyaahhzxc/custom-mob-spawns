package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterCreatureEntity.class)
public abstract class MixinWaterCreatureEntity extends MobEntity {

    protected MixinWaterCreatureEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !CustomMobSpawns.SPAWNS_CONFIG.waterPersistent;
    }
    
}

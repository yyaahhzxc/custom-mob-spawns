package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HostileEntity.class)
public abstract class MixinHostileEntity extends MobEntity {
    protected MixinHostileEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !CustomMobSpawns.SPAWNS_CONFIG.hostilePersistent;
    }
}

package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractHorseEntity.class)
public class MixinAbstractHorseEntity extends MobEntity {
    @Shadow
    private static TrackedData<Byte> HORSE_FLAGS;
    
    protected MixinAbstractHorseEntity() {
        super(null, null);
    }
    
    @Shadow
    protected boolean getHorseFlag(int integer) {
        return (this.dataTracker.<Byte>get(HORSE_FLAGS) & integer) != 0x0;
    }
    
    @Shadow
    public boolean isSaddled() {
        return this.getHorseFlag(4);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !(CustomMobSpawns.SPAWNS_CONFIG.passivePersistent || this.isSaddled());
    }
    
}

package mod.azylooper.custommobspawns.mixin.entity;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PigEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PigEntity.class)
public class MixinPigEntity extends MobEntity {
    @Shadow
    private SaddledComponent saddledComponent;
    
    protected MixinPigEntity() {
        super(null, null);
    }

    @Override
    public boolean canImmediatelyDespawn(double distance) {
        return !(CustomMobSpawns.SPAWNS_CONFIG.passivePersistent || this.saddledComponent.isSaddled());
    }
    
}

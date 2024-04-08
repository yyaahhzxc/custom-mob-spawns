package mod.azylooper.custommobspawns.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnDensityCapper;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SpawnHelper.Info.class, priority = 999)
public abstract class MixinSpawnHelperInfo {
    @Shadow private int spawningChunkCount;
    @Shadow private Object2IntOpenHashMap<SpawnGroup> groupToCount;
    @Shadow private SpawnDensityCapper densityCapper;
    
    @Inject(method = "isBelowCap", at = @At("HEAD"), cancellable = true)
    private void injectIsBelowCap(SpawnGroup spawnGroup, ChunkPos chunkPos, CallbackInfoReturnable<Boolean> info) {
        int cap = spawnGroup.getCapacity() * this.spawningChunkCount / (int)Math.pow(CustomMobSpawns.SPAWNS_CONFIG.chunkConstant, 2.0);

        boolean isBelowCap = this.groupToCount.getInt(spawnGroup) >= cap ?
            false :
            this.densityCapper.canSpawn(spawnGroup, chunkPos);
        
        info.setReturnValue(isBelowCap);
    }
}

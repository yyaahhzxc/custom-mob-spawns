package mod.azylooper.custommobspawns.mixin;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobSpawnerLogic.class)
public class MixinMobSpawnerLogic {
    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int spawnCount;
    @Shadow private int maxNearbyEntities;
    @Shadow private int requiredPlayerRange;
    @Shadow private int spawnRange;
    
    @Inject(method = "readNbt", at = @At("TAIL")) 
    private void injectReadNbt(World world, BlockPos pos, NbtCompound nbt, CallbackInfo info) {
        if (CustomMobSpawns.SPAWNS_CONFIG.overrideSpawnerDefaultValues) {
            this.minSpawnDelay = CustomMobSpawns.SPAWNS_CONFIG.minSpawnDelay;
            this.maxSpawnDelay = CustomMobSpawns.SPAWNS_CONFIG.maxSpawnDelay;
            this.spawnCount = CustomMobSpawns.SPAWNS_CONFIG.spawnCount;
            this.maxNearbyEntities = CustomMobSpawns.SPAWNS_CONFIG.maxNearbyEntities;
            this.requiredPlayerRange = CustomMobSpawns.SPAWNS_CONFIG.requiredPlayerRange;
            this.spawnRange = CustomMobSpawns.SPAWNS_CONFIG.spawnRange;
        }
    }
}

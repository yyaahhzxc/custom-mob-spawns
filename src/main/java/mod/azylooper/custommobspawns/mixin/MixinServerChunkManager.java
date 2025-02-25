package mod.azylooper.custommobspawns.mixin;

import mod.azylooper.custommobspawns.CustomMobSpawns;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerChunkManager.class)
public class MixinServerChunkManager {
    @Shadow private boolean spawnAnimals;
    @Shadow private ServerWorld world;
    
    @Redirect(
        method = "tickChunks",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/SpawnHelper;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/chunk/WorldChunk;Lnet/minecraft/world/SpawnHelper$Info;ZZZ)V"
        )
    )
    private void injectSpawn(ServerWorld serverWorld, WorldChunk chunk, SpawnHelper.Info info, boolean spawnAnimals, boolean spawnMonsters, boolean rareSpawn) {
        WorldProperties worldProps = this.world.getLevelProperties();
        rareSpawn = worldProps.getTime() % CustomMobSpawns.SPAWNS_CONFIG.rareSpawnTicksToWait == 0L;
        
        SpawnHelper.spawn(serverWorld, chunk, info, spawnAnimals, spawnMonsters, rareSpawn);
    }
}

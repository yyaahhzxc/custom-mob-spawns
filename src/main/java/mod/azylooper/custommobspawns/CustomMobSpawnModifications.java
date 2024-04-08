package mod.azylooper.custommobspawns;

import mod.azylooper.custommobspawns.config.CustomMobSpawnConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import org.slf4j.event.Level;

import java.util.function.Predicate;

public class CustomMobSpawnModifications {
    public static void modifySpawns() {
        CustomMobSpawnConfig config = CustomMobSpawns.SPAWNS_CONFIG;
        
        config.mobSpawnAdditions.forEach(addition -> {
            if (addition.biomeId.isBlank() && addition.biomeTag.isBlank())
                throw new IllegalArgumentException("[Custom Mob Spawns] An addition entry is missing a biome ID or tag!");
            
            Predicate<BiomeSelectionContext> biomePredicate = addition.biomeId.isBlank() ?
                BiomeSelectors.tag(biomeTag(addition.biomeTag)) :
                BiomeSelectors.includeByKey(biomeKey(addition.biomeId));

            CustomMobSpawns.log(Level.INFO, String.format(
                "Creating addition for mob '%s' to biome '%s' or tag '%s', to group '%s' with weight %d, min count %d, and max count %d",
                addition.mobId,
                addition.biomeId,
                addition.biomeTag,
                addition.spawnGroup.asString(),
                addition.weight,
                addition.minCount,
                addition.maxCount
            ));
            
            BiomeModifications
                .create(CustomMobSpawns.createId(Integer.toString(addition.hashCode())))
                .add(
                    ModificationPhase.ADDITIONS,
                    biomePredicate,
                    context -> {
                        context.getSpawnSettings().addSpawn(
                            addition.spawnGroup,
                            new SpawnSettings.SpawnEntry(
                                Registries.ENTITY_TYPE.get(entityKey(addition.mobId)),
                                addition.weight,
                                addition.minCount,
                                addition.maxCount
                            )
                        );
                    }
                );
        });
        
        config.mobSpawnRemovals.forEach(removal -> {
            if (removal.biomeId.isBlank() && removal.biomeTag.isBlank())
                throw new IllegalArgumentException("[Custom Spawns] A removal entry is missing a biome ID or tag!");
            
            Predicate<BiomeSelectionContext> biomePredicate = removal.biomeId.isBlank() ?
                BiomeSelectors.tag(biomeTag(removal.biomeTag)) :
                BiomeSelectors.includeByKey(biomeKey(removal.biomeId));
            
            CustomMobSpawns.log(Level.INFO, String.format(
                "Creating removal for mob '%s' from biome '%s' or tag '%s'",
                removal.mobId,
                removal.biomeId,
                removal.biomeTag
            ));
            
            BiomeModifications
                .create(CustomMobSpawns.createId(Integer.toString(removal.hashCode())))
                .add(
                    ModificationPhase.REMOVALS,
                    biomePredicate,
                    context -> {
                        context.getSpawnSettings().removeSpawnsOfEntityType(
                            Registries.ENTITY_TYPE.get(entityKey(removal.mobId))
                        );
                    }
                );
        });
        
        config.mobSpawnReplacements.forEach(replacement -> {
            if (replacement.biomeId.isBlank() && replacement.biomeTag.isBlank())
                throw new IllegalArgumentException("[Custom Spawns] A replacement entry is missing a biome ID or tag!");
            
            Predicate<BiomeSelectionContext> biomePredicate = replacement.biomeId.isBlank() ?
                BiomeSelectors.tag(biomeTag(replacement.biomeTag)) :
                BiomeSelectors.includeByKey(biomeKey(replacement.biomeId));
            
            CustomMobSpawns.log(Level.INFO, String.format(
                "Creating replacement for mob '%s' from biome '%s' or tag '%s', with mob '%s', to group '%s' with weight '%d', min count '%d', and max count '%d'",
                replacement.originalMobId,
                replacement.biomeId,
                replacement.biomeTag,
                replacement.replacementMobId,
                replacement.replacementSpawnGroup.asString(),
                replacement.replacementWeight,
                replacement.replacementMinCount,
                replacement.replacementMaxCount
            ));
            
            BiomeModifications
                .create(CustomMobSpawns.createId(Integer.toString(replacement.hashCode())))
                .add(
                    ModificationPhase.REPLACEMENTS,
                    biomePredicate,
                    context -> {
                        context.getSpawnSettings().removeSpawnsOfEntityType(
                            Registries.ENTITY_TYPE.get(entityKey(replacement.originalMobId))
                        );
                        
                        context.getSpawnSettings().addSpawn(
                            replacement.replacementSpawnGroup,
                            new SpawnSettings.SpawnEntry(
                                Registries.ENTITY_TYPE.get(entityKey(replacement.replacementMobId)),
                                replacement.replacementWeight,
                                replacement.replacementMinCount,
                                replacement.replacementMaxCount
                            )
                        );
                    }
                );
        });
    }
    
    private static RegistryKey<Biome> biomeKey(String id) {
        return RegistryKey.of(RegistryKeys.BIOME, new Identifier(id));
    }
    
    private static RegistryKey<EntityType<?>> entityKey(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(id));
    }
    
    private static TagKey<Biome> biomeTag(String tag) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(tag));
    }
}

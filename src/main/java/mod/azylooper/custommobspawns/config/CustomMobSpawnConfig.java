package mod.azylooper.custommobspawns.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.entity.SpawnGroup;

import java.util.List;

@Config(name = "custom-mob-spawns")
public class CustomMobSpawnConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "general")
    public int chunkConstant = 17;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "general")
    public long rareSpawnTicksToWait = 400L;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup monsterGroup = new CustomMobSpawnGroup(70, false, false, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup creatureGroup = new CustomMobSpawnGroup(10, true, true, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup ambientGroup = new CustomMobSpawnGroup(15, true, false, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup axolotlGroup = new CustomMobSpawnGroup(5, true, false, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup waterCreatureGroup = new CustomMobSpawnGroup(5, true, false, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup waterAmbientGroup = new CustomMobSpawnGroup(20, true, false, 64, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup undergroundWaterGroup = new CustomMobSpawnGroup(5, true, false, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "mobGroups")
    @ConfigEntry.Gui.CollapsibleObject
    public CustomMobSpawnGroup miscGroup = new CustomMobSpawnGroup(-1, true, true, 128, 32);

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "persistence")
    public boolean passivePersistent = true;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "persistence")
    public boolean hostilePersistent = false;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "persistence")
    public boolean ambientPersistent = false;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "persistence")
    public boolean waterPersistent = false;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "mobSpawnAdditions")
    public List<CustomMobSpawnAddition> mobSpawnAdditions = List.of();
    
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "mobSpawnRemovals")
    public List<CustomMobSpawnRemoval> mobSpawnRemovals = List.of();
    
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "mobSpawnReplacements")
    public List<CustomMobSpawnReplacement> mobSpawnReplacements = List.of();
    
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public boolean overrideSpawnerDefaultValues = false;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int minSpawnDelay = 200;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int maxSpawnDelay = 800;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int spawnCount = 4;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int maxNearbyEntities = 6;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int requiredPlayerRange = 16;

    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Category(value = "spawners")
    public int spawnRange = 4;
    
    public static class CustomMobSpawnGroup {
        @ConfigEntry.Gui.Tooltip()
        public int capacity;
        @ConfigEntry.Gui.Tooltip()
        public boolean peaceful;
        @ConfigEntry.Gui.Tooltip()
        public boolean rare;
        @ConfigEntry.Gui.Tooltip()
        public int immediateDespawnRange;
        @ConfigEntry.Gui.Tooltip()
        public int despawnStartRange;
        
        public CustomMobSpawnGroup(
            int capacity, 
            boolean peaceful, 
            boolean rare,
            int immediateDespawnRange, 
            int despawnStartRange
        ) {
            this.capacity = capacity;
            this.peaceful = peaceful;
            this.rare = rare;
            this.immediateDespawnRange = immediateDespawnRange;
            this.despawnStartRange = despawnStartRange;
        }
    }
    
    public static class CustomMobSpawnAddition {
        @ConfigEntry.Gui.Tooltip()
        public String biomeId;
        @ConfigEntry.Gui.Tooltip()
        public String biomeTag;
        @ConfigEntry.Gui.Tooltip()
        public String mobId;
        @ConfigEntry.Gui.Tooltip()
        public SpawnGroup spawnGroup;
        @ConfigEntry.Gui.Tooltip()
        public int weight;
        @ConfigEntry.Gui.Tooltip()
        public int minCount;
        @ConfigEntry.Gui.Tooltip()
        public int maxCount;
        
        public CustomMobSpawnAddition() {
            this.biomeId = "minecraft:plains";
            this.biomeTag = "";
            this.mobId = "minecraft:pig";
            this.spawnGroup = SpawnGroup.CREATURE;
            this.weight = 10;
            this.minCount = 4;
            this.maxCount = 4;
        }
    }
    
    public static class CustomMobSpawnRemoval {
        @ConfigEntry.Gui.Tooltip()
        public String biomeId;
        @ConfigEntry.Gui.Tooltip()
        public String biomeTag;
        @ConfigEntry.Gui.Tooltip()
        public String mobId;
        
        public CustomMobSpawnRemoval() {
            this.biomeId = "minecraft:plains";
            this.biomeTag = "";
            this.mobId = "minecraft:pig";
        }
    }
    
    public static class CustomMobSpawnReplacement {
        @ConfigEntry.Gui.Tooltip()
        public String biomeId;
        @ConfigEntry.Gui.Tooltip()
        public String biomeTag;
        @ConfigEntry.Gui.Tooltip()
        public String originalMobId;
        @ConfigEntry.Gui.Tooltip()
        public String replacementMobId;
        @ConfigEntry.Gui.Tooltip()
        public SpawnGroup replacementSpawnGroup;
        @ConfigEntry.Gui.Tooltip()
        public int replacementWeight;
        @ConfigEntry.Gui.Tooltip()
        public int replacementMinCount;
        @ConfigEntry.Gui.Tooltip()
        public int replacementMaxCount;
        
        public CustomMobSpawnReplacement() {
            this.biomeId = "minecraft:plains";
            this.biomeTag = "";
            this.originalMobId = "minecraft:pig";
            
            this.replacementMobId = "minecraft:pig";
            this.replacementSpawnGroup = SpawnGroup.CREATURE;
            this.replacementWeight = 10;
            this.replacementMinCount = 4;
            this.replacementMaxCount = 4;
        }
    }
}

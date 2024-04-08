package mod.azylooper.custommobspawns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import mod.azylooper.custommobspawns.config.CustomMobSpawnConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class CustomMobSpawns implements ModInitializer {
	public static final String MOD_ID = "custom-mob-spawns";
	public static final String MOD_NAME = "Custom Mob Spawns";

	public static final CustomMobSpawnConfig SPAWNS_CONFIG = AutoConfig.register(CustomMobSpawnConfig.class, GsonConfigSerializer::new).getConfig();

	private static final Logger LOGGER = LoggerFactory.getLogger("Custom Mob Spawns");

	public static void log(Level level, String message) {
		LOGGER.atLevel(level).log("[" + MOD_NAME + "] {}", message);
	}

	public static Identifier createId(String name) {
		return new Identifier(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		log(Level.INFO, "Initializing Custom Mob Spawns...");

		// Add custom spawns
		CustomMobSpawnModifications.modifySpawns();
	}
}
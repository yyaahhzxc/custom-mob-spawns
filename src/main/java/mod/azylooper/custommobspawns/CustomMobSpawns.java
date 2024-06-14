package mod.azylooper.custommobspawns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import mod.azylooper.custommobspawns.config.CustomMobSpawnConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class CustomMobSpawns implements ModInitializer {
	public static final String MOD_ID = "custom-mob-spawns";
	public static final String MOD_NAME = "Custom Mob Spawns";

	public static final CustomMobSpawnConfig SPAWNS_CONFIG = AutoConfig.register(CustomMobSpawnConfig.class, GsonConfigSerializer::new).getConfig();

	private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void print(String message) {
		LOGGER.info("[" + MOD_NAME + "] {}", message);
	}

	public static void warn(String message) {
		LOGGER.warn("\u001b[33m[" + MOD_NAME + "] {}\u001b[0m", message);
	}

	public static void error(String message) {
		LOGGER.error("[" + MOD_NAME + "] {}", message);
	}

	public static void crash(String message) {
		throw new IllegalArgumentException("[" + MOD_NAME + "] " + message);
	}

	public static Identifier createId(String name) {
		return Identifier.of(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		print("Initializing...");
		CustomMobSpawnModifications.modifySpawns();
		print("Successfully initialized!");
	}
}
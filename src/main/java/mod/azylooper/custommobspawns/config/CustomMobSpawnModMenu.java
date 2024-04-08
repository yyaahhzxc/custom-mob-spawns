package mod.azylooper.custommobspawns.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class CustomMobSpawnModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
        return screen -> AutoConfig.getConfigScreen(CustomMobSpawnConfig.class, screen).get();
    }
}

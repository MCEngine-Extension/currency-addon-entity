package io.github.mcengine.addon.currency.entity;

import io.github.mcengine.api.currency.extension.addon.IMCEngineCurrencyAddOn;
import io.github.mcengine.api.mcengine.MCEngineApi;
import io.github.mcengine.api.mcengine.addon.MCEngineAddOnLogger;
import io.github.mcengine.addon.currency.entity.listener.EntityListener;
import io.github.mcengine.addon.currency.entity.util.EntityUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Entity implements IMCEngineCurrencyAddOn {

    @Override
    public void onLoad(Plugin plugin) {
        MCEngineAddOnLogger logger = new MCEngineAddOnLogger(plugin, "MCEngineEntity");
        try {
            PluginManager pluginManager = Bukkit.getPluginManager();
            pluginManager.registerEvents(new EntityListener(plugin, logger), plugin);

            // Create example config files
            EntityUtil.createSimpleFiles(plugin);

        } catch (Exception e) {
            logger.warning("Failed to initialize Entity: " + e.getMessage());
            e.printStackTrace();
        }

        MCEngineApi.checkUpdate(plugin, logger.getLogger(),
        "[AddOn] [MCEngineEntity] ", "github", "MCEngine-Extension",
        "currency-addon-entity", plugin.getConfig().getString("github.token", "null"));
    }
}

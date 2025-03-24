package cn.i7mc.sagalib;

import cn.i7mc.sagalib.api.SagaLibAPI;
import cn.i7mc.sagalib.api.impl.SagaLibAPIImpl;
import cn.i7mc.sagalib.gui.listener.GuiListener;
import cn.i7mc.sagalib.command.ExampleCommand;
import cn.i7mc.sagalib.config.ConfigManager;
import cn.i7mc.sagalib.config.LanguageManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * SagaLib 插件主类
 */
public class SagaLib extends JavaPlugin {
    private FileConfiguration messageConfig;
    private FileConfiguration debugMessageConfig;
    private FileConfiguration config;

    private static SagaLib instance;
    private static SagaLibAPI api;
    private ConfigManager configManager;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;
        
        // 保存默认配置
        saveDefaultConfig();
        saveResource("message.yml", false);
        saveResource("debugmessage.yml", false);

        // 加载配置
        config = getConfig();
        messageConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "message.yml"));
        debugMessageConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "debugmessage.yml"));

        // 初始化管理器
        configManager = new ConfigManager(this);
        languageManager = new LanguageManager(this);
        api = new SagaLibAPIImpl(this);

        // 输出启动消息
        String message = messageConfig.getString("plugin.enable");
        if (message != null) {
            getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
        }

        // 如果开启调试模式，输出调试信息
        if (config.getBoolean("debug", false)) {
            String debugMessage = debugMessageConfig.getString("plugin.enable");
            if (debugMessage != null) {
                getLogger().info(ChatColor.translateAlternateColorCodes('&', debugMessage));
            }
        }

        // 注册事件监听器
        getServer().getPluginManager().registerEvents(new GuiListener(), this);

        // 注册命令
        getCommand("example").setExecutor(new ExampleCommand());
    }

    @Override
    public void onDisable() {
        if (messageConfig != null) {
            // 输出关闭消息
            String message = messageConfig.getString("plugin.disable");
            if (message != null) {
                getLogger().info(ChatColor.translateAlternateColorCodes('&', message));
            }

            // 如果开启调试模式，输出调试信息
            if (config != null && config.getBoolean("debug", false)) {
                String debugMessage = debugMessageConfig.getString("plugin.disable");
                if (debugMessage != null) {
                    getLogger().info(ChatColor.translateAlternateColorCodes('&', debugMessage));
                }
            }
        }

        instance = null;
        api = null;
    }

    /**
     * 获取SagaLib实例
     * @return SagaLib实例
     */
    public static SagaLib getInstance() {
        return instance;
    }

    /**
     * 获取SagaLib API实例
     * @return API实例
     */
    public static SagaLibAPI getAPI() {
        return api;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }
} 
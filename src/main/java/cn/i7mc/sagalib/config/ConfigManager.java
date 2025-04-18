package cn.i7mc.sagalib.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * 配置管理器
 * 实现配置的热重载功能
 */
public class ConfigManager {
    
    private final Plugin plugin;
    private final Map<String, FileConfiguration> configs;
    private final Map<String, File> configFiles;
    
    /**
     * 创建配置管理器
     * @param plugin 插件实例
     */
    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        this.configs = new HashMap<>();
        this.configFiles = new HashMap<>();
        loadConfigs();
    }
    
    /**
     * 加载所有配置文件
     */
    private void loadConfigs() {
        // 加载主配置文件
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
        }
        FileConfiguration config = plugin.getConfig();
        configs.put("config", config);
        configFiles.put("config", configFile);
        
        // 加载消息配置文件
        File messageFile = new File(plugin.getDataFolder(), "message.yml");
        if (!messageFile.exists()) {
            plugin.saveResource("message.yml", false);
        }
        FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messageFile);
        configs.put("message", messageConfig);
        configFiles.put("message", messageFile);
        
        // 加载调试消息配置文件
        File debugMessageFile = new File(plugin.getDataFolder(), "debugmessage.yml");
        if (!debugMessageFile.exists()) {
            plugin.saveResource("debugmessage.yml", false);
        }
        FileConfiguration debugMessageConfig = YamlConfiguration.loadConfiguration(debugMessageFile);
        configs.put("debugmessage", debugMessageConfig);
        configFiles.put("debugmessage", debugMessageFile);
        
        // 加载其他配置文件
        File[] files = plugin.getDataFolder().listFiles((dir, name) -> 
            name.endsWith(".yml") && 
            !name.equals("config.yml") && 
            !name.equals("message.yml") && 
            !name.equals("debugmessage.yml")
        );
        if (files != null) {
            for (File file : files) {
                String name = file.getName().replace(".yml", "");
                FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
                configs.put(name, fileConfig);
                configFiles.put(name, file);
            }
        }
    }
    
    /**
     * 获取配置文件
     * @param name 配置文件名
     * @return 配置文件
     */
    public FileConfiguration getConfig(String name) {
        return configs.get(name);
    }
    
    /**
     * 获取主配置文件
     * @return 主配置文件
     */
    public FileConfiguration getConfig() {
        return getConfig("config");
    }

    /**
     * 获取消息配置文件
     * @return 消息配置文件
     */
    public FileConfiguration getMessageConfig() {
        return getConfig("message");
    }

    /**
     * 获取调试消息配置文件
     * @return 调试消息配置文件
     */
    public FileConfiguration getDebugMessageConfig() {
        return getConfig("debugmessage");
    }
    
    /**
     * 重载配置文件
     * @param name 配置文件名
     */
    public void reloadConfig(String name) {
        File file = configFiles.get(name);
        if (file != null) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            configs.put(name, config);
        }
    }
    
    /**
     * 重载所有配置文件
     */
    public void reloadAllConfigs() {
        configs.clear();
        loadConfigs();
    }
    
    /**
     * 重载配置
     */
    public void reloadConfig() {
        reloadAllConfigs();
    }
    
    /**
     * 保存配置文件
     * @param name 配置文件名
     */
    public void saveConfig(String name) {
        FileConfiguration config = configs.get(name);
        File file = configFiles.get(name);
        if (config != null && file != null) {
            try {
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "无法保存配置文件: " + name, e);
            }
        }
    }
    
    /**
     * 保存所有配置文件
     */
    public void saveAllConfigs() {
        for (String name : configs.keySet()) {
            saveConfig(name);
        }
    }
    
    /**
     * 创建新的配置文件
     * @param name 配置文件名
     * @return 是否创建成功
     */
    public boolean createConfig(String name) {
        File file = new File(plugin.getDataFolder(), name + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                configs.put(name, config);
                configFiles.put(name, file);
                return true;
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "无法创建配置文件: " + name, e);
                return false;
            }
        }
        return false;
    }
    
    /**
     * 删除配置文件
     * @param name 配置文件名
     * @return 是否删除成功
     */
    public boolean deleteConfig(String name) {
        File file = configFiles.get(name);
        if (file != null && file.exists()) {
            if (file.delete()) {
                configs.remove(name);
                configFiles.remove(name);
                return true;
            }
        }
        return false;
    }
} 
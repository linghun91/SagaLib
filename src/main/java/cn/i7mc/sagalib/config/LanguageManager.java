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
 * 语言管理器
 * 实现多语言支持和热重载功能
 */
public class LanguageManager {
    
    private final Plugin plugin;
    private final Map<String, FileConfiguration> languages;
    private String currentLanguage;
    private FileConfiguration defaultConfig;
    
    /**
     * 创建语言管理器
     * @param plugin 插件实例
     */
    public LanguageManager(Plugin plugin) {
        this.plugin = plugin;
        this.languages = new HashMap<>();
        this.currentLanguage = "zh_CN";
        loadLanguages();
    }
    
    /**
     * 加载所有语言文件
     */
    private void loadLanguages() {
        // 创建语言文件夹
        File langFolder = new File(plugin.getDataFolder(), "languages");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }
        
        // 加载默认语言文件
        File defaultFile = new File(langFolder, "zh_CN.yml");
        if (!defaultFile.exists()) {
            plugin.saveResource("languages/zh_CN.yml", false);
        }
        defaultConfig = YamlConfiguration.loadConfiguration(defaultFile);
        languages.put("zh_CN", defaultConfig);
        
        // 加载其他语言文件
        File[] files = langFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files != null) {
            for (File file : files) {
                String lang = file.getName().replace(".yml", "");
                if (!lang.equals("zh_CN")) {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                    languages.put(lang, config);
                }
            }
        }
    }
    
    /**
     * 获取指定语言的文本
     * @param key 文本键
     * @return 文本内容
     */
    public String getText(String key) {
        return getText(key, currentLanguage);
    }
    
    /**
     * 获取指定语言的文本
     * @param key 文本键
     * @param language 语言
     * @return 文本内容
     */
    public String getText(String key, String language) {
        FileConfiguration config = languages.get(language);
        if (config == null) {
            config = defaultConfig;
        }
        
        String text = config.getString(key);
        if (text == null) {
            text = defaultConfig.getString(key, key);
        }
        
        return text;
    }
    
    /**
     * 设置当前语言
     * @param language 语言代码
     */
    public void setCurrentLanguage(String language) {
        if (languages.containsKey(language)) {
            this.currentLanguage = language;
        }
    }
    
    /**
     * 获取当前语言
     * @return 当前语言代码
     */
    public String getCurrentLanguage() {
        return currentLanguage;
    }
    
    /**
     * 获取支持的语言列表
     * @return 语言代码列表
     */
    public String[] getSupportedLanguages() {
        return languages.keySet().toArray(new String[0]);
    }
    
    /**
     * 重载语言文件
     */
    public void reload() {
        languages.clear();
        loadLanguages();
    }

    /**
     * 重载配置
     */
    public void reloadConfig() {
        reload();
    }
    
    /**
     * 保存语言文件
     * @param language 语言代码
     */
    public void saveLanguage(String language) {
        FileConfiguration config = languages.get(language);
        if (config != null) {
            try {
                File file = new File(plugin.getDataFolder() + "/languages", language + ".yml");
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "无法保存语言文件: " + language, e);
            }
        }
    }
    
    /**
     * 保存所有语言文件
     */
    public void saveAllLanguages() {
        for (String language : languages.keySet()) {
            saveLanguage(language);
        }
    }
} 
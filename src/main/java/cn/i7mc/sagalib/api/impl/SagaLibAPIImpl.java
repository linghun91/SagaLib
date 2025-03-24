package cn.i7mc.sagalib.api.impl;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.api.SagaLibAPI;
import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import cn.i7mc.sagalib.gui.holder.GuiHolder;
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.style.DefaultStyle;
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.layout.GuiLayout;
import cn.i7mc.sagalib.gui.layout.CustomLayout;
import cn.i7mc.sagalib.gui.layout.PaginationLayout;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import cn.i7mc.sagalib.config.ConfigManager;
import cn.i7mc.sagalib.config.LanguageManager;
import cn.i7mc.sagalib.util.ColorUtil;
import cn.i7mc.sagalib.util.TextUtil;
import cn.i7mc.sagalib.util.GuiUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * SagaLib API实现类
 */
public class SagaLibAPIImpl implements SagaLibAPI {
    private final SagaLib plugin;
    private final ConfigManager configManager;
    private final LanguageManager languageManager;
    private final TextUtil textUtil;
    private final ColorUtil colorUtil;
    private final GuiUtil guiUtil;
    private final Map<String, java.util.function.Function<Player, String>> customPlaceholders;
    private final Map<String, GuiAnimation> animations;

    public SagaLibAPIImpl(SagaLib plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.languageManager = plugin.getLanguageManager();
        this.textUtil = new TextUtil();
        this.colorUtil = new ColorUtil();
        this.guiUtil = new GuiUtil();
        this.customPlaceholders = new HashMap<>();
        this.animations = new HashMap<>();
    }

    // =============== GUI系统API实现 ===============

    @Override
    public GuiHolder createGui(Player player, String title, int size) {
        return new BaseGuiHolder(player, title, size);
    }

    @Override
    public void applyStyle(GuiHolder holder, GuiStyle style) {
        holder.setStyle(style);
    }

    @Override
    public void addButton(GuiHolder holder, int slot, GuiButton button) {
        holder.addButton(slot, button);
    }

    @Override
    public void setLayout(GuiHolder holder, GuiLayout layout) {
        holder.setLayout(layout);
    }

    @Override
    public void addAnimation(GuiHolder holder, GuiAnimation animation) {
        holder.addAnimation(animation);
    }

    @Override
    public void startAnimation(GuiHolder holder, GuiAnimation animation) {
        if (holder instanceof BaseGuiHolder) {
            ((BaseGuiHolder) holder).startAnimation(animation);
        }
    }

    @Override
    public void stopAnimation(GuiHolder holder, GuiAnimation animation) {
        if (holder instanceof BaseGuiHolder) {
            ((BaseGuiHolder) holder).stopAnimation(animation);
        }
    }

    @Override
    public GuiHolder createPaginationGui(Player player, String title, int itemsPerPage) {
        // 计算合适的GUI大小 (每页物品数量 + 底部导航栏)
        int rows = (int) Math.ceil((itemsPerPage + 9) / 9.0);  // 9是底部导航栏的格子数
        int size = Math.min(Math.max(rows * 9, 27), 54);  // 确保大小在27-54之间
        
        // 创建基础GUI
        GuiHolder holder = new BaseGuiHolder(player, title, size);
        
        // 创建分页布局
        GuiLayout layout = new PaginationLayout(itemsPerPage, 9, 1, 1);
        holder.setLayout(layout);
        
        return holder;
    }

    @Override
    public void addButtons(GuiHolder holder, Map<Integer, GuiButton> buttons) {
        if (buttons == null) return;
        buttons.forEach(holder::addButton);
    }

    @Override
    public GuiLayout createCustomLayout(int rows, int columns, int buttonSpacing, int borderWidth) {
        return new CustomLayout(rows, columns, buttonSpacing, borderWidth);
    }

    @Override
    public GuiHolder createDynamicGui(Player player, String title, int minSize, int maxSize, int contentSize) {
        // 计算合适的GUI大小
        int rows = (int) Math.ceil(Math.sqrt(contentSize));
        int size = Math.min(Math.max(rows * 9, minSize), maxSize);
        
        // 创建GUI
        GuiHolder holder = new BaseGuiHolder(player, title, size);
        
        // 创建自定义布局
        GuiLayout layout = new CustomLayout(rows, 9, 1, 1);
        holder.setLayout(layout);
        
        return holder;
    }

    @Override
    public void addItem(GuiHolder holder, int slot, ItemStack item) {
        if (holder instanceof BaseGuiHolder) {
            ((BaseGuiHolder) holder).getInventory().setItem(slot, item);
        }
    }

    @Override
    public void addItems(GuiHolder holder, Map<Integer, ItemStack> items) {
        if (holder instanceof BaseGuiHolder && items != null) {
            items.forEach((slot, item) -> ((BaseGuiHolder) holder).getInventory().setItem(slot, item));
        }
    }

    @Override
    public GuiStyle createDefaultStyle() {
        return new DefaultStyle();
    }

    @Override
    public GuiStyle createCustomStyle(Material borderMaterial, Material backgroundMaterial, Material buttonMaterial) {
        return new DefaultStyle(
            "自定义样式",
            "一个自定义的GUI样式",
            borderMaterial,
            backgroundMaterial,
            "&8边框",
            "&7背景"
        );
    }

    // =============== 配置系统API实现 ===============

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    @Override
    public void reloadConfig() {
        plugin.reloadConfig();
        configManager.reloadConfig();
        languageManager.reloadConfig();
    }

    @Override
    public FileConfiguration getMessageConfig() {
        return configManager.getMessageConfig();
    }

    @Override
    public FileConfiguration getDebugMessageConfig() {
        return configManager.getDebugMessageConfig();
    }

    // =============== 工具类API实现 ===============

    @Override
    public void setItemLore(ItemStack item, List<String> lore) {
        if (item == null || lore == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.setLore(lore.stream().map(ColorUtil::translateColor).toList());
        item.setItemMeta(meta);
    }

    @Override
    public String parseColor(String text) {
        return ColorUtil.translateColor(text);
    }

    @Override
    public String parsePlaceholders(Player player, String text) {
        if (text == null || player == null) return "";
        
        // 处理自定义占位符
        for (Map.Entry<String, java.util.function.Function<Player, String>> entry : customPlaceholders.entrySet()) {
            String placeholder = "%" + entry.getKey() + "%";
            if (text.contains(placeholder)) {
                text = text.replace(placeholder, entry.getValue().apply(player));
            }
        }
        
        // 处理PlaceholderAPI占位符
        if (hasPlaceholderAPI()) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }
        
        return text;
    }

    @Override
    public TextUtil getTextUtil() {
        return textUtil;
    }

    @Override
    public ColorUtil getColorUtil() {
        return colorUtil;
    }

    @Override
    public GuiUtil getGuiUtil() {
        return guiUtil;
    }

    // =============== PlaceholderAPI集成实现 ===============

    @Override
    public boolean hasPlaceholderAPI() {
        return plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    @Override
    public void registerPlaceholder(String name, java.util.function.Function<Player, String> placeholder) {
        customPlaceholders.put(name, placeholder);
    }

    @Override
    public String getAnimationStatus(GuiHolder holder, GuiAnimation animation) {
        if (holder instanceof BaseGuiHolder) {
            return ((BaseGuiHolder) holder).getAnimationStatus(animation);
        }
        return "STOP";
    }

    @Override
    public int getCurrentPage(GuiHolder holder) {
        if (holder.getLayout() instanceof PaginationLayout) {
            return ((PaginationLayout) holder.getLayout()).getCurrentPage();
        }
        return 0;
    }

    @Override
    public void setPage(GuiHolder holder, int page) {
        if (holder.getLayout() instanceof PaginationLayout) {
            ((PaginationLayout) holder.getLayout()).setCurrentPage(page);
            if (holder instanceof BaseGuiHolder) {
                ((BaseGuiHolder) holder).applyLayout(new GuiButton[0]);
            }
        }
    }
} 
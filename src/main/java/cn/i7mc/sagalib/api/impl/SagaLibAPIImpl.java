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
import cn.i7mc.sagalib.hologram.HologramManager;
import cn.i7mc.sagalib.hologram.animation.ColorChangeAnimation;
import cn.i7mc.sagalib.hologram.animation.FadeAnimation;
import cn.i7mc.sagalib.hologram.animation.HologramAnimation;
import cn.i7mc.sagalib.hologram.animation.ScrollingTextAnimation;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

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
    private final Map<String, HologramAnimation> hologramAnimations;

    public SagaLibAPIImpl(SagaLib plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.languageManager = plugin.getLanguageManager();
        this.textUtil = new TextUtil();
        this.colorUtil = new ColorUtil();
        this.guiUtil = new GuiUtil();
        this.customPlaceholders = new HashMap<>();
        this.animations = new HashMap<>();
        this.hologramAnimations = new HashMap<>();
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
        String customName = languageManager.getText("gui.style.custom_name");
        String customDescription = languageManager.getText("gui.style.custom_description");
        String borderText = languageManager.getText("gui.style.border_text");
        String backgroundText = languageManager.getText("gui.style.background_text");

        return new DefaultStyle(
            customName,
            customDescription,
            borderMaterial,
            backgroundMaterial,
            borderText,
            backgroundText
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
        return languageManager.getText("animation.status.stop");
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

    // =============== TextDisplay全息显示系统API实现 ===============

    @Override
    public String createHologram(Location location, String text) {
        return plugin.getHologramManager().createHologram(location, text);
    }

    @Override
    public String createHologram(Location location, String text, Color bgColor) {
        return plugin.getHologramManager().createHologram(location, text, bgColor);
    }

    @Override
    public String createHologram(Location location, String text, Color bgColor, 
                                TextDisplay.TextAlignment alignment, int lineWidth, 
                                byte opacity, boolean shadowed, boolean seeThrough) {
        return plugin.getHologramManager().createHologram(
            location, text, bgColor, alignment, lineWidth, opacity, shadowed, seeThrough
        );
    }

    @Override
    public boolean removeHologram(String id) {
        return plugin.getHologramManager().removeHologram(id);
    }

    @Override
    public boolean updateHologramText(String id, String text) {
        return plugin.getHologramManager().updateText(id, text);
    }

    @Override
    public boolean updateHologramLocation(String id, Location location) {
        return plugin.getHologramManager().updateLocation(id, location);
    }

    @Override
    public boolean updateHologramStyle(String id, Color bgColor, TextDisplay.TextAlignment alignment, 
                                      int lineWidth, byte opacity, boolean shadowed, boolean seeThrough) {
        return plugin.getHologramManager().updateStyle(
            id, bgColor, alignment, lineWidth, opacity, shadowed, seeThrough
        );
    }

    @Override
    public TextDisplay getHologramEntity(String id) {
        return plugin.getHologramManager().getHologram(id);
    }

    @Override
    public String createProgressBar(Location location, float width, float height, 
                                   double progress, Color fillColor, Color bgColor) {
        return plugin.getHologramManager().createProgressBar(location, width, height, progress, fillColor, bgColor);
    }

    @Override
    public boolean updateProgressBar(String id, double progress) {
        return plugin.getHologramManager().updateProgressBar(id, progress);
    }

    /**
     * 创建滚动文本动画
     * @param text 滚动文本
     * @param speed 滚动速度(tick间隔)
     * @param loop 是否循环
     * @return 动画实例
     */
    public HologramAnimation createScrollingTextAnimation(String text, int speed, boolean loop) {
        HologramAnimation animation = new ScrollingTextAnimation(plugin, text, speed, loop);
        hologramAnimations.put(animation.getId(), animation);
        return animation;
    }

    /**
     * 创建淡入淡出动画
     * @param fadeInTime 淡入时间(ticks)
     * @param stayTime 保持时间(ticks)
     * @param fadeOutTime 淡出时间(ticks)
     * @return 动画实例
     */
    public HologramAnimation createFadeAnimation(int fadeInTime, int stayTime, int fadeOutTime) {
        HologramAnimation animation = new FadeAnimation(plugin, fadeInTime, stayTime, fadeOutTime);
        hologramAnimations.put(animation.getId(), animation);
        return animation;
    }

    /**
     * 创建颜色变换动画
     * @param colors 颜色列表
     * @param interval 变换间隔(ticks)
     * @param smooth 是否平滑过渡
     * @return 动画实例
     */
    public HologramAnimation createColorChangeAnimation(List<Color> colors, int interval, boolean smooth) {
        HologramAnimation animation = new ColorChangeAnimation(plugin, colors, interval, smooth);
        hologramAnimations.put(animation.getId(), animation);
        return animation;
    }

    /**
     * 启动全息文本动画
     * @param hologramId 全息文本ID
     * @param animationId 动画ID
     * @return 是否成功启动
     */
    public boolean startHologramAnimation(String hologramId, String animationId) {
        TextDisplay display = getHologramEntity(hologramId);
        HologramAnimation animation = hologramAnimations.get(animationId);
        
        if (display == null || animation == null) {
            return false;
        }
        
        animation.start(display);
        return true;
    }

    /**
     * 停止全息文本动画
     * @param hologramId 全息文本ID
     * @param animationId 动画ID
     * @return 是否成功停止
     */
    public boolean stopHologramAnimation(String hologramId, String animationId) {
        HologramManager hologramManager = plugin.getHologramManager();
        HologramAnimation animation = hologramAnimations.get(animationId);
        
        if (animation == null) {
            return false;
        }
        
        TextDisplay textDisplay = hologramManager.getHologram(hologramId);
        if (textDisplay == null) {
            return false;
        }
        
        animation.stop(textDisplay);
        return true;
    }

    @Override
    public String createCircularProgressBar(Location location, float radius, 
                                          double progress, Color fillColor, Color bgColor) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.createCircularProgressBar(location, radius, progress, fillColor, bgColor);
    }
    
    @Override
    public boolean updateCircularProgressBar(String id, double progress) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.updateCircularProgressBar(id, progress);
    }
    
    @Override
    public String createVerticalProgressBar(Location location, float width, float height, 
                                          double progress, Color fillColor, Color bgColor) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.createVerticalProgressBar(location, width, height, progress, fillColor, bgColor);
    }
    
    @Override
    public boolean updateVerticalProgressBar(String id, double progress) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.updateVerticalProgressBar(id, progress);
    }
    
    @Override
    public String createInteractivePanel(Location location, String title, String content, 
                                       String interactionTag, Color bgColor) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.createInteractivePanel(location, title, content, interactionTag, bgColor);
    }
    
    @Override
    public boolean updatePanel(String id, String title, String content) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.updatePanel(id, title, content);
    }
    
    @Override
    public boolean setPanelInteractionTag(String id, String interactionTag) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.setPanelInteractionTag(id, interactionTag);
    }
    
    @Override
    public String createMultilinePanel(Location location, String title, String[] lines, Color bgColor) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.createMultilinePanel(location, title, lines, bgColor);
    }
    
    @Override
    public String createAutoUpdatePanel(Location location, String title, String initialContent, 
                                      int updateInterval, java.util.function.Supplier<String> contentSupplier, Color bgColor) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.createAutoUpdatePanel(location, title, initialContent, updateInterval, contentSupplier, bgColor);
    }
    
    @Override
    public boolean stopAutoUpdate(String id) {
        HologramManager hologramManager = plugin.getHologramManager();
        return hologramManager.stopAutoUpdate(id);
    }
} 
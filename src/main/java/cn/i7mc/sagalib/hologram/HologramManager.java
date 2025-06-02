package cn.i7mc.sagalib.hologram;

import cn.i7mc.sagalib.SagaLib;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 全息文本管理器
 * 管理所有的全息文本实体
 */
public class HologramManager {
    
    private final SagaLib plugin;
    private final Map<String, UUID> holograms = new HashMap<>();
    private final Map<String, UUID> progressBars = new HashMap<>();
    private static final String HOLOGRAM_KEY = "sagalib_hologram_id";
    private static final String PROGRESSBAR_KEY = "sagalib_progressbar_id";
    
    public HologramManager(SagaLib plugin) {
        this.plugin = plugin;
    }
    
    /**
     * 创建全息文本
     * @param location 位置
     * @param text 文本内容
     * @return 全息文本ID
     */
    public String createHologram(Location location, String text) {
        return createHologram(location, text, null, null, 200, (byte) -1, true, false);
    }
    
    /**
     * 创建全息文本(带背景颜色)
     * @param location 位置
     * @param text 文本内容
     * @param bgColor 背景颜色
     * @return 全息文本ID
     */
    public String createHologram(Location location, String text, Color bgColor) {
        return createHologram(location, text, bgColor, null, 200, (byte) -1, true, false);
    }
    
    /**
     * 创建全息文本(完整参数)
     * @param location 位置
     * @param text 文本内容
     * @param bgColor 背景颜色
     * @param alignment 对齐方式
     * @param lineWidth 行宽
     * @param opacity 不透明度
     * @param shadowed 是否有阴影
     * @param seeThrough 是否透视
     * @return 全息文本ID
     */
    public String createHologram(Location location, String text, Color bgColor, 
                                TextDisplay.TextAlignment alignment, int lineWidth, 
                                byte opacity, boolean shadowed, boolean seeThrough) {
        if (location == null || text == null) return null;
        
        World world = location.getWorld();
        if (world == null) return null;
        
        // 创建一个TextDisplay实体
        TextDisplay textDisplay = world.spawn(location, TextDisplay.class, display -> {
            // 设置文本
            display.setText(text);
            
            // 设置视觉效果
            display.setLineWidth(lineWidth);
            if (opacity != -1) display.setTextOpacity(opacity);
            display.setShadowed(shadowed);
            display.setSeeThrough(seeThrough);
            
            // 设置背景颜色
            if (bgColor != null) {
                display.setBackgroundColor(bgColor);
                display.setDefaultBackground(false);
            } else {
                // 默认设置为透明背景，而不是使用默认背景
                display.setDefaultBackground(false);
            }
            
            // 设置对齐方式
            if (alignment != null) {
                display.setAlignment(alignment);
            }
            
            // 设置基本显示属性
            display.setBillboard(Display.Billboard.CENTER);
            display.setViewRange(50);
            
            // 不受重力影响
            display.setGravity(false);
        });
        
        // 生成唯一ID
        String id = UUID.randomUUID().toString().substring(0, 8);
        
        // 存储到PersistentDataContainer中
        PersistentDataContainer pdc = textDisplay.getPersistentDataContainer();
        pdc.set(plugin.getKey(HOLOGRAM_KEY), PersistentDataType.STRING, id);
        
        // 保存到映射中
        holograms.put(id, textDisplay.getUniqueId());
        
        return id;
    }
    
    /**
     * 创建全息文本进度条
     * @param location 位置
     * @param width 宽度
     * @param height 高度
     * @param progress 进度(0-1)
     * @param fillColor 填充颜色
     * @param bgColor 背景颜色
     * @return 进度条ID
     */
    public String createProgressBar(Location location, float width, float height, 
                                  double progress, Color fillColor, Color bgColor) {
        if (location == null || progress < 0 || progress > 1) return null;
        
        World world = location.getWorld();
        if (world == null) return null;
        
        // 约束进度范围[0,1]
        final double finalProgress = Math.max(0, Math.min(1, progress));
        
        // 计算进度条的显示文本
        StringBuilder builder = new StringBuilder();
        
        // 默认颜色
        final Color finalFillColor = (fillColor != null) ? fillColor : Color.GREEN;
        final Color finalBgColor = (bgColor != null) ? bgColor : Color.fromRGB(50, 50, 50);
        
        // 创建背景和填充部分
        int totalBlocks = 20; // 进度条总块数
        final int filledBlocks = (int) Math.round(finalProgress * totalBlocks);
        
        // 创建进度条的TextDisplay实体
        TextDisplay progressBar = world.spawn(location, TextDisplay.class, display -> {
            // 构建进度条文本
            builder.append("§r");
            for (int i = 0; i < totalBlocks; i++) {
                if (i < filledBlocks) {
                    builder.append("█"); // 已填充部分
                } else {
                    builder.append("▒"); // 未填充部分
                }
            }
            
            // 设置文本
            display.setText(builder.toString());
            
            // 设置大小和比例
            display.setDisplayWidth(width);
            display.setDisplayHeight(height);
            
            // 设置视觉效果
            display.setBackgroundColor(finalBgColor);
            display.setDefaultBackground(false);
            display.setAlignment(TextDisplay.TextAlignment.CENTER);
            display.setShadowed(true);
            
            // 设置基本显示属性
            display.setBillboard(Display.Billboard.CENTER);
            display.setViewRange(50);
            
            // 存储进度值作为元数据，便于更新
            display.getPersistentDataContainer().set(
                plugin.getKey("progress_value"), 
                PersistentDataType.DOUBLE, 
                finalProgress
            );
            
            // 不受重力影响
            display.setGravity(false);
        });
        
        // 生成唯一ID
        String id = "pb_" + UUID.randomUUID().toString().substring(0, 6);
        
        // 存储到PersistentDataContainer中
        PersistentDataContainer pdc = progressBar.getPersistentDataContainer();
        pdc.set(plugin.getKey(PROGRESSBAR_KEY), PersistentDataType.STRING, id);
        
        // 保存到映射中
        progressBars.put(id, progressBar.getUniqueId());
        
        return id;
    }
    
    /**
     * 创建圆形进度条
     * @param location 位置
     * @param radius 半径
     * @param progress 进度(0-1)
     * @param fillColor 填充颜色
     * @param bgColor 背景颜色
     * @return 进度条ID
     */
    public String createCircularProgressBar(Location location, float radius, 
                                     double progress, Color fillColor, Color bgColor) {
        if (location == null || progress < 0 || progress > 1) return null;
        
        World world = location.getWorld();
        if (world == null) return null;
        
        // 约束进度范围[0,1]
        final double finalProgress = Math.max(0, Math.min(1, progress));
        
        // 默认颜色
        final Color finalFillColor = (fillColor != null) ? fillColor : Color.GREEN;
        final Color finalBgColor = (bgColor != null) ? bgColor : Color.fromRGB(50, 50, 50);
        
        // 构建圆形进度条文本，使用Unicode字符
        StringBuilder builder = new StringBuilder();
        
        // 创建圆形进度条的TextDisplay实体
        TextDisplay progressBar = world.spawn(location, TextDisplay.class, display -> {
            // 构建圆形进度条文本
            // 使用圆形Unicode字符创建圆形进度条
            // 每个扇区代表进度的一部分
            builder.append("§r");
            
            // 圆形组件字符集: "○◔◑◕●"
            if (finalProgress <= 0) {
                builder.append("○"); // 空圆
            } else if (finalProgress < 0.25) {
                builder.append("◔"); // 1/4圆
            } else if (finalProgress < 0.5) {
                builder.append("◑"); // 半圆
            } else if (finalProgress < 0.75) {
                builder.append("◕"); // 3/4圆
            } else {
                builder.append("●"); // 满圆
            }
            
            // 设置文本
            display.setText(builder.toString());
            
            // 设置大小和比例
            display.setDisplayWidth(radius * 2);
            display.setDisplayHeight(radius * 2);
            
            // 设置视觉效果
            display.setBackgroundColor(finalBgColor);
            display.setDefaultBackground(false);
            display.setAlignment(TextDisplay.TextAlignment.CENTER);
            display.setShadowed(true);
            
            // 设置基本显示属性
            display.setBillboard(Display.Billboard.CENTER);
            display.setViewRange(50);
            
            // 存储进度值作为元数据，便于更新
            display.getPersistentDataContainer().set(
                plugin.getKey("circular_progress_value"), 
                PersistentDataType.DOUBLE, 
                finalProgress
            );
            
            // 不受重力影响
            display.setGravity(false);
        });
        
        // 生成唯一ID
        String id = "crc_" + UUID.randomUUID().toString().substring(0, 6);
        
        // 存储到PersistentDataContainer中
        PersistentDataContainer pdc = progressBar.getPersistentDataContainer();
        pdc.set(plugin.getKey(PROGRESSBAR_KEY), PersistentDataType.STRING, id);
        
        // 保存到映射中
        progressBars.put(id, progressBar.getUniqueId());
        
        return id;
    }
    
    /**
     * 更新圆形进度条进度
     * @param id 进度条ID
     * @param progress 新进度(0-1)
     * @return 是否成功更新
     */
    public boolean updateCircularProgressBar(String id, double progress) {
        TextDisplay display = getProgressBar(id);
        if (display == null) return false;
        
        // 检查是否为圆形进度条
        PersistentDataContainer pdc = display.getPersistentDataContainer();
        if (!pdc.has(plugin.getKey("circular_progress_value"), PersistentDataType.DOUBLE)) {
            return false;
        }
        
        // 约束进度范围[0,1]
        progress = Math.max(0, Math.min(1, progress));
        
        // 更新进度值元数据
        display.getPersistentDataContainer().set(
            plugin.getKey("circular_progress_value"), 
            PersistentDataType.DOUBLE, 
            progress
        );
        
        // 构建圆形进度条文本
        StringBuilder builder = new StringBuilder();
        builder.append("§r");
        
        // 圆形组件字符集: "○◔◑◕●"
        if (progress <= 0) {
            builder.append("○"); // 空圆
        } else if (progress < 0.25) {
            builder.append("◔"); // 1/4圆
        } else if (progress < 0.5) {
            builder.append("◑"); // 半圆
        } else if (progress < 0.75) {
            builder.append("◕"); // 3/4圆
        } else {
            builder.append("●"); // 满圆
        }
        
        // 更新文本
        display.setText(builder.toString());
        
        return true;
    }

    /**
     * 创建垂直进度条
     * @param location 位置
     * @param width 宽度
     * @param height 高度
     * @param progress 进度(0-1)
     * @param fillColor 填充颜色
     * @param bgColor 背景颜色
     * @return 进度条ID
     */
    public String createVerticalProgressBar(Location location, float width, float height, 
                                         double progress, Color fillColor, Color bgColor) {
        if (location == null || progress < 0 || progress > 1) return null;
        
        World world = location.getWorld();
        if (world == null) return null;
        
        // 约束进度范围[0,1]
        final double finalProgress = Math.max(0, Math.min(1, progress));
        
        // 计算进度条的显示文本
        StringBuilder builder = new StringBuilder();
        
        // 默认颜色
        final Color finalFillColor = (fillColor != null) ? fillColor : Color.GREEN;
        final Color finalBgColor = (bgColor != null) ? bgColor : Color.fromRGB(50, 50, 50);
        
        // 创建垂直进度条的TextDisplay实体
        TextDisplay progressBar = world.spawn(location, TextDisplay.class, display -> {
            // 垂直进度条使用多行文本表示
            builder.append("§r");
            
            int totalRows = 10; // 进度条总行数
            int filledRows = (int) Math.round(finalProgress * totalRows);
            
            // 从下往上构建，先空后填充，因为进度条通常是从下往上增长的
            for (int i = totalRows - 1; i >= 0; i--) {
                if (i >= totalRows - filledRows) {
                    builder.append("█"); // 已填充部分
                } else {
                    builder.append("▒"); // 未填充部分
                }
                // 每行结束添加换行符
                if (i > 0) {
                    builder.append("\n");
                }
            }
            
            // 设置文本
            display.setText(builder.toString());
            
            // 设置大小和比例
            display.setDisplayWidth(width);
            display.setDisplayHeight(height);
            
            // 设置视觉效果
            display.setBackgroundColor(finalBgColor);
            display.setDefaultBackground(false);
            display.setAlignment(TextDisplay.TextAlignment.CENTER);
            display.setShadowed(true);
            
            // 设置基本显示属性
            display.setBillboard(Display.Billboard.CENTER);
            display.setViewRange(50);
            
            // 存储进度值作为元数据，便于更新
            display.getPersistentDataContainer().set(
                plugin.getKey("vertical_progress_value"), 
                PersistentDataType.DOUBLE, 
                finalProgress
            );
            
            // 不受重力影响
            display.setGravity(false);
        });
        
        // 生成唯一ID
        String id = "vpb_" + UUID.randomUUID().toString().substring(0, 6);
        
        // 存储到PersistentDataContainer中
        PersistentDataContainer pdc = progressBar.getPersistentDataContainer();
        pdc.set(plugin.getKey(PROGRESSBAR_KEY), PersistentDataType.STRING, id);
        
        // 保存到映射中
        progressBars.put(id, progressBar.getUniqueId());
        
        return id;
    }
    
    /**
     * 更新垂直进度条进度
     * @param id 进度条ID
     * @param progress 新进度(0-1)
     * @return 是否成功更新
     */
    public boolean updateVerticalProgressBar(String id, double progress) {
        TextDisplay display = getProgressBar(id);
        if (display == null) return false;
        
        // 检查是否为垂直进度条
        PersistentDataContainer pdc = display.getPersistentDataContainer();
        if (!pdc.has(plugin.getKey("vertical_progress_value"), PersistentDataType.DOUBLE)) {
            return false;
        }
        
        // 约束进度范围[0,1]
        progress = Math.max(0, Math.min(1, progress));
        
        // 更新进度值元数据
        display.getPersistentDataContainer().set(
            plugin.getKey("vertical_progress_value"), 
            PersistentDataType.DOUBLE, 
            progress
        );
        
        // 构建垂直进度条文本
        StringBuilder builder = new StringBuilder();
        builder.append("§r");
        
        int totalRows = 10; // 进度条总行数
        int filledRows = (int) Math.round(progress * totalRows);
        
        // 从下往上构建
        for (int i = totalRows - 1; i >= 0; i--) {
            if (i >= totalRows - filledRows) {
                builder.append("█"); // 已填充部分
            } else {
                builder.append("▒"); // 未填充部分
            }
            // 每行结束添加换行符
            if (i > 0) {
                builder.append("\n");
            }
        }
        
        // 更新文本
        display.setText(builder.toString());
        
        return true;
    }
    
    /**
     * 删除全息文本
     * @param id 全息文本ID
     * @return 是否成功删除
     */
    public boolean removeHologram(String id) {
        UUID entityUUID = holograms.get(id);
        if (entityUUID == null) return false;
        
        for (World world : plugin.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getUniqueId().equals(entityUUID)) {
                    entity.remove();
                    holograms.remove(id);
                    return true;
                }
            }
        }
        
        // 实体不存在，从映射中移除
        holograms.remove(id);
        return false;
    }
    
    /**
     * 根据ID获取全息文本实体
     * @param id 全息文本ID
     * @return 全息文本实体
     */
    public TextDisplay getHologram(String id) {
        UUID entityUUID = holograms.get(id);
        if (entityUUID == null) return null;
        
        for (World world : plugin.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getUniqueId().equals(entityUUID) && entity instanceof TextDisplay) {
                    return (TextDisplay) entity;
                }
            }
        }
        
        // 实体不存在，从映射中移除
        holograms.remove(id);
        return null;
    }
    
    /**
     * 获取进度条实体
     * @param id 进度条ID
     * @return 进度条实体
     */
    public TextDisplay getProgressBar(String id) {
        UUID entityUUID = progressBars.get(id);
        if (entityUUID == null) return null;
        
        for (World world : plugin.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getUniqueId().equals(entityUUID) && entity instanceof TextDisplay) {
                    return (TextDisplay) entity;
                }
            }
        }
        
        // 实体不存在，从映射中移除
        progressBars.remove(id);
        return null;
    }
    
    /**
     * 更新全息文本内容
     * @param id 全息文本ID
     * @param text 新文本内容
     * @return 是否成功更新
     */
    public boolean updateText(String id, String text) {
        TextDisplay display = getHologram(id);
        if (display == null) return false;
        
        display.setText(text);
        return true;
    }
    
    /**
     * 更新全息文本位置
     * @param id 全息文本ID
     * @param location 新位置
     * @return 是否成功更新
     */
    public boolean updateLocation(String id, Location location) {
        TextDisplay display = getHologram(id);
        if (display == null || location == null) return false;
        
        display.teleport(location);
        return true;
    }
    
    /**
     * 更新全息文本样式
     * @param id 全息文本ID
     * @param bgColor 背景颜色
     * @param alignment 对齐方式
     * @param lineWidth 行宽
     * @param opacity 不透明度
     * @param shadowed 是否有阴影
     * @param seeThrough 是否透视
     * @return 是否成功更新
     */
    public boolean updateStyle(String id, Color bgColor, TextDisplay.TextAlignment alignment, 
                              int lineWidth, byte opacity, boolean shadowed, boolean seeThrough) {
        TextDisplay display = getHologram(id);
        if (display == null) return false;
        
        // 更新样式
        if (bgColor != null) {
            display.setBackgroundColor(bgColor);
            display.setDefaultBackground(false);
        }
        
        if (alignment != null) {
            display.setAlignment(alignment);
        }
        
        display.setLineWidth(lineWidth);
        if (opacity != -1) display.setTextOpacity(opacity);
        display.setShadowed(shadowed);
        display.setSeeThrough(seeThrough);
        
        return true;
    }
    
    /**
     * 更新进度条进度
     * @param id 进度条ID
     * @param progress 新进度(0-1)
     * @return 是否成功更新
     */
    public boolean updateProgressBar(String id, double progress) {
        TextDisplay display = getProgressBar(id);
        if (display == null) return false;
        
        // 约束进度范围[0,1]
        progress = Math.max(0, Math.min(1, progress));
        
        // 更新进度值元数据
        display.getPersistentDataContainer().set(
            plugin.getKey("progress_value"), 
            PersistentDataType.DOUBLE, 
            progress
        );
        
        // 重新计算进度条的显示文本
        StringBuilder builder = new StringBuilder();
        
        // 创建背景和填充部分
        int totalBlocks = 20; // 进度条总块数
        int filledBlocks = (int) Math.round(progress * totalBlocks);
        
        // 构建进度条文本
        builder.append("§r");
        for (int i = 0; i < totalBlocks; i++) {
            if (i < filledBlocks) {
                builder.append("█"); // 已填充部分
            } else {
                builder.append("▒"); // 未填充部分
            }
        }
        
        // 更新文本
        display.setText(builder.toString());
        
        return true;
    }
    
    /**
     * 获取所有全息文本
     * @return 全息文本映射
     */
    public Map<String, UUID> getAllHolograms() {
        return new HashMap<>(holograms);
    }
    
    /**
     * 清除所有全息实体
     */
    public void clearAllHolograms() {
        for (UUID uuid : holograms.values()) {
            for (World world : plugin.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getUniqueId().equals(uuid)) {
                        entity.remove();
                    }
                }
            }
        }
        
        holograms.clear();
        clearAllProgressBars();
    }
    
    /**
     * 清除所有进度条
     */
    public void clearAllProgressBars() {
        for (UUID uuid : progressBars.values()) {
            for (World world : plugin.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getUniqueId().equals(uuid)) {
                        entity.remove();
                    }
                }
            }
        }
        
        progressBars.clear();
    }

    /**
     * 创建交互式信息面板
     * @param location 位置
     * @param title 标题
     * @param content 内容
     * @param interactionTag 交互标签(用于识别点击事件)
     * @param bgColor 背景颜色
     * @return 面板ID
     */
    public String createInteractivePanel(Location location, String title, String content, 
                                     String interactionTag, Color bgColor) {
        if (location == null || title == null || content == null) return null;
        
        World world = location.getWorld();
        if (world == null) return null;
        
        // 格式化显示内容
        StringBuilder builder = new StringBuilder();
        builder.append("§l§6").append(title).append("§r\n\n"); // 标题用金色粗体
        builder.append(content);
        
        // 默认背景颜色
        final Color finalBgColor = (bgColor != null) ? bgColor : Color.fromRGB(30, 30, 60);
        
        // 创建面板的TextDisplay实体
        TextDisplay panel = world.spawn(location, TextDisplay.class, display -> {
            // 设置文本
            display.setText(builder.toString());
            
            // 设置视觉效果
            display.setBackgroundColor(finalBgColor);
            display.setDefaultBackground(false);
            display.setAlignment(TextDisplay.TextAlignment.CENTER);
            display.setShadowed(true);
            display.setLineWidth(200); // 较大的行宽以适应内容
            
            // 设置基本显示属性 - 使用固定视图模式，更容易点击
            display.setBillboard(Display.Billboard.FIXED);
            display.setViewRange(64); // 增加视野范围
            
            // 设置尺寸 - 使面板更大更明显
            display.setDisplayWidth(2.0f);
            display.setDisplayHeight(1.0f);
            
            // 设置交互标签
            if (interactionTag != null && !interactionTag.isEmpty()) {
                display.getPersistentDataContainer().set(
                    plugin.getKey("interaction_tag"), 
                    PersistentDataType.STRING, 
                    interactionTag
                );
            }
            
            // 设置面板ID标识
            String panelId = "panel_" + UUID.randomUUID().toString().substring(0, 6);
            display.getPersistentDataContainer().set(
                plugin.getKey("panel_id"), 
                PersistentDataType.STRING, 
                panelId
            );
            
            // 存储标题和内容，便于后续更新
            display.getPersistentDataContainer().set(
                plugin.getKey("panel_title"), 
                PersistentDataType.STRING, 
                title
            );
            
            display.getPersistentDataContainer().set(
                plugin.getKey("panel_content"), 
                PersistentDataType.STRING, 
                content
            );
            
            // 不受重力影响
            display.setGravity(false);
            
            // 注意：TextDisplay没有setInteractive方法，所以我们不能直接设置其交互属性
            // 玩家可以通过实体点击事件来与TextDisplay交互
        });
        
        // 生成唯一ID
        String id = "panel_" + UUID.randomUUID().toString().substring(0, 6);
        
        // 存储到PersistentDataContainer中
        PersistentDataContainer pdc = panel.getPersistentDataContainer();
        pdc.set(plugin.getKey(HOLOGRAM_KEY), PersistentDataType.STRING, id);
        
        // 保存到映射中
        holograms.put(id, panel.getUniqueId());
        
        
        return id;
    }
    
    /**
     * 更新信息面板内容
     * @param id 面板ID
     * @param title 新标题
     * @param content 新内容
     * @return 是否成功更新
     */
    public boolean updatePanel(String id, String title, String content) {
        TextDisplay display = getHologram(id);
        if (display == null) return false;
        
        // 检查是否为面板
        PersistentDataContainer pdc = display.getPersistentDataContainer();
        if (!pdc.has(plugin.getKey("panel_id"), PersistentDataType.STRING)) {
            return false;
        }
        
        // 如果标题或内容为空，尝试使用现有的值
        if (title == null) {
            title = pdc.get(plugin.getKey("panel_title"), PersistentDataType.STRING);
        } else {
            // 更新存储的标题
            pdc.set(plugin.getKey("panel_title"), PersistentDataType.STRING, title);
        }
        
        if (content == null) {
            content = pdc.get(plugin.getKey("panel_content"), PersistentDataType.STRING);
        } else {
            // 更新存储的内容
            pdc.set(plugin.getKey("panel_content"), PersistentDataType.STRING, content);
        }
        
        // 格式化显示内容
        StringBuilder builder = new StringBuilder();
        builder.append("§l§6").append(title).append("§r\n\n"); // 标题用金色粗体
        builder.append(content);
        
        // 更新文本
        display.setText(builder.toString());
        
        return true;
    }
    
    /**
     * 设置面板交互标签
     * @param id 面板ID
     * @param interactionTag 新交互标签
     * @return 是否成功设置
     */
    public boolean setPanelInteractionTag(String id, String interactionTag) {
        TextDisplay display = getHologram(id);
        if (display == null) return false;
        
        // 检查是否为面板
        PersistentDataContainer pdc = display.getPersistentDataContainer();
        if (!pdc.has(plugin.getKey("panel_id"), PersistentDataType.STRING)) {
            return false;
        }
        
        // 更新交互标签
        if (interactionTag != null && !interactionTag.isEmpty()) {
            pdc.set(plugin.getKey("interaction_tag"), PersistentDataType.STRING, interactionTag);
        } else {
            // 如果提供空标签，则移除交互功能
            pdc.remove(plugin.getKey("interaction_tag"));
        }
        
        return true;
    }

    /**
     * 创建多行文本面板
     * @param location 位置
     * @param title 标题
     * @param lines 文本行数组
     * @param bgColor 背景颜色
     * @return 面板ID
     */
    public String createMultilinePanel(Location location, String title, String[] lines, Color bgColor) {
        if (location == null || lines == null || lines.length == 0) return null;
        
        // 格式化内容
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            content.append(lines[i]);
            if (i < lines.length - 1) {
                content.append("\n");
            }
        }
        
        // 使用通用方法创建面板，但不设置交互标签
        return createInteractivePanel(location, title, content.toString(), null, bgColor);
    }
    
    /**
     * 创建自动更新面板
     * @param location 位置
     * @param title 标题
     * @param initialContent 初始内容
     * @param updateInterval 更新间隔(tick)
     * @param contentSupplier 内容提供者函数
     * @param bgColor 背景颜色
     * @return 面板ID
     */
    public String createAutoUpdatePanel(Location location, String title, String initialContent, 
                                    int updateInterval, java.util.function.Supplier<String> contentSupplier, Color bgColor) {
        if (location == null || contentSupplier == null) return null;
        
        // 先创建面板
        String panelId = createInteractivePanel(location, title, initialContent, null, bgColor);
        if (panelId == null) return null;
        
        // 获取面板实体
        TextDisplay panel = getHologram(panelId);
        if (panel == null) return null;
        
        // 设置自动更新标记
        panel.getPersistentDataContainer().set(
            plugin.getKey("auto_update"), 
            PersistentDataType.BOOLEAN, 
            true
        );
        
        // 创建更新任务
        new BukkitRunnable() {
            @Override
            public void run() {
                // 检查面板是否存在
                TextDisplay currentPanel = getHologram(panelId);
                if (currentPanel == null || !currentPanel.isValid()) {
                    cancel();
                    return;
                }
                
                // 检查是否仍然启用自动更新
                PersistentDataContainer pdc = currentPanel.getPersistentDataContainer();
                if (!pdc.has(plugin.getKey("auto_update"), PersistentDataType.BOOLEAN) || 
                    !pdc.get(plugin.getKey("auto_update"), PersistentDataType.BOOLEAN)) {
                    cancel();
                    return;
                }
                
                // 获取最新内容
                String newContent = contentSupplier.get();
                
                // 更新面板
                String currentTitle = pdc.get(plugin.getKey("panel_title"), PersistentDataType.STRING);
                updatePanel(panelId, currentTitle, newContent);
            }
        }.runTaskTimer(plugin, updateInterval, updateInterval);
        
        return panelId;
    }
    
    /**
     * 停止面板自动更新
     * @param id 面板ID
     * @return 是否成功停止
     */
    public boolean stopAutoUpdate(String id) {
        TextDisplay display = getHologram(id);
        if (display == null) return false;
        
        // 检查是否为自动更新面板
        PersistentDataContainer pdc = display.getPersistentDataContainer();
        if (!pdc.has(plugin.getKey("auto_update"), PersistentDataType.BOOLEAN) || 
            !pdc.get(plugin.getKey("auto_update"), PersistentDataType.BOOLEAN)) {
            return false;
        }
        
        // 禁用自动更新
        pdc.set(plugin.getKey("auto_update"), PersistentDataType.BOOLEAN, false);
        
        return true;
    }
} 
package cn.i7mc.sagalib.api;

import cn.i7mc.sagalib.gui.holder.GuiHolder;
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.layout.GuiLayout;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import cn.i7mc.sagalib.config.ConfigManager;
import cn.i7mc.sagalib.config.LanguageManager;
import cn.i7mc.sagalib.util.ColorUtil;
import cn.i7mc.sagalib.util.TextUtil;
import cn.i7mc.sagalib.util.GuiUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Material;
import cn.i7mc.sagalib.SagaLib;

import java.util.List;
import java.util.Map;

/**
 * SagaLib API接口
 * 提供其他插件调用SagaLib功能的方法
 */
public interface SagaLibAPI {
    
    /**
     * 获取SagaLibAPI实例
     * @return SagaLibAPI实例
     */
    static SagaLibAPI getInstance() {
        return SagaLib.getAPI();
    }
    
    // =============== GUI系统API ===============
    
    /**
     * 创建GUI界面
     * @param player 玩家，用于解析占位符
     * @param title GUI标题
     * @param size GUI大小
     * @return GuiHolder实例
     */
    GuiHolder createGui(Player player, String title, int size);
    
    /**
     * 应用GUI样式
     * @param holder GUI容器
     * @param style 样式
     */
    void applyStyle(GuiHolder holder, GuiStyle style);
    
    /**
     * 添加GUI按钮
     * @param holder GUI容器
     * @param slot 按钮位置
     * @param button 按钮实例
     */
    void addButton(GuiHolder holder, int slot, GuiButton button);
    
    /**
     * 批量添加GUI按钮
     * @param holder GUI容器
     * @param buttons 按钮Map，key为位置，value为按钮实例
     */
    void addButtons(GuiHolder holder, Map<Integer, GuiButton> buttons);
    
    /**
     * 添加物品到GUI
     * @param holder GUI容器
     * @param slot 物品位置
     * @param item 物品实例
     */
    void addItem(GuiHolder holder, int slot, ItemStack item);
    
    /**
     * 批量添加物品到GUI
     * @param holder GUI容器
     * @param items 物品Map，key为位置，value为物品实例
     */
    void addItems(GuiHolder holder, Map<Integer, ItemStack> items);
    
    /**
     * 设置GUI布局
     * @param holder GUI容器
     * @param layout 布局实例
     */
    void setLayout(GuiHolder holder, GuiLayout layout);
    
    /**
     * 创建自定义布局
     * @param rows 行数
     * @param columns 列数
     * @param buttonSpacing 按钮间距
     * @param borderWidth 边框宽度
     * @return 自定义布局实例
     */
    GuiLayout createCustomLayout(int rows, int columns, int buttonSpacing, int borderWidth);
    
    /**
     * 创建默认样式
     * @return DefaultStyle实例
     */
    GuiStyle createDefaultStyle();
    
    /**
     * 创建自定义样式
     * @param borderMaterial 边框材质
     * @param backgroundMaterial 背景材质
     * @param buttonMaterial 按钮材质
     * @return GuiStyle实例
     */
    GuiStyle createCustomStyle(Material borderMaterial, Material backgroundMaterial, Material buttonMaterial);
    
    /**
     * 添加GUI动画
     * @param holder GUI容器
     * @param animation 动画实例
     */
    void addAnimation(GuiHolder holder, GuiAnimation animation);
    
    /**
     * 启动GUI动画
     * @param holder GUI容器
     * @param animation 动画实例
     */
    void startAnimation(GuiHolder holder, GuiAnimation animation);
    
    /**
     * 停止GUI动画
     * @param holder GUI容器
     * @param animation 动画实例
     */
    void stopAnimation(GuiHolder holder, GuiAnimation animation);
    
    /**
     * 创建分页GUI
     * @param player 玩家
     * @param title GUI标题
     * @param itemsPerPage 每页物品数量
     * @return GuiHolder实例
     */
    GuiHolder createPaginationGui(Player player, String title, int itemsPerPage);
    
    /**
     * 获取当前页码
     * @param holder GUI容器
     * @return 当前页码(从0开始)
     */
    int getCurrentPage(GuiHolder holder);
    
    /**
     * 设置当前页码
     * @param holder GUI容器
     * @param page 页码(从0开始)
     */
    void setPage(GuiHolder holder, int page);
    
    /**
     * 创建动态大小GUI
     * @param player 玩家
     * @param title GUI标题
     * @param minSize 最小大小
     * @param maxSize 最大大小
     * @param contentSize 内容大小
     * @return GuiHolder实例
     */
    GuiHolder createDynamicGui(Player player, String title, int minSize, int maxSize, int contentSize);
    
    // =============== 配置系统API ===============
    
    /**
     * 获取配置管理器
     * @return ConfigManager实例
     */
    ConfigManager getConfigManager();
    
    /**
     * 获取语言管理器
     * @return LanguageManager实例
     */
    LanguageManager getLanguageManager();
    
    /**
     * 重载配置
     */
    void reloadConfig();
    
    /**
     * 获取消息配置
     * @return FileConfiguration实例
     */
    FileConfiguration getMessageConfig();
    
    /**
     * 获取调试消息配置
     * @return FileConfiguration实例
     */
    FileConfiguration getDebugMessageConfig();
    
    // =============== 工具类API ===============
    
    /**
     * 设置物品LORE
     * @param item 物品
     * @param lore LORE列表
     */
    void setItemLore(ItemStack item, List<String> lore);
    
    /**
     * 解析颜色代码
     * @param text 包含颜色代码的文本
     * @return 解析后的文本
     */
    String parseColor(String text);
    
    /**
     * 解析占位符
     * @param player 玩家
     * @param text 包含占位符的文本
     * @return 解析后的文本
     */
    String parsePlaceholders(Player player, String text);
    
    /**
     * 文本工具类
     * @return TextUtil实例
     */
    TextUtil getTextUtil();
    
    /**
     * 颜色工具类
     * @return ColorUtil实例
     */
    ColorUtil getColorUtil();
    
    /**
     * GUI工具类
     * @return GuiUtil实例
     */
    GuiUtil getGuiUtil();
    
    // =============== PlaceholderAPI集成 ===============
    
    /**
     * 检查是否安装了PlaceholderAPI
     * @return 是否安装了PlaceholderAPI
     */
    boolean hasPlaceholderAPI();
    
    /**
     * 获取动画状态
     * @param holder GUI容器
     * @param animation 动画实例
     * @return 动画状态(START/STOP/PAUSE/RESUME)
     */
    String getAnimationStatus(GuiHolder holder, GuiAnimation animation);
    
    /**
     * 注册自定义占位符
     * @param name 占位符名称
     * @param placeholder 占位符处理器
     */
    void registerPlaceholder(String name, java.util.function.Function<Player, String> placeholder);
} 
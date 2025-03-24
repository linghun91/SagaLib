package cn.i7mc.sagalib.gui.style;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * GUI样式接口
 * 定义GUI的样式属性
 */
public interface GuiStyle {
    /**
     * 获取边框材质
     * @return 边框材质
     */
    Material getBorderMaterial();

    /**
     * 获取背景材质
     * @return 背景材质
     */
    Material getBackgroundMaterial();

    /**
     * 获取按钮材质
     * @return 按钮材质
     */
    Material getButtonMaterial();

    /**
     * 获取标题颜色
     * @param player 玩家，用于解析占位符
     * @return 标题颜色
     */
    String getTitleColor(Player player);

    /**
     * 获取按钮名称颜色
     * @param player 玩家，用于解析占位符
     * @return 按钮名称颜色
     */
    String getButtonNameColor(Player player);

    /**
     * 获取按钮描述颜色
     * @param player 玩家，用于解析占位符
     * @return 按钮描述颜色
     */
    String getButtonLoreColor(Player player);

    /**
     * 创建边框物品
     * @param player 玩家，用于解析占位符
     * @return 边框物品
     */
    ItemStack createBorderItem(Player player);

    /**
     * 创建背景物品
     * @param player 玩家，用于解析占位符
     * @return 背景物品
     */
    ItemStack createBackgroundItem(Player player);

    /**
     * 创建按钮物品
     * @param player 玩家，用于解析占位符
     * @param name 按钮名称
     * @return 按钮物品
     */
    ItemStack createButtonItem(Player player, String name);

    /**
     * 获取背景物品
     * @param player 玩家，用于解析占位符
     * @return 背景物品
     */
    ItemStack getBackgroundItem(Player player);

    /**
     * 获取边框物品
     * @param player 玩家，用于解析占位符
     * @return 边框物品
     */
    ItemStack getBorderItem(Player player);

    /**
     * 获取样式名称
     * @param player 玩家，用于解析占位符
     * @return 样式名称
     */
    String getName(Player player);

    /**
     * 获取样式描述
     * @param player 玩家，用于解析占位符
     * @return 样式描述
     */
    String getDescription(Player player);
} 
package cn.i7mc.sagalib.gui.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * GUI按钮接口
 * 定义按钮的基本行为和属性
 */
public interface GuiButton {
    /**
     * 获取按钮显示的物品
     * @param player 玩家，用于解析占位符
     * @return 按钮物品
     */
    ItemStack getItem(Player player);

    /**
     * 获取按钮槽位
     * @return 槽位索引
     */
    int getSlot();

    /**
     * 设置按钮槽位
     * @param slot 槽位索引
     */
    void setSlot(int slot);

    /**
     * 按钮点击事件处理
     * @param event 点击事件
     */
    void onClick(InventoryClickEvent event);

    /**
     * 更新按钮显示
     * @param player 玩家，用于解析占位符
     */
    void update(Player player);
} 
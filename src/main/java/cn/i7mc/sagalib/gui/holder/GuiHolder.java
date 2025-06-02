package cn.i7mc.sagalib.gui.holder;

import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.layout.GuiLayout;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * GUI容器接口
 * 用于管理GUI的基本功能
 */
public interface GuiHolder extends InventoryHolder {
    
    /**
     * 获取GUI标题
     * @return GUI标题
     */
    String getTitle();
    
    /**
     * 获取GUI大小
     * @return GUI大小
     */
    int getSize();
    
    /**
     * 设置GUI物品
     * @param slot 槽位
     * @param item 物品
     */
    void setItem(int slot, ItemStack item);
    
    /**
     * 获取GUI物品
     * @param slot 槽位
     * @return 物品
     */
    ItemStack getItem(int slot);
    
    /**
     * 清除GUI物品
     * @param slot 槽位
     */
    void clearItem(int slot);
    
    /**
     * 清除所有GUI物品
     */
    void clearAllItems();
    
    /**
     * 更新GUI
     */
    void update();

    /**
     * 添加按钮
     * @param slot 槽位
     * @param button 按钮
     */
    void addButton(int slot, GuiButton button);

    /**
     * 设置GUI样式
     * @param style GUI样式
     */
    void setStyle(GuiStyle style);

    /**
     * 获取GUI样式
     * @return GUI样式
     */
    GuiStyle getStyle();

    /**
     * 设置GUI布局
     * @param layout GUI布局
     */
    void setLayout(GuiLayout layout);

    /**
     * 获取GUI布局
     * @return GUI布局
     */
    GuiLayout getLayout();

    /**
     * 设置GUI动画
     * @param animation GUI动画
     */
    void setAnimation(GuiAnimation animation);

    /**
     * 获取GUI动画
     * @return GUI动画
     */
    GuiAnimation getAnimation();

    /**
     * 停止当前动画
     */
    void stopAnimation();

    /**
     * 获取GUI所属玩家
     * @return 玩家
     */
    org.bukkit.entity.Player getPlayer();

    /**
     * 添加动画
     * @param animation 动画
     */
    void addAnimation(GuiAnimation animation);
} 
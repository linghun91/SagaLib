package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.DecoratedPot;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * DecoratedPot类型的GUI容器实现
 * 用于装饰罐类型方块的GUI显示
 */
public class DecoratedPotGuiHolder extends BaseGuiHolder {

    private final DecoratedPot decoratedPot;

    /**
     * 创建一个新的DecoratedPot GUI容器
     * @param player 玩家，用于解析占位符
     * @param decoratedPot 装饰罐方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public DecoratedPotGuiHolder(Player player, DecoratedPot decoratedPot, String title, int size) {
        super(player, title, size);
        this.decoratedPot = decoratedPot;
    }

    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param decoratedPot 装饰罐方块
     * @param inventory 已有的Inventory
     */
    public DecoratedPotGuiHolder(Player player, DecoratedPot decoratedPot, Inventory inventory) {
        super(player, inventory);
        this.decoratedPot = decoratedPot;
    }

    /**
     * 获取该GUI对应的装饰罐方块
     * @return 装饰罐方块
     */
    public DecoratedPot getDecoratedPot() {
        return decoratedPot;
    }

    /**
     * 获取装饰罐的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return decoratedPot.getX();
    }

    /**
     * 获取装饰罐的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return decoratedPot.getY();
    }

    /**
     * 获取装饰罐的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return decoratedPot.getZ();
    }

    /**
     * 获取装饰罐所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return decoratedPot.getWorld().getName();
    }

    /**
     * 获取装饰罐里的物品
     * @return 物品
     */
    public ItemStack getItem() {
        // 1.20.1版本中DecoratedPot没有getInventory方法
        // 在1.20.1中，装饰罐不能存储物品
        return null;
    }

    /**
     * 设置装饰罐里的物品
     * @param item 物品
     */
    public void setItem(ItemStack item) {
        // 1.20.1版本中DecoratedPot没有getInventory方法
        // 在1.20.1中，装饰罐不能存储物品
    }

    /**
     * 更新装饰罐方块状态
     */
    @Override
    public void update() {
        super.update();
        decoratedPot.update();
    }
}
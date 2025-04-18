package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * DoubleChest类型的GUI容器实现
 * 用于大箱子类型方块的GUI显示
 */
public class DoubleChestGuiHolder extends BaseGuiHolder {
    
    private final DoubleChest doubleChest;
    
    /**
     * 创建一个新的DoubleChest GUI容器
     * @param player 玩家，用于解析占位符
     * @param doubleChest 大箱子方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public DoubleChestGuiHolder(Player player, DoubleChest doubleChest, String title, int size) {
        super(player, title, size);
        this.doubleChest = doubleChest;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param doubleChest 大箱子方块
     * @param inventory 已有的Inventory
     */
    public DoubleChestGuiHolder(Player player, DoubleChest doubleChest, Inventory inventory) {
        super(player, inventory);
        this.doubleChest = doubleChest;
    }
    
    /**
     * 获取该GUI对应的大箱子方块
     * @return 大箱子方块
     */
    public DoubleChest getDoubleChest() {
        return doubleChest;
    }
    
    /**
     * 获取大箱子的左侧箱子
     * @return 左侧箱子
     */
    public InventoryHolder getLeftSide() {
        return doubleChest.getLeftSide();
    }
    
    /**
     * 获取大箱子的右侧箱子
     * @return 右侧箱子
     */
    public InventoryHolder getRightSide() {
        return doubleChest.getRightSide();
    }
    
    /**
     * 获取大箱子的X坐标
     * @return X坐标
     */
    public double getX() {
        return doubleChest.getX();
    }
    
    /**
     * 获取大箱子的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return doubleChest.getY();
    }
    
    /**
     * 获取大箱子的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return doubleChest.getZ();
    }
    
    /**
     * 获取大箱子的宽度
     * @return 宽度
     */
    public double getWidth() {
        return doubleChest.getInventory().getSize() / 9;
    }
    
    /**
     * 获取大箱子的总容量
     * @return 总容量
     */
    public int getCapacity() {
        return doubleChest.getInventory().getSize();
    }
    
    /**
     * 更新大箱子状态
     */
    @Override
    public void update() {
        super.update();
        // 大箱子没有直接的update方法，但可以更新其内部的两个箱子
        if (getLeftSide() instanceof org.bukkit.block.Chest) {
            ((org.bukkit.block.Chest) getLeftSide()).update();
        }
        if (getRightSide() instanceof org.bukkit.block.Chest) {
            ((org.bukkit.block.Chest) getRightSide()).update();
        }
    }
}
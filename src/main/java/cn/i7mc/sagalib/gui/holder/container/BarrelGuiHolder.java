package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Barrel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Barrel类型的GUI容器实现
 * 用于木桶类型方块的GUI显示
 */
public class BarrelGuiHolder extends BaseGuiHolder {
    
    private final Barrel barrel;
    
    /**
     * 创建一个新的Barrel GUI容器
     * @param player 玩家，用于解析占位符
     * @param barrel 木桶方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public BarrelGuiHolder(Player player, Barrel barrel, String title, int size) {
        super(player, title, size);
        this.barrel = barrel;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param barrel 木桶方块
     * @param inventory 已有的Inventory
     */
    public BarrelGuiHolder(Player player, Barrel barrel, Inventory inventory) {
        super(player, inventory);
        this.barrel = barrel;
    }
    
    /**
     * 获取该GUI对应的木桶方块
     * @return 木桶方块
     */
    public Barrel getBarrel() {
        return barrel;
    }
    
    /**
     * 获取木桶的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Barrel"
     */
    public String getCustomName() {
        return barrel.getCustomName() != null ? barrel.getCustomName() : "Barrel";
    }
    
    /**
     * 获取木桶的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return barrel.getX();
    }
    
    /**
     * 获取木桶的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return barrel.getY();
    }
    
    /**
     * 获取木桶的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return barrel.getZ();
    }
    
    /**
     * 获取木桶所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return barrel.getWorld().getName();
    }
    
    /**
     * 判断木桶是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return barrel.isLocked();
    }
    
    /**
     * 获取木桶的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return barrel.getLock();
    }
    
    /**
     * 更新木桶方块状态
     */
    public void update() {
        super.update();
        barrel.update();
    }
} 
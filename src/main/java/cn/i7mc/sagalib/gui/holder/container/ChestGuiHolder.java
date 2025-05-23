package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Chest类型的GUI容器实现
 * 用于箱子类型方块的GUI显示
 */
public class ChestGuiHolder extends BaseGuiHolder {
    
    private final Chest chest;
    
    /**
     * 创建一个新的Chest GUI容器
     * @param player 玩家，用于解析占位符
     * @param chest 箱子方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public ChestGuiHolder(Player player, Chest chest, String title, int size) {
        super(player, title, size);
        this.chest = chest;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param chest 箱子方块
     * @param inventory 已有的Inventory
     */
    public ChestGuiHolder(Player player, Chest chest, Inventory inventory) {
        super(player, inventory);
        this.chest = chest;
    }
    
    /**
     * 获取该GUI对应的箱子方块
     * @return 箱子方块
     */
    public Chest getChest() {
        return chest;
    }
    
    /**
     * 获取箱子的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Chest"
     */
    public String getCustomName() {
        return chest.getCustomName() != null ? chest.getCustomName() : "Chest";
    }
    
    /**
     * 获取箱子的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return chest.getX();
    }
    
    /**
     * 获取箱子的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return chest.getY();
    }
    
    /**
     * 获取箱子的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return chest.getZ();
    }
    
    /**
     * 获取箱子所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return chest.getWorld().getName();
    }
    
    /**
     * 判断箱子是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return chest.isLocked();
    }
    
    /**
     * 获取箱子的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return chest.getLock();
    }
    
    /**
     * 更新箱子方块状态
     */
    public void update() {
        super.update();
        chest.update();
    }
} 
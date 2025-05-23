package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Crafter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Crafter类型的GUI容器实现
 * 用于合成器类型方块的GUI显示
 */
public class CrafterGuiHolder extends BaseGuiHolder {
    
    private final Crafter crafter;
    
    /**
     * 创建一个新的Crafter GUI容器
     * @param player 玩家，用于解析占位符
     * @param crafter 合成器方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public CrafterGuiHolder(Player player, Crafter crafter, String title, int size) {
        super(player, title, size);
        this.crafter = crafter;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param crafter 合成器方块
     * @param inventory 已有的Inventory
     */
    public CrafterGuiHolder(Player player, Crafter crafter, Inventory inventory) {
        super(player, inventory);
        this.crafter = crafter;
    }
    
    /**
     * 获取该GUI对应的合成器方块
     * @return 合成器方块
     */
    public Crafter getCrafter() {
        return crafter;
    }
    
    /**
     * 获取合成器的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Crafter"
     */
    public String getCustomName() {
        return crafter.getCustomName() != null ? crafter.getCustomName() : "Crafter";
    }
    
    /**
     * 获取合成器的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return crafter.getX();
    }
    
    /**
     * 获取合成器的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return crafter.getY();
    }
    
    /**
     * 获取合成器的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return crafter.getZ();
    }
    
    /**
     * 获取合成器所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return crafter.getWorld().getName();
    }
    
    /**
     * 判断合成器是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return crafter.isLocked();
    }
    
    /**
     * 获取合成器的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return crafter.getLock();
    }
    
    /**
     * 判断合成器是否处于触发状态
     * @return 是否触发
     */
    public boolean isTriggered() {
        return crafter.isTriggered();
    }
    
    /**
     * 更新合成器方块状态
     */
    public void update() {
        super.update();
        crafter.update();
    }
} 
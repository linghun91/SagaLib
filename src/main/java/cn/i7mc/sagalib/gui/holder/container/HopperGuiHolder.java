package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Hopper类型的GUI容器实现
 * 用于漏斗类型方块的GUI显示
 */
public class HopperGuiHolder extends BaseGuiHolder {
    
    private final Hopper hopper;
    
    /**
     * 创建一个新的Hopper GUI容器
     * @param player 玩家，用于解析占位符
     * @param hopper 漏斗方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public HopperGuiHolder(Player player, Hopper hopper, String title, int size) {
        super(player, title, size);
        this.hopper = hopper;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param hopper 漏斗方块
     * @param inventory 已有的Inventory
     */
    public HopperGuiHolder(Player player, Hopper hopper, Inventory inventory) {
        super(player, inventory);
        this.hopper = hopper;
    }
    
    /**
     * 获取该GUI对应的漏斗方块
     * @return 漏斗方块
     */
    public Hopper getHopper() {
        return hopper;
    }
    
    /**
     * 获取漏斗的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Hopper"
     */
    public String getCustomName() {
        return hopper.getCustomName() != null ? hopper.getCustomName() : "Hopper";
    }
    
    /**
     * 获取漏斗的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return hopper.getX();
    }
    
    /**
     * 获取漏斗的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return hopper.getY();
    }
    
    /**
     * 获取漏斗的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return hopper.getZ();
    }
    
    /**
     * 获取漏斗所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return hopper.getWorld().getName();
    }
    
    /**
     * 判断漏斗是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return hopper.isLocked();
    }
    
    /**
     * 获取漏斗的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return hopper.getLock();
    }
    
    /**
     * 更新漏斗方块状态
     */
    @Override
    public void update() {
        super.update();
        hopper.update();
    }
} 
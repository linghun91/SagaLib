package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Dispenser类型的GUI容器实现
 * 用于发射器类型方块的GUI显示
 */
public class DispenserGuiHolder extends BaseGuiHolder {
    
    private final Dispenser dispenser;
    
    /**
     * 创建一个新的Dispenser GUI容器
     * @param player 玩家，用于解析占位符
     * @param dispenser 发射器方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public DispenserGuiHolder(Player player, Dispenser dispenser, String title, int size) {
        super(player, title, size);
        this.dispenser = dispenser;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param dispenser 发射器方块
     * @param inventory 已有的Inventory
     */
    public DispenserGuiHolder(Player player, Dispenser dispenser, Inventory inventory) {
        super(player, inventory);
        this.dispenser = dispenser;
    }
    
    /**
     * 获取该GUI对应的发射器方块
     * @return 发射器方块
     */
    public Dispenser getDispenser() {
        return dispenser;
    }
    
    /**
     * 获取发射器的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Dispenser"
     */
    public String getCustomName() {
        return dispenser.getCustomName() != null ? dispenser.getCustomName() : "Dispenser";
    }
    
    /**
     * 获取发射器的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return dispenser.getX();
    }
    
    /**
     * 获取发射器的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return dispenser.getY();
    }
    
    /**
     * 获取发射器的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return dispenser.getZ();
    }
    
    /**
     * 获取发射器所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return dispenser.getWorld().getName();
    }
    
    /**
     * 触发发射器发射
     * @return 是否成功发射
     */
    public boolean dispense() {
        return dispenser.dispense();
    }
    
    /**
     * 判断发射器是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return dispenser.isLocked();
    }
    
    /**
     * 获取发射器的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return dispenser.getLock();
    }
    
    /**
     * 更新发射器方块状态
     */
    @Override
    public void update() {
        super.update();
        dispenser.update();
    }
} 
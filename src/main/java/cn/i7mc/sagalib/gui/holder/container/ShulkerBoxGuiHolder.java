package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.DyeColor;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * ShulkerBox类型的GUI容器实现
 * 用于潜影盒类型方块的GUI显示
 */
public class ShulkerBoxGuiHolder extends BaseGuiHolder {
    
    private final ShulkerBox shulkerBox;
    
    /**
     * 创建一个新的ShulkerBox GUI容器
     * @param player 玩家，用于解析占位符
     * @param shulkerBox 潜影盒方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public ShulkerBoxGuiHolder(Player player, ShulkerBox shulkerBox, String title, int size) {
        super(player, title, size);
        this.shulkerBox = shulkerBox;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param shulkerBox 潜影盒方块
     * @param inventory 已有的Inventory
     */
    public ShulkerBoxGuiHolder(Player player, ShulkerBox shulkerBox, Inventory inventory) {
        super(player, inventory);
        this.shulkerBox = shulkerBox;
    }
    
    /**
     * 获取该GUI对应的潜影盒方块
     * @return 潜影盒方块
     */
    public ShulkerBox getShulkerBox() {
        return shulkerBox;
    }
    
    /**
     * 获取潜影盒的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Shulker Box"
     */
    public String getCustomName() {
        return shulkerBox.getCustomName() != null ? shulkerBox.getCustomName() : "Shulker Box";
    }
    
    /**
     * 获取潜影盒的颜色
     * @return 潜影盒的颜色，如果是默认颜色则返回null
     */
    public DyeColor getColor() {
        return shulkerBox.getColor();
    }
    
    /**
     * 获取潜影盒的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return shulkerBox.getX();
    }
    
    /**
     * 获取潜影盒的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return shulkerBox.getY();
    }
    
    /**
     * 获取潜影盒的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return shulkerBox.getZ();
    }
    
    /**
     * 获取潜影盒所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return shulkerBox.getWorld().getName();
    }
    
    /**
     * 判断潜影盒是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return shulkerBox.isLocked();
    }
    
    /**
     * 获取潜影盒的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return shulkerBox.getLock();
    }
    
    /**
     * 打开潜影盒
     */
    public void open() {
        shulkerBox.open();
    }
    
    /**
     * 关闭潜影盒
     */
    public void close() {
        shulkerBox.close();
    }
    
    /**
     * 更新潜影盒方块状态
     */
    public void update() {
        super.update();
        shulkerBox.update();
    }
} 
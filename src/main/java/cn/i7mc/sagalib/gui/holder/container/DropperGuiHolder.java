package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Dropper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Dropper类型的GUI容器实现
 * 用于投掷器类型方块的GUI显示
 */
public class DropperGuiHolder extends BaseGuiHolder {
    
    private final Dropper dropper;
    
    /**
     * 创建一个新的Dropper GUI容器
     * @param player 玩家，用于解析占位符
     * @param dropper 投掷器方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public DropperGuiHolder(Player player, Dropper dropper, String title, int size) {
        super(player, title, size);
        this.dropper = dropper;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param dropper 投掷器方块
     * @param inventory 已有的Inventory
     */
    public DropperGuiHolder(Player player, Dropper dropper, Inventory inventory) {
        super(player, inventory);
        this.dropper = dropper;
    }
    
    /**
     * 获取该GUI对应的投掷器方块
     * @return 投掷器方块
     */
    public Dropper getDropper() {
        return dropper;
    }
    
    /**
     * 获取投掷器的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Dropper"
     */
    public String getCustomName() {
        return dropper.getCustomName() != null ? dropper.getCustomName() : "Dropper";
    }
    
    /**
     * 获取投掷器的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return dropper.getX();
    }
    
    /**
     * 获取投掷器的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return dropper.getY();
    }
    
    /**
     * 获取投掷器的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return dropper.getZ();
    }
    
    /**
     * 获取投掷器所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return dropper.getWorld().getName();
    }
    
    /**
     * 触发投掷器投掷
     */
    public void drop() {
        dropper.drop();
    }
    
    /**
     * 判断投掷器是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return dropper.isLocked();
    }
    
    /**
     * 获取投掷器的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return dropper.getLock();
    }
    
    /**
     * 更新投掷器方块状态
     */
    @Override
    public void update() {
        super.update();
        dropper.update();
    }
} 
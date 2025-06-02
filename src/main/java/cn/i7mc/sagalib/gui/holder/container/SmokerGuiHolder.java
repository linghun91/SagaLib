package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Smoker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Smoker类型的GUI容器实现
 * 用于烟熏炉类型方块的GUI显示
 */
public class SmokerGuiHolder extends BaseGuiHolder {
    
    private final Smoker smoker;
    
    /**
     * 创建一个新的Smoker GUI容器
     * @param player 玩家，用于解析占位符
     * @param smoker 烟熏炉方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public SmokerGuiHolder(Player player, Smoker smoker, String title, int size) {
        super(player, title, size);
        this.smoker = smoker;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param smoker 烟熏炉方块
     * @param inventory 已有的Inventory
     */
    public SmokerGuiHolder(Player player, Smoker smoker, Inventory inventory) {
        super(player, inventory);
        this.smoker = smoker;
    }
    
    /**
     * 获取该GUI对应的烟熏炉方块
     * @return 烟熏炉方块
     */
    public Smoker getSmoker() {
        return smoker;
    }
    
    /**
     * 获取烟熏炉的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Smoker"
     */
    public String getCustomName() {
        return smoker.getCustomName() != null ? smoker.getCustomName() : "Smoker";
    }
    
    /**
     * 获取烟熏炉的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return smoker.getX();
    }
    
    /**
     * 获取烟熏炉的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return smoker.getY();
    }
    
    /**
     * 获取烟熏炉的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return smoker.getZ();
    }
    
    /**
     * 获取烟熏炉所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return smoker.getWorld().getName();
    }
    
    /**
     * 获取烟熏炉的烧炼进度
     * @return 烧炼进度（0-200）
     */
    public short getCookTime() {
        return smoker.getCookTime();
    }
    
    /**
     * 获取烟熏炉的燃烧时间
     * @return 燃烧时间
     */
    public short getBurnTime() {
        return smoker.getBurnTime();
    }
    
    /**
     * 判断烟熏炉是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return smoker.isLocked();
    }
    
    /**
     * 获取烟熏炉的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return smoker.getLock();
    }
    
    /**
     * 更新烟熏炉方块状态
     */
    @Override
    public void update() {
        super.update();
        smoker.update();
    }
} 
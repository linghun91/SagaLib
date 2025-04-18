package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Furnace类型的GUI容器实现
 * 用于熔炉类型方块的GUI显示
 */
public class FurnaceGuiHolder extends BaseGuiHolder {
    
    private final Furnace furnace;
    
    /**
     * 创建一个新的Furnace GUI容器
     * @param player 玩家，用于解析占位符
     * @param furnace 熔炉方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public FurnaceGuiHolder(Player player, Furnace furnace, String title, int size) {
        super(player, title, size);
        this.furnace = furnace;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param furnace 熔炉方块
     * @param inventory 已有的Inventory
     */
    public FurnaceGuiHolder(Player player, Furnace furnace, Inventory inventory) {
        super(player, inventory);
        this.furnace = furnace;
    }
    
    /**
     * 获取该GUI对应的熔炉方块
     * @return 熔炉方块
     */
    public Furnace getFurnace() {
        return furnace;
    }
    
    /**
     * 获取熔炉的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Furnace"
     */
    public String getCustomName() {
        return furnace.getCustomName() != null ? furnace.getCustomName() : "Furnace";
    }
    
    /**
     * 获取熔炉的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return furnace.getX();
    }
    
    /**
     * 获取熔炉的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return furnace.getY();
    }
    
    /**
     * 获取熔炉的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return furnace.getZ();
    }
    
    /**
     * 获取熔炉所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return furnace.getWorld().getName();
    }
    
    /**
     * 获取熔炉的烧炼进度
     * @return 烧炼进度（0-200）
     */
    public short getCookTime() {
        return furnace.getCookTime();
    }
    
    /**
     * 获取熔炉的燃烧时间
     * @return 燃烧时间
     */
    public short getBurnTime() {
        return furnace.getBurnTime();
    }
    
    /**
     * 判断熔炉是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return furnace.isLocked();
    }
    
    /**
     * 获取熔炉的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return furnace.getLock();
    }
    
    /**
     * 更新熔炉方块状态
     */
    @Override
    public void update() {
        super.update();
        furnace.update();
    }
} 
package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * BrewingStand类型的GUI容器实现
 * 用于酿造台类型方块的GUI显示
 */
public class BrewingStandGuiHolder extends BaseGuiHolder {
    
    private final BrewingStand brewingStand;
    
    /**
     * 创建一个新的BrewingStand GUI容器
     * @param player 玩家，用于解析占位符
     * @param brewingStand 酿造台方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public BrewingStandGuiHolder(Player player, BrewingStand brewingStand, String title, int size) {
        super(player, title, size);
        this.brewingStand = brewingStand;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param brewingStand 酿造台方块
     * @param inventory 已有的Inventory
     */
    public BrewingStandGuiHolder(Player player, BrewingStand brewingStand, Inventory inventory) {
        super(player, inventory);
        this.brewingStand = brewingStand;
    }
    
    /**
     * 获取该GUI对应的酿造台方块
     * @return 酿造台方块
     */
    public BrewingStand getBrewingStand() {
        return brewingStand;
    }
    
    /**
     * 获取酿造台的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Brewing Stand"
     */
    public String getCustomName() {
        return brewingStand.getCustomName() != null ? brewingStand.getCustomName() : "Brewing Stand";
    }
    
    /**
     * 获取酿造台的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return brewingStand.getX();
    }
    
    /**
     * 获取酿造台的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return brewingStand.getY();
    }
    
    /**
     * 获取酿造台的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return brewingStand.getZ();
    }
    
    /**
     * 获取酿造台所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return brewingStand.getWorld().getName();
    }
    
    /**
     * 判断酿造台是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return brewingStand.isLocked();
    }
    
    /**
     * 获取酿造台的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return brewingStand.getLock();
    }
    
    /**
     * 获取酿造台的燃料数量
     * @return 燃料数量
     */
    public int getFuelLevel() {
        return brewingStand.getFuelLevel();
    }
    
    /**
     * 设置酿造台的燃料数量
     * @param level 燃料数量
     */
    public void setFuelLevel(int level) {
        brewingStand.setFuelLevel(level);
    }
    
    /**
     * 获取酿造台的酿造时间
     * @return 酿造时间（刻）
     */
    public int getBrewingTime() {
        return brewingStand.getBrewingTime();
    }
    
    /**
     * 设置酿造台的酿造时间
     * @param brewTime 酿造时间（刻）
     */
    public void setBrewingTime(int brewTime) {
        brewingStand.setBrewingTime(brewTime);
    }
    
    /**
     * 更新酿造台方块状态
     */
    public void update() {
        super.update();
        brewingStand.update();
    }
} 
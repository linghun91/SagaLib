package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * SkeletonHorse类型的GUI容器实现
 * 用于骷髅马实体的GUI显示
 */
public class SkeletonHorseGuiHolder extends AbstractHorseGuiHolder {
    
    private final SkeletonHorse skeletonHorse;
    
    /**
     * 创建一个新的SkeletonHorse GUI容器
     * @param player 玩家，用于解析占位符
     * @param skeletonHorse 骷髅马实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public SkeletonHorseGuiHolder(Player player, SkeletonHorse skeletonHorse, String title, int size) {
        super(player, skeletonHorse, title, size);
        this.skeletonHorse = skeletonHorse;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param skeletonHorse 骷髅马实体
     * @param inventory 已有的Inventory
     */
    public SkeletonHorseGuiHolder(Player player, SkeletonHorse skeletonHorse, Inventory inventory) {
        super(player, skeletonHorse, inventory);
        this.skeletonHorse = skeletonHorse;
    }
    
    /**
     * 获取该GUI对应的骷髅马实体
     * @return 骷髅马实体
     */
    public SkeletonHorse getSkeletonHorse() {
        return skeletonHorse;
    }
    
    /**
     * 获取骷髅马的名称
     * @return 骷髅马的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return skeletonHorse.getCustomName() != null ? skeletonHorse.getCustomName() : "Skeleton Horse";
    }
    
    /**
     * 检查骷髅马是否可以水下呼吸
     * @return 是否可以水下呼吸
     */
    public boolean canBreathUnderwater() {
        return true; // 骷髅马可以在水下呼吸
    }
    
    /**
     * 获取骷髅马的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "骷髅马：亡灵马匹，可以在水下呼吸，不能繁殖。";
    }
} 
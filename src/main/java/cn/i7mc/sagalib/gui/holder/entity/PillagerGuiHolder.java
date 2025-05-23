package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Pillager类型的GUI容器实现
 * 用于掠夺者实体的GUI显示
 */
public class PillagerGuiHolder extends BaseGuiHolder {
    
    private final Pillager pillager;
    
    /**
     * 创建一个新的Pillager GUI容器
     * @param player 玩家，用于解析占位符
     * @param pillager 掠夺者实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public PillagerGuiHolder(Player player, Pillager pillager, String title, int size) {
        super(player, title, size);
        this.pillager = pillager;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param pillager 掠夺者实体
     * @param inventory 已有的Inventory
     */
    public PillagerGuiHolder(Player player, Pillager pillager, Inventory inventory) {
        super(player, inventory);
        this.pillager = pillager;
    }
    
    /**
     * 获取该GUI对应的掠夺者实体
     * @return 掠夺者实体
     */
    public Pillager getPillager() {
        return pillager;
    }
    
    /**
     * 获取掠夺者的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Pillager"
     */
    public String getCustomName() {
        return pillager.getCustomName() != null ? pillager.getCustomName() : "Pillager";
    }
    
    /**
     * 获取掠夺者的当前健康值
     * @return 当前健康值
     */
    public double getHealth() {
        return pillager.getHealth();
    }
    
    /**
     * 获取掠夺者的最大健康值
     * @return 最大健康值
     */
    public double getMaxHealth() {
        return pillager.getMaxHealth();
    }
    
    /**
     * 检查掠夺者是否装备了弩
     * @return 是否装备了弩
     */
    public boolean hasArmedCrossbow() {
        org.bukkit.inventory.ItemStack mainHand = pillager.getEquipment().getItemInMainHand();
        return mainHand != null && mainHand.getType() == org.bukkit.Material.CROSSBOW;
    }
    
    /**
     * 检查掠夺者是否有袭击效果
     * @return 是否有袭击效果
     */
    public boolean isPatrolLeader() {
        return pillager.isPatrolLeader();
    }
    
    /**
     * 设置掠夺者是否为巡逻队长
     * @param leader 是否为队长
     */
    public void setPatrolLeader(boolean leader) {
        pillager.setPatrolLeader(leader);
    }
    
    /**
     * 检查掠夺者是否能捡拾物品
     * @return 是否能捡拾物品
     */
    public boolean canPickupItems() {
        return pillager.getCanPickupItems();
    }
    
    /**
     * 设置掠夺者是否能捡拾物品
     * @param pickup 是否能捡拾
     */
    public void setCanPickupItems(boolean pickup) {
        pillager.setCanPickupItems(pickup);
    }
    
    /**
     * 获取掠夺者的坐标
     * @return 坐标字符串（格式：x,y,z）
     */
    public String getLocation() {
        return String.format("%.2f, %.2f, %.2f", 
                pillager.getLocation().getX(),
                pillager.getLocation().getY(),
                pillager.getLocation().getZ());
    }
    
    /**
     * 获取掠夺者所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return pillager.getWorld().getName();
    }
}
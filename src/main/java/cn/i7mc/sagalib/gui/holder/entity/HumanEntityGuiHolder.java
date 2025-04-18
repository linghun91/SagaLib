package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * HumanEntity类型的GUI容器实现
 * 用于人类实体（玩家和NPC）的GUI显示
 */
public class HumanEntityGuiHolder extends BaseGuiHolder {
    
    private final HumanEntity humanEntity;
    
    /**
     * 创建一个新的HumanEntity GUI容器
     * @param player 玩家，用于解析占位符
     * @param humanEntity 人类实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public HumanEntityGuiHolder(Player player, HumanEntity humanEntity, String title, int size) {
        super(player, title, size);
        this.humanEntity = humanEntity;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param humanEntity 人类实体
     * @param inventory 已有的Inventory
     */
    public HumanEntityGuiHolder(Player player, HumanEntity humanEntity, Inventory inventory) {
        super(player, inventory);
        this.humanEntity = humanEntity;
    }
    
    /**
     * 获取该GUI对应的HumanEntity
     * @return HumanEntity
     */
    public HumanEntity getHumanEntity() {
        return humanEntity;
    }
    
    /**
     * 打开GUI，显示给目标玩家
     * @param target 目标玩家
     */
    public void openInventory(Player target) {
        target.openInventory(getInventory());
    }
    
    /**
     * 获取目标的名称
     * @return 名称
     */
    public String getName() {
        return humanEntity.getName();
    }
    
    /**
     * 获取目标的健康值
     * @return 健康值
     */
    public double getHealth() {
        return humanEntity.getHealth();
    }
    
    /**
     * 获取目标的饥饿值
     * @return 饥饿值
     */
    public int getFoodLevel() {
        return humanEntity.getFoodLevel();
    }
    
    /**
     * 检查目标是否为玩家
     * @return 是否为玩家
     */
    public boolean isPlayer() {
        return humanEntity instanceof Player;
    }
    
    /**
     * 获取目标的总在线时间（如果是玩家）
     * @return 玩家在线时间（刻），非玩家返回0
     */
    public long getPlayerTime() {
        if (isPlayer()) {
            return ((Player) humanEntity).getPlayerTime();
        }
        return 0;
    }
} 
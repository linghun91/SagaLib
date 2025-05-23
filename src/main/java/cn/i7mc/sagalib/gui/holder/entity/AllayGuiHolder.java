package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Allay类型的GUI容器实现
 * 用于悦灵实体的GUI显示
 */
public class AllayGuiHolder extends BaseGuiHolder {
    
    private final Allay allay;
    
    /**
     * 创建一个新的Allay GUI容器
     * @param player 玩家，用于解析占位符
     * @param allay 悦灵实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public AllayGuiHolder(Player player, Allay allay, String title, int size) {
        super(player, title, size);
        this.allay = allay;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param allay 悦灵实体
     * @param inventory 已有的Inventory
     */
    public AllayGuiHolder(Player player, Allay allay, Inventory inventory) {
        super(player, inventory);
        this.allay = allay;
    }
    
    /**
     * 获取该GUI对应的悦灵实体
     * @return 悦灵实体
     */
    public Allay getAllay() {
        return allay;
    }
    
    /**
     * 获取悦灵的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Allay"
     */
    public String getCustomName() {
        return allay.getCustomName() != null ? allay.getCustomName() : "Allay";
    }
    
    /**
     * 获取悦灵是否能复制
     * @return 是否能复制
     */
    public boolean canDuplicate() {
        return allay.canDuplicate();
    }
    
    /**
     * 获取悦灵的复制冷却时间
     * @return 复制冷却时间
     */
    public long getDuplicationCooldown() {
        return allay.getDuplicationCooldown();
    }
    
    /**
     * 设置悦灵的复制冷却时间
     * @param cooldown 复制冷却时间
     */
    public void setDuplicationCooldown(long cooldown) {
        allay.setDuplicationCooldown(cooldown);
    }
    
    /**
     * 获取悦灵是否在跳舞
     * @return 是否在跳舞
     */
    public boolean isDancing() {
        return allay.isDancing();
    }
    
    /**
     * 获取悦灵是否可以拾取物品
     * @return 是否可以拾取物品
     */
    public boolean canPickupItems() {
        return true; // 悦灵始终可以拾取物品
    }
    
    /**
     * 获取悦灵的库存
     * @return 悦灵的库存
     */
    public Inventory getInventory() {
        return allay.getInventory();
    }
} 
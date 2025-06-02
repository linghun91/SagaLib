package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Piglin类型的GUI容器实现
 * 用于猪灵实体的GUI显示
 */
public class PiglinGuiHolder extends BaseGuiHolder {
    
    private final Piglin piglin;
    
    /**
     * 创建一个新的Piglin GUI容器
     * @param player 玩家，用于解析占位符
     * @param piglin 猪灵实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public PiglinGuiHolder(Player player, Piglin piglin, String title, int size) {
        super(player, title, size);
        this.piglin = piglin;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param piglin 猪灵实体
     * @param inventory 已有的Inventory
     */
    public PiglinGuiHolder(Player player, Piglin piglin, Inventory inventory) {
        super(player, inventory);
        this.piglin = piglin;
    }
    
    /**
     * 获取该GUI对应的猪灵实体
     * @return 猪灵实体
     */
    public Piglin getPiglin() {
        return piglin;
    }
    
    /**
     * 获取猪灵的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Piglin"
     */
    public String getCustomName() {
        return piglin.getCustomName() != null ? piglin.getCustomName() : "Piglin";
    }
    
    /**
     * 检查猪灵是否成年
     * @return 是否成年
     */
    public boolean isAdult() {
        return piglin.isAdult();
    }
    
    /**
     * 检查猪灵是否免疫僵尸化
     * @return 是否免疫僵尸化
     */
    public boolean isImmuneToZombification() {
        return piglin.isImmuneToZombification();
    }
    
    /**
     * 设置猪灵是否免疫僵尸化
     * @param immune 是否免疫
     */
    public void setImmuneToZombification(boolean immune) {
        piglin.setImmuneToZombification(immune);
    }
    
    /**
     * 检查猪灵是否能捡拾物品
     * @return 是否能捡拾物品
     */
    public boolean canPickupItems() {
        return piglin.getCanPickupItems();
    }
    
    /**
     * 设置猪灵是否能捡拾物品
     * @param pickup 是否能捡拾
     */
    public void setCanPickupItems(boolean pickup) {
        piglin.setCanPickupItems(pickup);
    }
    
    /**
     * 判断猪灵是否是可交易的
     * @return 是否可交易
     */
    public boolean isAbleToHunt() {
        return !piglin.isBaby(); // 成年猪灵才能狩猎
    }
    
    /**
     * 获取猪灵库存
     * @return 库存
     */
    @Override
    public Inventory getInventory() {
        return piglin.getInventory();
    }
} 
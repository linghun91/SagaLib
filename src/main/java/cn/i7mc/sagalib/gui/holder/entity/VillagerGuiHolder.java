package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Villager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Villager类型的GUI容器实现
 * 用于村民实体的GUI显示
 */
public class VillagerGuiHolder extends AbstractVillagerGuiHolder {
    
    private final Villager villager;
    
    /**
     * 创建一个新的Villager GUI容器
     * @param player 玩家，用于解析占位符
     * @param villager 村民实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public VillagerGuiHolder(Player player, Villager villager, String title, int size) {
        super(player, villager, title, size);
        this.villager = villager;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param villager 村民实体
     * @param inventory 已有的Inventory
     */
    public VillagerGuiHolder(Player player, Villager villager, Inventory inventory) {
        super(player, villager, inventory);
        this.villager = villager;
    }
    
    /**
     * 获取该GUI对应的村民实体
     * @return 村民实体
     */
    public Villager getVillager() {
        return villager;
    }
    
    /**
     * 获取村民的职业
     * @return 职业名称
     */
    public String getProfession() {
        return villager.getProfession().toString();
    }
    
    /**
     * 获取村民的职业等级
     * @return 职业等级
     */
    public int getVillagerLevel() {
        return villager.getVillagerLevel();
    }
    
    /**
     * 获取村民的职业经验
     * @return 职业经验
     */
    public int getVillagerExperience() {
        return villager.getVillagerExperience();
    }
    
    /**
     * 获取村民的职业类型
     * @return 职业类型
     */
    public String getVillagerType() {
        return villager.getVillagerType().toString();
    }
    
    /**
     * 判断村民是否正在交易
     * @return 是否正在交易
     */
    @Override
    public boolean isTrading() {
        return villager.isTrading();
    }
} 
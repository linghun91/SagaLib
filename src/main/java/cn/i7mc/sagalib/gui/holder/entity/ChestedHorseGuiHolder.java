package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * ChestedHorse类型的GUI容器实现
 * 用于可装箱的马（驴、骡子、羊驼）的GUI显示
 */
public class ChestedHorseGuiHolder extends AbstractHorseGuiHolder {
    
    private final ChestedHorse chestedHorse;
    
    /**
     * 创建一个新的ChestedHorse GUI容器
     * @param player 玩家，用于解析占位符
     * @param chestedHorse 可装箱的马实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public ChestedHorseGuiHolder(Player player, ChestedHorse chestedHorse, String title, int size) {
        super(player, chestedHorse, title, size);
        this.chestedHorse = chestedHorse;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param chestedHorse 可装箱的马实体
     * @param inventory 已有的Inventory
     */
    public ChestedHorseGuiHolder(Player player, ChestedHorse chestedHorse, Inventory inventory) {
        super(player, chestedHorse, inventory);
        this.chestedHorse = chestedHorse;
    }
    
    /**
     * 获取该GUI对应的可装箱的马实体
     * @return 可装箱的马实体
     */
    public ChestedHorse getChestedHorse() {
        return chestedHorse;
    }
    
    /**
     * 判断马是否装有箱子
     * @return 是否装有箱子
     */
    public boolean isCarryingChest() {
        return chestedHorse.isCarryingChest();
    }
    
    /**
     * 设置马是否装有箱子
     * @param chest 是否装有箱子
     */
    public void setCarryingChest(boolean chest) {
        chestedHorse.setCarryingChest(chest);
    }
    
    /**
     * 获取马的箱子库存
     * @return 箱子库存
     */
    public Inventory getChestInventory() {
        // 在实际应用中，这需要根据不同的马类型做特殊处理
        return isCarryingChest() ? chestedHorse.getInventory() : null;
    }
} 
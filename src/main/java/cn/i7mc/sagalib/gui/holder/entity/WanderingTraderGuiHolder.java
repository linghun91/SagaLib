package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * WanderingTrader类型的GUI容器实现
 * 用于流浪商人实体的GUI显示
 */
public class WanderingTraderGuiHolder extends AbstractVillagerGuiHolder {
    
    private final WanderingTrader wanderingTrader;
    
    /**
     * 创建一个新的WanderingTrader GUI容器
     * @param player 玩家，用于解析占位符
     * @param wanderingTrader 流浪商人实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public WanderingTraderGuiHolder(Player player, WanderingTrader wanderingTrader, String title, int size) {
        super(player, wanderingTrader, title, size);
        this.wanderingTrader = wanderingTrader;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param wanderingTrader 流浪商人实体
     * @param inventory 已有的Inventory
     */
    public WanderingTraderGuiHolder(Player player, WanderingTrader wanderingTrader, Inventory inventory) {
        super(player, wanderingTrader, inventory);
        this.wanderingTrader = wanderingTrader;
    }
    
    /**
     * 获取该GUI对应的流浪商人实体
     * @return 流浪商人实体
     */
    public WanderingTrader getWanderingTrader() {
        return wanderingTrader;
    }
    
    /**
     * 获取流浪商人的名称
     * @return 流浪商人的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return wanderingTrader.getCustomName() != null ? wanderingTrader.getCustomName() : "Wandering Trader";
    }
    
    /**
     * 获取流浪商人离开前的剩余时间（以tick为单位）
     * @return 剩余时间
     */
    public int getDespawnDelay() {
        return wanderingTrader.getDespawnDelay();
    }
    
    /**
     * 设置流浪商人离开前的剩余时间
     * @param delay 剩余时间
     */
    public void setDespawnDelay(int delay) {
        wanderingTrader.setDespawnDelay(delay);
    }
} 
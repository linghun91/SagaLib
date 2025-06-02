package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * TraderLlama类型的GUI容器实现
 * 用于商人羊驼实体的GUI显示
 */
public class TraderLlamaGuiHolder extends LlamaGuiHolder {
    
    private final TraderLlama traderLlama;
    
    /**
     * 创建一个新的TraderLlama GUI容器
     * @param player 玩家，用于解析占位符
     * @param traderLlama 商人羊驼实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public TraderLlamaGuiHolder(Player player, TraderLlama traderLlama, String title, int size) {
        super(player, traderLlama, title, size);
        this.traderLlama = traderLlama;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param traderLlama 商人羊驼实体
     * @param inventory 已有的Inventory
     */
    public TraderLlamaGuiHolder(Player player, TraderLlama traderLlama, Inventory inventory) {
        super(player, traderLlama, inventory);
        this.traderLlama = traderLlama;
    }
    
    /**
     * 获取该GUI对应的商人羊驼实体
     * @return 商人羊驼实体
     */
    public TraderLlama getTraderLlama() {
        return traderLlama;
    }
    
    /**
     * 获取商人羊驼的名称
     * @return 商人羊驼的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return traderLlama.getCustomName() != null ? traderLlama.getCustomName() : "Trader Llama";
    }
    
    /**
     * 获取商人羊驼的类型描述
     * @return 类型描述
     */
    @Override
    public String getTypeDescription() {
        return "商人羊驼：流浪商人的羊驼，有特殊的装饰图案。";
    }
    
    /**
     * 检查是否是商人羊驼
     * @return 总是返回true
     */
    public boolean isTraderLlama() {
        return true;
    }
} 
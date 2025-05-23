package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Llama类型的GUI容器实现
 * 用于羊驼实体的GUI显示
 */
public class LlamaGuiHolder extends ChestedHorseGuiHolder {
    
    private final Llama llama;
    
    /**
     * 创建一个新的Llama GUI容器
     * @param player 玩家，用于解析占位符
     * @param llama 羊驼实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public LlamaGuiHolder(Player player, Llama llama, String title, int size) {
        super(player, llama, title, size);
        this.llama = llama;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param llama 羊驼实体
     * @param inventory 已有的Inventory
     */
    public LlamaGuiHolder(Player player, Llama llama, Inventory inventory) {
        super(player, llama, inventory);
        this.llama = llama;
    }
    
    /**
     * 获取该GUI对应的羊驼实体
     * @return 羊驼实体
     */
    public Llama getLlama() {
        return llama;
    }
    
    /**
     * 获取羊驼的名称
     * @return 羊驼的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return llama.getCustomName() != null ? llama.getCustomName() : "Llama";
    }
    
    /**
     * 获取羊驼的颜色
     * @return 羊驼的颜色
     */
    public String getColor() {
        return llama.getColor().toString();
    }
    
    /**
     * 获取羊驼的力量
     * @return 羊驼的力量
     */
    public int getStrength() {
        return llama.getStrength();
    }
    
    /**
     * 设置羊驼的力量
     * @param strength 力量值
     */
    public void setStrength(int strength) {
        llama.setStrength(strength);
    }
    
    /**
     * 获取羊驼的地毯
     * @return 羊驼的地毯
     */
    public ItemStack getDecor() {
        return llama.getInventory().getDecor();
    }
    
    /**
     * 判断羊驼是否有地毯
     * @return 是否有地毯
     */
    public boolean hasDecor() {
        return llama.getInventory().getDecor() != null;
    }
    
    /**
     * 获取羊驼的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "羊驼：可以携带箱子、装饰地毯的特殊坐骑，可以组成队伍。";
    }
    
    /**
     * 获取羊驼的队伍规模
     * @return 队伍规模
     */
    public int getCaravanSize() {
        int size = 0;
        Llama current = llama;
        while (current.getLeashHolder() instanceof Llama) {
            size++;
            current = (Llama) current.getLeashHolder();
        }
        return size;
    }
} 
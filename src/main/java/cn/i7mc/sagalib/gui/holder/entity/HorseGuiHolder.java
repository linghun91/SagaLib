package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Horse类型的GUI容器实现
 * 用于马实体的GUI显示
 */
public class HorseGuiHolder extends AbstractHorseGuiHolder {
    
    private final Horse horse;
    
    /**
     * 创建一个新的Horse GUI容器
     * @param player 玩家，用于解析占位符
     * @param horse 马实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public HorseGuiHolder(Player player, Horse horse, String title, int size) {
        super(player, horse, title, size);
        this.horse = horse;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param horse 马实体
     * @param inventory 已有的Inventory
     */
    public HorseGuiHolder(Player player, Horse horse, Inventory inventory) {
        super(player, horse, inventory);
        this.horse = horse;
    }
    
    /**
     * 获取该GUI对应的马实体
     * @return 马实体
     */
    public Horse getHorse() {
        return horse;
    }
    
    /**
     * 获取马的名称
     * @return 马的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return horse.getCustomName() != null ? horse.getCustomName() : "Horse";
    }
    
    /**
     * 获取马的颜色
     * @return 马的颜色
     */
    public String getColor() {
        return horse.getColor().toString();
    }
    
    /**
     * 获取马的样式
     * @return 马的样式
     */
    public String getHorseStyle() {
        return horse.getStyle().toString();
    }
    
    /**
     * 获取马的盔甲
     * @return 马的盔甲
     */
    public ItemStack getArmor() {
        return horse.getInventory().getArmor();
    }
    
    /**
     * 获取马的鞍
     * @return 马的鞍
     */
    public ItemStack getSaddle() {
        return horse.getInventory().getSaddle();
    }
    
    /**
     * 判断马是否有盔甲
     * @return 是否有盔甲
     */
    public boolean hasArmor() {
        return horse.getInventory().getArmor() != null;
    }
    
    /**
     * 判断马是否有鞍
     * @return 是否有鞍
     */
    public boolean hasSaddle() {
        return horse.getInventory().getSaddle() != null;
    }
    
    /**
     * 获取马的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "马：最基础的马匹类型，可以穿戴盔甲提供防护。";
    }
} 
package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Mule;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Mule类型的GUI容器实现
 * 用于骡子实体的GUI显示
 */
public class MuleGuiHolder extends ChestedHorseGuiHolder {
    
    private final Mule mule;
    
    /**
     * 创建一个新的Mule GUI容器
     * @param player 玩家，用于解析占位符
     * @param mule 骡子实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public MuleGuiHolder(Player player, Mule mule, String title, int size) {
        super(player, mule, title, size);
        this.mule = mule;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param mule 骡子实体
     * @param inventory 已有的Inventory
     */
    public MuleGuiHolder(Player player, Mule mule, Inventory inventory) {
        super(player, mule, inventory);
        this.mule = mule;
    }
    
    /**
     * 获取该GUI对应的骡子实体
     * @return 骡子实体
     */
    public Mule getMule() {
        return mule;
    }
    
    /**
     * 获取骡子的名称
     * @return 骡子的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return mule.getCustomName() != null ? mule.getCustomName() : "Mule";
    }
    
    /**
     * 获取骡子的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "骡子：马和驴的杂交后代，可以携带箱子但不能装备盔甲，比驴更快。";
    }
} 
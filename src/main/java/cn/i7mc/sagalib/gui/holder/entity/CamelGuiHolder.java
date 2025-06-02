package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Camel类型的GUI容器实现
 * 用于骆驼实体的GUI显示
 */
public class CamelGuiHolder extends AbstractHorseGuiHolder {
    
    private final Camel camel;
    
    /**
     * 创建一个新的Camel GUI容器
     * @param player 玩家，用于解析占位符
     * @param camel 骆驼实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public CamelGuiHolder(Player player, Camel camel, String title, int size) {
        super(player, camel, title, size);
        this.camel = camel;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param camel 骆驼实体
     * @param inventory 已有的Inventory
     */
    public CamelGuiHolder(Player player, Camel camel, Inventory inventory) {
        super(player, camel, inventory);
        this.camel = camel;
    }
    
    /**
     * 获取该GUI对应的骆驼实体
     * @return 骆驼实体
     */
    public Camel getCamel() {
        return camel;
    }
    
    /**
     * 获取骆驼是否正在坐下
     * @return 是否坐下
     */
    public boolean isSitting() {
        return camel.isSitting();
    }
    
    /**
     * 设置骆驼是否坐下
     * @param sitting 是否坐下
     */
    public void setSitting(boolean sitting) {
        camel.setSitting(sitting);
    }
    
    /**
     * 获取骆驼是否正在冲刺
     * @return 是否冲刺
     */
    public boolean isDashing() {
        return camel.isDashing();
    }
    
    /**
     * 设置骆驼是否正在冲刺
     * @param dashing 是否冲刺
     */
    public void setDashing(boolean dashing) {
        camel.setDashing(dashing);
    }
} 
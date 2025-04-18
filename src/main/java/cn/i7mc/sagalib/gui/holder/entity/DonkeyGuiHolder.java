package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Donkey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Donkey类型的GUI容器实现
 * 用于驴实体的GUI显示
 */
public class DonkeyGuiHolder extends ChestedHorseGuiHolder {
    
    private final Donkey donkey;
    
    /**
     * 创建一个新的Donkey GUI容器
     * @param player 玩家，用于解析占位符
     * @param donkey 驴实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public DonkeyGuiHolder(Player player, Donkey donkey, String title, int size) {
        super(player, donkey, title, size);
        this.donkey = donkey;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param donkey 驴实体
     * @param inventory 已有的Inventory
     */
    public DonkeyGuiHolder(Player player, Donkey donkey, Inventory inventory) {
        super(player, donkey, inventory);
        this.donkey = donkey;
    }
    
    /**
     * 获取该GUI对应的驴实体
     * @return 驴实体
     */
    public Donkey getDonkey() {
        return donkey;
    }
    
    /**
     * 获取驴的名称
     * @return 驴的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return donkey.getCustomName() != null ? donkey.getCustomName() : "Donkey";
    }
    
    /**
     * 获取驴的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "驴：一种可携带箱子的坐骑，但不能装备马铠。";
    }
} 
package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.JungleChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * JungleChestBoat类型的GUI容器实现
 * 用于丛林木带箱子的船的GUI显示
 */
public class JungleChestBoatGuiHolder extends BaseGuiHolder {
    
    private final JungleChestBoat jungleChestBoat;
    
    /**
     * 创建一个新的JungleChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param jungleChestBoat 丛林木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public JungleChestBoatGuiHolder(Player player, JungleChestBoat jungleChestBoat, String title, int size) {
        super(player, title, size);
        this.jungleChestBoat = jungleChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param jungleChestBoat 丛林木带箱子的船
     * @param inventory 已有的Inventory
     */
    public JungleChestBoatGuiHolder(Player player, JungleChestBoat jungleChestBoat, Inventory inventory) {
        super(player, inventory);
        this.jungleChestBoat = jungleChestBoat;
    }
    
    /**
     * 获取该GUI对应的丛林木带箱子的船
     * @return 丛林木带箱子的船
     */
    public JungleChestBoat getJungleChestBoat() {
        return jungleChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Jungle Chest Boat"
     */
    public String getCustomName() {
        return jungleChestBoat.getCustomName() != null ? jungleChestBoat.getCustomName() : "Jungle Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return jungleChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return jungleChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return jungleChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return jungleChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return jungleChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (jungleChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return jungleChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !jungleChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "JUNGLE";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
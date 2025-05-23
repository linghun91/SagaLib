package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.ChestBoat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * ChestBoat类型的GUI容器实现
 * 用于带箱子的船的GUI显示
 */
public class ChestBoatGuiHolder extends BaseGuiHolder {
    
    private final ChestBoat chestBoat;
    
    /**
     * 创建一个新的ChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param chestBoat 带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public ChestBoatGuiHolder(Player player, ChestBoat chestBoat, String title, int size) {
        super(player, title, size);
        this.chestBoat = chestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param chestBoat 带箱子的船
     * @param inventory 已有的Inventory
     */
    public ChestBoatGuiHolder(Player player, ChestBoat chestBoat, Inventory inventory) {
        super(player, inventory);
        this.chestBoat = chestBoat;
    }
    
    /**
     * 获取该GUI对应的带箱子的船
     * @return 带箱子的船
     */
    public ChestBoat getChestBoat() {
        return chestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Chest Boat"
     */
    public String getCustomName() {
        return chestBoat.getCustomName() != null ? chestBoat.getCustomName() : "Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return chestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return chestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return chestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return chestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return chestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (chestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return chestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !chestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return chestBoat.getBoatType().toString();
    }
} 
package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.BirchChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * BirchChestBoat类型的GUI容器实现
 * 用于白桦木带箱子的船的GUI显示
 */
public class BirchChestBoatGuiHolder extends BaseGuiHolder {
    
    private final BirchChestBoat birchChestBoat;
    
    /**
     * 创建一个新的BirchChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param birchChestBoat 白桦木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public BirchChestBoatGuiHolder(Player player, BirchChestBoat birchChestBoat, String title, int size) {
        super(player, title, size);
        this.birchChestBoat = birchChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param birchChestBoat 白桦木带箱子的船
     * @param inventory 已有的Inventory
     */
    public BirchChestBoatGuiHolder(Player player, BirchChestBoat birchChestBoat, Inventory inventory) {
        super(player, inventory);
        this.birchChestBoat = birchChestBoat;
    }
    
    /**
     * 获取该GUI对应的白桦木带箱子的船
     * @return 白桦木带箱子的船
     */
    public BirchChestBoat getBirchChestBoat() {
        return birchChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Birch Chest Boat"
     */
    public String getCustomName() {
        return birchChestBoat.getCustomName() != null ? birchChestBoat.getCustomName() : "Birch Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return birchChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return birchChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return birchChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return birchChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return birchChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (birchChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return birchChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !birchChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "BIRCH";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
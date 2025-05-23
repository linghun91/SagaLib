package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.MangroveChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * MangroveChestBoat类型的GUI容器实现
 * 用于红树木带箱子的船的GUI显示
 */
public class MangroveChestBoatGuiHolder extends BaseGuiHolder {
    
    private final MangroveChestBoat mangroveChestBoat;
    
    /**
     * 创建一个新的MangroveChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param mangroveChestBoat 红树木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public MangroveChestBoatGuiHolder(Player player, MangroveChestBoat mangroveChestBoat, String title, int size) {
        super(player, title, size);
        this.mangroveChestBoat = mangroveChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param mangroveChestBoat 红树木带箱子的船
     * @param inventory 已有的Inventory
     */
    public MangroveChestBoatGuiHolder(Player player, MangroveChestBoat mangroveChestBoat, Inventory inventory) {
        super(player, inventory);
        this.mangroveChestBoat = mangroveChestBoat;
    }
    
    /**
     * 获取该GUI对应的红树木带箱子的船
     * @return 红树木带箱子的船
     */
    public MangroveChestBoat getMangroveChestBoat() {
        return mangroveChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Mangrove Chest Boat"
     */
    public String getCustomName() {
        return mangroveChestBoat.getCustomName() != null ? mangroveChestBoat.getCustomName() : "Mangrove Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return mangroveChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return mangroveChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return mangroveChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return mangroveChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return mangroveChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (mangroveChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return mangroveChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !mangroveChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "MANGROVE";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
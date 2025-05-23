package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.AcaciaChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * AcaciaChestBoat类型的GUI容器实现
 * 用于金合欢木带箱子的船的GUI显示
 */
public class AcaciaChestBoatGuiHolder extends BaseGuiHolder {
    
    private final AcaciaChestBoat acaciaChestBoat;
    
    /**
     * 创建一个新的AcaciaChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param acaciaChestBoat 金合欢木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public AcaciaChestBoatGuiHolder(Player player, AcaciaChestBoat acaciaChestBoat, String title, int size) {
        super(player, title, size);
        this.acaciaChestBoat = acaciaChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param acaciaChestBoat 金合欢木带箱子的船
     * @param inventory 已有的Inventory
     */
    public AcaciaChestBoatGuiHolder(Player player, AcaciaChestBoat acaciaChestBoat, Inventory inventory) {
        super(player, inventory);
        this.acaciaChestBoat = acaciaChestBoat;
    }
    
    /**
     * 获取该GUI对应的金合欢木带箱子的船
     * @return 金合欢木带箱子的船
     */
    public AcaciaChestBoat getAcaciaChestBoat() {
        return acaciaChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Acacia Chest Boat"
     */
    public String getCustomName() {
        return acaciaChestBoat.getCustomName() != null ? acaciaChestBoat.getCustomName() : "Acacia Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return acaciaChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return acaciaChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return acaciaChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return acaciaChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return acaciaChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (acaciaChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return acaciaChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !acaciaChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "ACACIA";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
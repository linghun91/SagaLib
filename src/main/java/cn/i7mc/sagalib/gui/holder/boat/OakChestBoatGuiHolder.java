package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.OakChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * OakChestBoat类型的GUI容器实现
 * 用于橡木带箱子的船的GUI显示
 */
public class OakChestBoatGuiHolder extends BaseGuiHolder {
    
    private final OakChestBoat oakChestBoat;
    
    /**
     * 创建一个新的OakChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param oakChestBoat 橡木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public OakChestBoatGuiHolder(Player player, OakChestBoat oakChestBoat, String title, int size) {
        super(player, title, size);
        this.oakChestBoat = oakChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param oakChestBoat 橡木带箱子的船
     * @param inventory 已有的Inventory
     */
    public OakChestBoatGuiHolder(Player player, OakChestBoat oakChestBoat, Inventory inventory) {
        super(player, inventory);
        this.oakChestBoat = oakChestBoat;
    }
    
    /**
     * 获取该GUI对应的橡木带箱子的船
     * @return 橡木带箱子的船
     */
    public OakChestBoat getOakChestBoat() {
        return oakChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Oak Chest Boat"
     */
    public String getCustomName() {
        return oakChestBoat.getCustomName() != null ? oakChestBoat.getCustomName() : "Oak Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return oakChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return oakChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return oakChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return oakChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return oakChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (oakChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return oakChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !oakChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "OAK";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
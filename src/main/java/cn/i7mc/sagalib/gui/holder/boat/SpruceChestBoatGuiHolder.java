package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.SpruceChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * SpruceChestBoat类型的GUI容器实现
 * 用于云杉木带箱子的船的GUI显示
 */
public class SpruceChestBoatGuiHolder extends BaseGuiHolder {
    
    private final SpruceChestBoat spruceChestBoat;
    
    /**
     * 创建一个新的SpruceChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param spruceChestBoat 云杉木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public SpruceChestBoatGuiHolder(Player player, SpruceChestBoat spruceChestBoat, String title, int size) {
        super(player, title, size);
        this.spruceChestBoat = spruceChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param spruceChestBoat 云杉木带箱子的船
     * @param inventory 已有的Inventory
     */
    public SpruceChestBoatGuiHolder(Player player, SpruceChestBoat spruceChestBoat, Inventory inventory) {
        super(player, inventory);
        this.spruceChestBoat = spruceChestBoat;
    }
    
    /**
     * 获取该GUI对应的云杉木带箱子的船
     * @return 云杉木带箱子的船
     */
    public SpruceChestBoat getSpruceChestBoat() {
        return spruceChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Spruce Chest Boat"
     */
    public String getCustomName() {
        return spruceChestBoat.getCustomName() != null ? spruceChestBoat.getCustomName() : "Spruce Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return spruceChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return spruceChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return spruceChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return spruceChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return spruceChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (spruceChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return spruceChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !spruceChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "SPRUCE";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
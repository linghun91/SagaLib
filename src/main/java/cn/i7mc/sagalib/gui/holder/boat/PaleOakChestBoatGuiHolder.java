package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.PaleOakChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * PaleOakChestBoat类型的GUI容器实现
 * 用于苍白橡木带箱子的船的GUI显示
 */
public class PaleOakChestBoatGuiHolder extends BaseGuiHolder {
    
    private final PaleOakChestBoat paleOakChestBoat;
    
    /**
     * 创建一个新的PaleOakChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param paleOakChestBoat 苍白橡木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public PaleOakChestBoatGuiHolder(Player player, PaleOakChestBoat paleOakChestBoat, String title, int size) {
        super(player, title, size);
        this.paleOakChestBoat = paleOakChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param paleOakChestBoat 苍白橡木带箱子的船
     * @param inventory 已有的Inventory
     */
    public PaleOakChestBoatGuiHolder(Player player, PaleOakChestBoat paleOakChestBoat, Inventory inventory) {
        super(player, inventory);
        this.paleOakChestBoat = paleOakChestBoat;
    }
    
    /**
     * 获取该GUI对应的苍白橡木带箱子的船
     * @return 苍白橡木带箱子的船
     */
    public PaleOakChestBoat getPaleOakChestBoat() {
        return paleOakChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Pale Oak Chest Boat"
     */
    public String getCustomName() {
        return paleOakChestBoat.getCustomName() != null ? paleOakChestBoat.getCustomName() : "Pale Oak Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return paleOakChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return paleOakChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return paleOakChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return paleOakChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return paleOakChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (paleOakChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return paleOakChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !paleOakChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "PALE_OAK";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
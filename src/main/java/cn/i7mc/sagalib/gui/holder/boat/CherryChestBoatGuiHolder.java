package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.CherryChestBoat;
import org.bukkit.inventory.Inventory;

/**
 * CherryChestBoat类型的GUI容器实现
 * 用于樱花木带箱子的船的GUI显示
 */
public class CherryChestBoatGuiHolder extends BaseGuiHolder {
    
    private final CherryChestBoat cherryChestBoat;
    
    /**
     * 创建一个新的CherryChestBoat GUI容器
     * @param player 玩家，用于解析占位符
     * @param cherryChestBoat 樱花木带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public CherryChestBoatGuiHolder(Player player, CherryChestBoat cherryChestBoat, String title, int size) {
        super(player, title, size);
        this.cherryChestBoat = cherryChestBoat;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param cherryChestBoat 樱花木带箱子的船
     * @param inventory 已有的Inventory
     */
    public CherryChestBoatGuiHolder(Player player, CherryChestBoat cherryChestBoat, Inventory inventory) {
        super(player, inventory);
        this.cherryChestBoat = cherryChestBoat;
    }
    
    /**
     * 获取该GUI对应的樱花木带箱子的船
     * @return 樱花木带箱子的船
     */
    public CherryChestBoat getCherryChestBoat() {
        return cherryChestBoat;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Cherry Chest Boat"
     */
    public String getCustomName() {
        return cherryChestBoat.getCustomName() != null ? cherryChestBoat.getCustomName() : "Cherry Chest Boat";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return cherryChestBoat.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return cherryChestBoat.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return cherryChestBoat.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return cherryChestBoat.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return cherryChestBoat.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (cherryChestBoat.getPassengers().isEmpty()) {
            return "None";
        }
        return cherryChestBoat.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !cherryChestBoat.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "CHERRY";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
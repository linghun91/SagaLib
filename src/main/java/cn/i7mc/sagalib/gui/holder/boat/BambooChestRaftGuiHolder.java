package cn.i7mc.sagalib.gui.holder.boat;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.boat.BambooChestRaft;
import org.bukkit.inventory.Inventory;

/**
 * BambooChestRaft类型的GUI容器实现
 * 用于竹筏带箱子的船的GUI显示
 */
public class BambooChestRaftGuiHolder extends BaseGuiHolder {
    
    private final BambooChestRaft bambooChestRaft;
    
    /**
     * 创建一个新的BambooChestRaft GUI容器
     * @param player 玩家，用于解析占位符
     * @param bambooChestRaft 竹筏带箱子的船
     * @param title GUI标题
     * @param size GUI大小
     */
    public BambooChestRaftGuiHolder(Player player, BambooChestRaft bambooChestRaft, String title, int size) {
        super(player, title, size);
        this.bambooChestRaft = bambooChestRaft;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param bambooChestRaft 竹筏带箱子的船
     * @param inventory 已有的Inventory
     */
    public BambooChestRaftGuiHolder(Player player, BambooChestRaft bambooChestRaft, Inventory inventory) {
        super(player, inventory);
        this.bambooChestRaft = bambooChestRaft;
    }
    
    /**
     * 获取该GUI对应的竹筏带箱子的船
     * @return 竹筏带箱子的船
     */
    public BambooChestRaft getBambooChestRaft() {
        return bambooChestRaft;
    }
    
    /**
     * 获取船的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Bamboo Chest Raft"
     */
    public String getCustomName() {
        return bambooChestRaft.getCustomName() != null ? bambooChestRaft.getCustomName() : "Bamboo Chest Raft";
    }
    
    /**
     * 获取船的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return bambooChestRaft.getUniqueId().toString();
    }
    
    /**
     * 获取船的X坐标
     * @return X坐标
     */
    public double getX() {
        return bambooChestRaft.getLocation().getX();
    }
    
    /**
     * 获取船的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return bambooChestRaft.getLocation().getY();
    }
    
    /**
     * 获取船的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return bambooChestRaft.getLocation().getZ();
    }
    
    /**
     * 获取船所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return bambooChestRaft.getWorld().getName();
    }
    
    /**
     * 获取船的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (bambooChestRaft.getPassengers().isEmpty()) {
            return "None";
        }
        return bambooChestRaft.getPassengers().get(0).getName();
    }
    
    /**
     * 判断船是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !bambooChestRaft.getPassengers().isEmpty();
    }
    
    /**
     * 获取船的木材类型
     * @return 木材类型名称
     */
    public String getBoatType() {
        return "BAMBOO";
    }
    
    /**
     * 更新船实体状态
     */
    public void update() {
        super.update();
    }
} 
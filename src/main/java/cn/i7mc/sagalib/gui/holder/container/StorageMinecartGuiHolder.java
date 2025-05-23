package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;

/**
 * StorageMinecart类型的GUI容器实现
 * 用于运输矿车实体的GUI显示
 */
public class StorageMinecartGuiHolder extends BaseGuiHolder {
    
    private final StorageMinecart storageMinecart;
    
    /**
     * 创建一个新的StorageMinecart GUI容器
     * @param player 玩家，用于解析占位符
     * @param storageMinecart 运输矿车实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public StorageMinecartGuiHolder(Player player, StorageMinecart storageMinecart, String title, int size) {
        super(player, title, size);
        this.storageMinecart = storageMinecart;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param storageMinecart 运输矿车实体
     * @param inventory 已有的Inventory
     */
    public StorageMinecartGuiHolder(Player player, StorageMinecart storageMinecart, Inventory inventory) {
        super(player, inventory);
        this.storageMinecart = storageMinecart;
    }
    
    /**
     * 获取该GUI对应的运输矿车实体
     * @return 运输矿车实体
     */
    public StorageMinecart getStorageMinecart() {
        return storageMinecart;
    }
    
    /**
     * 获取运输矿车的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Storage Minecart"
     */
    public String getCustomName() {
        return storageMinecart.getCustomName() != null ? storageMinecart.getCustomName() : "Storage Minecart";
    }
    
    /**
     * 获取运输矿车的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return storageMinecart.getUniqueId().toString();
    }
    
    /**
     * 获取运输矿车的X坐标
     * @return X坐标
     */
    public double getX() {
        return storageMinecart.getLocation().getX();
    }
    
    /**
     * 获取运输矿车的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return storageMinecart.getLocation().getY();
    }
    
    /**
     * 获取运输矿车的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return storageMinecart.getLocation().getZ();
    }
    
    /**
     * 获取运输矿车所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return storageMinecart.getWorld().getName();
    }
    
    /**
     * 获取运输矿车的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (storageMinecart.getPassengers().isEmpty()) {
            return "None";
        }
        return storageMinecart.getPassengers().get(0).getName();
    }
    
    /**
     * 判断运输矿车是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !storageMinecart.getPassengers().isEmpty();
    }
    
    /**
     * 更新运输矿车实体状态
     */
    public void update() {
        super.update();
    }
} 
package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.inventory.Inventory;

/**
 * HopperMinecart类型的GUI容器实现
 * 用于漏斗矿车实体的GUI显示
 */
public class HopperMinecartGuiHolder extends BaseGuiHolder {
    
    private final HopperMinecart hopperMinecart;
    
    /**
     * 创建一个新的HopperMinecart GUI容器
     * @param player 玩家，用于解析占位符
     * @param hopperMinecart 漏斗矿车实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public HopperMinecartGuiHolder(Player player, HopperMinecart hopperMinecart, String title, int size) {
        super(player, title, size);
        this.hopperMinecart = hopperMinecart;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param hopperMinecart 漏斗矿车实体
     * @param inventory 已有的Inventory
     */
    public HopperMinecartGuiHolder(Player player, HopperMinecart hopperMinecart, Inventory inventory) {
        super(player, inventory);
        this.hopperMinecart = hopperMinecart;
    }
    
    /**
     * 获取该GUI对应的漏斗矿车实体
     * @return 漏斗矿车实体
     */
    public HopperMinecart getHopperMinecart() {
        return hopperMinecart;
    }
    
    /**
     * 获取漏斗矿车的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Hopper Minecart"
     */
    public String getCustomName() {
        return hopperMinecart.getCustomName() != null ? hopperMinecart.getCustomName() : "Hopper Minecart";
    }
    
    /**
     * 获取漏斗矿车的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return hopperMinecart.getUniqueId().toString();
    }
    
    /**
     * 获取漏斗矿车的X坐标
     * @return X坐标
     */
    public double getX() {
        return hopperMinecart.getLocation().getX();
    }
    
    /**
     * 获取漏斗矿车的Y坐标
     * @return Y坐标
     */
    public double getY() {
        return hopperMinecart.getLocation().getY();
    }
    
    /**
     * 获取漏斗矿车的Z坐标
     * @return Z坐标
     */
    public double getZ() {
        return hopperMinecart.getLocation().getZ();
    }
    
    /**
     * 获取漏斗矿车所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return hopperMinecart.getWorld().getName();
    }
    
    /**
     * 获取漏斗矿车的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (hopperMinecart.getPassengers().isEmpty()) {
            return "None";
        }
        return hopperMinecart.getPassengers().get(0).getName();
    }
    
    /**
     * 判断漏斗矿车是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !hopperMinecart.getPassengers().isEmpty();
    }
    
    /**
     * 判断漏斗矿车是否在工作
     * @return 是否在工作
     */
    public boolean isEnabled() {
        return hopperMinecart.isEnabled();
    }
    
    /**
     * 更新漏斗矿车实体状态
     */
    public void update() {
        super.update();
    }
} 
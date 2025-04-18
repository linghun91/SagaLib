package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * AbstractHorse类型的GUI容器实现
 * 用于抽象马类（马、驴、骡子等）的GUI显示
 */
public class AbstractHorseGuiHolder extends BaseGuiHolder {
    
    private final AbstractHorse abstractHorse;
    
    /**
     * 创建一个新的AbstractHorse GUI容器
     * @param player 玩家，用于解析占位符
     * @param abstractHorse 抽象马实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public AbstractHorseGuiHolder(Player player, AbstractHorse abstractHorse, String title, int size) {
        super(player, title, size);
        this.abstractHorse = abstractHorse;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param abstractHorse 抽象马实体
     * @param inventory 已有的Inventory
     */
    public AbstractHorseGuiHolder(Player player, AbstractHorse abstractHorse, Inventory inventory) {
        super(player, inventory);
        this.abstractHorse = abstractHorse;
    }
    
    /**
     * 获取该GUI对应的抽象马实体
     * @return 抽象马实体
     */
    public AbstractHorse getAbstractHorse() {
        return abstractHorse;
    }
    
    /**
     * 获取马的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回实体类型名称
     */
    public String getCustomName() {
        return abstractHorse.getCustomName() != null ? 
               abstractHorse.getCustomName() : 
               abstractHorse.getType().toString();
    }
    
    /**
     * 获取马的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return abstractHorse.getUniqueId().toString();
    }
    
    /**
     * 获取马的当前健康值
     * @return 当前健康值
     */
    public double getHealth() {
        return abstractHorse.getHealth();
    }
    
    /**
     * 获取马的最大健康值
     * @return 最大健康值
     */
    public double getMaxHealth() {
        return abstractHorse.getMaxHealth();
    }
    
    /**
     * 获取马的坐标
     * @return 坐标字符串（格式：x,y,z）
     */
    public String getLocation() {
        return String.format("%.2f, %.2f, %.2f", 
                abstractHorse.getLocation().getX(),
                abstractHorse.getLocation().getY(),
                abstractHorse.getLocation().getZ());
    }
    
    /**
     * 获取马所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return abstractHorse.getWorld().getName();
    }
    
    /**
     * 获取马的年龄
     * @return 年龄值
     */
    public int getAge() {
        return abstractHorse.getAge();
    }
    
    /**
     * 判断马是否被驯服
     * @return 是否被驯服
     */
    public boolean isTamed() {
        return abstractHorse.isTamed();
    }
    
    /**
     * 获取马的主人（如果有）
     * @return 主人UUID字符串，如果没有主人则返回"None"
     */
    public String getOwner() {
        return abstractHorse.getOwner() != null ? 
               abstractHorse.getOwner().getName() : "None";
    }
    
    /**
     * 获取马的跳跃强度
     * @return 跳跃强度
     */
    public double getJumpStrength() {
        return abstractHorse.getJumpStrength();
    }
    
    /**
     * 获取马的速度属性
     * @return 速度属性值
     */
    public double getSpeed() {
        if (abstractHorse.getAttribute(Attribute.valueOf("GENERIC_MOVEMENT_SPEED")) != null) {
            return abstractHorse.getAttribute(Attribute.valueOf("GENERIC_MOVEMENT_SPEED")).getValue();
        }
        return 0;
    }
    
    /**
     * 判断马是否有乘客
     * @return 是否有乘客
     */
    public boolean hasPassenger() {
        return !abstractHorse.getPassengers().isEmpty();
    }
    
    /**
     * 获取马的乘客（如果有）
     * @return 乘客名称，如果没有乘客则返回"None"
     */
    public String getPassenger() {
        if (abstractHorse.getPassengers().isEmpty()) {
            return "None";
        }
        return abstractHorse.getPassengers().get(0).getName();
    }
} 
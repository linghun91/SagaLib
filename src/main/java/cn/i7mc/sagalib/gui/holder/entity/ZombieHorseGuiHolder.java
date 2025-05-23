package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * ZombieHorse类型的GUI容器实现
 * 用于僵尸马实体的GUI显示
 */
public class ZombieHorseGuiHolder extends AbstractHorseGuiHolder {
    
    private final ZombieHorse zombieHorse;
    
    /**
     * 创建一个新的ZombieHorse GUI容器
     * @param player 玩家，用于解析占位符
     * @param zombieHorse 僵尸马实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public ZombieHorseGuiHolder(Player player, ZombieHorse zombieHorse, String title, int size) {
        super(player, zombieHorse, title, size);
        this.zombieHorse = zombieHorse;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param zombieHorse 僵尸马实体
     * @param inventory 已有的Inventory
     */
    public ZombieHorseGuiHolder(Player player, ZombieHorse zombieHorse, Inventory inventory) {
        super(player, zombieHorse, inventory);
        this.zombieHorse = zombieHorse;
    }
    
    /**
     * 获取该GUI对应的僵尸马实体
     * @return 僵尸马实体
     */
    public ZombieHorse getZombieHorse() {
        return zombieHorse;
    }
    
    /**
     * 获取僵尸马的名称
     * @return 僵尸马的名称或默认名称
     */
    @Override
    public String getCustomName() {
        return zombieHorse.getCustomName() != null ? zombieHorse.getCustomName() : "Zombie Horse";
    }
    
    /**
     * 检查僵尸马是否可以水下呼吸
     * @return 是否可以水下呼吸
     */
    public boolean canBreathUnderwater() {
        return true; // 僵尸马可以在水下呼吸
    }
    
    /**
     * 获取僵尸马的类型描述
     * @return 类型描述
     */
    public String getTypeDescription() {
        return "僵尸马：亡灵马匹，可以在水下呼吸，不能繁殖。";
    }
} 
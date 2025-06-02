package cn.i7mc.sagalib.gui.holder.entity;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.List;

/**
 * AbstractVillager类型的GUI容器实现
 * 用于抽象村民实体（普通村民和流浪商人）的GUI显示
 */
public class AbstractVillagerGuiHolder extends BaseGuiHolder {
    
    private final AbstractVillager abstractVillager;
    
    /**
     * 创建一个新的AbstractVillager GUI容器
     * @param player 玩家，用于解析占位符
     * @param abstractVillager 抽象村民实体
     * @param title GUI标题
     * @param size GUI大小
     */
    public AbstractVillagerGuiHolder(Player player, AbstractVillager abstractVillager, String title, int size) {
        super(player, title, size);
        this.abstractVillager = abstractVillager;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param abstractVillager 抽象村民实体
     * @param inventory 已有的Inventory
     */
    public AbstractVillagerGuiHolder(Player player, AbstractVillager abstractVillager, Inventory inventory) {
        super(player, inventory);
        this.abstractVillager = abstractVillager;
    }
    
    /**
     * 获取该GUI对应的抽象村民实体
     * @return 抽象村民实体
     */
    public AbstractVillager getAbstractVillager() {
        return abstractVillager;
    }
    
    /**
     * 获取村民的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回实体类型名称
     */
    public String getCustomName() {
        return abstractVillager.getCustomName() != null ? 
               abstractVillager.getCustomName() : 
               abstractVillager.getType().toString();
    }
    
    /**
     * 获取村民的UUID
     * @return UUID字符串
     */
    public String getUniqueId() {
        return abstractVillager.getUniqueId().toString();
    }
    
    /**
     * 获取村民的当前健康值
     * @return 当前健康值
     */
    public double getHealth() {
        return abstractVillager.getHealth();
    }
    
    /**
     * 获取村民的最大健康值
     * @return 最大健康值
     */
    public double getMaxHealth() {
        return abstractVillager.getMaxHealth();
    }
    
    /**
     * 获取村民的交易列表
     * @return 交易列表
     */
    public List<MerchantRecipe> getRecipes() {
        return abstractVillager.getRecipes();
    }
    
    /**
     * 获取村民的交易数量
     * @return 交易数量
     */
    public int getRecipeCount() {
        return abstractVillager.getRecipeCount();
    }
    
    /**
     * 判断村民是否愿意交易
     * @return 是否愿意交易
     */
    public boolean isTrading() {
        return !abstractVillager.getRecipes().isEmpty();
    }
    
    /**
     * 获取村民的坐标
     * @return 坐标字符串（格式：x,y,z）
     */
    public String getLocation() {
        return String.format("%.2f, %.2f, %.2f", 
                abstractVillager.getLocation().getX(),
                abstractVillager.getLocation().getY(),
                abstractVillager.getLocation().getZ());
    }
    
    /**
     * 获取村民所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return abstractVillager.getWorld().getName();
    }
}
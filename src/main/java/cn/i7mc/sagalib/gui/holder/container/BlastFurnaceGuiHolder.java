package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.BlastFurnace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * BlastFurnace类型的GUI容器实现
 * 用于高炉类型方块的GUI显示
 */
public class BlastFurnaceGuiHolder extends BaseGuiHolder {
    
    private final BlastFurnace blastFurnace;
    
    /**
     * 创建一个新的BlastFurnace GUI容器
     * @param player 玩家，用于解析占位符
     * @param blastFurnace 高炉方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public BlastFurnaceGuiHolder(Player player, BlastFurnace blastFurnace, String title, int size) {
        super(player, title, size);
        this.blastFurnace = blastFurnace;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param blastFurnace 高炉方块
     * @param inventory 已有的Inventory
     */
    public BlastFurnaceGuiHolder(Player player, BlastFurnace blastFurnace, Inventory inventory) {
        super(player, inventory);
        this.blastFurnace = blastFurnace;
    }
    
    /**
     * 获取该GUI对应的高炉方块
     * @return 高炉方块
     */
    public BlastFurnace getBlastFurnace() {
        return blastFurnace;
    }
    
    /**
     * 获取高炉的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Blast Furnace"
     */
    public String getCustomName() {
        return blastFurnace.getCustomName() != null ? blastFurnace.getCustomName() : "Blast Furnace";
    }
    
    /**
     * 获取高炉的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return blastFurnace.getX();
    }
    
    /**
     * 获取高炉的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return blastFurnace.getY();
    }
    
    /**
     * 获取高炉的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return blastFurnace.getZ();
    }
    
    /**
     * 获取高炉所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return blastFurnace.getWorld().getName();
    }
    
    /**
     * 判断高炉是否被锁定
     * @return 是否被锁定
     */
    public boolean isLocked() {
        return blastFurnace.isLocked();
    }
    
    /**
     * 获取高炉的锁定密码（如果有）
     * @return 锁定密码，如果没有则返回null
     */
    public String getLock() {
        return blastFurnace.getLock();
    }
    
    /**
     * 获取高炉的燃烧时间
     * @return 燃烧时间（刻）
     */
    public int getBurnTime() {
        return blastFurnace.getBurnTime();
    }
    
    /**
     * 设置高炉的燃烧时间
     * @param burnTime 燃烧时间（刻）
     */
    public void setBurnTime(short burnTime) {
        blastFurnace.setBurnTime(burnTime);
    }
    
    /**
     * 获取高炉的烹饪时间
     * @return 烹饪时间（刻）
     */
    public int getCookTime() {
        return blastFurnace.getCookTime();
    }
    
    /**
     * 设置高炉的烹饪时间
     * @param cookTime 烹饪时间（刻）
     */
    public void setCookTime(short cookTime) {
        blastFurnace.setCookTime(cookTime);
    }
    
    /**
     * 获取高炉的总烹饪时间
     * @return 总烹饪时间（刻）
     */
    public int getCookTimeTotal() {
        return blastFurnace.getCookTimeTotal();
    }
    
    /**
     * 设置高炉的总烹饪时间
     * @param cookTimeTotal 总烹饪时间（刻）
     */
    public void setCookTimeTotal(short cookTimeTotal) {
        blastFurnace.setCookTimeTotal(cookTimeTotal);
    }
    
    /**
     * 更新高炉方块状态
     */
    public void update() {
        super.update();
        blastFurnace.update();
    }
} 
package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Lectern类型的GUI容器实现
 * 用于讲台类型方块的GUI显示
 */
public class LecternGuiHolder extends BaseGuiHolder {
    
    private final Lectern lectern;
    
    /**
     * 创建一个新的Lectern GUI容器
     * @param player 玩家，用于解析占位符
     * @param lectern 讲台方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public LecternGuiHolder(Player player, Lectern lectern, String title, int size) {
        super(player, title, size);
        this.lectern = lectern;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param lectern 讲台方块
     * @param inventory 已有的Inventory
     */
    public LecternGuiHolder(Player player, Lectern lectern, Inventory inventory) {
        super(player, inventory);
        this.lectern = lectern;
    }
    
    /**
     * 获取该GUI对应的讲台方块
     * @return 讲台方块
     */
    public Lectern getLectern() {
        return lectern;
    }
    
    /**
     * 获取讲台的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return lectern.getX();
    }
    
    /**
     * 获取讲台的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return lectern.getY();
    }
    
    /**
     * 获取讲台的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return lectern.getZ();
    }
    
    /**
     * 获取讲台所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return lectern.getWorld().getName();
    }
    
    /**
     * 获取讲台中的书本
     * @return 书本物品
     */
    public ItemStack getBook() {
        return lectern.getInventory().getItem(0);
    }
    
    /**
     * 设置讲台中的书本
     * @param book 书本物品
     */
    public void setBook(ItemStack book) {
        lectern.getInventory().setItem(0, book);
    }
    
    /**
     * 获取当前阅读的页码
     * @return 当前页码
     */
    public int getPage() {
        return lectern.getPage();
    }
    
    /**
     * 设置当前阅读的页码
     * @param page 页码
     */
    public void setPage(int page) {
        lectern.setPage(page);
    }
    
    /**
     * 更新讲台方块状态
     */
    @Override
    public void update() {
        super.update();
        lectern.update();
    }
} 
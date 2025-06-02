package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * ChiseledBookshelf类型的GUI容器实现
 * 用于錾制书架类型方块的GUI显示
 */
public class ChiseledBookshelfGuiHolder extends BaseGuiHolder {
    
    private final ChiseledBookshelf bookshelf;
    
    /**
     * 创建一个新的ChiseledBookshelf GUI容器
     * @param player 玩家，用于解析占位符
     * @param bookshelf 錾制书架方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public ChiseledBookshelfGuiHolder(Player player, ChiseledBookshelf bookshelf, String title, int size) {
        super(player, title, size);
        this.bookshelf = bookshelf;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param bookshelf 錾制书架方块
     * @param inventory 已有的Inventory
     */
    public ChiseledBookshelfGuiHolder(Player player, ChiseledBookshelf bookshelf, Inventory inventory) {
        super(player, inventory);
        this.bookshelf = bookshelf;
    }
    
    /**
     * 获取该GUI对应的錾制书架方块
     * @return 錾制书架方块
     */
    public ChiseledBookshelf getBookshelf() {
        return bookshelf;
    }
    
    /**
     * 获取錾制书架的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return bookshelf.getX();
    }
    
    /**
     * 获取錾制书架的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return bookshelf.getY();
    }
    
    /**
     * 获取錾制书架的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return bookshelf.getZ();
    }
    
    /**
     * 获取錾制书架所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return bookshelf.getWorld().getName();
    }
    
    /**
     * 获取指定槽位的物品
     * @param slot 槽位
     * @return 物品
     */
    public ItemStack getItem(int slot) {
        return bookshelf.getInventory().getItem(slot);
    }
    
    /**
     * 更新錾制书架方块状态
     */
    @Override
    public void update() {
        super.update();
        bookshelf.update();
    }
} 
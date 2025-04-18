package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Jukebox类型的GUI容器实现
 * 用于唱片机类型方块的GUI显示
 */
public class JukeboxGuiHolder extends BaseGuiHolder {
    
    private final Jukebox jukebox;
    
    /**
     * 创建一个新的Jukebox GUI容器
     * @param player 玩家，用于解析占位符
     * @param jukebox 唱片机方块
     * @param title GUI标题
     * @param size GUI大小
     */
    public JukeboxGuiHolder(Player player, Jukebox jukebox, String title, int size) {
        super(player, title, size);
        this.jukebox = jukebox;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param jukebox 唱片机方块
     * @param inventory 已有的Inventory
     */
    public JukeboxGuiHolder(Player player, Jukebox jukebox, Inventory inventory) {
        super(player, inventory);
        this.jukebox = jukebox;
    }
    
    /**
     * 获取该GUI对应的唱片机方块
     * @return 唱片机方块
     */
    public Jukebox getJukebox() {
        return jukebox;
    }
    
    /**
     * 获取唱片机的自定义名称（如果有）
     * @return 自定义名称，如果没有则返回"Jukebox"
     */
    public String getCustomName() {
        return "Jukebox";
    }
    
    /**
     * 获取唱片机的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return jukebox.getX();
    }
    
    /**
     * 获取唱片机的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return jukebox.getY();
    }
    
    /**
     * 获取唱片机的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return jukebox.getZ();
    }
    
    /**
     * 获取唱片机所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return jukebox.getWorld().getName();
    }
    
    /**
     * 判断唱片机是否正在播放
     * @return 是否正在播放
     */
    public boolean isPlaying() {
        return jukebox.isPlaying();
    }
    
    /**
     * 获取当前播放的唱片
     * @return 唱片物品，如果没有则返回null
     */
    public ItemStack getPlayingRecord() {
        return jukebox.getRecord();
    }
    
    /**
     * 判断唱片机是否有唱片
     * @return 是否有唱片
     */
    public boolean hasRecord() {
        return jukebox.getRecord() != null;
    }
    
    /**
     * 获取当前播放唱片的名称
     * @return 唱片名称，如果没有则返回"None"
     */
    public String getRecordName() {
        if (!hasRecord()) {
            return "None";
        }
        ItemStack record = jukebox.getRecord();
        return record.hasItemMeta() && record.getItemMeta().hasDisplayName() ? 
               record.getItemMeta().getDisplayName() : record.getType().toString();
    }
    
    /**
     * 更新唱片机方块状态
     */
    public void update() {
        super.update();
        jukebox.update();
    }
} 
package cn.i7mc.sagalib.gui.holder.container;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;

/**
 * BlockInventoryHolder类型的GUI容器实现
 * 用于方块物品栏持有者的GUI显示
 */
public class BlockInventoryHolderGuiHolder extends BaseGuiHolder {
    
    private final BlockInventoryHolder blockInventoryHolder;
    
    /**
     * 创建一个新的BlockInventoryHolder GUI容器
     * @param player 玩家，用于解析占位符
     * @param blockInventoryHolder 方块物品栏持有者
     * @param title GUI标题
     * @param size GUI大小
     */
    public BlockInventoryHolderGuiHolder(Player player, BlockInventoryHolder blockInventoryHolder, String title, int size) {
        super(player, title, size);
        this.blockInventoryHolder = blockInventoryHolder;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param blockInventoryHolder 方块物品栏持有者
     * @param inventory 已有的Inventory
     */
    public BlockInventoryHolderGuiHolder(Player player, BlockInventoryHolder blockInventoryHolder, Inventory inventory) {
        super(player, inventory);
        this.blockInventoryHolder = blockInventoryHolder;
    }
    
    /**
     * 获取该GUI对应的方块物品栏持有者
     * @return 方块物品栏持有者
     */
    public BlockInventoryHolder getBlockInventoryHolder() {
        return blockInventoryHolder;
    }
    
    /**
     * 获取方块的位置X坐标
     * @return X坐标
     */
    public int getX() {
        return blockInventoryHolder.getBlock().getX();
    }
    
    /**
     * 获取方块的位置Y坐标
     * @return Y坐标
     */
    public int getY() {
        return blockInventoryHolder.getBlock().getY();
    }
    
    /**
     * 获取方块的位置Z坐标
     * @return Z坐标
     */
    public int getZ() {
        return blockInventoryHolder.getBlock().getZ();
    }
    
    /**
     * 获取方块所在的世界名称
     * @return 世界名称
     */
    public String getWorld() {
        return blockInventoryHolder.getBlock().getWorld().getName();
    }
    
    /**
     * 获取方块类型名称
     * @return 方块类型名称
     */
    public String getBlockType() {
        return blockInventoryHolder.getBlock().getType().toString();
    }
    
    /**
     * 获取方块对应的原始物品栏
     * @return 原始物品栏
     */
    public Inventory getBlockInventory() {
        return blockInventoryHolder.getInventory();
    }
    
    /**
     * 获取方块实例
     * @return 方块实例
     */
    public Block getBlock() {
        return blockInventoryHolder.getBlock();
    }
    
    /**
     * 更新方块状态
     */
    public void update() {
        super.update();
    }
} 
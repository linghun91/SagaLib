package cn.i7mc.sagalib.gui.holder.entity;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Player类型的GUI容器实现
 * 用于处理玩家特有的GUI功能
 */
public class PlayerGuiHolder extends HumanEntityGuiHolder {
    
    private final Player targetPlayer;
    
    /**
     * 创建一个新的Player GUI容器
     * @param player 玩家，用于解析占位符
     * @param targetPlayer 目标玩家
     * @param title GUI标题
     * @param size GUI大小
     */
    public PlayerGuiHolder(Player player, Player targetPlayer, String title, int size) {
        super(player, targetPlayer, title, size);
        this.targetPlayer = targetPlayer;
    }
    
    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param targetPlayer 目标玩家
     * @param inventory 已有的Inventory
     */
    public PlayerGuiHolder(Player player, Player targetPlayer, Inventory inventory) {
        super(player, targetPlayer, inventory);
        this.targetPlayer = targetPlayer;
    }
    
    /**
     * 获取目标玩家
     * @return 目标玩家
     */
    public Player getTargetPlayer() {
        return targetPlayer;
    }
    
    /**
     * 获取玩家的经验等级
     * @return 经验等级
     */
    public int getLevel() {
        return targetPlayer.getLevel();
    }
    
    /**
     * 获取玩家的游戏模式
     * @return 游戏模式
     */
    public String getGameMode() {
        return targetPlayer.getGameMode().toString();
    }
    
    /**
     * 获取玩家是否在飞行
     * @return 是否在飞行
     */
    public boolean isFlying() {
        return targetPlayer.isFlying();
    }
    
    /**
     * 获取玩家是否有权限
     * @param permission 权限
     * @return 是否有权限
     */
    public boolean hasPermission(String permission) {
        return targetPlayer.hasPermission(permission);
    }
    
    /**
     * 获取玩家的当前世界
     * @return 世界名称
     */
    public String getWorld() {
        return targetPlayer.getWorld().getName();
    }
    
    /**
     * 向玩家发送消息
     * @param message 消息
     */
    public void sendMessage(String message) {
        targetPlayer.sendMessage(message);
    }
    
    /**
     * 获取玩家是否为OP
     * @return 是否为OP
     */
    public boolean isOp() {
        return targetPlayer.isOp();
    }
    
    /**
     * 获取玩家的Ping值
     * @return Ping值
     */
    public int getPing() {
        return targetPlayer.getPing();
    }
} 
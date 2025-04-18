package cn.i7mc.sagalib.hologram;

import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 全息交互事件
 * 当玩家与全息文本交互时触发
 */
public class HologramInteractionEvent extends Event implements Cancellable {
    
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    
    private final Player player;
    private final TextDisplay textDisplay;
    private final String interactionTag;
    private final String panelId;
    
    /**
     * 创建全息交互事件
     * @param player 交互的玩家
     * @param textDisplay 被交互的全息文本
     * @param interactionTag 交互标签
     * @param panelId 面板ID
     */
    public HologramInteractionEvent(Player player, TextDisplay textDisplay, String interactionTag, String panelId) {
        this.player = player;
        this.textDisplay = textDisplay;
        this.interactionTag = interactionTag;
        this.panelId = panelId;
    }
    
    /**
     * 获取交互的玩家
     * @return 玩家
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * 获取被交互的全息文本
     * @return 全息文本
     */
    public TextDisplay getTextDisplay() {
        return textDisplay;
    }
    
    /**
     * 获取交互标签
     * @return 交互标签
     */
    public String getInteractionTag() {
        return interactionTag;
    }
    
    /**
     * 获取面板ID
     * @return 面板ID
     */
    public String getPanelId() {
        return panelId;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
} 
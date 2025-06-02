package cn.i7mc.sagalib.gui.listener;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * GUI事件监听器
 * 处理GUI相关的事件
 */
public class GuiListener implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof BaseGuiHolder)) {
            return;
        }

        event.setCancelled(true);
        
        BaseGuiHolder holder = (BaseGuiHolder) event.getInventory().getHolder();
        holder.handleClick(event.getRawSlot(), event);
    }
} 
package cn.i7mc.sagalib.gui.button;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import cn.i7mc.sagalib.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

/**
 * 简单按钮实现
 * 提供基础的按钮功能
 */
public class SimpleButton implements GuiButton {
    private final Material material;
    private final String name;
    private List<String> lore;
    private int slot;
    private Consumer<InventoryClickEvent> clickHandler;

    /**
     * 创建一个简单按钮
     * @param material 按钮材质
     * @param name 按钮名称
     */
    public SimpleButton(Material material, String name) {
        this.material = material;
        this.name = name;
    }

    /**
     * 设置按钮LORE
     * @param lore LORE列表
     * @return 按钮实例
     */
    public SimpleButton setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * 设置按钮数量
     * @param amount 数量
     * @return 按钮实例
     */
    public SimpleButton setAmount(int amount) {
        // This method is not provided in the original code or the new implementation
        // It's kept as it was in the original file
        return this;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * 设置点击事件处理器
     * @param handler 事件处理器
     * @return 按钮实例
     */
    public SimpleButton setClickHandler(Consumer<InventoryClickEvent> handler) {
        this.clickHandler = handler;
        return this;
    }

    @Override
    public ItemStack getItem(Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            // 解析名称中的占位符
            meta.setDisplayName(SagaLib.getAPI().parsePlaceholders(player, 
                    ColorUtil.translateColor(name)));
            
            // 解析LORE中的占位符
            if (lore != null) {
                meta.setLore(lore.stream()
                    .map(line -> SagaLib.getAPI().parsePlaceholders(player, 
                            ColorUtil.translateColor(line)))
                    .toList());
            }
            
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (clickHandler != null) {
            clickHandler.accept(event);
        }
    }

    @Override
    public void update(Player player) {
        // 更新按钮显示
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof BaseGuiHolder holder) {
            holder.setItem(slot, getItem(player));
        }
    }
} 
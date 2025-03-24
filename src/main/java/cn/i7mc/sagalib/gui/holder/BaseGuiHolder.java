package cn.i7mc.sagalib.gui.holder;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.layout.GuiLayout;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础GUI容器实现
 * 提供基本的GUI功能和按钮管理
 */
public class BaseGuiHolder implements GuiHolder {
    
    private final String title;
    private final int size;
    private final Inventory inventory;
    private final Map<Integer, GuiButton> buttons;
    private final Player player;
    private GuiStyle style;
    private GuiLayout layout;
    private Map<String, GuiAnimation> animations;
    
    /**
     * 创建一个新的GUI容器
     * @param player 玩家，用于解析占位符
     * @param title GUI标题
     * @param size GUI大小
     */
    public BaseGuiHolder(Player player, String title, int size) {
        this.player = player;
        this.title = SagaLib.getAPI().parsePlaceholders(player, ChatColor.translateAlternateColorCodes('&', title));
        this.size = size;
        this.inventory = Bukkit.createInventory(this, size, this.title);
        this.buttons = new HashMap<>();
        this.animations = new HashMap<>();
    }

    /**
     * 使用已有的Inventory创建GUI容器
     * @param player 玩家，用于解析占位符
     * @param inventory 已有的Inventory
     */
    public BaseGuiHolder(Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
        this.title = inventory.getViewers().isEmpty() ? "" : 
                    SagaLib.getAPI().parsePlaceholders(player, inventory.getViewers().get(0).getOpenInventory().getTitle());
        this.size = inventory.getSize();
        this.buttons = new HashMap<>();
        this.animations = new HashMap<>();
    }
    
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }
    
    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }
    
    @Override
    public void clearItem(int slot) {
        inventory.setItem(slot, null);
    }
    
    @Override
    public void clearAllItems() {
        inventory.clear();
        buttons.clear();
    }
    
    @Override
    public void update() {
        // 更新所有查看者
        inventory.getViewers().forEach(humanEntity -> {
            if (humanEntity instanceof Player) {
                Player viewer = (Player) humanEntity;
                // 更新所有按钮
                buttons.values().forEach(button -> button.update(viewer));
                // 重新打开GUI以更新标题
                viewer.openInventory(inventory);
            }
        });
    }

    /**
     * 设置GUI样式
     * @param style GUI样式
     */
    public void setStyle(GuiStyle style) {
        this.style = style;
        applyStyle();
    }

    /**
     * 获取GUI样式
     * @return GUI样式
     */
    public GuiStyle getStyle() {
        return style;
    }

    /**
     * 应用GUI样式
     */
    private void applyStyle() {
        if (style == null) {
            return;
        }

        // 填充边框
        for (int i = 0; i < 9; i++) {
            setItem(i, style.getBorderItem(player));
            setItem(size - 9 + i, style.getBorderItem(player));
        }
        for (int i = 0; i < size; i += 9) {
            setItem(i, style.getBorderItem(player));
            setItem(i + 8, style.getBorderItem(player));
        }

        // 填充背景
        for (int i = 10; i < size - 10; i++) {
            if (i % 9 != 0 && i % 9 != 8) {
                setItem(i, style.getBackgroundItem(player));
            }
        }
    }

    /**
     * 添加按钮
     * @param slot 槽位
     * @param button 按钮
     */
    @Override
    public void addButton(int slot, GuiButton button) {
        button.setSlot(slot);
        buttons.put(slot, button);
        setItem(slot, button.getItem(player));
    }

    /**
     * 移除按钮
     * @param slot 按钮槽位
     */
    public void removeButton(int slot) {
        buttons.remove(slot);
        clearItem(slot);
    }

    /**
     * 获取按钮
     * @param slot 按钮槽位
     * @return 按钮实例
     */
    public GuiButton getButton(int slot) {
        return buttons.get(slot);
    }

    /**
     * 处理点击事件
     * @param slot 点击槽位
     * @param event 点击事件
     */
    public void handleClick(int slot, InventoryClickEvent event) {
        GuiButton button = buttons.get(slot);
        if (button != null) {
            button.onClick(event);
        }
    }

    /**
     * 设置GUI布局
     * @param layout GUI布局
     */
    public void setLayout(GuiLayout layout) {
        this.layout = layout;
        if (layout != null) {
            layout.apply(this, buttons.values().toArray(new GuiButton[0]));
        }
    }

    /**
     * 获取GUI布局
     * @return GUI布局
     */
    public GuiLayout getLayout() {
        return layout;
    }

    /**
     * 应用布局
     * @param buttons 按钮列表
     */
    public void applyLayout(GuiButton[] buttons) {
        if (layout != null) {
            layout.apply(this, buttons);
        }
    }

    /**
     * 添加动画
     * @param animation GUI动画
     */
    @Override
    public void setAnimation(GuiAnimation animation) {
        if (animation != null) {
            animations.put(animation.getName(), animation);
            animation.start(this);
        }
    }

    /**
     * 获取GUI所属玩家
     * @return 玩家
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public GuiAnimation getAnimation() {
        return animations.values().stream().findFirst().orElse(null);
    }

    @Override
    public void stopAnimation() {
        animations.values().forEach(GuiAnimation::stop);
        animations.clear();
    }

    @Override
    public void addAnimation(GuiAnimation animation) {
        if (animation != null) {
            animations.put(animation.getName(), animation);
        }
    }

    public void startAnimation(GuiAnimation animation) {
        if (animation != null) {
            animation.start(this);
        }
    }
    
    public void stopAnimation(GuiAnimation animation) {
        if (animation != null) {
            animation.stop();
        }
    }
    
    public Map<String, GuiAnimation> getAnimations() {
        return animations;
    }

    /**
     * 获取动画状态
     * @param animation 动画实例
     * @return 动画状态(START/STOP/PAUSE/RESUME)
     */
    public String getAnimationStatus(GuiAnimation animation) {
        if (animation == null) return "STOP";
        return animation.getStatus();
    }
} 
package cn.i7mc.sagalib.example;

import cn.i7mc.sagalib.gui.holder.BaseGuiHolder;
import cn.i7mc.sagalib.gui.layout.PaginationLayout;
import cn.i7mc.sagalib.gui.button.SimpleButton;
import cn.i7mc.sagalib.gui.style.DefaultStyle;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import cn.i7mc.sagalib.gui.holder.GuiHolder;
import cn.i7mc.sagalib.util.ColorUtil;
import cn.i7mc.sagalib.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import cn.i7mc.sagalib.SagaLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GUI示例类
 * 展示如何使用SagaLib的GUI和工具类功能
 */
public class GuiExample {
    
    /**
     * 创建一个分页物品列表GUI
     * @param player 玩家
     * @param items 物品列表
     */
    public static void openPagedItemList(Player player, List<Material> items) {
        // 创建GUI容器
        BaseGuiHolder holder = new BaseGuiHolder(player, "&6物品列表", 54);
        
        // 设置样式
        holder.setStyle(new DefaultStyle());
        
        // 创建按钮列表
        List<SimpleButton> buttons = new ArrayList<>();
        for (Material material : items) {
            SimpleButton button = new SimpleButton(material, 
                    ColorUtil.translateColor("&f" + material.name()))
                    .setLore(Arrays.asList(
                            ColorUtil.translateColor("&7点击查看详情"),
                            ColorUtil.translateColor("&7ID: &f" + material.name())
                    ))
                    .setClickHandler(event -> {
                        Player clicker = (Player) event.getWhoClicked();
                        clicker.sendMessage(ColorUtil.translateColor(
                                "&a你点击了: &f" + material.name()));
                    });
            buttons.add(button);
        }
        
        // 设置分页布局
        PaginationLayout layout = new PaginationLayout(28, 7, 1, 1);
        holder.setLayout(layout);
        
        // 应用布局
        holder.applyLayout(buttons.toArray(new SimpleButton[0]));
        
        // 打开GUI
        player.openInventory(holder.getInventory());
    }
    
    /**
     * 创建一个带文本效果的GUI
     * @param player 玩家
     * @param title 标题
     */
    public static void openTextEffectGui(Player player, String title) {
        // 创建GUI容器
        BaseGuiHolder holder = new BaseGuiHolder(player, title, 27);
        
        // 设置样式
        holder.setStyle(new DefaultStyle());
        
        // 创建文本按钮
        String centeredTitle = TextUtil.center(title, 20);
        SimpleButton titleButton = new SimpleButton(Material.PAPER, 
                ColorUtil.translateColor(centeredTitle))
                .setLore(Arrays.asList(
                        ColorUtil.translateColor("&7这是一个示例GUI"),
                        ColorUtil.translateColor("&7使用了文本工具类")
                ));
        titleButton.setSlot(13);
        holder.addButton(13, titleButton);
        
        // 创建左对齐按钮
        String leftText = TextUtil.align("左对齐文本", 15, TextUtil.TextAlignment.LEFT);
        SimpleButton leftButton = new SimpleButton(Material.BOOK, 
                ColorUtil.translateColor(leftText));
        leftButton.setSlot(11);
        holder.addButton(11, leftButton);
        
        // 创建右对齐按钮
        String rightText = TextUtil.align("右对齐文本", 15, TextUtil.TextAlignment.RIGHT);
        SimpleButton rightButton = new SimpleButton(Material.BOOK, 
                ColorUtil.translateColor(rightText));
        rightButton.setSlot(15);
        holder.addButton(15, rightButton);
        
        // 打开GUI
        player.openInventory(holder.getInventory());
    }
    
    /**
     * 创建一个带颜色效果的GUI
     * @param player 玩家
     */
    public static void openColorEffectGui(Player player) {
        // 创建GUI容器
        BaseGuiHolder holder = new BaseGuiHolder(player, "&6颜色效果", 27);
        
        // 设置样式
        holder.setStyle(new DefaultStyle());
        
        // 创建彩色按钮
        String[] colors = {"red", "green", "blue", "yellow", "purple"};
        for (int i = 0; i < colors.length; i++) {
            String color = colors[i];
            Material glass = ColorUtil.getStainedGlassPane(color);
            Material wool = ColorUtil.getWool(color);
            
            // 创建玻璃板按钮
            SimpleButton glassButton = new SimpleButton(glass, 
                    ColorUtil.translateColor("&f" + color + "玻璃板"));
            glassButton.setSlot(i * 2);
            holder.addButton(i * 2, glassButton);
            
            // 创建羊毛按钮
            SimpleButton woolButton = new SimpleButton(wool, 
                    ColorUtil.translateColor("&f" + color + "羊毛"));
            woolButton.setSlot(i * 2 + 1);
            holder.addButton(i * 2 + 1, woolButton);
        }
        
        // 打开GUI
        player.openInventory(holder.getInventory());
    }
    
    /**
     * 创建一个展示动画效果的GUI
     * @param player 玩家
     */
    public static void openAnimationGui(Player player) {
        // 创建GUI容器
        BaseGuiHolder holder = new BaseGuiHolder(player, "&6动画效果", 27);
        
        // 设置样式
        holder.setStyle(new DefaultStyle());
        
        // 创建动画实例
        final GuiAnimation fadeAnimation = new GuiAnimation() {
            private boolean running = false;
            private int taskId = -1;
            private float opacity = 0.0f;
            private boolean increasing = true;
            
            @Override
            public void start(GuiHolder holder) {
                running = true;
                opacity = 0.0f;
                increasing = true;
                
                // 创建淡入淡出效果
                taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SagaLib.getInstance(), () -> {
                    if (!running) {
                        if (taskId != -1) {
                            Bukkit.getScheduler().cancelTask(taskId);
                            taskId = -1;
                        }
                        return;
                    }
                    
                    // 更新透明度
                    if (increasing) {
                        opacity += 0.1f;
                        if (opacity >= 1.0f) {
                            opacity = 1.0f;
                            increasing = false;
                        }
                    } else {
                        opacity -= 0.1f;
                        if (opacity <= 0.0f) {
                            opacity = 0.0f;
                            increasing = true;
                        }
                    }
                    
                    // 更新按钮颜色
                    for (int i = 0; i < holder.getInventory().getSize(); i++) {
                        ItemStack item = holder.getInventory().getItem(i);
                        if (item != null && item.getType() != Material.AIR) {
                            ItemMeta meta = item.getItemMeta();
                            if (meta != null) {
                                meta.setDisplayName(ColorUtil.translateColor(
                                    String.format("&f动画演示 (透明度: %.1f%%)", opacity * 100)));
                                item.setItemMeta(meta);
                            }
                        }
                    }
                }, 0L, 5L);
            }
            
            @Override
            public void stop() {
                running = false;
                if (taskId != -1) {
                    Bukkit.getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
            }
            
            @Override
            public boolean isRunning() {
                return running;
            }
            
            @Override
            public String getName() {
                return "fade";
            }
            
            @Override
            public String getDescription() {
                return "淡入淡出动画效果";
            }

            @Override
            public String getStatus() {
                return running ? "START" : "STOP";
            }
        };
        
        final GuiAnimation slideAnimation = new GuiAnimation() {
            private boolean running = false;
            private int taskId = -1;
            private int offset = 0;
            private boolean movingRight = true;
            
            @Override
            public void start(GuiHolder holder) {
                running = true;
                offset = 0;
                movingRight = true;
                
                // 创建滑动效果
                taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SagaLib.getInstance(), () -> {
                    if (!running) {
                        if (taskId != -1) {
                            Bukkit.getScheduler().cancelTask(taskId);
                            taskId = -1;
                        }
                        return;
                    }
                    
                    // 更新偏移量
                    if (movingRight) {
                        offset++;
                        if (offset >= 8) {
                            offset = 8;
                            movingRight = false;
                        }
                    } else {
                        offset--;
                        if (offset <= 0) {
                            offset = 0;
                            movingRight = true;
                        }
                    }
                    
                    // 更新按钮位置
                    ItemStack centerItem = holder.getInventory().getItem(13);
                    if (centerItem != null && centerItem.getType() != Material.AIR) {
                        holder.getInventory().setItem(13, null);
                        holder.getInventory().setItem(13 + offset, centerItem);
                    }
                }, 0L, 5L);
            }
            
            @Override
            public void stop() {
                running = false;
                if (taskId != -1) {
                    Bukkit.getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
            }
            
            @Override
            public boolean isRunning() {
                return running;
            }
            
            @Override
            public String getName() {
                return "slide";
            }
            
            @Override
            public String getDescription() {
                return "滑动动画效果";
            }

            @Override
            public String getStatus() {
                return running ? "START" : "STOP";
            }
        };
        
        // 创建中心按钮
        SimpleButton centerButton = new SimpleButton(Material.DIAMOND, 
                ColorUtil.translateColor("&f动画演示"))
                .setLore(Arrays.asList(
                        ColorUtil.translateColor("&7点击切换动画效果"),
                        ColorUtil.translateColor("&7当前动画: &f淡入淡出")
                ));
        
        // 设置按钮点击事件
        centerButton.setClickHandler(event -> {
            // 切换动画效果
            if (fadeAnimation.isRunning()) {
                fadeAnimation.stop();
                slideAnimation.start(holder);
                centerButton.setLore(Arrays.asList(
                        ColorUtil.translateColor("&7点击切换动画效果"),
                        ColorUtil.translateColor("&7当前动画: &f滑动")
                ));
            } else {
                slideAnimation.stop();
                fadeAnimation.start(holder);
                centerButton.setLore(Arrays.asList(
                        ColorUtil.translateColor("&7点击切换动画效果"),
                        ColorUtil.translateColor("&7当前动画: &f淡入淡出")
                ));
            }
        });
        
        // 设置按钮位置
        centerButton.setSlot(13);
        holder.addButton(13, centerButton);
        
        // 添加动画
        holder.addAnimation(fadeAnimation);
        holder.addAnimation(slideAnimation);
        
        // 启动淡入淡出动画
        fadeAnimation.start(holder);
        
        // 打开GUI
        player.openInventory(holder.getInventory());
    }
} 
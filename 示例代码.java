package cn.i7mc.testgui;

import cn.i7mc.sagalib.api.SagaLibAPI;
import cn.i7mc.sagalib.gui.holder.GuiHolder;
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.button.SimpleButton;
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGui extends JavaPlugin {
    private SagaLibAPI sagaLib;

    @Override
    public void onEnable() {
        sagaLib = SagaLibAPI.getInstance();
        getLogger().info("TestGui插件已启动!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TestGui插件已关闭!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("该命令只能由玩家执行!");
            return true;
        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("testgui")) {
            openMainMenu(player);
            return true;
        }
        return false;
    }

    private void openMainMenu(Player player) {
        // 创建基础GUI (6行 = 54格)
        GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建按钮
        Map<Integer, GuiButton> buttons = new HashMap<>();
        
        // 创建分页按钮
        SimpleButton paginationButton = new SimpleButton(Material.BOOK, "分页菜单");
        paginationButton.setLore(Arrays.asList("&7点击打开分页菜单", "&7展示分页功能"));
        paginationButton.setClickHandler(event -> openPaginationMenu(player));
        buttons.put(11, paginationButton);

        // 创建LORE展示按钮
        SimpleButton loreButton = new SimpleButton(Material.DIAMOND, "LORE展示");
        loreButton.setLore(Arrays.asList("&7点击查看LORE展示", "&7展示LORE功能"));
        loreButton.setClickHandler(event -> openLoreMenu(player));
        buttons.put(15, loreButton);
        
        // 创建动画展示按钮
        SimpleButton animationButton = new SimpleButton(Material.NETHER_STAR, "动画效果");
        animationButton.setLore(Arrays.asList("&7点击查看动画效果", "&7展示动画功能"));
        animationButton.setClickHandler(event -> openAnimationMenu(player));
        buttons.put(22, animationButton);

        // 创建彩虹渐变测试分页按钮
        SimpleButton rainbowButton = new SimpleButton(Material.END_CRYSTAL, "彩虹渐变测试分页");
        rainbowButton.setLore(Arrays.asList("&7点击查看彩虹渐变效果", "&7展示彩虹渐变分页"));
        rainbowButton.setClickHandler(event -> openRainbowPaginationMenu(player));
        buttons.put(31, rainbowButton);

        // 添加所有按钮
        sagaLib.addButtons(gui, buttons);

        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    private void openPaginationMenu(Player player) {
        // 创建普通GUI (6行 = 54格)，不使用分页GUI
        GuiHolder gui = sagaLib.createGui(player, "分页菜单 - 第1页", 54);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建返回按钮
        SimpleButton backButton = new SimpleButton(Material.BARRIER, "返回主菜单");
        backButton.setClickHandler(event -> openMainMenu(player));
        sagaLib.addButton(gui, 49, backButton);

        // 定义分页参数
        final int[] currentPage = {0}; // 使用数组存储当前页码，以便在lambda中修改
        final int itemsPerPage = 45;
        final int totalItems = 225; // 5页 * 45个物品
        final int maxPage = (totalItems - 1) / itemsPerPage;

        // 加载第一页的物品
        loadPageItems(gui, currentPage[0], itemsPerPage, totalItems);

        // 创建上一页按钮
        SimpleButton prevButton = new SimpleButton(Material.ARROW, "上一页");
        prevButton.setClickHandler(event -> {
            if (currentPage[0] > 0) {
                currentPage[0]--;
                // 关闭当前菜单，打开新页面
                openPageMenu(player, currentPage[0], itemsPerPage, totalItems, maxPage);
            }
        });
        sagaLib.addButton(gui, 45, prevButton);

        // 创建下一页按钮
        SimpleButton nextButton = new SimpleButton(Material.ARROW, "下一页");
        nextButton.setClickHandler(event -> {
            if (currentPage[0] < maxPage) {
                currentPage[0]++;
                // 关闭当前菜单，打开新页面
                openPageMenu(player, currentPage[0], itemsPerPage, totalItems, maxPage);
            }
        });
        sagaLib.addButton(gui, 53, nextButton);

        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    /**
     * 打开指定页码的菜单
     */
    private void openPageMenu(Player player, int page, int itemsPerPage, int totalItems, int maxPage) {
        // 创建普通GUI (6行 = 54格)
        GuiHolder gui = sagaLib.createGui(player, "分页菜单 - 第" + (page + 1) + "页", 54);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建返回按钮
        SimpleButton backButton = new SimpleButton(Material.BARRIER, "返回主菜单");
        backButton.setClickHandler(event -> openMainMenu(player));
        sagaLib.addButton(gui, 49, backButton);

        // 加载当前页的物品
        loadPageItems(gui, page, itemsPerPage, totalItems);

        // 创建上一页按钮
        SimpleButton prevButton = new SimpleButton(Material.ARROW, "上一页");
        prevButton.setClickHandler(event -> {
            if (page > 0) {
                openPageMenu(player, page - 1, itemsPerPage, totalItems, maxPage);
            }
        });
        sagaLib.addButton(gui, 45, prevButton);

        // 创建下一页按钮
        SimpleButton nextButton = new SimpleButton(Material.ARROW, "下一页");
        nextButton.setClickHandler(event -> {
            if (page < maxPage) {
                openPageMenu(player, page + 1, itemsPerPage, totalItems, maxPage);
            }
        });
        sagaLib.addButton(gui, 53, nextButton);

        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    /**
     * 加载指定页码的物品
     */
    private void loadPageItems(GuiHolder gui, int page, int itemsPerPage, int totalItems) {
        // 计算当前页的起始物品索引
        int startIndex = page * itemsPerPage;
        
        // 计算当前页的结束物品索引（不超过总物品数）
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        
        // 添加当前页的物品
        for (int i = startIndex; i < endIndex; i++) {
            // 计算在当前页内的索引位置（0-44）
            int slotIndex = i - startIndex;
            
            // 创建物品
            ItemStack item = new ItemStack(Material.DIAMOND);
            List<String> lore = Arrays.asList(
                "&7这是第 " + (i + 1) + " 个物品",
                "&7第 " + (page + 1) + " 页",
                "&7物品索引: " + i,
                "&7槽位索引: " + slotIndex
            );
            sagaLib.setItemLore(item, lore);
            
            // 添加物品
            sagaLib.addItem(gui, slotIndex, item);
        }
    }

    private void openLoreMenu(Player player) {
        // 创建动态大小GUI (最小3行=27格, 最大6行=54格, 内容45格)
        GuiHolder gui = sagaLib.createDynamicGui(player, "LORE展示", 27, 54, 45);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建返回按钮
        SimpleButton backButton = new SimpleButton(Material.BARRIER, "返回主菜单");
        backButton.setClickHandler(event -> openMainMenu(player));
        sagaLib.addButton(gui, 49, backButton);

        // 创建示例物品
        Map<Integer, GuiButton> buttons = new HashMap<>();
        
        // 钻石剑
        SimpleButton diamondSword = new SimpleButton(Material.DIAMOND_SWORD, "&b钻石剑");
        diamondSword.setLore(Arrays.asList(
            "&7这是一把钻石剑",
            "&7攻击力: &c+7",
            "&7耐久度: &e1561/1561"
        ));
        buttons.put(11, diamondSword);

        // 钻石胸甲
        SimpleButton diamondChestplate = new SimpleButton(Material.DIAMOND_CHESTPLATE, "&b钻石胸甲");
        diamondChestplate.setLore(Arrays.asList(
            "&7这是一件钻石胸甲",
            "&7防御力: &c+8",
            "&7耐久度: &e528/528"
        ));
        buttons.put(15, diamondChestplate);

        // 添加所有按钮
        sagaLib.addButtons(gui, buttons);

        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    /**
     * 打开动画效果展示菜单
     */
    private void openAnimationMenu(Player player) {
        // 创建普通GUI (3行 = 27格)
        GuiHolder gui = sagaLib.createGui(player, "动画效果展示", 27);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建返回按钮
        SimpleButton backButton = new SimpleButton(Material.BARRIER, "返回主菜单");
        backButton.setClickHandler(event -> openMainMenu(player));
        sagaLib.addButton(gui, 22, backButton);

        // 创建淡入淡出动画
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
                taskId = getServer().getScheduler().scheduleSyncRepeatingTask(TestGui.this, () -> {
                    if (!running) {
                        if (taskId != -1) {
                            getServer().getScheduler().cancelTask(taskId);
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
                    
                    // 更新动画按钮
                    for (int i = 0; i < 9; i++) {
                        ItemStack item = holder.getInventory().getItem(i + 9);
                        if (item != null && item.getType() != Material.AIR) {
                            if (item.hasItemMeta()) {
                                ItemStack newItem = new ItemStack(item);
                                ItemMeta meta = newItem.getItemMeta();
                                if (meta != null) {
                                    meta.setDisplayName(sagaLib.parseColor(
                                        String.format("&%d淡入淡出效果 (%.1f%%)", 
                                            (int)(opacity * 14), opacity * 100)));
                                    newItem.setItemMeta(meta);
                                    holder.getInventory().setItem(i + 9, newItem);
                                }
                            }
                        }
                    }
                }, 0L, 5L);
            }
            
            @Override
            public void stop() {
                running = false;
                if (taskId != -1) {
                    getServer().getScheduler().cancelTask(taskId);
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
        
        // 创建滑动动画
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
                taskId = getServer().getScheduler().scheduleSyncRepeatingTask(TestGui.this, () -> {
                    if (!running) {
                        if (taskId != -1) {
                            getServer().getScheduler().cancelTask(taskId);
                            taskId = -1;
                        }
                        return;
                    }
                    
                    // 清空旧物品
                    for (int i = 9; i < 18; i++) {
                        holder.getInventory().setItem(i, null);
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
                    
                    // 在新位置创建物品
                    ItemStack item = new ItemStack(Material.DRAGON_EGG);
                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName(sagaLib.parseColor("&e滑动动画效果"));
                        item.setItemMeta(meta);
                    }
                    holder.getInventory().setItem(9 + offset, item);
                }, 0L, 5L);
            }
            
            @Override
            public void stop() {
                running = false;
                if (taskId != -1) {
                    getServer().getScheduler().cancelTask(taskId);
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
        
        // 创建动画切换按钮
        SimpleButton fadeButton = new SimpleButton(Material.DIAMOND, "启动淡入淡出动画");
        fadeButton.setLore(Arrays.asList("&7点击启动淡入淡出动画"));
        fadeButton.setClickHandler(event -> {
            // 如果动画正在运行，先停止
            slideAnimation.stop();
            if (fadeAnimation.isRunning()) {
                sagaLib.stopAnimation(gui, fadeAnimation);
                // 创建新按钮实例而不是尝试修改现有按钮的名称
                SimpleButton newFadeButton = new SimpleButton(Material.DIAMOND, "启动淡入淡出动画");
                newFadeButton.setLore(Arrays.asList("&7点击启动淡入淡出动画"));
                // 重新创建点击处理程序
                newFadeButton.setClickHandler(e -> {
                    slideAnimation.stop();
                    sagaLib.startAnimation(gui, fadeAnimation);
                    updateFadeButton(gui, fadeAnimation, slideAnimation, true);
                });
                sagaLib.addButton(gui, 0, newFadeButton);
            } else {
                sagaLib.startAnimation(gui, fadeAnimation);
                // 创建新按钮实例而不是尝试修改现有按钮的名称
                SimpleButton newFadeButton = new SimpleButton(Material.DIAMOND, "停止淡入淡出动画");
                newFadeButton.setLore(Arrays.asList("&7点击停止淡入淡出动画"));
                // 重新创建点击处理程序
                newFadeButton.setClickHandler(e -> {
                    sagaLib.stopAnimation(gui, fadeAnimation);
                    updateFadeButton(gui, fadeAnimation, slideAnimation, false);
                });
                sagaLib.addButton(gui, 0, newFadeButton);
            }
        });
        sagaLib.addButton(gui, 0, fadeButton);
        
        SimpleButton slideButton = new SimpleButton(Material.EMERALD, "启动滑动动画");
        slideButton.setLore(Arrays.asList("&7点击启动滑动动画"));
        slideButton.setClickHandler(event -> {
            // 如果动画正在运行，先停止
            fadeAnimation.stop();
            if (slideAnimation.isRunning()) {
                sagaLib.stopAnimation(gui, slideAnimation);
                // 创建新按钮实例而不是尝试修改现有按钮的名称
                SimpleButton newSlideButton = new SimpleButton(Material.EMERALD, "启动滑动动画");
                newSlideButton.setLore(Arrays.asList("&7点击启动滑动动画"));
                // 重新创建点击处理程序
                newSlideButton.setClickHandler(e -> {
                    fadeAnimation.stop();
                    sagaLib.startAnimation(gui, slideAnimation);
                    updateSlideButton(gui, fadeAnimation, slideAnimation, true);
                });
                sagaLib.addButton(gui, 8, newSlideButton);
            } else {
                sagaLib.startAnimation(gui, slideAnimation);
                // 创建新按钮实例而不是尝试修改现有按钮的名称
                SimpleButton newSlideButton = new SimpleButton(Material.EMERALD, "停止滑动动画");
                newSlideButton.setLore(Arrays.asList("&7点击停止滑动动画"));
                // 重新创建点击处理程序
                newSlideButton.setClickHandler(e -> {
                    sagaLib.stopAnimation(gui, slideAnimation);
                    updateSlideButton(gui, fadeAnimation, slideAnimation, false);
                });
                sagaLib.addButton(gui, 8, newSlideButton);
            }
        });
        sagaLib.addButton(gui, 8, slideButton);
        
        // 显示动画效果的初始物品
        for (int i = 0; i < 9; i++) {
            ItemStack item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(sagaLib.parseColor("&f动画效果区域"));
                item.setItemMeta(meta);
            }
            sagaLib.addItem(gui, i + 9, item);
        }
        
        // 添加状态按钮
        SimpleButton statusButton = new SimpleButton(Material.PAPER, "动画状态");
        statusButton.setLore(Arrays.asList(
            "&7淡入淡出: &f" + sagaLib.getAnimationStatus(gui, fadeAnimation),
            "&7滑动: &f" + sagaLib.getAnimationStatus(gui, slideAnimation)
        ));
        statusButton.setClickHandler(event -> {
            // 更新状态按钮的LORE
            statusButton.setLore(Arrays.asList(
                "&7淡入淡出: &f" + sagaLib.getAnimationStatus(gui, fadeAnimation),
                "&7滑动: &f" + sagaLib.getAnimationStatus(gui, slideAnimation)
            ));
            sagaLib.addButton(gui, 18, statusButton);
        });
        sagaLib.addButton(gui, 18, statusButton);
        
        // 添加动画到GUI
        sagaLib.addAnimation(gui, fadeAnimation);
        sagaLib.addAnimation(gui, slideAnimation);
        
        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    private void updateFadeButton(GuiHolder gui, GuiAnimation fadeAnimation, GuiAnimation slideAnimation, boolean isRunning) {
        if (isRunning) {
            // 创建停止按钮
            SimpleButton stopButton = new SimpleButton(Material.DIAMOND, "停止淡入淡出动画");
            stopButton.setLore(Arrays.asList("&7点击停止淡入淡出动画"));
            stopButton.setClickHandler(e -> {
                sagaLib.stopAnimation(gui, fadeAnimation);
                updateFadeButton(gui, fadeAnimation, slideAnimation, false);
            });
            sagaLib.addButton(gui, 0, stopButton);
        } else {
            // 创建启动按钮
            SimpleButton startButton = new SimpleButton(Material.DIAMOND, "启动淡入淡出动画");
            startButton.setLore(Arrays.asList("&7点击启动淡入淡出动画"));
            startButton.setClickHandler(e -> {
                slideAnimation.stop();
                sagaLib.startAnimation(gui, fadeAnimation);
                updateFadeButton(gui, fadeAnimation, slideAnimation, true);
            });
            sagaLib.addButton(gui, 0, startButton);
        }
    }

    private void updateSlideButton(GuiHolder gui, GuiAnimation fadeAnimation, GuiAnimation slideAnimation, boolean isRunning) {
        if (isRunning) {
            // 创建停止按钮
            SimpleButton stopButton = new SimpleButton(Material.EMERALD, "停止滑动动画");
            stopButton.setLore(Arrays.asList("&7点击停止滑动动画"));
            stopButton.setClickHandler(e -> {
                sagaLib.stopAnimation(gui, slideAnimation);
                updateSlideButton(gui, fadeAnimation, slideAnimation, false);
            });
            sagaLib.addButton(gui, 8, stopButton);
        } else {
            // 创建启动按钮
            SimpleButton startButton = new SimpleButton(Material.EMERALD, "启动滑动动画");
            startButton.setLore(Arrays.asList("&7点击启动滑动动画"));
            startButton.setClickHandler(e -> {
                fadeAnimation.stop();
                sagaLib.startAnimation(gui, slideAnimation);
                updateSlideButton(gui, fadeAnimation, slideAnimation, true);
            });
            sagaLib.addButton(gui, 8, startButton);
        }
    }

    /**
     * 打开彩虹渐变分页菜单
     */
    private void openRainbowPaginationMenu(Player player) {
        // 创建分页GUI (5行 = 45格)
        GuiHolder gui = sagaLib.createPaginationGui(player, "彩虹渐变测试分页", 45);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
            Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
            Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
        );
        sagaLib.applyStyle(gui, style);

        // 创建返回按钮
        SimpleButton backButton = new SimpleButton(Material.BARRIER, "返回主菜单");
        backButton.setClickHandler(event -> openMainMenu(player));
        sagaLib.addButton(gui, 49, backButton);

        // 加载分页物品
        loadRainbowItems(gui, 45);
        
        // 创建彩虹渐变动画
        final GuiAnimation rainbowAnimation = new GuiAnimation() {
            private boolean running = false;
            private int taskId = -1;
            private int colorIndex = 0;
            private final Material[] colors = {
                Material.RED_STAINED_GLASS_PANE,
                Material.ORANGE_STAINED_GLASS_PANE,
                Material.YELLOW_STAINED_GLASS_PANE,
                Material.LIME_STAINED_GLASS_PANE,
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                Material.BLUE_STAINED_GLASS_PANE,
                Material.PURPLE_STAINED_GLASS_PANE
            };
            
            @Override
            public void start(GuiHolder holder) {
                running = true;
                colorIndex = 0;
                
                // 创建彩虹效果
                taskId = getServer().getScheduler().scheduleSyncRepeatingTask(TestGui.this, () -> {
                    if (!running) {
                        if (taskId != -1) {
                            getServer().getScheduler().cancelTask(taskId);
                            taskId = -1;
                        }
                        return;
                    }
                    
                    // 更新颜色索引
                    colorIndex = (colorIndex + 1) % colors.length;
                    
                    // 更新边框颜色
                    for (int i = 0; i < 9; i++) {
                        // 顶部边框
                        ItemStack topBorder = new ItemStack(colors[colorIndex]);
                        ItemMeta topMeta = topBorder.getItemMeta();
                        if (topMeta != null) {
                            topMeta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e渐&a变&b边&9框"));
                            topBorder.setItemMeta(topMeta);
                        }
                        holder.getInventory().setItem(i, topBorder);
                        
                        // 底部边框 (不包括导航按钮位置)
                        if (i == 4) continue; // 跳过中间按钮位置
                        
                        // 修复数组越界问题，使用Math.floorMod确保索引为非负数
                        int bottomColorIndex = Math.floorMod(colorIndex + colors.length - i, colors.length);
                        ItemStack bottomBorder = new ItemStack(colors[bottomColorIndex]);
                        ItemMeta bottomMeta = bottomBorder.getItemMeta();
                        if (bottomMeta != null) {
                            bottomMeta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e渐&a变&b边&9框"));
                            bottomBorder.setItemMeta(bottomMeta);
                        }
                        
                        // 底部导航栏 (54-9 到 54-1)
                        if (i != 0 && i != 8) { // 跳过导航按钮位置
                            holder.getInventory().setItem(holder.getInventory().getSize() - 9 + i, bottomBorder);
                        }
                    }
                    
                    // 更新侧边框
                    for (int i = 0; i < 5; i++) { // 5行
                        // 左侧边框
                        int leftColorIndex = Math.floorMod(colorIndex + i, colors.length);
                        ItemStack leftBorder = new ItemStack(colors[leftColorIndex]);
                        ItemMeta leftMeta = leftBorder.getItemMeta();
                        if (leftMeta != null) {
                            leftMeta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e渐&a变&b边&9框"));
                            leftBorder.setItemMeta(leftMeta);
                        }
                        holder.getInventory().setItem(i * 9, leftBorder);
                        
                        // 右侧边框
                        int rightColorIndex = Math.floorMod(colorIndex + colors.length - i, colors.length);
                        ItemStack rightBorder = new ItemStack(colors[rightColorIndex]);
                        ItemMeta rightMeta = rightBorder.getItemMeta();
                        if (rightMeta != null) {
                            rightMeta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e渐&a变&b边&9框"));
                            rightBorder.setItemMeta(rightMeta);
                        }
                        holder.getInventory().setItem(i * 9 + 8, rightBorder);
                    }
                }, 0L, 10L);
            }
            
            @Override
            public void stop() {
                running = false;
                if (taskId != -1) {
                    getServer().getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
            }
            
            @Override
            public boolean isRunning() {
                return running;
            }
            
            @Override
            public String getName() {
                return "rainbow";
            }
            
            @Override
            public String getDescription() {
                return "彩虹渐变动画效果";
            }

            @Override
            public String getStatus() {
                return running ? "START" : "STOP";
            }
        };
        
        // 添加动画到GUI
        sagaLib.addAnimation(gui, rainbowAnimation);
        
        // 启动动画
        sagaLib.startAnimation(gui, rainbowAnimation);
        
        // 创建动画控制按钮
        SimpleButton toggleButton = new SimpleButton(Material.NETHER_STAR, "停止彩虹动画");
        toggleButton.setLore(Arrays.asList("&7点击切换彩虹动画状态"));
        toggleButton.setClickHandler(event -> {
            if (rainbowAnimation.isRunning()) {
                sagaLib.stopAnimation(gui, rainbowAnimation);
                updateRainbowButton(gui, rainbowAnimation, false);
            } else {
                sagaLib.startAnimation(gui, rainbowAnimation);
                updateRainbowButton(gui, rainbowAnimation, true);
            }
        });
        sagaLib.addButton(gui, 4, toggleButton);
        
        // 打开GUI
        player.openInventory(gui.getInventory());
    }

    /**
     * 加载彩虹分页的物品
     */
    private void loadRainbowItems(GuiHolder gui, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            // 计算物品材质 (使用不同颜色的玻璃板模拟彩虹效果)
            Material material;
            switch (i % 7) {
                case 0: material = Material.RED_STAINED_GLASS_PANE; break;
                case 1: material = Material.ORANGE_STAINED_GLASS_PANE; break;
                case 2: material = Material.YELLOW_STAINED_GLASS_PANE; break;
                case 3: material = Material.LIME_STAINED_GLASS_PANE; break;
                case 4: material = Material.LIGHT_BLUE_STAINED_GLASS_PANE; break;
                case 5: material = Material.BLUE_STAINED_GLASS_PANE; break;
                default: material = Material.PURPLE_STAINED_GLASS_PANE; break;
            }
            
            // 创建物品
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e物&a品 &b#" + (i + 1)));
                List<String> lore = Arrays.asList(
                    "&7这是一个彩虹物品",
                    "&7序号: &f" + (i + 1),
                    "&7点击查看详情"
                );
                sagaLib.setItemLore(item, lore);
            }
            
            // 添加物品
            sagaLib.addItem(gui, i, item);
        }
    }

    /**
     * 更新彩虹动画按钮
     */
    private void updateRainbowButton(GuiHolder gui, GuiAnimation rainbowAnimation, boolean isRunning) {
        SimpleButton button;
        if (isRunning) {
            button = new SimpleButton(Material.NETHER_STAR, "停止彩虹动画");
            button.setLore(Arrays.asList("&7当前状态: &a运行中", "&7点击停止动画"));
        } else {
            button = new SimpleButton(Material.REDSTONE, "启动彩虹动画");
            button.setLore(Arrays.asList("&7当前状态: &c已停止", "&7点击启动动画"));
        }
        
        button.setClickHandler(event -> {
            if (rainbowAnimation.isRunning()) {
                sagaLib.stopAnimation(gui, rainbowAnimation);
                updateRainbowButton(gui, rainbowAnimation, false);
            } else {
                sagaLib.startAnimation(gui, rainbowAnimation);
                updateRainbowButton(gui, rainbowAnimation, true);
            }
        });
        
        sagaLib.addButton(gui, 4, button);
    }
} 
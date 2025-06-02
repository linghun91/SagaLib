package cn.i7mc.sagalib.example;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.api.SagaLibAPI;
import cn.i7mc.sagalib.hologram.animation.HologramAnimation;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 全息文本示例类
 * 演示如何使用全息文本功能
 */
public class HologramExample implements CommandExecutor {
    
    private final SagaLib plugin;
    private final SagaLibAPI api;
    private final Map<String, String> playerHolograms = new HashMap<>();
    private final Map<String, String> playerAnimations = new HashMap<>();
    
    public HologramExample(SagaLib plugin) {
        this.plugin = plugin;
        this.api = SagaLibAPI.getInstance();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c该命令只能由玩家执行！");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            sendHelpMessage(player);
            return true;
        }
        
        String action = args[0].toLowerCase();
        
        switch (action) {
            case "create":
                createHologram(player, args);
                break;
            case "remove":
                removeHologram(player);
                break;
            case "update":
                updateHologram(player, args);
                break;
            case "animation":
                handleAnimation(player, args);
                break;
            case "progress":
                createProgressBar(player, args);
                break;
            case "circle":
                createCircularProgressBar(player, args);
                break;
            case "vertical":
                createVerticalProgressBar(player, args);
                break;
            case "panel":
                createInteractivePanel(player, args);
                break;
            default:
                sendHelpMessage(player);
                break;
        }
        
        return true;
    }
    
    /**
     * 创建全息文本(完整参数)
     * @param player 玩家
     * @param args 参数
     */
    private void createHologram(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram create <文本> [背景色Hex] [透明度] [广告牌样式]");
            return;
        }
        
        // 获取文本内容
        StringBuilder text = new StringBuilder();
        for (int i = 1; i < (args.length > 4 ? 2 : args.length); i++) {
            text.append(args[i]).append(" ");
        }
        
        // 在玩家面前创建全息文本
        Location location = player.getLocation().add(0, 1.5, 0);
        
        // 定义参数，为lambda表达式准备
        Color bgColor = null;
        byte opacity = (byte) 127; // 默认不透明度
        TextDisplay.TextAlignment alignment = TextDisplay.TextAlignment.CENTER;
        Display.Billboard billboard = Display.Billboard.CENTER; // 默认广告牌样式
        
        // 解析背景色
        if (args.length >= 3) {
            try {
                String colorCode = args[2];
                if (colorCode.startsWith("#")) {
                    colorCode = colorCode.substring(1);
                }
                
                if (colorCode.length() == 6) {
                    int r = Integer.parseInt(colorCode.substring(0, 2), 16);
                    int g = Integer.parseInt(colorCode.substring(2, 4), 16);
                    int b = Integer.parseInt(colorCode.substring(4, 6), 16);
                    bgColor = Color.fromRGB(r, g, b);
                }
            } catch (Exception e) {
                player.sendMessage("§c无效的颜色代码！使用格式: #RRGGBB");
            }
        }
        
        // 解析不透明度
        if (args.length >= 4) {
            try {
                int opacityValue = Integer.parseInt(args[3]);
                if (opacityValue >= 0 && opacityValue <= 127) {
                    opacity = (byte) opacityValue;
                } else {
                    player.sendMessage("§c不透明度必须在0-127之间！使用默认值127。");
                }
            } catch (NumberFormatException e) {
                player.sendMessage("§c无效的不透明度！使用默认值127。");
            }
        }
        
        // 解析广告牌样式
        if (args.length >= 5) {
            String billboardType = args[4].toUpperCase();
            try {
                Display.Billboard parsedBillboard = Display.Billboard.valueOf(billboardType);
                billboard = parsedBillboard;
            } catch (IllegalArgumentException e) {
                player.sendMessage("§c无效的广告牌样式！可用类型: CENTER, FIXED, HORIZONTAL, VERTICAL。使用默认值CENTER。");
            }
        }
        
        // 创建需要在lambda中使用的final变量副本
        final byte finalOpacity = opacity;
        final Color finalBgColor = bgColor;
        final TextDisplay.TextAlignment finalAlignment = alignment;
        final Display.Billboard finalBillboard = billboard;
        
        // 创建全息文本
        TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class, display -> {
            display.setText(text.toString());
            display.setLineWidth(200);
            display.setTextOpacity(finalOpacity);
            display.setShadowed(true);
            display.setSeeThrough(false);
            
            if (finalBgColor != null) {
                display.setBackgroundColor(finalBgColor);
                display.setDefaultBackground(false);
            } else {
                // 未指定背景颜色时，设置为透明背景
                display.setDefaultBackground(false);
                // 完全透明，不设置背景色
            }
            
            display.setAlignment(finalAlignment);
            display.setBillboard(finalBillboard);
            display.setViewRange(50);
            display.setGravity(false);
        });
        
        if (textDisplay != null) {
            String hologramId = UUID.randomUUID().toString().substring(0, 8);
            textDisplay.getPersistentDataContainer().set(plugin.getKey("sagalib_hologram_id"), PersistentDataType.STRING, hologramId);
            playerHolograms.put(player.getName(), hologramId);
            
            player.sendMessage("§a成功创建全息文本！ID: " + hologramId);
        } else {
            player.sendMessage("§c创建全息文本失败！");
        }
    }
    
    /**
     * 移除全息文本
     * @param player 玩家
     */
    private void removeHologram(Player player) {
        String hologramId = playerHolograms.get(player.getName());
        if (hologramId == null) {
            player.sendMessage("§c你没有创建过全息文本！");
            return;
        }
        
        if (api.removeHologram(hologramId)) {
            playerHolograms.remove(player.getName());
            player.sendMessage("§a成功移除全息文本！");
        } else {
            player.sendMessage("§c移除全息文本失败！");
        }
    }
    
    /**
     * 更新全息文本
     * @param player 玩家
     * @param args 参数
     */
    private void updateHologram(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: /hologram update <text/location/style> <值>");
            return;
        }
        
        String hologramId = playerHolograms.get(player.getName());
        if (hologramId == null) {
            player.sendMessage("§c你没有创建过全息文本！");
            return;
        }
        
        String updateType = args[1].toLowerCase();
        
        switch (updateType) {
            case "text":
                updateText(player, hologramId, args);
                break;
            case "location":
                updateLocation(player, hologramId);
                break;
            case "style":
                updateStyle(player, hologramId, args);
                break;
            default:
                player.sendMessage("§c无效的更新类型！可用类型: text, location, style");
                break;
        }
    }
    
    /**
     * 更新全息文本内容
     * @param player 玩家
     * @param hologramId 全息文本ID
     * @param args 参数
     */
    private void updateText(Player player, String hologramId, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: /hologram update text <新文本>");
            return;
        }
        
        // 获取新文本内容
        StringBuilder text = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            text.append(args[i]).append(" ");
        }
        
        if (api.updateHologramText(hologramId, text.toString())) {
            player.sendMessage("§a成功更新全息文本内容！");
        } else {
            player.sendMessage("§c更新全息文本内容失败！");
        }
    }
    
    /**
     * 更新全息文本位置
     * @param player 玩家
     * @param hologramId 全息文本ID
     */
    private void updateLocation(Player player, String hologramId) {
        // 获取玩家当前位置
        Location location = player.getLocation().add(0, 1.5, 0);
        
        if (api.updateHologramLocation(hologramId, location)) {
            player.sendMessage("§a成功更新全息文本位置！");
        } else {
            player.sendMessage("§c更新全息文本位置失败！");
        }
    }
    
    /**
     * 更新全息文本样式
     * @param player 玩家
     * @param hologramId 全息文本ID
     * @param args 参数
     */
    private void updateStyle(Player player, String hologramId, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: /hologram update style <颜色代码>");
            return;
        }
        
        // 解析颜色代码
        String colorCode = args[2];
        Color color;
        
        try {
            if (colorCode.startsWith("#")) {
                // 十六进制颜色
                java.awt.Color awtColor = java.awt.Color.decode(colorCode);
                color = Color.fromRGB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
            } else {
                // 预设颜色
                switch (colorCode.toLowerCase()) {
                    case "red": color = Color.RED; break;
                    case "green": color = Color.GREEN; break;
                    case "blue": color = Color.BLUE; break;
                    case "yellow": color = Color.YELLOW; break;
                    case "purple": color = Color.PURPLE; break;
                    case "black": color = Color.BLACK; break;
                    case "white": color = Color.WHITE; break;
                    default: color = Color.BLACK; break;
                }
            }
            
            if (api.updateHologramStyle(hologramId, color, TextAlignment.CENTER, 200, (byte) 127, true, false)) {
                player.sendMessage("§a成功更新全息文本样式！");
            } else {
                player.sendMessage("§c更新全息文本样式失败！");
            }
        } catch (Exception e) {
            player.sendMessage("§c无效的颜色代码！");
        }
    }
    
    /**
     * 处理动画相关命令
     * @param player 玩家
     * @param args 参数
     */
    private void handleAnimation(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram animation <create/start/stop> [动画类型] [参数]");
            return;
        }
        
        String hologramId = playerHolograms.get(player.getName());
        if (hologramId == null) {
            player.sendMessage("§c你没有创建过全息文本！请先创建全息文本。");
            return;
        }
        
        String animationAction = args[1].toLowerCase();
        
        switch (animationAction) {
            case "create":
                createAnimation(player, args);
                break;
            case "start":
                startAnimation(player);
                break;
            case "stop":
                stopAnimation(player);
                break;
            default:
                player.sendMessage("§c无效的动画操作！可用操作: create, start, stop");
                break;
        }
    }
    
    /**
     * 创建动画
     * @param player 玩家
     * @param args 参数
     */
    private void createAnimation(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: /hologram animation create <scroll/fade/color> [参数]");
            return;
        }
        
        String hologramId = playerHolograms.get(player.getName());
        if (hologramId == null) {
            player.sendMessage("§c你没有创建过全息文本！请先创建全息文本。");
            return;
        }
        
        // 验证全息文本是否存在
        TextDisplay hologram = plugin.getHologramManager().getHologram(hologramId);
        if (hologram == null || !hologram.isValid()) {
            player.sendMessage("§c全息文本不存在或已失效！请重新创建全息文本。");
            playerHolograms.remove(player.getName());
            return;
        }
        
        String animationType = args[2].toLowerCase();
        HologramAnimation animation = null;
        
        try {
            switch (animationType) {
                case "scroll":
                    if (args.length < 5) {
                        player.sendMessage("§c用法: /hologram animation create scroll <文本> <速度>");
                        return;
                    }
                    
                    String text = args[3];
                    if (text.isEmpty()) {
                        player.sendMessage("§c文本不能为空！");
                        return;
                    }
                    
                    // 添加异常处理，确保速度参数是有效的整数
                    int speed;
                    try {
                        speed = Integer.parseInt(args[4]);
                        if (speed <= 0) {
                            player.sendMessage("§c速度必须大于0！");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage("§c无效的速度参数！必须是正整数。");
                        return;
                    }
                    
                    animation = api.createScrollingTextAnimation(text, speed, true);
                    break;
                    
                case "fade":
                    if (args.length < 6) {
                        player.sendMessage("§c用法: /hologram animation create fade <淡入时间> <保持时间> <淡出时间>");
                        return;
                    }
                    
                    // 添加异常处理，确保时间参数是有效的整数
                    int fadeIn, stay, fadeOut;
                    try {
                        fadeIn = Integer.parseInt(args[3]);
                        stay = Integer.parseInt(args[4]);
                        fadeOut = Integer.parseInt(args[5]);
                        
                        if (fadeIn < 0 || stay < 0 || fadeOut < 0) {
                            player.sendMessage("§c时间参数必须大于等于0！");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage("§c无效的时间参数！必须是整数。");
                        return;
                    }
                    
                    animation = api.createFadeAnimation(fadeIn, stay, fadeOut);
                    break;
                    
                case "color":
                    // 创建彩虹颜色列表
                    java.util.List<Color> colors = Arrays.asList(
                        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE
                    );
                    
                    // 添加异常处理，确保间隔参数是有效的整数
                    int interval = 20; // 默认值
                    boolean smooth = false; // 默认值
                    
                    if (args.length >= 4) {
                        try {
                            interval = Integer.parseInt(args[3]);
                            if (interval <= 0) {
                                player.sendMessage("§c间隔必须大于0！使用默认值20。");
                                interval = 20;
                            }
                        } catch (NumberFormatException e) {
                            player.sendMessage("§c无效的间隔参数！使用默认值20。");
                        }
                    }
                    
                    if (args.length >= 5) {
                        try {
                            smooth = Boolean.parseBoolean(args[4]);
                        } catch (Exception e) {
                            player.sendMessage("§c无效的平滑参数！使用默认值false。");
                        }
                    }
                    
                    animation = api.createColorChangeAnimation(colors, interval, smooth);
                    break;
                    
                default:
                    player.sendMessage("§c无效的动画类型！可用类型: scroll, fade, color");
                    return;
            }
            
            if (animation != null) {
                String oldAnimationId = playerAnimations.put(player.getName(), animation.getId());
                player.sendMessage("§a成功创建动画！ID: " + animation.getId());
                player.sendMessage("§a使用 /hologram animation start 启动动画");
            } else {
                player.sendMessage("§c创建动画失败！");
            }
        } catch (Exception e) {
            player.sendMessage("§c创建动画时发生错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 启动动画
     * @param player 玩家
     */
    private void startAnimation(Player player) {
        String hologramId = playerHolograms.get(player.getName());
        String animationId = playerAnimations.get(player.getName());
        
        if (hologramId == null) {
            player.sendMessage("§c你没有创建过全息文本！请先创建全息文本。");
            return;
        }
        
        if (animationId == null) {
            player.sendMessage("§c你没有创建过动画！请先创建动画。");
            return;
        }
        
        try {
            // 获取全息文本实体，确保它存在
            TextDisplay hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram == null || !hologram.isValid()) {
                player.sendMessage("§c全息文本不存在或已失效！请重新创建全息文本。");
                playerHolograms.remove(player.getName());
                return;
            }
            
            // 启动动画
            if (api.startHologramAnimation(hologramId, animationId)) {
                player.sendMessage("§a成功启动动画！");
            } else {
                player.sendMessage("§c启动动画失败！可能是动画ID无效或已被删除。");
                
                // 提供更多调试信息
                if (plugin.getConfigManager().getConfig().getBoolean("debug", false)) {
                    player.sendMessage("§7[调试] 全息ID: " + hologramId);
                    player.sendMessage("§7[调试] 动画ID: " + animationId);
                }
            }
        } catch (Exception e) {
            player.sendMessage("§c启动动画时发生错误：" + e.getMessage());
            if (plugin.getConfigManager().getConfig().getBoolean("debug", false)) {
                e.printStackTrace();
                player.sendMessage("§7[调试] 错误详情已打印到控制台。");
            }
        }
    }
    
    /**
     * 停止动画
     * @param player 玩家
     */
    private void stopAnimation(Player player) {
        String hologramId = playerHolograms.get(player.getName());
        String animationId = playerAnimations.get(player.getName());
        
        if (hologramId == null || animationId == null) {
            player.sendMessage("§c你没有创建过全息文本或动画！");
            return;
        }
        
        if (api.stopHologramAnimation(hologramId, animationId)) {
            player.sendMessage("§a成功停止动画！");
        } else {
            player.sendMessage("§c停止动画失败！");
        }
    }
    
    /**
     * 创建进度条
     * @param player 玩家
     * @param args 参数
     */
    private void createProgressBar(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram progress <进度值>");
            return;
        }
        
        try {
            double progress = Double.parseDouble(args[1]);
            if (progress < 0 || progress > 1) {
                player.sendMessage("§c进度值必须在0-1之间！");
                return;
            }
            
            // 获取玩家位置
            Location location = player.getLocation().add(0, 1.5, 0);
            
            // 创建进度条
            String id = api.createProgressBar(location, 3.0f, 0.5f, progress, Color.GREEN, Color.BLACK);
            
            if (id != null) {
                String oldId = playerHolograms.put(player.getName(), id);
                if (oldId != null) {
                    // 如果玩家已有全息文本，移除旧的
                    api.removeHologram(oldId);
                }
                
                player.sendMessage("§a成功创建进度条！ID: " + id);
            } else {
                player.sendMessage("§c创建进度条失败！");
            }
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的进度值！必须是0-1之间的小数。");
        }
    }
    
    /**
     * 创建圆形进度条
     * @param player 玩家
     * @param args 参数
     */
    private void createCircularProgressBar(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram circle <进度值>");
            return;
        }
        
        try {
            // 解析进度值
            double progress = Double.parseDouble(args[1]);
            if (progress < 0 || progress > 1) {
                player.sendMessage("§c进度值必须在0-1之间！");
                return;
            }
            
            // 在玩家面前创建圆形进度条
            Location location = player.getLocation().add(0, 1.5, 0);
            
            String progressBarId = plugin.getHologramManager().createCircularProgressBar(
                    location, 1.0f, progress, Color.GREEN, Color.fromRGB(30, 30, 30));
            
            if (progressBarId != null) {
                // 保存玩家对应的全息文本ID
                String oldId = playerHolograms.put(player.getName(), progressBarId);
                if (oldId != null) {
                    // 如果玩家已经有全息文本，先移除旧的
                    api.removeHologram(oldId);
                }
                
                player.sendMessage("§a成功创建圆形进度条！ID: " + progressBarId);
            } else {
                player.sendMessage("§c创建圆形进度条失败！");
            }
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的进度值！");
        }
    }
    
    /**
     * 创建垂直进度条
     * @param player 玩家
     * @param args 参数
     */
    private void createVerticalProgressBar(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram vertical <进度值>");
            return;
        }
        
        try {
            // 解析进度值
            double progress = Double.parseDouble(args[1]);
            if (progress < 0 || progress > 1) {
                player.sendMessage("§c进度值必须在0-1之间！");
                return;
            }
            
            // 在玩家面前创建垂直进度条
            Location location = player.getLocation().add(0, 1.5, 0);
            
            String progressBarId = plugin.getHologramManager().createVerticalProgressBar(
                    location, 0.5f, 2.0f, progress, Color.BLUE, Color.fromRGB(30, 30, 30));
            
            if (progressBarId != null) {
                // 保存玩家对应的全息文本ID
                String oldId = playerHolograms.put(player.getName(), progressBarId);
                if (oldId != null) {
                    // 如果玩家已经有全息文本，先移除旧的
                    api.removeHologram(oldId);
                }
                
                player.sendMessage("§a成功创建垂直进度条！ID: " + progressBarId);
            } else {
                player.sendMessage("§c创建垂直进度条失败！");
            }
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的进度值！");
        }
    }
    
    /**
     * 创建交互式面板
     * @param player 玩家
     * @param args 参数
     */
    private void createInteractivePanel(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: /hologram panel <类型>");
            player.sendMessage("§c可用类型: interactive, multiline, auto");
            return;
        }
        
        String panelType = args[1].toLowerCase();
        Location location = player.getLocation().add(0, 1.5, 0);
        String panelId = null;
        
        switch (panelType) {
            case "interactive":
                // 添加更明确的交互标签（包含玩家名称，确保唯一性）
                String interactionTag = "example_interaction_" + player.getName().toLowerCase();
                // 改进位置计算 - 在玩家面前2格距离，而不是上方
                Location panelLocation = player.getLocation().clone();
                // 获取玩家视线方向，并在其前方创建面板
                panelLocation.add(player.getLocation().getDirection().normalize().multiply(2));
                panelLocation.add(0, 1.0, 0); // 稍微上移一点，便于看到和点击
                
                // 先移除旧的全息文本
                String oldHologramId = playerHolograms.get(player.getName());
                if (oldHologramId != null) {
                    api.removeHologram(oldHologramId);
                    playerHolograms.remove(player.getName());
                }
                
                // 创建更明显的面板
                panelId = plugin.getHologramManager().createInteractivePanel(
                        panelLocation, 
                        "§l§e交互式面板", // 更明显的标题颜色
                        "§a点击此面板可以触发交互事件\n§f交互标签: §b" + interactionTag, 
                        interactionTag, 
                        Color.fromRGB(0, 0, 80)); // 更深的背景颜色，提高对比度
                
                if (panelId != null) {
                    // 保存玩家对应的全息文本ID
                    playerHolograms.put(player.getName(), panelId);
                    
                    // 获取面板实体并增强其可见性
                    TextDisplay panel = plugin.getHologramManager().getHologram(panelId);
                    if (panel != null) {
                        // 设置更大的显示尺寸
                        panel.setDisplayWidth(2.0f);
                        panel.setDisplayHeight(1.0f);
                        // 增加视野范围
                        panel.setViewRange(50.0f);
                        // 使用固定视图模式，使面板更容易点击
                        panel.setBillboard(Display.Billboard.FIXED);
                    }
                    
                    player.sendMessage("§a成功创建交互式面板！ID: " + panelId);
                    player.sendMessage("§e请用§b鼠标右键§e点击全息面板进行交互。");
                    player.sendMessage("§e交互标签：§f" + interactionTag);
                    
                    // 添加粒子效果指示面板位置
                    player.spawnParticle(org.bukkit.Particle.HEART, 
                        panelLocation, 20, 0.5, 0.5, 0.5, 0.1);
                } else {
                    player.sendMessage("§c创建交互式面板失败！");
                }
                break;
                
            case "multiline":
                String[] lines = {
                    "这是第一行内容",
                    "这是第二行内容",
                    "这是第三行内容",
                    "这是第四行内容"
                };
                
                panelId = plugin.getHologramManager().createMultilinePanel(
                        location, 
                        "多行文本面板", 
                        lines, 
                        Color.fromRGB(30, 0, 30));
                
                player.sendMessage("§a成功创建多行文本面板！");
                break;
                
            case "auto":
                // 创建一个自动更新的面板，显示玩家的当前坐标
                panelId = plugin.getHologramManager().createAutoUpdatePanel(
                        location,
                        "自动更新面板",
                        "正在加载...",
                        10, // 每10tick更新一次
                        () -> {
                            Location loc = player.getLocation();
                            return String.format(
                                "玩家: %s\n世界: %s\n坐标: %.2f, %.2f, %.2f",
                                player.getName(),
                                loc.getWorld().getName(),
                                loc.getX(), loc.getY(), loc.getZ()
                            );
                        },
                        Color.fromRGB(0, 30, 30)
                );
                
                player.sendMessage("§a成功创建自动更新面板！每0.5秒更新一次你的位置信息。");
                break;
                
            default:
                player.sendMessage("§c无效的面板类型！可用类型: interactive, multiline, auto");
                return;
        }
        
        if (panelId != null) {
            // 保存玩家对应的全息文本ID
            String oldId = playerHolograms.put(player.getName(), panelId);
            if (oldId != null) {
                // 如果玩家已经有全息文本，先移除旧的
                api.removeHologram(oldId);
            }
        } else {
            player.sendMessage("§c创建面板失败！");
        }
    }
    
    /**
     * 发送帮助信息
     * @param player 玩家
     */
    private void sendHelpMessage(Player player) {
        player.sendMessage("§6========== §e全息文本系统帮助 §6==========");
        player.sendMessage("§e/hologram create <文本> [背景色Hex] [透明度0-127] [广告牌样式] §7- 创建全息文本");
        player.sendMessage("§e/hologram remove §7- 移除全息文本");
        player.sendMessage("§e/hologram update text <文本> §7- 更新文本内容");
        player.sendMessage("§e/hologram update location §7- 更新文本位置");
        player.sendMessage("§e/hologram update style <颜色> §7- 更新文本样式");
        player.sendMessage("§e/hologram animation create scroll <文本> <速度> §7- 创建滚动动画");
        player.sendMessage("§e/hologram animation create fade <淡入时间> <保持时间> <淡出时间> §7- 创建淡入淡出动画");
        player.sendMessage("§e/hologram animation create color [间隔] [平滑true/false] §7- 创建颜色变换动画");
        player.sendMessage("§e/hologram animation start §7- 开始动画");
        player.sendMessage("§e/hologram animation stop §7- 停止动画");
        player.sendMessage("§e/hologram progress <进度值0-1> §7- 创建进度条");
        player.sendMessage("§e/hologram circle <进度值0-1> §7- 创建圆形进度条");
        player.sendMessage("§e/hologram vertical <进度值0-1> §7- 创建垂直进度条");
        player.sendMessage("§e/hologram panel interactive §7- 创建交互式面板");
        player.sendMessage("§e/hologram panel multiline §7- 创建多行文本面板");
        player.sendMessage("§e/hologram panel auto §7- 创建自动更新面板");
        player.sendMessage("§7--- §e背景色格式§7: #RRGGBB (例如 #FF0000 为红色)");
        player.sendMessage("§7--- §e广告牌样式§7: CENTER, FIXED, HORIZONTAL, VERTICAL");
        player.sendMessage("§7--- §e透明度范围§7: 0-127 (0为完全透明，127为完全不透明)");
    }
} 
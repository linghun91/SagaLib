package cn.i7mc.sagalib.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 颜色工具类
 * 提供颜色相关的功能
 */
public class ColorUtil {
    
    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
    private static final Map<String, ChatColor> COLOR_MAP = new HashMap<>();
    
    static {
        COLOR_MAP.put("&0", ChatColor.BLACK);
        COLOR_MAP.put("&1", ChatColor.DARK_BLUE);
        COLOR_MAP.put("&2", ChatColor.DARK_GREEN);
        COLOR_MAP.put("&3", ChatColor.DARK_AQUA);
        COLOR_MAP.put("&4", ChatColor.DARK_RED);
        COLOR_MAP.put("&5", ChatColor.DARK_PURPLE);
        COLOR_MAP.put("&6", ChatColor.GOLD);
        COLOR_MAP.put("&7", ChatColor.GRAY);
        COLOR_MAP.put("&8", ChatColor.DARK_GRAY);
        COLOR_MAP.put("&9", ChatColor.BLUE);
        COLOR_MAP.put("&a", ChatColor.GREEN);
        COLOR_MAP.put("&b", ChatColor.AQUA);
        COLOR_MAP.put("&c", ChatColor.RED);
        COLOR_MAP.put("&d", ChatColor.LIGHT_PURPLE);
        COLOR_MAP.put("&e", ChatColor.YELLOW);
        COLOR_MAP.put("&f", ChatColor.WHITE);
        COLOR_MAP.put("&l", ChatColor.BOLD);
        COLOR_MAP.put("&o", ChatColor.ITALIC);
        COLOR_MAP.put("&n", ChatColor.UNDERLINE);
        COLOR_MAP.put("&m", ChatColor.STRIKETHROUGH);
        COLOR_MAP.put("&r", ChatColor.RESET);
        COLOR_MAP.put("&k", ChatColor.MAGIC);
    }
    
    /**
     * 将颜色代码转换为Bukkit颜色
     * @param text 包含颜色代码的文本
     * @return 转换后的文本
     */
    public static String translateColor(String text) {
        if (text == null) return "";
        
        // 处理十六进制颜色
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();
        
        while (matcher.find()) {
            String color = matcher.group();
            matcher.appendReplacement(buffer, ChatColor.of(color).toString());
        }
        matcher.appendTail(buffer);
        
        // 处理传统颜色代码
        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }
    
    /**
     * 移除所有颜色代码
     * @param text 包含颜色代码的文本
     * @return 移除颜色代码后的文本
     */
    public static String stripColor(String text) {
        if (text == null) return "";
        // 先移除十六进制颜色代码
        text = HEX_PATTERN.matcher(text).replaceAll("");
        // 再移除传统颜色代码
        return text.replaceAll("&[0-9a-fk-or]", "");
    }
    
    /**
     * 获取染料颜色
     * @param color 颜色代码
     * @return 染料颜色
     */
    public static DyeColor getDyeColor(String color) {
        switch (color.toLowerCase()) {
            case "white": return DyeColor.WHITE;
            case "orange": return DyeColor.ORANGE;
            case "magenta": return DyeColor.MAGENTA;
            case "light_blue": return DyeColor.LIGHT_BLUE;
            case "yellow": return DyeColor.YELLOW;
            case "lime": return DyeColor.LIME;
            case "pink": return DyeColor.PINK;
            case "gray": return DyeColor.GRAY;
            case "light_gray": return DyeColor.LIGHT_GRAY;
            case "cyan": return DyeColor.CYAN;
            case "purple": return DyeColor.PURPLE;
            case "blue": return DyeColor.BLUE;
            case "brown": return DyeColor.BROWN;
            case "green": return DyeColor.GREEN;
            case "red": return DyeColor.RED;
            case "black": return DyeColor.BLACK;
            default: return DyeColor.WHITE;
        }
    }
    
    /**
     * 获取染色玻璃板
     * @param color 颜色代码
     * @return 染色玻璃板
     */
    public static Material getStainedGlassPane(String color) {
        switch (color.toLowerCase()) {
            case "white": return Material.WHITE_STAINED_GLASS_PANE;
            case "orange": return Material.ORANGE_STAINED_GLASS_PANE;
            case "magenta": return Material.MAGENTA_STAINED_GLASS_PANE;
            case "light_blue": return Material.LIGHT_BLUE_STAINED_GLASS_PANE;
            case "yellow": return Material.YELLOW_STAINED_GLASS_PANE;
            case "lime": return Material.LIME_STAINED_GLASS_PANE;
            case "pink": return Material.PINK_STAINED_GLASS_PANE;
            case "gray": return Material.GRAY_STAINED_GLASS_PANE;
            case "light_gray": return Material.LIGHT_GRAY_STAINED_GLASS_PANE;
            case "cyan": return Material.CYAN_STAINED_GLASS_PANE;
            case "purple": return Material.PURPLE_STAINED_GLASS_PANE;
            case "blue": return Material.BLUE_STAINED_GLASS_PANE;
            case "brown": return Material.BROWN_STAINED_GLASS_PANE;
            case "green": return Material.GREEN_STAINED_GLASS_PANE;
            case "red": return Material.RED_STAINED_GLASS_PANE;
            case "black": return Material.BLACK_STAINED_GLASS_PANE;
            default: return Material.WHITE_STAINED_GLASS_PANE;
        }
    }
    
    /**
     * 获取染色玻璃
     * @param color 颜色代码
     * @return 染色玻璃
     */
    public static Material getStainedGlass(String color) {
        switch (color.toLowerCase()) {
            case "white": return Material.WHITE_STAINED_GLASS;
            case "orange": return Material.ORANGE_STAINED_GLASS;
            case "magenta": return Material.MAGENTA_STAINED_GLASS;
            case "light_blue": return Material.LIGHT_BLUE_STAINED_GLASS;
            case "yellow": return Material.YELLOW_STAINED_GLASS;
            case "lime": return Material.LIME_STAINED_GLASS;
            case "pink": return Material.PINK_STAINED_GLASS;
            case "gray": return Material.GRAY_STAINED_GLASS;
            case "light_gray": return Material.LIGHT_GRAY_STAINED_GLASS;
            case "cyan": return Material.CYAN_STAINED_GLASS;
            case "purple": return Material.PURPLE_STAINED_GLASS;
            case "blue": return Material.BLUE_STAINED_GLASS;
            case "brown": return Material.BROWN_STAINED_GLASS;
            case "green": return Material.GREEN_STAINED_GLASS;
            case "red": return Material.RED_STAINED_GLASS;
            case "black": return Material.BLACK_STAINED_GLASS;
            default: return Material.WHITE_STAINED_GLASS;
        }
    }
    
    /**
     * 获取染色羊毛
     * @param color 颜色代码
     * @return 染色羊毛
     */
    public static Material getWool(String color) {
        switch (color.toLowerCase()) {
            case "white": return Material.WHITE_WOOL;
            case "orange": return Material.ORANGE_WOOL;
            case "magenta": return Material.MAGENTA_WOOL;
            case "light_blue": return Material.LIGHT_BLUE_WOOL;
            case "yellow": return Material.YELLOW_WOOL;
            case "lime": return Material.LIME_WOOL;
            case "pink": return Material.PINK_WOOL;
            case "gray": return Material.GRAY_WOOL;
            case "light_gray": return Material.LIGHT_GRAY_WOOL;
            case "cyan": return Material.CYAN_WOOL;
            case "purple": return Material.PURPLE_WOOL;
            case "blue": return Material.BLUE_WOOL;
            case "brown": return Material.BROWN_WOOL;
            case "green": return Material.GREEN_WOOL;
            case "red": return Material.RED_WOOL;
            case "black": return Material.BLACK_WOOL;
            default: return Material.WHITE_WOOL;
        }
    }
} 
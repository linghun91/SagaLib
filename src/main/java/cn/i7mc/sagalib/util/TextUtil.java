package cn.i7mc.sagalib.util;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本工具类
 * 提供文本处理相关的功能
 */
public class TextUtil {
    
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("%([^%]+)%");
    
    /**
     * 文本对齐方式
     */
    public enum TextAlignment {
        LEFT,
        CENTER,
        RIGHT
    }
    
    /**
     * 替换占位符
     * @param text 文本
     * @param replacements 替换内容
     * @return 替换后的文本
     */
    public static String replacePlaceholders(String text, Object... replacements) {
        if (text == null || replacements == null || replacements.length == 0) {
            return text;
        }
        
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 >= replacements.length) {
                break;
            }
            
            String placeholder = "%" + replacements[i] + "%";
            String replacement = String.valueOf(replacements[i + 1]);
            text = text.replace(placeholder, replacement);
        }
        
        return text;
    }
    
    /**
     * 获取占位符列表
     * @param text 文本
     * @return 占位符列表
     */
    public static List<String> getPlaceholders(String text) {
        List<String> placeholders = new ArrayList<>();
        if (text == null) {
            return placeholders;
        }
        
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(text);
        while (matcher.find()) {
            placeholders.add(matcher.group(1));
        }
        
        return placeholders;
    }
    
    /**
     * 分割文本
     * @param text 文本
     * @param maxLength 最大长度
     * @return 分割后的文本列表
     */
    public static List<String> splitText(String text, int maxLength) {
        List<String> lines = new ArrayList<>();
        if (text == null || maxLength <= 0) {
            return lines;
        }
        
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (currentLine.length() + word.length() + 1 <= maxLength) {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                if (currentLine.length() > 0) {
                    lines.add(currentLine.toString());
                }
                currentLine = new StringBuilder(word);
            }
        }
        
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        
        return lines;
    }
    
    /**
     * 获取文本长度（不包含颜色代码）
     * @param text 文本
     * @return 文本长度
     */
    public static int getLength(String text) {
        if (text == null) {
            return 0;
        }
        // 移除所有颜色代码
        text = text.replaceAll("&[0-9a-fk-or]", "");
        text = text.replaceAll("#[a-fA-F0-9]{6}", "");
        return text.length();
    }
    
    /**
     * 截断文本
     * @param text 文本
     * @param maxLength 最大长度
     * @param suffix 后缀
     * @return 截断后的文本
     */
    public static String truncate(String text, int maxLength, String suffix) {
        if (text == null || maxLength <= 0) {
            return text;
        }
        
        String stripped = ChatColor.stripColor(text);
        if (stripped.length() <= maxLength) {
            return text;
        }
        
        String truncated = stripped.substring(0, maxLength);
        String lastColor = "";
        
        // 获取最后一个颜色代码
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '&') {
                lastColor = "&" + text.charAt(i + 1);
            }
        }
        
        return truncated + lastColor + (suffix != null ? suffix : "");
    }
    
    /**
     * 重复文本
     * @param text 文本
     * @param count 重复次数
     * @return 重复后的文本
     */
    public static String repeat(String text, int count) {
        if (text == null || count <= 0) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(text);
        }
        
        return result.toString();
    }
    
    /**
     * 居中文本
     * @param text 文本
     * @param width 总宽度
     * @return 居中后的文本
     */
    public static String center(String text, int width) {
        if (text == null || width <= 0) {
            return text;
        }
        
        int textLength = getLength(text);
        if (textLength >= width) {
            return text;
        }
        
        int padding = (width - textLength) / 2;
        return repeat(" ", padding) + text + repeat(" ", width - textLength - padding);
    }
    
    /**
     * 对齐文本
     * @param text 文本
     * @param width 总宽度
     * @param alignment 对齐方式
     * @return 对齐后的文本
     */
    public static String align(String text, int width, TextAlignment alignment) {
        if (text == null || width <= 0) {
            return text;
        }
        
        int textLength = getLength(text);
        if (textLength >= width) {
            return text;
        }
        
        switch (alignment) {
            case LEFT:
                return text + repeat(" ", width - textLength);
            case RIGHT:
                return repeat(" ", width - textLength) + text;
            case CENTER:
                return center(text, width);
            default:
                return text;
        }
    }
} 
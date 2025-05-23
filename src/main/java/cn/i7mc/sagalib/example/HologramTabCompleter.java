package cn.i7mc.sagalib.example;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 全息文本命令Tab补全器
 */
public class HologramTabCompleter implements TabCompleter {
    
    private static final List<String> MAIN_COMMANDS = Arrays.asList(
            "create", "remove", "update", "animation", "progress", "circle", "vertical", "panel");
    
    private static final List<String> ANIMATION_TYPES = Arrays.asList(
            "scroll", "fade", "color", "stop");
    
    private static final List<String> PANEL_TYPES = Arrays.asList(
            "interactive", "multiline", "auto");
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            return MAIN_COMMANDS;
        }
        
        if (args.length == 1) {
            return filterStartsWith(MAIN_COMMANDS, args[0]);
        }
        
        // 子命令补全
        String subCommand = args[0].toLowerCase();
        switch (subCommand) {
            case "animation":
                if (args.length == 2) {
                    return filterStartsWith(ANIMATION_TYPES, args[1]);
                }
                break;
            case "panel":
                if (args.length == 2) {
                    return filterStartsWith(PANEL_TYPES, args[1]);
                }
                break;
            case "create":
                if (args.length == 3) {
                    return Arrays.asList("#FF0000", "#00FF00", "#0000FF", "#FFFF00");
                } else if (args.length == 5) {
                    return Arrays.asList("CENTER", "FIXED", "HORIZONTAL", "VERTICAL");
                }
                break;
        }
        
        return Collections.emptyList();
    }
    
    /**
     * 过滤以某个前缀开头的选项
     * @param options 选项列表
     * @param prefix 前缀
     * @return 过滤后的列表
     */
    private List<String> filterStartsWith(List<String> options, String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return options;
        }
        
        prefix = prefix.toLowerCase();
        List<String> result = new ArrayList<>();
        
        for (String option : options) {
            if (option.toLowerCase().startsWith(prefix)) {
                result.add(option);
            }
        }
        
        return result;
    }
} 
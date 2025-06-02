package cn.i7mc.sagalib.command;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.example.GuiExample;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 示例命令类
 * 用于展示SagaLib的基本功能
 */
public class ExampleCommand implements CommandExecutor, TabCompleter {
    
    private final List<String> subCommands = Arrays.asList("items", "text", "color", "animation");
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "该命令只能由玩家执行！");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            sendHelp(player);
            return true;
        }
        
        switch (args[0].toLowerCase()) {
            case "items" -> {
                List<Material> items = Arrays.asList(
                    Material.DIAMOND,
                    Material.EMERALD,
                    Material.GOLD_INGOT,
                    Material.IRON_INGOT,
                    Material.COAL,
                    Material.REDSTONE,
                    Material.LAPIS_LAZULI
                );
                GuiExample.openPagedItemList(player, items);
            }
            case "text" -> GuiExample.openTextEffectGui(player, "&6文本效果示例");
            case "color" -> GuiExample.openColorEffectGui(player);
            case "animation" -> GuiExample.openAnimationGui(player);
            default -> sendHelp(player);
        }
        
        return true;
    }
    
    private void sendHelp(Player player) {
        player.sendMessage(ChatColor.GOLD + "=== SagaLib 示例命令 ===");
        player.sendMessage(ChatColor.YELLOW + "/example items " + ChatColor.WHITE + "- 打开分页物品列表");
        player.sendMessage(ChatColor.YELLOW + "/example text " + ChatColor.WHITE + "- 打开文本效果示例");
        player.sendMessage(ChatColor.YELLOW + "/example color " + ChatColor.WHITE + "- 打开颜色效果示例");
        player.sendMessage(ChatColor.YELLOW + "/example animation " + ChatColor.WHITE + "- 打开动画效果示例");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        if (args.length == 1) {
            String input = args[0].toLowerCase();
            List<String> completions = new ArrayList<>();
            
            for (String subCommand : subCommands) {
                if (subCommand.startsWith(input)) {
                    completions.add(subCommand);
                }
            }
            
            return completions;
        }
        
        return null;
    }
} 
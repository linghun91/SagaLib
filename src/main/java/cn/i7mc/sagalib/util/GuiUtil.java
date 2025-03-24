package cn.i7mc.sagalib.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * GUI工具类
 * 提供GUI相关的通用方法
 */
public class GuiUtil {
    
    /**
     * 创建一个新的GUI
     * @param title GUI标题
     * @param size GUI大小
     * @return 创建的GUI
     */
    public static Inventory createGui(String title, int size) {
        return Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
    }

    /**
     * 填充GUI边框
     * @param inventory 要填充的GUI
     * @param material 填充材质
     */
    public static void fillBorder(Inventory inventory, Material material) {
        fillBorder(inventory, new ItemBuilder(material).build());
    }

    /**
     * 填充GUI边框
     * @param inventory 要填充的GUI
     * @param item 填充物品
     */
    public static void fillBorder(Inventory inventory, ItemStack item) {
        int size = inventory.getSize();
        int rows = size / 9;

        // 填充第一行和最后一行
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, item);
            inventory.setItem(size - 9 + i, item);
        }

        // 填充左右两边
        for (int i = 1; i < rows - 1; i++) {
            inventory.setItem(i * 9, item);
            inventory.setItem(i * 9 + 8, item);
        }
    }

    /**
     * 填充GUI
     * @param inventory 要填充的GUI
     * @param material 填充材质
     */
    public static void fillGui(Inventory inventory, Material material) {
        fillGui(inventory, new ItemBuilder(material).build());
    }

    /**
     * 填充GUI
     * @param inventory 要填充的GUI
     * @param item 填充物品
     */
    public static void fillGui(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, item);
        }
    }

    /**
     * 创建占位符物品
     * @return 占位符物品
     */
    public static ItemStack createPlaceholder() {
        return new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setName(" ")
                .build();
    }

    /**
     * 创建返回按钮
     * @return 返回按钮物品
     */
    public static ItemStack createBackButton() {
        return new ItemBuilder(Material.ARROW)
                .setName("&c返回")
                .setLore(Arrays.asList(
                        "&7点击返回上一页"
                ))
                .build();
    }

    /**
     * 创建关闭按钮
     * @return 关闭按钮物品
     */
    public static ItemStack createCloseButton() {
        return new ItemBuilder(Material.BARRIER)
                .setName("&c关闭")
                .setLore(Arrays.asList(
                        "&7点击关闭界面"
                ))
                .build();
    }
} 
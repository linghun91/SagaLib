package cn.i7mc.sagalib.gui.style;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.util.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * 默认GUI样式实现
 * 提供基础的GUI样式
 */
public class DefaultStyle implements GuiStyle {
    
    private final String name;
    private final String description;
    private final Material borderMaterial;
    private final Material backgroundMaterial;
    private final String borderName;
    private final String backgroundName;
    
    /**
     * 创建一个默认样式
     */
    public DefaultStyle() {
        // 从配置文件读取样式信息
        this.name = SagaLib.getAPI().getMessageConfig().getString("gui.style.default_name", "Default Style");
        this.description = SagaLib.getAPI().getMessageConfig().getString("gui.style.default_description", "A simple and elegant default style");
        this.borderMaterial = Material.BLACK_STAINED_GLASS_PANE;
        this.backgroundMaterial = Material.GRAY_STAINED_GLASS_PANE;
        this.borderName = SagaLib.getAPI().getMessageConfig().getString("gui.style.border_text", "&8Border");
        this.backgroundName = SagaLib.getAPI().getMessageConfig().getString("gui.style.background_text", "&7Background");
    }
    
    /**
     * 创建一个自定义样式
     * @param name 样式名称
     * @param description 样式描述
     * @param borderMaterial 边框材质
     * @param backgroundMaterial 背景材质
     * @param borderName 边框名称
     * @param backgroundName 背景名称
     */
    public DefaultStyle(String name, String description, 
                       Material borderMaterial, Material backgroundMaterial,
                       String borderName, String backgroundName) {
        this.name = name;
        this.description = description;
        this.borderMaterial = borderMaterial;
        this.backgroundMaterial = backgroundMaterial;
        this.borderName = borderName;
        this.backgroundName = backgroundName;
    }
    
    @Override
    public Material getBorderMaterial() {
        return borderMaterial;
    }
    
    @Override
    public Material getBackgroundMaterial() {
        return backgroundMaterial;
    }
    
    @Override
    public Material getButtonMaterial() {
        return Material.WHITE_STAINED_GLASS_PANE;
    }
    
    @Override
    public String getTitleColor(Player player) {
        return "&f";
    }
    
    @Override
    public String getButtonNameColor(Player player) {
        return "&f";
    }
    
    @Override
    public String getButtonLoreColor(Player player) {
        return "&7";
    }
    
    @Override
    public ItemStack createBorderItem(Player player) {
        ItemStack item = new ItemStack(borderMaterial);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(SagaLib.getAPI().parsePlaceholders(player, 
                    ColorUtil.translateColor(borderName)));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    @Override
    public ItemStack createBackgroundItem(Player player) {
        ItemStack item = new ItemStack(backgroundMaterial);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(SagaLib.getAPI().parsePlaceholders(player, 
                    ColorUtil.translateColor(backgroundName)));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    @Override
    public ItemStack createButtonItem(Player player, String name) {
        ItemStack item = new ItemStack(getButtonMaterial());
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(SagaLib.getAPI().parsePlaceholders(player, 
                    ColorUtil.translateColor(name)));
            item.setItemMeta(meta);
        }
        return item;
    }
    
    @Override
    public ItemStack getBackgroundItem(Player player) {
        return createBackgroundItem(player);
    }
    
    @Override
    public ItemStack getBorderItem(Player player) {
        return createBorderItem(player);
    }
    
    @Override
    public String getName(Player player) {
        return SagaLib.getAPI().parsePlaceholders(player, name);
    }
    
    @Override
    public String getDescription(Player player) {
        return SagaLib.getAPI().parsePlaceholders(player, description);
    }
} 
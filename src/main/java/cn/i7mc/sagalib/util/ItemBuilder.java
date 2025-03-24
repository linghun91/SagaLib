package cn.i7mc.sagalib.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 物品构建器工具类
 * 用于快速构建带有自定义属性的物品
 */
public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * 创建一个新的物品构建器
     * @param material 物品材质
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * 创建一个新的物品构建器
     * @param item 已有物品
     */
    public ItemBuilder(ItemStack item) {
        this.item = item.clone();
        this.meta = this.item.getItemMeta();
    }

    /**
     * 设置物品名称
     * @param name 物品名称
     * @return 物品构建器
     */
    public ItemBuilder setName(String name) {
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    /**
     * 设置物品描述
     * @param lore 物品描述
     * @return 物品构建器
     */
    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    /**
     * 设置物品描述
     * @param lore 物品描述列表
     * @return 物品构建器
     */
    public ItemBuilder setLore(List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        for (String line : lore) {
            coloredLore.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        meta.setLore(coloredLore);
        return this;
    }

    /**
     * 添加物品描述
     * @param lore 物品描述
     * @return 物品构建器
     */
    public ItemBuilder addLore(String... lore) {
        List<String> currentLore = meta.getLore();
        if (currentLore == null) {
            currentLore = new ArrayList<>();
        }
        for (String line : lore) {
            currentLore.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        meta.setLore(currentLore);
        return this;
    }

    /**
     * 设置物品数量
     * @param amount 物品数量
     * @return 物品构建器
     */
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    /**
     * 添加附魔
     * @param enchantment 附魔类型
     * @param level 附魔等级
     * @return 物品构建器
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * 添加多个附魔
     * @param enchantments 附魔Map
     * @return 物品构建器
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach(this::addEnchantment);
        return this;
    }

    /**
     * 添加物品标志
     * @param flags 物品标志
     * @return 物品构建器
     */
    public ItemBuilder addItemFlags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    /**
     * 设置物品是否不可破坏
     * @param unbreakable 是否不可破坏
     * @return 物品构建器
     */
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * 设置物品自定义模型数据
     * @param modelData 自定义模型数据
     * @return 物品构建器
     */
    public ItemBuilder setCustomModelData(Integer modelData) {
        meta.setCustomModelData(modelData);
        return this;
    }

    /**
     * 构建物品
     * @return 构建完成的物品
     */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
} 
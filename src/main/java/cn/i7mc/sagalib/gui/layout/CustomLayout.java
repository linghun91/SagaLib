package cn.i7mc.sagalib.gui.layout;

import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.holder.GuiHolder;
import org.bukkit.ChatColor;

/**
 * 自定义布局实现
 * 支持自定义行数、列数、按钮间距和边框宽度
 */
public class CustomLayout implements GuiLayout {
    private final int rows;
    private final int columns;
    private final int buttonSpacing;
    private final int borderWidth;
    private final String name;
    private final String description;

    /**
     * 创建自定义布局
     * @param rows 行数
     * @param columns 列数
     * @param buttonSpacing 按钮间距
     * @param borderWidth 边框宽度
     */
    public CustomLayout(int rows, int columns, int buttonSpacing, int borderWidth) {
        this.rows = rows;
        this.columns = columns;
        this.buttonSpacing = buttonSpacing;
        this.borderWidth = borderWidth;
        this.name = "CustomLayout";
        this.description = String.format("自定义布局 (行:%d, 列:%d, 间距:%d, 边框:%d)", 
            rows, columns, buttonSpacing, borderWidth);
    }

    @Override
    public void apply(GuiHolder holder, GuiButton[] buttons) {
        if (buttons == null || buttons.length == 0) return;

        int currentSlot = 0;
        int buttonIndex = 0;
        int maxButtons = (rows - 2 * borderWidth) * (columns - 2 * borderWidth);

        // 应用边框
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i < borderWidth || i >= rows - borderWidth || 
                    j < borderWidth || j >= columns - borderWidth) {
                    holder.setItem(currentSlot, null);
                }
                currentSlot++;
            }
        }

        // 重置currentSlot到内容区域
        currentSlot = borderWidth * columns + borderWidth;

        // 放置按钮
        while (buttonIndex < buttons.length && buttonIndex < maxButtons) {
            GuiButton button = buttons[buttonIndex];
            holder.addButton(currentSlot, button);

            // 移动到下一个位置
            currentSlot += buttonSpacing + 1;
            if (currentSlot % columns >= columns - borderWidth) {
                currentSlot += 2 * borderWidth;
            }
            buttonIndex++;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
} 
package cn.i7mc.sagalib.gui.layout;

import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.holder.GuiHolder;

/**
 * 网格布局实现
 * 将按钮按照网格方式排列
 */
public class GridLayout implements GuiLayout {
    
    private final int columns;
    private final int startRow;
    private final int startColumn;
    
    /**
     * 创建一个网格布局
     * @param columns 列数
     * @param startRow 起始行
     * @param startColumn 起始列
     */
    public GridLayout(int columns, int startRow, int startColumn) {
        this.columns = columns;
        this.startRow = startRow;
        this.startColumn = startColumn;
    }
    
    @Override
    public void apply(GuiHolder holder, GuiButton[] buttons) {
        if (buttons == null || buttons.length == 0) {
            return;
        }
        
        int currentRow = startRow;
        int currentColumn = startColumn;
        
        for (GuiButton button : buttons) {
            if (button == null) {
                continue;
            }
            
            int slot = currentRow * 9 + currentColumn;
            button.setSlot(slot);
            holder.addButton(slot, button);
            
            currentColumn++;
            if (currentColumn >= startColumn + columns) {
                currentColumn = startColumn;
                currentRow++;
            }
        }
    }
    
    @Override
    public String getName() {
        return "网格布局";
    }
    
    @Override
    public String getDescription() {
        return String.format("从第%d行第%d列开始,每行%d列的网格布局", startRow + 1, startColumn + 1, columns);
    }
} 
package cn.i7mc.sagalib.gui.layout;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.holder.GuiHolder;
import cn.i7mc.sagalib.gui.button.SimpleButton;
import cn.i7mc.sagalib.config.LanguageManager;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页布局实现
 * 将按钮按照分页方式排列，并添加翻页按钮
 */
public class PaginationLayout implements GuiLayout {
    
    private final int itemsPerPage;
    private final int startRow;
    private final int startColumn;
    private final int columns;
    private int currentPage;
    private List<GuiButton> allButtons;
    
    /**
     * 创建一个分页布局
     * @param itemsPerPage 每页显示数量
     * @param columns 列数
     * @param startRow 起始行
     * @param startColumn 起始列
     */
    public PaginationLayout(int itemsPerPage, int columns, int startRow, int startColumn) {
        this.itemsPerPage = itemsPerPage;
        this.columns = columns;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.currentPage = 0;
        this.allButtons = new ArrayList<>();
    }
    
    @Override
    public void apply(GuiHolder holder, GuiButton[] buttons) {
        if (buttons == null || buttons.length == 0) {
            return;
        }
        
        // 保存所有按钮
        allButtons.clear();
        for (GuiButton button : buttons) {
            if (button != null) {
                allButtons.add(button);
            }
        }
        
        // 计算总页数
        int totalPages = (int) Math.ceil((double) allButtons.size() / itemsPerPage);
        if (currentPage >= totalPages) {
            currentPage = Math.max(0, totalPages - 1);
        }
        
        // 清除当前页面
        for (int i = startRow * 9 + startColumn; i < (startRow + (itemsPerPage / columns)) * 9 + startColumn; i++) {
            holder.clearItem(i);
        }
        
        // 显示当前页的按钮
        int startIndex = currentPage * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, allButtons.size());
        int currentRow = startRow;
        int currentColumn = startColumn;
        
        for (int i = startIndex; i < endIndex; i++) {
            GuiButton button = allButtons.get(i);
            int slot = currentRow * 9 + currentColumn;
            button.setSlot(slot);
            holder.addButton(slot, button);
            
            currentColumn++;
            if (currentColumn >= startColumn + columns) {
                currentColumn = startColumn;
                currentRow++;
            }
        }
        
        // 添加翻页按钮
        addNavigationButtons(holder, totalPages);
    }
    
    /**
     * 添加导航按钮
     * @param holder GUI容器
     * @param totalPages 总页数
     */
    private void addNavigationButtons(GuiHolder holder, int totalPages) {
        LanguageManager languageManager = SagaLib.getInstance().getLanguageManager();

        // 上一页按钮
        if (currentPage > 0) {
            String prevText = languageManager.getText("gui.pagination.previous_page");
            SimpleButton prevButton = new SimpleButton(Material.ARROW, prevText)
                    .setClickHandler(event -> {
                        currentPage--;
                        apply(holder, allButtons.toArray(new GuiButton[0]));
                    });
            prevButton.setSlot(holder.getSize() - 9);
            holder.addButton(holder.getSize() - 9, prevButton);
        }

        // 下一页按钮
        if (currentPage < totalPages - 1) {
            String nextText = languageManager.getText("gui.pagination.next_page");
            SimpleButton nextButton = new SimpleButton(Material.ARROW, nextText)
                    .setClickHandler(event -> {
                        currentPage++;
                        apply(holder, allButtons.toArray(new GuiButton[0]));
                    });
            nextButton.setSlot(holder.getSize() - 1);
            holder.addButton(holder.getSize() - 1, nextButton);
        }

        // 页码显示
        String pageInfoFormat = languageManager.getText("gui.pagination.page_info");
        SimpleButton pageButton = new SimpleButton(Material.PAPER,
                String.format(pageInfoFormat, currentPage + 1, totalPages));
        pageButton.setSlot(holder.getSize() - 5);
        holder.addButton(holder.getSize() - 5, pageButton);
    }
    
    /**
     * 获取当前页码
     * @return 当前页码
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    /**
     * 设置当前页码
     * @param page 页码
     */
    public void setCurrentPage(int page) {
        this.currentPage = page;
    }
    
    @Override
    public String getName() {
        LanguageManager languageManager = SagaLib.getInstance().getLanguageManager();
        return languageManager.getText("gui.pagination.layout_name");
    }

    @Override
    public String getDescription() {
        LanguageManager languageManager = SagaLib.getInstance().getLanguageManager();
        String descriptionFormat = languageManager.getText("gui.pagination.layout_description");
        return String.format(descriptionFormat, itemsPerPage, startRow + 1, startColumn + 1);
    }
} 
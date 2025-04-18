package cn.i7mc.sagalib.gui.layout;

import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.holder.GuiHolder;

/**
 * GUI布局接口
 * 定义GUI的布局方式
 */
public interface GuiLayout {
    /**
     * 应用布局
     * @param holder GUI容器
     * @param buttons 按钮列表
     */
    void apply(GuiHolder holder, GuiButton[] buttons);

    /**
     * 获取布局名称
     * @return 布局名称
     */
    String getName();

    /**
     * 获取布局描述
     * @return 布局描述
     */
    String getDescription();
} 
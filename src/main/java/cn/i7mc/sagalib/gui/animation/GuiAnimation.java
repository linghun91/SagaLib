package cn.i7mc.sagalib.gui.animation;

import cn.i7mc.sagalib.gui.holder.GuiHolder;

/**
 * GUI动画接口
 * 定义GUI的动画效果
 */
public interface GuiAnimation {
    /**
     * 开始动画
     * @param holder GUI容器
     */
    void start(GuiHolder holder);

    /**
     * 停止动画
     */
    void stop();

    /**
     * 获取动画名称
     * @return 动画名称
     */
    String getName();

    /**
     * 获取动画描述
     * @return 动画描述
     */
    String getDescription();

    /**
     * 是否正在运行
     * @return 是否正在运行
     */
    boolean isRunning();

    /**
     * 获取动画状态
     * @return 动画状态(START/STOP/PAUSE/RESUME)
     */
    String getStatus();
} 
package cn.i7mc.sagalib.hologram.animation;

import org.bukkit.entity.TextDisplay;

/**
 * 全息文本动画接口
 * 定义全息动画的基本行为
 */
public interface HologramAnimation {
    
    /**
     * 开始动画
     * @param textDisplay 目标全息文本实体
     */
    void start(TextDisplay textDisplay);
    
    /**
     * 停止动画
     * @param textDisplay 目标全息文本实体
     */
    void stop(TextDisplay textDisplay);
    
    /**
     * 暂停动画
     * @param textDisplay 目标全息文本实体
     */
    void pause(TextDisplay textDisplay);
    
    /**
     * 恢复动画
     * @param textDisplay 目标全息文本实体
     */
    void resume(TextDisplay textDisplay);
    
    /**
     * 获取动画ID
     * @return 动画ID
     */
    String getId();
    
    /**
     * 获取动画状态
     * @return 动画状态 (START, STOP, PAUSE, RESUME)
     */
    AnimationStatus getStatus();
    
    /**
     * 动画状态枚举
     */
    enum AnimationStatus {
        START,
        STOP,
        PAUSE,
        RESUME
    }
} 
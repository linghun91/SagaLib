package cn.i7mc.sagalib.hologram.animation;

import cn.i7mc.sagalib.SagaLib;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

/**
 * 滚动文本动画
 * 实现文本滚动效果
 */
public class ScrollingTextAnimation implements HologramAnimation {
    
    private final SagaLib plugin;
    private final String id;
    private final String text;
    private final int speed;
    private final boolean loop;
    private AnimationStatus status = AnimationStatus.STOP;
    private BukkitTask task;
    
    /**
     * 创建滚动文本动画
     * @param plugin 插件实例
     * @param text 滚动文本
     * @param speed 滚动速度(tick间隔)
     * @param loop 是否循环
     */
    public ScrollingTextAnimation(SagaLib plugin, String text, int speed, boolean loop) {
        this.plugin = plugin;
        this.text = text;
        this.speed = Math.max(1, speed);
        this.loop = loop;
        this.id = "scroll_" + UUID.randomUUID().toString().substring(0, 6);
    }
    
    @Override
    public void start(TextDisplay textDisplay) {
        if (status == AnimationStatus.START) return;
        
        // 如果文本过短，不需要滚动
        if (text == null || text.length() <= 3) {
            textDisplay.setText(text);
            return;
        }
        
        // 取消之前的任务
        if (task != null) {
            task.cancel();
        }
        
        status = AnimationStatus.START;
        
        // 构建带填充的滚动文本
        final String fullText = text + "   " + text;
        final int length = fullText.length();
        final int[] position = {0};
        
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (textDisplay.isDead() || !textDisplay.isValid()) {
                    cancel();
                    return;
                }
                
                // 计算当前窗口内的文本
                int endPos = Math.min(position[0] + 20, length);
                String visibleText = fullText.substring(position[0], endPos);
                
                // 更新全息文本
                textDisplay.setText(visibleText);
                
                // 移动窗口位置
                position[0]++;
                
                // 如果到达文本末尾，根据循环设置重置
                if (position[0] >= text.length()) {
                    if (loop) {
                        position[0] = 0;
                    } else {
                        cancel();
                        status = AnimationStatus.STOP;
                    }
                }
            }
        }.runTaskTimer(plugin, 0, speed);
    }
    
    @Override
    public void stop(TextDisplay textDisplay) {
        if (task != null) {
            task.cancel();
            task = null;
        }
        
        // 重置为初始文本
        if (textDisplay != null && textDisplay.isValid()) {
            textDisplay.setText(text);
        }
        
        status = AnimationStatus.STOP;
    }
    
    @Override
    public void pause(TextDisplay textDisplay) {
        if (status != AnimationStatus.START) return;
        
        if (task != null) {
            task.cancel();
            task = null;
        }
        
        status = AnimationStatus.PAUSE;
    }
    
    @Override
    public void resume(TextDisplay textDisplay) {
        if (status != AnimationStatus.PAUSE) return;
        
        // 重新启动动画
        start(textDisplay);
        status = AnimationStatus.START;
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public AnimationStatus getStatus() {
        return status;
    }
} 
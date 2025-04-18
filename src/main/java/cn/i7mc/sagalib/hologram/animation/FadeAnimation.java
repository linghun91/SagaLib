package cn.i7mc.sagalib.hologram.animation;

import cn.i7mc.sagalib.SagaLib;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

/**
 * 淡入淡出动画
 * 实现文本淡入淡出效果
 */
public class FadeAnimation implements HologramAnimation {
    
    private final SagaLib plugin;
    private final String id;
    private final int fadeInTime; // 淡入时间(ticks)
    private final int stayTime;   // 保持时间(ticks)
    private final int fadeOutTime; // 淡出时间(ticks)
    private AnimationStatus status = AnimationStatus.STOP;
    private BukkitTask task;
    private byte originalOpacity = -1;
    
    /**
     * 创建淡入淡出动画
     * @param plugin 插件实例
     * @param fadeInTime 淡入时间(ticks)
     * @param stayTime 保持时间(ticks)
     * @param fadeOutTime 淡出时间(ticks)
     */
    public FadeAnimation(SagaLib plugin, int fadeInTime, int stayTime, int fadeOutTime) {
        this.plugin = plugin;
        this.fadeInTime = Math.max(1, fadeInTime);
        this.stayTime = Math.max(1, stayTime);
        this.fadeOutTime = Math.max(1, fadeOutTime);
        this.id = "fade_" + UUID.randomUUID().toString().substring(0, 6);
    }
    
    @Override
    public void start(TextDisplay textDisplay) {
        if (status == AnimationStatus.START) return;
        
        // 取消之前的任务
        if (task != null) {
            task.cancel();
        }
        
        status = AnimationStatus.START;
        
        // 保存原始不透明度
        originalOpacity = textDisplay.getTextOpacity();
        
        final int totalSteps = fadeInTime + stayTime + fadeOutTime;
        final int[] currentStep = {0};
        
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (textDisplay.isDead() || !textDisplay.isValid()) {
                    cancel();
                    return;
                }
                
                int step = currentStep[0];
                
                // 计算当前不透明度
                byte opacity;
                
                if (step < fadeInTime) {
                    // 淡入阶段
                    float progress = (float) step / fadeInTime;
                    opacity = (byte) (progress * 127);
                } else if (step < fadeInTime + stayTime) {
                    // 保持阶段
                    opacity = 127;
                } else if (step < totalSteps) {
                    // 淡出阶段
                    float progress = 1.0f - (float) (step - fadeInTime - stayTime) / fadeOutTime;
                    opacity = (byte) (progress * 127);
                } else {
                    // 动画结束
                    opacity = originalOpacity;
                    cancel();
                    status = AnimationStatus.STOP;
                    return;
                }
                
                // 更新不透明度
                textDisplay.setTextOpacity(opacity);
                
                // 递增步骤
                currentStep[0]++;
            }
        }.runTaskTimer(plugin, 0, 1);
    }
    
    @Override
    public void stop(TextDisplay textDisplay) {
        if (task != null) {
            task.cancel();
            task = null;
        }
        
        // 恢复原始不透明度
        if (textDisplay != null && textDisplay.isValid() && originalOpacity != -1) {
            textDisplay.setTextOpacity(originalOpacity);
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
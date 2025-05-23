package cn.i7mc.sagalib.hologram.animation;

import cn.i7mc.sagalib.SagaLib;
import cn.i7mc.sagalib.util.ColorUtil;
import org.bukkit.Color;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 颜色变换动画
 * 实现文本背景色渐变效果
 */
public class ColorChangeAnimation implements HologramAnimation {
    
    private final SagaLib plugin;
    private final String id;
    private final List<Color> colors;
    private final int interval;
    private final boolean smooth;
    private AnimationStatus status = AnimationStatus.STOP;
    private BukkitTask task;
    private Color originalColor = null;
    
    /**
     * 创建颜色变换动画
     * @param plugin 插件实例
     * @param colors 颜色列表
     * @param interval 变换间隔(ticks)
     * @param smooth 是否平滑过渡
     */
    public ColorChangeAnimation(SagaLib plugin, List<Color> colors, int interval, boolean smooth) {
        this.plugin = plugin;
        this.colors = new ArrayList<>(colors);
        this.interval = Math.max(1, interval);
        this.smooth = smooth;
        this.id = "color_" + UUID.randomUUID().toString().substring(0, 6);
        
        // 如果颜色列表为空，添加默认颜色
        if (this.colors.isEmpty()) {
            this.colors.add(Color.RED);
            this.colors.add(Color.ORANGE);
            this.colors.add(Color.YELLOW);
            this.colors.add(Color.GREEN);
            this.colors.add(Color.BLUE);
            this.colors.add(Color.PURPLE);
        }
    }
    
    @Override
    public void start(TextDisplay textDisplay) {
        if (status == AnimationStatus.START) return;
        
        // 取消之前的任务
        if (task != null) {
            task.cancel();
        }
        
        status = AnimationStatus.START;
        
        // 保存原始背景色
        originalColor = textDisplay.getBackgroundColor();
        
        final int[] currentIndex = {0};
        
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (textDisplay.isDead() || !textDisplay.isValid()) {
                    cancel();
                    return;
                }
                
                int index = currentIndex[0];
                Color currentColor = colors.get(index);
                
                // 设置背景颜色
                textDisplay.setBackgroundColor(currentColor);
                textDisplay.setDefaultBackground(false);
                
                // 更新索引
                currentIndex[0] = (currentIndex[0] + 1) % colors.size();
                
                // 如果是平滑过渡，需要计算中间颜色
                if (smooth && colors.size() > 1) {
                    final Color nextColor = colors.get(currentIndex[0]);
                    final int steps = interval / 2;
                    
                    // 创建过渡任务
                    new BukkitRunnable() {
                        int step = 0;
                        
                        @Override
                        public void run() {
                            if (textDisplay.isDead() || !textDisplay.isValid() || status != AnimationStatus.START) {
                                cancel();
                                return;
                            }
                            
                            // 计算过渡颜色
                            float progress = (float) step / steps;
                            Color transitionColor = interpolateColor(currentColor, nextColor, progress);
                            
                            // 更新背景颜色
                            textDisplay.setBackgroundColor(transitionColor);
                            
                            step++;
                            if (step > steps) {
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 1, 1);
                }
            }
        }.runTaskTimer(plugin, 0, interval);
    }
    
    /**
     * 在两种颜色之间插值
     * @param color1 起始颜色
     * @param color2 目标颜色
     * @param progress 进度(0-1)
     * @return 插值后的颜色
     */
    private Color interpolateColor(Color color1, Color color2, float progress) {
        int r = (int) (color1.getRed() + (color2.getRed() - color1.getRed()) * progress);
        int g = (int) (color1.getGreen() + (color2.getGreen() - color1.getGreen()) * progress);
        int b = (int) (color1.getBlue() + (color2.getBlue() - color1.getBlue()) * progress);
        
        return Color.fromRGB(r, g, b);
    }
    
    @Override
    public void stop(TextDisplay textDisplay) {
        if (task != null) {
            task.cancel();
            task = null;
        }
        
        // 恢复原始背景色
        if (textDisplay != null && textDisplay.isValid() && originalColor != null) {
            textDisplay.setBackgroundColor(originalColor);
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
package cn.i7mc.sagalib.gui.animation;

import cn.i7mc.sagalib.gui.holder.GuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * 淡入淡出动画实现
 * 实现GUI的淡入淡出效果
 */
public class FadeAnimation implements GuiAnimation {
    
    private final int duration;
    private final int steps;
    private final Map<Integer, ItemStack> originalItems;
    private BukkitRunnable task;
    private boolean running;
    
    /**
     * 创建一个淡入淡出动画
     * @param duration 动画持续时间(毫秒)
     * @param steps 动画步数
     */
    public FadeAnimation(int duration, int steps) {
        this.duration = duration;
        this.steps = steps;
        this.originalItems = new HashMap<>();
    }
    
    @Override
    public void start(GuiHolder holder) {
        if (running) {
            return;
        }
        
        // 保存原始物品
        originalItems.clear();
        for (int i = 0; i < holder.getSize(); i++) {
            ItemStack item = holder.getItem(i);
            if (item != null) {
                originalItems.put(i, item.clone());
            }
        }
        
        // 创建动画任务
        task = new BukkitRunnable() {
            int currentStep = 0;
            
            @Override
            public void run() {
                if (currentStep >= steps) {
                    stop();
                    return;
                }
                
                // 计算当前透明度
                float alpha = (float) currentStep / steps;
                
                // 更新所有物品
                for (Map.Entry<Integer, ItemStack> entry : originalItems.entrySet()) {
                    ItemStack item = entry.getValue().clone();
                    // TODO: 实现物品透明度调整
                    holder.setItem(entry.getKey(), item);
                }
                
                holder.update();
                currentStep++;
            }
        };
        
        // 启动动画
        task.runTaskTimer(Bukkit.getPluginManager().getPlugins()[0], 0L, duration / steps / 50L);
        running = true;
    }
    
    @Override
    public void stop() {
        if (!running) {
            return;
        }
        
        if (task != null) {
            task.cancel();
            task = null;
        }
        
        running = false;
    }
    
    @Override
    public String getName() {
        return "淡入淡出";
    }
    
    @Override
    public String getDescription() {
        return String.format("持续%d毫秒,分%d步完成的淡入淡出动画", duration, steps);
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
} 
# SagaLib API 精简调用示例

## 核心API

```java
// 获取API实例
SagaLibAPI sagaLib = SagaLibAPI.getInstance();
```

## GUI系统

### 创建GUI
```java
// 创建基础GUI
GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);

// 创建分页GUI
GuiHolder paginationGui = sagaLib.createPaginationGui(player, "分页菜单", 45);

// 创建动态大小GUI
GuiHolder dynamicGui = sagaLib.createDynamicGui(player, "动态菜单", 27, 54, 45);
```

### 按钮操作
```java
// 创建按钮
GuiButton button = new SimpleButton(Material.DIAMOND, "钻石按钮");
button.setClickHandler(event -> {
    // 处理点击事件
});

// 添加按钮
sagaLib.addButton(gui, 0, button);

// 批量添加按钮
Map<Integer, GuiButton> buttons = new HashMap<>();
buttons.put(0, button1);
buttons.put(1, button2);
sagaLib.addButtons(gui, buttons);
```

### 分页操作
```java
// 获取当前页码
int currentPage = sagaLib.getCurrentPage(gui);

// 设置页码
sagaLib.setPage(gui, 0);

// 添加导航按钮
GuiButton prevButton = new SimpleButton(Material.ARROW, "上一页");
prevButton.setClickHandler(event -> {
    int current = sagaLib.getCurrentPage(gui);
    if (current > 0) {
        sagaLib.setPage(gui, current - 1);
    }
});

GuiButton nextButton = new SimpleButton(Material.ARROW, "下一页");
nextButton.setClickHandler(event -> {
    int current = sagaLib.getCurrentPage(gui);
    int maxPage = (totalItems - 1) / itemsPerPage;
    if (current < maxPage) {
        sagaLib.setPage(gui, current + 1);
    }
});
```

### 布局和样式
```java
// 创建布局
GuiLayout layout = sagaLib.createCustomLayout(6, 9, 1, 1);
sagaLib.setLayout(gui, layout);

// 创建样式
GuiStyle style = sagaLib.createDefaultStyle();
GuiStyle customStyle = sagaLib.createCustomStyle(
    Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
    Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
    Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
);

// 应用样式
sagaLib.applyStyle(gui, style);
```

### 动画控制
```java
// 创建动画
GuiAnimation animation = new FadeAnimation();

// 添加动画
sagaLib.addAnimation(gui, animation);

// 控制动画
sagaLib.startAnimation(gui, animation);
sagaLib.stopAnimation(gui, animation);

// 获取动画状态
String status = sagaLib.getAnimationStatus(gui, animation);
```

### 物品操作
```java
// 添加物品
sagaLib.addItem(gui, 0, item);

// 设置物品LORE
List<String> lore = Arrays.asList("第一行", "第二行");
sagaLib.setItemLore(item, lore);
```

## 配置系统

```java
// 获取配置管理器
ConfigManager config = sagaLib.getConfigManager();
config.loadConfig();

// 获取语言管理器
LanguageManager lang = sagaLib.getLanguageManager();
lang.loadMessages();

// 重载配置
sagaLib.reloadConfig();

// 获取消息配置
FileConfiguration messageConfig = sagaLib.getMessageConfig();
String message = messageConfig.getString("message.key");

// 获取调试消息配置
FileConfiguration debugConfig = sagaLib.getDebugMessageConfig();
```

## 工具类

```java
// 解析颜色代码
String colored = sagaLib.parseColor("&a绿色文本");

// 解析占位符
String parsed = sagaLib.parsePlaceholders(player, "玩家名称: %player_name%");

// 检查PlaceholderAPI
boolean hasPlaceholderAPI = sagaLib.hasPlaceholderAPI();

// 注册自定义占位符
sagaLib.registerPlaceholder("custom", player -> "自定义值");

// 获取其他工具类
ColorUtil colorUtil = sagaLib.getColorUtil();
TextUtil textUtil = sagaLib.getTextUtil();
GuiUtil guiUtil = sagaLib.getGuiUtil();
```

## 按钮更新技巧

```java
// 创建新按钮替换现有按钮
SimpleButton newButton = new SimpleButton(Material.DIAMOND, "新的按钮名称");
newButton.setLore(Arrays.asList("&7新的描述文本"));
newButton.setClickHandler(event -> {
    // 新的点击处理程序
});
sagaLib.addButton(gui, slot, newButton);

// 更新按钮辅助方法
private void updateButton(GuiHolder gui, int slot, boolean state) {
    SimpleButton button = new SimpleButton(
        state ? Material.EMERALD : Material.REDSTONE,
        state ? "激活状态" : "禁用状态"
    );
    button.setLore(Arrays.asList(
        "&7点击" + (state ? "禁用" : "激活")
    ));
    button.setClickHandler(e -> {
        updateButton(gui, slot, !state);
    });
    sagaLib.addButton(gui, slot, button);
}
```

## 分页数据加载

```java
// 加载指定页的数据
private void loadItemsForPage(GuiHolder gui, int page, int itemsPerPage, int totalItems) {
    int startIndex = page * itemsPerPage;
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
    
    for (int i = startIndex; i < endIndex; i++) {
        int slotIndex = i - startIndex;
        ItemStack item = createItem(i);
        sagaLib.addItem(gui, slotIndex, item);
    }
}
```

## 自定义动画

```java
// 实现自定义动画
public class CustomAnimation implements GuiAnimation {
    private boolean running = false;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        // 动画开始逻辑
    }
    
    @Override
    public void stop() {
        running = false;
        // 动画停止逻辑
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "custom";
    }
    
    @Override
    public String getDescription() {
        return "自定义动画效果";
    }
    
    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
}

// 或者使用匿名内部类
GuiAnimation animation = new GuiAnimation() {
    private boolean running = false;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        // 实现动画逻辑
    }
    
    @Override
    public void stop() {
        running = false;
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "anonymous";
    }
    
    @Override
    public String getDescription() {
        return "匿名动画";
    }
    
    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
``` 
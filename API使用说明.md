# SagaLib API 使用说明

## 包名导入说明

### 核心API
```java
import cn.i7mc.sagalib.api.SagaLibAPI;
```

### GUI系统相关
```java
// GUI容器
import cn.i7mc.sagalib.gui.holder.GuiHolder;

// 按钮相关
import cn.i7mc.sagalib.gui.button.GuiButton;
import cn.i7mc.sagalib.gui.button.SimpleButton;

// 布局相关
import cn.i7mc.sagalib.gui.layout.GuiLayout;

// 样式相关
import cn.i7mc.sagalib.gui.style.GuiStyle;
import cn.i7mc.sagalib.gui.style.DefaultStyle;

// 动画相关
import cn.i7mc.sagalib.gui.animation.GuiAnimation;
import cn.i7mc.sagalib.gui.animation.FadeAnimation;

// 事件相关
import cn.i7mc.sagalib.gui.listener.GuiClickEvent;
import cn.i7mc.sagalib.gui.listener.GuiCloseEvent;
import cn.i7mc.sagalib.gui.listener.GuiOpenEvent;
```

### 配置系统相关
```java
import cn.i7mc.sagalib.config.ConfigManager;
import cn.i7mc.sagalib.config.LanguageManager;
```

### 工具类相关
```java
import cn.i7mc.sagalib.util.ColorUtil;
import cn.i7mc.sagalib.util.TextUtil;
import cn.i7mc.sagalib.util.GuiUtil;
```

### 异常相关
```java
import cn.i7mc.sagalib.exception.GuiException;
import cn.i7mc.sagalib.exception.ConfigException;
```

## 目录
1. [快速开始](#快速开始)
2. [GUI系统](#gui系统)
3. [配置系统](#配置系统)
4. [工具类](#工具类)
5. [最佳实践](#最佳实践)

## 快速开始

### 添加依赖
```xml
<dependency>
    <groupId>cn.i7mc</groupId>
    <artifactId>sagalib</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>
```

### 初始化
```java
public class YourPlugin extends JavaPlugin {
    private SagaLibAPI sagaLib;
    
    @Override
    public void onEnable() {
        sagaLib = SagaLibAPI.getInstance();
    }
}
```

## GUI系统

### 创建基础GUI
```java
// 创建基础GUI (6行 = 54格)
GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);

// 创建分页GUI (5行 = 45格)
// 注意: 分页GUI会自动计算合适的大小
// 计算公式: size = Math.min(Math.max((itemsPerPage + 9) / 9 * 9, 27), 54)
// 例如: 当itemsPerPage=45时, size = Math.min(Math.max((45 + 9) / 9 * 9, 27), 54) = 54
GuiHolder paginationGui = sagaLib.createPaginationGui(player, "分页菜单", 45);

// 创建动态大小GUI (最小3行=27格, 最大6行=54格, 内容45格)
GuiHolder dynamicGui = sagaLib.createDynamicGui(player, "动态菜单", 27, 54, 45);
```

### 注意事项
1. GUI大小必须是9的倍数
2. GUI大小范围必须在9到54之间
3. 分页GUI会自动计算合适的大小:
   - 底部导航栏固定9格
   - 内容区域根据itemsPerPage自动计算
   - 总大小 = 内容区域 + 底部导航栏
   - 最终大小会被限制在27-54之间
4. 分页GUI添加物品时:
   - 物品数量不要超过itemsPerPage参数
   - 物品位置从0开始计数
   - 分页布局会自动处理物品位置
   - 底部导航栏的9个格子不要放置物品

### 分页GUI导航
```java
// 获取当前页码
int currentPage = sagaLib.getCurrentPage(gui);

// 设置页码
sagaLib.setPage(gui, page);

// 添加导航按钮
GuiButton prevButton = new SimpleButton(Material.ARROW, "上一页");
prevButton.setClickHandler(event -> {
    int current = sagaLib.getCurrentPage(gui);
    if (current > 0) {
        sagaLib.setPage(gui, current - 1);
    }
});
sagaLib.addButton(gui, 45, prevButton);  // 上一页按钮位置

GuiButton nextButton = new SimpleButton(Material.ARROW, "下一页");
nextButton.setClickHandler(event -> {
    int current = sagaLib.getCurrentPage(gui);
    int maxPage = (totalItems - 1) / itemsPerPage;
    if (current < maxPage) {
        sagaLib.setPage(gui, current + 1);
    }
});
sagaLib.addButton(gui, 53, nextButton);  // 下一页按钮位置
```

### 自定义分页实现
在某些情况下，您可能需要自行实现分页功能，而不是使用内置的分页GUI。以下是一个自定义分页实现的示例：

```java
// 创建普通GUI (6行 = 54格)，不使用分页GUI
GuiHolder gui = sagaLib.createGui(player, "分页菜单 - 第1页", 54);

// 定义分页参数
final int itemsPerPage = 45;
final int totalItems = 225; // 5页 * 45个物品
final int maxPage = (totalItems - 1) / itemsPerPage;

// 创建上一页按钮
SimpleButton prevButton = new SimpleButton(Material.ARROW, "上一页");
prevButton.setClickHandler(event -> {
    if (currentPage > 0) {
        openNewPage(player, currentPage - 1);
    }
});
sagaLib.addButton(gui, 45, prevButton);

// 创建下一页按钮
SimpleButton nextButton = new SimpleButton(Material.ARROW, "下一页");
nextButton.setClickHandler(event -> {
    if (currentPage < maxPage) {
        openNewPage(player, currentPage + 1);
    }
});
sagaLib.addButton(gui, 53, nextButton);

// 加载当前页的物品
loadItemsForPage(gui, currentPage, itemsPerPage, totalItems);
```

加载指定页码的物品：
```java
private void loadItemsForPage(GuiHolder gui, int page, int itemsPerPage, int totalItems) {
    // 计算当前页的起始物品索引
    int startIndex = page * itemsPerPage;
    
    // 计算当前页的结束物品索引（不超过总物品数）
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
    
    // 添加当前页的物品
    for (int i = startIndex; i < endIndex; i++) {
        // 计算在当前页内的索引位置（0-44）
        int slotIndex = i - startIndex;
        
        // 创建物品
        ItemStack item = new ItemStack(Material.DIAMOND);
        List<String> lore = Arrays.asList(
            "&7这是第 " + (i + 1) + " 个物品",
            "&7第 " + (page + 1) + " 页"
        );
        sagaLib.setItemLore(item, lore);
        
        // 添加物品 - 务必使用SagaLibAPI的方法
        sagaLib.addItem(gui, slotIndex, item);
    }
}
```

### 添加按钮
```java
// 创建按钮
GuiButton button = new SimpleButton(Material.DIAMOND, "钻石按钮");
button.setClickHandler(event -> {
    // 处理点击事件
});

// 添加单个按钮
gui.addButton(0, button);

// 批量添加按钮
Map<Integer, GuiButton> buttons = new HashMap<>();
buttons.put(0, button1);
buttons.put(1, button2);
buttons.forEach(gui::addButton);
```

### 使用布局
```java
// 创建自定义布局
GuiLayout layout = sagaLib.createCustomLayout(6, 9, 1, 1);
sagaLib.setLayout(gui, layout);
```

### 应用样式
```java
// 创建默认样式
GuiStyle style = sagaLib.createDefaultStyle();

// 创建自定义样式
GuiStyle style = sagaLib.createCustomStyle(
    Material.BLACK_STAINED_GLASS_PANE,  // 边框材质
    Material.GRAY_STAINED_GLASS_PANE,   // 背景材质
    Material.WHITE_STAINED_GLASS_PANE   // 按钮材质
);

// 应用样式
sagaLib.applyStyle(gui, style);
```

### 使用动画
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

### 添加物品
```java
// 添加单个物品
gui.addItem(0, item);

// 批量添加物品
Map<Integer, ItemStack> items = new HashMap<>();
items.put(0, item1);
items.put(1, item2);
items.forEach((slot, item) -> gui.addItem(slot, item));
```

## 配置系统

### 加载配置
```java
// 获取配置管理器
ConfigManager config = sagaLib.getConfigManager();
config.loadConfig();

// 获取语言管理器
LanguageManager lang = sagaLib.getLanguageManager();
lang.loadMessages();

// 重载所有配置
sagaLib.reloadConfig();
```

### 获取配置值
```java
// 获取消息配置
FileConfiguration messageConfig = sagaLib.getMessageConfig();
String message = messageConfig.getString("message.key");

// 获取调试消息配置
FileConfiguration debugConfig = sagaLib.getDebugMessageConfig();
String debugMessage = debugConfig.getString("debug.key");
```

## 工具类

### 物品构建器
```java
// 设置物品LORE
List<String> lore = Arrays.asList("第一行", "第二行");
sagaLib.setItemLore(item, lore);
```

### 颜色工具
```java
// 解析颜色代码
String colored = sagaLib.parseColor("&a绿色文本");

// 获取颜色工具类
ColorUtil colorUtil = sagaLib.getColorUtil();
```

### 文本工具
```java
// 获取文本工具类
TextUtil textUtil = sagaLib.getTextUtil();
```

### GUI工具
```java
// 获取GUI工具类
GuiUtil guiUtil = sagaLib.getGuiUtil();
```

### 占位符解析
```java
// 解析占位符
String parsed = sagaLib.parsePlaceholders(player, "玩家名称: %player_name%");

// 检查是否安装了PlaceholderAPI
boolean hasPlaceholderAPI = sagaLib.hasPlaceholderAPI();

// 注册自定义占位符
sagaLib.registerPlaceholder("custom", "自定义值");
```

## 最佳实践

### GUI开发
1. 使用布局系统管理按钮位置
2. 实现动画效果提升用户体验
3. 使用分页处理大量数据
4. 统一使用消息配置文件

### 性能优化
1. 使用缓存减少配置读取
2. 优化GUI渲染逻辑
3. 合理使用事件监听器
4. 避免频繁创建对象

### 代码规范
1. 遵循统一的方法命名
2. 使用统一的错误处理
3. 保持代码简洁清晰
4. 添加必要的注释

### 5. 完整示例

```java
public class ExampleGui {
    private final SagaLibAPI sagaLib;
    
    public ExampleGui() {
        this.sagaLib = SagaLibAPI.getInstance();
    }
    
    public void openMenu(Player player) {
        // 创建GUI (6行 = 54格)
        GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,
            Material.GRAY_STAINED_GLASS_PANE,
            Material.WHITE_STAINED_GLASS_PANE
        );
        sagaLib.applyStyle(gui, style);
        
        // 创建按钮
        GuiButton button = new SimpleButton(Material.DIAMOND, "钻石按钮");
        button.setClickHandler(event -> {
            Player clicker = (Player) event.getWhoClicked();
            clicker.sendMessage(sagaLib.parseColor("&a你点击了钻石按钮!"));
        });
        
        // 添加按钮
        sagaLib.addButton(gui, 13, button);
        
        // 创建动画
        GuiAnimation animation = new FadeAnimation();
        sagaLib.addAnimation(gui, animation);
        sagaLib.startAnimation(gui, animation);
        
        // 打开GUI
        player.openInventory(gui.getInventory());
    }
}
```

### 6. 注意事项

1. GUI系统
   - 所有GUI操作都需要先创建GuiHolder
   - 按钮位置从0开始计数
   - 动画需要在打开GUI前启动
   - 添加物品时必须指定位置(slot)
   - 使用GuiHolder的方法添加按钮、物品和动画
   - 使用SagaLibAPI的方法创建GUI、样式和布局

2. 配置系统
   - 配置文件修改后需要调用reloadConfig()
   - 消息配置支持颜色代码和占位符

3. 工具类
   - 所有工具类都是单例模式
   - 颜色代码支持&和§两种格式

4. 占位符系统
   - 支持PlaceholderAPI的所有占位符
   - 自定义占位符需要注册才能使用
   - 占位符解析需要提供Player对象

### 7. 常见问题

1. GUI相关问题
   - Q: GUI大小必须是9的倍数吗？
   - A: 是的,所有GUI大小必须是9的倍数

2. 配置相关问题
   - Q: 配置文件修改后需要重启服务器吗？
   - A: 不需要,调用reloadConfig()即可

3. 占位符相关问题
   - Q: 占位符支持嵌套吗？
   - A: 支持,但建议不要过度嵌套

4. 动画相关问题
   - Q: 动画可以同时运行多个吗？
   - A: 可以,但要注意性能影响

## API方法说明

### GUI相关
1. `createGui(Player player, String title, int size)` - 创建基础GUI
   - 参数: player(玩家), title(GUI标题), size(GUI大小)
   - 返回: GuiHolder实例

2. `createPaginationGui(Player player, String title, int itemsPerPage)` - 创建分页GUI
   - 参数: player(玩家), title(GUI标题), itemsPerPage(每页物品数量)
   - 返回: GuiHolder实例

3. `createDynamicGui(Player player, String title, int minSize, int maxSize, int contentSize)` - 创建动态大小GUI
   - 参数: player(玩家), title(GUI标题), minSize(最小大小), maxSize(最大大小), contentSize(内容大小)
   - 返回: GuiHolder实例

4. `addButton(GuiHolder holder, int slot, GuiButton button)` - 添加单个按钮
   - 参数: holder(GUI容器), slot(按钮位置), button(按钮实例)

5. `addButtons(GuiHolder holder, Map<Integer, GuiButton> buttons)` - 批量添加按钮
   - 参数: holder(GUI容器), buttons(按钮Map)

6. `setLayout(GuiHolder holder, GuiLayout layout)` - 设置GUI布局
   - 参数: holder(GUI容器), layout(布局实例)

7. `createCustomLayout(int rows, int columns, int buttonSpacing, int borderWidth)` - 创建自定义布局
   - 参数: rows(行数), columns(列数), buttonSpacing(按钮间距), borderWidth(边框宽度)
   - 返回: GuiLayout实例

8. `applyStyle(GuiHolder holder, GuiStyle style)` - 应用GUI样式
   - 参数: holder(GUI容器), style(样式实例)

9. `addAnimation(GuiHolder holder, GuiAnimation animation)` - 添加GUI动画
   - 参数: holder(GUI容器), animation(动画实例)

10. `startAnimation(GuiHolder holder, GuiAnimation animation)` - 启动GUI动画
    - 参数: holder(GUI容器), animation(动画实例)

11. `stopAnimation(GuiHolder holder, GuiAnimation animation)` - 停止GUI动画
    - 参数: holder(GUI容器), animation(动画实例)

12. `getAnimationStatus(GuiHolder holder, GuiAnimation animation)` - 获取动画状态
    - 参数: holder(GUI容器), animation(动画实例)
    - 返回: 动画状态字符串

### 配置系统
1. `getConfigManager()` - 获取配置管理器
   - 返回: ConfigManager实例

2. `getLanguageManager()` - 获取语言管理器
   - 返回: LanguageManager实例

3. `reloadConfig()` - 重载所有配置

4. `getMessageConfig()` - 获取消息配置
   - 返回: FileConfiguration实例

5. `getDebugMessageConfig()` - 获取调试消息配置
   - 返回: FileConfiguration实例

### 工具类
1. `setItemLore(ItemStack item, List<String> lore)` - 设置物品LORE
   - 参数: item(物品), lore(LORE列表)

2. `parseColor(String text)` - 解析颜色代码
   - 参数: text(包含颜色代码的文本)
   - 返回: 解析后的文本

3. `parsePlaceholders(Player player, String text)` - 解析占位符
   - 参数: player(玩家), text(包含占位符的文本)
   - 返回: 解析后的文本

4. `getTextUtil()` - 获取文本工具类
   - 返回: TextUtil实例

5. `getColorUtil()` - 获取颜色工具类
   - 返回: ColorUtil实例

6. `getGuiUtil()` - 获取GUI工具类
   - 返回: GuiUtil实例

### PlaceholderAPI集成
1. `hasPlaceholderAPI()` - 检查是否安装了PlaceholderAPI
   - 返回: 是否安装了PlaceholderAPI

2. `registerPlaceholder(String name, Function<Player, String> placeholder)` - 注册自定义占位符
   - 参数: name(占位符名称), placeholder(占位符处理器)


### 1. GUI系统核心功能

#### 1.1 创建GUI
```java
// 基础GUI创建 (6行 = 54格)
GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);  
// 分页GUI创建 (5行 = 45格)
GuiHolder paginationGui = sagaLib.createPaginationGui(player, "分页菜单", 45);  
// 动态GUI创建 (最小3行=27格, 最大6行=54格, 内容45格)
GuiHolder dynamicGui = sagaLib.createDynamicGui(player, "动态菜单", 27, 54, 45);  
```

#### 1.2 按钮系统
```java
// 创建按钮
GuiButton button = new SimpleButton(Material.DIAMOND, "钻石按钮");
button.setClickHandler(event -> {
    Player player = (Player) event.getWhoClicked();
    // 处理点击事件
});

// 添加按钮
sagaLib.addButton(gui, 0, button);  // 单个按钮
sagaLib.addButtons(gui, buttons);   // 批量添加按钮
```

#### 1.3 布局系统
```java
// 创建布局
GuiLayout layout = sagaLib.createCustomLayout(6, 9, 1, 1);  // 6行9列,按钮间距1,边框宽度1
sagaLib.setLayout(gui, layout);
```

#### 1.4 样式系统
```java
// 创建样式
GuiStyle style = new DefaultStyle();
style.setBorderMaterial(Material.BLACK_STAINED_GLASS_PANE);
style.setBackgroundMaterial(Material.GRAY_STAINED_GLASS_PANE);
style.setButtonMaterial(Material.WHITE_STAINED_GLASS_PANE);

// 应用样式
sagaLib.applyStyle(gui, style);
```

#### 1.5 动画系统
```java
// 创建动画
GuiAnimation animation = new FadeAnimation();

// 控制动画
sagaLib.addAnimation(gui, animation);
sagaLib.startAnimation(gui, animation);
sagaLib.stopAnimation(gui, animation);
String status = sagaLib.getAnimationStatus(gui, animation);
```

### 2. 配置系统

#### 2.1 配置管理
```java
// 获取配置管理器
ConfigManager config = sagaLib.getConfigManager();
config.loadConfig();

// 获取语言管理器
LanguageManager lang = sagaLib.getLanguageManager();
lang.loadMessages();

// 重载配置
sagaLib.reloadConfig();
```

#### 2.2 消息配置
```java
// 获取消息配置
FileConfiguration messageConfig = sagaLib.getMessageConfig();
String message = messageConfig.getString("message.key");

// 获取调试消息配置
FileConfiguration debugConfig = sagaLib.getDebugMessageConfig();
String debugMessage = debugConfig.getString("debug.key");
```

### 3. 工具类系统

#### 3.1 物品处理
```java
// 设置物品LORE
List<String> lore = Arrays.asList("第一行", "第二行");
sagaLib.setItemLore(item, lore);
```

#### 3.2 颜色处理
```java
// 解析颜色代码
String colored = sagaLib.parseColor("&a绿色文本");

// 获取颜色工具类
ColorUtil colorUtil = sagaLib.getColorUtil();
```

#### 3.3 文本处理
```java
// 获取文本工具类
TextUtil textUtil = sagaLib.getTextUtil();
```

#### 3.4 GUI工具
```java
// 获取GUI工具类
GuiUtil guiUtil = sagaLib.getGuiUtil();
```

### 4. 占位符系统

#### 4.1 基础占位符
```java
// 解析占位符
String parsed = sagaLib.parsePlaceholders(player, "玩家名称: %player_name%");

// 检查PlaceholderAPI
boolean hasPlaceholderAPI = sagaLib.hasPlaceholderAPI();
```

#### 4.2 自定义占位符
```java
// 注册自定义占位符
sagaLib.registerPlaceholder("custom", player -> "自定义值");
```

### 5. 完整示例

```java
public class ExampleGui {
    private final SagaLibAPI sagaLib;
    
    public ExampleGui() {
        this.sagaLib = SagaLibAPI.getInstance();
    }
    
    public void openMenu(Player player) {
        // 创建GUI (6行 = 54格)
        GuiHolder gui = sagaLib.createGui(player, "主菜单", 54);
        
        // 创建样式
        GuiStyle style = sagaLib.createCustomStyle(
            Material.BLACK_STAINED_GLASS_PANE,
            Material.GRAY_STAINED_GLASS_PANE,
            Material.WHITE_STAINED_GLASS_PANE
        );
        sagaLib.applyStyle(gui, style);
        
        // 创建按钮
        GuiButton button = new SimpleButton(Material.DIAMOND, "钻石按钮");
        button.setClickHandler(event -> {
            Player clicker = (Player) event.getWhoClicked();
            clicker.sendMessage(sagaLib.parseColor("&a你点击了钻石按钮!"));
        });
        
        // 添加按钮
        sagaLib.addButton(gui, 13, button);
        
        // 创建动画
        GuiAnimation animation = new FadeAnimation();
        sagaLib.addAnimation(gui, animation);
        sagaLib.startAnimation(gui, animation);
        
        // 打开GUI
        player.openInventory(gui.getInventory());
    }
}
```

### 6. 注意事项

1. GUI系统
   - 所有GUI操作都需要先创建GuiHolder
   - 按钮位置从0开始计数
   - 动画需要在打开GUI前启动
   - 添加物品时必须指定位置(slot)
   - 使用GuiHolder的方法添加按钮、物品和动画
   - 使用SagaLibAPI的方法创建GUI、样式和布局

2. 配置系统
   - 配置文件修改后需要调用reloadConfig()
   - 消息配置支持颜色代码和占位符

3. 工具类
   - 所有工具类都是单例模式
   - 颜色代码支持&和§两种格式

4. 占位符系统
   - 支持PlaceholderAPI的所有占位符
   - 自定义占位符需要注册才能使用
   - 占位符解析需要提供Player对象

### 7. 常见问题

1. GUI相关问题
   - Q: GUI大小必须是9的倍数吗？
   - A: 是的,所有GUI大小必须是9的倍数

2. 配置相关问题
   - Q: 配置文件修改后需要重启服务器吗？
   - A: 不需要,调用reloadConfig()即可

### API调用注意事项
使用SagaLib API时，请注意以下几点：

1. **始终使用SagaLibAPI实例调用方法**：
   ```java
   // 正确的方式
   sagaLib.addButton(gui, slot, button);
   sagaLib.addItem(gui, slot, item);
   sagaLib.addAnimation(gui, animation);
   sagaLib.startAnimation(gui, animation);
   sagaLib.stopAnimation(gui, animation);
   
   // 错误的方式 - 不要直接调用GuiHolder的方法
   // gui.addButton(slot, button);     // 错误！
   // gui.addItem(slot, item);         // 错误！
   // gui.addAnimation(animation);     // 错误！
   // gui.startAnimation(animation);   // 错误！
   // gui.stopAnimation();             // 错误！
   ```

2. **避免混合使用API**：
   - 不要混合使用SagaLibAPI和GuiHolder的方法
   - 始终通过sagaLib实例调用方法
   - 这样可以确保代码一致性和避免编译错误

3. **物品索引范围检查**：
   - 添加物品时，确保索引不超出GUI大小
   - 普通GUI的最大索引为size-1（例如：54格GUI的最大索引为53）
   - 分页GUI中物品索引不要超过itemsPerPage

4. **动画控制注意事项**：
   - 动画必须通过SagaLibAPI实例添加和控制
   - 动画状态可以通过`sagaLib.getAnimationStatus(gui, animation)`获取
   - 支持同时运行多个动画，但需注意性能影响

### 动画系统详解

动画系统是SagaLib的高级功能，它允许开发者创建动态的GUI效果，提升用户体验。以下是动画系统的详细说明：

#### 动画类型
SagaLib内置了多种动画类型：
- `FadeAnimation`：淡入淡出效果
- `SlideAnimation`：滑动效果
- `BlinkAnimation`：闪烁效果
- `RotateAnimation`：旋转效果

#### 动画API方法
```java
// 添加动画到GUI
sagaLib.addAnimation(GuiHolder holder, GuiAnimation animation);

// 启动动画
sagaLib.startAnimation(GuiHolder holder, GuiAnimation animation);

// 停止动画
sagaLib.stopAnimation(GuiHolder holder, GuiAnimation animation);

// 获取动画状态 (返回值: "START", "STOP", "PAUSE", "RESUME")
String status = sagaLib.getAnimationStatus(GuiHolder holder, GuiAnimation animation);
```

#### 自定义动画
您可以通过实现`GuiAnimation`接口创建自定义动画：
```java
public class CustomAnimation implements GuiAnimation {
    private boolean running = false;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        // 实现动画开始逻辑
    }
    
    @Override
    public void stop() {
        running = false;
        // 实现动画停止逻辑
    }
    
    @Override
    public String getName() {
        return "custom"; // 动画名称
    }
    
    @Override
    public String getDescription() {
        return "自定义动画效果"; // 动画描述
    }
    
    @Override
    public boolean isRunning() {
        return running; // 是否正在运行
    }
    
    @Override
    public String getStatus() {
        return running ? "START" : "STOP"; // 动画状态
    }
}
```

#### 注意事项
1. 所有动画操作都必须通过SagaLibAPI实例进行，不要直接使用GuiHolder的方法
2. 动画需要在打开GUI前启动
3. 复杂的动画可能会影响性能，请谨慎使用
4. 可以同时运行多个动画，但注意动画之间的交互

### 实用开发技巧

#### 按钮更新技巧
SimpleButton类不提供setName或setDisplayName等方法来直接修改已创建按钮的文本。要更新按钮文本，必须创建新的按钮实例：

```java
// 创建新按钮来替换现有按钮
SimpleButton newButton = new SimpleButton(Material.DIAMOND, "新的按钮名称");
newButton.setLore(Arrays.asList("&7新的描述文本"));
newButton.setClickHandler(event -> {
    // 新的点击处理程序
});
sagaLib.addButton(gui, slot, newButton);
```

一个实用的模式是创建辅助方法来处理按钮更新：
```java
private void updateButton(GuiHolder gui, int slot, boolean state) {
    if (state) {
        SimpleButton activeButton = new SimpleButton(Material.EMERALD, "激活状态");
        activeButton.setLore(Arrays.asList("&7点击禁用"));
        activeButton.setClickHandler(e -> {
            // 处理点击逻辑
            updateButton(gui, slot, false);
        });
        sagaLib.addButton(gui, slot, activeButton);
    } else {
        SimpleButton inactiveButton = new SimpleButton(Material.REDSTONE, "禁用状态");
        inactiveButton.setLore(Arrays.asList("&7点击激活"));
        inactiveButton.setClickHandler(e -> {
            // 处理点击逻辑
            updateButton(gui, slot, true);
        });
        sagaLib.addButton(gui, slot, inactiveButton);
    }
}
```

#### 动画实现技巧
要创建自定义动画，需要实现GuiAnimation接口。以下是通过匿名内部类实现的滑动动画示例：

```java
// 创建滑动动画
final GuiAnimation slideAnimation = new GuiAnimation() {
    private boolean running = false;
    private int taskId = -1;
    private int offset = 0;
    private boolean movingRight = true;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        offset = 0;
        movingRight = true;
        
        // 创建滑动效果
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                if (taskId != -1) {
                    Bukkit.getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
                return;
            }
            
            // 清空旧物品
            for (int i = 9; i < 18; i++) {
                holder.getInventory().setItem(i, null);
            }
            
            // 更新偏移量
            if (movingRight) {
                offset++;
                if (offset >= 8) {
                    offset = 8;
                    movingRight = false;
                }
            } else {
                offset--;
                if (offset <= 0) {
                    offset = 0;
                    movingRight = true;
                }
            }
            
            // 在新位置创建物品
            ItemStack item = new ItemStack(Material.DRAGON_EGG);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(sagaLib.parseColor("&e滑动动画效果"));
                item.setItemMeta(meta);
            }
            holder.getInventory().setItem(9 + offset, item);
        }, 0L, 5L);
    }
    
    @Override
    public void stop() {
        running = false;
        if (taskId != -1) {
            Bukkit.getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "slide";
    }
    
    @Override
    public String getDescription() {
        return "滑动动画效果";
    }

    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
```

#### 多动画管理
同时管理多个动画时，应注意：

1. 避免动画冲突：
```java
// 启动新动画前停止可能冲突的动画
existingAnimation.stop();
sagaLib.startAnimation(gui, newAnimation);
```

2. 同步状态更新：
```java
// 更新动画状态显示
button.setLore(Arrays.asList(
    "&7动画1: &f" + sagaLib.getAnimationStatus(gui, animation1),
    "&7动画2: &f" + sagaLib.getAnimationStatus(gui, animation2)
));
```

3. 资源清理：
```java
// 在适当的时机清理动画资源
@Override
public void onDisable() {
    // 停止所有活动动画
    for (GuiAnimation animation : activeAnimations) {
        animation.stop();
    }
}
```

### 常见编程问题解决

#### 按钮状态切换
在使用SimpleButton时，若需要实现状态切换功能，推荐使用以下模式：

```java
// 创建状态切换按钮
SimpleButton toggleButton = new SimpleButton(Material.REDSTONE_LAMP, "点击切换状态");
toggleButton.setClickHandler(event -> {
    boolean newState = !currentState;
    currentState = newState;
    
    // 创建新按钮来反映状态变化
    SimpleButton updatedButton = new SimpleButton(
        newState ? Material.REDSTONE_BLOCK : Material.REDSTONE_LAMP,
        newState ? "当前状态: 开启" : "当前状态: 关闭"
    );
    updatedButton.setLore(Arrays.asList(
        "&7点击切换状态",
        "&7当前: " + (newState ? "&a开启" : "&c关闭")
    ));
    
    // 保留相同的点击处理逻辑
    updatedButton.setClickHandler(((SimpleButton)event.getButton()).getClickHandler());
    
    // 更新GUI中的按钮
    sagaLib.addButton(gui, slot, updatedButton);
});
```

#### 编译错误解决
当遇到编译错误时，请注意以下常见问题：

1. 按钮相关错误:
   - SimpleButton没有setName/setDisplayName方法，应创建新按钮实例
   - 使用lambdas表达式定义点击处理程序
   
   ```java
   // 正确方式:
   button.setClickHandler(event -> {
       // 处理点击
   });
   
   // 错误方式:
   button.setClickHandler(new ClickHandler() {...});
   ```

2. 动画相关错误:
   - 确保实现GuiAnimation接口的所有必要方法
   - 使用SagaLibAPI实例添加和控制动画
   
   ```java
   // 正确方式:
   sagaLib.addAnimation(gui, animation);
   sagaLib.startAnimation(gui, animation);
   sagaLib.stopAnimation(gui, animation);
   
   // 错误方式:
   gui.addAnimation(animation);
   animation.start(gui);
   animation.stop();
   ```

3. 导入错误:
   - 确保导入正确的包
   
   ```java
   // GUI动画导入
   import cn.i7mc.sagalib.gui.animation.GuiAnimation;
   
   // 物品元数据导入
   import org.bukkit.inventory.meta.ItemMeta;
   ```

#### 动画性能优化

当使用多个复杂动画时，请考虑以下性能优化技巧：

1. 减少更新频率:
   ```java
   // 更新间隔从5刻调整为10刻
   taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, runnable, 0L, 10L);
   ```

2. 限制动画范围:
   ```java
   // 只更新可见区域
   for (int i = visibleStartSlot; i <= visibleEndSlot; i++) {
       // 更新物品
   }
   ```

3. 避免频繁创建新物品:
   ```java
   // 预创建物品并重用
   private final ItemStack[] animationFrames;
   
   // 在start方法中初始化
   animationFrames = new ItemStack[frameCount];
   for (int i = 0; i < frameCount; i++) {
       animationFrames[i] = createAnimationFrame(i);
   }
   
   // 在动画更新中使用
   holder.getInventory().setItem(slot, animationFrames[currentFrame]);
   ```

4. 适当清理资源:
   ```java
   // 在玩家关闭GUI时停止动画
   gui.setCloseHandler(event -> {
       for (GuiAnimation animation : activeAnimations) {
           animation.stop();
       }
   });
   ```

### 动画的常见模式实现

以下提供几种常见动画效果的实现示例：

#### 淡入淡出效果

```java
final GuiAnimation fadeAnimation = new GuiAnimation() {
    private boolean running = false;
    private int taskId = -1;
    private float opacity = 0.0f;
    private boolean increasing = true;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        opacity = 0.0f;
        increasing = true;
        
        // 创建淡入淡出效果
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                if (taskId != -1) {
                    plugin.getServer().getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
                return;
            }
            
            // 更新透明度
            if (increasing) {
                opacity += 0.1f;
                if (opacity >= 1.0f) {
                    opacity = 1.0f;
                    increasing = false;
                }
            } else {
                opacity -= 0.1f;
                if (opacity <= 0.0f) {
                    opacity = 0.0f;
                    increasing = true;
                }
            }
            
            // 更新动画按钮
            for (int i = 0; i < 9; i++) {
                ItemStack item = holder.getInventory().getItem(i + 9);
                if (item != null && item.getType() != Material.AIR) {
                    ItemStack newItem = new ItemStack(item);
                    ItemMeta meta = newItem.getItemMeta();
                    if (meta != null) {
                        meta.setDisplayName(sagaLib.parseColor(
                            String.format("&%d淡入淡出效果 (%.1f%%)", 
                                (int)(opacity * 14), opacity * 100)));
                        newItem.setItemMeta(meta);
                        holder.getInventory().setItem(i + 9, newItem);
                    }
                }
            }
        }, 0L, 5L);
    }
    
    @Override
    public void stop() {
        running = false;
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "fade";
    }
    
    @Override
    public String getDescription() {
        return "淡入淡出动画效果";
    }

    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
```

#### 旋转效果

```java
final GuiAnimation rotateAnimation = new GuiAnimation() {
    private boolean running = false;
    private int taskId = -1;
    private int position = 0;
    private final int[] slots = {0, 1, 2, 5, 8, 7, 6, 3}; // 顺时针旋转位置
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        position = 0;
        
        // 创建旋转效果
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                if (taskId != -1) {
                    plugin.getServer().getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
                return;
            }
            
            // 清空之前的位置
            for (int slot : slots) {
                holder.getInventory().setItem(slot, null);
            }
            
            // 更新位置
            position = (position + 1) % slots.length;
            
            // 创建新物品
            ItemStack item = new ItemStack(Material.ENDER_PEARL);
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(sagaLib.parseColor("&b旋转动画效果"));
                item.setItemMeta(meta);
            }
            
            // 设置到新位置
            holder.getInventory().setItem(slots[position], item);
        }, 0L, 5L);
    }
    
    @Override
    public void stop() {
        running = false;
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "rotate";
    }
    
    @Override
    public String getDescription() {
        return "旋转动画效果";
    }

    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
```

#### 闪烁效果

```java
final GuiAnimation blinkAnimation = new GuiAnimation() {
    private boolean running = false;
    private int taskId = -1;
    private boolean visible = true;
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        visible = true;
        
        // 创建闪烁效果
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                if (taskId != -1) {
                    plugin.getServer().getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
                // 确保最后状态是可见的
                setVisibility(holder, true);
                return;
            }
            
            // 切换可见性
            visible = !visible;
            setVisibility(holder, visible);
        }, 0L, 10L);
    }
    
    private void setVisibility(GuiHolder holder, boolean visible) {
        for (int i = 0; i < 9; i++) {
            int slot = i + 9;
            if (visible) {
                ItemStack item = new ItemStack(Material.GOLD_INGOT);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(sagaLib.parseColor("&e闪烁动画效果"));
                    item.setItemMeta(meta);
                }
                holder.getInventory().setItem(slot, item);
            } else {
                holder.getInventory().setItem(slot, null);
            }
        }
    }
    
    @Override
    public void stop() {
        running = false;
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "blink";
    }
    
    @Override
    public String getDescription() {
        return "闪烁动画效果";
    }

    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
```

#### 彩虹效果（颜色变化）

```java
final GuiAnimation rainbowAnimation = new GuiAnimation() {
    private boolean running = false;
    private int taskId = -1;
    private int colorIndex = 0;
    private final Material[] colors = {
        Material.RED_STAINED_GLASS_PANE,
        Material.ORANGE_STAINED_GLASS_PANE,
        Material.YELLOW_STAINED_GLASS_PANE,
        Material.LIME_STAINED_GLASS_PANE,
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        Material.BLUE_STAINED_GLASS_PANE,
        Material.PURPLE_STAINED_GLASS_PANE
    };
    
    @Override
    public void start(GuiHolder holder) {
        running = true;
        colorIndex = 0;
        
        // 创建彩虹效果
        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                if (taskId != -1) {
                    plugin.getServer().getScheduler().cancelTask(taskId);
                    taskId = -1;
                }
                return;
            }
            
            // 更新颜色索引
            colorIndex = (colorIndex + 1) % colors.length;
            
            // 更新所有物品颜色
            for (int i = 0; i < 9; i++) {
                ItemStack item = new ItemStack(colors[colorIndex]);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(sagaLib.parseColor("&c彩&6虹&e动&a画&b效&9果"));
                    item.setItemMeta(meta);
                }
                holder.getInventory().setItem(i + 9, item);
            }
        }, 0L, 10L);
    }
    
    @Override
    public void stop() {
        running = false;
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public String getName() {
        return "rainbow";
    }
    
    @Override
    public String getDescription() {
        return "彩虹动画效果";
    }

    @Override
    public String getStatus() {
        return running ? "START" : "STOP";
    }
};
```
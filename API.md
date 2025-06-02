# SagaLib 开发者文档

## 项目概述

SagaLib 是一个基于 Paper 1.20.1 的 Minecraft 插件开发库，提供 GUI 系统、全息文本系统、配置管理等通用功能模块。

**版本**: 1.0.0
**API版本**: 1.20
**依赖**: PlaceholderAPI

## 快速开始

### 获取API实例
```java
SagaLibAPI api = SagaLibAPI.getInstance();
```

## 核心API接口

### SagaLibAPI 主要方法

#### GUI系统API
```java
// 创建GUI
GuiHolder createGui(Player player, String title, int size)
GuiHolder createPaginationGui(Player player, String title, int itemsPerPage)
GuiHolder createDynamicGui(Player player, String title, int minSize, int maxSize, int contentSize)

// GUI操作
void applyStyle(GuiHolder holder, GuiStyle style)
void addButton(GuiHolder holder, int slot, GuiButton button)
void addButtons(GuiHolder holder, Map<Integer, GuiButton> buttons)
void addItem(GuiHolder holder, int slot, ItemStack item)
void addItems(GuiHolder holder, Map<Integer, ItemStack> items)
void setLayout(GuiHolder holder, GuiLayout layout)

// 样式和布局
GuiStyle createDefaultStyle()
GuiStyle createCustomStyle(Material borderMaterial, Material backgroundMaterial, Material buttonMaterial)
GuiLayout createCustomLayout(int rows, int columns, int buttonSpacing, int borderWidth)

// 动画控制
void addAnimation(GuiHolder holder, GuiAnimation animation)
void startAnimation(GuiHolder holder, GuiAnimation animation)
void stopAnimation(GuiHolder holder, GuiAnimation animation)
String getAnimationStatus(GuiHolder holder, GuiAnimation animation)

// 分页控制
int getCurrentPage(GuiHolder holder)
void setPage(GuiHolder holder, int page)
```

#### 全息文本系统API
```java
// 基础全息文本
String createHologram(Location location, String text)
String createHologram(Location location, String text, Color bgColor)
String createHologram(Location location, String text, Color bgColor,
                     TextDisplay.TextAlignment alignment, int lineWidth,
                     byte opacity, boolean shadowed, boolean seeThrough)

// 全息文本操作
boolean removeHologram(String id)
boolean updateHologramText(String id, String text)
boolean updateHologramLocation(String id, Location location)
boolean updateHologramStyle(String id, Color bgColor, TextDisplay.TextAlignment alignment,
                           int lineWidth, byte opacity, boolean shadowed, boolean seeThrough)
TextDisplay getHologramEntity(String id)

// 进度条系统
String createProgressBar(Location location, float width, float height,
                        double progress, Color fillColor, Color bgColor)
boolean updateProgressBar(String id, double progress)
String createCircularProgressBar(Location location, float radius,
                                double progress, Color fillColor, Color bgColor)
boolean updateCircularProgressBar(String id, double progress)
String createVerticalProgressBar(Location location, float width, float height,
                                double progress, Color fillColor, Color bgColor)
boolean updateVerticalProgressBar(String id, double progress)

// 信息面板系统
String createInteractivePanel(Location location, String title, String content,
                             String interactionTag, Color bgColor)
boolean updatePanel(String id, String title, String content)
boolean setPanelInteractionTag(String id, String interactionTag)
String createMultilinePanel(Location location, String title, String[] lines, Color bgColor)
String createAutoUpdatePanel(Location location, String title, String initialContent,
                            int updateInterval, Supplier<String> contentSupplier, Color bgColor)
boolean stopAutoUpdate(String id)

// 全息动画系统
HologramAnimation createScrollingTextAnimation(String text, int speed, boolean loop)
HologramAnimation createFadeAnimation(int fadeInTime, int stayTime, int fadeOutTime)
HologramAnimation createColorChangeAnimation(List<Color> colors, int interval, boolean smooth)
boolean startHologramAnimation(String hologramId, String animationId)
boolean stopHologramAnimation(String hologramId, String animationId)
```

#### 配置系统API
```java
// 配置管理
ConfigManager getConfigManager()
LanguageManager getLanguageManager()
void reloadConfig()
FileConfiguration getMessageConfig()
FileConfiguration getDebugMessageConfig()
```

#### 工具类API
```java
// 文本和颜色处理
void setItemLore(ItemStack item, List<String> lore)
String parseColor(String text)
String parsePlaceholders(Player player, String text)
TextUtil getTextUtil()
ColorUtil getColorUtil()
GuiUtil getGuiUtil()

// PlaceholderAPI集成
boolean hasPlaceholderAPI()
void registerPlaceholder(String name, Function<Player, String> placeholder)
```

## GUI系统详解

### GuiHolder 接口
```java
// 基础操作
String getTitle()
int getSize()
void setItem(int slot, ItemStack item)
ItemStack getItem(int slot)
void clearItem(int slot)
void clearAllItems()
void update()

// 按钮管理
void addButton(int slot, GuiButton button)

// 样式和布局
void setStyle(GuiStyle style)
GuiStyle getStyle()
void setLayout(GuiLayout layout)
GuiLayout getLayout()

// 动画管理
void setAnimation(GuiAnimation animation)
GuiAnimation getAnimation()
void addAnimation(GuiAnimation animation)
void stopAnimation()

// 玩家信息
Player getPlayer()
```

### BaseGuiHolder 实现类
```java
// 构造方法
BaseGuiHolder(Player player, String title, int size)
BaseGuiHolder(Player player, Inventory inventory)

// 扩展方法
void removeButton(int slot)
GuiButton getButton(int slot)
void handleClick(int slot, InventoryClickEvent event)
void applyLayout(GuiButton[] buttons)
void startAnimation(GuiAnimation animation)
void stopAnimation(GuiAnimation animation)
Map<String, GuiAnimation> getAnimations()
String getAnimationStatus(GuiAnimation animation)
```

### GuiButton 接口
```java
ItemStack getItem(Player player)
int getSlot()
void setSlot(int slot)
void onClick(InventoryClickEvent event)
void update(Player player)
```

### SimpleButton 实现类
```java
// 构造方法
SimpleButton(Material material, String name)

// 配置方法
SimpleButton setLore(List<String> lore)
SimpleButton setAmount(int amount)
SimpleButton setClickHandler(Consumer<InventoryClickEvent> handler)
```

### GuiStyle 接口
```java
// 材质获取
Material getBorderMaterial()
Material getBackgroundMaterial()
Material getButtonMaterial()

// 颜色获取
String getTitleColor(Player player)
String getButtonNameColor(Player player)
String getButtonLoreColor(Player player)

// 物品创建
ItemStack createBorderItem(Player player)
ItemStack createBackgroundItem(Player player)
ItemStack createButtonItem(Player player, String name)
ItemStack getBackgroundItem(Player player)
ItemStack getBorderItem(Player player)

// 样式信息
String getName(Player player)
String getDescription(Player player)
```

### DefaultStyle 实现类
```java
// 构造方法
DefaultStyle()
DefaultStyle(String name, String description, Material borderMaterial,
            Material backgroundMaterial, String borderName, String backgroundName)
```

### GuiLayout 接口
```java
void apply(GuiHolder holder, GuiButton[] buttons)
String getName()
String getDescription()
```

### PaginationLayout 实现类
```java
// 构造方法
PaginationLayout(int itemsPerPage, int columns, int startRow, int startColumn)

// 分页控制
int getCurrentPage()
void setCurrentPage(int page)
```

## 全息文本系统详解

### HologramManager 核心方法
```java
// 基础全息文本
String createHologram(Location location, String text)
String createHologram(Location location, String text, Color bgColor)
String createHologram(Location location, String text, Color bgColor,
                     TextDisplay.TextAlignment alignment, int lineWidth,
                     byte opacity, boolean shadowed, boolean seeThrough)

// 全息文本管理
boolean removeHologram(String id)
TextDisplay getHologram(String id)
boolean updateText(String id, String text)
boolean updateLocation(String id, Location location)
boolean updateStyle(String id, Color bgColor, TextDisplay.TextAlignment alignment,
                   int lineWidth, byte opacity, boolean shadowed, boolean seeThrough)

// 进度条管理
String createProgressBar(Location location, float width, float height,
                        double progress, Color fillColor, Color bgColor)
boolean updateProgressBar(String id, double progress)
TextDisplay getProgressBar(String id)

// 圆形进度条
String createCircularProgressBar(Location location, float radius,
                                double progress, Color fillColor, Color bgColor)
boolean updateCircularProgressBar(String id, double progress)

// 垂直进度条
String createVerticalProgressBar(Location location, float width, float height,
                                double progress, Color fillColor, Color bgColor)
boolean updateVerticalProgressBar(String id, double progress)

// 信息面板
String createInteractivePanel(Location location, String title, String content,
                             String interactionTag, Color bgColor)
boolean updatePanel(String id, String title, String content)
boolean setPanelInteractionTag(String id, String interactionTag)
String createMultilinePanel(Location location, String title, String[] lines, Color bgColor)
String createAutoUpdatePanel(Location location, String title, String initialContent,
                            int updateInterval, Supplier<String> contentSupplier, Color bgColor)
boolean stopAutoUpdate(String id)

// 清理方法
void clearAllHolograms()
void clearAllProgressBars()
Map<String, UUID> getAllHolograms()
```

### HologramAnimation 接口
```java
void start(TextDisplay textDisplay)
void stop(TextDisplay textDisplay)
void pause(TextDisplay textDisplay)
void resume(TextDisplay textDisplay)
String getId()
AnimationStatus getStatus()

// 动画状态枚举
enum AnimationStatus { START, STOP, PAUSE, RESUME }
```

## 配置管理系统

### ConfigManager 方法
```java
// 构造方法
ConfigManager(Plugin plugin)

// 配置获取
FileConfiguration getConfig(String name)
FileConfiguration getConfig()
FileConfiguration getMessageConfig()
FileConfiguration getDebugMessageConfig()

// 配置重载
void reloadConfig(String name)
void reloadAllConfigs()
void reloadConfig()

// 配置保存
void saveConfig(String name)
void saveAllConfigs()

// 配置管理
boolean createConfig(String name)
boolean deleteConfig(String name)
```

### LanguageManager 方法
```java
// 构造方法
LanguageManager(Plugin plugin)

// 文本获取
String getText(String key)
String getText(String key, String language)

// 语言管理
void setCurrentLanguage(String language)
String getCurrentLanguage()
String[] getSupportedLanguages()

// 重载和保存
void reload()
void reloadConfig()
void saveLanguage(String language)
void saveAllLanguages()
```

## 工具类详解

### ColorUtil 静态方法
```java
// 颜色转换
static String translateColor(String text)
static String stripColor(String text)

// 染料颜色
static DyeColor getDyeColor(String color)
static Material getStainedGlassPane(String color)
static Material getStainedGlass(String color)
static Material getWool(String color)
```

### TextUtil 静态方法
```java
// 占位符处理
static String replacePlaceholders(String text, Object... replacements)
static List<String> getPlaceholders(String text)

// 文本处理
static List<String> splitText(String text, int maxLength)
static int getLength(String text)
static String truncate(String text, int maxLength, String suffix)
static String repeat(String text, int count)

// 文本对齐
static String center(String text, int width)
static String align(String text, int width, TextAlignment alignment)

// 对齐方式枚举
enum TextAlignment { LEFT, CENTER, RIGHT }
```

### GuiUtil 静态方法
```java
// GUI创建
static Inventory createGui(String title, int size)

// GUI填充
static void fillBorder(Inventory inventory, Material material)
static void fillBorder(Inventory inventory, ItemStack item)
static void fillGui(Inventory inventory, Material material)
static void fillGui(Inventory inventory, ItemStack item)

// 预设物品
static ItemStack createPlaceholder()
static ItemStack createBackButton()
static ItemStack createCloseButton()
```

### ItemBuilder 方法
```java
// 构造方法
ItemBuilder(Material material)
ItemBuilder(ItemStack item)

// 属性设置
ItemBuilder setName(String name)
ItemBuilder setLore(String... lore)
ItemBuilder setLore(List<String> lore)
ItemBuilder addLore(String... lore)
ItemBuilder setAmount(int amount)

// 附魔和标志
ItemBuilder addEnchantment(Enchantment enchantment, int level)
ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments)
ItemBuilder addItemFlags(ItemFlag... flags)
ItemBuilder setUnbreakable(boolean unbreakable)
ItemBuilder setCustomModelData(Integer modelData)

// 构建
ItemStack build()
```

## 示例代码

### 创建简单GUI
```java
SagaLibAPI api = SagaLibAPI.getInstance();
GuiHolder holder = api.createGui(player, "&6示例GUI", 27);
api.applyStyle(holder, api.createDefaultStyle());

SimpleButton button = new SimpleButton(Material.DIAMOND, "&b钻石按钮")
    .setLore(Arrays.asList("&7点击获取钻石"))
    .setClickHandler(event -> {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
        player.sendMessage("&a获得钻石！");
    });

api.addButton(holder, 13, button);
player.openInventory(holder.getInventory());
```

### 创建全息文本
```java
SagaLibAPI api = SagaLibAPI.getInstance();
Location location = player.getLocation().add(0, 2, 0);

// 基础全息文本
String id = api.createHologram(location, "&6欢迎来到服务器！");

// 带样式的全息文本
String styledId = api.createHologram(
    location,
    "&a服务器状态：在线",
    Color.fromRGB(0, 100, 0),
    TextDisplay.TextAlignment.CENTER,
    200,
    (byte) 127,
    true,
    false
);

// 创建进度条
String progressId = api.createProgressBar(
    location.add(0, -1, 0),
    3.0f, 0.5f,
    0.75, // 75%进度
    Color.GREEN,
    Color.BLACK
);
```

### 创建动画
```java
// 滚动文本动画
HologramAnimation scrollAnimation = api.createScrollingTextAnimation(
    "这是一个滚动的文本示例",
    10, // 10tick间隔
    true // 循环
);

// 启动动画
api.startHologramAnimation(hologramId, scrollAnimation.getId());
```
# SagaLib

SagaLib 是一个基于 Spigot 1.21.4 的通用功能库，旨在简化 Minecraft 插件开发过程，提供常用功能的统一实现。

## 项目概述

本项目主要解决以下问题：
1. 减少重复代码编写
2. 统一功能实现标准
3. 提供简单易用的API接口
4. 确保代码质量和可维护性

> **重要说明**: 本项目严格遵循 Spigot 官方API规范，所有GUI相关功能都基于 `org.bukkit.inventory.InventoryHolder` 接口实现，确保与Minecraft原生系统的完全兼容性。

## 技术栈

- Java 21
- Spigot 1.21.4
- Maven

## 项目结构

```
src/main/java/cn/i7mc/sagalib/
├── SagaLib.java                    # 插件主类
├── api/                           # API接口层
│   ├── SagaLibAPI.java            # API接口定义
│   └── impl/                      # API实现
│       └── SagaLibAPIImpl.java    # API实现类
├── gui/                           # GUI相关功能
│   ├── holder/                    # GUI容器相关
│   │   ├── boat/                  # 船只类型容器
│   │   │   ├── AcaciaChestBoatGuiHolder.java      # 金合欢木带箱子的船容器
│   │   │   ├── BambooChestRaftGuiHolder.java      # 竹筏带箱子的船容器
│   │   │   ├── BirchChestBoatGuiHolder.java       # 白桦木带箱子的船容器
│   │   │   ├── CherryChestBoatGuiHolder.java      # 樱花木带箱子的船容器
│   │   │   ├── ChestBoatGuiHolder.java            # 基础带箱子的船容器
│   │   │   ├── DarkOakChestBoatGuiHolder.java     # 深色橡木带箱子的船容器
│   │   │   ├── JungleChestBoatGuiHolder.java      # 丛林木带箱子的船容器
│   │   │   ├── MangroveChestBoatGuiHolder.java    # 红树木带箱子的船容器
│   │   │   ├── OakChestBoatGuiHolder.java         # 橡木带箱子的船容器
│   │   │   ├── PaleOakChestBoatGuiHolder.java     # 苍白橡木带箱子的船容器
│   │   │   └── SpruceChestBoatGuiHolder.java      # 云杉木带箱子的船容器
│   │   ├── container/             # 容器类型
│   │   │   ├── BarrelGuiHolder.java               # 木桶容器
│   │   │   ├── BlastFurnaceGuiHolder.java         # 高炉容器
│   │   │   ├── BlockInventoryHolderGuiHolder.java # 方块物品栏持有者容器
│   │   │   ├── BrewingStandGuiHolder.java         # 酿造台容器
│   │   │   ├── ChestGuiHolder.java                # 箱子容器
│   │   │   ├── ChiseledBookshelfGuiHolder.java    # 錾制书架容器
│   │   │   ├── CrafterGuiHolder.java              # 合成器容器
│   │   │   ├── DecoratedPotGuiHolder.java         # 装饰罐容器
│   │   │   ├── DispenserGuiHolder.java            # 发射器容器
│   │   │   ├── DoubleChestGuiHolder.java          # 大箱子容器
│   │   │   ├── DropperGuiHolder.java              # 投掷器容器
│   │   │   ├── FurnaceGuiHolder.java              # 熔炉容器
│   │   │   ├── HopperGuiHolder.java               # 漏斗容器
│   │   │   ├── HopperMinecartGuiHolder.java       # 漏斗矿车容器
│   │   │   ├── JukeboxGuiHolder.java              # 唱片机容器
│   │   │   ├── LecternGuiHolder.java              # 讲台容器
│   │   │   ├── ShulkerBoxGuiHolder.java           # 潜影盒容器
│   │   │   ├── SmokerGuiHolder.java               # 烟熏炉容器
│   │   │   └── StorageMinecartGuiHolder.java      # 运输矿车容器
│   │   ├── entity/                # 实体类型
│   │   │   ├── AbstractHorseGuiHolder.java        # 抽象马类容器
│   │   │   ├── AbstractVillagerGuiHolder.java     # 抽象村民容器
│   │   │   ├── AllayGuiHolder.java                # 悦灵容器
│   │   │   ├── CamelGuiHolder.java                # 骆驼容器
│   │   │   ├── ChestedHorseGuiHolder.java         # 可装箱的马容器
│   │   │   ├── DonkeyGuiHolder.java               # 驴容器
│   │   │   ├── HorseGuiHolder.java                # 马容器
│   │   │   ├── HumanEntityGuiHolder.java          # 人形实体容器
│   │   │   └── LlamaGuiHolder.java                # 羊驼容器
│   │   ├── BaseGuiHolder.java     # 基础GUI容器
│   │   └── GuiHolder.java         # GUI容器接口
│   ├── button/                    # 按钮相关
│   │   ├── GuiButton.java         # 按钮接口
│   │   └── SimpleButton.java      # 简单按钮实现
│   ├── style/                     # 样式相关
│   │   ├── GuiStyle.java          # 样式接口
│   │   └── DefaultStyle.java      # 默认样式实现
│   ├── layout/                    # 布局相关
│   │   ├── GuiLayout.java         # 布局接口
│   │   ├── GridLayout.java        # 网格布局实现
│   │   ├── CustomLayout.java      # 自定义布局实现
│   │   └── PaginationLayout.java  # 分页布局实现
│   ├── animation/                 # 动画相关
│   │   ├── FadeAnimation.java     # 淡入淡出动画实现
│   │   └── GuiAnimation.java      # 动画接口
│   └── listener/                  # 事件监听相关
│       └── GuiListener.java       # GUI事件监听器
├── config/                        # 配置相关
│   ├── ConfigManager.java         # 配置管理器
│   └── LanguageManager.java       # 语言管理器
├── util/                          # 工具类
│   ├── ItemBuilder.java           # 物品构建器
│   ├── GuiUtil.java               # GUI工具类
│   ├── ColorUtil.java             # 颜色工具类
│   └── TextUtil.java              # 文本工具类
├── command/                       # 命令相关
│   └── ExampleCommand.java        # 示例命令类
└── example/                       # 示例代码
    └── GuiExample.java            # GUI示例类

src/main/resources/                # 资源文件
├── config.yml                     # 主配置文件
├── message.yml                    # 消息配置文件
├── debugmessage.yml               # 调试消息配置
├── plugin.yml                     # 插件配置文件
└── languages/                     # 语言文件目录
    └── zh_CN.yml                  # 中文语言文件

src/test/java/cn/i7mc/sagalib/     # 测试代码
└── util/                          # 工具类测试
    ├── ColorUtilTest.java         # 颜色工具测试
    └── TextUtilTest.java          # 文本工具测试
```

> 注：所有GUI类型均基于Spigot官方API中的`org.bukkit.inventory.InventoryHolder`接口实现，确保与Minecraft原生系统的完全兼容性。

## 已完成功能
1. 基础框架
   - 项目结构搭建
   - 配置系统
   - 工具类
   - 事件系统

2. GUI系统
   - 基础容器
     - 木桶 (Barrel)
     - 箱子 (Chest)
     - 大箱子 (DoubleChest)
     - 潜影盒 (ShulkerBox)
     - 高炉 (BlastFurnace)
     - 酿造台 (BrewingStand)
   - 功能性容器
     - 合成器 (Crafter)
     - 发射器 (Dispenser)
     - 投掷器 (Dropper)
     - 熔炉 (Furnace)
     - 漏斗 (Hopper)
     - 漏斗矿车 (HopperMinecart)
     - 唱片机 (Jukebox)
     - 讲台 (Lectern)
     - 烟熏炉 (Smoker)
     - 运输矿车 (StorageMinecart)
   - 装饰性容器
     - 錾制书架 (ChiseledBookshelf)
     - 装饰罐 (DecoratedPot)
   - 实体类型容器
     - 人形实体 (HumanEntity)
     - 玩家 (Player)
     - 抽象马类 (AbstractHorse)
     - 骆驼 (Camel)
     - 可装箱的马 (ChestedHorse)
     - 驴 (Donkey)
     - 马 (Horse)
     - 羊驼 (Llama)
     - 骡子 (Mule)
     - 骷髅马 (SkeletonHorse)
     - 商人羊驼 (TraderLlama)
     - 僵尸马 (ZombieHorse)
     - 抽象村民 (AbstractVillager)
     - 村民 (Villager)
     - 流浪商人 (WanderingTrader)
     - 悦灵 (Allay)
     - 猪灵 (Piglin)
     - 掠夺者 (Pillager)
   - 船只类型容器
     - 基础带箱子的船 (ChestBoat)
     - 金合欢木带箱子的船 (AcaciaChestBoat)
     - 竹筏带箱子的船 (BambooChestRaft)
     - 白桦木带箱子的船 (BirchChestBoat)
     - 樱花木带箱子的船 (CherryChestBoat)
     - 深色橡木带箱子的船 (DarkOakChestBoat)
     - 丛林木带箱子的船 (JungleChestBoat)
     - 红树木带箱子的船 (MangroveChestBoat)
     - 橡木带箱子的船 (OakChestBoat)
     - 苍白橡木带箱子的船 (PaleOakChestBoat)
     - 云杉木带箱子的船 (SpruceChestBoat)
   - 方块容器
     - 方块物品栏持有者 (BlockInventoryHolder)
   - 按钮系统
   - 样式系统
   - 布局系统
   - 动画系统
   - 分页系统

3. API接口
   - 接口层设计
   - 核心方法实现
   - PlaceholderAPI集成
   - 自定义占位符系统(带saga_前缀)

4. 工具类
   - ColorUtil (颜色工具类)
     - 支持十六进制颜色代码
     - 支持传统颜色代码
     - 支持染料颜色转换
     - 支持染色玻璃和羊毛材质
   - TextUtil (文本工具类)
     - 支持文本对齐
     - 支持占位符替换
     - 支持文本分割
     - 支持文本截断
     - 支持文本重复
     - 支持文本居中

5. 文档和示例
   - API使用文档
   - 配置说明文档
   - 示例插件
   - 最佳实践指南

## 开发计划

1. 全息文本系统 (TextDisplay)
   - 基于官方API: [TextDisplay](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html)
   - 基础功能
     - 创建全息文本
     - 删除全息文本
     - 更新全息文本
     - 移动全息文本
     - PlaceholderAPI占位符支持
   - TextDisplay核心方法实现
     - 文本内容管理
       - [getText()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#getText()) - 获取显示的文本
       - [setText(String text)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setText(java.lang.String)) - 设置显示的文本
     - 文本布局控制
       - [getLineWidth()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#getLineWidth()) - 获取换行前的最大行宽
       - [setLineWidth(int width)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setLineWidth(int)) - 设置换行前的最大行宽
     - 文本样式设置
       - [getBackgroundColor()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#getBackgroundColor()) - 获取文本背景颜色
       - [setBackgroundColor(Color color)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setBackgroundColor(org.bukkit.Color)) - 设置文本背景颜色
       - [getTextOpacity()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#getTextOpacity()) - 获取文本不透明度
       - [setTextOpacity(byte opacity)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setTextOpacity(byte)) - 设置文本不透明度
       - [isShadowed()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#isShadowed()) - 获取文本是否有阴影
       - [setShadowed(boolean shadow)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setShadowed(boolean)) - 设置文本是否有阴影
       - [isSeeThrough()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#isSeeThrough()) - 获取文本是否透视
       - [setSeeThrough(boolean seeThrough)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setSeeThrough(boolean)) - 设置文本是否透视
       - [isDefaultBackground()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#isDefaultBackground()) - 获取文本是否使用默认背景
       - [setDefaultBackground(boolean defaultBackground)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setDefaultBackground(boolean)) - 设置文本是否使用默认背景
     - 文本对齐方式
       - [getAlignment()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#getAlignment()) - 获取文本对齐方式
       - [setAlignment(TextAlignment alignment)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/TextDisplay.html#setAlignment(org.bukkit.entity.TextDisplay.TextAlignment)) - 设置文本对齐方式
   - Display接口继承方法
     - 基础显示设置
       - [getBillboard()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getBillboard()) - 获取广告牌模式
       - [setBillboard(Display.Billboard billboard)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setBillboard(org.bukkit.entity.Display.Billboard)) - 设置广告牌模式
       - [getDisplayHeight()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getDisplayHeight()) - 获取显示高度
       - [setDisplayHeight(float height)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setDisplayHeight(float)) - 设置显示高度
       - [getDisplayWidth()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getDisplayWidth()) - 获取显示宽度
       - [setDisplayWidth(float width)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setDisplayWidth(float)) - 设置显示宽度
     - 变换与位置
       - [getTransformation()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getTransformation()) - 获取变换
       - [setTransformation(Transformation transformation)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setTransformation(org.bukkit.util.Transformation)) - 设置变换
       - [getTransformationMatrix()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getTransformationMatrix()) - 获取变换矩阵
       - [setTransformationMatrix(Matrix4f transformationMatrix)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setTransformationMatrix(org.joml.Matrix4f)) - 设置变换矩阵
     - 亮度控制
       - [getBrightness()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getBrightness()) - 获取亮度
       - [setBrightness(Display.Brightness brightness)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setBrightness(org.bukkit.entity.Display.Brightness)) - 设置亮度
     - 视觉效果
       - [getViewRange()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getViewRange()) - 获取可视范围
       - [setViewRange(float range)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setViewRange(float)) - 设置可视范围
       - [getShadowRadius()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getShadowRadius()) - 获取阴影半径
       - [setShadowRadius(float radius)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setShadowRadius(float)) - 设置阴影半径
       - [getShadowStrength()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getShadowStrength()) - 获取阴影强度
       - [setShadowStrength(float strength)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setShadowStrength(float)) - 设置阴影强度
     - 其他属性
       - [getInterpolationDuration()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getInterpolationDuration()) - 获取插值持续时间
       - [setInterpolationDuration(int duration)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setInterpolationDuration(int)) - 设置插值持续时间
       - [getInterpolationDelay()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#getInterpolationDelay()) - 获取插值延迟
       - [setInterpolationDelay(int delay)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setInterpolationDelay(int)) - 设置插值延迟
       - [isTeleportDuration()](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#isTeleportDuration()) - 获取是否使用传送持续时间
       - [setTeleportDuration(boolean teleportDuration)](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Display.html#setTeleportDuration(boolean)) - 设置是否使用传送持续时间
   - 扩展功能
     - 全息文本动画系统
       - 文本滚动动画
       - 淡入淡出动画
       - 颜色变换动画
     - 全息进度条系统
       - 水平进度条
       - 垂直进度条
       - 圆形进度条
     - 全息信息面板
       - 多行文本面板
       - 自动更新面板
       - 交互式面板

## 项目状态与进度

### 最新状态（2024年7月更新）

1. 全息文本系统已完成基础功能实现，当前进度：
   - 基础功能
     - 创建全息文本 ✅
     - 删除全息文本 ✅
     - 更新全息文本 ✅
     - 移动全息文本 ✅
     - 支持背景颜色设置 ✅
     - 支持透明度设置 ✅
     - 支持广告牌样式设置 ✅
     - 命令参数自动补全 ✅

   - 动画功能
     - 文本滚动动画 ✅
     - 淡入淡出动画 ✅
     - 颜色变换动画 ✅
     - 动画创建与控制命令 ✅
     - 参数解析与异常处理优化 ✅

   - 全息进度条系统
     - 水平进度条 ✅
     - 垂直进度条 ✅
     - 圆形进度条 ✅

   - 全息信息面板
     - 多行文本面板 ✅
     - 自动更新面板 ✅
     - 交互式面板 ✅

2. 最近变更
   - 增强了全息文本创建命令，支持背景颜色、透明度和广告牌样式参数
   - 修复了动画创建命令的参数解析和异常处理
   - 添加了命令参数自动补全功能，提升了用户操作体验
   - 优化了全息文本系统的错误提示与使用指南

3. 下一步计划
   - 优化全息文本的性能，减少资源占用
   - 增加更多动画效果与面板类型
   - 完善全息文本与实体交互机制
   - 添加持久化存储功能，支持服务器重启后恢复全息文本

> 注：所有实现均严格遵循 Spigot 1.21.4 官方API规范，确保插件的稳定性和兼容性。
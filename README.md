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
└── plugin.yml                     # 插件配置文件

src/test/java/cn/i7mc/sagalib/     # 测试代码
└── util/                          # 工具类测试
    ├── ColorUtilTest.java         # 颜色工具测试
    └── TextUtilTest.java          # 文本工具测试
```


### 已完成功能
1. 基础框架
   - 项目结构搭建
   - 配置系统
   - 工具类
   - 事件系统

2. GUI系统
   - 基础容器
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
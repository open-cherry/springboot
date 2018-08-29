# 工程说明
1. 本工程为spring-boot2.x的web项目工程
2. 项目集成了访问校验、jpa数据访问、redis缓存等功能
3. 实现了jpa自定义接口基类实现
4. 实现了自定义数据渲染过滤等功能
5. 为了方便管理和理解在工程内合理的进行了分包

## 项目结构

### java源代码
```
com.example  
    -- base         公用的基类包
    -- config       自动配置层
    -- controller   请求控制层
    -- dao          数据访问层
    -- entity       数据访问实体层
    -- proto        请求访问协议定义
    -- repository   数据仓库定义
    -- service      业务服务层
    -- util         工具包
    -- valid        请求验证过滤
    -- vo           view层值对象
    DemoApplication

```
### resources
存放系统资源文件
```
1. 静态文件
2. 模板文件
3. 系统配置
4. spring-boot核心文件:application.properties
5. 校验资源文件：ValidationMessages.properties
```

## 使用工具说明
本项目是基于Intellij IDEA 开发，请上官网下载最新版本
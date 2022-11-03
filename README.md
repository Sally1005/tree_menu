# 项目介绍

该工程的主要目的是练习如何制作树形菜单。前后端分离，前端项目名为treemenu-ui,后端的项目名为treemenu-web。目前已实现的功能有将数据存储在数据库中，模糊查询树的所有内容。在后续的学习中，会慢慢补充新的功能。



# 开发环境

其后端开发环境为IDEA + MySQL + Druid + SpringBoot + MyBatis。

项目演示地址：http://localhost:8080/treeMenu/getMenuTree   
           http://localhost:8080/treeMenu/selectByMenuName



# 目录结构

```          
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─lonton
│  │  │          └─tree
│  │  │              └─treemenu
│  │  │                  ├─common
│  │  │                  │  ├─exception     异常包      
│  │  │                  │  └─util          工具包     
│  │  │                  ├─controller       控制层  
│  │  │                  ├─entity           内含实体类   
│  │  │                  └─mapper           持久层                                       
│  │  └─resources                           配置   
│  └─test                                   测试  
```


# 项目使用

拿到项目后直接启动TreeMenuApplication，在浏览器中访问http://localhost:8080/treeMenu/selectAllWithTree / http://localhost:8080/treeMenu/selectByWd 即可。

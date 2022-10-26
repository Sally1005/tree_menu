# 项目介绍

该工程的主要目的是练习如何制作树形菜单。前后端分离，前端项目名为treemenu-ui,后端的项目名为treemenu-web。目前已实现的功能有将数据存储在数据库中，模糊查询树的所有内容。在后续的学习中，会慢慢补充新的功能。



# 开发环境

其后端开发环境为IDEA + mysql + druid + JDK1.8 + SpringBoot + mybatis。

项目演示地址：http://localhost:8080/treeMenu/selectAllWithTree    
           http://localhost:8080/treeMenu/selectByWd



# 目录结构

```
# 
├─.idea        
├─.mvn
│  └─wrapper
│          maven-wrapper.jar
│          maven-wrapper.properties
│          MavenWrapperDownloader.java      
├─lib
│      c3p0-0.9.1.2.jar
│      commons-dbutils-1.7.jar
│      druid-1.1.10.jar
│      mysql-connector-java-8.0.15.jar     
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─lonton
│  │  │          └─tree
│  │  │              └─treemenu
│  │  │                  │  TreeMenuApplication.java
│  │  │                  ├─common
│  │  │                  │  ├─exception
│  │  │                  │  │      GlobalException.java
│  │  │                  │  │      GlobalExceptionHandler.java      
│  │  │                  │  └─util
│  │  │                  │          Result.java
│  │  │                  │          ToolDruid.java          
│  │  │                  ├─controller
│  │  │                  │      TreeMenuController.java
│  │  │                  ├─dao
│  │  │                  │      BasicDao.java
│  │  │                  │      ITreeMenuDao.java
│  │  │                  │      NodeDao.java 
│  │  │                  ├─entity
│  │  │                  │      TreeMenu.java  
│  │  │                  └─mapper
│  │  │                          TreeMenuMapper.java                      
│  │  └─resources
│  │      │  application.properties
│  │      │  druid.properties
│  │      ├─db
│  │      │      treemenu   
│  │      ├─mapper
│  │      │      TreeMenuMapper.xml   
│  │      ├─static
│  │      └─templates
│  └─test
│      └─java
│          └─com
│              └─lonton
│                  └─tree
│                      └─treemenu
│                              TreeMenuApplicationTests.java                        
└─target
    ├─classes
    │  │  application.properties
    │  │  druid.properties 
    │  ├─com
    │  │  └─lonton
    │  │      └─tree
    │  │          └─treemenu
    │  │              │  TreeMenuApplication.class 
    │  │              ├─common
    │  │              │  ├─exception
    │  │              │  │      GlobalException.class
    │  │              │  │      GlobalExceptionHandler.class   
    │  │              │  └─util
    │  │              │          Result.class
    │  │              │          ToolDruid.class  
    │  │              ├─controller
    │  │              │      TreeMenuController.class 
    │  │              ├─dao
    │  │              │      BasicDao.class
    │  │              │      ITreeMenuDao.class
    │  │              │      NodeDao.class    
    │  │              ├─entity
    │  │              │      TreeMenu.class
    │  │              └─mapper
    │  │                      TreeMenuMapper.xml      
    │  ├─db
    │  │      treemenu     
    │  └─mapper
    │          TreeMenuMapper.xml
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    ├─maven-status
    │  └─maven-compiler-plugin
    │      └─compile
    │          └─default-compile
    │                  createdFiles.lst
    │                  inputFiles.lst              
    └─test-classes
        └─com
            └─lonton
                └─tree
                    └─treemenu
                            TreeMenuApplicationTests.class
```



# 项目使用

拿到项目后直接启动TreeMenuApplication，在浏览器中访问http://localhost:8080/treeMenu/selectAllWithTree / http://localhost:8080/treeMenu/selectByWd 即可。

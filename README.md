# 项目介绍

该工程的主要目的是练习如何制作树形菜单。前后端分离，前端项目名为treemenu-ui，后端的项目名为treemenu-web。目前已实现的功能有将数据存储在数据库中，模糊查询树的所有内容。在后续的学习中，会慢慢补充新的功能。



# 开发环境

其后端开发环境为IDEA + MySQL + Druid + SpringBoot + MyBatis。

项目演示地址：http://localhost:8080/treeMenu/  
     



# 目录结构

```          
├─assets                                    SQL资源
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


# 项目使用（开发环境）

1. 该工程是在 JDK1.8 环境下运行的，建议运行前先自行配置好环境变量，浏览器推荐edge和Chrome，兼容性较好；
2. 首先克隆项目的地址，打开Intellij IDEA后，分别点击File > New > Project from Version Control，再将克隆下来的地址粘贴到URL框中；
3. 展开src下的目录，运行TreeMenuApplication，在终端查看是否能够正常运行；
4. 能够正常启动项目后，将assets下的sql文件拿到，在数据库中建表；
5. 打开 <http://localhost:8080/treeMenu/> 可以查看父级菜单id为0的数据，如下图：<br/>
![1](https://github.com/Sally1005/treemenu-web/blob/master/.README_images/01.png)
   打开 <http://localhost:8080/treeMenu/2/>　其中２为任意非叶子节点id，具体数据可参考数据库，如下图：<br/>
![2](https://github.com/Sally1005/treemenu-web/blob/master/.README_images/02.png)



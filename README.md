
# 项目介绍
  该工程的主要目的是练习如何制作树形菜单。
  其展示的是国家统计局统计页面（https://data.stats.gov.cn/tablequery.htm?code=AA0701）左侧的树结构，实现的功能为能将其存入数据库并在前端
页面展示树结构。

# 开发环境
   其后端开发环境为IDEA + mysql + druid + JDK1.8 + SpringBoot + mybatis,所实现的是在访问（http://localhost:8080/treeMenu/select
AllWithTree）地址时返回json数据；前端主要使用了Vue + ElementUI 展示树形数据，使用 Axios 向后台发送请求并返回数据。当访问（http://localhost
:8888/）地址时可以看到后台传回来的数据以树形的结构返回。

# 目录结构



# 项目使用
   拿到后端项目后直接启动TreeMenuApplication，在浏览器中输入（http://localhost:8080/treeMenu/selectAllWithTree）即可，拿到后端项目后
在Terminal中输入命令：npm run serve,启动完成后访问（http://localhost:8888/）即可。若端口号被占用，可以在package.json下的scripts中进行
如下修改： "serve": "vue-cli-service serve --port xxxx"。

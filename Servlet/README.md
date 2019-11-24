# Servlet

## Step

0. Maven模板默认从网络拉去，速度很慢，改为本地设置加速Maven模板生成
    * `Preferences->Build,Execution,Deployment->Build Tools->Maven->Runner`
    * VM Options添加选项: `-DarchetypeCatalog=internal`
1. 从Maven模板创建Java Web应用
    * `File->New->Module`
    * `Maven->Create from archetype->org.apache.maven.archetype:maven-archetype-webapp`
2. 因为Servlet依赖于`javax.servlet.*`包，所以用Maven添加依赖
    * 前往<https://mvnrepository.com/>搜索servlet
    * 将servlet api依赖添加至pom.xml
3. 写Servlet Java源码，参见`src/com/RunExample`
4. 编译运行
    * `Run/Debud Configurations-> + -> Tomcat Server->Local`
    * `Deployment-> + -> Artifact...-> {project}:war exploded`
    * `Deployment->Application context`修改为`/`或者其他根目录路径，在Tomcat中是各个项目文件夹名，这里可设置
    * `Run`

## Servlet实现

Servlet实现有三种方式，参见`src/com/RunExample/`三个文件

## 路由

对于Tomcat的路由规则，可以参见 <https://github.com/RunExample/Tomcat>

Idea 在Tomcat的路由基础上方便用户编写，由两部分决定，

* 一个是根目录，由`Deployment->Application context`设置
* 另一个路由通过注解生成，例如参加`src/com/RunExample/ServletImpl.java`中的`@WebServlet(...)`

```java
@WebServlet("/ServletImpl")
public class ServletImpl implements Servlet {
    //...
}
```

可以看到这种方式比tomcat原本的修改web.xml方便很多

## 测试

本项目的`Deployment->Application context`设置为`/servlets/`

根据`src/com/RunExample/xxx.java`的路由设置`@WebServlet(...)`

对应的路由地址为：

<http://localhost:8080/servlets/try/HttpServletImpl>
<http://localhost:8080/servlets/try/GenericServletImpl>
<http://localhost:8080/servlets/try/ServletImpl>

测试该url应该能得到相应结果，如果返回HTTP 404，可能路由设置不对

### Reference

Idea+Servlet+Tomcat <https://www.bilibili.com/video/av65369940?p=16>


# zhangxin

##资料

[spring资料](https://spring.io)

[spring web](https://spring.io/guides/gs/serving-web-content)

[java se](https://www.oracle.com/java/technologies/java-se-glance.html)

[bootstrap](https://v3.bootcss.com)

[java.util介绍](https://www.cnblogs.com/TestMa/p/10641367.html)

[API下载](https://blog.csdn.net/qq_19877501/article/details/108578369)

[OAuth App认证](https://docs.github.com/en/developers/apps/building-oauth-apps)

[spring boot 文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

[spring mvc](https://docs.spring.io/spring-framework/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-servlet)

[Mybatis SpringBoot](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[Mybatis generator](http://mybatis.org/generator/)

[Mybatis dynamic SQL](https://mybatis.org/mybatis-dynamic-sql/)

[okhttp：一个处理网络请求的开源项目](https://square.github.io/okhttp/) 

[thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

[jQuery](https://jquery.com/)

[markdown](http://editor.md.ipandao.com/)

[markdown_github](https://github.com/pandao/editor.md)

[postman]

##工具

[git](https://git-scm.com)

[maven](https://maven.apache.org)

[maven repository](https://mvnrepository.com)

[tomcat](https://tomcat.apache.org)

[visual paradigm](https://www.visual-paradigm.com)

[Mysql](https://www.runoob.com/mysql/mysql-tutorial.html)

[H2 database](https://h2database.com/html/main.html)

[flyway--管理数据库版本](https://flywaydb.org/)

[lombok](https://projectlombok.org/)


##tomcat、 spring、 spring mvc、 servlet、jvm 关系和联系 

##脚本

```sql
    create table user
(
    ID           INT auto_increment primary key NOT NULL ,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        VARCHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
);
```

```bash
mvn flyway:migrate
mvn flaway:repair
mvn -D mybatis.generator.overwrite=true mybatis-generator:generate
数据库脚本全部小写
```

##部署

###依赖
-git ---拉代码
-JDK ---编译
-Maven ---构建项目
-Mysql ---搭建数据库
###步骤
- yum update
- yum install git
- yum install maven
- mkdir App
- cd App
- git clone 地址
- mvn (clean) compile package
- cp 配置文件 配置文件生产
- mvn package
- java -jar -D spring.profiles.active=production target/SNAPSHOT.jar
- ps -aux | grep java

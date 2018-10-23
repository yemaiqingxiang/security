# Spring Cloud 基于网关的统一授权认证


 使用OAuth2实现多个微服务的统一认证授权,通过向`OAUTH`服务发送某个类型的`grant type`进行集中认证和授权获得`access token`,这个`access token`是受其他微服务信任的。后续访问中可以通过这个`access token`来进行。
 
 * account: 用户微服务
 * auth:  OAUTH2认证授权中心
 * zuul: 边界网关
 * eureka:  服务注册和发现
 

## 基础环境
1. 开启MySql 修改`auth`配置文件`bootstrap.yml`中的`datasource`配置mysql用户名、密码、数据库名。
2. 开启Redis 修改`auth`配置文件`bootstrap.yml`中的`redis`如果默认端口号是6379 host为 localhost 则不用修改。

## 运行

1. 运行eureka 端口号8888

2. 运行zuul    端口号8088

3. 运行auth(因为使用了JPA会自动创建数据表不用导入数据库,只需要开启mysql) 端口号5000

    * 账户1: username:`fpf`    password:`fpf`
    * 账户2: username:`wl`     password:`wl`
    
   相关的设置可以在`auth`项目中的`Init`类中看到

4. 运行account    端口号8083

## 测试
1. 通关`zuul`网关访问认证服务获取 `access token` 8088是网关端口
![](https://ws4.sinaimg.cn/large/006tKfTcly1fjxbv9b9poj318o10en4m.jpg)

2. 通过`access token`访问`auth`中的`/user`API获取用户信息
![](https://ws4.sinaimg.cn/large/006tKfTcly1fjxby3oecyj3190106n3p.jpg)

3. 使用相同的`access token`访问`account`中的`/current`API获取用户信息
![](https://ws3.sinaimg.cn/large/006tKfTcly1fjxc3bnuzvj318w0zmq8s.jpg)
可以看到都是相同的用户信息

4. 使用`access token`访问`account`中带`权限`的`/query`API
![](https://ws3.sinaimg.cn/large/006tKfTcly1fjxc604ucmj319g0mygos.jpg)

5. 使用`wl`用户重新获取`access token`访问`account`中带`权限`的`/query`API
![](https://ws4.sinaimg.cn/large/006tKfTcly1fjxc8wrybwj318g0ren0r.jpg)


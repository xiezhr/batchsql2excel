### 项目简介

项目主要实现通过读取配置文件连接`Oracle`或`MySQL`数据库，根据输入的动态`SQL`脚本展现数据结果集，

并将结果集导出到excel。项目通过JavaFx技术提供界面操作，对普通用户使用非常友好。

### 项目结构

![image-20240220212407121](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220212407121.png)



### 操作步骤

① 再db.setting配置文件中配置数据库连接信息

> 可配置多数据源，目前支持Oracle和MySQL，后续可扩展

![image-20240220213139957](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220213139957.png)

② 启动项目，检查数据库连接是否正常

![image-20240220213601681](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220213601681.png)

③ 输入动态SQL，获取数据结果集

![image-20240220213733603](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220213733603.png)



![image-20240220214213896](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220214213896.png)

④ 将数据结果集导出成excel

![image-20240220214251176](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220214251176.png)

![image-20240220214354719](C:\Users\16663\AppData\Roaming\Typora\typora-user-images\image-20240220214354719.png)
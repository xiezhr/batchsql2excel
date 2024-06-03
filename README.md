<p align="center">
	<img alt="logo" width="200px" src="imgs/logo.png">
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;"> batchsql2excel</h1>
<h4 align="center">批量SQL转excel</h4>
<p align="center">
  <a href="https://gitee.com/xiezhr/image-learn-bed/raw/master/image/wx.jpg"><img src="https://img.shields.io/badge/weChat-%E5%BE%AE%E4%BF%A1%E5%8F%B7-green.svg" alt="微信"></a>
  <a href="https://gitee.com/xiezhr/image-learn-bed/raw/master/image/微信公众号.png"><img src="https://img.shields.io/badge/%E5%85%AC%E4%BC%97%E5%8F%B7-XiezhrSpace-blue.svg" alt="公众号"></a>
  <a href="https://www.xiezhrspace.cn"><img src="https://img.shields.io/badge/%E4%B8%AA%E4%BA%BA%E5%8D%9A%E5%AE%A2-www.xiezhrspace.cn-orange.svg" alt="个人博客"></a>
  <a href="https://blog.csdn.net/rong09_13"><img src="https://img.shields.io/badge/csdn-CSDN-red.svg" alt="cndn"></a>
   <a href="https://www.zhihu.com/people/rong-xie-49-35/posts"><img 		         src="https://img.shields.io/badge/zhihu-%E7%9F%A5%E4%B9%8E-blue.svg" alt="知乎"></a>
  <a href="https://juejin.im/user/1829211147871415"><img src="https://img.shields.io/badge/juejin-%E6%8E%98%E9%87%91-9cf.svg" alt="掘金"></a>
  <a href="https://space.bilibili.com/305330347"><img src="https://img.shields.io/badge/bilibili-%E5%93%94%E5%93%A9%E5%93%94%E5%93%A9-critical.svg" alt="哔哩哔哩"></a> 
</p>

-----

如果项目您有所帮助，记得 Star ⭐关注哦，这对我是小凡非常不错的鼓励与支持。

别只点赞收藏哦，收藏≠学会，赶紧动起手敲起来吧。

重新开始最好的时间就是现在 *★,°*:.☆(￣▽￣)/$:*.°★* 。

--------



**严肃声明：现在、未来都不会有商业版本，所有代码全部开源!！**

### 🍉项目简介

项目主要实现通过读取配置文件连接`Oracle`或`MySQL`数据库，根据输入的动态`SQL`脚本展现数据结果集，

并将结果集导出到excel。项目通过JavaFx技术提供界面操作，对普通用户使用非常友好。

### 🍊项目结构

![image-20240220212407121](imgs/image-20240220212407121.png)



### 🍋操作步骤

🍓① 再db.setting配置文件中配置数据库连接信息

> 可配置多数据源，目前支持Oracle和MySQL，后续可扩展

![image-20240220213139957](imgs/image-20240220213139957.png)

🍓② 启动项目，检查数据库连接是否正常

![image-20240220213601681](imgs/image-20240220213601681.png)

🍓③ 输入动态SQL，获取数据结果集

![image-20240220213733603](imgs/image-20240220213733603.png)



![image-20240220214213896](imgs/image-20240220214213896.png)

🍓④ 将数据结果集导出成excel

![image-20240220214251176](imgs/image-20240220214251176.png)

![image-20240220214354719](imgs/image-20240220214354719.png)



 ### 🍅 未来计划

添加批量导出功能，即根据批量SQL脚本，批量导出excel。。。。。。

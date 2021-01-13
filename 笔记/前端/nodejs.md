# 简介

是一个可以在本地运行js的跨平台js运行环境

单线程所以对服务器要求降低，但是处理不了高并发场景

# 版本

奇数版本为开发版

偶数版本为稳定版

# 使用

node xxx.js可以直接执行js文件

也可以在命令行中执行

# commonjs规范

ECMAScript缺陷：（缺少下列标准）

- 模块化
- 标准接口
- 标准库
- 包管理工具

commonJs规范

- 模块引用
- 模块定义
- 模块标识

## 定义

写一个js文件即可

## 引入

`require("路径")`

引入模块后的值代表这个模块

`var a = require("路径")`

实际上引用的代码相当于在一个自调用函数中，不能引用到这个变量中的值

`exports.x="aaa"`

可以导出变量，只有导出的变量和方法才能被访问到



```js
当js的文件内容为

console.log(arguments.callee+"")
时，

执行的时候nodejs会自动转化为

function (exports, require, module, __filename, __dirname) {
	console.log(arguments.callee+"")
}
```

**arguments为函数执行的参数列表对象**

**arguments.callee可以获取当前执行的函数**

exports是module的一个属性

如果要对exports赋值要使用

```
module.exports={
	aaa:xxx
}
```

不能直接使用exports={},因为后者相当于直接给exports赋值，本来exports=module.exports,现在改变了引用

> exports是暴露出来的一个属性

## 标识

模块分类：

- 核心模块
  - node引擎提供的模块，模块表示为模块名称，使用模块名称引用
- 文件模块
  - 用户自己定义的模块，使用路径引用

# 全局变量

global为全局，全局中创建的变量被global保存，全局创建的函数会作为global的函数

`var a = 10`此时为局部变量`a = 10`为全局变量



# 包结构

![image-20210107214840385](E:\github\fuckJava\笔记\前端\nodejs.assets\image-20210107214840385.png)



# NPM

## 命令

```
npm -v

npm 帮助

npm search 包名

npm install 包名【等同于npm i】

npm install 包名 -g (全局安装)

npm init初始化一个包环境[相当于git init初始化一个项目]

npm remove 包名

npm install 包名 --save 安装并添加到依赖

npm install -g cnpm --registry=https://registry.npm.taobao.org

npm config set registry https://registry.npm.taobao.org 设置阿里源
```

淘宝npm镜像地址https://developer.aliyun.com/mirror/NPM?from=tnpm

## 寻找模块

寻找模块的时候会一直找node_modules目录中的包，找不到就会去上一层目录找node_modules中的包，一直往上直到根目录，不会查找根目录同级的其他目录
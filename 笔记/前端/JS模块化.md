# 模块化

简单封装--对象+属性

匿名闭包，返回一个立即执行函数(function(){return{属性，方法}})()

导入依赖(function($){return{属性，方法}})(jQuery)



# commonJS规范

- 导入

直接引入打包模块名称package.json中标识

或者引入路径

- 导出

module.exports导出

编写package.json

打包

# AMD规范

先加载模块，再运行方法

```javascript
require(['moduleA', 'moduleB', 'moduleC'], function (moduleA, moduleB, moduleC){

　　　　// some code here

});
```

模块定义

```javascript
　　define(['myLib'], function(myLib){

　　　　function foo(){

　　　　　　myLib.doSomething();

　　　　}

　　　　return {

　　　　　　foo : foo

　　　　};

　　});
```


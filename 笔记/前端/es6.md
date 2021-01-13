# 变量声明

let关键字

- 不能重复声明
- 块级作用域（全局,函数,eval）
- 不允许变量提升(var允许在变量声明之前使用，会返回undefined)

# 常量声明

const关键字

- 初始化
- 大写
- 不能修改
- 块级作用域
- 对数组或对象的元素修改不算对常量的修改，不报错

> 常用于对数组和对象的声明

# 解构

- 数组解构
- 对象解构

数组解构成数组let [a] =[a,b]

对象解构成对象const {a} = {a:1,b:2}

# 模板字符串

``

可以包含变量和换行符

```js
`${a}
换行之后的字符串内容
不需要使用+号拼接`
```

# 对象简化

```javascript
const school={
    name:name
}
可以直接写成
const school={
    name
}
```

# 方法简化 

```javascript
a:function(){
    
}
// 可以直接写成
a(){}
```

# lambda表达式

- 箭头函数的this不会改变，为声明函数时的this

```javascript
let fn = (a,b)=>{}

/**
    箭头函数中的this是静态的，始终指向函数声明时的作用域
    例如声明了一个window.name="aa"
    那么就算改变了name的值，还是会读取到aa
    普通函数只有指定调用方法的对象才会将对象的值传入
*/
window.aa="1";
// 箭头函数
let getName=()=>{
    console.log(aa) //1
}
//普通方法声明
function getName2(){
    console.log(this.name)//1
}
const school = {
   name:2 
}
getName.call(school);//1
getName2.call(school)//2
```

- 箭头函数不能作为构造器实例化对象

```javascript
let Person=(name,age)=>{
    this.name = name;
    this.age=age;
}

let me = new Person("zhangsan","1")//不能当做构造器使用
```

- 箭头函数不能使用arguments(普通函数有arguments对象)

- 箭头函数入参只有一个的时候可以省略小括号
- 箭头函数只有一个语句并且结果是返回值时可以省略花括号和return关键字

# rest参数

>  es6引入rest参数代替argments 

- rest参数必须放在最后做可变参数

```javascript
function(...rest){
    
}
```

- 数组合并
- 浅拷贝

```javascript
// 深拷贝可以通过以下几种方法
function deepClone2(obj) {
  var _obj = JSON.stringify(obj),
    objClone = JSON.parse(_obj);
  return objClone;
}

// 这种方法只能拷贝一层属性
Object.assign()

//引入lodash进行深拷贝
lodash.cloneDeep()
```



# Symbol

> 可用于防止变量重名，覆盖包中的变量等等情况

- 不能进行运算

```javascript
let s = Symbol('sss')
let a = Symbol('sss')
console.log(s===a)//false		
```

基本数据类型

USONB

- undefined
- string symbol
- object
- null number
- boolean

**iterator**

```javascript
const obj = {
  [Symbol.iterator] : function () {
    return {
      next: function () {
        return {
          value: 1,
          done: true
        };
      }
    };
  }
};

/*因此可以使用 obj[Symbol.iterator]来获取迭代器
for...of遍历的时候会使用默认的迭代器进行迭代
重写[Symbol.iterator] 方法可以实现自定义迭代*/
```

# 生成器

通过next方法才能执行

![image-20210108131800374](E:\github\fuckJava\笔记\前端\es6.assets\image-20210108131800374.png)

# Promise

resolve--成功返回

reject--失败返回

```javascript
new Promise(function (resolve, reject) {
  setTimeout(() => reject(new Error('fail')), 3000)
})
```

```javascript
Promise.all([promises]).then(function (posts) {
  // ...
}).catch(function(reason){
  // ...
});
```

# ES6模块化

```javascript
//通用导入
import *  as [name] from 'path'

//解构导入
import {aa,bb} from 'path'

//简便导入，仅导出default
import aa from 'path'
```

在app.js中导入模块，然后用下列方法引入

![image-20210108134820835](E:\github\fuckJava\笔记\前端\es6.assets\image-20210108134820835.png)


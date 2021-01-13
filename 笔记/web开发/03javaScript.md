# 引入js文件

> script标签可以放在head或者body中

**行内**

```html
<a href="https://www.baidu.com"  onclick="alert('您将要跳转到百度')">百度</a>
```

**内嵌**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script>
        window.onload = function () {
            alert('你好')
        }
    </script>
</head>
<body>
</body>
</html>
```

**外联**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="one.js"></script>
</head>
<body>
</body>
</html>
```

# 输出

JavaScript 可以通过不同的方式来输出数据：

- 使用 **window.alert()** 弹出警告框。
- 使用 **document.write()** 方法将内容写到 HTML 文档中，即直接将内容写入html。
- 使用 **innerHTML** 写入到 HTML 元素。
- 使用 **console.log()** 写入到浏览器的控制台。

# 语法

## 字面量

- 数字
- 字符串
- 数组
- 对象
- 表达式
- 函数

## 变量

```
var x, length
x = 5
length = 6
```

## 操作符

JavaScript语言有多种类型的运算符：

| Type                   | 实例      | 描述                   |
| :--------------------- | :-------- | :--------------------- |
| 赋值，算术和位运算符   | = + - * / | 在 JS 运算符中描述     |
| 条件，比较及逻辑运算符 | == != < > | 在 JS 比较运算符中描述 |

# 注释

```javascript
// 单行注释
/*
	多行注释
*/
```

# 数据类型

1. 布尔
2. 数字--NaN表示不是数值，NaN是数字类型，isNan()返回true表示不能转换成数值
3. 字符串
4. 数组--typeof返回类型为对象类型
5. 对象
6. 函数--typeof返回function类型
7. 空--null，返回object
8. 未定义--undefined，返回undefined

**数组使用**

```javascript
// 1
var cars=new Array();
 cars[0]="Saab";
 cars[1]="Volvo";
 cars[2]="BMW";
// 2
 var cars=new Array("Saab","Volvo","BMW");
// 3
var cars=["Saab","Volvo","BMW"];
```

**对象**

对象由花括号分隔。在括号内部，对象的属性以名称和值对的形式 (name : value) 来定义。属性由逗号分隔：

```javascript
 var person={firstname:"John", lastname:"Doe", id:5566};
```

**寻址方式**

```javascript
name=person.lastname;
name=person["lastname"];
```

**Undefined 和 Null**

Undefined 这个值表示变量不含有值。typeof返回Undefined 

可以通过将变量的值设置为 null 来清空变量。typeof返回object

# 流程控制

- 选择--三目运算符也属于选择控制
- 循环
- 跳转
- 异常处理

![image-20201105114553452](E:\github\fuckJava\笔记\web开发\03javaScript.assets\image-20201105114553452.png)

# 类型转换

**constructor返回构造函数**

```javascript
"John".constructor         // 返回函数 String() { [native code] }
(3.14).constructor         // 返回函数 Number() { [native code] }
false.constructor         // 返回函数 Boolean() { [native code] }
[1,2, 3,4].constructor       // 返回函数 Array()  { [native code] }
{name:'John', age:34}.constructor // 返回函数 Object() { [native code] }
new Date().constructor       // 返回函数 Date()  { [native code] }
function() {}.constructor     // 返回函数 Function(){ [native code] }
```

可以使用constructor检验对象是不是数组或日期类型

```javascript
function isArray(myArray) {
  return myArray.constructor.toString().indexOf("Array") > -1;
  return myDate.constructor.toString().indexOf("Date") > -1;
}
```

**类型转换**

- 函数转换
- javaScript自身转换



> **当 JavaScript 尝试操作一个 "错误" 的数据类型时，会自动转换为 "正确" 的数据类型。**

以下输出结果不是你所期望的：

```javascript
5 + null  // 返回 5     because null is converted to 0
"5" + null // 返回"5null"  because null is converted to "null"
"5" + 1   // 返回 "51"   because 1 is converted to "1" 
"5" - 1   // 返回 4     because "5" is converted to 5
```

**转换为字符串**

1. String()
2. xxx.toString()

数字转换成字符串：

```
| toExponential() | 把对象的值转换为指数计数法。                         |
| toFixed()       | 把数字转换为字符串，结果的小数点后有指定位数的数字。 |
| toPrecision()   | 把数字格式化为指定的长度。                           |
```

字符串转化成数字

```
// 不能转换成数字的转化成NaN
Number("3.14")    // 返回 3.14
Number(" ")       // 返回 0
Number("")        // 返回 0
Number("99 88")   // 返回 NaN
//也可以使用parse方法
parseFloat()	解析一个字符串，并返回一个浮点数。
parseInt()	解析一个字符串，并返回一个整数。
```

# 正则表达式

在 JavaScript 中，正则表达式通常用于两个字符串方法 : search() 和 replace()。

**search() 方法** 用于检索字符串中指定的子字符串，或检索与正则表达式相匹配的子字符串，并返回子字符串的起始位置。

**replace() 方法** 用于在字符串中用一些字符替换另一些字符，或替换一个与正则表达式匹配的子字符串。

```javascript
// search
var str = "Visit w3cschool";
var n = str.search(/w3cschool/i);

// replace
var str = "Visit Microsoft!";
var res = str.replace(/microsoft/i, "w3cschool");
```

## 正则表达式修饰符

**修饰符** 可以在全局搜索中不区分大小写:

| 修饰符 | 描述                                                     |
| :----- | :------------------------------------------------------- |
| i      | 执行对大小写不敏感的匹配。                               |
| g      | 执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。 |
| m      | 执行多行匹配。                                           |

## 正则表达式模式

方括号用于查找某个范围内的字符：

| 表达式 | 描述                       |
| :----- | :------------------------- |
| [abc]  | 查找方括号之间的任何字符。 |
| [0-9]  | 查找任何从 0 至 9 的数字。 |
| (x\|y) | 查找任何以 \| 分隔的选项。 |

元字符是拥有特殊含义的字符：

| 元字符 | 描述                                        |
| :----- | :------------------------------------------ |
| \d     | 查找数字。                                  |
| \s     | 查找空白字符。                              |
| \b     | 匹配单词边界。                              |
| \uxxxx | 查找以十六进制数 xxxx 规定的 Unicode 字符。 |

量词:

| 量词 | 描述                                  |
| :--- | :------------------------------------ |
| n+   | 匹配任何包含至少一个 *n* 的字符串。   |
| n*   | 匹配任何包含零个或多个 *n* 的字符串。 |
| n?   | 匹配任何包含零个或一个 *n* 的字符串。 |

**regexp使用**

- test -- 匹配true,否则false
- exec -- 匹配返回字符串数组，不匹配返回null
- compile -- 将regexp对象内人ongoing进行转换

```javascript
var patt = /e/;
patt.test("The best things in life are free!");

/e/.exec("The best things in life are free!");

var patt1=new RegExp("e"); 
document.write(patt1.test("The best things in life are free")); 
patt1.compile("d");// 将表达式转化成/d/
```

# 异常处理

1. try...catch--finally
2. throw

**Error对象的六种派生对象**

- **SyntaxError**--变量名错误
- **ReferenceError**--引用不存在的变量
- **RangeError**--下标越界等范围异常
- **TypeError**--变量或参数类型异常或不存在
- **URIError**--URI相关参数不正确
- **EvalError**--eval执行异常

**自定义异常**

```javascript
function UserError(message) {
   this.message = message || "默认信息";
   this.name = "UserError";
}

UserError.prototype = new Error();
UserError.prototype.constructor = UserError;

// 使用
new UserError("这是自定义的错误！");
```

# 调试

浏览器开发者工具使用

debugger关键字

# javascript:void(0) 

表示计算一个表达式但是没有返回值

**href="#"与href="javascript:void(0)"的区别**

**#** 包含了一个位置信息，默认的锚是**#top** 也就是网页的上端。

而javascript:void(0), 仅仅表示一个死链接。

在页面很长的时候会使用 **#** 来定位页面的具体位置，格式为：**# + id**。

如果你要定义一个死链接请使用 javascript:void(0) 。

```javascript
<a href="javascript:void(0);">点我没有反应的!</a>
<a href="#pos">点我定位到指定位置!</a>
<br><br><br> <p id="pos">尾部定位点</p>
```

# 函数

## 函数定义

```javascript
function *functionName*(*parameters*) {        
 *执行的代码*        
}

// 函数存储在变量中，变量也可以当做函数使用
var x = function (a, b) {return a * b};
var z = x(4, 3);
  
// 可以通过function定义函数，也可以通过Function构造器来构造函数
var myFunction = new Function("a", "b", "return a * b");
var x = myFunction(4, 3);
    
// 函数提升：函数可以先试用后定义，使用表达式定义函数时无法提升。
myFunction(5);               
function myFunction(y) {        
    return y * y;        
}
    
// 自调用函数，函数可以通过(function(){})()的方式实现匿名函数的自我调用
(function () {
    var x = "Hello!!";      // 我将调用自己
})();
```

## 函数参数

```javascript
// 显示调用参数
functionName(parameter1, parameter2, parameter3) {
    code to be executed
}

//  arguments对象使用，arguments[i]可以获取下标为i的参数，成为隐藏参数，也可以用arguments.length获取参数长度
x = sumAll(1, 123, 500, 115, 44, 88);

function sumAll() {
    var i, sum = 0;
    for (i = 0; i < arguments.length; i++) {
        sum += arguments[i];
    }
    return sum;
}

//  参数为值类型的时候，对传入参数进行修改不会修改到函数外部，传入对象作为参数，对对象的属性进行修改会修改外部参数
```

## 函数调用

```javascript
// 使用this进行调用，this指代当前对象

// 作为函数调用,函数默认为window对象的函数，所以function和window.function是一样的
function myFunction(a, b) {
    return a * b;
}
window.myFunction(10, 2); 

// 当函数没有被自身的对象调用时， this 的值就会变成全局对象。在 web 浏览器中全局对象是浏览器窗口（window 对象）。
function myFunction() {
    return this;
}
myFunction();                // 返回 window 对象

// 可以将函数作为对象的一个方法，此时方法的this指向调用对象，否则为全局对象window
var myObject = {
    firstName:"John",
    lastName: "Doe",
    fullName: function () {
        return this;
    }
}
myObject.fullName();          // 返回 [object Object] (所有者对象)

// 如果函数调用前使用了 new 关键字, 则是调用了构造函数。
// 构造函数:
function myFunction(arg1, arg2) {
    this.firstName = arg1;
    this.lastName  = arg2;
}

// This creates a new object
var x = new myFunction("John","Doe");
x.firstName;                             // 返回 "John"

// 使用函数方法调用函数call() 和 apply() 是预定义的函数方法。 区别在于call是传入参数，apply传入参数数组，第一个参数为this的值，如果为null或undefined则会用全局对象代替
function myFunction(a, b) {
    return a * b;
}
myFunction.call(myObject, 10, 2);      // 返回 20
myArray = [10,2];
myFunction.apply(myObject, myArray);   // 返回 20
```

## 闭包

**全局变量**

变量声明不使用var关键字也是全局变量在，web页面中全局变量属于 window 对象。

**闭包情景解释**

* 写一个add方法，进行计数

```javascript
var counter = 0;

function add() {
  counter += 1;
}
```

此时存在一个问题，不是add方法也可以修改counter变量

*  为了让变量安全，声明为局部变量

```javascript
function add() {
    var counter = 0;
    counter += 1;
}
```

但是这种情况下每次调用都会初始化，counter永远为1

* 为了解决上述问题，使用内嵌函数

```javascript
function add() {
    var counter = 0;
    function plus() {counter += 1;}
    plus();   
    return counter;
}
```

如果能够调用到方法内的方法plus就解决问题了，于是使用闭包

* 闭包

```javascript
var add = (function () {
    var counter = 0;
    return function () {return counter += 1;}
})();
```

## 轮播图案例

# 对象比较

## 深拷贝

# 时间操作

# DOM操作

```javascript
var x=document.getElementById("intro");
// 综合使用
var x=document.getElementById("main");
var y=x.getElementsByTagName("p");
var x=document.getElementsByClassName("intro");
// 改变html输出
document.write(Date());
// 改变html内容
document.getElementById("p1").innerHTML="New text!";
// 改变html属性
document.getElementById("image").src="landscape.jpg";
// 改变css
document.getElementById("p2").style.color="blue";
```

**事件**

事件调用

- 使用事件属性例如标签内加入onclick属性
- 使用DOM进行事件分配例如`document.getElementById("myBtn").onclick=function(){displayDate()};`

常用事件

- onclick
- onmousedown
- onmouseup
- onchange
- onload
- onunload
- onmouseover
- onmouseout
- onfocus

**addEventListener()**

`element.addEventListener(event, function, useCapture);`

第一个参数是事件的类型 (如 "click" 或 "mousedown").

第二个参数是事件触发后调用的函数。

第三个参数是个布尔值用于描述事件是冒泡还是捕获。该参数是可选的。

```javascript
element.addEventListener("click", myFunction);

function myFunction() {
    alert ("Hello World!");
}

// 给window对象添加事件句柄
window.addEventListener("resize", function(){
    document.getElementById("demo").innerHTML = sometext;
});

// 移除事件句柄
element.removeEventListener("mousemove", myFunction);

// 对应低版本添加和删除事件句柄的方法
element.attachEvent(event, function);
element.detachEvent(event, function); 

// 兼容做法
var x = document.getElementById("myBtn");
if (x.addEventListener) {                    // 所有主流浏览器，除了 IE 8 及更早版本
    x.addEventListener("click", myFunction);
} else if (x.attachEvent) {                  // IE 8 及更早版本
    x.attachEvent("onclick", myFunction);
}
```

**在文档对象模型 (DOM) 中，每个节点都是一个对象。DOM 节点有三个重要的属性，分别是：**

1. nodeName : 节点的名称
2. nodeValue ：节点的值
3. nodeType ：节点的类型

**DOM元素操作**

1. appendChild
2. createElement
3. createTextNode
4. removeChild

```javascript
// 创建元素和添加元素
var para=document.createElement("p");
var node=document.createTextNode("This is new.");
para.appendChild(node);

var element=document.getElementById("div1");
element.appendChild(para);

// 删除元素
var parent=document.getElementById("div1");
var child=document.getElementById("p1");
parent.removeChild(child);
```

DOM如果要删除或添加元素，只能通过appendChild和removeChild方法实现，所以要知道要添加的元素和子元素才能完成操作。

# BOM对象

## Window 子对象

Window的子对象主要有如下几个：

1. JavaScript document 对象
2. JavaScript frames 对象
3. JavaScript history 对象
4. JavaScript location 对象
5. JavaScript navigator 对象
6. JavaScript screen 对象

**获取浏览器宽高**

1. window.innerWidth--新版浏览器可用
2. document.documentElement.clientWidth或者document.body.clientWidth;是兼容老板浏览器

```javascript
var w=window.innerWidth
|| document.documentElement.clientWidth
|| document.body.clientWidth;

var h=window.innerHeight
|| document.documentElement.clientHeight
|| document.body.clientHeight;
```

其他方法

- [window.open()](https://www.w3cschool.cn/jsref/met-win-open.html) - 打开新窗口
- [window.close()](https://www.w3cschool.cn/jsref/met-win-close.html) - 关闭当前窗口
- [window.moveTo() ](https://www.w3cschool.cn/jsref/met-win-moveto.html)- 移动当前窗口
- [window.resizeTo()](https://www.w3cschool.cn/jsref/met-win-resizeto.html) - 调整当前窗口的尺寸

## screen对象

**window.screen**对象在编写时可以不使用 window 这个前缀。

一些属性：

- screen.availWidth - 可用的屏幕宽度
- screen.availHeight - 可用的屏幕高度

## location对象

**window.location** 对象表示地址，在编写时可不使用 window 这个前缀。 一些例子：

一些实例:

- [location.hostname](https://www.w3cschool.cn/jsref/prop-loc-hostname.html) 返回 web 主机的域名
- [location.pathname](https://www.w3cschool.cn/jsref/prop-loc-pathname.html) 返回当前页面的路径和文件名
- [location.port](https://www.w3cschool.cn/jsref/prop-loc-port.html) 返回 web 主机的端口 （80 或 443）
- [location.protocol](https://www.w3cschool.cn/jsref/prop-loc-protocol.html) 返回所使用的 web 协议（http:// 或 https://）
- location.href 属性返回当前页面的 URL。
- location.assign() 方法加载新的文档。

## history

window.history 对象包含浏览器的历史。

**window.history**对象在编写时可不使用 window 这个前缀。

为了保护用户隐私，对 JavaScript 访问该对象的方法做出了限制。

一些方法：

- [history.back()](https://www.w3cschool.cn/jsref/met-his-back.html) - 与在浏览器点击后退按钮相同
- [history.forward()](https://www.w3cschool.cn/jsref/met-his-forward.html) - 与在浏览器中点击向前按钮向前相同

## navigator 

window.navigator 对象包含有关访问者浏览器的信息。

```javascript
<script>
txt = "<p>浏览器代号: " + navigator.appCodeName + "</p>";
txt+= "<p>浏览器名称: " + navigator.appName + "</p>";
txt+= "<p>浏览器版本: " + navigator.appVersion + "</p>";
txt+= "<p>启用Cookies: " + navigator.cookieEnabled + "</p>";
txt+= "<p>硬件平台: " + navigator.platform + "</p>";
txt+= "<p>用户代理: " + navigator.userAgent + "</p>";
txt+= "<p>用户代理语言: " + navigator.systemLanguage + "</p>";
document.getElementById("example").innerHTML=txt;
</script>

// 输出
浏览器代号: Mozilla
浏览器名称: Netscape
浏览器版本: 5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36
启用Cookies: true
硬件平台: Win32
用户代理: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36
用户代理语言: undefined
```

# 计时和cookie

## 定时事件

**使用间隔函数(周期执行)**

window.setInterval("*javascript function*",*milliseconds*);

**清除间隔函数**

window.clearInterval(*intervalVariable*)

```javascript
var myVar=setInterval(function(){myTimer()},1000);
clearInterval(myVar);// 这里清除间隔函数
```

**使用定时函数**

window.setTimeout("*javascript 函数*",*毫秒数*);

**清除定时函数**

window.clearTimeout(*timeoutVariable*)

```javascript
myVar=setTimeout(function(){alert("Hello")},3000);
clearTimeout(myVar);
```

# AJAX

# JSON

**JSON 语法规则**

- 数据为 键/值 对。
- 数据由逗号分隔。
- 大括号保存对象
- 方括号保存数组

```json
"employees":[        
    {"firstName":"John", "lastName":"Doe"},        
    {"firstName":"Anna", "lastName":"Smith"},     
    {"firstName":"Peter", "lastName":"Jones"}        
]
```

# ES6新增


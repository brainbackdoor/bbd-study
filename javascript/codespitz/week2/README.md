## 2 주차

#### Flow Control Statement

##### Record, Completion Record

```
자바스크립트 엔진은 개발자가 문을 작성하면 문을 하나의 Record로 바꿔준 후 Record 단위로 수행
Flow Control Statement -> Completion Record
```

##### Direct Flow Control

```
if, while, for를 지원하면, ABC 언어의 후손
B언어까지는 직접 flow control
```

##### Label

```
identifier : $를 제외한 js 변수명 규칙과 똑같이.. 끝에 :

label만 문에 있으면 에러가 뜬다. 
label은 문이 아니다. 일종의 태그같은거 


```

```html
<html>
<body>
<script>
abc:
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
abc:;
</script>
</body>
</html>
```

```html
label scope
바깥쪽의 label이 안쪽의 label로 인해 쉐도잉이 발생
<html>
<body>
<script>
abc: {
    abc:3;
}
4;
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
abc: {
    console.log('start');
    if(true) {
        break;
    }
    console.log('end');
}
</script>
</body>
</html>
```
```html
<html>
<body>
<script>
abc: {
    console.log('start');
    if(true) {
        break abc;
    }
    console.log('end');
}
</script>
</body>
</html>
```

```
label iterator, label 
```

```html
<html>
<body>
<script>
console.log('0');
abc:
    if(true) {
        break abc;
    }
console.log('1');
bbb:
console.log('2');
</script>
</body>
</html>
```
```
auto label
for문이나 switch문 앞에 js 엔진이 자동으로 label을 생성함 (-> break 뒤에 label 명을 작성안해서 error가 안남)
```

```html
<html>
<body>
<script>
temp38:
for (var i = 0; i < 10; i ++) {
    if(i == 5 ) break temp38;
}
c: console.log(end); // 앞 레이블을 쓰는 이유는 병행제어의 경우에 사이드이펙트를 안주며.. 앞에 있으므로 가독성이 좋다..
b: console.log('000000'); // a
a: console.log('000000000000000'); // a
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
temp38:
for (var i = 0; i < 10; i ++) {
    if(i == 5 ) break temp38;
} // 보통은 함수 호출하면 앞으로 가는데.. label 구문은 블럭의 제일 끝으로 간다. 
function a() {
    .
    .
    .
}
a() ;
</script>
</body>
</html>
```

```
Switch문 
Special label block
Switch의 중괄호는 Special label(case안의 식을 동적으로 해석할 수 있는)이 가능한 공간으로 만들어 줌
Fall Through (break 안쓰면 쭈욱 내려감)
break를 걸면 switch 문 앞의 auto label로 빠져나간다.

중괄호 안에서는 case label과 default label만 쓸 수 있다.

JS 는 런타임에 순서대로 읽어들인다..
```

```html
<html>
<body>
<script>
switch(1) {
    default: console.log('c'); //default에는 해당사항이 있으니 fall through
    case true: console.log('a');
    case false: console.log('b');
  
}
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
temp17:
switch(1) {
    default: console.log('c'); 
    case true: console.log('a'); break temp17;
    case false: console.log('b');
}
console.log('end');
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
var a = true // 값에 대한 routing
temp17:
switch(a) {
    default: console.log('c'); 
    // 값에대해 동적으로 라우팅해도 됨
    case f1(a): console.log('a'); break temp17; 
    case f2(a): console.log('b');
}
console.log('end');
switch(true) { //chainable responsibility 제일 먼저 true 인 case로..
    case network(): 
    case localCache():
    default: // 안내문..
}

switch(true) { 
    case network() === 'online':
    case network() === 'lte':
    case network() === 'offline':
    case localCache():
    default: // 안내문..
}
</script>
</body>
</html>
```

```html
<html>
<body>
<script>
var c = 2;
switch (true) {
    case c++ > 5: console.log(c); break;
    case c++ > 5: console.log(c); break;
    case c++ > 5: console.log(c); break;
    case c++ > 5: console.log(c); break;
    case c++ > 5: console.log(c); break;
    case c++ > 5: console.log(c); break;
}
</script>
</body>
</html>
```

##### Optional Flow Control (<-> mandatory 반드시 필요한)

```
Programming 언어는 컴퓨터를 위한 언어.. 좀더 엄밀하게 표현합시다.
If : If 식 -> Optional
If Else : If 식 Else 식 -> Mandatory 
If else 는 후방결합으로 중괄호 를 꼭 씌워라


Truthy : 이정도면 참으로 인정해줄께 (Falsy 빼고 다)
Falsy : 0, 빈문자열 '', false, undefined, null, NaN

Optional
Mandatory
```

```html
<html>
<body>
<script>
if(a) { // Mandatory .. 반드시 Case 1이거나 Case 2이다.
    console.log('case 1');
} else  {
    console.log('case 2');
}

if(a) { // Mandatory .. Mandatory는 모든 case를 다 커버해야한다.
    console.log('case 1');
} else {
        if (b)  {
            if (a) {
                console.log('case 2');
            } else {
                console.log('case 3');
            }
        
        } else {
            if (a) {
                console.log('case 4');
            } else {
                console.log('case 5');
            }
        }
    }
}


if(a) { // 이건 Mandatory가 아님 
    console.log('case 1');
} else {
        if (b)  {
            if (a) { // a 와 b가 decoupling이 아님
                console.log('case 2');
            } 
            console.log('case 3');
        } else {
            if (a) {
                console.log('case 4');
            } else {
                console.log('case 5');
            }
        }
    }
}

if(a) { // 
    console.log('case 1');
} else {
        if (b)  {
            if (c) { // 요렇게 되어야함..
                console.log('case 2');
            } 
            console.log('case 3');
        } else {
            if (a) {
                console.log('case 4');
            } else {
                console.log('case 5');
            }
        }
    }
}

const f = () => 3, 5; // 후방결합.. 에러남
const f = () => (3, 5);

if (b) {
    console.log('case 3'); // Optional .. Case 3이 나올수도 있고 안나올 수도 있다.
}
console.log('case 4');
</script>
</body>
</html>
```

##### Iterate Flow Control

```
반복해야 할 내용이 변하지 않으면 iteration
다양한 경로를 통해서 반복해야 할 내용이 변하면 recursive

1번쨰 block 특성 : Limited Statement
a = var b -> 안됨 var a = b 는 문 
a = b -> 식

2번째 block 특성
Empty Truthy
for ( a = 3;;)
for ( a = 3;true;)
    가운데가 공문이어도 true로 해석

3번째 block 특성
    Last Excution :  마지막에 수행
```


##### While문

```html
<html>
<body>
<script>
while(a) { // a가 while body에 있어야 함. a의 변화가 없으면 loop 아니면 들어오지도 않음. 

}

var k = a.b() 
while (k) { // while문의 trigger가 되는 문이 추적 불가능할 경우 잘못된 코드이므로, 통제 가능한 변수가 가시성있게 드러나야 한다.
    some.k()
    k = a.b()
}
</script>
</body>
</html>
```
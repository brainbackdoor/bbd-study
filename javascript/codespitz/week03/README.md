## 3주차

#### Interface IN JS

```
1. 인터페이스란 사양에 맞는 값과 연결된 속성키의 셋트
2. 어떤 Object라도 인터페이스의 정의를 충족시킬 수 있다.
3. 하나의 Object는 여러개의 인터페이스를 충족시킬 수 있다.
```


```
Interface Test
1. test라는 키를 갖고
2. 값으로 문자열인자를 1개 받아 boolean 결과를 반호나하는 함수가 온다.
```

```javascript
test(str){
    return true;
}

```

#### ITERATOR interface

```
1. next라는 키를 갖고
2. 값을 인자로 받지않고 IteratorResultObject를 반환하는 함수가 온다.
3. IteratorResultObject는 Value와 done이라는 키를 갖고있다.
4. 이 중 done은 계속반복할 수 있을지 없을지에 따라 불린 값을 반환한다.
```
```javascript
{
    next() {
        return {value:1, done:false};
    }
}
{
    data:[1,2,3,4],
    next() {
        return {
            done:this.data.length ==0,
            value:this.data.pop()
        }
    }
}
```

#### Iterable Interface

```
1. Symbol.iterator라는 키를 갖고
2. 값으로 인자를 받지 않고 Iterator Object를 반환하는 함수가 온다.

loop를 여러번 돌려도 객체가 안깨지므로, Iterator를 reset할 수 있다.
```
```javascript
{
    [Symbol.iterator]() {
        return {
            next() {
                return {value:1, done:false};
            }
        }
    }
}
```

#### Loop to iterator

```
문은 한번 실행하고나면 사라져
그래서 loop를 식으로 바꾸고 싶다. 객체화하고 싶다.
현대 언어들은 기존에 문이하던 것들을 식으로 바꾸고 싶어하..

While 문으로 살펴보는 iterator
```

```javascript
let arr = [1,2,3,4];
while(arr.length > 0) {     // 계속 반복할지 판단
    console.log(arr.pop()); // 반복시마다 처리할 것
}
{
    data:[1,2,3,4],
    next() {
        return {
            done:this.data.length ==0,  // 계속 반복할지 판단  
            //-> 반복 자체를 하지 않지만 반복을 위해 필요한 것들을 iterator가 다 가지고 있음
            //-> 따라서 밖에서는 필요한만큰 반복을 재현할 수 있음 next()만 계속 실행해주면 됨. 
            value:this.data.pop()       // 반복시마다 처리할 것
        }
    }
}
```

```javascript
const iter = {
    data:[1,2,3,4],
    [Symbol.iterator](){return this;},
    next() {
        return {
            done:this.data.length ==0,  // 계속 반복할지 판단  
            //-> 반복 자체를 하지 않지만 반복을 위해 필요한 것들을 iterator가 다 가지고 있음
            //-> 따라서 밖에서는 필요한만큰 반복을 재현할 수 있음 next()만 계속 실행해주면 됨. 
            value:this.data.pop()       // 반복시마다 처리할 것
        }
    }
}
const loop = (iter, f) => {
    // Iterable이라면 Iterator를 얻음
    if(typeof iter[Symbol.iterator] == 'function') {
        iter = iter[Symbol.iterator]();
    }else return;
    //IteratorObject가 아니라면 건너뜀

    if(typeof iter.next != 'function') return;

    do {
        const v = iter.next();
        if(v.done) return; // 종료처리
        f(v.value); // 현재 값을 전달함
        // 반복기에서만 돌리고, 반복에 대한 상태는 iterator에서 책임짐
    }
}

loop(iter, console.log);
```
#### 내장 반복 처리기

```javascript
//Array Destructuring
const [a,...b] = iter; //첫번째 값 이후의 값을 배열로 묶어서 처리 --> Iterable을 씀으로써 언어의 지원을 받을 수 있다.
console.log(a,b)
// 4, [3,2,1];
```

```javascript
// Object Destructuring
const {a,...b} = iter; 
console.log(a, b);
// undefined, {'0':4. '1':3. '2':2. '3':1}
```
```javascript
//Spread
const a = [...iter]; 
console.log(a)
// [4,3,2,1]
```
```javascript
// Object Spread
const a = {...iter}; 
console.log(a)
// {'0':4. '1':3. '2':2. '3':1}
```

```javascript
// Rest Parameter
const test = (...arg) => console.log(arg);
test(...iter);
// [4,3,2,1]
```

```javascript
// For of
for(const v of iter) { // 배열, String 등도 올 수 있다. 다 해체됨
    console.log(v);
}
```

#### 제곱을 요소로 갖는 가상 컬렉션 

```javascript

// 크롬은 Blocking 을 20초, Windows OS는 15초, Android 는 5초 만 허용함
// 따라서 CPU를 Release해주어야 함 JS에서는 이 단위를 Frame이라고 함
// Loop를 분산해서 제어하는 습관을 들여야..
const N2 = class {
    constructor(max) {
        this.max = max;
    }
    [Symbol.iterator]() {
        let cursor = 0, max = this.max;
        return {
            done:false,
            next() {
                // 함수는 함수 바깥의 변수를 참조할 수 있다. 이를 자유변수라고 한다.(지역변수도 인자도 아닌..)
                // 따라서 자유변수가 갇힌 범위를 클로저라 한다. 따라서 함수는 클로저.. 자유변수를 캡쳐해서 클로저에 ..
                if(cursor > max) {
                    this.done = true;
                } else {
                    this.value = cursor * cursor;
                    cursor++;
                }
                return this;
            }
        }
    }
}
console.log([...new N2(5)]);
for(const v of new N2(5)) {
    console.log(v);
}
```

#### Geneator

```javascript
//Iterator의 구현을 돕는 Generator
const generator = function*(max) {
    let cursor = 0;
    while(cursor < max) {
        yield cursor * cursor; // yield가 호출될때마다 next가 호출되는 것과 동일한 효과가 생김
                                // js 엔진은 폰노이만 머신과 달리 record로 만들어서 돌리기 때문에
                                // 문을 이제 멈출 수 있게됨 .. loop 조건에 걸릴때마다.. iterator resultObject 를 리턴 (suspension)
                                // 이 후 거기서부터 다시 진행
                                // routine vs coroutines
        cursor++;           
    }                           // 빠져나올때 사실상 done = true;
}
//generator를 호출할 떄 마다 iterator가 만들어짐
console.log([...generator(5)]);

for(const v of generator(5)) {
    console.log(v);
}
```
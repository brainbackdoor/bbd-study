## 5주차

#### Block, NonBlock & Sync, Async

```javascript
/*Block : Flow를 막고 있는 것

Flow is Blocking 
    프로그램이 실행되면 도중에 멈춰지지 않고 끝까지 실행됨

Blocking에 대한 업계 요구사항이 각기 다름.. */

for(const i of (function*(){
    let i = 0;
    while(true) yield i++;
})()) console.log(i);

// script timieout
// 플랫폼의 안정성을 위해 블록되는 시간이 길면 강제 종료시킴

// Blocking Function
// 점유하는 시간만큼 블록을 일으키는 함수

const f = v -> {
    let i = 0;
    while(i++ < v );
    return i;
};
f(10);
f(100000000000);

/*
    배열순회, 정렬 - 배열 크기에따라
    DOM 순회 - DOM의 하위구조에 따라
    이미지 프로세싱 - 이미지 크기에 따라
*/
```
##### Blocking Evasion

```javascript
/*
    독점적인 cpu 점유로 인해 모든 동작이 정지됨
    타임아웃체크에 의해 프로그램이 강제 중단됨

    시분할 운영체제의 동시 실행 // -> 멀티 프로셋스를 위해..


    Frame - 명령큐
    Main UI Thread
*/
const looper = (n, f, slice =3 ) => { // manual
    let limit =0, i=0;
    const runner =_= => {
        while(i < n){
            if( limit ++ <slice) f(i++);
            else {
                limit = 0;
                requestAnimationFrame(runner);
                break;
            }
        }
    };
    requestAnimationFrame(runner);
}

const looper = (n, f, ms = 5000, i = 0 ) => { // auto
    let old = performance.now(), curr;
    const runner = () => {
        while(i < n) {
            if(performance.now() - old < ms) f(i++);
            else {
                old = performance.now();
                requestAnimationFrame(runner);
                break;
            }
        }
    }
    requestAnimationFrame(runner);
}
// date.now() -> os IO 
// performance.now() -> browser 단이라.. 더빠를것 같지만 .. 맨날 다름 ㅡ,.ㅡ?

// Web Worker || Worker Thread Pattern
// 
/*
채널이라 불리는 쓰레드풀 관리자가 있고
여기에 등록되어 실제 작업을 수행하는 워커가 등장합니다.
실제 워커가 처리할 작업을 요청이라 하는데 보통 Runnable의 구상체입니다.
마지막으로 요청을 생성하고 채널에게 등록할 클라이언트가 있습니다.
*/
const backRun = (f, end, ...arg)=>{
    // onmessage : main thread-> background thread 에게 message를 보냄
    // event handler를 걸어둠 (background thread가 main thread에게 return)
    // 왜냐면 f 함수의 비용이 커서 main thread가 영향 받는 것이 싫어서 background thread에게 위임하는 것임
    const blob = new Blob([`
    onmessage =e=>postMessage(($f)(e.data)); 
    `], {type: 'text/javascript'}); // https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Atomics
    // atomic이란 것도 있어
    const url = URL.createObjectURL(blob); // 유사 메모리 URL을 만들어줄 수 있음
    const worker = new Worker(url);
    worker.onmessage =e=> end(e.data); // event handler를 걸어둠 (backgroudn thread가 postmessage를 할때 end 함수가 받음)
    worker.onerror =e=>end(null);
    worker.postMessage(arg); // 최종적으로 main thread가 worker한테 arg를 보냄
}
backRun(v=>v[0] + v[1], console.log, 3, 5); // 3과 5를 인자로 -> 합산 후 8을 console.log(8)에 담아 리턴
// IO보다 메모리가 비용이 적기 때문에 왠만하면 background로 처리해야 함

```

#### Non Blocking

```javascript
/*
    서브루틴이 즉시 Flow 제어권을 내놓는 것
*/
const a = 123;
looper(12, console.log);
backRun(v=>v[0] + v[1], console.log, 3, 5); 
console.log(a); // 어쨋든 콘솔은 123 부터 출력
```

#### Sync

```javascript
// 서브루틴이 즉시 값을 반환함
const double =v=>v*2;
console.log(double(2));

// Block - Sync // nomalAPI, legacy API
const sum =n=>{
    let sum = 0;
    for(let i = 1; i <=n;i++) sum += i;
    return sum;
};
sum(100);

// Nonblock - sync // oldAPI
const sum =n=> {
    const result = {isComplete:false};
    requestAnimationFrame(_=>{
        let sum = 0;
        for(let i=1; i<= n; i++) sum +=i;
        result.isComlete = true;
        result.value = sum;
    });
    return result;
};
const result = sum(100);
const id = setInterval(()-> {
    if(result.isComplete) {
        clearInterval(id);
        console.log(result.value);
    }
}, 10);// sync nonblocking의 경우 결과값을 조사하기 위해 다시 sync nonblocking을 구현해야 함
```

#### Async


```javascript
// 서브루틴이 콜백을 통해 값을 반환함

// Sync - ASYNC //  TRAP!!
const double =(v,f)=>f(v*2);
double(2, console.log);

const sum = (n,f)=>{
    let sum = 0;
    for(let i = 1; i <= n; i++) sum += 1;
    return f(sum);
};


// NonBlokck Async // modernAPI
// 의식이 널뛴다.. 그래서 promise 로 반환하는거.. Async를 직렬로 연결..
const sum = (n, f) => 
```

```javascript
// SIMILAR ASYNC - Block
const sum = (n,f)=>{
    requestAnimationFrame(_=>{
        let sum = 0;
        for(let i = 1; i<=n; i++) sum += i;
        f(sum);
    });
}
sum(100000000, console.log);
console.log(123);

//

const f = v=>{
    for(let i =1, sum =0; i<= v[0]; i++){
        sum += i;
    }
    return sum;
};
let i = 1000;
while(i--) backRun(f, console.log, 100000);
```

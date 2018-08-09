## 6주차

#### GENERATOR



```javascript
/*Breaking Block --Command Pattern || 문을 객체화
    프로그램을 중도에 멈췄다가 다시 실행할 수 있음
    yield를 통해서 제어문을 중간에 멈출 수 있다.
*/

/*
    Time Slicing Manual Using Generator
    loop를 추상화함으로 써 executor의 유지보수가 유연해짐
*/

/*
    GENERATOR + ASYNC + EXECUTOR
    call back 없이도 동기로직처럼 보이게 짤 수 있다.

    generator call routine pattern
    co utility에서 요렇게 씀
*/

const profile = function*(end, next, r) {
    const userid = yield $.post('memmber.php', {r}, next);
    let added = yield $.post('detail.php', {userid}, next);
    added = added.split(",");
    end({userid, nick:added[0], thumb:added[1]});
}
const executor = (end, gene, ...arg) => {
    const next =v=> iter.next(v);
    const iter=gene(end, next, ...arg);
    iter.next();
}
executor(console.log, profile, 123);


/*
    Passive Async Control
    callback을 보낼 수는 있지만 언제 올지는 모른다. 걸고나면 나의 제어권이 없음. 무조건 callback을 받아야함..
    
    Active Async Control
    promise는 then을 호출하기 전까지는 아무 일도 일어나지 않음(preload 구현하기가 좋음)
*/
/*
    GENERATOR + PROMISE

    promise는 비동기를 발현시키는 method가 없다. then을 호출하는 시점만..(후방제어권 ㅡ.ㅡ?)
*/

const profile = function*(end, r) {
    const userid = yield new Promise(res=>$.post('memmber.php', {r}, res));
    let added = yield new Promise(res=>$.post('detail.php', {userid}, res));
    added = added.split(",");
    end({userid, nick:added[0], thumb:added[1]});
}
const executor = (end, gene, ...arg) => {
    const iter=gene(end, ...arg);
    const next = ({value, done}) => {
        if(!done) value.then(v=> next(iter.next(v)));
    }
    next(iter.next());
};
executor(console.log, profile, 123);

/*
    ASYNC AWAIT (C#에서 옮)
    = SYNC
    await가 then을 대신 호출해준다고 생각해보자

    다만 이 안에서 yield를 쓸 수 없으니.. 위에 코드가 더 유리함
    ES8에서는 ASYNC GENERATOR가 도입됨..
*/

const profile = async function(end, r) {
    const userid = await new Promise(res=>$.post('memmber.php', {r}, res));
    let added = await new Promise(res=>$.post('detail.php', {userid}, res));
    added = added.split(",");
    end({userid, nick:added[0], thumb:added[1]});
}
profile(console.log, 123);


```
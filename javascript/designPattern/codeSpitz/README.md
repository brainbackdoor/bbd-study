### Code Spitz - 75 디자인패턴 + 뷰패턴

#### 강한 응집성 && 낮은 의존성
```
모든 프로그램은 변한다!
=> 이미 작성된 복잡하고 거대한 프로그램을 어떻게 변경할 수 있을 것인가?
**격리**

격리전략의 기본 : "변화율에 따라 작성하기"

변화율이란 시간적인 대칭성
변화의 원인과 주기별로 정리
```


#### 1. 의도를 먼저 표현하자
```html
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>CodeSpitz</title>
</head>
<body>
    <section id="data"></section>
<script>
const Table =(_=>{
    return class {

    };
})();
const table = new Table("#data");
table.load("75_1.json");
</script>
</body>
</html>
```

#### 2. Table 구조
```html
<script>
const Table =(_=>{
        /* static private */
    return class{
        /* constructor
        public methods
        private methods
         */
        constructor(parent){
        }
        async load(url){
        }
        render(){
        }
    };
})
</script>
```

#### 3. 한번에 한가지만 집중하라
```html
<script>
...
    constructor(parent){
        if(typeof prarent != 'string' || !parent) throw "invalid param";
        this[Private] = {parent};
    }
...
</script>
```

#### 4. ES2015 문법 (async)

```html
<script>
/* 이전 */
    load(url){
        fetch(url).then(response =>{
            return response.json();
        }).then(json => {
            this._render();
        });
    }
/* ES6 */
    async load(url){
        const response = await fetch(url);
        const json = await response.json();
        this._render();
    }   
</script>
```
#### 5. throw !!
```html
<script>
/* 1.컴파일 에러 2. 런타임 에러 3. 컨텍스트 에러
   자바스크립트는 Syntax 정도의 컴파일 에러만 발생
   런타임 에러로 남기지 않으면 컨텍스트 에러로 번질 수 있음 
*/
    async load(url){
        const response = await fetch(url);
        if(!response.ok) throw "invalid response";
        const json = await response.json();
        const {title, header, items} = json;
        if(!items.length) throw "no items";
        Object.assign(this[Private], {title, header, items});
        this._render();
    }
</script>
```
#### 6. 의사코드
```html
<script>
    _render(){
        //부모, 데이터 체크
        //table 생성
        //캡션을 title로
        //header를 thead로
        //items를 tr로
        //부모에 table 삽입
    }
</script>
```
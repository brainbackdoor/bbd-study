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

#### 7. Bind 분리

```
DATA LOAD > RENDERING > NATIVE BIND
Device의 다변화로 Binding 작업을 별도로 분류함으로써 Rendering 과정은 보다 강한 응집력을 가진다.
```

#### 8. Value Object

```html
<script>
const loader = new Loader("71_1.json");
loader.load(json=>{
    const renderer = new Renderer();
    renderer.setData(json);
    renderer.render();
})
// 여기서 json의 Validation을 어디서 하여야 하는가?
// Value -> Object로 변경하고, VO 생성시에 Validation 체크를 함으로써, VO를 받기만 해도 검증되었음을 확신할 수 있다.
// DATA SUPPLY[DATA:{JSON_DATA|XML_DATA|CSV_DATA}] > RENDER > NATIVE BIND
const data = new JsonData("71_1.json");
const renderer = new Renderer();
renderer.render(data);
// 하지만 여전히 JsonData 객체를 직접 생성하는 것은 응집력이 약해보인다.

const Data = class{
    async getData(){throw "getData must oveeride";}
}
const JsonData = class extends Data{
    constructor(data){
        super();
        this._data = data;
    }
    async getData(){
        if(typeof this._data == 'string'){
            const response = await fetch(this._data);
            return await response.json();
        } else return this._data;
    }
}
const Renderer = class {
    constructor(){}
    async render(data){
        if(!(data instanceof Data)) throw "invalid data type";
        const json = await data.getData();
        console.log(json);// View에 해당하는 부분
    }
}
// renderer에서 json을 직접 다루어야 하나?
const Info = class{
    constructor(json){
        const {title, header, items} = json;
        if(typeof title != 'string || !title') throw "invalid title";
        if(!Array.isArray(header) || !header.length) throw "invalid header";
        if(!Array.isArray(items) || !items.length) throw "invalid items";
        items.forEach((v, idx)=> {
            if(!Array.isArray(v) || v.length != header.length) throw "invalid items:" + idx;
        });
        this._private = {title, header, items};
    }
    get title(){return this._private.title;}
    get header(){return this._private.header;}
    get items(){return this._private.items;}
}
class Data = class{
    async getData(){
        const json = await this._getData();
        return new Info(json);
    }
    async _getData(){
        throw "_getData must overrided";
    }
};
const JsonData = class extends Data{
    constructor(data){
        super();
        this._data = data;
    }
    async _getData(){
        let json;
        if(typeof this._data == 'string'){
            const response = await fetch(this._data);
            return await response.json();
        } else return this._data;
    }
}
const Renderer = class {
    constructor(){}
    async render(data){
        if(!(data instanceof Data)) throw "invalid data type";
        const info = await data.getData();
        console.log(info.title, info.header, info.items);// View에 해당하는 부분
    }
}
</script>

```
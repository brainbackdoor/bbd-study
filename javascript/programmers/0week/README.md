
```javascript
function Cat(name, age) {
    this.name = name;
    this.age = age;
}
const tabby1 = Cat('nana', 5)
console.log(tabby1.name)

- 정답은 '오류가 발생한다'
- apply, call, bind 등으로 this에 대해 주입한 상황이 아니고 new 키워드없이 실행한 함수 내 this는 전역 객체(window)를 바라본다.
- 즉 this.name = name의 결과는 window.name = name 이라는 이야기.
```

```javascript
(function(name){
	console.log(`hello ${name}`)
})('roto')

# Function Scope라 Function 안에서만 유효하다. 이는 Function 안에서만 유효하며 ('roto')는 즉시 실행 함수

- hello roto가 출력된다.
- `와 `로 감싼 문자열은 ES6에 있는 template strings라는 문법으로, 즉시 실행 함수 표현(IIFE, Immediately Invoked Function Expression)이라고 하며, 함수를 정의함과 동시에 실행한다.

- JavaScript 특성상 변수의 scope는 해당 변수를 감싸고 있는 function에 한정되는데, 이걸 이용해 변수나 함수의 전역화를 최소화 시킬 수 있다.
다음은 대표적인 IIFE의 응용이다.

var logger = (function(){
  // logCount는 밖에서 접근할 수 없다. 일종의 private 효과
  var logCount = 0;
  function log(message) {
    console.log(message);
    logCount = logCount + 1;
  }
  function getLogCount() {
    return logCount;
  }
  return {
    log: log,
    getLogCount: getLogCount
  }
})() 
```

```javascript
var idiots = {
	name: 'idiots',
	genre: 'punk rock',
	members: {
		roto: {
			memberName: 'roto',
			play: function() {
				console.log(`band ${this.name} ${this.memberName} play start.`)
			}
		}
	}
}

idiots.members.roto.play()

play 함수의 this 내에는 name이 없기 때문에 undefined가 출력 되는 것
```

```javascript
perform 함수 아래 setTimeout으로 인해 실행되는 함수의 this는 RockBand의 this가 아니기 때문에, 참조 오류가 발생
- 클로저를 이용한 해결법
  function RockBand(members) {
    var that = this; 
    	<--!! 요새 self나 that을 안쓰는 이유는 뭘까
    				이런 형태는 최근에 안쓰게 됨 (this 를 활용하지 않는 방식으로 코드를 만드는 경우가 많은 점도 있습니다)
    this.members = members;
    this.perform = function() {
      setTimeout(function(){
        that.members.forEach(function(member){ member.perform() })
      }, 1000)
    }
  }

  var theOralCigarettes = new RockBand([
    {
      name: 'takuya',
      perform: function() { console.log('a e u i a e u i')}
    }
  ])

  theOralCigarettes.perform()

- bind를 이용한 해결법
  function RockBand(members) {
    this.members = members;
    this.perform = function() {
      setTimeout(function(){
        this.members.forEach(function(member){ member.perform() })
      }.bind(this), 1000)
    }
  }

  var theOralCigarettes = new RockBand([
    {
      name: 'takuya',
      perform: function() { console.log('a e u i a e u i')}
    }
  ])

  theOralCigarettes.perform()

```

```javascript
setTimeout을 IIFE로 감싸고, 파라메터로 i를 넘기는 것으로 해결 가능
const numbers = [1, 2, 3, 4, 5];
for(var i = 0; i < numbers.length; i++){ 
  (function(count){
    setTimeout(function(){
      console.log(`number index ${count}`);
    }, 1000);
  })(i)
}

let을 써도 됨
const numbers = [1, 2, 3, 4, 5];
for(let i = 0; i < numbers.length; i++){ 
    setTimeout(function(){
      console.log(`number index ${i}`);
    }, 1000)
}
```

```javascript
const users = [
	{
		name: 'roto',
		type: 'human'
	},
	{
		name: 'nana',
		type: 'cat'
	},
 	{
		name: 'chai',
		type: 'cat'
	} 
]

function printCats() {
  const userNames = []
  for(let i = 0; i < users.length; i++) {
    if (users[i].type === 'cat') {
      userNames.push(users[i].name);
    }
  }
  console.log(userNames.join(''));
}

function printCats() {
  console.log(users.filter(user => user.type === 'cat').join(''));
}
```

```javascript
# var: function level scope를 가지며 이로 인해 호이스팅 현상이 일어난다. 재할당 가능
				var는 호이스팅으로 문제가 발생함.
        var로 변수를 선언하면 undefined로 선언이 되기때문에 접근은 가능함
          
# let: block level scope를 가지며 재할당이 가능하다.
# const: block level scope를 가지며 재할당이 불가능하다. 그러나 할당된 객체의 함수를 이용해 객체를 변경하는 일은 가능하다.
        let과 const도 호이스팅이 TDZ관련해서 일어난다. 임시 사망 공간
        let과 const도 최상단으로 호이스팅이 되지만 메모리상에 assign이 안되어 있어서(undefined로도)
			  그래서 접근이 안됨

const arr1 = []

arr1 = [1,2]; // Error!

// 가능
arr1.push(1);
arr1.push(2);
```

```javascript
클로저란?
자신의 scope 외부에 있는 것을 가져와 쓰는 것.

var a = 1;

function hello() {
  console.log(a); // a가 hello function scope에 없는데에도 접근 가능함.
}


```

```
Javascript Execution Context 
https://velog.io/@imacoolgirlyo/JS-자바스크립트의-Hoisting-The-Execution-Context-호이스팅-실행-컨텍스트-6bjsmmlmgy

let과 const 선언의 경우에도 클로져 특성을 가지는 경우가 있나요?
?????
```




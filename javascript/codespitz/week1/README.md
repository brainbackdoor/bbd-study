## 1 주차

#### Programming & JS ELEMENTRY

##### Why did you do that?

```
- 지식에 얽매이지 말자. 언어에 집착하지 말자. 
- 일관성있게 생각해야 하는 가치관 _ 
    철학 => 합리주의 / 상대주의(부모/자식 클래스, OS/JVM/App)
    
    가치 | 원칙 | 패턴
    - 가치 => 우리가 같이 동의할 수 있는 것.. 의사소통(팀원간), 단순함, 유연함
    - 원칙 => 원칙의 예외를 바로 알 수 있다.. 지역화(영향의 국지화), 중복제거, 대칭성(getter, setter.. 쌍을 맞춰라)
    - 패턴 => 선배들의 경험.. 개발론(함수지향,객체지향,TDD), 설계론(3 tier, architecture), 각종 적용 패턴

    동기 => 그래서 내가 왜 이렇게 만들었는지.. 돈, 시간

    의사보다 돈을 더 벌려면 의사보다 더 공부하는 개발자가 되자.
```

#### Program & Timings

```
language code       LINT TIME 
machine language    COMPILE TIME 
file
load
run                 RUN TIME
terminate

메모리에 적재되었을 때부터를 program이라 한다. 그 이전까지를 programming이라 한다.

RUN TIME에도 안잡히는 에러를 Context Error라고 한다.
RUN TIME ERROR도 잡기 힘들다. (구현도 어렵고.. 복잡함)
```

#### Script Program

```
language code       LINT TIME 
file
load
machine language    !!
run                 RUN TIME
terminate
```

#### RUN TIME

##### Compile program
```
File이 메모리에 적재되면 '명령'과 '값' 두가지로 적재됨
CPU : L1/L2 cache - 외부 인터페이스(외부버스)
        제어유닛 디코더 (메모리의 명령부분을 받음, 디코딩)
        연산유닛 제어정보 (실행)
        데이터유닛 (메모리의 값부분을 받아온 후 연산유닛에게 보낸 후 연산 후 다시 데이터 유닛으로.. 이후 다시 메모리 상의 값으로..)
    Instruction fetch(외부버스) & decoding = EXCUTION

    노이만 머신
    결국 메모리에서 값을 가져와서 CPU에서 연산 후 메모리에 넣는 것밖에..

Compiler는 가상메모리에 올려서 이 작업을 함
>
Essential definition loading
vtable mapping
run
runtime defintion loading
```

##### Script program

```
Run 
        Declare base function, class. 
    STATIC TIME 
    -----(상대적으로 보자)
    RUNTIME     
        Declare extended function, class.
    STATIC TIME 
    -----(상대적으로 보자)
    RUNTIME      
        Use function, class
```

#### Memory, Address, Pointer, Variables, Dispatch

```
메모리는 고유한 주소를 가지고 있는 블록 체계
직접 참조는 ㅡ.ㅡ
FP -> 참조하지마! 값만써
OOP -> 직접참조하지마! -> Double Dispatch(참조에 참조.. B.VALUE = &K 즉, VALUE값을 바꿔도..
```

#### LEXICAL GRAMMAR

```
Control character 제어문자 (아랍문자에나 있는..)
white space 공백문자
line terminators 개행문자
comments 개행문자
keyword 예약어
literals 리터럴(더이상 나눌 수 없는 객체나 숫자)
```

#### LANGUAGE ELEMENT

```
Statements 문    | 공문, 식문, 제어문, 선언문  | 단문, 중문 
                    if문 -> 메모리에 안남아 a= if() 이런형태는 없어. 실행기에 보내는 힌트다. (ruby는 문이 없어서 a= if가 가능)
                    공문 : 아무것도 없는 것 ;;;;;
                    선언문 : var, const -> 메모리상에 변수를 선언하는 문
                        변수란 메모리 주소의 별명, 크기, 타입 => vtable에 써둠
                    제어문 : 
                    
                    중문 : if(true) {;;}
                           if(true) a = 3; else {if (a>2) b = 3; else b = 5;}

                           const f = (a) => {}  -- body
                           const f = (a) => ({})  -- value
Expression 식    | 값식, 연산식, 호출식
                    식 == 값
                    값식 : 3, true , ..
                    호출식 : java에는 void가 있지만, js는 undefined든 어쨋든 값이 나옴..
                    하나의 식을 식문으로 인정한다. 
                        3;5;6;
                        if(true) 3
IDENTIFIER 식별자 | 기본형, 참조형
```

#### Sync Flow

```
폰노이만 머신에선 CPU에서 동작하는 동안에는 건드릴수..
제어문을 통해 흐름을 제어할 수 있음.
Sub Flow: 함수, 클래스를 통해 구현
```


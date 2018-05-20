## 6장 메서드 정리

```
거의 모든 문제점은 장황한 메서드로 인해 생긴다.
```

#### 메서드 추출 (Extract Method)

```
어떤 코드를 그룹으로 묶어도 되겠다고 판단될 땐 그 코드를 빼내서 목적을 잘 나타내는 직관적 이름의 메서드로 만들자.
중요한 것은 메서드 길이가 아니라 메서드명과 메서드 내용의 의미적 차이라고 할 수 있다.
```

#### 메서드 내용 직접 삽입 (Inline Method)

```
메서드 기능이 너무 단순해서 메서드명만 봐도 너무 뻔할 땐 그 메서드의 기능을 호출하는 메서드에 넣어버리고 그 메서드는 삭제하자
```

```java
int getRating() {
    return (moreThanFiveLateDeliveries()) ? 2 : 1;
}
boolean moreThanFiveLateDeliveries() {
    return _numberOfLateDeliveries > 5;
}
```

```java
int getRating() {
    return (_numberOfLateDeliveries > 5) ? 2 : 1;
}
```

#### 임시변수 내용 직접 삽입 (Inline Temp)

```
간단한 수식을 대입받는 임시변수로 인해 다른 리팩토링 기법 적용이 힘들 땐 그 임시변수를 참조하는 부분을 전부 수식으로 치환하자
```

```java
double basePrice = anOrder.basePrice()
return (basePrice > 1000)
```

```java
return (anOrder.basePrice() > 1000)
```

#### 임시변수를 메서드 호출로 전환 (Replace Temp with Query)

```
임시변수는 일시적이고 적용이 국소적 범위로 제한된다.

수식의 결과를 저장하는 임시변수가 있을 땐 
그 수식을 뺴내서 메서드로 만든 후, 임시변수 참조부분을 전부 수식으로 교체하자
```

```java
double basePrice = _quantity * _itemPrice;
if (basePrice > 1000)
    return basePrice * 0.95;
else
    return basePrice * 0.98;
```

```java
if (basePrice() > 1000)
    return basePrice() * 0.95;
else 
    return basePrice() * 0.98;

double basePrice() {
    return _quantity * _itemPrice;
}        
```    

```

```

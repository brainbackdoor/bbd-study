#### 생성자 인자가 많을 때는 Builder 패턴을 적용하라


```java
public NutritionFacts(int servingSize) {
    this(servingSize);
}
public NutritionFacts(int servingSize, int servings) {
    this(servingSize, servings);
}
...
```

```
1. 점층적 생성자 패턴는 잘 동작하지만 인자 수가 늘어나면 클라이언트 코드를 작성하기가 어려워지고, 무엇보다 읽기 어려운 코드가 되고 만다.
또한 클라이언트가 두 개 인자의 순서를 실수로 뒤집어도 컴파일러는 알지 못하며, 프로그램 실행 도중에 문제가 생기게 된다.
```

```java
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
...
```

```
2. 자바빈 패턴의 경우엔 코드량이 조금 많아질 수는 있지만 객체를 생성하기도 쉽고, 읽기도 좋다. 다만, 1회의 함수호출로 객체 생성을 끝낼수 없으므로 객체 일관성이 일시적으로 꺠질 수 있어 디버깅하기가 어렵다. 또한 Immutable 클래스를 만들 수 없고 Thread-safty를 제공하기 위해 해야 할 일도 많아진다.
```

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;

    public static class Builder {
        private final int servingSize;
        private final int servings;

        public Builder() {
        }

        public Builder servingSize(int servingSize){
            this.servingSize = servingSize;
        }

        public Builder servings(int servings) {
            this.servings = servings;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }

        private NutritionFacts(Builder builder) {
            servingSize = builder.servingSize;
            servings = builder.servings;
        }
    }
}

NutritionFacts cocaCola = new NutritionFacts.Builder().servingSize(240).servings(8).build();
```

```
빌터패턴은 인자가 많은 생성자나 정적 팩터리가 필요한 클래스를 설계할 때, 특히 대부분의 인자가 선택적 인자인 상황에 유용하다.
```
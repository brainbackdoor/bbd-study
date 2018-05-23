#### 객체 생성을 막을 때는 private 생성자를 사용해라

```
유틸리티 클래스들은 객체를 만들 목적의 클래스가 아니다.
하지만 생성자를 생략하면 컴파일러는 자동으로 인자 없는 public default constructor를 만들어버린다.
```

```java
public class UtilityClass {
    //기본 생성자가 자동 생성되지 못하도록 하여 객체 생성 방지
    private UtilityClass() {
        throw new AssertionError();
    }
}
```

#### 재사용이 가능하다면, 불필요한 객체는 만들지 말라

```java
public class Person {
    public boolean isBabyBoomer() {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >=0 &&
                birtDate.compareTo(boomEnd) < 0;
    }
}
```

```java
public class Person {

    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >=0 &&
                birtDate.compareTo(BOOM_END) < 0;
    }
}
```
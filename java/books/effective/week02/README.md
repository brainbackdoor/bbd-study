#### private 생성자나 enum 자료형은 싱글턴 패턴을 따르도록 설계하라

```
싱글턴은 객체를 하나만 만들 수 있는 클래스다.
```

```java
// private + static + final 선언
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() {}
}
// 이 방법은 AccessibleObject.setAccessible 메서드의 도움을 받아 권한을 획득한 후 리플렉션을 통해 private 생성자를 호출할 수 있다. 이를 방어하기 위해서는 두번째 객체 생성요청에 대해 예외처리를 해야 한다.
```

```java
// 정적 팩터리 메서드 이용
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis {}
    public static Elvis getInstance() { return INSTANCE; }
}
// Serializable 시에 모든 필드를 transient로 선언하고 readResolve 메서드를 추가해야 한다. 그렇지 않으면 serialize된 객체가 deserialize될 때마다 새로운 객체가 생기게 된다.
```

```java
private Object readResolve() {
    // 동일한 Elvis 객체가 반환되는 동시에, 가짜 Elvis 객체는 Garbage Collector가 처리하도록 만든다.
    return INSTANCE;
}
```

```java
// 원소가 하나뿐인 enum 자료형을 통한 싱글턴 구현은 위의 문제점을 모두 해결하는 가장 좋은 방법
public enum Elvis {
    INSTANCE;
}
```
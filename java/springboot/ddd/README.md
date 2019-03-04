## DDD Start

#### Requirements

```
- 최소 한 종류 이상의 상품을 주문해야 한다.
- 한 상품을 한 개 이상 주문할 수 있다.
- 총 주문 금액은 각 상품의 구매 가격 합을 모두 더한 금액이다.
- 각 상품의 구매 가격 합은 상품 가격에 구매 개수를 곱한 값이다.
- 주문할 때 배송지 정보를 반드시 지정해야 한다.
- 배송지 정보는 받는 사람 이름, 전화번호, 주소로 구성된다.
- 출고를 하면 배송지 정보를 변경할 수 없다.
- 출고 전에 주문을 취소할 수 있다.
- 고객이 결제를 완료하기 전에는 상품을 준비하지 않는다.
```

#### Chapter 1

```
- 단순히 코드를 보기 좋게 작성하는 것 뿐만 아니라 도메인 관점에서 코드가 도메인을 잘 표현해야 
  비로소 코드의 가독성이 높아지며 문서로서 코드가 의미를 갖는다.
```

##### Entity vs Value

```
- Entity는 식별자를 갖는다.
- Value를 Immutable로 생성해야 하는 이유: 1) 참조투명성, 2) Thread-safe 
```

##### set method를 쓰지 말아야 할 이유

```
- 도메인의 핵심 개념이나 의도를 코드에서 사라지게 한다.
- 도메인 객체가 불완전한 상태로 생성되어 사용되는 것을 방지하려면 생성자를 통해 필요한 데이터를 모두 받아야 한다.
```

#### Chapter 2

```
- 표현영역은 웹 브라우저가 HTTP 요청 파라미터로 전송한 데이터를 응용서비스가 요구하는 형식의 객체 타입으로 변환해서 전달하고,
  응용서비스가 리턴한 결과를 JSON 형식으로 변환해서 HTTP 응답으로 웹 브라우저에게 전송한다.
  
- 응용서비스는 로직을 직접 수행하기보다는 도메인 모델에 로직 수행을 위임한다.

- 인프라스트럭처 영역은 구현 기술에 대한 것을 다룬다. 
  이 영역은 RDBMS 연동을 처리하고, 메시징 큐에 메시지를 전송하거나 수신하는 기능을 구현하고,
  몽고DB나 HBase를 사용해서 데이터베이스 연동을 처리한다. 
  이 영역은 SMTP를 이용한 메일 발송 기능을 구현하거나 HTTP 클라이언트를 이용해서 REST API를 호출하는 것도 처리한다.
```

```
- 인프라스트럭처에 의존하면 '테스트 어려움'과 '기능 확장의 어려움'이 발생한다.
  이에 DIP가 필요하다. 고수준 모듈에서는 인터페이스를 사용할 뿐이고, 저수준 모듈에서 인터페이스를 구현한다.
  고수준 모듈이 저수준 모듈에 의존하지 않기 때문에 테스트 대용객체(Mock)을 이용해서 테스트가 가능하다.
  주의할 점은, DIP를 적용할 때 하위 기능을 추상화한 인터페이스는 고수준 모듈 관점에서 도출해야 한다는 것이다.
```

#### Chapter 3

```
- 수많은 객체를 애그리거트로 묶어서 바라보면 좀 더 상위 수준에서 도메인 모델 간의 관계를 파악할 수 있다.
- 애그리거트에 속한 구성요소는 대부분 함께 생성하고 제거된다.
- 애그리거트에 속한 모든 객체가 일관된 상태를 유지하려면 애그리거트 전체를 관리할 주체가 필요한데 이 책임을 지는 것이 바로 애그리거트 루트 엔티티이다.
  이를 위해 애그리거트 루트는 애그리거트가 제공해야 할 도메인 기능을 구현한다. 
```

#### Chapter 4

```
- Repository 인터페이스는 도메인 영역에 속하고 Repository를 구현한 클래스는 인프라스트럭처 영역에 속한다.
- Repository 인터페이스는 루트 엔티티를 기준으로 작성한다. (@Entity)
- 한 테이블에 엔티티와 밸류데이터가 같이 있다면, 밸류는 @Embeddable로, 밸류 타입 프로퍼티는 @Embedded로 매핑 설정한다.

@Embeddable
public class Orderer {
    @Embedded
    private MemberId memberId;
}

- 기본생성자는 JPA프로바이더가 객체를 생성할 때만 사용한다. 다만, 하이버네이트가 클래스롤 상속한 프록시 객체를 이용해서 지연로딩을 구한하기도 하므로,
  지연 로딩 대상이 되는 @Entity와 @Embeddable의 기본생성자는 private가 아닌 protected로 지정해야 한다.
  
- int, long, String, LocalDate와 같은 타입은 DB 테이블의 한 개 칼럼과 매핑된다.
- 밸류타입의 프로퍼트를 한 개 칼럼에 매핑해야 할 때도 있다. 

public class Length {
    private int value;
    private String unit;
} -> WIDTH VARCHAR(28) 
ex) 1000mm 로 저장하는 경우

  이 경우 AttributeConverter를 이용하자.

@Converter(autoApply = true)
public class LengthConverter implements AttributeConverter<Length, String> {
    @Override
    public String convertToDatabaseColumn(Length length) {
        if (length == null)
            return null;
        else 
            return length.getValue().toString() + length.getUnith();
    }
    
    @Override
    public Length convertToEntityAttribute(String value) {
        ...
    }
}

- 밸류 컬렉션을 별도 테이블로 매핑할 때는 @ElementCollection과 @CollectionTeable을 함께 사용한다.

@Entity
@Table(name = "purchase_order")
public class Order {
    ...
    @ElementCollection
    @CollectionTable(name = "order_line", 
                        joinColumns = @JoinColumn(name = "order_number")
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;
    ... 
} // JPA는 @OrderColumn 애노테이션을 이용해서 지정한 칼럼에 리스트의 인덱스 값을 저장한다.
@Embeddable
public class OrderLine {
    @Embedded
    private ProductId productId;
    ...
}

- 밸류타입을 식별자로 매핑하려면 @Id 대신 @EmbeddedId를 사용한다.
  JPA에서 식별자 타입은 Serializable 타입이어야 한다.
  
@Entity
public cloass Order {
    @EmbeddedId
    private OrderNo number;
    ...
}

@Embeddable
public class OrderNo implements Serializable {
    @Column(name = "order_number")
    private String number;
    ...
}

- 애그리거트에 속한 객체가 밸류인지 엔티티인지 구분하는 방법은 고유 식별자를 갖는지 여부를 확인하는 것이다.
  하지만 식별자를 찾을 때 매핑되는 테이블의 식별자를 애그리거트 구성요소의 식별자와 동일한 것으로 착각하면 안된다.
  밸류를 매핑한 테이블을 지정하기 위해 @SecondaryTable과 @AttributeOverride를 사용한다.

@Entity
@Table(anem = "article")
@SencodaryTable(
    name = "article_content",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
public class Article {
    @id
    private Long id;
    ...
    @AttributeOverrides({
        @AttributeOverride(name = "content", column = @Column(table = "artitcle_content")),
        @AttributeOverride(name = "contentType", column = @Column(table = "artitcle_content"))
    })
    private ArticleContent content;
    ...   
// @SecondaryTable로 매핑된 article_content 테이블을 조인
Article article - entityManger.find(Article.class, 1L);
    모든 조회시에 article_content 데이터가 필요하지 않을 수 있다.
    그렇다고 ArticleContent를 엔티티로 매핑하고 Article에서 ArticleContent로의 로딩을 지연로딩 방식으로 설정하는 것은 좋은 방법이 아니다 (왜???!!!!!)
    JPA에서 조회 전용 쿼리를 실행하는 방식으로 해결하자. 
    
- 애그리거트 간 집합 연관은 성능상의 이유로 피해야 한다.
  그럼에도 요구사항을 구현하는데 집합 연관을 사용하는 것이 유리하다면 ID 참조를 이용한 단방향 집합 연관을 적용핼볼 수 있다.
  
@Entity
@Table(name = "product")
public class Product {
    @EmbeddedId
    private ProductId id;
    
    @ElementCollection
    @CollectionTable(name = "product_category",
                    joinColumns = @JoinColumn(name = "product_id"))
    private Set<CategoryId> categoryIds;
}
    @ElementCollection을 이요하기 때문에 Product를 삭제할 때 매핑에 사용한 조인테이블의 데이터도 함께 삭제된다. 
    애그리거트를 직접 참조하는 방식을 사용했다면 영속성 전파나 로딩 전략을 고민해야 하는데 ID 참조 방식을 사용함으로써 이런 고민을 할 필요가 없다.
    
- @Embeddable 매핑 타입의 경우 함께 저장되고 삭제되므로 cascade 속성을 추가로 설정하지 않아도 된다.

- JPA의 식별자 생성기능을 사용하는 경우 DB의 insert쿼리가 실행해야 식별자가 생성된다.
  JPA는 저장 시점에 생성한 식별자를 @Id로 매핑한 프로퍼티/필드에 할당한다.

public class ... {
    ...
    articleRepository.save(article);
    return article.getId(); // 저장 이후 식별자 사용 가능
}
```

#### Chapter 5

```
- 리포지토리는 애그리거트의 저장소이다. 애그리거트를 저장하고 찾고 삭제하는 것이 리포지토리의 기본 기능이다.
- 다양한 검색 조건을 조합하기 위해 CriteriaBuilder와 Predicate를 사용하여 구현해야 한다.
- 정렬, 페이징, 개수 등 구현 --> Spring-data-jpa에서 지원
- 1)여러 애그리거트를 조합해서 한 화면에 보여주는 데이터 제공 2)각종 통계데이터 제공 등의 이유로 리포지토리를 사용하는 것은 적합하지 않다.
  애그리거트간 직접 연관을 맺으면 ID로 참조할 떄의 장점을 활용할 수 없게 된다.
  또한 JPA의 지연로딩/즉시로딩 설정, 연관관계 매핑과 더불어 조인 등의 쿼리를 JPQL/Criteria 등으로 구현하는 등에 있어 러닝커브가 있다. 
  이런 부분들은 조회 전용 쿼리로 처리해야 한다. (JPA를 좀 더 학습하자!!!!!!!)
```

#### Chapter 6

```
- 사용자와의 상호작용은 표현영역이 처리하기 때문에 응용서비스는 표현 영역에 의존하지 않는다. 
  응용 영역은 사용자가 웹 브라우저를 사용하는지, REST API를 호출하는지, TCP 소켓을 사용하는지 여부를 알 필요가 없다.
  단지, 응용영역은 기능 실행에 필요한 입력값을 전달받고 실행 결과만 리턴하면 될 뿐이다.
- 응용 서비스의 주요 역할은 도메인 객체를 사용해서 사용자의 요청을 처리하는 것이므로 
  표현(사용자) 영역 입장에서 보았을 때 응용서비스는 도메인 영역과 표현 영역을 연결해 주는 창구인 파사드(facade) 역할을 한다.
- 도메인 객체 간의 실행 흐름을 제어하는 것과 더불어 응용 서비스의 주된 역할 중 하나는 트랜잭션 처리이다.
- 도메인 로직을 도메인 영역과 응용서비스에 분산해서 구현하면 코드 품질에 문제가 발생한다.
  이에 코드의 응집성이 떨어져, 여러 응용 서비스에서 동일한 도메인 로직을 구현할 가능성이 높아진다. 
- 구분되는 기능별로 서비스 클래스를 구현하는 방식은 한 응용 서비스 클래스에서 한 개 내지 2~3개의 기능을 구현한다. 
  이 방식을 사용하면 클래스 개수는 많아지지만 한 클래스에 관련 기능을 모두 구현하는 것과 비교해서 코드 품질을 일정 수준으로 유지하는데 도움이 된다. 
  또한, 각 클래스별로 필요한 의존객체만 포함하므로 다른 기능을 구현한 코드에 영향을 받지 않는다. 
- 각 기능마다 동일한 로직을 구현할 경우 여러 클래스에 중복해서 동일한 코드를 구현할 가능성이 있다. 
  이런 경우 Helper 클래스에 로직을 구현해서 코드가 중복되는 것을 방지할 수 있다.
  
- 표현 영역의 주요 역할은, 1) 사용자가 시스템을 사용할 수 있는 (화면) 흐름을 제공하고 제어한다.
  2) 사용자의 요청을 알맞은 응용서비스에 전달하고 결과를 사용자에게 제공한다.
  3) 사용자의 세션을 관리한다. 
- 값 검증은 표현영역과 응용서비스 두 곳에서 모두 수행된다. 원칙적으로 모든 값에 대한 검증은 응용서비스에서 처리하지만,
  필수 값, 값의 형식, 범위 등을 확인할 목적으로는 표현영역에서 검사하는 것이 좋다.   
  응용서비스는 데이터의 존재유무와 같은 논리적 오류를 검증한다. 

- 권한검사
  표현영역: URL을 처리하는 컨트롤러에 웹 요청을 전달하기 전에 인증여부를 검사해서 인증된 사용자의 웹 요청만 컨트롤러에 전달한다.
          인증된 사용자가 아닐 경우 로그인 화면으로 리다이렉트시킨다.
          이런 접근 제어를 하기에 좋은 위치는 서블릿 필터이다.
  응용서비스: AOP를 활용 ( @PreAuthorize("hasRole('ADMIN'")
```

#### Chapter 7

```
- 특정 기능이 응용서비스인지 도에인 서비스인지 감을 잡기 어려울 때는 해당 로직이 애그리거트의 상태를 변경하거나 애그리거트의 상태 값을 계산하는지 검사해 보면 된다.
  예를 들어, 계좌 이체 로직은 계좌 애그리거트의 상태를 변경한다. 결제 금액 로직은 주문 애그리거트의 주문 금액을 계산한다. 
  이 두 로직은 각각 애그리거트를 변경하고 애그리거트의 값을 계산하는 도메인 로직이다. 
  도메인 로직이면서 한 애그리거트에 넣기 적합하지 않으므로 이 두 로직은 도메인 서비스로 구현하게 된다. 
- 도메인 서비스는 도메인 로직을 실행하므로 패키지 위치도 도메인과 동일한 곳에 위치한다.
  다만, 도메인 서비스 구현이 특정 구현 기술에 의존적이거나 외부 시스템의 API를 실행한다면 도메인 영역의 도메인 서비스는 인터페이스로 추상화해야한다.
  그리고 실제 구현은 인프라스트럭처 영역에 위치시켜야 한다. 
```

#### Chapter 8

```
- 애그리거트의 트랜잭션 관리에는 선점잠금, 비선점잠금, 오프라인 선점잠금방식이 있다.
  선점잠금의 경우 먼저 애그리거트를 구한 스레드가 애그리거트 사용이 끝날 때까지 다른 스레드가 해당 애그리거트를 수정하는 것을 막는 방식이다.
  하지만, 이 경우 교착상태가 발생할 수 있다.
  비선점잠금의 경우 잠금을 해서 동시에 접근하는 것을 막는 대신, 변경한 데이터를 실제 DBMS에 반영하는 시점에 변경 가능 여부를 확인하는 방식이다. 
  오프라인 선점잠금방식의 경우 특정 사용자가 먼저 편집중이면 다른 사용자가 수정하려 할 떄 안내 문구를 보여줄 수 있다.
```

#### Chapter 9

```
- Bounded Context는 논리적으로 한 개의 모델을 갖는다.
- 도메인 기능을 사용하는 사용자에게 제공하는데 필요한 모든 영역(Presentation, Application, ...)을 포함한다.
- CQRS는 상태를 변경하는 명령기능과 내용을 조회하는 쿼리기능을 위한 모델을 구분하는 패턴
- Bounded Context간 메시지큐를 사용하여 간접적으로 통합하기도 한다.
```
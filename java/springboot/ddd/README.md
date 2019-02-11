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
}
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
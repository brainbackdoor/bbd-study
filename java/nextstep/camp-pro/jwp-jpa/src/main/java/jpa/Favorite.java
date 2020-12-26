package jpa;

import javax.persistence.Entity;

@Entity
public class Favorite extends BaseEntity {
    private Long memberId;

    private Long upLineId;

    private Long downLineId;

    public Favorite() {
    }

    public Favorite(Long memberId, Long upLineId, Long downLineId) {
        this.memberId = memberId;
        this.upLineId = upLineId;
        this.downLineId = downLineId;
    }
}

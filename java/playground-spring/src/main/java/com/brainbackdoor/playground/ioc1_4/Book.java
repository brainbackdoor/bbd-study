package com.brainbackdoor.playground.ioc1_4;

import java.util.Date;

public class Book {
    private Date created;
    private BookStatus status;

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public BookStatus getStatus() {
        return status;
    }
}

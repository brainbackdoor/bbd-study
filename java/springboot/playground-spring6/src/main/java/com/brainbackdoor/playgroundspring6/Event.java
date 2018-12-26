package com.brainbackdoor.playgroundspring6;

import javax.validation.constraints.*;

public class Event {

    Integer id;

    @NotEmpty
    String title;

    @NotNull @Min(0)
    Integer limit;

    @Email
    String email;

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLimit() {

        return limit;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }
}

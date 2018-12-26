package com.elasticsearch.bbd.model;

public class Suggest {
    public String name;
    public String field;
    public String text;

    public Suggest() {
    }

    public Suggest(String name, String field, String text) {
        this.name = name;
        this.field = field;
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }

    public String getText() {
        return text;
    }
}

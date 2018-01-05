package com.example.model.valueobject;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.ToString;

@JsonRootName("queryStore")
@Getter
@ToString
public class QueryStore {

    private AcademyQueryDto queryStore;
}

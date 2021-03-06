package com.educhoice.motherchoice.valueobject.models.query;

import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.ToString;

@JsonRootName("queryStore")
@Getter
@ToString
public class QueryStore {

    private AcademyQueryDto queryStore;
}

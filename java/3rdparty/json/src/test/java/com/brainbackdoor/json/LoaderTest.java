package com.brainbackdoor.json;

import org.everit.json.schema.Schema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoaderTest {
    @Test
    void load() {
        Loader._schemas.values().stream().forEach(v-> System.out.println(v.getTitle()));
        assertThat(Loader._schemas.values().stream().findFirst().get().getTitle(),is("Pageview"));
    }

    @Test
    @DisplayName("Restrict the execution of puts from outside")
    void exceptionWhenPut() {
        Schema schema = Loader._schemas.get("Pageview");
        assertThrows(UnsupportedOperationException.class, () -> Loader._schemas.put("Temp", schema));
    }
}
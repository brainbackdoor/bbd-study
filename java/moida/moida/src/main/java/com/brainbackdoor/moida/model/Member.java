package com.brainbackdoor.moida.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String name;
    private String title;
    private String blogLink;
    private String fbLink;

    public Map<String, Object> get() {
        Map map = new HashMap();
        map.put("member", this);
        return map;
    }
}

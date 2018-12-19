package com.brainbackdoor.playgroundspring7;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

// Property Editor는 Thread-safe하지 않기 때문에 빈에 등록해서 쓰면 안된다!!
public class EventEditor extends PropertyEditorSupport {
    /*
        String과 Object간의 변환만 가능하다.
     */

    @Override
    public String getAsText() {
        Event event = (Event) getValue();
        return event.getId().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new Event(Integer.parseInt(text)));
    }
}

package com.brainbackdoor.playgroundspring9;

import java.lang.annotation.*;
// CLass 이상으로.. 이게 Default

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS) // 이 애노테이션을 정보를 얼마나 유지할 것인가란 의미..
public @interface PerfLogging {
}

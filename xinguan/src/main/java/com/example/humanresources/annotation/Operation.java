package com.example.humanresources.annotation;

import java.lang.annotation.*;

/**
 * @author zf
 * @since 2021/12/09
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Operation {
    String value();
}

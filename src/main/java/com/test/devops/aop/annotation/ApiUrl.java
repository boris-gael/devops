package com.test.devops.aop.annotation;

import java.lang.annotation.*;

@Repeatable(ApiUrls.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiUrl {

    String value() default "";

}

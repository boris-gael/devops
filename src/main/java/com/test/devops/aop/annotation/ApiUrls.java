package com.test.devops.aop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiUrls {
    ApiUrl[] value();
}

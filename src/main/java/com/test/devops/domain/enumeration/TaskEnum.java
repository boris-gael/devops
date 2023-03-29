package com.test.devops.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskEnum {

    BATCH_IMPORT_PRODUCT("BATCH_IMPORT_PRODUCT", "0 53 11 * * *"),
    BATCH_IMPORT_TODO("TODO", "* * * * *");

    private String job;
    private String cronExpression;
}

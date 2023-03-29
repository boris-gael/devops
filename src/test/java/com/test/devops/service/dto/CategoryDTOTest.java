package com.test.devops.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDTOTest {

    @Test
    void builderPatternTest() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Sport")
                .description("All Sports")
                .build();
        assertAll(
                () -> assertEquals("Sport", categoryDTO.getName()),
                () -> assertEquals("All Sports", categoryDTO.getDescription())
        );
    }

}

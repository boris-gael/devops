package com.test.devops.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private String name;
    private String description;

    private CategoryDTO(CategoryDTOBuilber categoryDTOBuilber) {
        this.name = categoryDTOBuilber.name;
        this.description = categoryDTOBuilber.description;
    }

    public static class CategoryDTOBuilber {
        private String name;
        private String description;

        public CategoryDTOBuilber() {}

        public CategoryDTOBuilber name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDTOBuilber description(String description) {
            this.description = description;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }

    public static CategoryDTOBuilber builder() {
        return new CategoryDTOBuilber();
    }

}

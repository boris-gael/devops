package com.test.devops.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;

    private CategoryDTO(CategoryDTOBuilber categoryDTOBuilber) {
        this.id = categoryDTOBuilber.id;
        this.name = categoryDTOBuilber.name;
        this.description = categoryDTOBuilber.description;
    }

    public static class CategoryDTOBuilber {
        private Long id;
        private String name;
        private String description;

        public CategoryDTOBuilber() {}

        public CategoryDTOBuilber id(Long id) {
            this.id = id;
            return this;
        }

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

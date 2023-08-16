package com.test.devops.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;

    private CategoryDTO(CategoryDTOBuilder CategoryDTOBuilder) {
        this.id = CategoryDTOBuilder.id;
        this.name = CategoryDTOBuilder.name;
        this.description = CategoryDTOBuilder.description;
    }

    public static class CategoryDTOBuilder {
        private Long id;
        private String name;
        private String description;

        public CategoryDTOBuilder() {}

        public CategoryDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CategoryDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }

    public static CategoryDTOBuilder builder() {
        return new CategoryDTOBuilder();
    }

}

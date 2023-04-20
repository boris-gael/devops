package com.test.devops.service.mapper;


import com.test.devops.domain.Category;
import com.test.devops.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDto(Category category);

    List<Category> toEntity(List<CategoryDTO> categoryDTOS);

    List<CategoryDTO> toDto(List<Category> categories);
}

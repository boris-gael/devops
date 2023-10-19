package com.test.devops.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Cloneable {

    private Long id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Double price;
    private CategoryDTO categoryDTO;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

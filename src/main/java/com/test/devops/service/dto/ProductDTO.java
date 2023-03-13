package com.test.devops.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Double price;

}

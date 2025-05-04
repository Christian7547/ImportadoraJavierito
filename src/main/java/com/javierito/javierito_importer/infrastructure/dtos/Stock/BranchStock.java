package com.javierito.javierito_importer.infrastructure.dtos.Stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchStock {
    private String branchName;
    private Integer quantity;
}

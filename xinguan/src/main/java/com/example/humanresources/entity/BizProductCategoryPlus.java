package com.example.humanresources.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizProductCategoryPlus {
    private BizProductCategory bizProductCategory;
    private List<BizProductCategory> rows;
    private int total;
}

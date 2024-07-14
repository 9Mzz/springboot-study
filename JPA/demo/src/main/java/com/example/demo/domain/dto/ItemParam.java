package com.example.demo.domain.dto;

import lombok.Data;

@Data

public class ItemParam {

    private Long itemId;
    private int  itemCount;

    public ItemParam(Long itemId, int itemCount) {
        this.itemId    = itemId;
        this.itemCount = itemCount;
    }
}

package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.swing.plaf.basic.BasicTreeUI;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String itemName;
    private int    itemPrice;
    private int    itemQuantity;

    public Item(String itemName, int itemPrice, int itemQuantity) {
        this.itemName     = itemName;
        this.itemPrice    = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public void reduceCount(int count) {
        int totalCount = this.itemQuantity - count;
        if (totalCount < 0) {
            throw  new RuntimeException("수량이 부족합니다. 재고를 확인해주세요");
        }
    }
}

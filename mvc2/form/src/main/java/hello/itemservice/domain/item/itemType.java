package hello.itemservice.domain.item;

import jdk.dynalink.beans.StaticClass;

import java.security.PrivateKey;

public enum itemType {

    BOOK("도서"),
    FOOD("음식"),
    ETC("기타");

    private final String description;

    itemType(String description) {
        this.description = description;
    }
}

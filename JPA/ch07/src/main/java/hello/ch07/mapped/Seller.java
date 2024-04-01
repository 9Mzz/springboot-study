package hello.ch07.mapped;

import jakarta.persistence.Entity;

import java.io.PipedReader;

@Entity
public class Seller extends BaseEntity {

    /**
     * ID 상속
     * Name 상속
     */
    private String shopName;

}

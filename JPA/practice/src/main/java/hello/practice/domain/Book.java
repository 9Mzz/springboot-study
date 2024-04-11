package hello.practice.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("book")
public class Book extends Item {

    private String autor;
    private int    ISBN;

    //

    public Book() {
    }

    public Book(String autor, int ISBN) {
        this.autor = autor;
        this.ISBN  = ISBN;
    }

    public Book(String name, int price, String autor, int ISBN) {
        super(name, price);
        this.autor = autor;
        this.ISBN  = ISBN;
    }
}

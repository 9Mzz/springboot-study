package hello.practice.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

}

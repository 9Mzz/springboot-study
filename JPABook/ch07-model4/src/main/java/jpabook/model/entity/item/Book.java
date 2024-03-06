package jpabook.model.entity.item;

import javax.persistence.*;

/**
 * Created by holyeye on 2014. 3. 11..
 */

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    @JoinTable(name = "",
               joinColumns = @JoinColumn(name = ""),
               inverseJoinColumns = @JoinColumn(name = ""))
    private String author;
    private String isbn;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{}";
    }
}

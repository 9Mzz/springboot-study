package hello.practice.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
public class Movie extends Item {

    private String movie;
    private String actor;

    //
    public Movie() {
    }

    public Movie(String name, int price) {
        super(name, price);
    }

    public Movie(String name, int price, String movie, String actor) {
        super(name, price);
        this.movie = movie;
        this.actor = actor;
    }
}

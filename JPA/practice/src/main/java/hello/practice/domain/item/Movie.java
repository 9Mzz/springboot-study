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
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;
}

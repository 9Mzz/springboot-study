package hello.practice.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("album")
@NoArgsConstructor
@AllArgsConstructor
public class Album extends Item {

    private String artist;

}

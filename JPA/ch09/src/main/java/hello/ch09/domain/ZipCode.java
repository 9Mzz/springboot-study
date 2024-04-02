package hello.ch09.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ZipCode {

    private String zip;
    private String plusFour;
    //


    public ZipCode(String zip, String plusFour) {
        this.zip      = zip;
        this.plusFour = plusFour;
    }
}

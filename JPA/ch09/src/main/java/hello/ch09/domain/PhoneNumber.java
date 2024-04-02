package hello.ch09.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PhoneNumber {

    private String areaCode;
    private String localNumber;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    PhoneServiceProvider provider;
    //

    public PhoneNumber(String areaCode, String localNumber) {
        this.areaCode    = areaCode;
        this.localNumber = localNumber;
    }
}

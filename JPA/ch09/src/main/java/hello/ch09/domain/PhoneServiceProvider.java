package hello.ch09.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class PhoneServiceProvider {
    @Id
    @Column(name = "provider_id")
    private Long id;

}

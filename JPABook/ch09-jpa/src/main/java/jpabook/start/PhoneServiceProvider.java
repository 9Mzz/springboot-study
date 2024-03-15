package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneServiceProvider {

    @Id
    @Column(name = "provider_name")
    private String name;


}

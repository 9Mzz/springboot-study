package hello.querydsl.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Entity
public class Hello {

    @Id
    private Long   id;
    private String helloWorld;

}

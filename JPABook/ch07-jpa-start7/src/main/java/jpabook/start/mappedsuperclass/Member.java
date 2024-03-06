package jpabook.start.mappedsuperclass;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Entity
@Table(name = "name")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

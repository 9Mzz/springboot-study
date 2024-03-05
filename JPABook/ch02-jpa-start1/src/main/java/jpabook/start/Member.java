package jpabook.start;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
//@org.hibernate.annotations.DynamicUpdate
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long          id;
    @Column(name = "user_name")
    private String        username;
    private Integer       age;
    // n
    @OneToOne(mappedBy = "member")
    private Locker        locker;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team          team;
    @ManyToMany
    @JoinTable(name = "member_product",
               joinColumns = @JoinColumn(name = "member_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        product.getMembers().add(this);
        System.out.println("product = " + product);

    }

    //con
    public Member() {
    }

    public Member(String username, Integer age) {
        this.username = username;
        this.age      = age;
    }

    public Member(Long id, String username, Integer age) {
        this.id       = id;
        this.username = username;
        this.age      = age;
    }

    //g&s
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    //

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", username='" + username + '\'' + ", age=" + age + ", team=" + team + ", products=" + products + '}';
    }
}

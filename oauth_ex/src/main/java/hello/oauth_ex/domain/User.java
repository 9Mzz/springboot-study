package hello.oauth_ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role   role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name    = name;
        this.email   = email;
        this.picture = picture;
        this.role    = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

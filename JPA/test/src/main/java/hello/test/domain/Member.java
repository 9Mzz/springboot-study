package hello.test.domain;

import hello.test.exception.NoSuchMoneyException;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@Setter
@ToString(exclude = {"orders"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private String      memberName;
    private int         money;
    @Embedded
    private Address     address;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    //
    public Member(String memberName, int money, Address address) {
        this.memberName = memberName;
        this.money      = money;
        this.address    = address;
    }

    public int removeMoney(int totalPrice) throws NoSuchMoneyException {
        int resultMoney = this.getMoney() - totalPrice;
        if (resultMoney < 0) {
            throw new NoSuchMoneyException("잔액이 부족합니다");
        }
        return resultMoney;
    }

}

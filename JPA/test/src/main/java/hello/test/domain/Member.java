package hello.test.domain;

import hello.test.exception.NoSuchMoneyException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  memberName;
    private int     memberMoney;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    public Member(String memberName, int memberMoney, Address address) {
        this.memberName  = memberName;
        this.memberMoney = memberMoney;
        this.address     = address;
    }

    public void reduceMoney(int totalPrice) throws NoSuchMoneyException {
        if (this.memberMoney - totalPrice < 0) {
            throw new NoSuchMoneyException("잔액이 부족합니다.");
        }
        this.memberMoney -= totalPrice;
    }
}

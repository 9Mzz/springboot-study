package hello.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private String      memberName;
    @Embedded
    private Address     memberAddress;
    private int         memberMoney;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    //
    public Member(String memberName, Address memberAddress, int memberMoney) {
        this.memberName    = memberName;
        this.memberAddress = memberAddress;
        this.memberMoney   = memberMoney;
    }

    // 회원 money 감소
    public void reduceMoney(int money) {
        int totalMoney = this.getMemberMoney() - money;
        if (totalMoney < 0) {
            throw new IllegalStateException("잔액이 부족합니다");
        }
        this.memberMoney = totalMoney;

    }


}

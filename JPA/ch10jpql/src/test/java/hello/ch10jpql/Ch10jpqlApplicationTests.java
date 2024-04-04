package hello.ch10jpql;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.ch10jpql.domain.Member;
import hello.ch10jpql.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static hello.ch10jpql.domain.QMember.member;
import static hello.ch10jpql.domain.QOrder.order;

@Slf4j
@Transactional
@SpringBootTest
public class Ch10jpqlApplicationTests {


    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {

        // 부모
        Member memberA = new Member("memberA", new Random().nextInt(20));
        Member memberB = new Member("memberB", new Random().nextInt(30));

        Order orderA = new Order("orderA");
        orderA.setMember(memberA);
        memberA.getOrders().add(orderA);
        Order orderB = new Order("orderB");
        orderB.setMember(memberA);
        memberA.getOrders().add(orderB);
        Order orderC = new Order("orderC");
        orderC.setMember(memberA);
        memberA.getOrders().add(orderA);
        Order orderD = new Order("orderD");
        orderD.setMember(memberA);
        memberA.getOrders().add(orderA);
        Order orderE = new Order("orderE");
        orderE.setMember(memberA);
        memberA.getOrders().add(orderA);
        Order orderF = new Order("orderF");
        orderF.setMember(memberA);
        memberA.getOrders().add(orderA);

        em.persist(memberA);
    }

    @Test
    void 검색조건() {
        // 검색 조건은 .and().or()을 메서드 체인으로 연결할 수 있다.
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Order> orderList = query.selectFrom(order)
                // .where(member.memberName.startsWith("member").and(member.age.goe(5)))
                .where(order.orderName.startsWith("order")).fetch();
        for (Order order : orderList) {
            log.info("order = {}", order);
        }

    }

    @Test
    void 결과조회() {
        // fetch () -> 리스트 조회, 없으면 빈 리스트 반환
        JPAQueryFactory query = new JPAQueryFactory(em);
        // 리스트 조회
        query.selectFrom(member).fetch();
        // 단건 조회
        query.selectFrom(member).where().fetchOne();
        // 처음 한 건 조회
        query.selectFrom(member)
                // .limit(1).fetchOne(); ->  .fetchFirst()와 동이랗ㄴ 것
                .fetchFirst();
        // 페이징
        query.selectFrom(member).fetchResults().getResults();
    }

    @Test
    void 정렬() {
        // nullsLast(), nullsFirst() -> Null 데이터 순서 부여
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Member> memberList = query.selectFrom(member)
                .where(member.age.goe(5))
                .orderBy(member.age.desc(), member.memberName.asc().nullsLast())
                .fetch();
        for (Member member1 : memberList) {
            log.info("정렬 member = {}", member1);
        }
    }

    @Test
    void 페이징() {
        JPAQueryFactory query      = new JPAQueryFactory(em);
        List<Member>    memberList = query.selectFrom(member).orderBy(member.id.asc()).offset(0).limit(6).fetch();
        for (Member members : memberList) {
            log.info("페이징 members  = {}", members);
        }
    }

    @Test
    void 집합() {
        // Select 를 특정 컬럼으로 지정해주는 경우 queryDSL tuple을 조회
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Tuple> result = query.select(member.count(), member.age.sum(), member.age.avg(), member.age.max(), member.age.min())
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            // 집합 tuple = [11, 109, 9.909090909090908, 17, 4]
            log.info(" 집합 tuple = {}", tuple);
        }
    }

    @Test
    void groupByHaving() {
        // groupBy로 그룹화 된 결과를 제한하려면 having 절로 제한
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Tuple> fetch = query.select(order.orderDate, member.age.avg())
                .from(member)
                .join(member.orders, order)
                .groupBy(order.orderDate)
                .fetch();
        for (Tuple tuple : fetch) {
            log.info("having tuple = {}", tuple);
        }
    }

    @Test
    void 조인() {
        // join 의 기본 문법 -> 첫 번째 파라미터에 조인 대상을 지정, 두 번째 파라미터에 별칭(alias)로 사용할 Q타입을 지정
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Member> memberList = query.selectFrom(member)
                .join(member.orders, order)
                .where(order.orderName.eq("orderA"))
                .fetch();
        for (Member member1 : memberList) {
            log.info("조인 memberList = {}", member1);
        }
    }

    @Test
    void 페치조인X() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Order> orderList = query.selectFrom(order)

                .fetch();
        log.info("페치조인X =  {}", orderList);
    }

    @Test
    void 페치조인O() {
        JPAQueryFactory query = new JPAQueryFactory(em);

    }


    // @Test
    void grammer() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        query.select(member).where(
                // 검색 조건
                member.memberName.eq("member"),   // memberNAme = member
                member.memberName.ne("member"),  // memberNAme != member
                member.memberName.eq("member").not(),      // memberNAme != member
                member.memberName.isNotNull(),   // 이름이 not null
                member.age.in(10, 20),   // age = 10 or 20
                member.age.notIn(10, 20), // age != 10 or 20
                member.age.between(10, 20), // 10<= age <= 20
                member.age.goe(20), // age >= 20
                member.age.gt(20), // age > 20
                member.age.loe(20), // age <= 20
                member.age.lt(20), // age < 20
                member.memberName.like("member"),// memberName = member
                member.memberName.contains("member"), // memberName = %member%
                member.memberName.startsWith("member"), // memberName = member%
                member.memberName.endsWith("member") // memberName = %member

                //
        ).fetch();
    }

}

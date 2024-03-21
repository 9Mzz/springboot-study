package jpabook.start;

import jpabook.start.dto.UserDTO;
import jpabook.start.jpql.Member;
import jpabook.start.jpql.Product;
import jpabook.start.jpql.Team;
import org.hibernate.sql.Select;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MemberMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //logic
            memberAddLogic(em);
            //            embadedLogic(em);
            //            findEntityType(em);
            //new Logic
            //            newEmbaedLogic(em);
            // 페이징
            //            pagingLogic(em);
            // 조인
            innerJoinTest(em);

            tx.commit();
        } catch (Exception e) {

            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void memberAddLogic(EntityManager em) {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        for (int i = 0; i < 5; i++) {
            Member memberA = new Member(UUID.randomUUID()
                    .toString(), new Random().nextInt(50), teamA);
            em.persist(memberA);
        }
        for (int i = 0; i < 5; i++) {
            Member memberB = new Member(UUID.randomUUID()
                    .toString(), new Random().nextInt(50), teamB);
            em.persist(memberB);
        }
        for (int i = 0; i < 5; i++) {
            Member memberNull = new Member(UUID.randomUUID()
                    .toString(), new Random().nextInt(50), null);
            em.persist(memberNull);
        }
    }

    private static void embadedLogic(EntityManager em) {

        String jpql = "select m.userName, m.age From Member m";
        List<Object[]> resultList = em.createQuery(jpql)
                .getResultList();
        for (Object[] row : resultList) {
            String  userName = (String) row[0];
            Integer age      = (Integer) row[1];
        }
    }

    private static void findEntityType(EntityManager em) {
        String jpql = "select o.member, o.products, o.orderAmount From Orders o";
        List<Object[]> resultList = em.createQuery(jpql)
                .getResultList();
        for (Object[] row : resultList) {
            Member  findMember      = (Member) row[0];
            Product findProduct     = (Product) row[1];
            Integer findOrderAmount = (Integer) row[2];
        }
    }

    private static void newEmbaedLogic(EntityManager em) {
        /**
         * 패키지 명을 포함한 전체 클래스 명을 입력해야 함.
         * 순서와 타입이 일치하는 생성자가 필요함.
         */
        String jpql = "select new jpabook.start.dto.UserDTO(m.userName, m.age) From Member m";
        List<UserDTO> resultList = em.createQuery(jpql, UserDTO.class)
                .getResultList();

    }

    private static void pagingLogic(EntityManager em) {
        String jpql = "select m from Member m where m.team.id=:team_id";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("team_id", 1L)
                .setFirstResult(1)
                .setMaxResults(20)
                .getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    private static void innerJoinTest(EntityManager em) {

        // inner join
        //        String jpql = "select m from Member  m inner join m.team t";
        //        String jpql = "select t From Member  m join m.team t";
        //        String jpql = "select t.members.username  From Team  t";  //사용 금지!!

        // outer join
        //        String jpql = "select m From Member m left join fetch m.team t where m.age >= 30";
        //  fetch join
        //        String jpql = "select m from  Member m join fetch m.team";
        //        String jpql = "select t From Team t join fetch t.members where t.name ='teamA' ";
        // 집합 함수
        //        String jpql = "select distinct m From Member m";
        //        String jpql = "select  count (distinct m.age) from Member m";
        // 서브 쿼리
        //        String jpql = "select m from  Member  m where m.age >(select avg (m2.age) from Member m2 )";    //avg
//        String jpql = "select  m from  Member  m where exists (select t from m.team t where t.name = 'teamA')";     //EXISTS
        String jpql = "select ";

        //  Result
        List<Member> resultList1 = em.createQuery(jpql, Member.class)
                .getResultList();
        for (Member member : resultList1) {
            System.out.println("member = " + member);
        }
        //        List<Team> resultList2 = em.createQuery(jpql, Team.class)
        //                .getResultList();
        //        for (Team team : resultList2) {
        //            System.out.println("team = " + team);
        //        }


    }
}

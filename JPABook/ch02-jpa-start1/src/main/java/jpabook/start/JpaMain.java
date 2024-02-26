package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {
/*
    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        //공장 만들기, 비용이 아주 많이 듦.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager(); //엔티티 매니저 생성
        em.setFlushMode(FlushModeType.AUTO);
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {

        String id1 = "id1";
        String id2 = "id2";
        //엔티티를 생성한 상태(비영속)
        Member memberA = new Member(id1, "지한", 20);
        Member memberB = new Member(id2, "영한", 25);

        *//**
     * 등록
     * Entity를 영속(1차 캐시에 저장) - DB에 저장되진 않았음.
     *//*
        em.persist(memberA);
        em.persist(memberB);

        *//**
     * 한 건 조회
     * 1차 캐시에서 Entity를 조회, 없으면 DB에서 조회
     *//*
        Member findMemberA      = em.find(Member.class, id1);
        Member findMemberAClone = em.find(Member.class, id1);
        System.out.println("findMemberA = " + findMemberA);
        //영속 엔티티의 동일성 보장
        System.out.println(findMemberA == findMemberAClone);

        //영속성 컨테스트 초기화
        em.detach(findMemberA);
        //        em.clear();

        //병합
//        em.merge(findMemberA);

        //수정
        findMemberA.setUsername("hi");
        findMemberA.setAge(10);

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members = " + members.toString());
        System.out.println("members.size=" + members.size());

        //삭제 - 삭제 대상 Entity를 먼저 조회해야 함
        em.remove(memberA);
        em.remove(memberB);

    }*/
}

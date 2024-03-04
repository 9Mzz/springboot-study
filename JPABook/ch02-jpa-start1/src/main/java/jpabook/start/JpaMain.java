package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction    tx  = em.getTransaction(); //트랜잭션 기능 획득
        try {
            //트랜잭션 시작 -> EntityManager 는 데이터 변경 시 트랜잭션을 시작해야 한다.
            tx.begin();
            //비즈니스 로직
            boardLogic(em);
            //            logic(em);
            //            testDetached(em);
            //트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //트랜잭션 롤백
            tx.rollback();
        } finally {
            //엔티티 매니저 종료
            em.close();
        }
        //엔티티 매니저 팩토리 종료
        emf.close();
    }

    private static void boardLogic(EntityManager em) {
        Board board = new Board();
        em.persist(board);

        System.out.println("board.getId() = " + board.getId());
    }

    private static void testDetached(EntityManager em) {
        Member member = new Member();
        member.setId("memberA");
        member.setUsername("회원A");

        //회원 엔티티 영속 상태
        em.persist(member);

        Member findMember = em.find(Member.class, "memberA");
        System.out.println("findMember = " + findMember);
        em.clear();

        member.setUsername("changeNAme");

        //회원 엔티티를 영속성 컨테스트에서 분리, 준영속 상태
        //        em.detach(member);
    }

    public static void logic(EntityManager em) {

        String id     = "id1";
        Member member = new Member();   //엔티티를 생성한 상태(비영속)
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        //엔티티를 영속 + 1차 캐시에 저장됨
        em.persist(member);

        //한 건 조회
        //1차 캐시에서 조회
        Member findMember1 = em.find(Member.class, id);
        Member findMember2 = em.find(Member.class, id);
        System.out.println(findMember1 == findMember2);
        System.out.println("findMember1=" + findMember1.getUsername() + ", age=" + findMember1.getAge());

        //수정
        member.setAge(20);

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);

    }
}

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
            //            testSave(em);
            //            queryLogicJoin(em);
            //            updateRelation(em);
            //            deleteRelation(em);
            //            boardLogic(em);
            //            logic(em);
            //            testDetached(em);
            //테스트
            testSaveNonOwner(em);
            //            test순수한객체_양방향(em);
            //                        testORM_양방향(em);
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


    private static void testSave(EntityManager em) {

        //팀1 저장
        Team team1 = new Team("일반 회원");
        Team team2 = new Team("관리자");
        em.persist(team1);
        em.persist(team2);

        //회원1 저장
        Member member1 = new Member("회원1", 30);
        member1.setTeam(team1);
        em.persist(member1);

        //회원2 저장
        Member member2 = new Member("회원2", 52);
        member2.setTeam(team1);
        em.persist(member2);

        //객체 그래프 탐색
        Member findMember = em.find(Member.class, 1L);
        Team   findTeam   = findMember.getTeam();
        System.out.println("findMember = " + findMember);
        System.out.println("findTeam = " + findTeam);

    }

    private static void queryLogicJoin(EntityManager em) {
        String       jpql       = "select m from Member m join m.team t where t.name = :teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class).setParameter("teamName", "일반 회원").getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    private static void updateRelation(EntityManager em) {
        Team team2 = new Team("A등급 회원");
        em.persist(team2);

        Member member = em.find(Member.class, 1L);
        member.setTeam(team2);
    }

    private static void deleteRelation(EntityManager em) {
        Member findMember1 = em.find(Member.class, 1L);
        Member findMember2 = em.find(Member.class, 2L);
        findMember1.setTeam(null);
        findMember2.setTeam(null);

        String jpql = "delete from Team t where t.id= :team_id";
        em.createQuery(jpql).setParameter("team_id", 1L);

        System.out.println("JpaMain.deleteRelation");
    }

    private static void boardLogic(EntityManager em) {
        Board board = new Board();
        em.persist(board);
        System.out.println("board.getId() = " + board.getId());
    }

    private static void testDetached(EntityManager em) {
        Member member = new Member();
        member.setId(1L);
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

        Long   id     = 1L;
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
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(member);
    }

    private static void testSaveNonOwner(EntityManager em) {
        Member member1 = new Member("회원1", 53);
        em.persist(member1);
        Member member2 = new Member("회원2", 33);
        em.persist(member1);

        Team team = new Team("일반 회원");
        em.persist(team);
        team.getMembers().add(member1);
        team.getMembers().add(member2);
    }

    private static void test순수한객체_양방향(EntityManager em) {
        Team   team    = new Team("일반 회원");
        Member member1 = new Member("회원1", 50);
        Member member2 = new Member("회원2", 34);
        member1.setTeam(team);
        team.getMembers().add(member1);
        member2.setTeam(team);
        team.getMembers().add(member2);

        List<Member> members = team.getMembers();
        System.out.println("members.size() = " + members.size());

    }

    private static void testORM_양방향(EntityManager em) {
        //팀1 저장
        Team team = new Team("일반 회원");
        em.persist(team);

        //회원 저장
        Member member1 = new Member("회원1", 40);
        //양방향 연관관계 설정
        // member1 -> team
        member1.setTeam(team);
        // team -> member1
        //        team.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("회원2", 52);
        // member2 -> team
        member2.setTeam(team);
        // team -> member2
        //        team.getMembers().add(member2);
        em.persist(member2);

    }

}

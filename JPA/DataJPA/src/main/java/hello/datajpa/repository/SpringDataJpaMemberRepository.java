package hello.datajpa.repository;

import hello.datajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameLike(final String name);

    List<Member> findByAgeLessThanEqual(final Integer age);

    // 쿼리 메서드
    List<Member> findByNameLikeAndAgeLessThan(final String name, Integer age);

    // 쿼리 직접 실행
    @Query("select m from Member m where m.name like :memberName and " +
            "m.age <= :age")
    List<Member> findMembers(@Param("memberName") String name, @Param("age") Integer age);

}

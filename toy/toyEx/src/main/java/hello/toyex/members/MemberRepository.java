package hello.toyex.members;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Members save(Members members);

    Members findById(Long id);

    Optional<Members> findByLoginId(String loginId);

    List<Members> findAll();


}

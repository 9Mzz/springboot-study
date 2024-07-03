package hello.springtx.propagation;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LogRepository {

    private final EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(LogEntity logMessage) {
        log.info("LogEntity 저장");
        em.persist(logMessage);

        if (logMessage.getMessage()
                .contains("로그예외")) {
            log.info("로그 저장 시 예외 발생");
            throw new RuntimeException();
        }
    }


    public Optional<LogEntity> find(String message) {
        String jpql = "select l from LogEntity l where l.message = :message";
        return em.createQuery(jpql, LogEntity.class)
                .setParameter("message", message)
                .getResultList()
                .stream()
                .findAny();
    }
}

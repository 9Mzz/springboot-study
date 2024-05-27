package hello.datajpatest.repository.team;

import hello.datajpatest.domain.Team;
import hello.datajpatest.repository.member.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTeamRepository extends JpaRepository<Team, Long> {
}

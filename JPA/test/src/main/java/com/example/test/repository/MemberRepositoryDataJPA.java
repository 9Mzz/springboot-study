package com.example.test.repository;

import com.example.test.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositoryDataJPA extends JpaRepository<Member, Long> {
}

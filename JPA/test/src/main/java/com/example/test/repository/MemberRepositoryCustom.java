package com.example.test.repository;

import com.example.test.domain.Member;
import org.hibernate.metamodel.mapping.MappingModelExpressible;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepositoryCustom extends JpaRepository<Member, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + :ageParam")
    int bulkAgePlus(@Param("ageParam") int ageParam);

}

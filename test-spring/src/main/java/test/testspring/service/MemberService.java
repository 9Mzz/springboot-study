package test.testspring.service;

import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

public class MemberService {

    private final MemberRepository repository = new MemoryMemberRepository();


}

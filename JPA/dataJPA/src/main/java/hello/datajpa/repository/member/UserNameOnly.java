package hello.datajpa.repository.member;


import org.springframework.beans.factory.annotation.Value;

public interface UserNameOnly {

    @Value("#{target.userName + ' ' + target.age}")
    String getUserName();
}

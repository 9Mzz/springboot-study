package hello.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    String getUserName();

    @Value("#{target.userName + ' ' + target.age +' '+target.createBy}")
    String getValueUserName();

}

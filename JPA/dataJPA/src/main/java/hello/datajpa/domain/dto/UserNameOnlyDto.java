package hello.datajpa.domain.dto;

public class UserNameOnlyDto {

    private final String userName;

    public UserNameOnlyDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

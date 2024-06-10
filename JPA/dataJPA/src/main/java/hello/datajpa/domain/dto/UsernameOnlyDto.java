package hello.datajpa.domain.dto;

public class UsernameOnlyDto {

    private final String userName;

    public UsernameOnlyDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

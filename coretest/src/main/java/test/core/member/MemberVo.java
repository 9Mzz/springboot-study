package test.core.member;

public class MemberVo {

    //필드
    private Long   id;
    private String name;
    private Grade  grade;

    //생성자
    public MemberVo(Long id, String name, Grade grade) {
        this.id    = id;
        this.name  = name;
        this.grade = grade;
    }

    //메소드 gs
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    //


    @Override
    public String toString() {
        return "MemberVo{" + "id=" + id + ", name='" + name + '\'' + ", grade=" + grade + '}';
    }
}

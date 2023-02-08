package test.coretest.member;

public class MemberVo {

    private Long   memberId;
    private String memberName;
    private Grade  grade;
    //


    public MemberVo() {
    }

    public MemberVo(Long memberId, String memberName, Grade grade) {
        this.memberId   = memberId;
        this.memberName = memberName;
        this.grade      = grade;
    }
    //

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
        return "MemberVo{" + "memberId=" + memberId + ", memberName='" + memberName + '\'' + ", grade=" + grade + '}';
    }
}

CREATE TABLE MEMBER
(
    ID   VARCHAR(255) NOT NULL, --아이디(기본 키)
    NAME VARCHAR(255),          --이름
    AGE  INTEGER      NOT NULL, --나이
    PRIMARY KEY (ID)
)

drop table board;
drop table member;
drop table team;

SELECT M.MEMBER_ID, M.NAME, M.TEAM_ID, T.NAME AS TEAM_NAME
    FROM MEMBER M
JOIN TEAM T ON M.TEAM_ID = T.TEAM_ID;
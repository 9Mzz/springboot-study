-----------------------------------------------
drop table member if exists;
create table member
(
    member_id varchar(10),
    money     integer not null default 0,
    primary key (member_id)
);

-- 오토 커밋 모드
set
autocommit true;

-- 수동 커밋 모드
set
autocommit false;

-- 데이터 초기화
set
autocommit true;
delete
from member;
insert into member(member_id, money)
values ('memberA', 10000);
insert into member(member_id, money)
values ('memberB', 10000);

-- 롤백 & 커밋
rollback;
commit;

--h2 기본 위치
-- C:\javaStudy\h2\bin;

------------------------------------------ DB 락 - 변경

set
autocommit true;
delete
from member;
insert into member(member_id, money)
values ('memberA', 10000);

-- Set 1 - 세션 1
Set
autocommit false;
update member
set money=500
where member_id = 'memberA';

-- Set 2 - 세션 2
SET
LOCK_TIMEOUT 60000;
set
autocommit false;
update member
set money=1000
where member_id = 'memberA';

-- Set 3 - 세션 1
commit;

------------------------------------------ DB 락 - 조회
set
autocommit true;
delete
from member;
insert into member(member_id, money)
values ('memberA', 10000);

-- Set 1 - 세션 1
set
autocommit false;
select *
from member
where member_id = 'memberA' for update;

-- Set 2 - 세션 2
set
autocommit false;
update member
set money=500
where member_id = 'memberA';

-- Set 3 - 세션 1
commit;

-- Set 4 - 세션 2
commit;
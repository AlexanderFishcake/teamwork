--============================================
--관리자계정
--============================================
--web계정 생성
create user web
identified by web
default tablespace users;

--권한부여
grant connect, resource to web;

--============================================
--WEB계정
--============================================
show user;

--회원테이블 생성
create table member(
    member_id varchar2(15),
    password varchar2(300) not null,
    member_name varchar2(50) not null,
    member_role char(1) default 'U' not null,
    gender char(1),
    birthday date,
    email varchar2(100),
    phone char(11) not null,
    address varchar2(200),
    hobby varchar2(200),                                                    --운동, 등산, 독서, 게임, 여행
    enroll_date date default sysdate,
    
    constraint pk_member_id primary key(member_id),
    constraint ck_gender check(gender in ('M', 'F')),               --M 남자, F 여자
    constraint ck_member_role check(member_role in ('U','A')) --U 일반사용자, A 관리자
);

--회원 추가
insert into member
values (
    'honggd', '1234', '홍길동', 'U', 'M', to_date('20000909','yyyymmdd'),
    'honggd@naver.com', '01012341234', '서울시 강남구', '운동,등산,독서', default
);
insert into member
values (
    'qwerty', '1234', '쿠어티', 'U', 'F', to_date('19900215','yyyymmdd'),
    'qwerty@naver.com', '01012341234', '서울시 송파구', '운동,등산', default
);
insert into member
values (
    'admin', '1234', '관리자', 'A', 'M', to_date('19801010','yyyymmdd'),
    'admin@naver.com', '01056785678', '서울시 관악구', '게임,독서', default
);

delete from member where member_id like 'newId%';
update member set password='1234', member_name='newOne', member_role=''
where member_id='asdfasdf';

--update member set password='1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==';
update member set password='1234' where member_id='';

commit;
select * from member;
select * from member where gender ='M';

--페이징
--1. rownum 행 추가시 자동으로 부여되는 no
select *
from(
        select rownum rnum, M.*
        from(
                select M.*
                from member M
                order by enroll_date desc    
                ) M
        )M
where rnum between 11 and 20;
--2. window함수 row_number
--cPage = 1  : 1~10
--cPage = 2 : 11~20
--cPage = 3 : 21~30
--...
--cPage = 10 : 91~100
--cPage = 11 : 101~105
select *
from(
        select row_number() over(order by enroll_date desc) rnum, M.*
        from member M
        ) M
where rnum between 11 and 20;

select * from(select row_number() over(order by enroll_date desc) rnum, M.* from member M) M where rnum between 11 and 20
;

select count(*) from member;
 
select *
from(
        select row_number() over(order by enroll_date desc) rnum, M.*
        from (
                select * from member where member_id like '%a%'
                ) M
        ) M
where rnum between 11 and 20;

select * from( select row_number() over(order by enroll_date desc) rnum, M.* from ( select * from member where member_id like '%a%' ) M ) M where rnum between 11 and 20;
select * from member where member_id like '%a%';
select * from member where member_id like '%a%';

--댓글 테이블 작성
create table board_comment(
    no number,
    comment_level number default 1,
    writer varchar2(15),
    content varchar2(2000),
    board_no number,
    comment_ref number,
    reg_date date default sysdate,
    constraint pk_board_comment_no primary key(no),
    constraint fk_board_comment_writer foreign key(writer) references member(member_id) on delete set null,
    constraint fk_board_comment_board_no foreign key(board_no) references board(no) on delete cascade,
    constraint fk_board_comment_ref foreign key(comment_ref) references board_comment(no) on delete cascade
);

comment on column board_comment.no is '게시판댓글번호';
comment on column board_comment.comment_level is '게시판댓글 레벨';
comment on column board_comment.writer is '게시판댓글 작성자';
comment on column board_comment.content is '게시판댓글';
comment on column board_comment.board_no is '참조원글번호';
comment on column board_comment.comment_ref is '게시판댓글 참조번호';
comment on column board_comment.reg_date is '게시판댓글 작성일';

create sequence seq_board_comment_no;

--샘플 테스트
--77번 글

--댓글 추가
insert into board_comment(no, comment_level, writer, content, board_no, comment_ref)
values(seq_board_comment_no.nextval, 1, 'puma', '잘 읽었습니다.', 77, null);

insert into board_comment(no, comment_level, writer, content, board_no, comment_ref)
values(seq_board_comment_no.nextval, 1, 'shadow', '잘 읽었습니다2.', 77, null);

insert into board_comment(no, comment_level, writer, content, board_no, comment_ref)
values(seq_board_comment_no.nextval, 1, 'bandit', '잘 읽었습니다3.', 77, null);

--대댓글 추가
insert into board_comment(no, comment_level, writer, content, board_no, comment_ref)
values(seq_board_comment_no.nextval, 2, 'shqkel', '잘 읽어줘서 고마워요.', 77, 6);

insert into board_comment(no, comment_level, writer, content, board_no, comment_ref)
values(seq_board_comment_no.nextval, 3, 'bandit', '사실 잘 안읽음', 77, 6);

select * from board_comment;
delete from board_comment where no=16;

--계층형 쿼리
--기준컬럼을 이용해 행간의 수직구조를 표현한 쿼리
--댓글, 조직도, 메뉴 등의 트리구조를 표현할 수 있다.
--start with 최상위 행을 지정
--connect by 부모행-자식행의 관계작성. 부모행의 컬럼 앞에 prior키워드를 작성.
--level 가상컬럼을 사용할 수 있다.

select lpad(' ', (level - 1)*5) || content, 
        level,
        bc.*
from board_comment bc
start with comment_level = 1
connect by prior no = comment_ref
order siblings by reg_date desc;

select bc.* from board_comment bc where board_no = ? start with comment_level = 1 connect by prior no = comment_ref order siblings by reg_date
;
delete from board_comment where no='18';

select * from board_comment;

CREATE TABLE TEST(
    SEQ NUMBER,
    WRITER VARCHAR2(30),
    TITLE VARCHAR2(100),
    CONTENT VARCHAR2(200),
    REGDATE DATE
);
select * from test;
insert into test values(1, '성자1', '제목1', '내용1', '2021-04-23');
insert into test values(2, '성자2', '제목2', '내용2', '2021-04-24');



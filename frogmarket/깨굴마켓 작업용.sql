---여기부터 임의 sql---
select * from member;
insert into member
values(
    'cms','1a2s3d','U','01012341234','blucency@gmail.com',default,1,'치미시'
);

select * from tabs;
select * from member;
select * from p_board;
select * from r_board;
------------------------member에 입력 3개
insert into member
values(
    'oneman','132435','U','01099998888','kjk@naver.com',default,1,'김종국'
);
insert into member
values(
    'blackcow','1a2s3d','U','01011442266','mow@gmail.com',default,0,'흑우'
);
insert into member
values(
    'logcat','abcdef','U','01013134646','cutecat@naver.com',default,3,'로그캣'
);
------------------------p_board에 입력 3개
select * from p_board;

insert into p_board
values(
   seq_p_board_no.nextval ,'oneman','동전지갑','new', 5000,'그냥 동전 넣을 수 있는 주머니입니다.',default,'대전'
);
insert into p_board
values(
   seq_p_board_no.nextval ,'blackcow','마우스','new', 7000,'작동은 됩니다. 좀 더러워요..',default,'서울'
);
insert into p_board
values(
   seq_p_board_no.nextval ,'logcat','가위','new', 3000,'가위가 몇개 남아서 팝니다.',default,'인천'
);
------------------------r_board에 입력 3개
select * from r_board;

insert into r_board
values(
    seq_r_board_no.nextval, 'oneman', 'logcat', default, '판 가위가 날이 상해있습니다.'
);
insert into r_board
values(
    seq_r_board_no.nextval, 'blackcow', 'logcat', default, '못쓰는 가위를 팔았어요'
);
insert into r_board
values(
    seq_r_board_no.nextval, 'oneman', 'blackcow', default, '마우스 선이 끊겨있는걸 팔고있습니다'
);


--sql구문 작성중
insert into p_board values( seq_p_board_no.nextval ,'oneman','동전지갑','new', 5000,'그냥 동전 넣을 수 있는 주머니입니다.',default,'대전')
;
select * from p_board;
select * from p_board where board_no=1;
select * from member;
select * from member where member_id='oneman';
select * from(
            select row_number() over(order by b.board_no desc) rnum,  b.*, a.no attach_no, a.original_filename, a.renamed_filename
            from p_board b left join p_attach a
                                        on b.board_no = a.board_no
            ) B 
where rnum between 1 and 3
;
select * from(select row_number() over(order by b.board_no desc) rnum,  b.*, a.no attach_no, a.original_filename, a.renamed_filename from p_board b left join p_attach a on b.board_no = a.board_no) B where rnum between 1 and 3
;
select * from p_attach;
select count(*) cnt from p_board;
select * from p_board;
select * from p_attach where board_no=47;
select * from(
                select row_number() over(order by b.board_no desc) rnum,  b.*, a.no attach_no, a.original_filename, a.renamed_filename 
                from p_board b left join p_attach a 
                                        on b.board_no = a.board_no
                ) B 
where rnum between 1 and 9
;
select * from p_board;
select * from p_attach;

select B.board_no, min(no), min(a.renamed_filename) filename
from p_board B
        left join p_attach A
                on B.board_no = A.board_no
group by B.board_no
;
--selectList ver2.0
select * from(
                select row_number() over(order by b.board_no desc) rnum,  b.*, a.filename 
                from p_board b left join (select B.board_no, min(no), min(a.renamed_filename) filename
                                                    from p_board B
                                                            left join p_attach A
                                                                    on B.board_no = A.board_no
                                                    group by B.board_no
                                                    ) a 
                                        on b.board_no = a.board_no
                ) B 
where rnum between 1 and 9
;
select * from(select row_number() over(order by b.board_no desc) rnum,  b.*, a.filename from p_board b left join (select B.board_no, min(no), min(a.renamed_filename) filename from p_board B left join p_attach A on B.board_no = A.board_no group by B.board_no) a on b.board_no = a.board_no) B where rnum between 1 and 9
;
select * from p_board;
select * from (
                select row_number() over(order by board_no desc) rnum, B.* 
                from p_board B 
                where title like '%팝니다%' and title like '%시계%'
                ) B
where rnum between 1 and 9
;
--title like '%팝니다%' and title like '%시계%'
select * from (select row_number() over(order by board_no desc) rnum, B.* from p_board B where # ) B where rnum between 1 and 9
;
select count(*) from p_board where title like '%팝니다%'
;
--searchProduct에 첨부파일 컬럼 추가
select * from (
                select row_number() over(order by B.board_no desc) rnum, B.*,filename
                from p_board B left join (select B.board_no, min(no), min(a.renamed_filename) filename
                                                    from p_board B
                                                            left join p_attach A
                                                                    on B.board_no = A.board_no
                                                    group by B.board_no
                                                    ) a 
                                        on b.board_no = a.board_no 
                where title like '%test%' --and title like '%시계%'
                ) B
where rnum between 1 and 9;
select * from ( select row_number() over(order by B.board_no desc) rnum, B.*,filename from p_board B left join (select B.board_no, min(no), min(a.renamed_filename) filename from p_board B left join p_attach A on B.board_no = A.board_no group by B.board_no) a on b.board_no = a.board_no where # ) B where rnum between ? and ?
;

select * from p_board;
update p_board
set title='테스트14'
    ,status='soldout'
    ,sell_price=4321
    ,description='재수정한 내용'
    ,area_info='대전'
where board_no=56
;
select * from p_board;
update p_board set title='테스트14' ,status='soldout' ,sell_price=4321 ,description='재수정한 내용' ,area_info='대전' where board_no=56
;
select * from reply;
select * from p_attach;
--delete from p_board where board_no=57;

--on delete cascade 조건울 추가하기 위해서는 기존 조건을 삭제하고 추가할 내용 넣어서 다시 만들어야 한다.
SELECT * FROM ALL_CONSTRAINTS
WHERE TABLE_NAME = 'P_ATTACH';
--삭제부터 하고
ALTER TABLE p_attach DROP constraint FK_P_BOARD_TO_P_ATTACH_1;
--새로운 constraint에 cascade 붙여서 추가
ALTER TABLE p_attach 
ADD CONSTRAINT FK_P_BOARD_TO_P_ATTACH_1 
FOREIGN KEY (board_no)
REFERENCES p_board(board_no) 
ON DELETE CASCADE;

SELECT * FROM ALL_CONSTRAINTS 
WHERE TABLE_NAME = 'R_ATTACH';
--삭제부터 하고
ALTER TABLE r_attach DROP constraint FK_R_BOARD_TO_R_ATTACH_1;
--새로운 constraint에 cascade 붙여서 추가
ALTER TABLE r_attach 
ADD CONSTRAINT FK_R_BOARD_TO_R_ATTACH_1 
FOREIGN KEY (report_no)
REFERENCES r_board(report_no) 
ON DELETE CASCADE;

select * from notifications;

SELECT * FROM ALL_CONSTRAINTS 
WHERE TABLE_NAME = 'NOTIFICATIONS';
create sequence seq_notice_no;

CREATE TABLE notice (
    no  number primary key,
	board_no	number		NOT NULL,
	sender_id	varchar2(15)		NOT NULL,
	receiver_id	varchar2(15)		NOT NULL,
	title	varchar2(100)		NOT NULL,
	content	VARCHAR2(200)		NOT NULL,
    
    constraint fk_board_no foreign key (board_no) references p_board(board_no),
    --constraint fk_sender_id foreign key (sender_id) references member(member_id),
    constraint fk_receiver_id foreign key (receiver_id) references member(member_id)
   
);
alter table notice drop constraint fk_sender_id;
alter table notice rename column sender_nick to sender_id;
alter table notice modify content varchar2(200);

select * from reply;
select * from member;
select * from p_board;
select * from notice;

--insert into notice values(seq_notice_no, ?, ?, ?, ?, ?);
insert into notice values(seq_notice_no.nextval, 54, 'oneman', 'oneman', '테스트12', '댓글테스트3');

select no,board_no,sender_id, nick_id, receiver_id,title,content
from (
        select * from notice N left join member M on n.sender_id = M.member_id
      ) N
where receiver_id='oneman'
order by no desc
;
select no,board_no,sender_id, nick_id, receiver_id,title,content from (select * from notice N left join member M on n.sender_id = M.member_id) N where receiver_id='oneman' order by no desc
;
select * from reply;

SELECT *
FROM USER_SEQUENCES;

SELECT * FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'MEMBER';

alter TABLE R_BOARD disable CONSTRAINTS pk_R_BOARD cascade;
alter TABLE NOTICE enable CONSTRAINTS pk_R_BOARD;

select * from member;

insert into member values ('admin8', '1234', 'A', '01012341234', 'abc@gmail.com', sysdate, '10', '관리자8', null);
select * from r_board;

insert into member values ('admin8', '1234', 'A', '01012341234', 'abc@gmail.com', sysdate, '10', '관리자8', null);
insert into member values ('newuser', '1234', 'U', '01012341234', 'aaa@gmail.com', sysdate, '1', '새유저1', null);

select * from all_objects where object_type = 'TABLE';
select * from member;
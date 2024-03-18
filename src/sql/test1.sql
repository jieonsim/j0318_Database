show tables;

create table test (
	idx INT NOT NULL auto_increment PRIMARY KEY, /*고유번호 (추가나 수정했다면 여기에 반드시 넣어놓을 것!!)*/
	name varchar(20) not null,			/*성명*/
	age int default 20,					/*나이*/
	gender varchar(2) default '남자',	/*성별*/
	job varchar(20) default '기타',		/*직업(추가나 수정했다면 여기에 반드시 넣어놓을 것!!)*/
	address varchar(50)					/*주소*/
);
drop table test; /*구조까지 지우기*/
delete from test; /*내용만 지우기*/
desc test; /*테이블 구조보기*/

insert into test values (default,'홍길동', default , default, default, '서울');
insert into test values (default,'김말숙', 34 , '여자', default,'청주');
insert into test values (default,'이기자', 29 , '남자', default,'부산');
insert into test values (default,'김연아', default , '여자', default, '제주');
insert into test values (default,'손흥민', 33 , default, default, '서울');
insert into test values (default, '소나무', 55, default, default, '제주');
insert into test values (default, '대나무', 11 , '여자','학생', '제주'); 
insert into test values (default, '감나무', 22 , '남자', '회사원', '서울'); /*직접 auto_increment 값이 없는 거면 직접 지정 가능 */

-- 리스트 보기
select * from test;

delete from test where name = '손흥민';

-- alt + s : 커서 있는 곳 한 줄 실행 / alt + x : 범위 준 곳만 실행
-- 레코드 수정하기 : update 테이블명 set 필드명 = '수정내용' where '조건(필드명=값)';
update test set age = 25 where name = '홍길동';
-- dml select delete update / ddm create / dcl 권한 주기

-- 남자들의 나이를 1살씩 모두 더해주시오.
update test set age = age + 1; -- 전체 모두 플러스
update test set age = age - 1;
update test set age = age + 1 where gender = '남자';

-- 서울에 사는 사람들만 보여주세요.
select * from test where address = '서울';
select * from test where address = '서울' or address = '부산';
-- db에서의 or, and는 |,&가 아닌 or, and다

-- 나이가 30살 미만인 사람인 회원을 보여주시오
select * from test where age < 30;

-- 나이가 30살 미만인 여자 회원을 보여주시오
select * from test where age < 30 and gender = '여자';

-- 청주에 사는 회원 확인
select * from test where address = '청주';

-- 청주에 사는 회원 삭제
delete from test where address = '청주';

-- 주소:청주 / 성별: 남자 / 나이: 19 / 이름: 강감찬 인 회원을 등록하세요
insert into test values ('강감찬', 19, '남자', '청주');

-- 서울에 사는 여자 회원의 나이를 2살씩 빼주시오
update test set age = age - 2 where gender = '여자' and address = '서울';

-- test 테이블의 구조보기
desc test;

-- 필드 구조 변경 : alter table 테이블명 add column   ...

-- test 테이블에 job 필드(직업명은 5글자 이내, 기본값 : 기타)-컬럼(을)를 추가(add column)하시오
alter table test add column job varchar(6) default '기타';

-- 필드 삭제 : alter table 테이블명 drop column 필드명
-- test 테이블에 job 필드 삭제하기
alter table test drop column job;

-- 필드 길이 수정 : alter table 테이블명 modify column 필드명...
-- test 테이블의 job 필드의 길이를 20자로 수정하시오
alter table test modify column job varchar(20);

-- change COLUMN 필드명 변경
-- test 테이블의 name 필드명을 irum 필드로 변경하시오.(column의 이름 변경)
ALTER TABLE test change COLUMN name irum VARCHAR(20);
ALTER TABLE test change COLUMN irum name VARCHAR(20);

-- 기본키 (구분이 될 수 있는 중복을 배제한 필드) 추가 : ALTER TABLE 테이블명 ADD COLUMN 필드명 타입명 NOT NULL auto_increment PRIMARY KEY;
-- test 테이블에 고유번호(idx) 필드를 추가하시오
ALTER TABLE test ADD COLUMN idx INT NOT NULL auto_increment PRIMARY KEY;
-- Mysql에서 auto_increment 지정한 값은 무조건 primary key를 줘야함
-- unique key : primary key가 이미 있을 때 추가로 고유한 키(중복되지 않은) 값을 주고싶을 때 사용
-- primary key : 필드명 뿐 아니라 타입도 달라야함,, 두개의 필드를 같이 묶어서 primary key를 줄 수 있음
-- auto_increment의 한번 사용한 수는 재사용하지 않음. 내용을 삭제하더라도 삭제된 값의 수는 재사용 X

-- idx = 5번 삭제하시오
DELETE FROM test WHERE idx = 5;

-- idx = 7번 삭제하시오
DELETE FROM test WHERE idx = 7;

-- 고유번호(idx)값을 5번부터 시작하도록 설정하시오
ALTER TABLE test auto_increment = 5;

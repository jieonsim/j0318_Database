-- 아래 내용을 번호순으로 한단계씩 작업처리하시오.
-- 작업시는 문제번호와 문제를 붙여넣고 처리할 명령어를 기술하고, 처리결과를 캡처해서 보여줄 수 있도록 처리하시오.(시간이 부족할시, 작업을 모두 마치고 나중에 모두 실행한 결과를 화면캡처해서 올려도 됨) - 작업이 끝날때까지 명령프롬프트창을 닫지마세요~~~(결과 화면 캡처를 위해서~~)

-- 1. MySql접속하기 : 시작메뉴에서 'MySQL 5.7 Command Line Client - Unicode'를 이용해서 root 계정으로 접속한다.



-- (2~12). root 계정에서의 작업이다.
-- 2. 저장된 데이터베이스의 목록을 확인한다.



-- 3. mysql 데이터베이스로 이동(활성화)한다.



-- 4. 지금부터는 mysql 데이터베이스안에서의 작업이다.
-- 모든테이블의 목록보기



-- 5. user테이블의 host필드와 user필드의 모든 내용 보기?



-- 6. 'sample 데이터베이스를 만드시오.(기본값이 utf8형식이나, 별도로 utf8 형식으로 생성하고자 한다면, 기술된 명령어뒤에 '~  character set utf8 collate utf8_general_ci;'를 추가해주면 된다.)



-- 7. 데이터베이스 만들어졌는지 확인하시오.



-- 8. 'atom2' 계정을 만드시오. 접속권한(위치)는 'localhost'로 설정하고 비밀번호는 '1234'로 설정한다.



-- 9. 'atom2' 계정의 권한을 부여한다. atom2계정사용자는 'sample' 데이터베이스만 접근할 수 있고, 'sample'데이터베이스 안에서의 모든 작업을 허용할 수 있도록 권한을 부여한다.




-- 10. 9번에서 'atom2' 계정에 부여한 권한을 모두 거둬들인다.




-- 11. 'atom2' 계정의 권한을 문제 9번에서와 똑같이 다시한번 설정시켜준다.



-- 12. 'MySQL 5.7 Command Line Client - Unicode'에서 탈출(작업종료)하기



-- (13~17) cmd창에서 'C:\Program Files\MySQL\MySQL Server 5.7\bin'폴더로 이동한후, 'atom2'계정으로 접속하여 작업하시오.
-- 13. atom2 사용자의 현재 부여된 환경(권한) 보기



-- 14. 데이터베이스 목록검색하기



-- 15. 'sample'데이터베이스에 접속하기



-- 16. 'sample'데이터베이스의 모든 테이블 목록보기



-- 17. 현재 접속위치에서 탈출한다.



-- 다음 이부분 문제부터는 Workbench또는 이클립스 에서 작업처리한다.
-- 이클립스에서 작업할 경우에는 'Data Source Explorer'탭에 'Database Connections'에 작업할 'sample'데이터베이스를 등록후 처리한다.
-- Workbench(이클립스)에 접속하여 'atom2'계정으로 접속할 수 있도록 환경을 설정을 추가 한후, atom2계정으로 접속한다.


-- 이후 부터는 모두 atom2 계정으로 접속하여 문제를 풀어봅니다.
-- 18. 'sample' 데이터베이스로 이동한다.(use 데이터베이스명령으로 처리할것)
use sample;


/*
 19. 아래와 같은 구조로 테이블을 설계하시오. 테이블명 : hoewon
 아이디(mid)  : 가변길이(15) , 널값불허
 비밀번호(pwd) : 가변길이(15) , 널값불허
 성명(name)   : 가변길이(20) , 널값불허
 나이(age)    : 정수형, 기본값은 20
 성별(sex)    : 고정길이문자(2), 기본값(남자)
 입사일(ipsail)  : 날짜타입, 기본값은 'now()'로 지정한다.
 주소(address) : 가변길이(50)
*/;

CREATE TABLE hoewon (
	mid		VARCHAR(15) NOT NULL,				/* 아이디 */
	pwd		VARCHAR(15) NOT NULL,				/* 비밀번호 */
	name	VARCHAR(20) NOT NULL,				/* 이름 */
	age		INT DEFAULT 20,						/* 나이 */
	sex		CHAR(2) DEFAULT '남자',				/* 성별 */
	ipsail	datetime NOT NULL DEFAULT now(), 	/* 입사일 */
	address	VARCHAR(50),							/* 주소 */
	soge 	VARCHAR(100),						/* 소재지 추가 */
	point	INT DEFAULT 1000,					/* 포인트 추가 */
	idx 	INT NOT NULL auto_increment PRIMARY KEY		/* idx 추가 */
);




-- 20. hoewon테이블 구조보기
DESC hoewon;


-- 21. hoewon테이블을 삭제한다.(단, 실제로 삭제하지말고 명령어만 기술하시오. 만약에 삭제하였으면 19번에 기술된 명령으로 다시 생성시킨후 아래 작업하시오)
DROP TABLE hoewon;


-- 22. hoewon테이블의 이름을 'sinsang' 테이블로 변경한다.
ALTER TABLE hoewon rename sinsang;


-- 23. 모든 테이블 목록보기
show tables;


-- 24. sinsang테이블에 content 필드를 text 타입으로 추가하시오.
ALTER TABLE sinsang ADD COLUMN content text;


-- 25. sinsang테이블 구조보기
DESC sinsang;


-- 26. sinsang테이블에 content 필드를 soge필드로 변경하시오. 타입은 똑같은 text타입이다.
ALTER TABLE sinsang change COLUMN content soge text;


-- 27. sinsang테이블의 soge필드의 타입을 가변길이 100Byte로 변경하시오.(null값은 허용한다)
ALTER TABLE sinsang modify COLUMN soge VARCHAR(100);


-- 28. sinsang테이블에 content 필드를 삭제하시오.(실제로 삭제하지는 말고 명령어만 사용하시오. 만약 삭제하였다면 새롭게 content필드를 가변길이 100Byte로 추가하시오)
ALTER TABLE sinsang DROP COLUMN soge;


-- 29. sinsang테이블 구조보기
DESC sinsang;


-- 30. sinsang테이블의 sex필드명을 gender필드명으로 변경하시오.(고정길이문자(2), 기본값(여자))
ALTER TABLE sinsang change COLUMN sex gender CHAR(2) DEFAULT '여자';


-- 31. sinsang테이블 구조보기
DESC sinsang;


ALTER TABLE sinsang MODIFY gender VARCHAR(3) DEFAULT '여자';
/* 32. 아래와 같은 자료를 입력시키시오.(순서는 '아이디/비밀번호/성명/나이/성별/입사일/주소') - 입력되는 내용은 복사해서 추가하시오.
'hong','1234','홍길동',25,'남자','1990-10-05','서울'
'kim','1234','김말숙',33,'여자','1997-12-3','부산'
'lee','1234','이순자',24,'여자','1985-7-25','광주'
'lee1','1234','이기자',40,'남자','1999-10-3','울산'
'park','1234','박찬호',32,'남자','1980-6-08','대전'
'bae','1234','배은숙',19,'여자','1993-11-22','마산'
'son','1234','손기정',45,'남자','1987-12-15','제주';
'kim1','1234','김영숙',36,'여자','2002-10-5','부천'
'kim2','1234','김영철',39,'남자','1988-10-05','인천'
'lee2','1234','이겨라',29,'남자','2000-10-09','서울'
'son2','1234','손오공',23,'남자','2005-12-15','서울'
*/
INSERT INTO sinsang VALUES ('hong','1234','홍길동',25,'남자','1990-10-05','서울', DEFAULT);
INSERT INTO sinsang VALUES ('kim','1234','김말숙',33,'여자','1997-12-03','부산', DEFAULT);
INSERT INTO sinsang VALUES ('lee','1234','이순자',24,'여자','1985-07-25','광주', DEFAULT);
INSERT INTO sinsang VALUES ('lee1','1234','이기자',40,'남자','1999-10-03','울산', DEFAULT);
INSERT INTO sinsang VALUES ('park','1234','박찬호',32,'남자','1980-06-08','대전', DEFAULT);
INSERT INTO sinsang VALUES ('bae','1234','배은숙',19,'여자','1993-11-22','마산', DEFAULT);
INSERT INTO sinsang VALUES ('son','1234','손기정',45,'남자','1987-12-15','제주', DEFAULT);
INSERT INTO sinsang VALUES ('kim1','1234','김영숙',36,'여자','2002-10-05','부천',DEFAULT);
INSERT INTO sinsang VALUES ('kim2','1234','김영철',39,'남자','1988-10-05','인천', DEFAULT);
INSERT INTO sinsang VALUES ('lee2','1234','이겨라',29,'남자','2000-10-09','서울',DEFAULT);
INSERT INTO sinsang VALUES ('son2','1234','손오공',23,'남자','2005-12-15','서울',DEFAULT);



-- 33. sinsang테이블의 입력된 모든자료의 내용을 출력하시오.
SELECT * FROM sinsang;


-- 34. 32번에서 입력한 모든 자료를 삭제시키는 명령어를 기술하시오.(실제로 실행시키지는 말고 명령어만 기술하시오. 만약 삭제했으면 32번을 다시 실행해서 추가되도록 하시오.)
DELETE FROM sinsang;


-- 35. sinsang테이블의 입력된 모든자료의 내용을 출력하시오.(자료가 없으면 다시 입력후 작업할것)
SELECT * FROM sinsang;



-- 36. 아이디가 'kim'인 자료를 보여주시오.
SELECT * FROM sinsang WHERE mid = 'kim';


-- 37. 성별이 남자만 보여주시오.
SELECT * FROM sinsang WHERE gender = '남자';

-- 38. 주소가 '서울'을 보여주시오
SELECT * FROM sinsang WHERE address = '서울';

-- 39. 주소가 '서울'과 '인천'을 보여주시오
SELECT * FROM sinsang WHERE address = '서울' OR address = '인천';

-- 40. '서울'에 살고있는 '남자'들을 출력하시오.
SELECT * FROM sinsang WHERE gender = '남자' AND address ='서울';


-- 41. sinsang테이블에 'point' 필드를 추가하시오(타입:int, 기본값:1000)
ALTER TABLE sinsang ADD COLUMN point INT DEFAULT 1000;


-- 42. sinsang 테이블 구조확인?
DESC sinsang;


-- 43. sinsang 테이블 내용(모든 레코드) 확인?
SELECT * FROM sinsang;


-- 44. 여자회원들에 대하여 모두 100 포인씩 추가지급한다.
UPDATE sinsang SET point = point + 100 WHERE gender = '여자';


-- 45. sinsang 테이블 내용 확인(모든 레코드)?
SELECT * FROM sinsang;



-- 46. 나이가 35살 이상인 남자회원들만 point를 50포인트씩 추가 지급한다.
UPDATE sinsang SET point = point + 50 WHERE gender = '남자' and age >= 35;


-- 47. sinsang 테이블 내용 확인(모든 레코드)?
SELECT * FROM sinsang;



-- 48. 아이디가 'kim1'회원의 주소를 '서울'로 변경처리하시오.
UPDATE sinsang SET address = '서울' WHERE mid = 'kim1';


-- 49. sinsang 테이블 '아이디'와 '나이'와 '주소' 내용만 확인?
SELECT mid, age, address FROM sinsang;


-- 50. 2000년 1월 1일 이후(포함)에 입사한 사람들에 대하여 '아이디' 와 '성별', '입사일'을 출력하되,
--     'mid'는 '아이디'로, 'gender'는 '성별'로, 'ipsail'은 '입사일'이란 필드명으로 출력되게 하시오?
SELECT mid AS 아이디, gender AS 성별, ipsail AS 입사일 FROM sinsang WHERE ipsail >= '2000-01-01';




-- 51. 다음자료를 추가 입력하시오. : 아이디:park, 비밀번호:1234, 성명:박세리, 나이:35,성별:여자,입사일과 포인트는 기본값으로, 주소: 인천
INSERT INTO sinsang VALUES ('park', '1234', '박세리', 35, '여자', DEFAULT, '인천', DEFAULT, DEFAULT)


-- 52. 30대인 '여자'의 '아이디'와 '나이'와 '성별'과 '주소'를 출력하시오.
SELECT mid, age, gender, address FROM sinsang WHERE gender = '여자';


-- **참고 :  53번문제를 먼저 풀이하기 전에 sinsang 테이블 내용을 확인하고, 53번 문제와 54번문제 풀이후에 sinsang테이블의 내용을 확인해본다.
SELECT * FROM sinsang;


-- 53. 서울과 인천지역에 살지 않는 여자회원들에 대하여 포인트를 500씩 더 지급하시오.('같다'의 연산자는 '='이고, '같지않다'의 연산자는 '!=' 입니다.)
UPDATE sinsang SET point = point + 50 WHERE gender = '여자' AND address != '서울' AND address != '인천';


-- 54. 1990년 이전에 입사한 남자회원들에 대하여는 모든 포인트를 회수하시오.
UPDATE sinsang SET point = 0 WHERE gender = '남자' and ipsail < '1990-01-01';


-- 55. 포인트가 전혀 없는 회원중에서 나이가 40이상인 회원의 '성별'을 '여자'로 변경하시오.
UPDATE sinsang SET gender = '여자' WHERE point = 0 AND age >= 40;



-- 56. '서울'지역 사용자는 비밀번호를 '0000'으로, 포인트는 300점을 추가로 지급하시오.
UPDATE sinsang SET pwd = '0000', point = point + 300 WHERE address = '서울';



-- 57. 포인트가 없고, 남자이면서 1985년 이전에 입사한 회원을 삭제처리하시오.
DELETE FROM sinsang WHERE point = 0 AND gender = '남자' AND ipsail < '1985-01-01'



-- 58. 30대 여자인 자료만 출력하시오.(2가지 방법 모두 기술하기)
SELECT * FROM sinsang WHERE age BETWEEN 30 AND 39 AND gender = '여자';
SELECT * FROM sinsang WHERE age >= 30 AND age <= 39 AND gender = '여자';


-- 59. 주소가 '서울'이거나 '부산'인 사람들의 '이름/나이/성별/주소'필드만을 출력하시오.(2가지 방법 모두 기술하기)
SELECT name, age, gender, address FROM sinsang WHERE address = '서울' or address = '부산';
SELECT name, age, gender, address FROM sinsang WHERE address in('서울', '부산');
 



-- 60. sinsang 테이블에 자동으로 숫자가 증가할 수 있는 idx필드를 추가해 주시오.
ALTER TABLE sinsang ADD COLUMN idx INT NOT NULL auto_increment PRIMARY KEY;
DESC sinsang;



-- 61. 나이 내림차순으로 출력하되, 나이가 같으면, 이름 오름차순으로 출력하고,
-- 	   만약 이름도 같다면 고유번호 오름차순으로 출력하시오.
SELECT * FROM sinsang ORDER BY age DESC, name, idx;




-- 62. sinsang 테이블에 주어진 필드순서대로 다음 레코드를 추가하시오.(성명:홍길공자 , 아이디:hkgj , 비밀번호: 1234)
INSERT INTO sinsang VALUES ('hkgj', '1234', '홍길공자', DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)



-- 63.  sinsang 테이블에서 '홍'씨성을 가진 모든 레코드를 출력하시오.
SELECT * FROM sinsang WHERE name like '홍%';



-- 64. sinsang 테이블에서 성명이 '자'로 끝나는 모든 레코드를 출력하시오.
SELECT * FROM sinsang WHERE name like '%자';



-- 65. sinsang 테이블에서 이름중에서 '공'자가 들어있는 모든 레코드를 출력하시오.
SELECT * FROM sinsang WHERE name like '%공%';



-- 66. sinsang 테이블에서 이름중에서 2번째 글자가 '기' 자를 가진 모든 레코드의 '아이디/이름/성별'필드를 출력하시오.
-- (단, 출력되는 필드명은 mid필드는 '아이디'로, name필드는 '이름', 'gender'필드는 '성별'로 출력하시오.)
SELECT mid AS '아이디', name AS '이름', gender AS '성별' FROM sinsang WHERE name LIKE '_기%';



-- 67. sinsang 테이블에서 이름중에서 뒤에서 2번째 글자가 '공'인 레코드를 검색하시오.
SELECT * FROM sinsang WHERE name like '__공%';


--68. sinsang 테이블에서 모든자료의 '아이디/성명/성별'을 처음부터 5개만 출력하시오.
SELECT mid, name, gender FROM sinsang LIMIT 5; 



--68-2. sinsang 테이블에서 모든자료의 '아이디/성명/성별'을 5번레코드부터 5개만 출력하시오.
-- (단, 출력되는 필드명은 mid필드는 '아이디'로, name필드는 '이름', 'gender'필드는 '성별'로 출력하시오.)
SELECT mid AS '아이디', name AS '이름', gender AS '성별' FROM sinsang LIMIT 4,5;



-- 69. 68번과 같이 출력하되, 첫번째로 성별 내림차순 출력, 두번째로 이름 오름차순으로 출력하시오.
SELECT mid AS '아이디', name AS '이름', gender AS '성별' FROM sinsang ORDER BY gender DESC, name LIMIT 4,5;



-- 70. sinsang 테이블에서 나이가 많은 사람순으로 3명만 출력하시오.
SELECT * FROM sinsang ORDER BY age DESC LIMIT 3;

show tables;

CREATE TABLE insarok (
	idx		INT NOT NULL auto_increment PRIMARY KEY,	/* 고유번호 */
	buser	VARCHAR(10) NOT NULL, 						/* 부서명 */
	name	VARCHAR(20) NOT NULL,						/* 부서원 이름 */
	jikwi	VARCHAR(10) NOT NULL DEFAULT '사원',			/* 직위 */
	gender	CHAR(2) NOT NULL DEFAULT '남자',				/* 성별 */
	age		INT DEFAULT 25,								/* 나이 */
	ipsail 	datetime NOT NULL DEFAULT now()	,			/* 입사일(기본값 : 오늘날짜), now() : 오늘 날짜 함수 */ 
	address	VARCHAR(50)									/* 주소 */
	/* PRIMARY KEY (idx) < 이런식으로 primary key 줘도 됨 */
);

DESC insarok;
-- DROP TABLE insarok; 테이블 지우기 (주석 부분 빼고서 범위 준 후에 alt + x 하면 됨)
SELECT * FROM insarok;

INSERT INTO insarok VALUES (DEFAULT, '인사과', '홍길동', '과장', DEFAULT, '38', '1995-01-05', '서울');
INSERT INTO insarok VALUES (DEFAULT, '총무과', '김철수', '부장', '여자', '45', '1990-11-30', '청주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '신짱구', '대리', '여자', '30', '2004-06-12', '부산');
INSERT INTO insarok VALUES (DEFAULT, '자재과', '박훈이', '사원', DEFAULT, '25', '2022-07-04', '울산');
INSERT INTO insarok VALUES (DEFAULT, '인사과', '이유리', '대리', DEFAULT, '20', '2021-01-30', '대전');
INSERT INTO insarok VALUES (DEFAULT, '총무과', '고맹구', '사원', '여자', '20', '2024-01-26', '하남');
INSERT INTO insarok VALUES (DEFAULT, '총무과', '김민지', '사원', DEFAULT, '23', '2020-01-17', '인천');
INSERT INTO insarok VALUES (DEFAULT, '자재과', '강해린', '대리', DEFAULT, '26', '2000-01-19', '서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '이하니', '과장', DEFAULT, '24', '2002-01-30', '광주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '심지언', '과장', '여자', '20', '2004-01-27', '세종');
INSERT INTO insarok VALUES (DEFAULT, '자재과', '다니엘', '대리', DEFAULT, '25', '2022-01-07', '시흥');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '이혜린', '사원', DEFAULT, '20', '2024-01-04', '청주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '고인돌', '사원', '여자', '24', '2021-01-03', '서울');
INSERT INTO insarok VALUES (DEFAULT, '인사과', '나도야', '사원', DEFAULT, '29', '2022-01-02', '서울');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '이기자', '사원', DEFAULT, '30', '2000-01-01', '청주');
INSERT INTO insarok VALUES (DEFAULT, '자재과', '김말숙', '과장', '여자', '40', '1999-01-11', '대전');
INSERT INTO insarok VALUES (DEFAULT, '총무과', '심지민', '대리', DEFAULT, '38', '2017-01-18', '부산');
INSERT INTO insarok VALUES (DEFAULT, '총무과', '이영자', '부장', '여자', '45', '2015-01-13', '경주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '심희준', '대리', DEFAULT, '36', '2014-01-17', '광주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '이지현', '사원', DEFAULT, '32', '2024-01-28', '전주');
INSERT INTO insarok VALUES (DEFAULT, '영업과', '홍길동', '사원', '여자', '30', '2020-01-15', '청주');

-- insarok 테이블의 성명, 직위, 주소, 필드만 모든 자료 표시
SELECT name, jikwi, address FROM insarok;

-- 홍길동 레코드만 출력
SELECT * FROM insarok WHERE name = '홍길동';

-- 서울에 사는 홍길동 레코드만 출력
SELECT * FROM insarok WHERE address = '서울' AND name = '홍길동';

-- 홍길동 사원만 출력
SELECT * FROM insarok WHERE name = '홍길동' AND jikwi = '사원';

-- 서울에 사는 모든 직원 출력
SELECT * FROM insarok WHERE address = '서울';

-- 서울에 살지 않는 모든 직원 출력 (위아래 두개 같음)
SELECT * FROM insarok WHERE address != '서울';
SELECT * FROM insarok WHERE address <> '서울';

-- 입사년도가 2000년 이전에 입사한 직원 출력
SELECT * FROM insarok WHERE ipsail < '2000-01-01';

-- 입사년도가 2000년~2010년 사이에 입사한 직원 출력
SELECT * FROM insarok WHERE '2000-01-01' <= ipsail AND ipsail <= '2010-12-31';
-- 앞의 범위 연산자 대신에 BETWEEN~AND 으로도 가능
SELECT * FROM insarok WHERE ipsail BETWEEN '2000-01-01' AND '2010-12-31';

-- 30대 회사원 출력
SELECT * FROM insarok WHERE 30 <= age AND age <= 39;
SELECT * FROM insarok WHERE age BETWEEN 30 AND 39;

-- 서울 또는 부산에 사는 직원 출력
SELECT * FROM insarok WHERE address = '서울' or address = '부산'
-- 앞의 OR 연산자는 IN()으로 변경 가능
SELECT * FROM insarok WHERE address IN('서울', '부산');

-- 서울 부산에 사는 사원만 출력
SELECT * FROM insarok WHERE jikwi = '사원' AND address IN('서울', '부산');

-- 김씨만 출력 // LIKE %
SELECT * FROM insarok WHERE name LIKE '김%'; 
-- 홍씨만 출력
SELECT * FROM insarok WHERE name LIKE '홍%';

-- 나무로 끝나는 이름을 가진 직원 출력 // % 가 앞으로
SELECT * FROM insarok WHERE name LIKE '%나무';

-- 이지현을 이재혁으로 이름 변경
UPDATE insarok SET name = '이재혁' WHERE name = '이지현';
-- 고맹구를 맹맹구로 이름 변경
UPDATE insarok SET name = '맹맹구' WHERE name = '고맹구';
-- 김말숙을 김말재로 이름 변경
UPDATE insarok SET name = '김말재' WHERE name = '김말숙';

-- 이름 중에서 '재'란 글자가 포함된 직원의 직급을 과장으로 변경하시오
SELECT * FROM insarok WHERE name like '%재%'; /*일단 셀렉트로 확인하고 할 것*/
UPDATE insarok SET jikwi = '과장' WHERE name LIKE '%재%';

-- 이름 중 두번째 글자가 '니'인 직원 출력
SELECT * FROM insarok WHERE name like '_니%';

-- 이름에 '이'가 포함된 직원 중에서 청주에 사는 직원의 입사일, 이름, 주소 출력
SELECT name,ipsail,address FROM insarok WHERE name like '%이%' AND address = '청주';

-- 이름에 '이'가 포함된 직원 중에서 청주에 사는 직원 중 나이가 20살 이상인 직원을 퇴사시키오
SELECT * FROM insarok WHERE name like '%이%' AND address = '청주';
DELETE FROM insarok WHERE name like '%이%' AND address = '청주' AND AGE >= 20;

-- 김민지의 성별을 여자로 변경
UPDATE insarok SET gender = '여자' WHERE name = '김민지';

-- 이름 오름차순으로 출력(순서 : order by ~~ , 오름차순 : asc(생략 가능), 내림차순 : desc)
SELECT * FROM insarok ORDER BY name;
SELECT * FROM insarok ORDER BY name DESC;

-- 남자 직원만 나이 오름차순으로 출력
SELECT * FROM insarok WHERE gender = '남자' ORDER BY age;

-- 남자 직원 중 나이를 오름차순으로, 같은 나이면 입사일은 내림차순으로 출력
SELECT * FROM insarok WHERE gender = '남자' ORDER BY age, ipsail DESC;

-- 전체 자료 중에서 다섯명만 출력하시오 (입력 순서 중 앞에서 5개)
SELECT * FROM insarok LIMIT 5;

-- 전체 자료 중 뒤에서 5명만 출력(나중에 입력된 5명만 출력이라는 의미)
SELECT * FROM insarok ORDER BY idx DESC limit 5;

-- 남자 회원 다섯명만 나이 내림 차순으로 보여주시오. (limit 출력개수)
SELECT * FROM insarok WHERE gender = '남자' ORDER BY age DESC limit 5;

-- 남자 회원 중에서 앞에서 두명을 빼고 다섯명만 출력 (limit 인덱스 번호, 출력개수)
SELECT * FROM insarok WHERE gender = '남자' ORDER BY idx limit 2,5;
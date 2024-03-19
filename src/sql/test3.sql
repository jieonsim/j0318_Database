show tables;

-- 기본 키 (primary key) : 테이블을 대표하는 키, 중복을 허용하지 않는다. 기본키는 여러개 올 수 있다.
CREATE TABLE test1 (
	idx		INT NOT NULL auto_increment PRIMARY KEY,
	name	VARCHAR(20) NOT NULL,
	age		INT DEFAULT 20,
	address VARCHAR(50)
);
DESC test1;
DROP TABLE test1;
INSERT INTO test1 VALUES (DEFAULT, '홍길동', DEFAULT, '서울');
INSERT INTO test1 VALUES (DEFAULT, '김말숙', 25, '청주');
INSERT INTO test1 VALUES (1, '소나무', 55, '제주');		/*idx 1이 이미 있기 때문에 불가*/
SELECT * FROM test1;



CREATE TABLE test2 (
	idx		INT NOT NULL auto_increment PRIMARY KEY,
	name	VARCHAR(20) NOT NULL,
	age		INT DEFAULT 20,
	test2Code VARCHAR(10) NOT NULL
	/*PRIMARY KEY (idx, test2Code)*/
);
DESC test2;
DROP TABLE test2;
INSERT INTO test2 VALUES (DEFAULT, '이기자', 23, 'aaa');
INSERT INTO test2 VALUES (DEFAULT, '김길자', 33, 'bbb');
INSERT INTO test2 VALUES (1, '소나무', 55, 'ccc');		/* 처음엔 가능하나 추가는 불가 */
INSERT INTO test2 VALUES (DEFAULT, '소나무', 55, 'bbb');	
SELECT * FROM test2;

-- UNIQUE KEY : 중복 불허를 위해 설정하는 키(PRIMARY KEY를 대신해서 중복을 불허시키고자 할 때 사용한다)
CREATE TABLE test3 (
	idx		INT NOT NULL auto_increment,
	name	VARCHAR(20) NOT NULL,
	age		INT DEFAULT 20,
	job		VARCHAR(10) NOT NULL,
	address VARCHAR(20) NOT NULL,
	test3Code VARCHAR(10) NOT NULL,
	PRIMARY KEY (idx),
	UNIQUE KEY(test3Code)
);
DESC test3;
DROP TABLE test3;
INSERT INTO test3 VALUES (DEFAULT, '소나무', 13, '학생', '서울', 'ccc');
INSERT INTO test3 VALUES (DEFAULT, '대나무', 43, '회사원', '청주', 'eee');
INSERT INTO test3 VALUES (DEFAULT, '사과나무', 27, '군인', '대전', 'ggg');
INSERT INTO test3 VALUES (1, '감나무', 19, 'fff');		/* 불가 */
INSERT INTO test3 VALUES (DEFAULT, '감나무', 19, 'eee');		/* test3Code(UNIQUE KEY)가 같기 때문에 불가 */
SELECT * FROM test3;

/*
 	- 외래키 (FOREIGN KEY) :
 	하나의 테이블에서 다른 테이블의 정보를 찾기 위해 연결해주는 역할을 할 때 지정하는 키.
 	
 	조건, 현재 테이블의 필드에 외래키로 설정하려한다면
 	반드시 상대쪽 테이블의 해당 필드는 PRIMARY KEY이거나 UNIQUE KEY로 등록되어 있어야 한다.
 	
 	또한 외래키로 지정하려는 필드는 상대쪽 테이블의 해당 필드 속성과 같아야 한다.
 	
 */

-- 중복된 걸 뽑아서 관리하자 : 정규화
CREATE TABLE test4 (
	idx			INT NOT NULL auto_increment PRIMARY KEY,
	gender		CHAR(2) DEFAULT '남자',
	test2Idx 	INT NOT NULL,
	test3Code 	VARCHAR(10) NOT NULL,
	FOREIGN KEY (test2Idx) REFERENCES test2 (idx),
	FOREIGN KEY (test3Code) REFERENCES test3 (test3Code)
);
DESC test4;
DROP TABLE test4;
INSERT INTO test4 VALUES (DEFAULT, DEFAULT, 1, 'ggg');
INSERT INTO test4 VALUES (DEFAULT, DEFAULT, 1, 'ccc');
SELECT * FROM test4;

-- SELECT 필드명 FROM 테이블명 WHERE 조건식 옵션;
SELECT *, gender FROM test3, test4;			/* cross join */
SELECT test3.*, gender FROM test3, test4;   /* test3의 모든 것과 gender (test4 에 하나 있는거)*/
SELECT test4.idx, gender FROM test3, test4; /* test4의 idx와 gender(test4 에 하나 있는거) */
SELECT t3.idx AS 고유번호, t4.gender AS 성별 FROM test3 t3, test4 t4; /* 필드명에는 AS 붙이기 */


SELECT t3.*, t4.gender FROM test3 t3, test4 t4 WHERE t3.test3Code = t4.test3Code;
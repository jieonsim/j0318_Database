show tables;

create table hoewon (
	idx INT NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age INT DEFAULT 20,
	gender CHAR(2) DEFAULT '여자',
	address VARCHAR(30)
);

DESC hoewon
DROP TABLE hoewon;

INSERT INTO hoewon VALUES (DEFAULT, '홍길동', DEFAULT, '남자', '서울');
INSERT INTO hoewon VALUES (DEFAULT, '김말숙', 29, DEFAULT, '청주');
INSERT INTO hoewon VALUES (DEFAULT, '이기자', 33, '남자', '제주');
INSERT INTO hoewon VALUES (DEFAULT, '소나무', 41, '남자', '서울');
INSERT INTO hoewon VALUES (DEFAULT, '오하늘', 19, DEFAULT, '청주');

SELECT * FROM hoewon;

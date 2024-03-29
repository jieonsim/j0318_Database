show tables;


desc sungjuk;
select * from sungjuk;

-- 집계 함수(개수 : count(), sum(), avg())
select count(*) from sungjuk; -- 성적의 개수 구하기
select count(*) as cnt from sungjuk; -- cnt 라는 이름으로 부르기
select count(kor) as 국어건수, sum(kor) as 국어합계, avg(kor) as 국어평균 from sungjuk;
-- vo에 등록하려면 한글이 아닌 영어로 써야함

-- 최대(max), 최소(min)
select max(kor) as 국어최대점수, min(kor) as 국어최소점수 from sungjuk;

-- 문자열 연결 : concat()
select concat(max(kor), '점') as 국어최대점수, concat(min(kor), '점') as 국어최소점수 from sungjuk;

-- 출력용 형식 지정 : format(필드[, 소수이하자리수])
select name, (kor + eng + mat) / 3 as 평균 from sungjuk; 
select name, format((kor + eng + mat) / 3, 1) as 평균 from sungjuk; 
-- 소수 이하 한자리만 출력

-- 수치 함수
select 123.456 as su;

-- 반올림 : round
select round(123.456) as su; -- 반올림 안됨
select round(123.567) as su; -- 반올림됨
select round(123.456) as su, round(123.567) as su; -- 두개 한줄로 쓰기도 가능
select round(123.456, 1) as su; -- 소수이하 첫째짜리까지 구하기. 즉 소수 둘째자리에서 반올림 처리
select round(123.456, -1) as su; -- 정수부 첫째자리에서 반올림, 3이라서 반올림 안된거임

-- 절삭 : truncate(수치자료, 절삭할 위치)
select truncate(123.456, 1) as su;
select truncate(123.456, 0) as su;
select truncate(123.456, -1) as su; -- 원단위 그냥 절삭된거
select truncate(129.456, -2) as su;

-- 무조건 올림: ceil(), 무조건 내림: floor()
select ceil(123.456);
select floor(123.567);

-- 나머지: mod()
select mod(10,3) as na;

-- 거듭제곱 : power()
select power(2,5) as 2의5승; -- 실수형으로 나옴, 정수형으로 바꾸려면 round 쓰거나 format하면 됨

-- 숫자리스트 중에서 최대숫자 가져오기 : greatest()
select greatest(15,4,21,7,9) as max;
select greatest(kor, eng, mat) as max from sungjuk;

-- 숫자리스트 중에서 최대숫자 가져오기 : greatest()
select least(15,4,21,7,9) as max;
select least(kor, eng, mat) as max from sungjuk;

-- 문자함수
-- 문자열의 길이 : length() - 바이트 단위로 변환
select length('seoul');
select length ('서울'); -- 유니코드는 한글1글자를 3byte로 처리
select length(name), length(kor) from sungjuk;

-- 실제 문자 개수로 반환 char_length
select char_length('seoul');
select char_length ('서울'); -- 유니코드는 한글1글자를 3byte로 처리
select char_length(name), length(kor) from sungjuk;

-- 대문자 변경 : upper(), 소문자 : lower()
select 'sEoUl' as 서울;
select upper('sEoUl') as 서울;
select lower('sEoUl') as 서울;

-- 문자열 발췌 : substring(데이터, 시작위치, 발췌개수) : 시작 위치는 1번부터
select '1234567890' as 위치값;
select substring ('1234567890', 1, 5) as 위치값;
select substring ('1234567890', 2, 5) as 위치값;
select now() as 오늘날짜;
select substring(now(), 1,10) as 오늘날짜;
select substring(now(), 12,5) as 오늘시간분; -- 현재 '시간:분' 출력

-- 특정 문자의 유무 instr() - 값이 있으면 '위치값', 없으면 '0'
select 'Welcome to Korea!!!' as data;
select instr('Welcome to Korea!!!', 'o') as data;
select instr('Welcome to Korea!!!', 'z') as data;
select instr('Welcome to Korea!!!', 'o') as data;

-- 지정 위치부터 자리수를 더한 위치 이후의 문자를 모두 버린다. : substring_index()
select substring_index('ab.cd.efg', '.', 2);
-- 왼쪽(오른쪽)부터 지정길이 만큼 문자 추출: left(), right()
select left('abcdefg', 3);
select left('자바프로그래밍', 3);
select right('abcdefg', 3);
select right('자바프로그래밍', 3);

-- 중간 글자 발췌 : mid()
select mid('abcdefg', 3,2);

-- 문자열 치환 : replace()
select replace('Welcom to korea!!!', ' ', '_') as data;
select replace('010-1234-5678', '_', '') as data;

-- 공백 지우기 : trim(), ltrim(), rtrim()
select concat('지역명:', '  s e o u l  ', '지역') as data;
select concat('지역명:',trim('     s  e  o  u  l    '), '지역') as data;
select concat('지역명:',ltrim('  s e o u l  '), '지역') as data;
select concat('지역명:',rtrim('  s e o u l  '), '지역') as data;

-- 지정된 위치에 지정한 문자열로 채운다 : insert()
select insert('가나다라마바사아', 2, 3, '****');
select insert('가나다라마바사아', 2, 3, '*');

-- 반복 : repeat()
select repeat('*', 10);
select repeat('abc', 10);
select repeat('abc/', 10);

-- 날짜 함수
select now();
select year(now());
select month(now());
select day(now());

desc insarok;
select name, ipsail from insarok;
select name, month(ipsail), day(ipsail) from insarok;
select name, concat(year(ipsail), '-', month(ipsail), '-', day(ipsail)) as ipsaDate from insarok;

select hour(now());
select concat(hour(now()), '시');
select minute(now());
select second(now());
select concat(hour(now()), '시 ', minute(now()) '분 ', second(now()), '초');

-- 요일 : 일요일 (0), 월요일 (1) ...
-- 날짜 형식 지정 : date_format()
-- 날짜 형식 포맷 : y : 년도2자리, Y :년도 4자리, m:월(숫자두자리), M: 월을 문자로, d : 일
select date_format(now(), '%y-%m-%d') as wDate;
select date_format(now(), '%Y-%m-%d');
select date_format(now(), '%Y-%M-%D'); -- 영문으로 나옴
select date_format(now(), '%Y년-%m월-%d일') as wDate;

-- 날짜 연산 : date_add(), to_days(앞의 날짜 - 뒤의 날짜)
select now();
select date_add(now(), interval 1 day); -- 오늘보다 +1 날짜 (내일날짜)
select date_add(now(), interval -1 day); -- 오늘보다 -1 날짜 (어제날짜)
select date_add(now(), interval 24 hour); -- 오늘보다 -1 날짜 (어제날짜)
select date_add(now(), interval 10 hour); -- 오늘보다 -1 날짜 (어제날짜)
select date_add(now(), interval 1 month);
select date_add(now(), interval 1 year);

select now()-1;
select to_days(now()) - to_days('2024-3-1');
select name, (to_days(now()) - to_days(ipsail)) as 입사일차이 from insarok;

select datediff(now(),'2024-3-1'); -- select to_days(now()) - to_days('2024-3-1'); 랑 같은 결과
select name, datediff(now(), ipsail) as 입사일차이 from insarok;

-- 마지막 일자 구하기 : last_day()
select last_day(now());
select last_day('2024-2-1');
select last_day('2023-2-1');

















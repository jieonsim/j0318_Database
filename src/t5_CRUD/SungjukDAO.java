package t5_CRUD;

import java.sql.*;
import java.util.*;

public class SungjukDAO {
	Connection conn = null;
	PreparedStatement pstmt = null; // PreparedStatement는 보안 때문에 생겨났다
	ResultSet rs = null;

	SungjukVO vo = null;
	String sql = "";

	// 생성자
	public SungjukDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패" + e.getMessage());
		}
	}

	// conn 객체 close 하는 메소드
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	// pstmt 객체 close 하는 메소드
	public void pstmtClose() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
		}
	}

	// rs 객체 close 하는 메소드
	public void rsClose() {
		try {
			if (rs != null) {
				rs.close(); // rs 닫은 후에는 pstmt도 닫아줘야한다
			}
			pstmtClose();
		} catch (Exception e) {
		}
	}

	// 성적 자료 입력 처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;
		// 웹으로오는 것들은 모두 문자로오기 때문에 ? 에 ''가 포함되어있다 그래서 '?'로 쓰지 않고 그냥 ?으로 쓴다
		// 그냥 디폴트를 제외한 모든 것은 ?로 쓰면된다
		try {
			sql = "insert into sungjuk values (default,?,?,?,?)"; // 순서는 sql 데이터목록순
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName()); // 첫번째 물음표
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 이름 조회(개별 검색 / 동명이인 검색)
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();
		try {
			sql = "select * from sungjuk where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name); // name : ?가 들어갈 내용
			rs = pstmt.executeQuery();
			// 하나 확인할 땐 if, 여러개면 while
			if (rs.next()) { // 있는지 확인
				vo.setIdx(rs.getInt("idx")); // 있으면 set으로 저장
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("eng"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
			} 
			else {
				vo = null;
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); // sql 명령문이 select 이기 때문에 rsClose
		}
		return vo;
	}
	
	// 전체 자료 검색 처리
	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<SungjukVO>();
		
		try {
			sql = "select * from sungjuk order by name"; // 이름 오름차순 / 나중에 추가한 순으로 할거면 idx desc
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 매번 vo 객체를 만들어야하므로 while로 반복
				vo = new SungjukVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); // sql 명령문이 select 이기 때문에 rsClose
		}
		return vos;
	}

	// 성적 자료 수정하기
	public int setSungjukUpdate(SungjukVO vo) {
		int res = 0;
		
		try {
			sql = "update sungjuk set name = ?, kor = ?, eng = ?, mat = ? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose(); // sql 명령문이 update이기 때문에 rsclose가 아님
		}
		return res;
	}

	// 성적 삭제하기
	public int setSungjukDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from sungjuk where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose(); // sql 명령문이 update이기 때문에 rsclose가 아님
		}
		return res;
	}
}

package t4_CRUD; // jsp 프로젝트까지는 해당 구조 유지, 수정할 때는 주석 달기

import java.sql.*;
import java.util.*;

public class HoewonDAO2 {
	private Connection conn = null; // 데이터베이스 연동
	private Statement stmt = null; // sql 명령어 쓰기 위함
	private ResultSet rs = null; // sql의 select 절이 있다면 무조건 있어야 함, select 값을 받기 위함

	HoewonVO vo = null; // 자료 담기위함
	private String sql = ""; // sql 명령어 변수

	public HoewonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/javaclass";
//			String url = "jdbc:mysql://127.0.0.1:3306/javaclass";
//			String url = "jdbc:mysql://192.168.50.65:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패");
		}
	}

	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}

	}

	public void stmtClose() {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			stmtClose();
		} catch (SQLException e) {
		}
	}

	// 전체 회원 조회
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<HoewonVO>(); // 1.리턴 타입(ArrayList<HoewonVO>)으로 생성 먼저 해주고
		try {
			stmt = conn.createStatement();
			sql = "select * from hoewon";
			rs = stmt.executeQuery(sql);

//			HoewonVO vo = null; // 선언 / 위 필드에 선언해서 주석
			while (rs.next()) {
				vo = new HoewonVO(); // 생성
				// 담기
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));

				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); // select 절이 있기 때문에 rs랑 stmt 객체 같이 닫기
		}
		return vos; // 2.리턴값 변수 써주기
	}

	// 개별 검색 처리
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO();

		try {
			stmt = conn.createStatement();
			sql = "select * from hoewon where name = '" + name + "'";
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); // select 절이 있기 때문에 rs랑 stmt 객체 같이 닫기
		}
		return vo;
	}

	// 회원 자료 수정 처리 t3_crud version
	/*
	 * public void setUpdate(int idx, int choice, String content) { try { stmt =
	 * conn.createStatement(); if (choice == 1) { sql = "update hoewon set name = '"
	 * + content + "' where idx = " + idx; } else if (choice == 2) { sql =
	 * "update hoewon set age = " + Integer.parseInt(content) + " where idx = " +
	 * idx; } else if (choice == 3) { sql = "update hoewon set gender = '" + content
	 * + "' where idx = " + idx; } else if (choice == 4) { sql =
	 * "update hoewon set address = '" + content + "' where idx = " + idx; }
	 * stmt.executeUpdate(sql); // executeQuery() : 결과를 넘겨받고자할 때 씀(select에만 쓰이고 나머지는
	 * excecuteUpdate - 그냥 수행하고 끝) } catch (SQLException e) {
	 * System.out.println("SQL 오류 : " + e.getMessage()); } finally { stmtClose(); //
	 * select 절이 없기 때문에 stmt만 닫기 } }
	 */

	// 회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql = "delete from hoewon where name = '" + name + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); // select 절이 없기 때문에 stmt만 닫기
		}
	}

	// 회원 등록 처리
	public void setInput(HoewonVO vo) {
		try {
			stmt = conn.createStatement();
			sql = "insert into hoewon values (default,'" + vo.getName() + "'," + vo.getAge() + ",'" + vo.getGender()
					+ "','" + vo.getAddress() + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); // select 절이 없기 때문에 stmt만 닫기
		}
	}

	// 회원 정보 수정하기
	public int setUpdate(HoewonVO vo) {
		int res = 0;
		try {
			stmt = conn.createStatement();
			sql = "update hoewon set name = '" + vo.getName() + "', age = " + vo.getAge() + ", gender = '"
					+ vo.getGender() + "', address = '" + vo.getAddress() + "' where idx = " + vo.getIdx();
			res = stmt.executeUpdate(sql); // 한 건이라도 수정했으면 값이 1이상, 자료가 없어서라던지 수정이 안되면 0이됨ㄴ
//			System.out.println("res : " + res); // res 찍어서 결과보고 지우기
//			stmt.executeUpdate(sql);
			// executeQuery() : 결과를 넘겨받고자할 때 씀(select에만 쓰이고 나머지는 excecuteUpdate - 그냥 수행하고 끝)
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); // select 절이 없기 때문에 stmt만 닫기
		}
		return res;
	}
}

package t3_CRUD;

import java.sql.*;
import java.util.*;

public class HoewonDAO2 {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	// finally로 statement랑 resultset 닫아줘야 함
	private String sql = null;

	public HoewonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/javaclass";
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

	// 전체 회원 조회
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<HoewonVO>();
		try {
			stmt = conn.createStatement();
			sql = "select * from hoewon";
			rs = stmt.executeQuery(sql);

			HoewonVO vo = null; // 선언
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
		} 
		return vos;
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
		}
		return vo;
	}

	// 회원 자료 수정 처리
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt = conn.createStatement();
			sql = "";
			if (choice == 1) {
				sql = "update hoewon set name = '" + content + "' where idx = " + idx;
			} else if (choice == 2) {
				sql = "update hoewon set age = " + Integer.parseInt(content) + " where idx = " + idx;
			} else if (choice == 3) {
				sql = "update hoewon set gender = '" + content + "' where idx = " + idx;
			} else if (choice == 4) {
				sql = "update hoewon set address = '" + content + "' where idx = " + idx;
			}
			stmt.executeUpdate(sql);
			// executeQuery() : 결과를 넘겨받고자할 때 씀(select에만 쓰이고 나머지는 excecuteUpdate - 그냥 수행하고 끝)
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

	// 회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql = "delete from hoewon where name = '" + name + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

	// 회원 등록 처리
	public void setInput(HoewonVO vo) {
		try {
			stmt = conn.createStatement();
			sql = "insert into hoewon values (default,'" + vo.getName() + "'," + vo.getAge() + ",'"
					+ vo.getGender() + "','" + vo.getAddress() + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
}

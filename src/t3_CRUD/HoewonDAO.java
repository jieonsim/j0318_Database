package t3_CRUD;

import java.sql.*;

public class HoewonDAO {
	private Connection conn = null;

	public HoewonDAO() {
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
	public HoewonVO getList() {
		HoewonVO vo = new HoewonVO();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			
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
}

package t2_CRUD;

import java.sql.*;

public class HoewonDAO3 {
	private Connection conn = null;

	public HoewonDAO3() {
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
	public void getList() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			// sql 관리하기 위해 Statement 객체 사용, 명령어를 관리
			// 저장, 수정, 삭제하고 끝나면 ResultSet 객체 필요없음
			// 단, select라는 sql 명령을 사용하려면 ResultSet 객체 꼭 필요함
			
			System.out.println("-----------------------------------------------");
			System.out.println("번호\t성명\t나이\t성별\t주소");
			System.out.println("-----------------------------------------------");
			while (rs.next()) { // rs.next() : 자료 하나씩 내려서 확인
				System.out.print(rs.getInt("idx") + "\t");
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getInt("age") + "\t");
				System.out.print(rs.getString("gender") + "\t");
				System.out.print(rs.getString("address") + "\n");
				System.out.println();
			}
			System.out.println("-----------------------------------------------");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
}

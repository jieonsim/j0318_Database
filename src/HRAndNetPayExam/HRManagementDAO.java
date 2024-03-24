package HRAndNetPayExam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HRManagementDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String sql;

	public HRManagementDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터 베이스 연동 실패 " + e.getMessage());
		}
	}

	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	public void pstmtClose() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null) {
				rs.close();
			}
			pstmt.close();
		} catch (SQLException e) {
		}
	}

	// 본봉 신규 등록
	public int setBasicSalaryInput(HRVO vo) {
		int result = 0;

		try {
			sql = "insert into salary values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getJikkub());
			pstmt.setInt(2, vo.getBonbong());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	// 직급 유무 확인
	public HRVO getJikkubSearch(String jikkub) {
		HRVO vo = new HRVO();
		try {
			sql = "select * from salary where jikkub = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikkub);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setJikkub(rs.getString("jikkub"));
			} else {
				vo = null;
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 본봉 전체 조회
	public ArrayList<HRVO> getBasicSalaryList() {
		ArrayList<HRVO> vos = new ArrayList<HRVO>();

		try {
			sql = "select * from salary order by bonbong desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HRVO vo = new HRVO();
				vo.setJikkub(rs.getString("jikkub"));
				vo.setBonbong(rs.getInt("bonbong"));

				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 본봉 수정
	public int setBasicSalaryUpdate(HRVO vo) {
		int result = 0;

		try {
			sql = "update salary set bonbong = ? where jikkub = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBonbong());
			pstmt.setString(2, vo.getJikkub());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	// 본봉 삭제
	public int setBasicSalaryDelete(String jikkub) {
		int result = 0;

		try {
			sql = "delete from salary where jikkub = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikkub);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	// 사번 유무 확인 및 본봉 정보 조회
	public HRVO getSabunSearch(String sabun) {
		HRVO vo = null;
		try {
			// 인사 정보 조회
			sql = "select * from insa where sabun = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sabun);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new HRVO();
				vo.setSabun(rs.getString("sabun"));
				vo.setBuseo(rs.getString("buseo"));
				vo.setName(rs.getString("name"));
				vo.setJikkub(rs.getString("jikkub"));
				vo.setAge(rs.getInt("age"));
				vo.setIpsail(rs.getString("ipsail"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));

				// 본봉 정보 조회
				String salarySQl = "select bonbong from salary where jikkub = ?";
				pstmt = conn.prepareStatement(salarySQl);
				pstmt.setString(1, vo.getJikkub());
				ResultSet rsSalary = pstmt.executeQuery();
				if (rsSalary.next()) {
					vo.setBonbong(rsSalary.getInt("bonbong"));
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
			vo = null;
		} finally {
			rsClose();
		}
		return vo;
	}

	// 직원 신규 등록
	public int setEmployeeInput(HRVO vo) {
		int result = 0;
		try {
			sql = "insert into insa values (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getSabun());
			pstmt.setString(3, vo.getBuseo());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getJikkub());
			pstmt.setInt(6, vo.getAge());
			pstmt.setString(7, vo.getIpsail());
			pstmt.setString(8, vo.getGender());
			pstmt.setString(9, vo.getAddress());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	// 직원 전체 조회
	public ArrayList<HRVO> getEmployeeFullList() {
		ArrayList<HRVO> vos = new ArrayList<HRVO>();

		try {
			sql = "select * from insa order by idx";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HRVO vo = new HRVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSabun(rs.getString("sabun"));
				vo.setBuseo(rs.getString("buseo"));
				vo.setName(rs.getString("name"));
				vo.setJikkub(rs.getString("jikkub"));
				vo.setAge(rs.getInt("age"));
				vo.setIpsail(rs.getString("ipsail"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));

				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 직원 정보 수정하기
	public int setEmployeeUpdate(HRVO vo) {
		int result = 0;

		try {
			sql = "update insa set buseo = ?, name = ?, jikkub = ?, age = ?, ipsail = ?, gender = ?, address = ? where sabun = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBuseo());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getJikkub());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getIpsail());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getAddress());
			pstmt.setString(8, vo.getSabun());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}

	// 직원 삭제
	public int setEmployeeDelete(String sabun) {
		int result = 0;

		try {
			sql = "delete from insa where sabun = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sabun);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return result;
	}
}

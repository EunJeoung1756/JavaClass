package Project;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class ExcerciseSQL {
	// 1. DB접속
	Connection con = null;

	// 2. DB데이터 보내기
	Statement stmt = null;
	PreparedStatement pstmt = null;

	// 3. DB데이터 받기
	ResultSet rs = null;

	EMember mem = new EMember();
	Recode rc = new Recode();
	Sport sp = new Sport();
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	Scanner sc = new Scanner(System.in);

	// DB 접속 메소드
	public void connect() {
		con = DBC.DBConnect();
	}

	// DB 접속해제 메소드
	public void conClose() {
		try {
			con.close();
			System.out.println("DB 접속해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ID 중복 체크 메소드
	public boolean Check(String iD) {
		boolean a = true;
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iD);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println();
				System.out.println(" 아이디가 중복됩니다.\n");

			} else {
				System.out.println();
				System.out.println(" 아이디 확인 성공!\n");
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	// 회원가입 메소드
	public void memberJoin(EMember mem) {

		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getName());
			pstmt.setInt(4, mem.getAge());
			pstmt.setInt(5, mem.getHight());
			pstmt.setInt(6, mem.getWeight());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입 성공!!\n");
			} else {
				System.out.println("회원가입 실패\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int acCount() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM MEMBER";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 로그인 메소드
	public boolean checkInfo(String iD, String pW) {
		boolean a = false;
		String sql = "SELECT ID, PW FROM MEMBER WHERE ID = ? AND PW = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iD);
			pstmt.setString(2, pW);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("로그인 성공!!\n");
				a = true;
			} else {
				System.out.println("아이디나 비밀번호를 확인해 주세요\n");
				a = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	// 운동종목 테이블에 넣는 메소드(사용 X)
	public void excerciseInfo() {

		System.out.println("운동종목 불러오기");
		String sql1 = "INSERT INTO EXERCISE VALUES('벤치 프레스', '가슴', 5)";
		String sql2 = "INSERT INTO EXERCISE VALUES('덤벨 프레스', '가슴', 5)";
		String sql3 = "INSERT INTO EXERCISE VALUES('풀 업', '등', 5)";
		String sql4 = "INSERT INTO EXERCISE VALUES('풀 다운', '등', 5)";
		String sql5 = "INSERT INTO EXERCISE VALUES('밀리터리 프레스', '어깨', 5)";
		String sql6 = "INSERT INTO EXERCISE VALUES('덤벨 컬', '팔', 5)";
		String sql7 = "INSERT INTO EXERCISE VALUES('크런치', '복부', 5)";
		String sql8 = "INSERT INTO EXERCISE VALUES('스쿼트', '하체', 5)";
		String sql9 = "INSERT INTO EXERCISE VALUES('데드리프트', '전신', 10)";
		String sql10 = "INSERT INTO EXERCISE VALUES('버피 테스트', '전신', 10)";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			rs = stmt.executeQuery(sql2);
			rs = stmt.executeQuery(sql3);
			rs = stmt.executeQuery(sql4);
			rs = stmt.executeQuery(sql5);
			rs = stmt.executeQuery(sql6);
			rs = stmt.executeQuery(sql7);
			rs = stmt.executeQuery(sql8);
			rs = stmt.executeQuery(sql9);
			rs = stmt.executeQuery(sql10);

		} catch (SQLException e) {
			System.out.println("2번 테이블 정보 넣기에 오류 발생!\n");
			e.printStackTrace();
		}

	}

	// 운동 조회 메소드
	public void choice() {
		System.out.println("운동 종목!");
		System.out.println();
		String sql = "SELECT * FROM EXERCISE";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("==================================");
				System.out.println("운동 명칭 : " + rs.getString(1));
				System.out.println("운동 부위 : " + rs.getString(2));
				System.out.println("칼로리 소모 : " + rs.getInt(3));
				System.out.println("=================================");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 선택한 운동 테이블에 넣는 메소드
	public void input(String ex, String ID) {

		String sql1 = "SELECT E_NAME FROM EXERCISE WHERE E_NAME = ?";

		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, ex);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String sql2 = "SELECT * FROM EXERCISE WHERE E_NAME = ?";

				try {
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, ex);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						rc.setR_name(rs.getString(1));
						rc.setR_part(rs.getString(2));
						rc.setR_cal(rs.getInt(3));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("┏                           ┓");
				System.out.print  ("  선택한 운동의 횟수를 입력하시오 : ");
				rc.setR_set(sc.nextInt());
				System.out.println("┖                           ┛ ");

				String sq3 = "INSERT INTO EX_RECORD VALUES(?, TO_DATE(?), ?, ?, ?, ?)";

				try {
					pstmt = con.prepareStatement(sq3);

					pstmt.setString(1, ID);
					pstmt.setString(2, sdf.format(cal.getTime()));
					pstmt.setString(3, rc.getR_name());
					pstmt.setString(4, rc.getR_part());
					pstmt.setInt(5, rc.getR_set());
					pstmt.setInt(6, rc.getR_cal());

					int result;
					result = pstmt.executeUpdate();

					if (result > 0) {
						System.out.println("운동추가 성공!");
						System.out.println();
						sc.nextLine();
					} else {
						System.out.println("운동추가 실패!");
						System.out.println();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("운동을 잘 못 선택하셨습니다.\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 회원 정보 수정 메소드
	public void modify(EMember mem) {
		String sql = "UPDATE MEMBER SET Pw = ?, Name = ?, Age = ?, Hight = ?, Weight = ? WHERE Id = ?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(6, mem.getId());
			pstmt.setString(1, mem.getPw());
			pstmt.setString(2, mem.getName());
			pstmt.setInt(3, mem.getAge());
			pstmt.setInt(4, mem.getHight());
			pstmt.setInt(5, mem.getWeight());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원 정보 수정 성공!");
			} else {
				System.out.println("회원 정보 수정 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 운동종목 추가 메소드
	public void addr() {

		boolean run = true;
		while (run) {
			System.out.println("추가하실 운동종목의 이름을 입력하세요");
			String name = sc.nextLine();
			sp.setE_name(name);
			String sql1 = "SELECT E_NAME FROM EXERCISE WHERE E_NAME = ?";

			try {
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, sp.getE_name());

				rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("이미 있는 운동입니다.");
				} else {
					run = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("추가하실 운동종목의 부위를 입력하세요");
		String part = sc.nextLine();
		sp.setE_part(part);
		System.out.println("추가하실 운동종목의 횟수별 칼로리를 입력하세요");
		int cal = sc.nextInt();

		sp.setE_cal(cal);

		String sql2 = "INSERT INTO EXERCISE VALUES(?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql2);

			pstmt.setString(1, sp.getE_name());
			pstmt.setString(2, sp.getE_part());
			pstmt.setInt(3, sp.getE_cal());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("운동 추가 성공!");
				sc.nextLine();
			} else {
				System.out.println("운동 추가 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void allSearch(String iD) {
		String sql = "SELECT R_ID, R_DATE, R_NAME, R_SET, R_CAL,(R_SET*R_CAL), RANK() OVER (ORDER BY R_SET DESC) FROM "
				+ "EX_RECORD WHERE R_ID = ? ORDER BY RANK() OVER (ORDER BY R_SET DESC)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iD);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("====================================");
				System.out.println();
				System.out.println("ID : " + rs.getString(1));
				System.out.println("날짜 : " + rs.getString(2));
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("횟수 : " + rs.getInt(4));
				System.out.println("운동량 : " + rs.getInt(5) + "kcal");
				System.out.println("BURNING CALORIES : " + rs.getString(6) + "kcal");
				System.out.println();
				System.out.println("====================================");
				System.out.println();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void partSearch(String exname) {

		String sql = "SELECT R_DATE, R_PART, R_SET, (R_CAL*R_SET)," + "RANK() OVER (ORDER BY R_SET DESC)"
				+ "FROM EX_RECORD WHERE R_NAME = ? ORDER BY R_DATE";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, exname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println();
				System.out.println("날짜 : " + rs.getString(1));
				System.out.println("부위 : " + rs.getString(2));
				System.out.println("횟수 : " + rs.getInt(3));
				System.out.println("총 칼로리 소모량 : " + rs.getInt(4));
				System.out.println("순위 : " + rs.getInt(5));
				System.out.println();
				System.out.println();
			}

			if (rs.getRow() <= 0) {
				System.out.println("운동 기록이 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void onlyName() {
		String sql = "SELECT E_NAME FROM EXERCISE";
		int i = 1;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("      운동 목록");
			while (rs.next()) {

				System.out.println("   "+i + ". " + rs.getString(1));
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean finalSearch(String exname) {

		boolean c = false;

		String sql = "SELECT * FROM EXERCISE WHERE E_NAME = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, exname);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				c = true;
			} else {
				c = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}

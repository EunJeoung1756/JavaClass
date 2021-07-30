package BANK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import NAVERID.DBC;

public class BankSQL {
	
	// DB접속을 위한 변수 선언
		Connection con = null;
		
	// DB로 쿼리문 전송을 위한 변수 선언
		Statement stmt = null;
		PreparedStatement pstmt = null;
	// PreparedStatement : ? 를 문자로 인식!
		
	// 조회(SELECT)의 결과를 저장하기 위한 변수 선언
		ResultSet rs = null;

		
	public void connect() {
		con = DBC.DBConnect();
	}

	
	public void conClose() {
		try {
			con.close();
			System.out.println("DB 접속 해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int acCount() {
		
		int count = 0; 
		
		String sql = "SELECT COUNT(*) FROM BANK";
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}


	public void insert(Client ct) {
		String sql = "INSERT INTO BANK VALUES(?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, ct.getAccountNo());
			pstmt.setInt(2, ct.getClientNo());
			pstmt.setString(3, ct.getcName());
			pstmt.setInt(4, ct.getBalance());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println(ct.getcName() + "님 계좌가 생성되었습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void deposit(String accountNo, int balance) {
		String sql = "UPDATE BANK SET BALANCE = BALANCE + ? WHERE ACCOUNTNO = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, balance);
			pstmt.setString(2, accountNo);
			
			int result = pstmt.executeUpdate();
			
			if (result>0) {
				System.out.println(balance + "원 입금 성공!");
			} else {
				System.out.println("입금 실패!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	
	public void withdraw(String accountNo, int balance) {
		
		String sql = "UPDATE BANK SET BALANCE = BALANCE - ? WHERE ACCOUNTNO = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, balance);
			pstmt.setString(2, accountNo);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println(balance+"원 출금 성공!");
			} else {
				System.out.println("출금 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}


	public void Balance(String accountNo) {
		
		String sql = "SELECT BALANCE FROM BANK WHERE ACCOUNTNO = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, accountNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(accountNo+"계좌의 잔액은"+rs);
			} else {
				System.out.println("잔액 조회 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	
	public int checkBalance(String accountNo) {
		
		int result = 0;
		
		String sql = "SELECT BALANCE FROM BANK WHERE ACCOUNTNO = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			} else {
				System.out.println("잔액 조회 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	


	public boolean sendCheck(String sAccount) {

		boolean result = false;
		String sql = "SELECT ACCOUNTNO FROM BANK WHERE ACCOUNTNO=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sAccount);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			} else {
				result = false;
				System.out.println("보내시는 분의 계좌번호 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	public boolean receive(String rAccount) {
		boolean result = false;
		String sql = "SELECT ACCOUNTNO FROM BANK WHERE ACCOUNTNO=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rAccount);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			} else {
				result = false;
				System.out.println("받으시는 분의 계좌번호 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	public void transfer(String sAccount, String rAccount, int sbalance) {
		
		String sql1 ="UPDATE BANK SET BALANCE = BALANCE-? WHERE ACCOUNTNO=?";
		try {
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, sbalance);
			pstmt.setString(2, sAccount);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		String sql2 ="UPDATE BANK SET BALANCE = BALANCE+? WHERE ACCOUNTNO=?";
		try {
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, sbalance);
			pstmt.setString(2, rAccount);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("송금이 정상적으로 완료됐습니다.");
			} else {
				System.out.println("송금을 실패했습니다.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}

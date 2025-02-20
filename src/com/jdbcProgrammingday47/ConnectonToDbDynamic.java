package com.jdbcProgrammingday47;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectonToDbDynamic {
	private static Statement pstmt;

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		//Step : 1 = Load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Step:2 = Establish Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/janJDBC","root","roor123");
		if(con==null) {
			System.out.println("Connection Failed");
			
		}
		else {
			System.out.println("Connected to the Database");
			//Step:3 = Create SQL Query
			String sql = "insert into student values(?,?,?,?,?,?,?)";
//				String sql1 = "update student set sname = ? where id = ?";
			//Step:4 = Convert the string based query to SQL based query
			PreparedStatement pstmt = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the id : ");
			int id = sc.nextInt();
			System.out.println("Enter the name : ");
			String name = sc.next();
			System.out.println("Enter the school : ");
			String school = sc.next();
			System.out.println("Enter the father name : ");
			String fname = sc.next();
			System.out.println("Enter the mother name : ");
			String mname = sc.next();
			System.out.println("Enter the phone number : ");
			long phone = sc.nextLong();
			System.out.println("Enter the email : ");
			String email = sc.next();
			
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3,school);
			pstmt.setString(4,fname);
			pstmt.setString(5,mname);
			pstmt.setLong(6,phone);
			pstmt.setString(7,email);
			
			//Step:5 = Execute Query and collect the result
			int x = pstmt.executeUpdate();
			if(x>=0) {
				System.out.println("Table data inserted");
			
			}
			else {
				System.out.println("Table data not inserted");
		
			}
			
			pstmt.close();
			con.close();
			
		}
	}
	
}
	






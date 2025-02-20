package com.jdbcProgrammingday47;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProjectStudentSystem {
	
	private static Statement stmt;
	private static PreparedStatement pstmt;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/janJDBC", "root", "roor123");
		if (con == null) {
			System.out.println("Connection Failed");
		} else {
			System.out.println("Connection Successful");
			stmt = con.createStatement();
			System.out.println("Welcome to Student Management System");
			System.out.println("Type 1 for Insert Operation");
			System.out.println("Type 2 for Update Operation");
			System.out.println("Type 3 for Delete Operation");
			int cond = sc.nextInt();

			switch (cond) {
			case 1:
				String sql = "insert into student values(?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				System.out.println("Enter id:");
				int id = sc.nextInt();
				System.out.println("Enter name:");
				String name = sc.next();
				System.out.println("Enter school:");
				String sname = sc.next();
				System.out.println("Enter father name:");
				String fname = sc.next();
				System.out.println("Enter mother name:");
				String mname = sc.next();
				System.out.println("Enter email:");
				String email = sc.next();
				System.out.println("Enter phone:");
				boolean phone = sc.hasNextInt();
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, sname);
				pstmt.setString(4, fname);
				pstmt.setString(5, mname);
				pstmt.setString(6, email);
				pstmt.setBoolean(7, phone);
				int y = pstmt.executeUpdate();
				if (y >= 0) {
					System.out.println("Table data inserted");
				} else {
					System.out.println("Table data not inserted");
				}
				break;

			case 2:
				System.out.println("Choose field to update:");
				System.out.println("1. Name");
				System.out.println("2. Email");
				System.out.println("3. Father Name");
				System.out.println("4. Mother Name");
				System.out.println("5. Phone");
				int choice = sc.nextInt();
				System.out.println("Enter id:");
				int studentId = sc.nextInt();
				switch (choice) {
				case 1:
					updateField(con, sc, "name", studentId);
					break;
				case 2:
					updateField(con, sc, "email", studentId);
					break;
				case 3:
					updateField(con, sc, "fname", studentId);
					break;
				case 4:
					updateField(con, sc, "mname", studentId);
					break;
				case 5:
					updateField(con, sc, "phone", studentId);
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
				break;

			case 3:
				System.out.println("Delete operation");
				System.out.println("Enter id:");
				int id3 = sc.nextInt();
				deleteopertion(con,sc,id3);
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}

	private static void updateField(Connection con, Scanner sc, String fieldName, int id) throws SQLException {
		String sql = "UPDATE student SET " + fieldName + " = ? WHERE id = ?";
		pstmt = con.prepareStatement(sql);
		System.out.println("Enter new value for " + fieldName + ":");
		String value = sc.next();
		pstmt.setString(1, value);
		pstmt.setInt(2, id);
		int z = pstmt.executeUpdate();
		if (z >= 0) {
			System.out.println("Table data updated");
		} else {
			System.out.println("Table data not updated");
		}
	}
	private static void deleteopertion(Connection con, Scanner sc, int id) throws SQLException {
		String sql = "delete from student WHERE id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		int z = pstmt.executeUpdate();
		if (z >= 0) {
			System.out.println("Table data deleted");
		} else {
			System.out.println("Table data not deleted");
		}
	}
}



package com.hospital.java;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Doctors {
	private Connection conn;
	Scanner sc=new Scanner(System.in);
	public Doctors(Connection conn) {
		this.conn=conn;
	}
	public void addDoctors() throws SQLException {
		System.out.println("enter the Doctor Id: ");
		int id=sc.nextInt();
		System.out.println("Enter the Doctor  NAME : ");
		String NAME=sc.next();
		System.out.println("enter the Department of Doctor : ");
		String department=sc.next();
		
		String query="insert into doctors(id,NAME,department) values(?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			ps.setInt(1,id);
			ps.setString(2, NAME);
			ps.setString(3,department);
			if(ps.executeUpdate()>0) {
				System.out.println("Details added successfully");
			}
			else {
				System.out.println("Failed to Add");
			}
		}
				
	}
	public void viewDoctors() throws SQLException {
		String query="select * from doctors";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			try(ResultSet rs=ps.executeQuery(query)){
				
				System.out.println("Doctorss Details: ");
				while(rs.next()) {
					int id=rs.getInt("id");
					String NAME=rs.getString("NAME");
					String department=rs.getString("department");
					System.out.println("Dcotor Id :"+id);
					System.out.println("Doctor  Name :"+NAME);
					System.out.println("Doctors Department :"+department);
					
					
				}
				
			}
		}
	}
	public boolean getDoctorbyid(int id) throws SQLException {
		String query="select count(1) from doctors where id=?";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			ps.setInt(1,id);
			try(ResultSet rs=ps.executeQuery()){
				if(rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
	}
		return false;


}
	
}
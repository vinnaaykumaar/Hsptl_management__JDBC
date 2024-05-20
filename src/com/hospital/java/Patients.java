package com.hospital.java;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Patients{
	private Connection conn;
	Scanner sc=new Scanner(System.in);
	public Patients(Connection conn) {
		this.conn=conn;
	}
	public void addPatients() throws SQLException {
		System.out.println("enter the Patient Name: ");
		String Name=sc.nextLine();
		System.out.println("Enter the Patient Age: ");
		int agr=sc.nextInt();
		System.out.println("enter the Gender of the Patient: ");
		String gender=sc.next();
		String query="insert into patients(name,agr,gender) values(?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			ps.setString(1,Name);
			ps.setInt(2, agr);
			ps.setString(3,gender);
		
			if(ps.executeUpdate()>0) {
				System.out.println("Details added successfully");
			}
			else {
				System.out.println("Failed to Add");
			}
		}
				
	}
	public void viewPatients() throws SQLException {
		String query="select * from patients";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			try(ResultSet rs=ps.executeQuery(query)){
				
				System.out.println("Patients Details: ");
				while(rs.next()) {
					int id=rs.getInt("id");
					String name=rs.getString("name");
					int agr=rs.getInt("agr");
					String gender=rs.getString("gender");
					System.out.println("Patient Id :"+id);
					System.out.println("Patient Name :"+name);
					System.out.println("Patients Age :"+agr);
					System.out.println("Pateints Gender :"+gender);
					
					
				}
				
			}
		}
	}
	public boolean getPatientbyid(int id) throws SQLException {
		String query="select count(1) from patients where id=?";
		try(PreparedStatement ps=conn.prepareStatement(query)){
			ps.setInt(1,id);
			try(ResultSet rs=ps.executeQuery()){
				if(rs.next()) {
				return rs.getInt(1) > 0;
			}
		}
	}
		return false;

}}


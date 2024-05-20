package com.hospital.java;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.SQLException;
public class Hospital_Management {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(System.in);
		try(Connection conn = Database.getConnection()){
			Patients patient=new Patients(conn);
			Doctors doctor=new Doctors(conn);
			Appointment appointment=new Appointment(conn,patient,doctor);
			while(true) {
				System.out.println("********welcome to hospital management system***********");
				System.out.println("1. ADD PATIENTS");
				System.out.println("2. ADD DOCTOR");
				System.out.println("3. VIEW PATIENTS");
				System.out.println("4. VIEW DOCTORS");
				System.out.println("5. BOOK APPOINTMENT");
				System.out.println("6. Exit");
				System.out.println("ENTER YOUR CHOICE");
				int ch=sc.nextInt();
				switch(ch) {
				case 1:{
					patient.addPatients();
					break;
				}
					
				
				case 2:{
					doctor.addDoctors();
					break;
					
					
				}
				case 3:{
					patient.viewPatients();
					break;
					
				}
				case 4:{
					doctor.viewDoctors();
					break;
					
					
				}
				case 5:{
					appointment.Appointment();
					break;}
				case 6:{
					
					sc.close();
					return;
				}
				default:
					System.out.println("enter the correct choice:");
				}
			}
			
		}
		
		

		
	}

	
}

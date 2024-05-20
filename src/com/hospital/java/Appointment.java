package com.hospital.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
	    private Connection connection;
	    private Patients patient; // Define the Patients class
	    private Doctors doctor; // Define the Doctors class

	    Scanner scanner = new Scanner(System.in);

	    public Appointment(Connection connection, Patients patient, Doctors doctor) {
	        this.connection = connection;
	        this.patient = patient;
	        this.doctor = doctor;
	    }

	    public void Appointment() throws SQLException {
	        System.out.print("Enter Patient Id: ");
	        int patient_id = scanner.nextInt();

	        System.out.print("Enter Doctor Id: ");
	        int id = scanner.nextInt();

	        System.out.print("Enter appointment date yyyy-mm-dd: ");
	        String appointmentDate = scanner.next();
	        
	        System.out.println("Enter the speciliast Doctor:  ");
	        String department=scanner.next();

	        if (!patient.getPatientbyid(patient_id)) { 
	            System.out.println("Please provide a valid patient id.");
	            System.out.println();
	            return;
	        }

	        if (!doctor.getDoctorbyid(id)) { 
	            System.out.println("Please provide a valid doctor id.");
	            System.out.println();
	            return;
	        }

	        if (!checkAvailability(connection, id, appointmentDate)) {
	            System.out.println("Doctor not available.");
	            return;
	        }
	        String query = "INSERT INTO appointments(patient_id, id, appointment_date,department) VALUES(?, ?, ?,?)";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, patient_id);
	            preparedStatement.setInt(2, id);
	            preparedStatement.setString(3, appointmentDate);
	            preparedStatement.setString(4, department);
	            

	            if (preparedStatement.executeUpdate() > 0) {
	                System.out.println("Appointment booked successfully.");
	                System.out.println();
	            } else {
	                System.out.println("Appointment not booked successfully.");
	                System.out.println();
	            }
	        }
	    }

	    public boolean checkAvailability(Connection connection, int id, String appointment_date) throws SQLException {
	        String query = "SELECT COUNT(1) FROM appointments WHERE id = ? AND appointment_date = ?";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, appointment_date);

	            try (ResultSet rs = preparedStatement.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) == 0;
	                }
	            }
	        }

	        return false;
	    }
	}

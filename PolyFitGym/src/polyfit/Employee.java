/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
public class Employee extends Person {
    private static final long serialVersionUID = 1L;
    
    private int staffId;
    private double salary;

    public Employee(int staffId, double salary, String firstName, String lastName, String address, String phone) {
        super(firstName, lastName, address, phone);
        this.staffId = staffId;
        this.salary = salary;
    }

    public int getStaffId() { 
        return staffId; 
    }
    
    // GymSystem needs this exact getter method to compile report details
    public double getSalary() { 
        return salary; 
    }
    
    public void setSalary(double salary) { 
        this.salary = salary; 
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
public class PolyStaff extends Member {
    private static final long serialVersionUID = 1L;
    
    private String position;
    private String department;

    public PolyStaff(int memberId, String dateOfBirth, String gender, String position, String department, 
                     String firstName, String lastName, String address, String phone) {
        super(memberId, dateOfBirth, gender, firstName, lastName, address, phone);
        this.position = position;
        this.department = department;
    }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
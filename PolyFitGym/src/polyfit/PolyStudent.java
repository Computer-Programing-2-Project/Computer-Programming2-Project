/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
public class PolyStudent extends Member {
    private static final long serialVersionUID = 1L;
    
    private String course;
    private String team; // Optional field, can be empty string

    public PolyStudent(int memberId, String dateOfBirth, String gender, String course, String team, 
                       String firstName, String lastName, String address, String phone) {
        super(memberId, dateOfBirth, gender, firstName, lastName, address, phone);
        this.course = course;
        this.team = (team == null) ? "" : team;
    }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
}
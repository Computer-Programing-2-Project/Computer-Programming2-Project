/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *
 * @author ABC
 */
public class PolyStudent extends Member {
        private String course;
    private String team;

    public PolyStudent(String course, String team, int memberId, String dateOfBirth, String gender, PersonalTrainer trainer, int id, String fn, String ln, String ad, String ph) {
        super(memberId, dateOfBirth, gender, trainer, id, fn, ln, ad, ph);
        this.course = course;
        this.team = team;
    }

    

    public String getCourse() {
        return course;
    }

    public String getTeam() {
        return team;
    }

        @Override
    public int getId() {
        return id;
    }

        @Override
    public String getFirstName() {
        return firstName;
    }

        @Override
    public String getLastName() {
        return lastName;
    }

        @Override
    public String getAddress() {
        return address;
    }

        @Override
    public String getPhone() {
        return phone;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setTeam(String team) {
        this.team = team;
    }

        @Override
    public void setId(int id) {
        this.id = id;
    }

        @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

        @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

        @Override
    public void setAddress(String address) {
        this.address = address;
    }

        @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

   
}



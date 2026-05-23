/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *
 * @author ABC
 */
public class PolySttaf extends Member {
        private String Position;
    private String department;

    public PolySttaf(String Position, String department, int memberId, String dateOfBirth, String gender, PersonalTrainer trainer, int id, String fn, String ln, String ad, String ph) {
        super(memberId, dateOfBirth, gender, trainer, id, fn, ln, ad, ph);
        this.Position = Position;
        this.department = department;
    }

    

    public String getPosition() {
        return Position;
    }

    public String getDepartment() {
        return department;
    }

        @Override
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setDepartment(String department) {
        this.department = department;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *
 * @author huzaifasuhail   765
 */
public class Member extends Person {
    private int memberId;
    private String dateOfBirth;
    private String gender;
    private PersonalTrainer trainer;

    public Member(int memberId, String dateOfBirth, String gender, PersonalTrainer trainer, int id, String fn, String ln, String ad, String ph) {
        super(id, fn, ln, ad, ph);
        this.memberId = memberId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.trainer = trainer;
    }

   

    

    public int getMemberId() {
        return memberId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public PersonalTrainer getTrainer() {
        return trainer;
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

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTrainer(PersonalTrainer trainer) {
        this.trainer = trainer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    

   

    @Override
    public String serialize() {
        return super.serialize() + ","+ memberId + dateOfBirth + "," + gender;
    }

    @Override
    public void deserialize(String data) {
        super.deserialize(data);
        String[] parts = data.split(",");
        if (parts.length >= 10) {
            dateOfBirth = parts[5];
            gender = parts[6];
            ;
            
        }
    }
}

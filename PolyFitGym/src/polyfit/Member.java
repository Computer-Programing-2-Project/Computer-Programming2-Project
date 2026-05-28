/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polyfit;

/**
 *
 * @author huzaifasuhail
 */
 public abstract class Member extends Person {
    private static final long serialVersionUID = 1L;
    
    private int memberId;
    private String dateOfBirth;
    private String gender;
    private PersonalTrainer trainer; // Optional field, can be null

    public Member(int memberId, String dateOfBirth, String gender, String firstName, String lastName, String address, String phone) {
        super(firstName, lastName, address, phone);
        this.memberId = memberId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.trainer = null;
    }

    public int getMemberId() { return memberId; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dob) { this.dateOfBirth = dob; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public PersonalTrainer getTrainer() { return trainer; }
    public void setTrainer(PersonalTrainer trainer) { this.trainer = trainer; }
}
